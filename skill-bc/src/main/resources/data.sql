INSERT INTO category(id, description)
VALUES ('fe1cc8aa-0c08-11ed-861d-0242ac120002', 'Programming'),
       ('0eff9eb8-0c09-11ed-861d-0242ac120002', 'Version Control'),
       ('721428a6-136c-11ed-861d-0242ac120002', 'SCRUM'),
       ('1b1cc3c4-0c09-11ed-861d-0242ac120002', 'Testing');

INSERT INTO skill(id, description, category)
VALUES ('c347b348-0c08-11ed-861d-0242ac120002', 'Java', 'fe1cc8aa-0c08-11ed-861d-0242ac120002'),
       ('ed20eddc-0c27-11ed-861d-0242ac120002', 'Python', 'fe1cc8aa-0c08-11ed-861d-0242ac120002'),
       ('d7332130-0c08-11ed-861d-0242ac120002', 'Git', '0eff9eb8-0c09-11ed-861d-0242ac120002'),
       ('e44db664-0c08-11ed-861d-0242ac120002', 'JUnit', '1b1cc3c4-0c09-11ed-861d-0242ac120002');


