CREATE TABLE users (
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name     VARCHAR(100)  NOT NULL,
    last_name      VARCHAR(100)  NOT NULL,
    email          VARCHAR(255)  NOT NULL UNIQUE,
    password_hash  TEXT          NOT NULL,
    role           VARCHAR(20)   NOT NULL DEFAULT 'USER',
    enabled        BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at     TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMPTZ   NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_users_email ON users (email);

CREATE INDEX idx_users_name ON users (LOWER(first_name), LOWER(last_name));
