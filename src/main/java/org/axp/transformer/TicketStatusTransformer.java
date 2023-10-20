package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.TicketStatus;
import org.axp.domain.TicketStatusDto;

@ApplicationScoped
public class TicketStatusTransformer {

    public TicketStatusDto transform(TicketStatus status) {
        return new TicketStatusDto(status.id(), status.name(), status.description());
    }

    public TicketStatus transform(TicketStatusDto dto) {
        return new TicketStatus(dto.id(), dto.name(), dto.description());
    }

}
