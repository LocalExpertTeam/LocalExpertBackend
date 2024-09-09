INSERT INTO account (id, mail) VALUES (1, 'mat@gmail.com');
INSERT INTO account (id, mail) VALUES (2, 'pablo@gmail.com');
INSERT INTO customer (id, first_name, last_name, phone, profile, preferred_contact_time, preferred_contact_method, account_id, creation_date)
VALUES (1, 'Jerzy', 'Kowalski', '999999183', null, 'Pn - Pt 8 - 16', 'PHONE', 1, '2023-10-04');
INSERT INTO customer (id, first_name, last_name, phone, profile, preferred_contact_time, preferred_contact_method, account_id, creation_date)
VALUES (2, 'Marianna', 'Sen','998899183', null, null, 'NONE', 2, '2023-11-04');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (1, 5.0, 'Very nice and kind client :)', '2023-10-04');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (2, 4.0, 'I am enough of this guy!', '2023-11-04');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (3, 4.0, 'Well done!', '2023-11-04');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (4, 2.0, 'Very annoying client.', '2023-11-14');
INSERT INTO comment_customer (id, rate, description, added_date) VALUES (5, 2.0, '!?$#', '2023-11-14');
INSERT INTO contract (id, customer_comment_id, customer_id) VALUES (1, 1, 1);
INSERT INTO contract (id, customer_comment_id, customer_id) VALUES (2, 2, 1);
INSERT INTO contract (id, customer_comment_id, customer_id) VALUES (3, 3, 1);
INSERT INTO contract (id, customer_comment_id, customer_id) VALUES (4, 4, 1);
INSERT INTO contract (id, customer_comment_id, customer_id) VALUES (5, 5, 2);
