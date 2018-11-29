INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (1, 'store_name', 'QH''s Fashion');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (2, 'store_address', 'PTIT, Trần Phú, Hà Đông, Hà Nội');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (3, 'store_phone', '0399 686 868');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (4, 'store_email', 'contact@qhshop.com');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (5, 'store_about', 'Shop chuyên cung cấp các mặt hàng thời trang, phụ kiện, quần áo');
INSERT INTO datn.store_config (config_id, config_key, config_value) VALUES (6, 'contact_description', 'Mọi thắc mắc, giải đáp, vui lòng liên hệ với chúng tôi.');
drop table if exists  persistent_logins;
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)
