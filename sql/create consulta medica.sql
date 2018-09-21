
use climedic;


create table consulta_medica(
id_consulta_medica Integer auto_increment PRIMARY KEY,
id_consulta Integer not null,
id_medicamento Integer not null,
prescricao varchar(200) not null,
quantidade int not null,
diagnotisco_consulta varchar(255) null,
FOREIGN KEY (id_consulta) 
        REFERENCES consulta(id_consulta)
        ON DELETE CASCADE,
FOREIGN KEY (id_medicamento) 
        REFERENCES medicamento(id_medicamento)
        ON DELETE CASCADE
)DEFAULT CHARSET=utf8;
