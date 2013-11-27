/*
Navicat MySQL Data Transfer

Source Server         : liujiawinds
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2013-11-23 15:02:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(2) NOT NULL auto_increment,
  `password` varchar(30) NOT NULL,
  `name` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `last_login_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'liujia', 'liujia', 'normal', '2013-11-23 14:30:21');
INSERT INTO `admin` VALUES ('2', 'liujiawinds', 'liujiawinds', 'super', null);
INSERT INTO `admin` VALUES ('3', 'asdf', 'asdf', 'normal', null);

-- ----------------------------
-- Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(5) NOT NULL auto_increment,
  `user_id` int(5) NOT NULL,
  `product_id` int(5) NOT NULL,
  `quantity` int(5) default NULL,
  `isLike` int(1) unsigned zerofill default '0',
  `oid` int(10) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  KEY `fk_oid` (`oid`),
  CONSTRAINT `fk_oid` FOREIGN KEY (`oid`) REFERENCES `t_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pid` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_uid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('8', '23', '2', '0', '1', null);
INSERT INTO `cart` VALUES ('11', '37', '2', '0', '1', null);
INSERT INTO `cart` VALUES ('29', '41', '1', '1', '0', '29');
INSERT INTO `cart` VALUES ('30', '41', '3', '1', '0', '30');
INSERT INTO `cart` VALUES ('31', '41', '1', '1', '0', '31');
INSERT INTO `cart` VALUES ('32', '41', '2', '1', '0', '34');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(3) NOT NULL auto_increment,
  `name` varchar(30) NOT NULL,
  `parent_id` int(3) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '服装', null);
INSERT INTO `category` VALUES ('2', '包袋', null);
INSERT INTO `category` VALUES ('3', '鞋子', null);
INSERT INTO `category` VALUES ('4', '配饰', null);
INSERT INTO `category` VALUES ('5', '上衣', '1');
INSERT INTO `category` VALUES ('6', '裤子', '1');
INSERT INTO `category` VALUES ('7', '裙子', '1');
INSERT INTO `category` VALUES ('8', '沙滩装', '1');
INSERT INTO `category` VALUES ('9', '单肩包', '2');
INSERT INTO `category` VALUES ('10', '手拿包', '2');
INSERT INTO `category` VALUES ('11', '旅行箱包', '2');
INSERT INTO `category` VALUES ('12', '高跟鞋', '3');
INSERT INTO `category` VALUES ('13', '平底鞋', '3');
INSERT INTO `category` VALUES ('14', '靴子', '3');
INSERT INTO `category` VALUES ('15', '凉鞋', '3');
INSERT INTO `category` VALUES ('16', '运动鞋', '3');
INSERT INTO `category` VALUES ('17', '手套', '4');
INSERT INTO `category` VALUES ('18', '帽子', '4');
INSERT INTO `category` VALUES ('19', '太阳镜', '4');
INSERT INTO `category` VALUES ('20', '手表', '4');

-- ----------------------------
-- Table structure for `privilege`
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `role` varchar(20) NOT NULL,
  `operation` varchar(100) default NULL,
  PRIMARY KEY  (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('admin', 'add_product,delete_product,modify_product,find_product,');
INSERT INTO `privilege` VALUES ('superadmin', 'add_admin,add_product,add_category');
INSERT INTO `privilege` VALUES ('user', 'addIntoCart');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(6) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `category_id` int(3) default NULL,
  `price` decimal(8,2) NOT NULL,
  `description` varchar(500) default NULL,
  `designer` varchar(60) default NULL,
  `add_time` datetime default NULL,
  `amount` int(11) default '0',
  PRIMARY KEY  (`id`),
  KEY `FK_MERCHANTABLE_TYPE_ID` (`category_id`),
  CONSTRAINT `FK_MERCHANTABLE_TYPE_ID` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '扎染纯棉针织上衣', '5', '301.00', 'Proenza Schouler 这款亮丽的青柠色上衣采用奇特的扎染手法演绎了都市炫酷风情。这款纯棉基本款上衣采用超薄针织面料裁制而成，非常适合层搭穿着。建议用它搭配摩托夹克和荧光半身裙，轻松打造出高视觉冲击力的装束。', 'PROENZA SCHOULER', '2013-05-11 00:00:00', '50');
INSERT INTO `product` VALUES ('2', '渐变色条纹棉布毛衣', '5', '706.00', 'Proenza Schouler 以夺人眼球的设计而闻名，它醒目张扬的必备款毛衣绝对是我们的不二选择。渐变色条纹巧妙地融入黄色、灰色和橙色，营造出时尚的对比效果，深得我们的喜爱。建议用这款针织棉毛衣搭配白色长裤和抛光高跟鞋。 ', 'PROENZA SCHOULER', '2013-05-11 00:00:00', '50');
INSERT INTO `product` VALUES ('3', 'Palmyto 印花水洗真丝锥形裤', '6', '412.00', 'Paul & Joe 的水洗真丝中腰锥形长裤采用凸显身材曲线的利落剪裁，让你轻松玩转风情印花。 这款轻薄的长裤采用暖色调橙色设计，绝对是你开展城市之旅的完美之选。', 'PAUL & JOE', '2013-05-11 00:00:00', '50');
INSERT INTO `product` VALUES ('9', '绿色的衣服', '5', '12.00', '法师打发', 'asdfa', '2013-11-23 15:00:12', '123');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(5) NOT NULL auto_increment,
  `order_time` datetime NOT NULL,
  `status` varchar(20) default NULL,
  `payment` varchar(50) default NULL,
  `remark` varchar(500) default NULL,
  `send_time` datetime default NULL,
  `address` varchar(50) NOT NULL,
  `total_price` decimal(8,2) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('29', '2013-11-14 16:24:56', '待发货', '货到付款', null, null, '成都市武侯区龙腾东路中海大厦5楼', '301.00', '41');
INSERT INTO `t_order` VALUES ('30', '2013-11-14 16:27:11', '待发货', '货到付款', null, null, '成都市武侯区龙腾东路中海大厦5楼', '412.00', '41');
INSERT INTO `t_order` VALUES ('31', '2013-11-14 16:29:42', '待发货', '货到付款', null, null, '成都市武侯区龙腾东路中海大厦5楼', '301.00', '41');
INSERT INTO `t_order` VALUES ('34', '2013-11-14 16:32:02', '待发货', '货到付款', null, null, '成都市武侯区龙腾东路中海大厦5楼', '706.00', '41');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(5) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `real_name` varchar(20) default NULL,
  `telephone` varchar(15) default NULL,
  `address` varchar(50) default NULL,
  `regist_date` datetime default NULL,
  `postcode` varchar(10) default NULL,
  `email` varchar(50) default NULL,
  `balance` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('23', '拉风的超人', '1642f9c0fae43b68', '刘佳', '15283773646', '四川大学锦江学院', '2013-02-20 17:53:39', '629100', '527609945@qq.com', '0');
INSERT INTO `user` VALUES ('36', 'liujia', '34c3a4200643799d', '刘佳', '15283773646', '四川大学锦江学院', '2013-05-23 11:11:15', '629100', 'liujia@ncsi.com.cn', '1000');
INSERT INTO `user` VALUES ('37', '店主', '1642f9c0fae43b68', 'liujia', '15283773646', '是的佛啊好地方奥帆 ', '2013-05-25 13:39:48', '629100', 'liujiawinds@outlook.com', '0');
INSERT INTO `user` VALUES ('38', 'liujia', '34c3a4200643799d', '刘佳', '15283773646', '四川省彭山县锦江学院09计科2班', '2013-06-02 17:07:07', '629100', 'liujiawinds@126.com', '0');
INSERT INTO `user` VALUES ('39', 'liujia', '34c3a4200643799d', 'lijia', '15283773646', '地球', '2013-06-03 11:54:06', '629100', 'nice@qq.com', '0');
INSERT INTO `user` VALUES ('40', '超人', '1642f9c0fae43b68', '刘佳', '15283773646', '地球', '2013-06-03 15:09:57', '629100', 'nice1@qq.com', '0');
INSERT INTO `user` VALUES ('41', '哈哈', '1642f9c0fae43b68', '刘佳', '15283773646', '成都市武侯区龙腾东路中海大厦5楼', '2013-11-13 14:31:14', '610000', 'liujiawinds@gmail.com', '0');
