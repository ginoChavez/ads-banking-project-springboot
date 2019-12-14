CREATE TABLE bank_account (
  bank_account_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  number VARCHAR(50) NOT NULL,
  balance DECIMAL(10,2) NOT NULL,
  overdraft DECIMAL(10,2) NOT NULL,
  locked BIT NOT NULL,
  person_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY(bank_account_id),
  INDEX IX_bank_account_person_id (person_id),
  UNIQUE INDEX UQ_bank_account_number (number),
  CONSTRAINT FK_bank_account_person_id FOREIGN KEY (person_id) REFERENCES person (person_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;