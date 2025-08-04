CREATE TABLE image (
    id BIGSERIAL PRIMARY KEY,
    urlImg VARCHAR(255),
    imgId VARCHAR(255),
    post_id BIGINT UNIQUE,
    CONSTRAINT fk_image_post FOREIGN KEY (post_id) REFERENCES post(id)
);

ALTER TABLE post
ADD COLUMN image_id BIGINT;

ALTER TABLE post
ADD CONSTRAINT fk_post_image FOREIGN KEY (image_id) REFERENCES image(id);