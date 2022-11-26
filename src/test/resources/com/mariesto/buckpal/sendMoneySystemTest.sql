DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS activity;

CREATE TABLE account (
    account_id int auto_increment PRIMARY KEY
);
CREATE TABLE activity (
                          id bigint AUTO_INCREMENT PRIMARY KEY,
                          owner_account_id int,
                          source_account_id int,
                          destination_account_id int,
                          amount int,
                          time_stamp TIMESTAMP
);

insert into account (account_id) values (1);
insert into account (account_id) values (2);

insert into activity (id, time_stamp, owner_account_id, source_account_id, destination_account_id, amount)
values (1001, '2018-08-08 08:00:00.0', 1, 1, 2, 500);

insert into activity (id, time_stamp, owner_account_id, source_account_id, destination_account_id, amount)
values (1002, '2018-08-08 08:00:00.0', 2, 1, 2, 500);

insert into activity (id, time_stamp, owner_account_id, source_account_id, destination_account_id, amount)
values (1003, '2018-08-09 10:00:00.0', 1, 2, 1, 1000);

insert into activity (id, time_stamp, owner_account_id, source_account_id, destination_account_id, amount)
values (1004, '2018-08-09 10:00:00.0', 2, 2, 1, 1000);