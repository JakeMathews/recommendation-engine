CREATE TABLE recommendations
(
    recommendation_id  VARCHAR(255) PRIMARY KEY,
    member_id          VARCHAR(255) NOT NULL,
    item_id            VARCHAR(255) NOT NULL,
    alternate_item_ids VARCHAR(255)[],
    generated_at       TIMESTAMP    NOT NULL
);
