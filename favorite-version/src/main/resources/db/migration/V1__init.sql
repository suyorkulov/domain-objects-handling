CREATE TABLE roles
(
  id                 BIGSERIAL PRIMARY KEY,
  created_timestamp  TIMESTAMP,
  modified_timestamp TIME,
  name               VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE organizations
(
  id                 BIGSERIAL PRIMARY KEY,
  created_timestamp  TIMESTAMP,
  modified_timestamp TIME,
  name               VARCHAR(255)  NOT NULL,
  address            VARCHAR(1000) NOT NULL
);

CREATE TABLE users
(
  id                 BIGSERIAL PRIMARY KEY,
  created_timestamp  TIMESTAMP,
  modified_timestamp TIME,
  first_name         VARCHAR(255)                         NOT NULL,
  last_name          VARCHAR(1000)                        NOT NULL,
  email              VARCHAR(255)                         NOT NULL UNIQUE,
  password           VARCHAR(255)                         NOT NULL,
  status             VARCHAR(20)                          NOT NULL,
  phone_number       VARCHAR(30),
  role_id            BIGINT REFERENCES roles (id)         NOT NULL,
  organization_id    BIGINT REFERENCES organizations (id) NOT NULL
);