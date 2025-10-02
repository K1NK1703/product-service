CREATE TABLE employees
(
    id              UUID         NOT NULL,
    username        VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    role            VARCHAR(255) NOT NULL,
    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) NOT NULL,
    salary          DECIMAL,
    pickup_point_id UUID,
    CONSTRAINT pk_employees PRIMARY KEY (id)
);

CREATE TABLE pickup_points
(
    id           UUID         NOT NULL,
    contact_id   UUID         NOT NULL,
    rating       DECIMAL(4, 2),
    country      VARCHAR(255) NOT NULL,
    region       VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    street       VARCHAR(255) NOT NULL,
    house_number VARCHAR(255) NOT NULL,
    entrance     VARCHAR(255),
    open_time    VARCHAR(255),
    close_time   VARCHAR(255),
    CONSTRAINT pk_pickup_points PRIMARY KEY (id)
);

CREATE TABLE point_contacts
(
    id           UUID         NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    site         VARCHAR(255),
    CONSTRAINT pk_point_contacts PRIMARY KEY (id)
);

ALTER TABLE employees
    ADD CONSTRAINT uc_employees_email UNIQUE (email);

ALTER TABLE employees
    ADD CONSTRAINT uc_employees_phone UNIQUE (phone_number);

ALTER TABLE employees
    ADD CONSTRAINT uc_employees_username UNIQUE (username);

ALTER TABLE point_contacts
    ADD CONSTRAINT uc_point_contacts_info_phone_number UNIQUE (phone_number);

ALTER TABLE employees
    ADD CONSTRAINT FK_EMPLOYEES_ON_PICKUP_POINT FOREIGN KEY (pickup_point_id) REFERENCES pickup_points (id);

ALTER TABLE pickup_points
    ADD CONSTRAINT FK_PICKUP_POINTS_ON_CONTACT FOREIGN KEY (contact_id) REFERENCES point_contacts (id);