CREATE TABLE tb_guiche (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(10) UNIQUE NOT NULL,
    atendente VARCHAR(100),
    livre BOOLEAN NOT NULL
);

CREATE TABLE tb_senha (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    data_emissao DATE NOT NULL,
    hora_emissao TIME NOT NULL,
    atendida BOOLEAN NOT NULL
);

CREATE TABLE tb_chamada (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    atual BOOLEAN NOT NULL,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    senha_fk BIGINT NOT NULL,
    guiche_fk BIGINT NOT NULL,
    CONSTRAINT fk_chamada_senha FOREIGN KEY (senha_fk) REFERENCES tb_senha(id),
    CONSTRAINT fk_chamada_guiche FOREIGN KEY (guiche_fk) REFERENCES tb_guiche(id)
);
