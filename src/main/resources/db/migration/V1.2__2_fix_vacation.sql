ALTER TABLE public.vacation_sick RENAME TO vacation;
ALTER TABLE public.vacation RENAME COLUMN type_of_vacation_sick TO type_of_vacation;
