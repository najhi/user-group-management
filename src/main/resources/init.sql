-- Drop all tables
DROP TABLE IF EXISTS "public"."user_groups";
DROP TABLE IF EXISTS "public"."groups";
DROP TABLE IF EXISTS "public"."users";

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS groups_id_seq;

-- Table Definition
CREATE TABLE "public"."groups"(
                                                "id" int8 NOT NULL DEFAULT nextval('groups_id_seq'::regclass),
                                                "description" varchar(255),
                                                "name" varchar(255) NOT NULL,
                                                PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."user_groups" (
                                        "group_id" int8 NOT NULL,
                                        "user_id" int8 NOT NULL,
                                        PRIMARY KEY ("group_id","user_id")
);

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS users_id_seq;

-- Table Definition
CREATE TABLE IF NOT EXISTS "public"."users" (
                                  "id" int8 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
                                  "email" varchar(255) NOT NULL,
                                  "password" varchar(255) NOT NULL,
                                  "username" varchar(255) NOT NULL,
                                  PRIMARY KEY ("id")
);

INSERT INTO "public"."groups" ("id", "description", "name") VALUES
                                                                (1, 'Administrators group', 'Admin'),
                                                                (2, 'Regular users group', 'User');

INSERT INTO "public"."users" ("id", "email", "password", "username") VALUES
                                                                         (1, 'test1@gmail.com', 'pass', 'test1'),
                                                                         (2, 'test2@gmail.com', 'pass', 'test2'),
                                                                         (3, 'test3@gmail.com', 'pass', 'test3');

-- Indices
CREATE UNIQUE INDEX groups_name_key ON public.groups USING btree (name);
ALTER TABLE "public"."user_groups" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id");
ALTER TABLE "public"."user_groups" ADD FOREIGN KEY ("group_id") REFERENCES "public"."groups"("id");


-- Indices
CREATE UNIQUE INDEX users_email_key ON public.users USING btree (email);
CREATE UNIQUE INDEX users_username_key ON public.users USING btree (username);
