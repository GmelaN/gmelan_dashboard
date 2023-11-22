CREATE DATABASE `dashboard` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE dashboard;

CREATE TABLE `user` (
                        `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                        `permission_id`	BIGINT	NOT NULL,
                        `username`	VARCHAR(15)	NOT NULL	UNIQUE,
                        `password`	VARCHAR(128)	NOT NULL	COMMENT 'hashed string',
                        `email`	VARCHAR(30)	NOT NULL	UNIQUE COMMENT 'email',
                        `is_available`	BOOLEAN	NOT NULL	DEFAULT true,
                        `type`	VARCHAR(10)	NOT NULL	DEFAULT 'IN_APP'	COMMENT 'IN_APP, KAKAO, FACEBOOK, ...',
                        `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                        `updated_at`	TIMESTAMP	NULL

);

CREATE TABLE `reference` (
                             `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                             `user_id`	BIGINT	NOT NULL,
                             `reference_category_id`	BIGINT	NOT NULL,
                             `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목 없음',
                             `content`	TEXT	NULL	DEFAULT '내용 없음',
                             `url`	VARCHAR(50)	NULL DEFAULT 'URL 없음',
                             `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                             `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `media` (
                         `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                         `url`	VARCHAR(50)	NOT NULL,
                         `type`	VARCHAR(10)	NULL DEFAULT '분류 없음',
                         `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                         `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `todo-category` (
                                 `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                 `name`	VARCHAR(20)	NOT NULL	UNIQUE DEFAULT '분류 없음',
                                 `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                                 `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `todo-media` (
                              `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                              `media_id`	BIGINT	NOT NULL,
                              `todo_id`	BIGINT	NOT NULL,
                              `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                              `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `reference-media` (
                                   `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                   `reference_id`	BIGINT	NOT NULL,
                                   `media_id`	BIGINT	NOT NULL,
                                   `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                                   `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `user-permission` (
                              `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                              `permission`	VARCHAR(10)	NOT NULL UNIQUE	DEFAULT 'USER',
                              `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` TIMESTAMP NULL
);

CREATE TABLE `JWT_token` (
                             `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                             `user_id`	BIGINT	NOT NULL,
                             `token`	VARCHAR(255)	NOT NULL	UNIQUE,
                             `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                             `expire_at`	TIMESTAMP	NOT NULL
);

CREATE TABLE `reference-category` (
                                      `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                      `name`	VARCHAR(20)	NOT NULL	UNIQUE DEFAULT '분류 없음',
                                      `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                                      `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `todo` (
                        `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                        `user_id`	BIGINT	NOT NULL,
                        `todo_category_id`	BIGINT	NOT NULL,
                        `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목',
                        `content`	TEXT	NULL	DEFAULT '내용 없음',
                        `url`	VARCHAR(50)	NULL default 'URL 없음',
                        `start_date`	TIMESTAMP	NULL,
                        `end_date`	TIMESTAMP	NULL,
                        `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `updated_at` TIMESTAMP NULL

);

ALTER TABLE `user` ADD CONSTRAINT `FK_user_permission_TO_user_1` FOREIGN KEY (
                                                                         `permission_id`
    )
    REFERENCES `user-permission` (
                             `id`
        );

ALTER TABLE `reference` ADD CONSTRAINT `FK_user_TO_reference_1` FOREIGN KEY (
                                                                             `user_id`
    )
    REFERENCES `user` (
                       `id`
        );

ALTER TABLE `reference` ADD CONSTRAINT `FK_reference_category_TO_reference_1` FOREIGN KEY (
                                                                                           `reference_category_id`
    )
    REFERENCES `reference-category` (
                                     `id`
        );

ALTER TABLE `todo-media` ADD CONSTRAINT `FK_media_TO_todo-media_1` FOREIGN KEY (
                                                                                `media_id`
    )
    REFERENCES `media` (
                        `id`
        );

ALTER TABLE `todo-media` ADD CONSTRAINT `FK_todo_TO_todo-media_1` FOREIGN KEY (
                                                                               `todo_id`
    )
    REFERENCES `todo` (
                       `id`
        );

ALTER TABLE `reference-media` ADD CONSTRAINT `FK_reference_TO_reference-media_1` FOREIGN KEY (
                                                                                              `reference_id`
    )
    REFERENCES `reference` (
                            `id`
        );

ALTER TABLE `reference-media` ADD CONSTRAINT `FK_media_TO_reference-media_1` FOREIGN KEY (
                                                                                          `media_id`
    )
    REFERENCES `media` (
                        `id`
        );

ALTER TABLE `JWT_token` ADD CONSTRAINT `FK_user_TO_JWT_token_1` FOREIGN KEY (
                                                                             `user_id`
    )
    REFERENCES `user` (
                       `id`
        );

ALTER TABLE `todo` ADD CONSTRAINT `FK_user_TO_todo_1` FOREIGN KEY (
                                                                   `user_id`
    )
    REFERENCES `user` (
                       `id`
        );

ALTER TABLE `todo` ADD CONSTRAINT `FK_todo_category_TO_todo_1` FOREIGN KEY (
                                                                            `todo_category_id`
    )
    REFERENCES `todo-category` (
                                `id`
        );

