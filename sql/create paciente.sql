
use climedic;

create table paciente(
id_paciente Integer auto_increment PRIMARY KEY,
nome varchar(40) not null,
email varchar(50) not null,
telefone varchar(13) null,
celular varchar(13) null,
endereco varchar(130) null
)DEFAULT CHARSET=utf8;

ALTER TABLE paciente ADD COLUMN cartao_sus varchar(30) not null;
ALTER TABLE paciente ADD COLUMN data_nascimento datetime not null;
ALTER TABLE paciente ADD COLUMN sexo int not null;