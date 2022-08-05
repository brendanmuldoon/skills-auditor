INSERT INTO employee (id,
                   fullname_firstname,
                   fullname_surname,
                   address_housenumber,
                   address_streetname,
                   address_postcode,
                   role,
                   securitycredentials_username,
                   securitycedentials_password)
VALUES  ('123e4567-e89b-12d3-a456-426614174000', 'Brendan', 'Muldoon', '5', 'Foxton Place', 'BT365FS', 'Manager', 'brendy', 'password'),
        ('5199227a-0845-11ed-861d-0242ac120002', 'Emma', 'Muldoon', '5', 'Foxton Place', 'BT365FS', 'Staff', 'emma', 'password'),
        ('6f331876-e037-4d98-a906-4274420570e1', 'test', 'test', '1', 'Foxton Place', 'BT365FS', 'Staff', 'test', 'password'),
        ('5f52c9e8-0845-11ed-861d-0242ac120002', 'Billy', 'Bob', '56', 'Rad Town', 'BT124FT', 'Staff', 'billybob', 'password');




INSERT INTO staff_skills(skill_id, strength_of_skill, expiry, staff_id)
VALUES ('c347b348-0c08-11ed-861d-0242ac120002', 'Basic', '2022-12-31', '5199227a-0845-11ed-861d-0242ac120002'),
       ('d7332130-0c08-11ed-861d-0242ac120002', 'Intermediate', '2022-12-31', '5199227a-0845-11ed-861d-0242ac120002'),
       ('e44db664-0c08-11ed-861d-0242ac120002', 'Advanced', '2022-12-31', '5f52c9e8-0845-11ed-861d-0242ac120002');
create sequence skills_sequence_id start with (select max(id) + 1 from staff_skills);

INSERT INTO manager_team(staff_id, manager_id)
VALUES ('5199227a-0845-11ed-861d-0242ac120002', '123e4567-e89b-12d3-a456-426614174000'),
       ('5f52c9e8-0845-11ed-861d-0242ac120002', '123e4567-e89b-12d3-a456-426614174000');
create sequence team_sequence_id start with (select max(id) + 1 from manager_team);

INSERT INTO category(id, description)
VALUES ('fe1cc8aa-0c08-11ed-861d-0242ac120002', 'Programming'),
       ('0eff9eb8-0c09-11ed-861d-0242ac120002', 'Version Control'),
       ('721428a6-136c-11ed-861d-0242ac120002', 'SCRUM'),
       ('1b1cc3c4-0c09-11ed-861d-0242ac120002', 'Testing');
-- create sequence category_sequence_id start with (select max(id) + 1 from category);

INSERT INTO skill(id, description, category)
VALUES ('c347b348-0c08-11ed-861d-0242ac120002', 'Java', 'fe1cc8aa-0c08-11ed-861d-0242ac120002'),
       ('ed20eddc-0c27-11ed-861d-0242ac120002', 'Python', 'fe1cc8aa-0c08-11ed-861d-0242ac120002'),
       ('d7332130-0c08-11ed-861d-0242ac120002', 'Git', '0eff9eb8-0c09-11ed-861d-0242ac120002'),
       ('e44db664-0c08-11ed-861d-0242ac120002', 'JUnit', '1b1cc3c4-0c09-11ed-861d-0242ac120002');


