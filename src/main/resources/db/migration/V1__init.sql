CREATE TABLE `roles` (
                         `id` int(11) NOT NULL,
                         `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `roles`  ADD PRIMARY KEY (`id`);
#########################
CREATE TABLE `users` (
                         `id` bigint(20) NOT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `users`
    ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
#####################################
CREATE TABLE `users_roles` (
                               `role_id` int(11) NOT NULL,
                               `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `users_roles`
    ADD KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
    ADD KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`);

ALTER TABLE `users_roles`
    ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    ADD CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;
