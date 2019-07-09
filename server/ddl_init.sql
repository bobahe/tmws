CREATE SCHEMA IF NOT EXISTS `tmdb` DEFAULT CHAR SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `tmdb`;

CREATE TABLE `app_user`
(
    `id`           VARCHAR(255) NOT NULL,
    `email`        VARCHAR(255) DEFAULT NULL,
    `firstName`    VARCHAR(255) DEFAULT NULL,
    `lastName`     VARCHAR(255) DEFAULT NULL,
    `locked`       BIT(1)       DEFAULT NULL,
    `login`        VARCHAR(255) DEFAULT NULL,
    `middleName`   VARCHAR(255) DEFAULT NULL,
    `passwordHash` VARCHAR(255) DEFAULT NULL,
    `phone`        VARCHAR(255) DEFAULT NULL,
    `role`         VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE InnoDB
  DEFAULT CHARSET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `app_session`
(
    `id`        VARCHAR(255) NOT NULL,
    `signature` VARCHAR(255) DEFAULT NULL,
    `timestamp` BIGINT       DEFAULT NULL,
    `userId`    VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `session_to_user_fk` FOREIGN KEY (`userId`) REFERENCES `app_user` (`id`)
) ENGINE InnoDB
  DEFAULT CHARSET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `app_project`
(
    `id`          VARCHAR(255) NOT NULL,
    `name`        VARCHAR(255) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `dateBegin`   DATETIME     DEFAULT NULL,
    `dateEnd`     DATETIME     DEFAULT NULL,
    `userId`      VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `project_to_user_fk` FOREIGN KEY (`userId`) REFERENCES `app_user` (`id`)
) ENGINE InnoDB
  DEFAULT CHARSET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `app_task`
(
    `id`          VARCHAR(255) NOT NULL,
    `name`        VARCHAR(255) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `dateBegin`   DATETIME     DEFAULT NULL,
    `dateEnd`     DATETIME     DEFAULT NULL,
    `userId`      VARCHAR(255) DEFAULT NULL,
    `projectId`   VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `task_to_user_fk` FOREIGN KEY (`userId`) REFERENCES `app_user` (`id`),
    CONSTRAINT `task_to_project_fk` FOREIGN KEY (`projectId`) REFERENCES `app_project` (`id`)
) ENGINE InnoDB
  DEFAULT CHARSET utf8
  COLLATE utf8_general_ci;