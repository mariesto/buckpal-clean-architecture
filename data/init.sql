USE buckpal;

CREATE TABLE account (
    id int auto_increment PRIMARY KEY
);

CREATE TABLE activity (
    id int auto_increment PRIMARY KEY,
    owner_account_id int UNIQUE,
    source_account_id int UNIQUE,
    destination_account_id int UNIQUE,
    amount int,
    time_stamp TIMESTAMP
);

INSERT INTO account(id) values (1001), (10002);