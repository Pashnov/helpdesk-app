package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.CommentDao;
import org.axp.dao.ProjectStatisticDao;
import org.axp.dao.TicketDao;
import org.axp.domain.ProjectStatisticDto;
import org.axp.entity.Comment;
import org.axp.entity.ProjectStatistic;
import org.axp.entity.Ticket;
import org.axp.transformer.ProjectStatisticTransformer;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ApplicationScoped
public class ProjectStatisticService {

    record StartEndDate(LocalDateTime start, LocalDateTime end) {}

    private static final int WORK_START_HOUR = 8; // 8 AM
    private static final int LUNCH_START_HOUR = 12; // 12 PM
    private static final int LUNCH_END_HOUR = 13; // 1 PM
    private static final int WORK_END_HOUR = 17; // 5 PM
    private static final int WORKING_HOURS_PER_DAY = 8;

    @Inject
    TicketDao ticketDao;

    @Inject
    CommentDao commentDao;

    @Inject
    ProjectStatisticDao projectStatisticDao;

    @Inject
    ProjectStatisticTransformer transformer;

    public List<ProjectStatisticDto> getStatisticForProject(String projectId) {
        List<ProjectStatistic> all = projectStatisticDao.findAllByProjectId(projectId).all();
        return all.stream().map(transformer::transform).toList();
    }

    public void calculateStatisticsForCurrentWeek(String projectId, boolean currentTime) {
        LocalDate currentDate = LocalDate.now();
        TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int year = currentDate.getYear();
        int week = currentDate.get(weekOfYear);

        StartEndDate weekRange = getStartAndEndOfWeek(year, week);
        List<Ticket> ticketsForCurrentWeek = ticketDao.findByProjectIdInAndRangeOfDateSubmitted(projectId, weekRange.start(), weekRange.end()).all();
        List<Ticket> ticketsFor3weeks = ticketDao.findByProjectIdInAndRangeOfDateSubmitted(projectId, weekRange.start().minusWeeks(2), weekRange.end()).all();

        var workHours = currentTime ? countWorkingHoursForCurrentTime() : 5*8;

        float lambda = calculateArrivalRate(ticketsForCurrentWeek, workHours);
        float mu = calculateServiceRate(ticketsFor3weeks, weekRange.start(), workHours);
        // Інші обчислення...

        projectStatisticDao.save(new ProjectStatistic(projectId, year, week, lambda, mu));
    }

    public void calculateStatisticsForCurrentWeek(String projectId, int week, boolean currentTime) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();

        StartEndDate weekRange = getStartAndEndOfWeek(year, week);
        List<Ticket> ticketsForCurrentWeek = ticketDao.findByProjectIdInAndRangeOfDateSubmitted(projectId, weekRange.start(), weekRange.end()).all();
        List<Ticket> ticketsFor3weeks = ticketDao.findByProjectIdInAndRangeOfDateSubmitted(projectId, weekRange.start().minusWeeks(2), weekRange.end()).all();

        var workHours = currentTime ? countWorkingHoursForCurrentTime() : 5*8;

        float lambda = calculateArrivalRate(ticketsForCurrentWeek, workHours);
        float mu = calculateServiceRate(ticketsFor3weeks, weekRange.start(), workHours);
        // Інші обчислення...

        projectStatisticDao.save(new ProjectStatistic(projectId, year, week, lambda, mu));
    }

    private int countWorkingHoursForCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfWorkingDay = now.with(LocalTime.of(WORK_START_HOUR, 0));

        // If it's before work hours, count from 0.
        if (now.isBefore(startOfWorkingDay)) {
            return 0;
        }

        // Calculate the number of full working days since Monday (excluding today).
        int fullDaysWorked = Math.max(0, now.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        long workingHours = (long) fullDaysWorked * WORKING_HOURS_PER_DAY;

        // Add hours worked in the current day.
        LocalDateTime startOfLunch = now.with(LocalTime.of(LUNCH_START_HOUR, 0));
        LocalDateTime endOfLunch = now.with(LocalTime.of(LUNCH_END_HOUR, 0));
        LocalDateTime endOfWorkingDay = now.with(LocalTime.of(WORK_END_HOUR, 0));

        if (now.isAfter(endOfWorkingDay)) {
            // If after work hours, add all of today's working hours.
            workingHours += WORKING_HOURS_PER_DAY;
        } else if (now.isAfter(endOfLunch)) {
            // If after lunch, add hours worked before and after lunch.
            workingHours += Duration.between(startOfWorkingDay, startOfLunch).toHours();
            workingHours += Duration.between(endOfLunch, now).toHours();
        } else if (now.isAfter(startOfLunch)) {
            // If during lunch, add hours worked before lunch.
            workingHours += Duration.between(startOfWorkingDay, startOfLunch).toHours();
        } else {
            // If before lunch, add hours worked so far.
            workingHours += Duration.between(startOfWorkingDay, now).toHours();
        }

        return (int)workingHours;
    }

    private float calculateArrivalRate(List<Ticket> tickets, int workHours) {
        String query = "SELECT COUNT(*) FROM helpdesk.ticket WHERE project_id = ? AND date_submitted >= ? AND date_submitted <= ?";
        long ticketCount = tickets.size();
        return ((float) ticketCount) / workHours;
    }

    private float calculateServiceRate(List<Ticket> tickets, LocalDateTime startOfWeek, int workHours) {
        List<Comment> closedTicketsForCurrentWeek = tickets.stream()
                .map(ticket -> commentDao.findLastComment(ticket.projectId(), ticket.id()))
                .flatMap(Optional::stream)
                .filter(comment -> "The ticket was closed".equals(comment.content()))
                .filter(comment -> comment.dateSubmitted().isAfter(startOfWeek))
                .toList();
        String query = "SELECT COUNT(*) FROM helpdesk.comment WHERE project_id = ? AND date_submitted >= ? AND date_submitted <= ?";

        long commentCount = closedTicketsForCurrentWeek.size() + 1; // we suppose that system has some extra free possibilities, so make + 1
        return ((float) commentCount) / workHours; // Поділіть кількість коментарів на кількість днів у тижні

    }

    private StartEndDate getStartAndEndOfWeek(int year, int week) {
        LocalDate start = LocalDate.ofYearDay(year, 1)
                .with(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(), week)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime startOfWeek = start.atStartOfDay();
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1).minusSeconds(1);
        return new StartEndDate(startOfWeek, endOfWeek);
    }

}
