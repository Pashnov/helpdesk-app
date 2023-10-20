package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.StatusDao;
import org.axp.domain.TicketStatusDto;
import org.axp.transformer.TicketStatusTransformer;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class TicketStatusService {

    @Inject
    StatusDao dao;

    @Inject
    TicketStatusTransformer transformer;

    private static final int cacheSize = 100;
    private final ConcurrentSkipListMap<Integer, TicketStatusDto> cache = new ConcurrentSkipListMap<>();

    public boolean save(TicketStatusDto statusDto) {
        return dao.save(transformer.transform(statusDto));
    }

    public List<TicketStatusDto> getAll() {
        return dao.findAll().all().stream().map(transformer::transform).collect(Collectors.toList());
    }

    public TicketStatusDto getById(Integer id) {
        TicketStatusDto statusDto = cache.get(id);
        if (Objects.isNull(statusDto)) {
            statusDto = transformer.transform(dao.findById(id));
            cache.put(id, statusDto);
            if (cache.size() > cacheSize) {
                cache.pollFirstEntry();
            }
        }
        return statusDto;
    }

    public boolean update(TicketStatusDto statusDto) {
        var updated = dao.update(transformer.transform(statusDto));
        if (updated) {
            cache.put(statusDto.id(), statusDto);
        }
        return updated;
    }

    public boolean delete(TicketStatusDto statusDto) {
        boolean deleteStatus = dao.delete(transformer.transform(statusDto));
        cache.remove(statusDto.id());
        return deleteStatus;
    }

}
