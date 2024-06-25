CREATE TABLE topicos (

    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP,
    status BOOLEAN NOT NULL,
    autor varchar(100) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,

    primary key(id)
);

