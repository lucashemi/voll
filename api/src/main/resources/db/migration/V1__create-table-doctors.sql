create table doctors(

                        id bigint not null auto_increment,
                        name varchar(100) not null,
                        email varchar(100) not null unique,
                        specialty varchar(100) not null,
                        address_line1 varchar(100) not null,
                        address_line2 varchar(100) not null,
                        postal_code varchar(9) not null,
                        state varchar(100) not null,
                        city varchar(100) not null,

                        primary key(id)

);