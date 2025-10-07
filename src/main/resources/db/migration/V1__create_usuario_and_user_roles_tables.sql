-- V1__create_usuario_and_user_roles_tables.sql

------------------------------------------------------
-- 1. Criação da Tabela USUARIO
------------------------------------------------------

CREATE TABLE usuario (
    -- Aplica o padrão UUID com geração automática.
    -- O 'random_uuid()' é comum em H2 ou mapeado para UUIDs nativos em outros bancos.
    id UUID DEFAULT random_uuid() PRIMARY KEY,

    -- Corresponde a @Column(unique = true, nullable = false)
    username VARCHAR(255) NOT NULL,

    -- Corresponde a @Column(nullable = false)
    password VARCHAR(255) NOT NULL
);

-- Cria um índice único para garantir que não haja usernames duplicados
CREATE UNIQUE INDEX idx_usuario_username_unq ON usuario (username);


------------------------------------------------------
-- 2. Criação da Tabela USER_ROLES (Para o @ElementCollection)
------------------------------------------------------

CREATE TABLE user_roles (
    -- Chave estrangeira para a tabela 'usuario' (referencia o ID)
    user_id UUID NOT NULL,

    -- O valor da role/scope (Ex: "SCOPE_message:read")
    role VARCHAR(255) NOT NULL,

    -- Define a chave estrangeira (FK)
    CONSTRAINT fk_user_roles_on_user_id
        FOREIGN KEY (user_id)
        REFERENCES usuario (id)
        ON DELETE CASCADE, -- Remove roles se o usuário for deletado
        
    -- Garante que o par (user_id, role) seja único (Primary Key Composta)
    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role)
);