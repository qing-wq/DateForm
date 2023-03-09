CREATE TABLE IF NOT EXISTS `data` (
                                      title varchar(50) not null comment '标题',
                                      `desc` varchar(500) not null comment '内容',
                                      `begin-time` bigint not null comment '开始时间',
                                      `end-time` bigint not null comment '结束时间'
)ENGINE=INNODB DEFAULT CHARSET=utf8;
