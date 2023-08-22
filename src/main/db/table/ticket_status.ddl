CREATE TABLE IF NOT EXISTS helpdesk.ticket_status
(
    status_id   int,
    name        text,
    description text,
    PRIMARY KEY (status_id)
);