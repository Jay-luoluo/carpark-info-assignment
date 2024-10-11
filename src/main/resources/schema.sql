CREATE TABLE car_park (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         carpark_number VARCHAR(255) NOT NULL UNIQUE,
                         location VARCHAR(255),
                         free_parking BOOLEAN,
                         night_parking BOOLEAN,
                         max_vehicle_height FLOAT
);
CREATE TABLE user_favorites (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                carpark_id BIGINT NOT NULL,
                                added_on TIMESTAMP NOT NULL,
                                FOREIGN KEY (user_id) REFERENCES user(id),      -- Assuming 'user' table exists
                                FOREIGN KEY (carpark_id) REFERENCES car_park(id)
);

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL
    -- Additional user fields like email, etc.
);