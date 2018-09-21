create database climedic;

use climedic;

create table profissional(
id_profissional Integer auto_increment PRIMARY KEY,
nome varchar(40) not null,
email varchar(50) not null,
telefone varchar(13) null,
celular varchar(13) null,
endereco varchar(130) null,
senha varchar(255) not null,
profissional int not null,
crm_rms varchar(13) null
)DEFAULT CHARSET=utf8;
