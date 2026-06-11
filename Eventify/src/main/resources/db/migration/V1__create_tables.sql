
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE venues (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    places VARCHAR(255),
    city VARCHAR(255),
    capacity INT
);

CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    date VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    venue_id BIGINT,
    CONSTRAINT fk_event_venue FOREIGN KEY (venue_id) REFERENCES venues(id) ON DELETE SET NULL
);

CREATE TABLE event_categories (
    event_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (event_id, category_id),
    CONSTRAINT fk_ec_event FOREIGN KEY (event_id) REFERENCES events(id),
    CONSTRAINT fk_ec_category FOREIGN KEY (category_id) REFERENCES categories(id)
);
