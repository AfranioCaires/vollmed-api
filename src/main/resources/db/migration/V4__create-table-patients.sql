create table patients
(

    id           bigint       not null auto_increment,
    name         varchar(100) not null,
    email        varchar(100) not null unique,
    cpf          varchar(14)  not null unique,
    street       varchar(100) not null,
    neighbour    varchar(100) not null,
    zipcode      varchar(9)   not null,
    complement   varchar(100),
    number       varchar(20),
    state        char(2)      not null,
    city         varchar(100) not null,
    phone_number varchar(20)  not null,
    active       tinyint      not null,

    primary key (id)

);