use climedic;

create table exame(
id_exame Integer auto_increment PRIMARY KEY,
exame varchar(150) not null,
observacao varchar(1500) null
)DEFAULT CHARSET=utf8;