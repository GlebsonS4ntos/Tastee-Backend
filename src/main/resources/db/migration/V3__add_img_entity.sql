CREATE TABLE image (
    id BIGSERIAL PRIMARY KEY,
    url_img VARCHAR(255),
    img_id_cloudnary VARCHAR(255),
    post_id BIGINT UNIQUE,
    CONSTRAINT fk_image_post FOREIGN KEY (post_id) REFERENCES post(id)
);