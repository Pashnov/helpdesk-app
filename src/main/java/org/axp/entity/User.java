package org.axp.entity;

import lombok.Data;

@Data
public class User {

    private int userId;
    private String username;
    private String email;
    private Role role;

    // Constructor, getters, and setters.

    public enum Role {
        CUSTOMER, AGENT, ADMIN, SUPERUSER;
    }

    /*
    * a. Customer Role:

Customers are users who submit support tickets seeking assistance with their issues.
Actions:
Create a new ticket: Customers can create new support tickets to request help for their problems.
View their own tickets: Customers can view the status and details of the tickets they have submitted.
View responses: Customers can view responses provided by helpdesk agents for their tickets.
*

b. Agent Role:

Agents are support staff responsible for handling and resolving customer tickets.
Actions:
View open tickets: Agents can view a list of open tickets that need attention.
Assign tickets: Agents can assign open tickets to themselves for resolution.
Provide responses: Agents can respond to tickets and provide assistance to customers.
Close tickets: Agents can mark tickets as resolved and provide a final response.
*

c. Admin Role:

Admins have overall access to manage both users and tickets in the helpdesk application.
Actions:
View all tickets: Admins can view a list of all tickets in the system, regardless of status.
View all users: Admins can view a list of all users registered in the system.
Create users: Admins can create new user accounts and assign roles to them.
Edit users: Admins can update user information, such as usernames and emails.
Delete users: Admins can remove user accounts from the system.
Close tickets: Admins can close tickets on behalf of agents if needed.
    * */

}
