CREATE TABLE aluno (
    id int not NULL PRIMARY key AUTO_INCREMENT,
    nome VARCHAR(100) not null,
    email VARCHAR(100) not null,
    data_nascimento DATE not null,
    matricula VARCHAR(20)
);

create Table professor (
    id INT not null PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) not NULL,
    email VARCHAR(100) not null,
    telefone VARCHAR(15),
    especialidade VARCHAR(100) not null
);

CREATE Table curso (
    id int not null PRIMARY key AUTO_INCREMENT,
    nome VARCHAR(100) not null,
    codigo VARCHAR(20) not null,
    carga_horaria int not null
);

create Table disciplina (
    id int not null PRIMARY key AUTO_INCREMENT,
    nome VARCHAR(100) NOT null,
    codigo VARCHAR(20),
    curso_id int not null,
    professor_id int not null,
    Foreign Key (curso_id) REFERENCES curso(id),
    Foreign Key (professor_id) REFERENCES professor(id)
);

CREATE Table turma (
    id int NOT null PRIMARY key AUTO_INCREMENT,
    ano int not null,
    semestre int not null,
    curso_id int not null,
    Foreign Key (curso_id) REFERENCES curso(id)
);

CREATE Table matricula (
    id int not null primary key AUTO_INCREMENT,
    turma_id int not null,
    aluno_id int not null,
    Foreign Key (turma_id) REFERENCES turma(id),
    Foreign Key (aluno_id) REFERENCES aluno(id)
);

create Table nota (
    id int not null PRIMARY key AUTO_INCREMENT,
    nota DECIMAL(5,2),
    data_lancamento date,
    matricula_id int not null,
    disciplina_id int not null,
    Foreign Key (matricula_id) REFERENCES matricula(id),
    Foreign Key (disciplina_id) REFERENCES disciplina(id)
);
