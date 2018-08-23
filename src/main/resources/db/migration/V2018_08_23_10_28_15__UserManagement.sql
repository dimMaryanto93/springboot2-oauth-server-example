drop schema if exists user_management;
create schema user_management;

create table user_management.users (
  id                character varying(150) not null primary key,
  username          character varying(100) not null unique,
  email             character varying(150) not null unique,
  password          character varying(255) not null,
  is_enabled        boolean                not null         default false,
  is_locked         boolean                not null         default true,
  is_sudo           boolean                not null         default false,
  created_by        character varying(150) not null,
  created_date      timestamp              not null         default now(),
  last_updated_by   character varying(150),
  last_updated_date timestamp
);

create table user_management.roles (
  id          serial                 not null primary key,
  name        character varying(100) not null,
  description character varying(255),
  is_hide     boolean                not null default false
);

create table user_management.group_role (
  id          character varying(150) not null primary key,
  name        character varying(100) not null,
  description character varying(255)
);

create table user_management.group_member_role (
  id               character varying(150) not null primary key,
  group_id         character varying(150) not null,
  role_id          int                    not null,
  created_by       character varying(150) not null,
  created_date     timestamp              not null default now(),
  last_update_by   character varying(150),
  last_update_date timestamp
);

alter table user_management.group_member_role
  add constraint fk_group_role_id foreign key (group_id)
references user_management.group_role (id)
on update cascade on delete cascade;

alter table user_management.group_member_role
  add constraint fk_roles_id foreign key (role_id)
references user_management.roles (id)
on update cascade on delete cascade;

alter table user_management.group_member_role
  add constraint uq_group_and_role unique (group_id, role_id);

create table user_management.group_member_users (
  id               character varying(150) not null primary key,
  user_id          character varying(150) not null,
  group_id         character varying(100) not null,
  created_by       character varying(150) not null,
  created_date     timestamp              not null default now(),
  last_update_by   character varying(150),
  last_update_date timestamp
);

alter table user_management.group_member_users
  add constraint fk_users_id foreign key (user_id)
references user_management.users (id) on update cascade on delete cascade;

alter table user_management.group_member_users
  add constraint fk_group_id foreign key (group_id)
references user_management.group_role on update cascade on delete cascade;

alter table user_management.group_member_users
  add constraint uq_user_and_group unique (user_id, group_id);