INSERT INTO person(created_by, created_date, updated_by, updated_date, dob, email, full_name, password, phone, short_name, username) VALUE (null, NOW(), null, NOW(), "2018-09-30","contact@qhshop.com","Shop Admin","$2a$10$ekkmYAL.x6CJdG1cfaSBtuXWOkLGMRC9vG8ryhwyiI5baYjZd4mza","0399999999","sa","admin");
drop table if exists  persistent_logins;
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)
