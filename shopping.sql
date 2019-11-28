/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.7.18 : Database - shopping
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shopping` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shopping`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `caid` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品类别id',
  `caname` varchar(20) DEFAULT NULL COMMENT '商品类别名称',
  `caparent` varchar(20) DEFAULT NULL COMMENT '父类别id',
  `casub` varchar(20) DEFAULT NULL COMMENT '子类别id',
  PRIMARY KEY (`caid`),
  KEY `caid` (`caid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`caid`,`caname`,`caparent`,`casub`) values (1,'饮料',NULL,NULL),(2,'食品',NULL,NULL),(3,'图书',NULL,NULL),(4,'手机','1','2'),(5,'电脑','1','1'),(6,'电子产品','1','1,2,3,4');

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `cid` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品Id',
  `caid` int(10) NOT NULL COMMENT '商品类别',
  `dicout` decimal(4,2) DEFAULT NULL COMMENT '折扣',
  `cname` varchar(20) NOT NULL COMMENT '商品名称',
  `cprice` decimal(10,2) NOT NULL COMMENT '商品单价',
  `inStock` varchar(20) NOT NULL COMMENT '商品库存',
  `place` varchar(30) DEFAULT NULL COMMENT '商品产地',
  `productImg` varchar(10000) DEFAULT NULL COMMENT '商品图片',
  `productDes` varchar(500) DEFAULT NULL COMMENT '商品简介',
  PRIMARY KEY (`cid`),
  KEY `caid` (`caid`),
  CONSTRAINT `commodity_ibfk_1` FOREIGN KEY (`caid`) REFERENCES `category` (`caid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `commodity` */

insert  into `commodity`(`cid`,`caid`,`dicout`,`cname`,`cprice`,`inStock`,`place`,`productImg`,`productDes`) values (4,1,NULL,'可口可乐',3.00,'800','河南·郑州','假装这里有图片','全世界好多人都在喝'),(5,2,NULL,'牛排',258.00,'90','澳大利亚','这是一块牛肉','新鲜的，好吃的'),(6,3,NULL,'牛奶可乐经济学',45.25,'62','美国','黄色书皮的书','一本通俗经济学，帮你走进新世界'),(7,4,NULL,'HUAWEI Mate30Pro',5888.00,'894','广东·深圳','板砖厚的手机','反正就是超级NB的手机，徕卡垃圾！华为NB'),(8,5,NULL,'DELL XPS15',12880.00,'521','广东·深圳','很薄的笔记本电脑','雷电三接口，长续航，PD充电');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `oid` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int(10) DEFAULT NULL COMMENT '用户id',
  `otime` datetime DEFAULT NULL COMMENT '订单下单时间',
  `ostatus` varchar(20) DEFAULT NULL COMMENT '订单状态',
  `oprice` decimal(10,2) DEFAULT NULL COMMENT '订单总价',
  PRIMARY KEY (`oid`),
  KEY `oid` (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `orderdetails` */

DROP TABLE IF EXISTS `orderdetails`;

CREATE TABLE `orderdetails` (
  `odid` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
  `oid` int(10) DEFAULT NULL COMMENT '订单id',
  `cid` int(10) DEFAULT NULL COMMENT '商品id',
  `sid` int(10) DEFAULT NULL COMMENT '卖家id',
  PRIMARY KEY (`odid`),
  KEY `oid` (`oid`),
  KEY `cid` (`cid`),
  KEY `sid` (`sid`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `commodity` (`cid`),
  CONSTRAINT `orderdetails_ibfk_3` FOREIGN KEY (`sid`) REFERENCES `seller` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderdetails` */

/*Table structure for table `receiver` */

DROP TABLE IF EXISTS `receiver`;

CREATE TABLE `receiver` (
  `rid` int(10) NOT NULL AUTO_INCREMENT COMMENT '收货人id',
  `uid` int(10) NOT NULL COMMENT '用户id',
  `rname` varchar(20) NOT NULL COMMENT '收货人姓名',
  `rtel` varchar(11) NOT NULL COMMENT '收货人电话',
  `address` varchar(20) NOT NULL COMMENT '地址',
  PRIMARY KEY (`rid`),
  KEY `uid` (`uid`),
  CONSTRAINT `receiver_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `receiver` */

/*Table structure for table `seller` */

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `sid` int(10) NOT NULL AUTO_INCREMENT COMMENT '卖家id',
  `sacc` varchar(20) NOT NULL COMMENT '卖家账号',
  `spwd` varchar(20) NOT NULL COMMENT '卖家密码',
  `stime` datetime DEFAULT NULL COMMENT '创建时间',
  `shopName` varchar(20) DEFAULT NULL COMMENT '店铺名称',
  `mainBusiness` varchar(20) DEFAULT NULL COMMENT '主营业务',
  `stel` varchar(11) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `seller` */

insert  into `seller`(`sid`,`sacc`,`spwd`,`stime`,`shopName`,`mainBusiness`,`stel`) values (1,'12','12',NULL,'12','12','12'),(2,'23','23',NULL,'23','23','23'),(3,'amao','111','2019-11-22 09:23:00','穿拖鞋的猫爷','装修科普','46636215');

/*Table structure for table `shoppingcart` */

DROP TABLE IF EXISTS `shoppingcart`;

CREATE TABLE `shoppingcart` (
  `shid` int(10) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `uid` int(10) NOT NULL COMMENT '用户id',
  `shtime` datetime DEFAULT NULL COMMENT '创建时间',
  `shstatus` varchar(20) DEFAULT NULL COMMENT '购物车状态',
  PRIMARY KEY (`shid`),
  KEY `shid` (`shid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `shoppingcart` */

insert  into `shoppingcart`(`shid`,`uid`,`shtime`,`shstatus`) values (1,1,'2019-11-25 15:21:46',NULL);

/*Table structure for table `shoppingcartdetails` */

DROP TABLE IF EXISTS `shoppingcartdetails`;

CREATE TABLE `shoppingcartdetails` (
  `shacaid` int(10) NOT NULL AUTO_INCREMENT COMMENT '详情表id',
  `shid` int(10) NOT NULL COMMENT '购物车id',
  `cid` int(10) NOT NULL COMMENT '商品Id',
  `Num` int(20) NOT NULL COMMENT '商品数量',
  `totalprice` decimal(50,2) NOT NULL COMMENT '商品总价',
  PRIMARY KEY (`shacaid`),
  KEY `shacaid` (`shacaid`),
  KEY `shid` (`shid`),
  KEY `cid` (`cid`),
  CONSTRAINT `shoppingcartdetails_ibfk_1` FOREIGN KEY (`shid`) REFERENCES `shoppingcart` (`shid`),
  CONSTRAINT `shoppingcartdetails_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `commodity` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shoppingcartdetails` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uname` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `uacc` varchar(20) NOT NULL COMMENT '用户账号',
  `upwd` varchar(20) NOT NULL COMMENT '用户密码',
  `utel` varchar(11) DEFAULT NULL COMMENT '用户联系电话',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`uname`,`uacc`,`upwd`,`utel`) values (1,'王得发','wdf','235','2906437573'),(2,'王忘杰','wwj','982','3300908602'),(3,'阿驴','al','211','23565489'),(4,'熊猫学长','303','303','300254'),(81,'来福（福总）','429','429','492790286');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
