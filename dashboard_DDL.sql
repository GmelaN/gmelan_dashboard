CREATE TABLE `news` (
                        `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                        `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목',
                        `published_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                        `content`	TEXT	NULL	DEFAULT '내용 없음',
                        `url`	VARCHAR(50)	NULL,
                        `fetched_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                        `category`	VARCHAR(20)	NOT NULL  DEFAULT '분류 없음'	COMMENT '뉴스 카테고리',
                        `company`	VARCHAR(20)	NULL,
                        `reporter`	VARCHAR(20)	NULL
);

CREATE TABLE `notice` (
                          `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                          `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목',
                          `published_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                          `content`	TEXT	NULL	DEFAULT '내용 없음',
                          `url`	VARCHAR(50)	NULL,
                          `agency`	VARCHAR(20)	NULL,
                          `fetched_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `user` (
                        `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                        `permission_id`	BIGINT	NOT NULL,
                        `username`	VARCHAR(15)	NOT NULL	UNIQUE,
                        `password`	VARCHAR(128)	NOT NULL	COMMENT 'hashed string',
                        `email`	VARCHAR(30)	NOT NULL	UNIQUE COMMENT 'email',
                        `created_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                        `is_available`	BOOLEAN	NOT NULL	DEFAULT true,
                        `type`	VARCHAR(10)	NOT NULL	DEFAULT 'IN_APP'	COMMENT '인앱, 카카오, 페이스북'
);

CREATE TABLE `reference` (
                             `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                             `user_id`	BIGINT	NOT NULL,
                             `reference_category_id`	BIGINT	NOT NULL,
                             `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목',
                             `content`	TEXT	NULL	DEFAULT '내용 없음',
                             `url`	VARCHAR(50)	NULL,
                             `created_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                             `updated_at`	DATETIME	NULL
);

CREATE TABLE `media` (
                         `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                         `url`	VARCHAR(50)	NULL,
                         `uploaded_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                         `type`	VARCHAR(10)	NULL
);

CREATE TABLE `todo-category` (
                                 `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                 `name`	VARCHAR(20)	NOT NULL	UNIQUE DEFAULT '분류 없음',
                                 `created_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP
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

CREATE TABLE `permission` (
                              `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                              `permission`	VARCHAR(10)	NOT NULL	DEFAULT 'USER'
);

CREATE TABLE `JWT_token` (
                             `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                             `user_id`	BIGINT	NOT NULL,
                             `token`	VARCHAR(255)	NOT NULL	UNIQUE,
                             `expire_at`	DATETIME	NOT NULL
);

CREATE TABLE `reference-category` (
                                      `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                                      `name`	VARCHAR(20)	NOT NULL	UNIQUE DEFAULT '분류 없음',
                                      `created_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `todo` (
                        `id`	BIGINT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
                        `user_id`	BIGINT	NOT NULL,
                        `todo_category_id`	BIGINT	NOT NULL,
                        `title`	VARCHAR(30)	NOT NULL	DEFAULT '제목',
                        `content`	TEXT	NULL	DEFAULT '내용 없음',
                        `start_date`	DATETIME	NULL,
                        `end_date`	DATETIME	NULL,
                        `url`	VARCHAR(50)	NULL
);

ALTER TABLE `news` ADD CONSTRAINT `PK_NEWS` PRIMARY KEY (
                                                         `id`
    );

ALTER TABLE `notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
                                                             `id`
    );

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
                                                         `id`,
                                                         `permission_id`
    );

ALTER TABLE `reference` ADD CONSTRAINT `PK_REFERENCE` PRIMARY KEY (
                                                                   `id`,
                                                                   `user_id`,
                                                                   `reference_category_id`
    );

ALTER TABLE `media` ADD CONSTRAINT `PK_MEDIA` PRIMARY KEY (
                                                           `id`
    );

ALTER TABLE `todo-category` ADD CONSTRAINT `PK_TODO_CATEGORY` PRIMARY KEY (
                                                                           `id`
    );

ALTER TABLE `todo-media` ADD CONSTRAINT `PK_TODO-MEDIA` PRIMARY KEY (
                                                                     `id`,
                                                                     `media_id`,
                                                                     `todo_id`
    );

ALTER TABLE `news-media` ADD CONSTRAINT `PK_NEWS-MEDIA` PRIMARY KEY (
                                                                     `id`,
                                                                     `media_id`,
                                                                     `news_id`
    );

ALTER TABLE `notice-media` ADD CONSTRAINT `PK_NOTICE-MEDIA` PRIMARY KEY (
                                                                         `id`,
                                                                         `media_id`,
                                                                         `notice_id`
    );

ALTER TABLE `reference-media` ADD CONSTRAINT `PK_REFERENCE-MEDIA` PRIMARY KEY (
                                                                               `id`,
                                                                               `reference_id`,
                                                                               `media_id`
    );

ALTER TABLE `permission` ADD CONSTRAINT `PK_PERMISSION` PRIMARY KEY (
                                                                     `id`
    );

ALTER TABLE `JWT_token` ADD CONSTRAINT `PK_JWT_TOKEN` PRIMARY KEY (
                                                                   `id`,
                                                                   `user_id`
    );

ALTER TABLE `reference-category` ADD CONSTRAINT `PK_REFERENCE_CATEGORY` PRIMARY KEY (
                                                                                     `id`
    );

ALTER TABLE `todo` ADD CONSTRAINT `PK_TODO` PRIMARY KEY (
                                                         `id`,
                                                         `user_id`,
                                                         `todo_category_id`
    );

ALTER TABLE `user` ADD CONSTRAINT `FK_permission_TO_user_1` FOREIGN KEY (
                                                                         `permission_id`
    )
    REFERENCES `permission` (
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

