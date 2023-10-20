CREATE TABLE IF NOT EXISTS helpdesk.comment
(
    project_id     text,
    ticket_id      int,
    date_submitted timestamp,
    content        text,
    user_id        uuid,
    PRIMARY KEY (project_id, ticket_id, date_submitted)
) WITH CLUSTERING ORDER BY (ticket_id DESC, date_submitted DESC);