CREATE TABLE `user` ( `id` int(10) NOT NULL AUTO_INCREMENT, `user_name` varchar(255) NOT NULL,  `password` varchar(255) NOT NULL, `role` enum('user','admin') default 'user' NOT NULL, PRIMARY KEY (`id`) );

INSERT into `user`(user_name,password,role) values("user","$2y$10$6qTQyIF9k2mDpiTya5bsnurfvHhvIjWhs243AQn2XCiyWU6JOUDJu","user");

INSERT into `user`(user_name,password,role) values("admin","$2y$10$yb1tmcFQuFFyKa8o7OO2EOwFvVDdcUBn8FwCafxzA9OXOt1r.G5Om","admin");

INSERT into `user`(user_name,password,role) values("thiri","$2y$10$6qTQyIF9k2mDpiTya5bsnurfvHhvIjWhs243AQn2XCiyWU6JOUDJu","user");



