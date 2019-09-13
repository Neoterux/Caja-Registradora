SET global time_zone = '+5:00';
-- set global max_connections_per_hour = 500;
Create database if not exists Neoterux;
use Neoterux;

create table if not exists empleados(
	id  varchar(7) not null  primary key unique, -- This would be used for authentication
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    cedula varchar(10) not null unique,
    estado_civil varchar(10) not null,
    direccion varchar(100) not null,
    email varchar(100) not null unique,
    pass varchar(100) not null,
    isAdmin bool not null default false,
    index(id),
    index(cedula)
);

create table if not exists clientes(
	cedula varchar(10) not null primary key unique,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    telefono varchar(10) not null,
    direccion varchar(100) not null,
    email varchar(100) not null,
    index(cedula)

);


create table if not exists bodega(
	id varchar(10) not null primary key unique,
    nombre_producto varchar(25) not null,
    precio float(10,2) not null,
    cantidad_disponible int unsigned not null,
    index(id)
);

create table if not exists proforma(
	id binary(16),
    id_text varchar(36) generated always as (insert(
    insert(
      insert(
        insert(hex(id),9,0,'-'),
        14,0,'-'),
      19,0,'-'),
    24,0,'-')
 ) virtual,

cedula varchar(10) not null,
    id_producto varchar(10) not null,
    cantidad int not null,
    precio float not null ,
    total_precio float not null,
    fecha timestamp not null,
    empleado_id varchar(7) not null ,
    primary key (id),
    foreign key(cedula) references clientes (cedula),
    foreign key(id_producto) references bodega (id),
    foreign key(empleado_id) references empleados(id),
    index(id)

);

insert into empleados values ('ntrx', 'Luis Andres', 'Bajaña Fernandez', '0951792498', 'Soltero', 'Cdla. Huancavilca Sur Mz. C12 Villa 9', 'labfernandez2014@gmail.com', '123', true);
insert into clientes values('0951792498', 'Luis Andres', 'Bajaña Fernandez', '0969096089', 'una casa', 'labfernandez2014@gmail.com');
insert into bodega values('testp','test product name', 99.0, 99);