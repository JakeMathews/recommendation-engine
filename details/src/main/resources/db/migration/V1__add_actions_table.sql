CREATE TABLE actions
(
    action_id VARCHAR(255) PRIMARY KEY,
    member_id VARCHAR(255) NOT NULL,
    item_id   VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP    NOT NULL
);
