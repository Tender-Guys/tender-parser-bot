DROP TABLE IF EXISTS 	users, 
						tenders, 
						key_words, 
						users_key_words, 
						tender_sites, 
						tender_initiators;

CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
	user_chat_id VARCHAR(32) UNIQUE NOT NULL,
    user_first_name VARCHAR(32),
    user_last_name VARCHAR(32),

	CONSTRAINT pk_users_user_id PRIMARY KEY (user_id)
);

CREATE TABLE key_words (
	key_word_id INT NOT NULL AUTO_INCREMENT,
	key_word_value VARCHAR(256) UNIQUE NOT NULL,	

	CONSTRAINT pk_key_words_key_words_id PRIMARY KEY (key_word_id)
);

CREATE TABLE users_key_words (
	user_id	INT NOT NULL,
	key_word_id	INT NOT NULL,
	
	CONSTRAINT pk_users_key_words_user_id_key_word_id PRIMARY KEY (user_id, key_word_id),
	CONSTRAINT fk_users_key_words_user FOREIGN KEY (user_id) 
		REFERENCES users (user_id),
	CONSTRAINT fk_users_key_words_key_word FOREIGN KEY (key_word_id) 
		REFERENCES key_words (key_word_id)
);


CREATE TABLE tender_sites (
	tender_site_id INT NOT NULL AUTO_INCREMENT,
	tender_site_name VARCHAR(32) NOT NULL,
	tender_site_home_url VARCHAR(512) UNIQUE NOT NULL,

	CONSTRAINT pk_tender_sites_tender_site_id PRIMARY KEY (tender_site_id)
);

CREATE TABLE tender_initiators (
	tender_initiator_id	INT NOT NULL AUTO_INCREMENT,
	tender_initiator_name VARCHAR(256) NOT NULL,
    tender_initiator_contact_name VARCHAR(256),
    tender_initiator_contact_phone VARCHAR(32),

	CONSTRAINT pk_initiators_initiator_id PRIMARY KEY (tender_initiator_id)
);

CREATE TABLE tenders (
	tender_id INT NOT NULL AUTO_INCREMENT,
	tender_site_id INT,
	tender_site_inner_id VARCHAR(32) NOT NULL,
	tender_initiator_id	INT,
	tender_subject VARCHAR(512) NOT NULL,
	tender_start_time DATETIME NOT NULL,
	tender_end_time	DATETIME NOT NULL,
	tender_url VARCHAR(512) UNIQUE NOT NULL,

	CONSTRAINT pk_tenders_tender_id PRIMARY KEY (tender_id),
	CONSTRAINT fk_tenders_tender_site_id FOREIGN KEY (tender_site_id) 
		REFERENCES tender_sites (tender_site_id),
	CONSTRAINT fk_tenders_tender_initiator_id FOREIGN KEY (tender_initiator_id) 
		REFERENCES tender_initiators (tender_initiator_id)
);