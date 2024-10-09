INSERT INTO account (id) VALUES (1);
INSERT INTO account (id) VALUES (2);
INSERT INTO account (id) VALUES (3);
INSERT INTO account (id) VALUES (4);
INSERT INTO account (id) VALUES (5);
INSERT INTO account (id) VALUES (6);
INSERT INTO account (id) VALUES (7);

INSERT INTO company (id, account_id) VALUES (1, 1);
INSERT INTO company (id, account_id) VALUES (2, 2);
INSERT INTO company (id, account_id) VALUES (3, 3);
INSERT INTO company (id, account_id) VALUES (4, 7);

INSERT INTO customer (id, account_id, first_name, last_name) VALUES (1, 4, 'Mateusz', 'Wołek');
INSERT INTO customer (id, account_id, first_name, last_name) VALUES (2, 5, 'Janusz', 'Kiełb');
INSERT INTO customer (id, account_id, first_name, last_name, profile) VALUES (3, 6, 'Dorota', 'Jasic', '/profile');

INSERT INTO contract_status (id, value) VALUES (1, 'W trakcie');
INSERT INTO contract_status (id, value) VALUES (2, 'Współpraca zakończona');
INSERT INTO contract_status (id, value) VALUES (3, 'Współpraca przerwana z winy klienta');
INSERT INTO contract_status (id, value) VALUES (4, 'Współpraca przerwana z winy kontrahenta');

INSERT INTO contract (id, title, company_id, customer_id, status_id, last_actualisation)
VALUES (1, 'Bathroom renovation', 1, 1, 1, '2023-10-08');
INSERT INTO contract (id, title, company_id, customer_id, status_id, last_actualisation)
VALUES (2, 'Roof renovation', 1, 1, 2, null);
INSERT INTO contract (id, title, company_id, customer_id, status_id, last_actualisation)
VALUES (3, 'Sidewalk project', 2, 2, 3, null);
INSERT INTO contract (id, title, company_id, customer_id, status_id, last_actualisation)
VALUES (4, 'Fence project', 1, 2, 4, null);

INSERT INTO message (id, contract_id, creation_date) VALUES (1, 1, '2023-10-03');
INSERT INTO message (id, contract_id, creation_date) VALUES (2, 1, '2023-10-04');
INSERT INTO message (id, contract_id, creation_date) VALUES (3, 1, '2023-10-05');
INSERT INTO message (id, contract_id, creation_date) VALUES (4, 1, '2023-10-06');
INSERT INTO message (id, contract_id, creation_date) VALUES (5, 1, '2023-10-07');
INSERT INTO message (id, contract_id, creation_date) VALUES (6, 1, '2023-10-08');

INSERT INTO user_who_do_not_view_message (message_id, account_id) VALUES (3, 1);
INSERT INTO user_who_do_not_view_message (message_id, account_id) VALUES (2, 4);