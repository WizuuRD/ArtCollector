create table t_picture
(
    id           bigint generated by default as identity
        constraint pk_t_picture
            primary key,
    url          varchar(255),
    title        varchar(255),
    content      bytea,
    file_name    varchar(255)
);

create table t_session
(
    id                       bigint generated by default as identity
        constraint pk_t_session
            primary key,
    grant_type               varchar(255),
    expires_in               timestamp,
    access_token             varchar(255),
    refresh_token            varchar(255),
    refresh_token_expires_in timestamp,
    code                     varchar(255)
);