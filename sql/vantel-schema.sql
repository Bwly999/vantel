DROP TABLE IF EXISTS `vantel_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vantel_admin` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `username` varchar(128) DEFAULT NULL,
                               `password` varchar(255) DEFAULT NULL,
                               `state` tinyint(4) DEFAULT '0',
                               `be_deleted` tinyint(4) DEFAULT '0',
                               `creator_id` bigint(20) DEFAULT NULL,
                               `creator_name` varchar(128) DEFAULT NULL,
                               `modifier_id` bigint(20) DEFAULT NULL,
                               `modifier_name` varchar(128) DEFAULT NULL,
                               `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `gmt_modified` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '管理员表';

DROP TABLE IF EXISTS `vantel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vantel_room` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `number` varchar(128) DEFAULT NULL,
                               `state` tinyint(4) DEFAULT '0',
                               `be_deleted` tinyint(4) DEFAULT '0',
                               `creator_id` bigint(20) DEFAULT NULL,
                               `creator_name` varchar(128) DEFAULT NULL,
                               `modifier_id` bigint(20) DEFAULT NULL,
                               `modifier_name` varchar(128) DEFAULT NULL,
                               `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `gmt_modified` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '房间表';

DROP TABLE IF EXISTS `vantel_humiture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vantel_temperature` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `room_id` bigint(20) DEFAULT NULL,
                               `value` float(8, 2) DEFAULT NULL,
                               `creator_id` bigint(20) DEFAULT NULL,
                               `creator_name` varchar(128) DEFAULT NULL,
                               `modifier_id` bigint(20) DEFAULT NULL,
                               `modifier_name` varchar(128) DEFAULT NULL,
                               `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `gmt_modified` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '温度表';

DROP TABLE IF EXISTS `vantel_humidity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vantel_humidity` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `room_id` bigint(20) DEFAULT NULL,
                                   `value` float(8, 2) DEFAULT NULL,
                                   `creator_id` bigint(20) DEFAULT NULL,
                                   `creator_name` varchar(128) DEFAULT NULL,
                                   `modifier_id` bigint(20) DEFAULT NULL,
                                   `modifier_name` varchar(128) DEFAULT NULL,
                                   `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `gmt_modified` datetime DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '湿度表';


