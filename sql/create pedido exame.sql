
use climedic;

create table pedido_exame(
id_pedido_exame Integer auto_increment PRIMARY KEY,
id_exame Integer not null,
id_consulta Integer not null,
FOREIGN KEY (id_exame) 
        REFERENCES exame(id_exame)
        ON DELETE CASCADE,
FOREIGN KEY (id_consulta) 
        REFERENCES consulta(id_consulta)
        ON DELETE CASCADE
)DEFAULT CHARSET=utf8;

ALTER TABLE pedido_exame ADD COLUMN existe_resultado boolean DEFAULT 0;
