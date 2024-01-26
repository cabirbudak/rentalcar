--liquibase formatted sql

INSERT INTO personality_data("driver_license_number", "last_name", "name", "passport_number", "phone")
VALUES ('555 555', 'ATAR', 'FATMA', '5555 555555', '555 555 555');

INSERT INTO users("username", "password", "active", "personality_data_id")
VALUES ('q', 'q', 'true', 1);

INSERT INTO user_roles("user_id", "roles")
VALUES ('1', 'ROLE_ADMIN');

INSERT INTO public.cars (active, car_body, color, engine, gearbox, manufacturer, model, plate, power, price,
                         wheel_drive, "year")
VALUES (true, 'CAR_BODY_SUV', 'BLACK', 'ENGINE_DIESEL', 'AUTO', 'Toyota', 'Land Cruiser Prado', 'А001АА154', 250,
        12000, 'ALL_WHEEL_DRIVE', 2023),
       (true, 'CAR_BODY_WAGON', 'SILVER', 'ENGINE_PETROL', 'AUTO', 'Subaru', 'Outback', 'А002АА154', 250, 12000,
        'ALL_WHEEL_DRIVE', 2023),
       (true, 'CAR_BODY_CROSSOVER', 'WHITE', 'ENGINE_PETROL', 'AUTO', 'KIA', 'Sorento', 'А003АА154', 170, 8000,
        'FRONT_WHEEL_DRIVE', 2023),
       (true, 'CAR_BODY_CROSSOVER', 'BLACK', 'ENGINE_PETROL', 'AUTO', 'Mazda', 'CX-5', 'А004АА154', 170, 8000,
        'FRONT_WHEEL_DRIVE', 2023);

INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (4, 'CLASS_COMFORT');
INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (4, 'CLASS_SUV');
INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (3, 'CLASS_COMFORT');
INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (3, 'CLASS_SUV');
INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (2, 'CLASS_COMFORT');
INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (2, 'CLASS_SUV');
INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (1, 'CLASS_COMFORT');
INSERT INTO public.cars_classifications (car_id, classifications)
VALUES (1, 'CLASS_SUV');

INSERT INTO public.personality_data (driver_license_number, last_name, name, passport_number, phone)
VALUES ('555 555', 'CAN', 'ALI', '5555 55555', '9999999');
