CREATE TABLE cars (id int NOT NULL PRIMARY KEY, model char(50), brand char(50), fuelType char(50), performance int, type char(50), seats int, doors int, price int, booked int DEFAULT(0));
CREATE TABLE rental (id int NOT NULL PRIMARY KEY, startDate date, endDate date, carID int, customerID int);
CREATE TABLE customers (id int NOT NULL PRIMARY KEY, firstName char(50), lastName char(50), adress char(50));
INSERT INTO cars (id, model, brand, fuelType, performance, seats, seats, doors, price) VALUES (0, 'Vierer', 'Bmw', 'Diesel', 188, 'Coupe', 4, 3, 70), (1, 'Dreier', 'Bmw', 'Diesel', 256, 'Kombi', 5, 5, 60), (2, 'LaFerrari', 'Ferrari', 'Benzin', 950, 'Sport', 2, 3, 200), (3, 'Golf', 'VW', 'Diesel', 150, 'Kompakt', 5, 5, 30), (4, 'Cherokee', 'Jeep', 'Diesel', 165, 'Gelaend_date', 5, 5, 40), (5, 'RS3', 'Audi', 'Benzin', 400, 'Kompakt', 5, 5, 70), (6, 'UP!', 'VW', 'Benzin', 80, 'Kleinwagen', 4, 3, 15), (7, 'M4Competition', 'Bmw', 'Benzin', 510, 'Coupe', 4, 3, 150), (8, 'Golf Gti TCR', 'VW', 'Benzin', 290, 'Kompaktwagen', 5, 3, 60), (9, 'Passat', 'VW', 'Diesel', 150, 'Kombi', 5, 5, 40), (10, 'M8Competition', 'Bmw', 'Benzin', 625, 'Coupe', 4, 3, 180), (11, '918Spyder', 'Porsche', 'Benzin', 867, 'Sport', 2, 3, 210);
INSERT INTO customers (id, firstName, lastName, adress) VALUES (0, 'Fritz', 'Fischer', 'Erlangen'), (1, 'Bilde', 'Bauer', 'Muenchen'), (2, 'Merbert', 'Maurer', 'Nuernberg'), (3, 'Willi', 'Wombat', 'Bamberg');