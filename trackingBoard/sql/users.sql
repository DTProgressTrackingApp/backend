/*
 Navicat Premium Data Transfer

 Source Server         : PostgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 140008
 Source Host           : localhost:5432
 Source Catalog        : DTwebapp
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140008
 File Encoding         : 65001

 Date: 12/07/2023 23:13:17
*/


-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" int4 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "role" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "public"."users" VALUES (1, 'manager@gmail.com', '$2a$10$fpk71zauCfT2R/FXXOCt8ucY16WtGqloEjs/BswRv5W43gyj5MNTu', 'MANAGER');
INSERT INTO "public"."users" VALUES (2, 'member1@gmail.com', '$2a$10$fhRSca7qq7ecOkxxZXLrMOL9W3oPv5SsY0IdsOqg/tGsXNp.Yhd0i', 'MEMBER');
INSERT INTO "public"."users" VALUES (3, 'member2@gmail.com', '$2a$10$5Xe0bRtg7rfXgglKT0K2IujfMHy2uZms.pcmErMYm2PErmHZHR/Ru', 'MEMBER');

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");
