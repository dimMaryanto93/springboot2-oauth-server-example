insert into user_management.users (id,
                                   username,
                                   email,
                                   password,
                                   is_enabled,
                                   is_locked,
                                   is_sudo,
                                   created_by,
                                   created_date,
                                   last_updated_by,
                                   last_updated_date)
values ('sudo', 'root', 'root@example.com', '$2a$11$BN3ZZd8JnkfmDHkAcIt8MOym3rqMIj/6AkrOxl7gpIBdkdLbD6iwm',
        true, false, true, 'migration', now(), null, null),
       ('user', 'user', 'user@example.com', '$2a$11$8WpUcgUaKUeqLL3W1uSWR.LfOFdzLptfF5/qUcI7.cx/.fCpuw/5m',
        true, false, false, 'migration', now(), null, null);

insert into user_management.roles (id, name, description, is_hide)
values (1, 'ROLE_MEMBER_CREATE', 'Role untuk membuat data user', false),
       (2, 'ROLE_MEMBER_UPDATE', 'Role untuk update data user', false),
       (3, 'ROLE_MEMBER_DELETE', 'Role untuk menghapus data user', false),
       (4, 'ROLE_MEMBER_READ', 'Role untuk read data user', false),
       (5, 'ROLE_GROUP_CREATE', 'Role untuk membuat group baru', false),
       (6, 'ROLE_GROUP_UPDATE', 'Role untuk mengupdate group', false),
       (7, 'ROLE_GROUP_DELETE', 'Role untuk delete group', false),
       (8, 'ROLE_GROUP_READ', 'Role untuk membaca data group', false);

insert into user_management.group_role (id, name, description)
VALUES ('USER_MANAGEMENT', 'ALL MANAGEMENT USER', '-'),
       ('GROUP_MANAGEMENT', 'ALL GROUP ROLE MANAGEMENT', '-');

insert into user_management.group_member_role (id,
                                               group_id,
                                               role_id,
                                               created_by,
                                               created_date,
                                               last_update_by,
                                               last_update_date)
values ('USER_MANAGEMENT_1', 'USER_MANAGEMENT', 1, 'migration', now(), null, null),
       ('USER_MANAGEMENT_2', 'USER_MANAGEMENT', 2, 'migration', now(), null, null),
       ('USER_MANAGEMENT_3', 'USER_MANAGEMENT', 3, 'migration', now(), null, null),
       ('USER_MANAGEMENT_4', 'USER_MANAGEMENT', 4, 'migration', now(), null, null),
       ('GROUP_MANAGEMENT_1', 'GROUP_MANAGEMENT', 5, 'migration', now(), null, null),
       ('GROUP_MANAGEMENT_2', 'GROUP_MANAGEMENT', 6, 'migration', now(), null, null),
       ('GROUP_MANAGEMENT_3', 'GROUP_MANAGEMENT', 7, 'migration', now(), null, null),
       ('GROUP_MANAGEMENT_4', 'GROUP_MANAGEMENT', 8, 'migration', now(), null, null);

INSERT INTO user_management.group_member_users (id,
                                                user_id,
                                                group_id,
                                                created_by,
                                                created_date,
                                                last_update_by,
                                                last_update_date)
values ('USER_GROUP_1', 'user', 'USER_MANAGEMENT', 'migration', now(), null, null);