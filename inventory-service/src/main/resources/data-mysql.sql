
insert into inventories (inventory_id, type) values ('d846a5a7-2e1c-4c79-809c-4f3f471e826d', 'gpu');
insert into inventories (inventory_id, type) values ('g5j5sbn7-55uc-e5b9-h53c-b53bb35h826d', 'laptop');

insert into electronics (ein, inventory_id, status, usage_type, manufacturer, model, msrp, cost, total_options_cost) values ('JN8AS5MTXDW375966', 'd846a5a7-2e1c-4c79-809c-4f3f471e826d', 'AVAILABLE', 'NEW', 'Nvidia', 'RTX 4090 ti', 1999.00, 50.00, 2049.00);
insert into electronics (ein, inventory_id, status, usage_type, manufacturer, model, msrp, cost, total_options_cost) values ('G6DS5MTXDW3JD7PC6', 'g5j5sbn7-55uc-e5b9-h53c-b53bb35h826d', 'SOLD', 'USED', 'Asus', 'Laptop', 2999.00, 50.00, 3049.00);
insert into electronics (ein, inventory_id, status, usage_type, manufacturer, model, msrp, cost, total_options_cost) values ('H443UFH43GH4GHOWJ', 'g5j5sbn7-55uc-e5b9-h53c-b53bb35h826d', 'SOLD', 'NEW', 'ROG', 'Laptop', 1999.00, 50.00, 2049.00);
insert into electronic_options (electronic_id, name, description, cost) values (1, '1 year warranty', "If the product breaks or malfunctions we will give you a replacement", 50.00);
insert into electronic_options (electronic_id, name, description, cost) values (2, '2 years warranty', "If the product breaks or malfunctions we will give you a replacement", 75.00);