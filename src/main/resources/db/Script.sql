DROP TABLE IF EXISTS job_advertisement CASCADE;
DROP TABLE IF EXISTS job_position CASCADE;
DROP TABLE IF EXISTS job_seeker CASCADE;
DROP TABLE IF EXISTS system_employee CASCADE;
DROP TABLE IF EXISTS city CASCADE;
DROP TABLE IF EXISTS employer CASCADE;

CREATE TABLE city (
    city_id SERIAL PRIMARY KEY,
    city_name VARCHAR(255) NOT NULL
);

CREATE TABLE job_position (
    job_position_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE job_seeker (
    job_seeker_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    national_id VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    re_password VARCHAR(255) NOT NULL
);

CREATE TABLE employer (
    employer_id SERIAL PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    company_web_page VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    re_password VARCHAR(255) NOT NULL
);

CREATE TABLE system_employee (
    system_employee_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

CREATE TABLE job_advertisement (
    job_advertisement_id SERIAL PRIMARY KEY,
    open_position_count INT NOT NULL,
    description TEXT NOT NULL,
    min_salary INT NOT NULL,
    max_salary INT NOT NULL,
    job_release_date DATE NOT NULL,
    application_deadline DATE NOT NULL,
    job_position_id INT NOT NULL,
    city_id INT NOT NULL,
    employer_id INT NOT NULL,
    FOREIGN KEY (job_position_id) REFERENCES job_position(job_position_id) ON DELETE CASCADE,
    FOREIGN KEY (city_id) REFERENCES city(city_id) ON DELETE CASCADE,
    FOREIGN KEY (employer_id) REFERENCES employer(employer_id) ON DELETE CASCADE
);
