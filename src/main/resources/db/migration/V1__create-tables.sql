-- Migration consolidada para criar e atualizar todas as tabelas do banco de dados 'forumhub'

-- Criando a tabela 'usuarios'
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    ativo TINYINT NOT NULL DEFAULT 1,
    role VARCHAR(20),
    PRIMARY KEY (id)
);

-- Criando a tabela 'perfis'
CREATE TABLE perfis (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Criando a tabela de relacionamento entre 'usuarios' e 'perfis'
CREATE TABLE usuarios_perfis (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuarios_perfis_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_usuarios_perfis_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfis (id)
);

-- Criando a tabela 'cursos'
CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Criando a tabela 'topicos'
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP,
    status VARCHAR(100) NOT NULL,
    curso_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos (id),
    CONSTRAINT fk_topicos_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios (id)
);

-- Criando a tabela 'respostas'
CREATE TABLE respostas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    topico_id BIGINT NOT NULL,
    data_criacao TIMESTAMP,
    autor_id BIGINT NOT NULL,
    solucao VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_respostas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos (id),
    CONSTRAINT fk_respostas_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios (id)
);
