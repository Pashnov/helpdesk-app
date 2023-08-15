package org.axp.entity;

import lombok.Data;

import java.util.List;

@Data
public class TicketFlow {

    private int ticketFlowId;
    private List<TicketStatus> statusFlow;

}
