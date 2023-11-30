CREATE TABLE IF NOT EXISTS helpdesk.project_statistic
(
    project_id   text,
    year         int,
    week         int,
    arrival_rate float,
    service_rate float,
    PRIMARY KEY ((project_id), year, week)
) WITH CLUSTERING ORDER BY (year DESC, week DESC);