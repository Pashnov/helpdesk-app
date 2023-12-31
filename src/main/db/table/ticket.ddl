CREATE TABLE IF NOT EXISTS helpdesk.ticket
(
    project_id     text,
    ticket_id      int,
    name           text,
    description    text,
    is_active      boolean,
    date_submitted timestamp,
    priority       text,
    reporter_user  uuid,
    assignee_user  uuid,
    status_id      int,
    PRIMARY KEY (project_id, ticket_id)
);