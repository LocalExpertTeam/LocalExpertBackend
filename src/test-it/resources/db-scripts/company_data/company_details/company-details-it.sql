INSERT INTO account (id, mail, password, account_type)
VALUES (13,"SPGlobal@issuu.com","aasds","company");

INSERT INTO company (
id, name, creation_date, nip, regon, has_free_commute,
contact_mail, krs, owner_first_name, owner_last_name,is_phone_visible, account_id,
preferred_contact_method, has_free_pricing, is_free_diagnostics, profile, gallery,
phone,
description)
VALUES (1,"S&P Global","2023-09-18",1600000000,0000,0,
"SPGlobal@issuu.com",000000000,"Eyde","Baton",0,13,
"none",1,0, "s&pglobal.webp","s&pglobal1.jpg,s&pglobal2.jpg, s&pglobal3.jpg",
"123321123",
"S&P Global Builders jest wiodącą firmą budowlaną");

INSERT INTO scope (id, value) VALUES
(1, 10);

INSERT INTO province (id, name) VALUES (1,'Lubuskie');

INSERT INTO city (id, name, province_id) VALUES
(1, 'Zielona Góra', 1),
(2, 'Sulechów', 1);

INSERT INTO company_region (id, company_id, region_id, scope_id) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1);

INSERT INTO service (id, name) VALUES (1, "Malowanie"), (2, "Dekarstwo");

INSERT INTO company_service (id, company_id, service_id) VALUES (1, 1, 1), (2, 1, 2);

INSERT INTO price_list (id, service_name, company_id, price_min, price_max)
VALUES
(1, "B", 1, 150, 200),
(2, "A", 1, 150, NULL);
