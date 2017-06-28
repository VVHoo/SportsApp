/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : sportapp

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-06-27 23:51:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `articleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `title` varchar(100) NOT NULL,
  `author` varchar(400) NOT NULL,
  `imgPath` varchar(400) NOT NULL,
  `category` varchar(400) NOT NULL,
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2015 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('2001', '2017-04-10 18:07:20', '共享单车，自在又甩肉', '春雨医生', 'http://192.168.43.132:8080/img/articleCover/1491877092230_750x340.jpg', '科普作者');
INSERT INTO `t_article` VALUES ('2002', '2017-03-26 11:08:02', '像换新衣一样吃早餐，一个月不重样', 'FitTime美小编', 'http://192.168.43.132:8080/img/articleCover/jksp_yyzc_0120.jpg', '营养饮食堂');
INSERT INTO `t_article` VALUES ('2003', '2017-03-15 09:12:14', '连00后都练出了腹肌！你每天仰卧起坐，还只有一块肚皮？', 'FitTime君', 'http://192.168.43.132:8080/img/articleCover/encourage_00gotsixpack_0327.jpg', '增肌技术流');
INSERT INTO `t_article` VALUES ('2004', '2017-03-22 09:12:32', '享瘦食谱：好身材吃出来，为夏天做准备', '娘娘SaAaaa花', 'http://192.168.43.132:8080/img/articleCover/szjxr_1208.jpg', '营养饮食堂');
INSERT INTO `t_article` VALUES ('2005', '2017-03-22 09:12:50', '90%健身小白都会错过的重要训练！', 'FitTime美小编', 'http://192.168.43.132:8080/img/articleCover/0EDe0c6MdG.jpg', '技术分享');
INSERT INTO `t_article` VALUES ('2006', '2017-03-23 09:13:04', '深蹲，让男人更强劲有力，让女人更性感多姿！', '我没有七尺高', 'http://192.168.43.132:8080/img/articleCover/jq_sdrnrgnr_0.jpg', '增肌技术流');
INSERT INTO `t_article` VALUES ('2007', '2017-04-18 14:22:42', '晚餐怎么吃才能心安理得？', '@丁香医生', 'http://192.168.43.132:8080/img/articleCover/1491013950545_750x340.jpg', '科普作者');
INSERT INTO `t_article` VALUES ('2008', '2017-04-19 14:23:26', '运动后膝痛别光静养，这份自救指南请私藏', '@AT运动物理治疗中心', 'http://192.168.43.132:8080/img/articleCover/1490844293732_750x340.jpg', '技术分享');
INSERT INTO `t_article` VALUES ('2009', '2017-04-01 15:39:05', '造成你胖的原因，无非就这4点！', 'FitTime美小编', 'http://192.168.43.132:8080/img/articleCover/wd_fpd4gys_0.jpg', '减脂方法论');
INSERT INTO `t_article` VALUES ('2010', '2017-04-01 10:13:40', '减脂也要因人而异“对症下药”', '我没有七尺高', 'http://192.168.43.132:8080/img/articleCover/PersonalTrainer.jpg', '减脂方法论');
INSERT INTO `t_article` VALUES ('2011', '2017-04-01 10:14:04', 'fsf', 'sfsff', 'sfsdfs', '减脂方法论');
INSERT INTO `t_article` VALUES ('2012', '2017-04-01 10:14:12', '减脂一日食谱：减脂餐也可以很豪华', '娘娘SaAaaa花', 'http://192.168.43.132:8080/img/articleCover/yrsp_ygryyhhcf_1230_1.jpg', '减脂方法论');
INSERT INTO `t_article` VALUES ('2013', '2017-03-16 23:37:03', '为什么减脂和增肌很难同时进行？', 'MikeLingFitness_凌云健身', 'http://192.168.43.132:8080/img/articleCover/wd_wsmjzhzjhntsjx_0.jpg', '减脂方法论');
INSERT INTO `t_article` VALUES ('2014', '2017-03-08 23:39:08', '减脂塑形期，如何控制自己的食欲', '周瑾', 'http://192.168.43.132:8080/img/articleCover/ys_rhkzsy_00.jpg', '减脂方法论');

-- ----------------------------
-- Table structure for t_articlecontent
-- ----------------------------
DROP TABLE IF EXISTS `t_articlecontent`;
CREATE TABLE `t_articlecontent` (
  `articleId` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `articleContent` varchar(4000) NOT NULL,
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_articlecontent
-- ----------------------------
INSERT INTO `t_articlecontent` VALUES ('2007', '2017-04-05 11:53:36', '<h3>晚餐怎么吃才能心安理得？</h3><span>作者:@丁香医生 范志红</span><p>以前，人生的三大难题只是：早上有什么吃？中午有什么吃？晚上有什么吃？ \r\n</p><p>后来，温饱问题基本解决，且随着大家生活节奏的加快，形成了新的一日三餐格局：早餐马虎，午餐应付，晚餐</p><p>跑跳痛」、「膝前痛」是运动者膝部最常见的症状之一，这些你都中招了吗?</p><img src=\"http://172.18.61.14:8080/img/articleContent/56c3a5f2f7800000.png\">');
INSERT INTO `t_articlecontent` VALUES ('2008', '2017-03-26 11:08:02', '<h3>运动后膝痛别光静养，这份自救指南请私藏</h3><span>作者:@AT运动物理治疗中心</span><p>「想要运动变得健康，没想到却换来了膝盖痛。」 </p><p>相信经常参加球类运动、骑行、跑步等项目的你，难免会有这样的抱怨。</p><p>跑跳痛」、「膝前痛」是运动者膝部最常见的症状之一，这些你都中招了吗?</p><img src=\"http://172.18.61.14:8080/img/articleContent/56c3a5f2f7800000.png\">');
INSERT INTO `t_articlecontent` VALUES ('2009', '2017-04-01 15:39:05', '<h3>造成你胖的原因，无非就这4点！</h3><img src=\"http://172.18.61.14:8080/img/articleCover/wd_fpd4gys_0.jpg\"><span>作者:@AT运动物理治疗中心</span><p>「想要运动变得健康，没想到却换来了膝盖痛。」 </p><p>相信经常参加球类运动、骑行、跑步等项目的你，难免会有这样的抱怨。</p><p>跑跳痛」、「膝前痛」是运动者膝部最常见的症状之一，这些你都中招了吗?</p>');
INSERT INTO `t_articlecontent` VALUES ('2012', '2017-04-01 10:14:12', '<h3>减脂一日食谱：减脂餐也可以很豪华</h3><img src=\"http://172.18.61.14:8080/img/articleCover/yrsp_ygryyhhcf_1230_1.jpg\"><span>作者:@AT运动物理治疗中心</span><p>「想要运动变得健康，没想到却换来了膝盖痛。」 </p><p>相信经常参加球类运动、骑行、跑步等项目的你，难免会有这样的抱怨。</p><p>跑跳痛」、「膝前痛」是运动者膝部最常见的症状之一，这些你都中招了吗?</p>');

-- ----------------------------
-- Table structure for t_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection` (
  `collectId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(20) NOT NULL,
  `videoId` bigint(20) NOT NULL,
  PRIMARY KEY (`collectId`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_collection
-- ----------------------------
INSERT INTO `t_collection` VALUES ('3', '2', '1001');
INSERT INTO `t_collection` VALUES ('154', '1', '1001');

-- ----------------------------
-- Table structure for t_messages
-- ----------------------------
DROP TABLE IF EXISTS `t_messages`;
CREATE TABLE `t_messages` (
  `messageId` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `articleId` bigint(20) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `sendTime` datetime NOT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_messages
-- ----------------------------
INSERT INTO `t_messages` VALUES ('11', '1', '2008', 'gagagaga', '2017-03-16 14:43:12');
INSERT INTO `t_messages` VALUES ('12', '1', '2008', 'hahaha', '2017-03-20 16:01:14');
INSERT INTO `t_messages` VALUES ('13', '1', '2008', 'hahaha', '2017-03-21 11:20:22');
INSERT INTO `t_messages` VALUES ('14', '1', '2008', 'xxxxxxx', '2017-04-24 10:41:13');
INSERT INTO `t_messages` VALUES ('15', '1', '2008', 'jjjjjjj', '2017-04-23 10:41:25');
INSERT INTO `t_messages` VALUES ('16', '1', '2008', 'qqqqq', '2017-04-22 10:41:37');
INSERT INTO `t_messages` VALUES ('17', '1', '2008', 'tttttt', '2017-04-25 10:41:51');
INSERT INTO `t_messages` VALUES ('18', '1', '2008', 'ooooo', '2017-04-26 10:42:02');
INSERT INTO `t_messages` VALUES ('19', '1', '2008', '哈哈哈', '2017-04-25 15:43:35');
INSERT INTO `t_messages` VALUES ('20', '1', '2008', '哈哈哈哈哈哈', '2017-04-25 15:46:28');
INSERT INTO `t_messages` VALUES ('21', '2', '2008', 'fas', '2017-05-06 15:14:54');
INSERT INTO `t_messages` VALUES ('22', '1', '2008', 'hskajsn', '2017-05-15 13:07:02');
INSERT INTO `t_messages` VALUES ('32', '1', '2008', '发送', '2017-05-19 22:31:35');
INSERT INTO `t_messages` VALUES ('33', '1', '2008', '发送', '2017-05-19 22:34:17');
INSERT INTO `t_messages` VALUES ('34', '1', '2008', 'fdsa ', '2017-05-19 23:13:21');

-- ----------------------------
-- Table structure for t_subvideo
-- ----------------------------
DROP TABLE IF EXISTS `t_subvideo`;
CREATE TABLE `t_subvideo` (
  `subVideoId` bigint(11) NOT NULL,
  `videoId` bigint(20) NOT NULL,
  `videoPath` varchar(400) NOT NULL,
  `coverImg` varchar(400) NOT NULL,
  `subVideoTitle` varchar(500) NOT NULL,
  `videoContent` varchar(700) NOT NULL,
  PRIMARY KEY (`subVideoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subvideo
-- ----------------------------
INSERT INTO `t_subvideo` VALUES ('100101', '1001', 'http://192.168.43.132:8080/video/56b2e6ab3ac00000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1489567382282_750x700.jpg', '股后肌群动态拉伸', '<h3>步骤：</h3><ul><li>准备姿势：自然站立，一腿迈向前方大概一个脚掌的距离，身体正直，挺胸收腹</li><li>拉伸：后腿微屈向下坐，前腿伸直，身体正直状态向前趴，感受前腿大腿后侧牵拉，左右交替进行</li>\r\n</ul>');
INSERT INTO `t_subvideo` VALUES ('100102', '1001', 'http://192.168.43.132:8080/video/ATM0048_Vertical_Sharpen.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1489567382282_750x700.jpg', '臀部肌群动态拉伸', 'fa');
INSERT INTO `t_subvideo` VALUES ('100103', '1001', 'http://192.168.43.132:8080/video/ATF0019_Vertical_Sharpen.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1489567382282_750x700.jpg', '左腿侧卧外展', 'fdafa');
INSERT INTO `t_subvideo` VALUES ('100301', '1003', 'http://192.168.43.132:8080/video/5513218dc3c00000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478594247518_750x700.jpg', '平板支撑', 'as');
INSERT INTO `t_subvideo` VALUES ('100302', '1003', 'http://192.168.43.132:8080/video/be26b438cb7b2e7cf074935611c55dfebbba5bc1.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478594247518_750x700.jpg', '箭步蹲转体', 'as');
INSERT INTO `t_subvideo` VALUES ('100303', '1003', 'http://192.168.43.132:8080/video/55131d3930c00000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478594247518_750x700.jpg', '支撑抬臀', '发送');
INSERT INTO `t_subvideo` VALUES ('100304', '1003', 'http://192.168.43.132:8080/video/38f2021c02e9ee0ce64c6f477be120bc9c3479d5.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478594247518_750x700.jpg', '开合跳', '开合跳');
INSERT INTO `t_subvideo` VALUES ('100401', '1004', 'http://192.168.43.132:8080/video/540b3314bd400000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478592225696_750x700.jpg', '上斜俯卧撑', '上斜俯卧撑');
INSERT INTO `t_subvideo` VALUES ('100402', '1004', 'http://192.168.43.132:8080/video/G00107.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478592225696_750x700.jpg', '半蹲', '安顿');
INSERT INTO `t_subvideo` VALUES ('100501', '1005', 'http://192.168.43.132:8080/video/f0273bc44bfb90b3e666e5e81cf1bab4d113627f.mp4', 'http://192.168.43.132:8080/img/classifyTrain/542129df26400000.jpg', '肩部环绕', '发送');
INSERT INTO `t_subvideo` VALUES ('100502', '1005', 'http://192.168.43.132:8080/video/cdf165467ccfcfef13538c174d04e2d6ebdb9400.mp4', 'http://192.168.43.132:8080/img/classifyTrain/542129df26400000.jpg', '左右跳', '发是');
INSERT INTO `t_subvideo` VALUES ('100503', '1005', 'http://192.168.43.132:8080/video/551322ac82000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/542129df26400000.jpg', ' 腹肌激活', '发送');
INSERT INTO `t_subvideo` VALUES ('100602', '1006', 'http://192.168.43.132:8080/video/Y1Cut0.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1488452238494_750x700.jpg', '金刚坐', '安抚');
INSERT INTO `t_subvideo` VALUES ('100701', '1007', 'http://192.168.43.132:8080/video/5513252b89400000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '单腿仰卧起坐', 'fdas');
INSERT INTO `t_subvideo` VALUES ('100702', '1007', 'http://192.168.43.132:8080/video/b3eb29642e266d1b9b32dd4f4240a2449b76332f.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '屈膝收腹', '分');
INSERT INTO `t_subvideo` VALUES ('100703', '1007', 'http://192.168.43.132:8080/video/551321156e000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '西西里卷腹', '分');
INSERT INTO `t_subvideo` VALUES ('100704', '1007', 'http://192.168.43.132:8080/video/2b8362d44d54e017e12cd016a6a97f4b39e24a25.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '倒蹬车', '范德萨');
INSERT INTO `t_subvideo` VALUES ('100705', '1007', 'http://192.168.43.132:8080/video/5513218dc3c00000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '平板支撑', '地方');
INSERT INTO `t_subvideo` VALUES ('100706', '1007', 'http://192.168.43.132:8080/video/55131fd2a2400000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '腹部拉伸', '腹部拉伸');
INSERT INTO `t_subvideo` VALUES ('100707', '1007', 'http://192.168.43.132:8080/video/d413483ef0ce6fea6e86d969359166a805d38a96.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '慢速两头起', '的');
INSERT INTO `t_subvideo` VALUES ('100708', '1007', 'http://192.168.43.132:8080/video/55132616ddc00000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '仰卧举腿', '分');
INSERT INTO `t_subvideo` VALUES ('100801', '1008', 'http://192.168.43.132:8080/video/53ceb5d88b000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591243022_750x700.jpg', ' 90°卷腹', '发送');
INSERT INTO `t_subvideo` VALUES ('100901', '1009', 'http://192.168.43.132:8080/video/R0042.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588129599_750x700.jpg', '左腿后侧拉伸', '大');
INSERT INTO `t_subvideo` VALUES ('100902', '1009', 'http://192.168.43.132:8080/video/R0031.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478588129599_750x700.jpg', '右腿后侧拉伸', '大');
INSERT INTO `t_subvideo` VALUES ('101001', '1010', 'http://192.168.43.132:8080/video/H00130.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478590731733_750x700.jpg', '辅助卷腹', '发斯蒂芬');
INSERT INTO `t_subvideo` VALUES ('101101', '1011', 'http://192.168.43.132:8080/video/55131db15c400000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '猫式伸展', '发顺丰');
INSERT INTO `t_subvideo` VALUES ('101102', '1011', 'http://192.168.43.132:8080/video/55131d605b800000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '弓步转体', '安抚');
INSERT INTO `t_subvideo` VALUES ('101103', '1011', 'http://192.168.43.132:8080/video/G00048.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '俯卧挺身', '大立科技');
INSERT INTO `t_subvideo` VALUES ('101104', '1011', 'http://192.168.43.132:8080/video/G00050.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '俯卧Y字伸展', '发送');
INSERT INTO `t_subvideo` VALUES ('101105', '1011', 'http://192.168.43.132:8080/video/55132a9380c00000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '十字挺身', '发送');
INSERT INTO `t_subvideo` VALUES ('101106', '1011', 'http://192.168.43.132:8080/video/55131ff8f2000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '背部拉伸', '发');
INSERT INTO `t_subvideo` VALUES ('101107', '1011', 'http://192.168.43.132:8080/video/5448fdc092000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '俯卧挺身转体', '发送');
INSERT INTO `t_subvideo` VALUES ('101108', '1011', 'http://192.168.43.132:8080/video/G00044.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '毛巾俯身划船', '范德萨');
INSERT INTO `t_subvideo` VALUES ('101201', '1012', 'http://192.168.43.132:8080/video/55132bc319800000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591073270_750x700.jpg', '侧卧左侧提膝', '俯');
INSERT INTO `t_subvideo` VALUES ('101202', '1012', 'http://192.168.43.132:8080/video/55132b604b000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591073270_750x700.jpg', '侧卧左侧前踢腿', '俯');
INSERT INTO `t_subvideo` VALUES ('101203', '1012', 'http://192.168.43.132:8080/video/55132b1d2ec00000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591073270_750x700.jpg', '侧卧左侧抬腿', '发顺丰');
INSERT INTO `t_subvideo` VALUES ('101204', '1012', 'http://192.168.43.132:8080/video/55131b161e400000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591073270_750x700.jpg', '俯卧左侧后踢腿', '发送');
INSERT INTO `t_subvideo` VALUES ('101205', '1012', 'http://192.168.43.132:8080/video/5435c7315c000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478591073270_750x700.jpg', '左腿翘曲卷腹', '发送');
INSERT INTO `t_subvideo` VALUES ('101301', '1013', 'http://192.168.43.132:8080/video/546f91a7c6400000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478590907071_750x700.jpg', '快走', '快走');
INSERT INTO `t_subvideo` VALUES ('101302', '1013', 'http://192.168.43.132:8080/video/5470cdf8d4000000.mp4', 'http://192.168.43.132:8080/img/classifyTrain/1478590907071_750x700.jpg', '慢跑', '安排');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `avatarUrl` varchar(400) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(400) NOT NULL,
  `hometown` varchar(400) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(100) DEFAULT NULL,
  `admitSign` tinyint(11) NOT NULL,
  `signNum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'aaa', '123456', 'http://192.168.43.132:8080/img/avatar/userAvatar1timg.jpg', '0', '123456@email.com', 'China', '2017-05-04', '男', '1', '16');
INSERT INTO `t_user` VALUES ('2', 'bbb', '12345', 'http://192.168.43.132:8080/img/avatar/userAvatar24.jpg', null, '', null, null, null, '1', '0');

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `videoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `coverPath` varchar(500) NOT NULL,
  `videoTitle` varchar(500) NOT NULL,
  `videoType` varchar(500) NOT NULL,
  `typeCover` varchar(500) NOT NULL,
  `videoTag` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`videoId`)
) ENGINE=InnoDB AUTO_INCREMENT=1018 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES ('1001', 'http://192.168.43.132:8080/img/classifyTrain/1489567382282_750x700.jpg', '膝前侧疼痛缓解阶段1', '运动损伤预防及康复', 'http://192.168.43.132:8080/img/classifyTrain/1479785763576_345x345.jpg', '腿部、臀部、腹部');
INSERT INTO `t_video` VALUES ('1002', 'http://192.168.43.132:8080/img/classifyTrain/1489568012017_750x700.jpg', '膝前侧疼痛缓解阶段2', '运动损伤预防及康复', 'http://192.168.43.132:8080/img/classifyTrain/1479785763576_345x345.jpg', '腿部、臀部、腹部');
INSERT INTO `t_video` VALUES ('1003', 'http://192.168.43.132:8080/img/classifyTrain/1478594247518_750x700.jpg', '2分钟碎片减脂练习', '零基础减脂', 'http://192.168.43.132:8080/img/classifyTrain/1489760891017_750x700.jpg', '腿部、手部、腹部');
INSERT INTO `t_video` VALUES ('1004', 'http://192.168.43.132:8080/img/classifyTrain/1478592225696_750x700.jpg', 'HIIT唤醒', '零基础减脂', 'http://192.168.43.132:8080/img/classifyTrain/1489760891017_750x700.jpg', '全身');
INSERT INTO `t_video` VALUES ('1005', 'http://192.168.43.132:8080/img/classifyTrain/542129df26400000.jpg', 'HIIT适应性训练', '新手入门', 'http://192.168.43.132:8080/img/classifyTrain/547dc433f9400000.jpg', '全身');
INSERT INTO `t_video` VALUES ('1006', 'http://192.168.43.132:8080/img/classifyTrain/1478590731733_750x700.jpg', '腹肌训练入门', '新手入门', 'http://192.168.43.132:8080/img/classifyTrain/547dc433f9400000.jpg', '腹部');
INSERT INTO `t_video` VALUES ('1007', 'http://192.168.43.132:8080/img/classifyTrain/1478588565783_750x700.jpg', '马甲线养成', '女生热门', 'http://192.168.43.132:8080/img/classifyTrain/547dc45b9a000000.jpg', '腹部');
INSERT INTO `t_video` VALUES ('1008', 'http://192.168.43.132:8080/img/classifyTrain/1478591243022_750x700.jpg', '腹肌撕裂者进阶', '男生热门', 'http://192.168.43.132:8080/img/classifyTrain/547dc4696d000000.jpg', '腹部');
INSERT INTO `t_video` VALUES ('1009', 'http://192.168.43.132:8080/img/classifyTrain/1478588129599_750x700.jpg', '跑后拉伸', '跑步能力提升', 'http://192.168.43.132:8080/img/classifyTrain/547dc440c9800000.jpg', '全身');
INSERT INTO `t_video` VALUES ('1010', 'http://192.168.43.132:8080/img/classifyTrain/1488452238494_750x700.jpg', '瑜伽关节舒展', '瑜伽', 'http://192.168.43.132:8080/img/classifyTrain/554a893540400000.jpg', '全身');
INSERT INTO `t_video` VALUES ('1011', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '背部塑形', '女生热门', 'http://192.168.43.132:8080/img/classifyTrain/1478591416051_750x700.jpg', '背部');
INSERT INTO `t_video` VALUES ('1012', 'http://192.168.43.132:8080/img/classifyTrain/1478591073270_750x700.jpg', '瘦腿训练', '女生热门', 'http://192.168.43.132:8080/img/classifyTrain/1478591073270_750x700.jpg', '腿部、腹部、臀部');
INSERT INTO `t_video` VALUES ('1013', 'http://192.168.43.132:8080/img/classifyTrain/1478590907071_750x700.jpg', '跑步机HIIT变速跑', '跑步能力提升', 'http://192.168.43.132:8080/img/classifyTrain/547dc440c9800000.jpg', '腿部');
INSERT INTO `t_video` VALUES ('1014', ' http://192.168.43.132:8080/img/classifyTrain/1478590907071_750x700.jpg', '缓解腰背疼痛', '运动损伤预防及康复', 'http://192.168.43.132:8080/img/classifyTrain/1479785763576_345x345.jpg', '背部');
INSERT INTO `t_video` VALUES ('1015', 'http://192.168.43.132:8080/img/classifyTrain/1489567382282_750x700.jpg', '膝关节自我康复阶段1', '运动损伤预防及康复', 'http://192.168.43.132:8080/img/classifyTrain/1479785763576_345x345.jpg', null);
INSERT INTO `t_video` VALUES ('1016', 'http://192.168.43.132:8080/img/classifyTrain/1489567382282_750x700.jpg', '膝关节自我康复阶段2', '运动损伤预防及康复', 'http://192.168.43.132:8080/img/classifyTrain/1479785763576_345x345.jpg', null);
INSERT INTO `t_video` VALUES ('1017', 'http://192.168.43.132:8080/img/classifyTrain/1489567382282_750x700.jpg', '跑步膝预防', '运动损伤预防及康复', 'http://192.168.43.132:8080/img/classifyTrain/1479785763576_345x345.jpg', null);

-- ----------------------------
-- Event structure for event_update
-- ----------------------------
DROP EVENT IF EXISTS `event_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `event_update` ON SCHEDULE EVERY 1 DAY STARTS '2017-03-25 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
UPDATE t_user SET admitSign = 1;
END
;;
DELIMITER ;
