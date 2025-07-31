CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE post (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(50),
    content VARCHAR(2000)
);

CREATE TABLE post_category (
    post_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (post_id, category_id),
    FOREIGN KEY (post_id) REFERENCES post(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);
