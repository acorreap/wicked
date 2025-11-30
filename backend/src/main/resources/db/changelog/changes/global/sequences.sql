--liquibase formatted sql

--changeset alberto.correa:202511291
--description: crear sequencia generecaion de ids para las tablas auth
--comment: crear sequencia generecaion de ids para las tablas auth
CREATE SEQUENCE global_seq_id
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1000
    CACHE 1
    NO CYCLE;
--rollback DROP SEQUENCE IF EXISTS global_seq_id;