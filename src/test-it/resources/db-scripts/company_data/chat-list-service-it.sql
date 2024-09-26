INSERT INTO account (id) VALUES (1);
INSERT INTO account (id) VALUES (2);
INSERT INTO account (id) VALUES (3);
INSERT INTO account (id) VALUES (4);
INSERT INTO account (id) VALUES (5);
INSERT INTO account (id) VALUES (6);

INSERT INTO company (id, owner_first_name, owner_last_name, account_id)
    VALUES (1, 'Mateusz', 'Wołek', 1);
INSERT INTO company (id, owner_first_name, owner_last_name, account_id)
    VALUES (2, 'Janusz', 'Kiełb', 2);
INSERT INTO company (id, owner_first_name, owner_last_name, profile, account_id)
    VALUES (3, 'Dorota', 'Jasic', '/profile', 3);
INSERT INTO customer (id, account_id) VALUES (1, 4);
INSERT INTO customer (id, account_id) VALUES (2, 5);
INSERT INTO customer (id, account_id) VALUES (3, 6);

INSERT INTO contract_status (id, value) VALUES (1, 'In progress');

INSERT INTO contract (id, title, company_id, customer_id, status_id, last_actualisation)
VALUES (1, 'Bathroom renovation', 1, 1, 1, '2023-10-08');
INSERT INTO contract (id, title, company_id, customer_id, status_id, last_actualisation)
VALUES (2, 'Roof renovation', 1, 1, 1, null);
INSERT INTO contract (id, title, company_id, customer_id, status_id, last_actualisation)
VALUES (3, 'Sidewalk project', 2, 2, 1, null);

INSERT INTO message (id, contract_id, creation_date) VALUES (1, 1, '2023-10-03');
INSERT INTO message (id, contract_id, creation_date) VALUES (2, 1, '2023-10-04');
INSERT INTO message (id, contract_id, creation_date) VALUES (3, 1, '2023-10-05');
INSERT INTO message (id, contract_id, creation_date) VALUES (4, 1, '2023-10-06');
INSERT INTO message (id, contract_id, creation_date) VALUES (5, 1, '2023-10-07');
INSERT INTO message (id, contract_id, creation_date) VALUES (6, 1, '2023-10-08');

INSERT INTO user_who_do_not_view_message (message_id, account_id) VALUES (3, 1);
INSERT INTO user_who_do_not_view_message (message_id, account_id) VALUES (2, 4);