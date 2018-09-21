
use climedic;

create table medicamento(
id_medicamento Integer auto_increment PRIMARY KEY,
nome_generico varchar(100) not null,
nome_comercial varchar(100) not null,
fabricante int null
)DEFAULT CHARSET=utf8;
