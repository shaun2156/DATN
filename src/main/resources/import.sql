INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (1, 'store_name', 'QH''s Fashion');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (2, 'store_address', 'PTIT, Trần Phú, Hà Đông, Hà Nội');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (3, 'store_phone', '0399 686 868');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (4, 'store_email', 'contact@qhshop.com');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (5, 'store_about', 'Shop chuyên cung cấp các mặt hàng thời trang, phụ kiện, quần áo');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (6, 'contact_description', 'Mọi thắc mắc, giải đáp, vui lòng liên hệ với chúng tôi.');

drop table if exists  persistent_logins;
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null);

# INIT ROLE and SYSADMIN with full ROLE
INSERT INTO datn.role (role_id, role_name, description) VALUES (1, 'admin', 'System Administrator');
INSERT INTO datn.role (role_id, role_name, description) VALUES (2, 'storage_manager', 'Storage Manager Employee');
INSERT INTO datn.role (role_id, role_name, description) VALUES (3, 'store_owner', 'Owner of the System');
INSERT INTO datn.role (role_id, role_name, description) VALUES (4, 'store_manager', 'Store Content Manager Employee');

INSERT INTO datn.person (person_id, email, full_name, password, short_name, username) VALUES (1, 'admin@example.com', 'Shop Admin', '1', 'SA', 'admin');
INSERT INTO datn.employee (join_date, person_id, rank) VALUES (NOW(), 1, 1);
INSERT INTO datn.employee_role_access (role_access_id, employee_id, role_id) VALUES (1, 1, 1);
INSERT INTO datn.employee_role_access (role_access_id, employee_id, role_id) VALUES (2, 1, 2);
INSERT INTO datn.employee_role_access (role_access_id, employee_id, role_id) VALUES (3, 1, 3);
INSERT INTO datn.employee_role_access (role_access_id, employee_id, role_id) VALUES (4, 1, 4);
