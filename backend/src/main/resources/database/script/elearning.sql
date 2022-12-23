--DDL
CREATE TABLE activities(
id serial,
activity_code VARCHAR(10) NOT NULL,
activity_type VARCHAR(20) NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE activities ADD CONSTRAINT activities_pk PRIMARY KEY(id);
ALTER TABLE activities ADD CONSTRAINT activities_bk UNIQUE(activity_code);
ALTER TABLE activities ADD CONSTRAINT activities_ck UNIQUE(activity_code, activity_type);

CREATE TABLE roles(
id serial,
role_code varchar(10) NOT NULL,
role_name varchar(20) NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY(id);
ALTER TABLE roles ADD CONSTRAINT roles_bk UNIQUE(role_code);
ALTER TABLE roles ADD CONSTRAINT roles_ck UNIQUE(role_code, role_name);

CREATE TABLE files(
id serial,
file_code text NOT NULL,
file_ext text NOT NULL,
	
created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE files ADD CONSTRAINT files_pk PRIMARY KEY(id);

CREATE TABLE users(
id serial,
email text NOT NULL,
password text NOT NULL,
fullname text NOT NULL,

role_id int NOT NULL,
photo_id int,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY(id);
ALTER TABLE users ADD CONSTRAINT users_bk UNIQUE(email);
ALTER TABLE users ADD CONSTRAINT roles_users_fk FOREIGN KEY(role_id) REFERENCES roles(id);
ALTER TABLE users ADD CONSTRAINT files_users_fk FOREIGN KEY(photo_id) REFERENCES files(id);

CREATE TABLE class_hdr(
id serial,
class_hdr_code varchar(10) NOT NULL,
class_hdr_subject varchar(200) NOT NULL,
class_hdr_description text NOT NULL,

instructor_id int NOT NULL,
photo_id int,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE class_hdr ADD CONSTRAINT class_hdr_pk PRIMARY KEY(id);
ALTER TABLE class_hdr ADD CONSTRAINT class_hdr_bk UNIQUE(class_hdr_code);
ALTER TABLE class_hdr ADD CONSTRAINT users_class_hdr_fk FOREIGN KEY(instructor_id) REFERENCES users(id);
ALTER TABLE class_hdr ADD CONSTRAINT files_class_hdr_fk FOREIGN KEY(photo_id) REFERENCES files(id);

CREATE TABLE class_dtl(
id serial,

student_id int NOT NULL,
class_hdr_id int NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE class_dtl ADD CONSTRAINT class_dtl_pk PRIMARY KEY(id);
ALTER TABLE class_dtl ADD CONSTRAINT users_class_dtl_fk FOREIGN KEY(student_id) REFERENCES users(id);
ALTER TABLE class_dtl ADD CONSTRAINT class_hdr_class_dtl_fk FOREIGN KEY(class_hdr_id) REFERENCES class_hdr(id);

CREATE TABLE materials(
id serial,
material_code varchar(10) NOT NULL,
material_subject varchar(200) NOT NULL,
material_description text NOT NULL,

class_hdr_id int NOT NULL,
activities_id INT NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE materials ADD CONSTRAINT materials_pk PRIMARY KEY(id);
ALTER TABLE materials ADD CONSTRAINT materials_bk UNIQUE(material_code);
ALTER TABLE materials ADD CONSTRAINT class_hdr_materials_fk FOREIGN KEY(class_hdr_id) REFERENCES class_hdr(id);
ALTER TABLE materials ADD CONSTRAINT activites_materials_fk FOREIGN KEY(activities_id) REFERENCES activities(id);

CREATE TABLE attachments(
id serial,

material_id int NOT NULL,
file_id int NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE attachments ADD CONSTRAINT attachments_pk PRIMARY KEY(id);
ALTER TABLE attachments ADD CONSTRAINT materials_attachments_fk FOREIGN KEY(material_id) REFERENCES materials(id);
ALTER TABLE attachments ADD CONSTRAINT files_attachments_fk FOREIGN KEY(file_id) REFERENCES files(id);

CREATE TABLE schedules(
id serial,
start_time timestamp WITHOUT TIME ZONE NOT NULL,
end_time timestamp WITHOUT TIME ZONE NOT NULL,

material_id int NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE schedules ADD CONSTRAINT schedules_pk PRIMARY KEY(id);
ALTER TABLE schedules ADD CONSTRAINT materials_schedules_pk FOREIGN KEY(material_id) REFERENCES materials(id);

CREATE TABLE submissions(
id serial,
score float,

class_dtl_id int NOT NULL,
schedule_id int NOT NULL,
file_id int NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE submissions ADD CONSTRAINT submissions_pk PRIMARY KEY(id);
ALTER TABlE submissions ADD CONSTRAINT class_dtl_submissions_fk FOREIGN KEY(class_dtl_id) REFERENCES class_dtl(id);
ALTER TABLE submissions ADD CONSTRAINT schedules_submissions_fk FOREIGN KEY(schedule_id) REFERENCES schedules(id);
ALTER TABLE submissions ADD CONSTRAINT files_submissions_fk FOREIGN KEY(file_id) REFERENCES files(id);

CREATE TABLE attendances(
id serial,
approval boolean DEFAULT FALSE,

class_dtl_id int NOT NULL,
schedule_id int DEFAULT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE attendances ADD CONSTRAINT attendances_pk PRIMARY KEY(id);
ALTER TABLE attendances ADD CONSTRAINT class_dtl_id_attendances_fk FOREIGN KEY(class_dtl_id) REFERENCES class_dtl(id);
ALTER TABLE attendances ADD CONSTRAINT schedule_attendances_fk FOREIGN KEY(schedule_id) REFERENCES schedules(id);

CREATE TABLE forums(
id serial,
title varchar(200) NOT NULL,
content text NOT NULL,

class_dtl_id int NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE forums ADD CONSTRAINT forum_pk PRIMARY KEY(id);
ALTER TABLE forums ADD CONSTRAINT class_dtl_forum_fk FOREIGN KEY(class_dtl_id) REFERENCES class_dtl(id);

CREATE TABLE comments(
id serial,
comment text NOT NULL,

forum_id int NOT NULL,
user_id int NOT NULL,

created_by int NOT NULL,
created_at timestamp WITHOUT TIME ZONE NOT NULL,
updated_by int,
updated_at timestamp WITHOUT TIME ZONE,
ver int NOT NULL DEFAULT 0,
is_active boolean NOT NULL DEFAULT TRUE
);
ALTER TABLE comments ADD CONSTRAINT comments_pk PRIMARY KEY(id);
ALTER TABLE comments ADD CONSTRAINT forums_comments_fk FOREIGN KEY(forum_id) REFERENCES forums(id);
ALTER TABLE comments ADD CONSTRAINT users_comments_fk FOREIGN KEY(user_id) REFERENCES users(id);

--DML
INSERT INTO roles (role_code, role_name, created_by, created_at) VALUES
('RLSYS', 'System', 1, now()),
('RLSAM', 'Super Admin', 1, now()),
('RLINS', 'Instructor', 1, now()),
('RLSTD', 'Student', 1, now());

INSERT INTO users (email, password, fullname, role_id, created_by, created_at) VALUES
('system@gmail.com', '$2a$10$mQ77inhXemDEE0zlr9kwc.94nqJERo1uHIsaPjesr0upzK1Hm6cWa', 'System', 1, 1, now()),
('superadmin@gmail.com', '$2a$10$mQ77inhXemDEE0zlr9kwc.94nqJERo1uHIsaPjesr0upzK1Hm6cWa', 'Super Admin', 2, 1, now()),
('guru@gmail.com', '$2a$10$mQ77inhXemDEE0zlr9kwc.94nqJERo1uHIsaPjesr0upzK1Hm6cWa', 'Instructor', 3, 1, now()),
('siswa@gmail.com', '$2a$10$mQ77inhXemDEE0zlr9kwc.94nqJERo1uHIsaPjesr0upzK1Hm6cWa', 'Student', 4, 4, now());


INSERT INTO activities (activity_code, activity_type, created_by, created_at) VALUES
('ACTLN', 'Learning', 1, NOW()),
('ACTQZ', 'Quiz', 1, NOW()),
('ACTEM', 'Exam', 1, NOW());