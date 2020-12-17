-- create sequence hibernate_sequence start 1 increment 1;
CREATE TABLE IF NOT EXISTS people (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1),
    name character varying(64) NOT NULL,
    age integer NOT NULL
);