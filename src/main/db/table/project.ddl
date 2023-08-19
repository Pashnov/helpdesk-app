CREATE TABLE IF NOT EXISTS helpdesk.user
(
    projectId   uuid,
    name text,
    PRIMARY KEY ((projectId), name)
);