CREATE SEQUENCE public.id_task
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.id_task
  OWNER TO postgres;




CREATE TABLE public.task
(
  tsk_id integer NOT NULL DEFAULT nextval('id_task'::regclass),
  tsk_iname character(50),
  CONSTRAINT task_pkey PRIMARY KEY (tsk_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.task
  OWNER TO postgres;


-- Sequence: public.id_user

-- DROP SEQUENCE public.id_user;

CREATE SEQUENCE public.id_user
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.id_user
  OWNER TO postgres;


-- Table: public.usertt

-- DROP TABLE public.usertt;

CREATE TABLE public.usertt
(
  usr_id integer NOT NULL DEFAULT nextval('id_user'::regclass),
  usr_name character(50),
  usr_email character(50),
  CONSTRAINT usertt_pkey PRIMARY KEY (usr_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usertt
  OWNER TO postgres;


-- Sequence: public.id_worktime

-- DROP SEQUENCE public.id_worktime;

CREATE SEQUENCE public.id_worktime
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.id_worktime

-- Table: public.worktime

-- DROP TABLE public.worktime;

CREATE TABLE public.worktime
(
  id integer NOT NULL DEFAULT nextval('id_worktime'::regclass),
  usr_id integer NOT NULL,
  tsk_id integer NOT NULL,
  wt_begin bigint,
  wt_end bigint,
  CONSTRAINT worktime_pkey PRIMARY KEY (id),
  CONSTRAINT tsk_id_pkey FOREIGN KEY (tsk_id)
      REFERENCES public.task (tsk_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT usr_id_pkey FOREIGN KEY (usr_id)
      REFERENCES public.usertt (usr_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.worktime
  OWNER TO postgres;

