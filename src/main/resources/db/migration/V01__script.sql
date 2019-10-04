drop table if exists oportunidade;
drop table if exists restaurante;
drop table if exists colaborador;
drop table if exists votacao;


create table oportunidade (
  id bigint auto_increment not null,
  nome_prospecto varchar(80) not null,
  descricao varchar(200) not null,
  valor decimal(10,2),

  primary key (id)
);

create table restaurante (
  id bigint auto_increment not null,
  nome varchar(80) not null,

  primary key (id)
);

insert into restaurante (nome) values ('Mc Donald''s');
insert into restaurante (nome) values ('Burguer King');
insert into restaurante (nome) values ('Habib''s');
insert into restaurante (nome) values ('Petiskeira');
insert into restaurante (nome) values ('Applebees');

create table colaborador (
  id bigint auto_increment not null,
  nome varchar(80) not null,

  primary key (id)
);

insert into colaborador (nome) values ('Michael Jackson');
insert into colaborador (nome) values ('Lady Gaga');
insert into colaborador (nome) values ('Anitta');
insert into colaborador (nome) values ('Gaúcho da Fronteira');
insert into colaborador (nome) values ('Thiaguinho');


create table votacao  (
  id_restaurante bigint not null,
  id_colaborador bigint not null,
  data date not null,
  
  primary key (id_restaurante, id_colaborador, data)
);