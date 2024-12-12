INSERT INTO company
    (id, name, nip, regon, contact_mail, krs, owner_first_name, owner_last_name, creation_date)
VALUES
(1, 'Wołek sp. z.ł.o', '8117047582', '730335907', 'mymail@gmail.com', '0000385161', 'Mateusz', 'Wołek', '2023-10-03'),
(2, 'STL', '8117047582', '730335907', 'mymail3@gmail.com', '0000385161', 'Janusz', 'Kiełb', '2023-10-13');

INSERT INTO company
(id, name, nip, regon, contact_mail, krs, owner_first_name, owner_last_name, profile, description, creation_date)
VALUES (3, 'PKP', '8117047582', '730335907', 'mymail4@gmail.com', '0000385161', 'Dorota', 'Jasic', '/profile', 'We are an awesome company', '2023-11-13');

INSERT INTO customer (id, first_name, last_name, creation_date) VALUES (1, 'Jerzy', 'Kowalec', '2023-10-04');

INSERT INTO service (id, name) VALUES (1, 'Deratyzacja');

INSERT INTO comment_company (id, rate) VALUES
(1, 5.0),
(2, 3.0),
(3, 2.0);

INSERT INTO contract (id, company_id, company_comment_id, service_id) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 2, 3, 1);

INSERT INTO scope (id, value) VALUES
(1, 10),
(2, 100),
(3, 200);

INSERT INTO province (id, name) VALUES (1,'Lubuskie');

INSERT INTO city (id, name, province_id) VALUES
(1, 'Zielona Góra', 1),
(2, 'Sulechów', 1),
(3, 'Nowa Sól', 1);

INSERT INTO company_region (id, company_id, region_id, scope_id) VALUES
(1, 1, 1, 3),
(2, 1, 2, 1),
(3, 1, 3, 2),
(4, 2, 3, 1),
(5, 3, 2, 2);