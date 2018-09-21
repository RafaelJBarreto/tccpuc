
use climedic;


create table consulta(
id_consulta Integer auto_increment PRIMARY KEY,
id_paciente Integer not null,
id_profissional Integer not null,
data_hora datetime not null,
cancelada boolean not null default 0,
 FOREIGN KEY (id_paciente) 
        REFERENCES paciente(id_paciente)
        ON DELETE CASCADE,
FOREIGN KEY (id_profissional) 
        REFERENCES profissional(id_profissional)
        ON DELETE CASCADE
)DEFAULT CHARSET=utf8;

ALTER TABLE consulta ADD COLUMN realizada boolean DEFAULT 0;
ALTER TABLE consulta ADD COLUMN pedido_realizado boolean DEFAULT 0;
