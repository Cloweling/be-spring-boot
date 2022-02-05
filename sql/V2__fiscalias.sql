create table fiscalias (
    id int not null auto_increment primary key,
    name varchar(100) not null,
    telefono varchar(20) not null,
    ubicacion_id int not null,
    foreign key (ubicacion_id) references ubicaciones(id)
);