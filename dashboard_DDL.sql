CREATE DATABASE `dashboard` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE dashboard;

CREATE TABLE `news` (
                        `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                        `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목 없음',
                        `published_at`	TIMESTAMP 	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                        `content`	TEXT	NULL	DEFAULT '내용 없음',
                        `url`	VARCHAR(50)	NULL DEFAULT 'URL 정보 없음',
                        `category`	VARCHAR(20)	NOT NULL  DEFAULT '분류 없음'	COMMENT '뉴스 카테고리',
                        `company`	VARCHAR(20)	NULL DEFAULT '언론사 정보 없음',
                        `reporter`	VARCHAR(20)	NULL DEFAULT '기자 정보 없음',
                        `fetched_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `notice` (
                          `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                          `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목 없음',
                          `published_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                          `content`	TEXT	NULL	DEFAULT '내용 없음',
                          `url`	VARCHAR(50)	NULL DEFAULT 'URL 정보 없음',
                          `agency`	VARCHAR(20)	NULL DEFAULT '기관 정보 없음',
                          `fetched_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

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
                             `url`	VARCHAR(50)	NULL DEFAULT 'URL 정보 없음',
                             `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                             `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `media` (
                         `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                         `url`	VARCHAR(50)	NULL DEFAULT 'URL 정보 없음',
                         `type`	VARCHAR(10)	NULL DEFAULT '타입 정보 없음',
                         `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                         `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `todo-category` (
                                 `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                 `name`	VARCHAR(20)	NOT NULL	UNIQUE DEFAULT '기본 분류',
                                 `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `todo-media` (
                              `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                              `media_id`	BIGINT	NOT NULL,
                              `todo_id`	BIGINT	NOT NULL
);

CREATE TABLE `news-media` (
                              `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                              `media_id`	BIGINT	NOT NULL,
                              `news_id`	BIGINT	NOT NULL
);

CREATE TABLE `notice-media` (
                                `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                `media_id`	BIGINT	NOT NULL,
                                `notice_id`	BIGINT	NOT NULL
);

CREATE TABLE `reference-media` (
                                   `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                   `reference_id`	BIGINT	NOT NULL,
                                   `media_id`	BIGINT	NOT NULL
);

CREATE TABLE `user-permission` (
                              `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                              `permission`	VARCHAR(10)	NOT NULL UNIQUE	DEFAULT 'USER',
                              `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
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
                                      `created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `todo` (
                        `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                        `user_id`	BIGINT	NOT NULL,
                        `todo_category_id`	BIGINT	NOT NULL,
                        `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목',
                        `content`	TEXT	NULL	DEFAULT '내용 없음',
                        `url`	VARCHAR(50)	NULL default 'URL 정보 없음',
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

ALTER TABLE `news-media` ADD CONSTRAINT `FK_media_TO_news-media_1` FOREIGN KEY (
                                                                                `media_id`
    )
    REFERENCES `media` (
                        `id`
        );

ALTER TABLE `news-media` ADD CONSTRAINT `FK_news_TO_news-media_1` FOREIGN KEY (
                                                                               `news_id`
    )
    REFERENCES `news` (
                       `id`
        );

ALTER TABLE `notice-media` ADD CONSTRAINT `FK_media_TO_notice-media_1` FOREIGN KEY (
                                                                                    `media_id`
    )
    REFERENCES `media` (
                        `id`
        );

ALTER TABLE `notice-media` ADD CONSTRAINT `FK_notice_TO_notice-media_1` FOREIGN KEY (
                                                                                     `notice_id`
    )
    REFERENCES `notice` (
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

