package org.axp.entity;

import java.util.List;

public record TicketFlow(int ticketFlowId, List<TicketStatus> statusFlow) {}
