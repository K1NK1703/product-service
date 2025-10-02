INSERT INTO point_contacts
(
    id,
    phone_number,
    site
) VALUES
    (gen_random_uuid(), '+7-999-111-11-11', 'https://pickup-point-1.ru'),
    (gen_random_uuid(), '+7-999-222-22-22', 'https://pickup-point-2.ru'),
    (gen_random_uuid(), '+7-999-333-33-33', 'https://pickup-point-3.ru');

INSERT INTO pickup_points
(
    id,
    contact_id,
    rating,
    country,
    region,
    city,
    street,
    house_number,
    entrance,
    open_time,
    close_time
) SELECT
    gen_random_uuid(),
    (SELECT id FROM point_contacts WHERE phone_number = '+7-999-111-11-11'),
    4.75, 'Россия', 'Московская область', 'Москва', 'Тверская', '15', '1', '09:00', '21:00'
UNION ALL
SELECT
    gen_random_uuid(),
    (SELECT id FROM point_contacts WHERE phone_number = '+7-999-222-22-22'),
    4.50, 'Россия', 'Ленинградская область', 'Санкт-Петербург', 'Невский проспект', '25', '2', '08:30', '20:30'
UNION ALL
SELECT
    gen_random_uuid(),
    (SELECT id FROM point_contacts WHERE phone_number = '+7-999-333-33-33'),
    4.90, 'Россия', 'Новосибирская область', 'Новосибирск', 'Красный проспект', '30', NULL, '10:00', '22:00';

INSERT INTO employees
(
    id,
    username,
    email,
    phone_number,
    password,
    role,
    first_name,
    last_name,
    salary,
    pickup_point_id
) SELECT
    gen_random_uuid(),
    'ivanov_i', 'ivanov@company.ru', '+7-911-111-11-11', '$2a$10$hashedpassword1', 'EMPLOYEE', 'Иван', 'Иванов', 75000.00,
    (SELECT id FROM pickup_points WHERE street = 'Тверская' AND house_number = '15')
UNION ALL
SELECT
    gen_random_uuid(),
    'petrov_p', 'petrov@company.ru', '+7-911-222-22-22', '$2a$10$hashedpassword2', 'EMPLOYEE', 'Петр', 'Петров', 55000.00,
    (SELECT id FROM pickup_points WHERE street = 'Тверская' AND house_number = '15')
UNION ALL
SELECT
    gen_random_uuid(),
    'sidorova_s', 'sidorova@company.ru', '+7-911-333-33-33', '$2a$10$hashedpassword3', 'EMPLOYEE', 'Светлана', 'Сидорова', 65000.00,
    (SELECT id FROM pickup_points WHERE street = 'Невский проспект' AND house_number = '25');