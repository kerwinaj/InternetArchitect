create database ssm;
use ssm;

create table `account` (
  `id` int(5) NOT NULL DEFAULT 0 AUTO_INCREMENT COMMENT 'id',
  `login_name` varchar(45) NOT NULL COMMENT '登录名',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `nick_name` varchar(45) NOT NULL COMMENT '昵称',
  `age` int(13) NOT NULL DEFAULT 0 COMMENT '年龄',
  `location` varchar(45) DEFAULT NULL COMMENT '位置',
  PRIMARY KEY (`id`),
  unique key (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='account';