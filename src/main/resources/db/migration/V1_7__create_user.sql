CREATE TABLE user (
  user_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  person_id BIGINT UNSIGNED,
  role_id BIGINT UNSIGNED NOT NULL,
  user_name VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE INDEX UQ_user_name (user_name),
  INDEX IX_user_person_id (person_id),
  INDEX IX_user_role_id (role_id),
  CONSTRAINT FK_user_person_id FOREIGN KEY (person_id) REFERENCES person (person_id),
  CONSTRAINT FK_user_role_id FOREIGN KEY (role_id) REFERENCES role (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;