create schema MAIN;

create schema SCHEMA_1;
use SCHEMA_1;
create table location(id bigint auto_increment, name varchar(255));

create schema SCHEMA_2;
use SCHEMA_2;
create table location(id bigint auto_increment, name varchar(255));

