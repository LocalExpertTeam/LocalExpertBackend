INSERT INTO company
    (id, name, nip, regon, contact_mail, krs, owner_first_name, owner_last_name, creation_date)
VALUES (1, 'Wołek sp. z.ł.o', '8117047582', '730335907', 'mymail@gmail.com', '0000385161', 'Mateusz', 'Wołek', '2023-10-03');
INSERT INTO company
(id, name, nip, regon, contact_mail, krs, owner_first_name, owner_last_name, creation_date)
VALUES (2, 'STL', '8117047582', '730335907', 'mymail3@gmail.com', '0000385161', 'Janusz', 'Kiełb', '2023-10-13');
INSERT INTO company
(id, name, nip, regon, contact_mail, krs, owner_first_name, owner_last_name, profile, description, creation_date)
VALUES (3, 'PKP', '8117047582', '730335907', 'mymail4@gmail.com', '0000385161', 'Dorota', 'Jasic', '/profile', 'We are an awesome company', '2023-11-13');
INSERT INTO customer (id, first_name, last_name, creation_date) VALUES (1, 'Jerzy', 'Kowalec', '2023-10-04');
INSERT INTO customer (id, first_name, last_name, creation_date) VALUES (2, 'Marianna', 'Sen', '2023-11-04');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (1, 5.0, 'Very nice and kind client :)', '2023-10-04');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (2, 3.0, 'I am enough of this guy!', '2023-11-04');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (3, 2.0, 'Very annoying client.', '2023-11-14');
INSERT INTO contract_status(id, value) VALUES (1, 'In progress');
INSERT INTO contract (id, company_id, customer_comment_id, status_id, customer_id) VALUES (1, 1, 1, 1, 1);
INSERT INTO contract (id, company_id, customer_comment_id, status_id, customer_id) VALUES (2, 1, 2, 1, 1);
INSERT INTO contract (id, company_id, customer_comment_id, status_id, customer_id) VALUES (3, 2, 3, 1, 1);
