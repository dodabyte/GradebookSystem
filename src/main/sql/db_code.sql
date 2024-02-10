create database rpbd;

\c rpbd
\! chcp 1251

create user doda with password 'doda';
grant all privileges on database "rpbd" to doda;
grant all privileges on all tables in schema public to "doda";

create table statuses (id serial primary key, 
						name varchar not null);
create table current_payments (id serial primary key, 
								amount float not null, 
								p_date timestamp not null, 
								doc_number int not null);
create table addresses (id serial primary key, 
						city varchar not null, 
						street varchar not null, 
						house_number varchar not null, 
						apartment_number int not null);
create table types_of_marks (id serial primary key, 
							name varchar not null);
create table disciplines (id serial primary key, 
							name varchar not null);
create table specializations (id serial primary key, 
								number varchar not null, 
								name varchar not null);
create table groups (id serial primary key, 
						name varchar not null, 
						course int not null,
						semester int not null,
						specialization_id int not null,
						foreign key (specialization_id) references specializations (id));
create table forms_of_education (id serial primary key, 
									name varchar not null);
create table basis_of_education (id serial primary key, 
									name varchar not null);

create table learning_conditions (id serial primary key, 
									form_id int not null, 
									basis_id int not null, 
									foreign key (form_id) references forms_of_education (id),
									foreign key (basis_id) references basis_of_education (id));
create table students (id serial primary key, 
						last_name varchar not null,
						first_name varchar not null,
						patronymic varchar not null,
						address_id int not null,
						date_of_birth date not null,
						group_id int not null,
						learning_conditions_id int not null,
						foreign key (address_id) references addresses (id),
						foreign key (group_id) references groups (id),
						foreign key (learning_conditions_id) references learning_conditions (id));
create table parents (id serial primary key, 
						student_id int not null,
						last_name varchar not null, 
						first_name varchar not null, 
						patronymic varchar not null, 
						address_id int not null, 
						foreign key (student_id) references students (id)
						foreign key (address_id) references addresses (id));
create table contract_data (id serial primary key, 
							student_id int not null,
							c_date date not null, 
							payment_amount float not null, 
							current_payments_id int not null,
							status_id int not null,
							foreign key (student_id) references students (id),
							foreign key (current_payments_id) references current_payments (id),
							foreign key (status_id) references statuses (id));
create table semester_perfomance (id serial primary key, 
									student_id int not null, 
									course int not null, 
									semester int not null,
									discipline_id int not null,
									type_of_marks_id int not null,
									mark int not null,
									foreign key (student_id) references students (id),
									foreign key (discipline_id) references disciplines (id),
									foreign key (type_of_marks_id) references types_of_marks (id));

insert into statuses (name) values ('Оплачено'), ('Не оплачено');
