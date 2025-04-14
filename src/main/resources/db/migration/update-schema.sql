ALTER TABLE test_model
DROP
COLUMN text_field;

ALTER TABLE sc_user
    MODIFY no_of_help_requests INT NOT NULL;

ALTER TABLE sc_user
    MODIFY user_type INT NULL;