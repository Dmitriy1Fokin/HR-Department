CREATE TABLE public.sick_leave (
	id bigserial NOT NULL,
	employee_id int8 NOT NULL,
	date_start date NOT NULL,
	date_end date NULL,
	type_of_vacation varchar NOT NULL,
	CONSTRAINT sick_leave_pk PRIMARY KEY (id),
	CONSTRAINT sick_leave_employeefk FOREIGN KEY (employee_id) REFERENCES public.employee(id)
);
