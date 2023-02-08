create table patients(

                        id bigint not null auto_increment,
                        name varchar(100) not null,
                        email varchar(100) not null unique,
                        phone varchar(20) not null,
                        ssn varchar(11) not null unique,
                        address_line1 varchar(100) not null,
                        address_line2 varchar(100) not null,
                        postal_code varchar(9) not null,
                        state char(100) not null,
                        city varchar(100) not null,

                        primary key(id)

);