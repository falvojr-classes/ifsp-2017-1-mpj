create database mpj;
use mpj;

create table usuario (
  id int not null primary key auto_increment,
  email varchar(50) not null,
  senha varchar(15) not null,
  ativo int not null default 1
);

create table permissao (
  id int not null primary key auto_increment,
  descricao varchar(25)
);

alter table usuario
add id_permissao int not null,
add foreign key(id_permissao) references permissao(id);

-- Insere as pemissões (tabela de dominio)
insert into permissao (descricao)
values ('Administrador'),
	   ('Funcionário'),
       ('Visitante');

-- Insere um usuário padrão (root)
insert into usuario (email, senha, id_permissao)
values ('root@root.com', 'root', 1);
