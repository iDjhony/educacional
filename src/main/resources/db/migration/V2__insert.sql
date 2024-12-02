-- Inserindo dados na tabela aluno
INSERT INTO aluno (nome, email, data_nascimento, matricula) VALUES
('Carlos Silva', 'carlos.silva@example.com', '2000-05-15', '20230001'),
('Maria Oliveira', 'maria.oliveira@example.com', '1999-09-25', '20230002'),
('João Santos', 'joao.santos@example.com', '2001-02-12', '20230003');

-- Inserindo dados na tabela professor
INSERT INTO professor (nome, email, telefone, especialidade) VALUES
('Ana Pereira', 'ana.pereira@example.com', '99999-1234', 'Matemática'),
('Roberto Costa', 'roberto.costa@example.com', '98888-5678', 'Física'),
('Fernanda Souza', 'fernanda.souza@example.com', '97777-9101', 'Química');

-- Inserindo dados na tabela curso
INSERT INTO curso (nome, codigo, carga_horaria) VALUES
('Engenharia Civil', 'ECIV101', 3600),
('Engenharia de Computação', 'ECOMP202', 4000),
('Engenharia Elétrica', 'EEL203', 3800);

-- Inserindo dados na tabela disciplina
INSERT INTO disciplina (nome, codigo, curso_id, professor_id) VALUES
('Cálculo I', 'CALC101', 1, 1),
('Física Geral I', 'FISG102', 2, 2),
('Química Básica', 'QUIB103', 3, 3);

-- Inserindo dados na tabela turma
INSERT INTO turma (ano, semestre, curso_id) VALUES
(2024, 1, 1),
(2024, 1, 2),
(2024, 1, 3);

-- Inserindo dados na tabela matricula
INSERT INTO matricula (turma_id, aluno_id) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Inserindo dados na tabela nota
INSERT INTO nota (nota, data_lancamento, matricula_id, disciplina_id) VALUES
(8.5, '2024-03-15', 1, 1),
(7.8, '2024-03-16', 2, 2),
(9.0, '2024-03-17', 3, 3);
