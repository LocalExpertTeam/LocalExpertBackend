INSERT INTO account (id, mail, password, account_type) VALUES (1, 'mat@gmail.com', '$2a$12$hADZ0I8UVBmsPfbTV1PFQOneF/Agl4Kz31vBpCaHWCt2fMTJm7oh2', 'CUSTOMER');
INSERT INTO confirmation_code (id, confirmation_code, user_id, expiry_date) VALUES (1, '0436', 1, NOW());