package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.domain.ProjectStatisticDto;
import org.axp.entity.ProjectStatistic;

@ApplicationScoped
public class ProjectStatisticTransformer {

    public ProjectStatisticDto transform(ProjectStatistic statistic) {

        if (statistic.serviceRate() > statistic.arrivalRate()) {
            // Розрахунок статистик для нормального стану системи
            return new ProjectStatisticDto(
                    statistic.projectId(), statistic.year(), statistic.week(),
                    statistic.arrivalRate(), statistic.serviceRate(),
                    statistic.arrivalRate() / statistic.serviceRate(),
                    statistic.arrivalRate() / (statistic.serviceRate() - statistic.arrivalRate()),
                    1.0f / (statistic.serviceRate() - statistic.arrivalRate()),
                    (statistic.arrivalRate() * statistic.arrivalRate()) / (statistic.serviceRate() * (statistic.serviceRate() - statistic.arrivalRate())),
                    statistic.arrivalRate() / (statistic.serviceRate() * (statistic.serviceRate() - statistic.arrivalRate()))
            );
        } else {
            // Обробка перенавантаженої системи
            return new ProjectStatisticDto(
                    statistic.projectId(), statistic.year(), statistic.week(),
                    statistic.arrivalRate(), statistic.serviceRate(),
                    statistic.arrivalRate() / statistic.serviceRate(),
                    Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY
            );
        }
    }

}
