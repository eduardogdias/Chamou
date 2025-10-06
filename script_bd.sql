CREATE TABLE tb_guiche (
    id INT IDENTITY(1,1) PRIMARY KEY,
    numero VARCHAR(10) UNIQUE NOT NULL,
    atendente VARCHAR(100),
    livre BIT NOT NULL
);

CREATE TABLE tb_senha (
    id INT IDENTITY(1,1) PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    data_emissao DATE NOT NULL,
    hora_emissao TIME NOT NULL,
    atendida BIT NOT NULL
);

CREATE TABLE tb_chamada (
    id INT IDENTITY(1,1) PRIMARY KEY,
    atual BIT NOT NULL,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    senha_fk INT NOT NULL,
    guiche_fk INT NOT NULL,
    CONSTRAINT fk_chamada_senha FOREIGN KEY (senha_fk) REFERENCES tb_senha(id),
    CONSTRAINT fk_chamada_guiche FOREIGN KEY (guiche_fk) REFERENCES tb_guiche(id)
);




-- populando as tabelas

INSERT INTO tb_guiche (numero, atendente, livre) VALUES ('01', 'Juliano Freitas', 1);
INSERT INTO tb_guiche (numero, atendente, livre) VALUES ('02', 'Raquel Silva', 1);
INSERT INTO tb_guiche (numero, atendente, livre) VALUES ('03', 'Abel Lima', 1);
INSERT INTO tb_guiche (numero, atendente, livre) VALUES ('04', 'Fernando Gustavo', 1);
INSERT INTO tb_guiche (numero, atendente, livre) VALUES ('05', 'Maria Fernanda', 1);
