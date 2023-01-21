create table if not exists jmember
(
    id                   serial
    constraint jmember_id_pk primary key,
    name                 varchar(255),
    email                 varchar(255),
    create_date          bigint
);

create table if not exists kmember
(
    id                   serial
    constraint kmember_id_pk primary key,
    name                 varchar(255),
    email                 varchar(255),
    create_date          bigint
);