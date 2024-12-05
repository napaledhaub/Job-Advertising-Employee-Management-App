DROP TABLE IF EXISTS service_menu CASCADE;
DROP TABLE IF EXISTS exercise CASCADE;
DROP TABLE IF EXISTS participant CASCADE;
DROP TABLE IF EXISTS auth_token CASCADE;
DROP TABLE IF EXISTS subscription CASCADE;

CREATE TABLE service_menu (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price_per_session DECIMAL(10, 2) NOT NULL,
    total_sessions INT NOT NULL,
    schedule TEXT,
    duration_in_minutes INT NOT NULL
);

CREATE TABLE exercise (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    duration_in_minutes INT NOT NULL,
    description TEXT,
    service_menu_id BIGINT,
    FOREIGN KEY (service_menu_id) REFERENCES service_menu(id) ON DELETE SET NULL
);

CREATE TABLE participant (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(20),
    is_verified BOOLEAN,
    verification_code VARCHAR(50),
    reset_token VARCHAR(255),
    payment_status BOOLEAN,
    payment_otp VARCHAR(6),
    payment_otp_expiration TIMESTAMP,
    bill_amount DECIMAL(10, 2),
    is_bill_verified BOOLEAN,
    credit_card_info VARCHAR(255)
);

CREATE TABLE auth_token (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiration_date_time TIMESTAMP NOT NULL,
    participant_id BIGINT,
    FOREIGN KEY (participant_id) REFERENCES participant(id) ON DELETE CASCADE
);

CREATE TABLE subscription (
    id BIGSERIAL PRIMARY KEY,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    remaining_sessions INT NOT NULL,
    participant_id BIGINT,
    service_menu_id BIGINT,
    FOREIGN KEY (participant_id) REFERENCES participant(id) ON DELETE CASCADE,
    FOREIGN KEY (service_menu_id) REFERENCES service_menu(id) ON DELETE CASCADE
);
