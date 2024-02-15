create database GradebookSystem;

\c GradebookSystem
\! chcp 1251

create user superuser with password 'password';
grant all privileges on database "GradebookSystem" to superuser;
grant all privileges on all tables in schema public to "superuser";

create table addresses (id serial primary key, 
						city varchar not null, 
						street varchar not null, 
						house_number varchar not null, 
						apartment_number int not null);
create table types_of_marks (id serial primary key, 
							name varchar not null);
create table disciplines (id serial primary key, 
							name varchar not null,
						  	type_of_marks_id int not null,
						  	foreign key (type_of_marks_id) references types_of_marks (id));
create table specializations (id serial primary key, 
								number varchar not null, 
								name varchar not null,
								study_duration int not null);
create table groups (id serial primary key, 
						name varchar not null, 
						course int not null,
						semester int not null,
						specialization_id int not null,
						date_formation date not null,
						date_graduation date not null,
						foreign key (specialization_id) references specializations (id));
create table forms_of_education (id serial primary key, 
									name varchar not null);
create table basis_of_education (id serial primary key, 
									name varchar not null);
create table students (id serial primary key,
						last_name varchar not null,
						first_name varchar not null,
						patronymic varchar not null,
						address_id int not null,
						date_of_birth date not null,
						group_id int not null,
            form_of_education_id int not null,
            basis_of_education_id int not null,
            date_admission date not null,
						foreign key (address_id) references addresses (id),
						foreign key (group_id) references groups (id),
						foreign key (form_of_education_id) references forms_of_education (id),
					   	foreign key (basis_of_education_id) references basis_of_education (id));
create table parents (id serial primary key, 
						student_id int not null,
						last_name varchar not null, 
						first_name varchar not null, 
						patronymic varchar not null, 
						address_id int not null, 
						foreign key (student_id) references students (id),
						foreign key (address_id) references addresses (id));
create table semester_performance (id serial primary key,
									student_id int not null, 
									course int not null, 
									semester int not null,
									discipline_id int not null,
									mark int not null,
									foreign key (student_id) references students (id),
									foreign key (discipline_id) references disciplines (id));
create table auth_data (id serial primary key,
						email varchar not null unique,
						password varchar not null,
						type_of_user smallint not null);
create table departments (id serial primary key,
							name varchar not null);
create table posts (id serial primary key,
					name varchar not null,
					salary float not null);
create table teachers (id serial primary key,
						department_id int not null,
						post_id int not null,
						address_id int not null,
						last_name varchar not null,
						first_name varchar not null,
						patronymic varchar not null,
						date_of_birth date not null,
					   	foreign key (department_id) references departments (id),
					   	foreign key (post_id) references posts (id),
					   	foreign key (address_id) references addresses (id));
create table teacher_discipline (id serial primary key,
								 discipline_id int not null,
								 teacher_id int not null,
								 foreign key (discipline_id) references disciplines (id),
								 foreign key (teacher_id) references teachers (id));
create table teacher_group (id serial primary key,
							group_id int not null,
							foreign key (group_id) references groups (id));
create table specialization_discipline (specialization_id int not null,
										discipline_id int not null,
										foreign key (specialization_id) references specializations (id),
										foreign key (discipline_id) references disciplines (id));
