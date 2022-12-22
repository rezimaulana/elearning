CREATE TABLE tb_role(
	id serial,
	role_code varchar(5) NOT NULL,
	role_name varchar(30) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE
);

ALTER TABLE tb_role 
	ADD CONSTRAINT tb_role_pk PRIMARY KEY(id);
	
ALTER TABLE tb_role 
	ADD CONSTRAINT tb_role_bk UNIQUE(role_code);

ALTER TABLE tb_role 
	ADD CONSTRAINT tb_role_ck UNIQUE(role_code, role_name);
	
CREATE TABLE tb_file (
	id serial,
	file_encode text NOT NULL,
	extensions varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE
);

ALTER TABLE tb_file
	ADD CONSTRAINT tb_file_pk PRIMARY KEY(id);

CREATE TABLE tb_category_test(
	id serial,
	category_code varchar(5)  NOT NULL,
	category_name varchar(50)  NOT NULL,
	category_detail text NOT NULL,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE
	);
	
ALTER TABLE tb_category_test
	ADD CONSTRAINT tb_category_test_pk PRIMARY KEY(id);
	

ALTER TABLE tb_category_test
	ADD CONSTRAINT tb_category_test_bk UNIQUE(category_code);
	
CREATE TABLE tb_user (
	id serial,
	email varchar(50) NOT NULL,
	username varchar(50) NOT NULL,
	pwd text NOT NULL,
	full_name varchar(100) NOT NULL,
	photo_id int,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE
);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_pk PRIMARY KEY(id);
	
ALTER TABLE tb_user
	ADD CONSTRAINT tb_user_email_bk UNIQUE(email);

ALTER TABLE tb_user
	ADD CONSTRAINT tb_user_un_bk UNIQUE(username);
	
ALTER TABLE tb_user
	ADD CONSTRAINT tb_user_email_ck UNIQUE(email, full_name);

ALTER TABLE tb_user
	ADD CONSTRAINT tb_user_un_ck UNIQUE(username, full_name);
	
ALTER TABLE tb_user
	ADD CONSTRAINT tb_user_photo_fk FOREIGN KEY (photo_id)
	REFERENCES tb_file(id);
	
CREATE TABLE tb_candidate(
	id serial,
	candidate_code varchar(5) NOT NULL,
	user_id int NOT NULL,
	cv_id int,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE
);

ALTER TABLE tb_candidate
	ADD CONSTRAINT tb_candidate_pk PRIMARY KEY(id);

ALTER TABLE tb_candidate
	ADD CONSTRAINT tb_candidate_bk UNIQUE(candidate_code);

ALTER TABLE tb_candidate
	ADD CONSTRAINT tb_candidate_user_bk UNIQUE(user_id);

ALTER TABLE tb_candidate
	ADD CONSTRAINT tb_candidate_user_fk FOREIGN KEY(user_id)
	REFERENCES tb_user(id);

ALTER TABLE tb_candidate
	ADD CONSTRAINT tb_candidate_cv_bk UNIQUE(cv_id);

ALTER TABLE tb_candidate
	ADD CONSTRAINT tb_candidate_cv_fk FOREIGN KEY(cv_id)
	REFERENCES tb_file(id);

CREATE TABLE tb_assign (
	id serial,
	assign_code varchar(5) NOT NULL,
	reviewer_id int NOT NULL,
	candidate_id int  NOT NULL,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE
);


ALTER TABLE tb_assign
	ADD CONSTRAINT tb_assign_pk PRIMARY KEY(id);

ALTER TABLE tb_assign
	ADD CONSTRAINT tb_assign_bk UNIQUE(assign_code);

ALTER TABLE tb_assign
	ADD CONSTRAINT tb_assign_reviewer_fk FOREIGN KEY(reviewer_id)
	REFERENCES tb_user(id);
	
ALTER TABLE tb_assign
	ADD CONSTRAINT tb_assign_candidate_bk UNIQUE(candidate_id);

ALTER TABLE tb_assign
	ADD CONSTRAINT tb_assign_candidate_fk FOREIGN KEY(candidate_id)
	REFERENCES tb_candidate(id);
	

CREATE TABLE tb_subcategory(
		id serial,
		subcategory_code varchar(5) NOT NULL,
		subcategory_name varchar(30) NOT NULL,
		category_test_id int NOT NULL,
		created_by int NOT NULL,
		created_at timestamp WITHOUT TIME ZONE NOT NULL,
		updated_by int,
		updated_at timestamp WITHOUT TIME ZONE,
		versions int NOT NULL DEFAULT 0,
		is_active boolean NOT NULL DEFAULT TRUE	
	);
	
ALTER TABLE tb_subcategory
	ADD CONSTRAINT tb_subcategory_pk PRIMARY KEY(id);

ALTER TABLE tb_subcategory
	ADD CONSTRAINT tb_subcategory_bk UNIQUE(subcategory_code);

