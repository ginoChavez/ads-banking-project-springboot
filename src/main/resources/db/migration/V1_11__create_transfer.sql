CREATE TABLE transfer(
	transfer_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	person_id BIGINT UNSIGNED NOT NULL,
    number_account_origin VARCHAR(50),
	number_account_destiny VARCHAR(50),
    amount DECIMAL(10,2) NOT NULL,
    operation_number VARCHAR(15),
    date_registry DATE NOT NULL,
    transfer_type VARCHAR(2) NOT NULL,
	PRIMARY KEY (transfer_id),
	INDEX IX_transfer_person_id (person_id),
	CONSTRAINT FK_transfer_person_id FOREIGN KEY (person_id) REFERENCES person (person_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;