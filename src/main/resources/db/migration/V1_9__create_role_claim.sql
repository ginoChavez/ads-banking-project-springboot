CREATE TABLE role_claim (
  role_claim_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  claim_type VARCHAR(100) NOT NULL,
  claim_value VARCHAR(100) NOT NULL,
  role_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (role_claim_id),
  INDEX IX_role_claim_role_id (role_id),
  CONSTRAINT FK_role_claim_role_id FOREIGN KEY (role_id) REFERENCES role (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;