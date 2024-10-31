DROP SEQUENCE IF EXISTS "id";
CREATE SEQUENCE users_seq
   START WITH 1
   INCREMENT BY 1
   NO MINVALUE
   NO MAXVALUE
   CACHE 1;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id smallint Not NULL DEFAULT nextval('users_seq'),
    user_name VARCHAR(256),
    email VARCHAR(256),
    password VARCHAR(256)
);


DROP SEQUENCE IF EXISTS "author_id_seq";
CREATE SEQUENCE "author_id_seq" INCREMENT BY 50 START 1;

DROP SEQUENCE IF EXISTS "books_isbn_seq";
CREATE SEQUENCE "books_isbn_seq" INCREMENT BY 50 START 1;

DROP TABLE IF EXISTS "authors";
CREATE TABLE "authors"(
   "id" bigint Not NULL,
   "name" VARCHAR(512),
   "age"  smallint,
   "description" VARCHAR(512),
   "image" VARCHAR(512),
   CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "books";
CREATE TABLE "books"(
  "isbn" bigint Not NULL,
  "title" VARCHAR(512),
  "description" VARCHAR(2048),
  "image" VARCHAR(512),
  "author_id" bigint,
  CONSTRAINT "books_pkey" PRIMARY KEY ("isbn")
);