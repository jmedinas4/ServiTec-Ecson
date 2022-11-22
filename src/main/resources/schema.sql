DROP TABLE IF EXISTS public.fallas;
DROP TABLE IF EXISTS public.tecnicos;
DROP TABLE IF EXISTS public.clientes;
DROP TABLE IF EXISTS public.informes;
CREATE TABLE IF NOT EXISTS public.tecnicos
(
    id serial,
    horario character varying(255),
    horario_ocupado character varying(255),
    nombre character varying(255),
    CONSTRAINT tecnicos_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.clientes
(
    id serial,
    correo character varying(255),
    direccion character varying(255),
    nombre character varying(255),
    CONSTRAINT clientes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.informes
(
    id serial,
    comentarios character varying(255),
    solucionado character varying(255),
    CONSTRAINT informes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.fallas
(
    id serial NOT NULL,
    descripcion character varying(255),
    fecha character varying(255),
    cliente_id integer NOT NULL,
    informe_id bigint,
    tecnico_id integer NOT NULL,
    CONSTRAINT fallas_pkey PRIMARY KEY (id),
    CONSTRAINT fkj58ffdfajv16x3cacokvvwnft FOREIGN KEY (informe_id)
        REFERENCES public.informes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkotb4otsadlcv40bj72g111h2f FOREIGN KEY (cliente_id)
        REFERENCES public.clientes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpq7h024hhv4qx7opkstik275 FOREIGN KEY (tecnico_id)
        REFERENCES public.tecnicos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
