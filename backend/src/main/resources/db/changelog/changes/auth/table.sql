--liquibase formatted sql

--changeset alberto.correa:202511292
--description: crea la tabla permissions
--comment: crea la tabla que representa la entidad permission
CREATE TABLE public.permissions (
    enabled bool DEFAULT true NOT NULL,
    id int4 NOT NULL DEFAULT nextval('global_seq_id'),
    visible bool DEFAULT true NOT NULL,
    created_at timestamp(6) DEFAULT now() NOT NULL,
    description varchar(255) NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT permissions_name_key UNIQUE (name),
    CONSTRAINT permissions_pkey PRIMARY KEY (id)
);
--rollback DROP TABLE IF EXISTS public.permissions;

--changeset alberto.correa:202511293
--description: Crea la tabla refresh_tokens
--comment: Crea la tabla que representa la entidad refresh_tokens
CREATE TABLE public.refresh_tokens (
    enabled bool DEFAULT true NOT NULL,
    remember_me bool NOT NULL,
    user_id int4 NOT NULL,
    creation_date timestamp(6) DEFAULT now() NOT NULL,
    expiration_date timestamp(6) NULL,
    jti int8 NOT NULL DEFAULT nextval('global_seq_id'),
    last_refresh_date timestamp(6) DEFAULT now() NULL,
    ip varchar(15) NOT NULL,
    device varchar(150) NULL,
    CONSTRAINT refresh_tokens_pkey PRIMARY KEY (jti)
);
--rollback DROP TABLE IF EXISTS public.refresh_tokens;

--changeset alberto.correa:202511294
--description: Crea la tabla roles
--comment: Crea la tabla que representa la entidad roles
CREATE TABLE public.roles (
    enabled bool DEFAULT true NOT NULL,
    id int4 NULL DEFAULT nextval('global_seq_id'),
    visible bool DEFAULT true NOT NULL,
    created_at timestamp(6) DEFAULT now() NOT NULL,
    description varchar(255) NULL,
    internal_name varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT roles_internal_name_key UNIQUE (internal_name),
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);
--rollback DROP TABLE IF EXISTS public.roles;

--changeset alberto.correa:202511295
--description: Crea la tabla intermedia roles_permissions
--comment: Crea la tabla de unión para representar la relación muchos a muchos entre las entidades Role y Permission.
CREATE TABLE public.roles_permissions (
    permission_id int4 NOT NULL,
    role_id int4 NOT NULL,
    CONSTRAINT roles_permissions_pkey PRIMARY KEY (permission_id, role_id)
    );
--rollback DROP TABLE IF EXISTS public.roles_permissions;

--changeset alberto.correa:202511296
--description: Crea la tabla users
--comment: Crea la tabla que representa la entidad user
CREATE TABLE public.users (
    enabled bool DEFAULT true NULL,
    id int4 NULL DEFAULT nextval('global_seq_id'),
    visible bool DEFAULT true NULL,
    last_password_change timestamp(6) DEFAULT now() NULL,
    register_date timestamp(6) DEFAULT now() NULL,
    email varchar(100) NULL,
    first_surname varchar(100) NOT NULL,
    names varchar(100) NOT NULL,
    second_surname varchar(100) NULL,
    username varchar(100) NOT NULL,
    "password" varchar(255) NOT NULL,
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_pkey PRIMARY KEY (id)
    );
--rollback DROP TABLE IF EXISTS public.users;


--changeset alberto.correa:202511297
--description: Crea la tabla intermedia user_roles
--comment: Crea la tabla de unión para representar la relación muchos a muchos entre las entidades User y Role.
CREATE TABLE public.user_roles (
        role_id int4 NOT NULL,
        user_id int4 NOT NULL,
        CONSTRAINT user_roles_pkey PRIMARY KEY (role_id, user_id)
);
--rollback DROP TABLE IF EXISTS public.user_roles;