ALTER TABLE tb_subcategory
	ADD CONSTRAINT tb_subcategory_ck UNIQUE(subcategory_code, subcategory_name);

ALTER TABLE tb_subcategory
	ADD CONSTRAINT tb_subcategory_category_fk FOREIGN KEY(category_test_id)
	REFERENCES tb_category_test(id);
	

CREATE TABLE tb_question_bank (
	id serial,
	question_bank_code varchar(5) NOT NULL,
	subcategory_id int  NOT NULL,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE	
);

ALTER TABLE tb_question_bank
	ADD CONSTRAINT tb_question_bank_pk PRIMARY KEY(id);

ALTER TABLE tb_question_bank
	ADD CONSTRAINT tb_question_bank_bk UNIQUE(question_bank_code);
	
ALTER TABLE tb_question_bank
	ADD CONSTRAINT tb_question_bank_fk FOREIGN KEY(subcategory_id)
	REFERENCES tb_subcategory(id);

CREATE TABLE tb_question (
	id serial,
	question_code varchar(5) NOT NULL,
	question text NOT NULL,
	question_bank_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE	
);

ALTER TABLE tb_question
	ADD CONSTRAINT tb_question_pk PRIMARY KEY(id);
	
ALTER TABLE tb_question
	ADD CONSTRAINT tb_question_bk UNIQUE(question_code);

ALTER TABLE tb_question
	ADD CONSTRAINT tb_question_bank_fk FOREIGN KEY(question_bank_id)
	REFERENCES tb_question_bank(id);

CREATE 	TABLE tb_multiple_choice (
	id serial,
	choice_code varchar(5) NOT NULL,
	choice_text text NOT NULL,
	answer_key boolean NOT NULL,
	question_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp WITHOUT TIME ZONE NOT NULL,
	updated_by int,
	updated_at timestamp WITHOUT TIME ZONE,
	versions int NOT NULL DEFAULT 0,
	is_active boolean NOT NULL DEFAULT TRUE
);

ALTER TABLE tb_multiple_choice
	ADD CONSTRAINT tb_multiple_choice_pk PRIMARY KEY(id);
	
ALTER TABLE tb_multiple_choice
	ADD CONSTRAINT tb_multiple_choice_bk UNIQUE(choice_code);

ALTER TABLE tb_multiple_choice
	ADD CONSTRAINT tb_multiple_choice_fk FOREIGN KEY(question_id)
	REFERENCES tb_question(id);
	
CREATE TABLE tb_assign_detail (
		id serial,
		assign_detail_code varchar(5) NOT NULL,
		score int,
		note text,
		question_bank_id int NOT NULL,
		assign_id int NOT NULL,
		created_by int NOT NULL,
		created_at timestamp WITHOUT TIME ZONE NOT NULL,
		updated_by int,
		updated_at timestamp WITHOUT TIME ZONE,
		versions int NOT NULL DEFAULT 0,
		is_active boolean NOT NULL DEFAULT TRUE
	);
	
ALTER TABLE tb_assign_detail
	ADD CONSTRAINT tb_assign_detail_pk PRIMARY KEY(id);
	
ALTER TABLE tb_assign_detail
	ADD CONSTRAINT tb_assign_detail_bk UNIQUE(assign_detail_code);

ALTER TABLE tb_assign_detail
	ADD CONSTRAINT tb_assign_fk FOREIGN KEY(assign_id)
	REFERENCES tb_assign(id);

ALTER TABLE tb_assign_detail
	ADD CONSTRAINT tb_assign_bank_fk FOREIGN KEY(question_bank_id)
	REFERENCES tb_question_bank(id);
	
CREATE TABLE tb_answer(
		id serial,
		answer_essay text,
		answer_mc_id int, 
		question_id int NOT NULL,
		assign_detail_id int NOT NULL,
		created_by int NOT NULL,
		created_at timestamp WITHOUT TIME ZONE NOT NULL,
		updated_by int,
		updated_at timestamp WITHOUT TIME ZONE,
		versions int NOT NULL DEFAULT 0,
		is_active boolean NOT NULL DEFAULT TRUE
);
	
ALTER TABLE tb_answer
	ADD CONSTRAINT tb_answer_pk PRIMARY KEY(id);

ALTER TABLE tb_answer
	ADD CONSTRAINT tb_answer_q_fk FOREIGN KEY(question_id)
	REFERENCES tb_question(id);

ALTER TABLE tb_answer
	ADD CONSTRAINT tb_answer_mc_fk FOREIGN KEY(answer_mc_id)
	REFERENCES tb_multiple_choice(id);

ALTER TABLE tb_answer
	ADD CONSTRAINT tb_answer_assign_fk FOREIGN KEY(assign_detail_id)
	REFERENCES tb_assign_detail(id);