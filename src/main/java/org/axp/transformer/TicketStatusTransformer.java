package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.TicketStatus;
import org.axp.rest.TicketStatusDto;

@ApplicationScoped
public class TicketStatusTransformer {

    public TicketStatusDto transform(TicketStatus status) {
        return new TicketStatusDto(status.getId(), status.getName(), status.getDescription());
    }

    public TicketStatus transform(TicketStatusDto dto) {
        return new TicketStatus(dto.getId(), dto.getName(), dto.getDescription());
    }

}
