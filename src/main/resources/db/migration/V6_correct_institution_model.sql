ALTER TABLE admin_user_model
DROP FOREIGN KEY fk_admin_user_institution;

ALTER TABLE admin_user_model
ADD CONSTRAINT fk_admin_user_institution
FOREIGN KEY (id_institution)
REFERENCES institutions(id_institution)
ON DELETE CASCADE;
