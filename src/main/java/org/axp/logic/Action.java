package org.axp.logic;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.Ticket;
import org.axp.entity.User;
import org.axp.entity.User.Role;

@ApplicationScoped
public class Action {

    public void createTicket(User createdByUser, String description, Ticket.Priority priority) {
        // Implementation to create a new ticket and add it to the 'tickets' map.
    }

    public void assignTicket(int ticketId, User assignedToUser) {
        // Implementation to assign a ticket to a specific user.
    }

    public void closeTicket(int ticketId, String response) {
        // Implementation to close a ticket and add the response provided.
    }

    public void createUser(User adminUser, String username, String email, Role role) {
        // Implementation to create a new user and add it to the 'users' map.
    }

    public void updateUser(User adminUser, int userId, String username, String email, Role role) {
        // Implementation to update user information by admin.
    }

    public void deleteUser(User adminUser, int userId) {
        // Implementation to delete a user by admin.
    }

    public void viewOpenTickets(User agentUser) {
        // Implementation to view a list of open tickets assigned to the agent.
    }

    public void viewUserTickets(User customerUser) {
        // Implementation to view a list of tickets submitted by the customer.
    }

    public void viewAllTickets(User adminUser) {
        // Implementation to view a list of all tickets in the system.
    }

    public void viewAllUsers(User adminUser) {
        // Implementation to view a list of all users in the system.
    }

    public void closeAllTickets(User adminUser) {
        // Implementation to close all open tickets in the system by admin.
    }

    public void escalateTicket(User agentUser, int ticketId) {
        // Implementation to escalate a ticket's priority by an agent.
    }

    public void reopenTicket(User agentUser, int ticketId) {
        // Implementation to reopen a closed ticket by an agent.
    }

    public void searchTickets(User user, String keyword) {
        // Implementation to search for tickets based on a keyword in the description or response.
    }

    public void changeTicketStatus(User agentUser, int ticketId, boolean isOpen) {
        // Implementation to change the status of a ticket (open/closed) by an agent.
    }

    public void viewAgentAssignedTickets(User agentUser) {
        // Implementation to view a list of tickets assigned to a specific agent.
    }

}
