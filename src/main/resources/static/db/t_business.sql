CREATE TABLE `t_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `business_name` varchar(20) NOT NULL COMMENT '商家名',
  `password` char(32) NOT NULL COMMENT '密码',
  `salt` char(36) NOT NULL COMMENT '盐值',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `license` varchar(100) DEFAULT NULL COMMENT '营业执照',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除，0-未删除，1-已删除',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '最后修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `the_status` int(11) DEFAULT NULL COMMENT '审核状态0：审核中，1：通过，2：未通过',
  PRIMARY KEY (`id`),
  UNIQUE KEY `business_name` (`business_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ;
