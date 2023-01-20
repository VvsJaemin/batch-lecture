create table `customer`(
    `id` mediumint(8) unsigned not null auto_increment,
    `firstName` varchar(255) default null,
    `lastName` varchar(255) default null,
    `birthdate` varchar(255),
    PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;