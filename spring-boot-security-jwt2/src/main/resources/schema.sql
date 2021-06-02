DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `users_roles`;
DROP TABLE IF EXISTS `roles_permissions`;
DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `users`
(
    `id`       bigint(11)   NOT NULL AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `enabled`  int          NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `roles`
(
    `id`   bigint(11)   NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `users_roles`
(
    `user_id` bigint(11) NOT NULL,
    `role_id` bigint(11) NOT NULL
);
CREATE TABLE `roles_authorities`
(
    `role_id`      bigint(11) NOT NULL,
    `authority_id` bigint(11) NOT NULL
);
CREATE TABLE `authorities`
(
    `id`          bigint(11)   NOT NULL AUTO_INCREMENT,
    `url`         varchar(255) NOT NULL,
    `name`        varchar(255) NOT NULL,
    `description` varchar(255) NULL,
    `pid`         bigint(11)   NOT NULL,
    PRIMARY KEY (`id`)
);