
use climedic;

create table resultado_exame(
id_resultado_exame Integer auto_increment PRIMARY KEY,
id_exame Integer not null,
id_consulta Integer not null,
data_coleta datetime not null,
valores_obtidos float not null,
unidade integer null,
valor_referencial varchar(100) null,
enviado_medico boolean DEFAULT 0,
enviado_paciente boolean DEFAULT 0,
FOREIGN KEY (id_exame) 
        REFERENCES exame(id_exame)
        ON DELETE CASCADE,
FOREIGN KEY (id_consulta) 
        REFERENCES consulta(id_consulta)
        ON DELETE CASCADE
)DEFAULT CHARSET=utf8;

ALTER TABLE resultado_exame MODIFY data_coleta datetime null;

