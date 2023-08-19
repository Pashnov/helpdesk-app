CREATE TABLE IF NOT EXISTS helpdesk.user
(
    user_id   uuid,
    username text,
    email    text,
    role     text,
    PRIMARY KEY ((user_id), username)
);