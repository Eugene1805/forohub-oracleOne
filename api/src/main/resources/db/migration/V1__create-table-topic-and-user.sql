CREATE table topic(
    id bigint not null auto_increment,
    topic varchar(255) not null,
    title varchar(255) not null,
    content varchar(255) not null,
    primary key (id)
);


CREATE table user(
    id bigint not null auto_increment,
    username varchar(100) not null,
    password varchar(300) not null,
    primary key(id)
);