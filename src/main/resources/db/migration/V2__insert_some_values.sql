INSERT INTO point_contacts_info
(
    phone_number,
    site
) VALUES
    ('+7-999-123-45-67', 'https://pickup-point1.ru'),
    ('+7-999-987-65-43', 'https://pickup-point2.ru'),
    ('+7-999-555-44-33', 'https://pickup-point3.ru');

INSERT INTO users
(
    username,
    email,
    phone,
    password,
    role,
    first_name,
    last_name
) VALUES
    ('ivanov_user', 'ivanov@mail.ru', '+7-911-111-11-11', 'password123', 'USER', 'Иван', 'Иванов'),
    ('petrov_user', 'petrov@mail.ru', '+7-922-222-22-22', 'password456', 'USER', 'Петр', 'Петров'),
    ('sidorov_user', 'sidorov@mail.ru', '+7-933-333-33-33', 'password789', 'ADMIN', 'Сидор', 'Сидоров');

INSERT INTO couriers
(
    username,
    email,
    phone,
    password,
    role,
    first_name,
    last_name,
    vehicle_type
) VALUES
    ('courier_alex', 'alex.courier@mail.ru', '+7-944-444-44-44', 'courierpass1', 'COURIER', 'Алексей', 'Курьеров', 'CAR'),
    ('courier_max', 'max.courier@mail.ru', '+7-955-555-55-55', 'courierpass2', 'COURIER', 'Максим', 'Доставкин', 'BICYCLE'),
    ('courier_anna', 'anna.courier@mail.ru', '+7-966-666-66-66', 'courierpass3', 'COURIER', 'Анна', 'Быстрая', 'SCOOTER');

INSERT INTO pickup_points
(
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
) VALUES
    (1, 4.8, 'Россия', 'Московская область', 'Москва', 'Тверская', '15', '1', '09:00', '21:00'),
    (2, 4.5, 'Россия', 'Московская область', 'Москва', 'Арбат', '22', '3', '10:00', '20:00'),
    (3, 4.9, 'Россия', 'Московская область', 'Химки', 'Ленинградская', '8', '2', '08:00', '22:00');

INSERT INTO products
(
    id,
    name,
    description,
    price,
    discount,
    category
) VALUES
    (nextval('products_id_seq'), 'Смартфон Samsung Galaxy', 'Мощный смартфон с отличной камерой', 29999.99, 10.0, 'ELECTRONICS'),
    (nextval('products_id_seq'), 'Ноутбук ASUS', 'Игровой ноутбук с процессором Intel Core i7', 89999.99, 15.0, 'ELECTRONICS'),
    (nextval('products_id_seq'), 'Футболка хлопковая', 'Комфортная футболка из 100% хлопка', 1999.99, 5.0, 'CLOTHING'),
    (nextval('products_id_seq'), 'Книга "Война и мир"', 'Классическое произведение Л.Н. Толстого', 1500.00, 0.0, 'BOOKS');

INSERT INTO product_photos
(
    product_id,
    photos
) VALUES
    (1, 'samsung_galaxy_1.jpg'),
    (1, 'samsung_galaxy_2.jpg'),
    (2, 'asus_laptop_1.jpg'),
    (2, 'asus_laptop_2.jpg'),
    (3, 'tshirt_1.jpg'),
    (4, 'war_and_peace_cover.jpg');

INSERT INTO delivery
(
    type,
    delivery_time,
    created_at,
    created_by_user_id,
    status, pickup_point_address,
    courier_id,
    delivery_address
) VALUES
    ('PICKUP', '2024-01-15 14:00:00', '2024-01-14 10:30:00', 1, 'IN_TRANSIT', 'Россия, Московская область, Москва, Тверская, 15, 1', NULL, NULL),
    ('PICKUP', '2024-01-16 16:30:00', '2024-01-15 09:15:00', 2, 'CREATED', 'Россия, Московская область, Москва, Арбат, 22, 3', NULL, NULL),
    ('COURIER', '2024-01-15 18:00:00', '2024-01-15 12:00:00', 3, 'DELIVERED', NULL, 1, 'Россия, Москва, ул. Пушкина, д. 10, кв. 25'),
    ('COURIER', '2024-01-16 13:00:00', '2024-01-16 09:45:00', 1, 'IN_PROGRESS', NULL, 2, 'Россия, Химки, ул. Московская, д. 5, кв. 12');