CREATE TABLE public.attendance_sheet (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    date date NOT NULL,
    hour_at_work integer DEFAULT 0 NOT NULL,
    type_of_attendance character varying NOT NULL
);

CREATE SEQUENCE public.attendance_sheet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.attendance_sheet_id_seq OWNED BY public.attendance_sheet.id;


CREATE TABLE public.department (
    id bigint NOT NULL,
    name character varying NOT NULL
);

CREATE SEQUENCE public.department_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.department_id_seq OWNED BY public.department.id;


CREATE TABLE public.employee (
    id bigint NOT NULL,
    date_in date NOT NULL,
    date_out date,
    department_id bigint NOT NULL,
    position_id bigint NOT NULL
);

CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.person_id_seq OWNED BY public.employee.id;


CREATE TABLE public."position" (
    id bigint NOT NULL,
    name character varying NOT NULL
);

CREATE SEQUENCE public.position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.position_id_seq OWNED BY public."position".id;


CREATE TABLE public.vacation_sick (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    date_start date NOT NULL,
    date_end date,
    type_of_vacation_sick character varying NOT NULL
);

CREATE SEQUENCE public.vacation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.vacation_id_seq OWNED BY public.vacation_sick.id;


ALTER TABLE ONLY public.attendance_sheet ALTER COLUMN id SET DEFAULT nextval('public.attendance_sheet_id_seq'::regclass);
ALTER TABLE ONLY public.department ALTER COLUMN id SET DEFAULT nextval('public.department_id_seq'::regclass);
ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);
ALTER TABLE ONLY public."position" ALTER COLUMN id SET DEFAULT nextval('public.position_id_seq'::regclass);
ALTER TABLE ONLY public.vacation_sick ALTER COLUMN id SET DEFAULT nextval('public.vacation_id_seq'::regclass);


SELECT pg_catalog.setval('public.attendance_sheet_id_seq', 1, false);
SELECT pg_catalog.setval('public.department_id_seq', 1, false);
SELECT pg_catalog.setval('public.person_id_seq', 1, false);
SELECT pg_catalog.setval('public.position_id_seq', 1, false);
SELECT pg_catalog.setval('public.vacation_id_seq', 1, false);

ALTER TABLE ONLY public.attendance_sheet
    ADD CONSTRAINT attendance_sheet_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.employee
    ADD CONSTRAINT person_pk PRIMARY KEY (id);
ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.vacation_sick
    ADD CONSTRAINT vacation_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.attendance_sheet
    ADD CONSTRAINT attendance_employee_sheet_fk FOREIGN KEY (employee_id) REFERENCES public.employee(id);
ALTER TABLE ONLY public.employee
    ADD CONSTRAINT person_department_fk FOREIGN KEY (department_id) REFERENCES public.department(id);
ALTER TABLE ONLY public.employee
    ADD CONSTRAINT person_position_fk FOREIGN KEY (position_id) REFERENCES public."position"(id);
ALTER TABLE ONLY public.vacation_sick
    ADD CONSTRAINT vacation_employee_fk FOREIGN KEY (employee_id) REFERENCES public.employee(id);