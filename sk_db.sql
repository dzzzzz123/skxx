/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : sk_db

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 23/09/2022 20:57:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `aId` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `aAccount` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员账号',
  `aPwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`aId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (1, 'admin1', '123456');
INSERT INTO `tb_admin` VALUES (2, 'admin2', '123456');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `cId` int(0) NOT NULL COMMENT '课程Id',
  `sId` int(0) NOT NULL COMMENT '学生Id',
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '学生评论',
  `star` tinyint(0) NULL DEFAULT NULL COMMENT '学生评分',
  INDEX `fk_c3`(`cId`) USING BTREE,
  INDEX `fk_s3`(`sId`) USING BTREE,
  CONSTRAINT `fk_c3` FOREIGN KEY (`cId`) REFERENCES `tb_course` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_s3` FOREIGN KEY (`sId`) REFERENCES `tb_student` (`sId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (1, 1, '老师很不错，讲课很认真！', 8);
INSERT INTO `tb_comment` VALUES (2, 1, '<p>老师很不错，经常带着我们跑步的撒大</p>', 6);
INSERT INTO `tb_comment` VALUES (3, 1, '<p>我们思想品德都非常的端正</p>', 9);

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course`  (
  `cId` int(0) NOT NULL AUTO_INCREMENT COMMENT '课程Id',
  `cName` varchar(22) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名字',
  `cStartDate` date NULL DEFAULT NULL COMMENT '课程开始日期',
  `cEndDate` date NULL DEFAULT NULL COMMENT '课程结束日期',
  `cCredit` tinyint(0) NULL DEFAULT 100 COMMENT '课程满分',
  `cRequire` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程要求',
  `tId` int(0) NULL DEFAULT NULL COMMENT '教授课程老师id\r\n',
  PRIMARY KEY (`cId`) USING BTREE,
  INDEX `fk_tid`(`tId`) USING BTREE,
  CONSTRAINT `fk_tid` FOREIGN KEY (`tId`) REFERENCES `tb_teacher` (`tId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_course
-- ----------------------------
INSERT INTO `tb_course` VALUES (1, '语文', '2022-09-04', '2022-09-15', 100, '无', 1);
INSERT INTO `tb_course` VALUES (2, '体育', '2022-09-01', '2022-09-04', 30, '有腿有手', 1);
INSERT INTO `tb_course` VALUES (3, '思想品德', '2010-05-01', '2010-06-09', 50, '思想品德端正', 1);
INSERT INTO `tb_course` VALUES (4, '数学', '2010-09-07', '2011-01-05', 120, '无', 2);
INSERT INTO `tb_course` VALUES (5, '奥数提高', '2012-06-07', '2013-01-06', 20, '数学能力强', 2);
INSERT INTO `tb_course` VALUES (6, '英语', '2008-06-07', '2015-07-09', 100, '无', 3);
INSERT INTO `tb_course` VALUES (7, '英语口语', '2015-07-09', '2016-09-05', 50, '会说话', 3);
INSERT INTO `tb_course` VALUES (8, '数学2', '2010-05-09', '2010-06-09', 30, '体验课，都能上', 7);
INSERT INTO `tb_course` VALUES (9, '体育', '2010-03-09', '2015-09-07', 50, '四肢健全', 7);

-- ----------------------------
-- Table structure for tb_cs
-- ----------------------------
DROP TABLE IF EXISTS `tb_cs`;
CREATE TABLE `tb_cs`  (
  `cId` int(0) NOT NULL COMMENT '课程Id',
  `sId` int(0) NOT NULL COMMENT '学生Id',
  INDEX `fk_c`(`cId`) USING BTREE,
  INDEX `fk_s`(`sId`) USING BTREE,
  CONSTRAINT `fk_c` FOREIGN KEY (`cId`) REFERENCES `tb_course` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_s` FOREIGN KEY (`sId`) REFERENCES `tb_student` (`sId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cs
-- ----------------------------
INSERT INTO `tb_cs` VALUES (1, 1);
INSERT INTO `tb_cs` VALUES (2, 1);
INSERT INTO `tb_cs` VALUES (3, 1);
INSERT INTO `tb_cs` VALUES (4, 1);
INSERT INTO `tb_cs` VALUES (1, 2);
INSERT INTO `tb_cs` VALUES (2, 2);
INSERT INTO `tb_cs` VALUES (5, 1);

-- ----------------------------
-- Table structure for tb_marvelous_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_marvelous_student`;
CREATE TABLE `tb_marvelous_student`  (
  `sId` int(0) NOT NULL COMMENT '校友Id',
  `sImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '校友图片',
  `sName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '校友名字',
  `sInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '校友信息',
  `contribution` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '校友捐献',
  `achievement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '校友成就',
  PRIMARY KEY (`sId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_marvelous_student
-- ----------------------------
INSERT INTO `tb_marvelous_student` VALUES (1, 'http://ria4omh4f.hn-bkt.clouddn.com/FspLDj_--XpBqINebeCaOkE-MOfW', '小崔', '小崔是一个人', NULL, NULL);
INSERT INTO `tb_marvelous_student` VALUES (4, 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', '党章', '端午期间工地开工皮卡', NULL, NULL);
INSERT INTO `tb_marvelous_student` VALUES (7, 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', '小王', '端午期间工地开工皮卡', NULL, NULL);

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news`  (
  `nId` int(0) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `nImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '新闻图片',
  `nTitle` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '新闻标题',
  `nContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '新闻内容',
  PRIMARY KEY (`nId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_news
-- ----------------------------
INSERT INTO `tb_news` VALUES (1, 'http://ria4omh4f.hn-bkt.clouddn.com/FvfD2s7SAHnYSHLUQCK7M-VWBJZE', '沙口小学一年级画展冠军', '一年一班的同学小崔所画的我的学校和我们，在校长和老师们的商议下，荣获本次一等奖，并最后夺得冠军奖项！');
INSERT INTO `tb_news` VALUES (2, 'http://ria4omh4f.hn-bkt.clouddn.com/Fgc-zOApDI5YOb-VQzDgzxbhCjUj', '沙口小学新教学楼建起', '从2005年开始投资300万的第十八教学楼，于2009年8月31号建成，计划立马投入使用，为师生创建更好的环境！');
INSERT INTO `tb_news` VALUES (4, 'http://ria4omh4f.hn-bkt.clouddn.com/FkXyXbs4nLzXcX6Cma9d6foaFL5G', '沙口小学国际校区渲染图', '沙口小学国际校区，经过了八年多的筹划，现开始正式建设，我们聘请了沙口设计院院长来领导我们完成设计工作，希望今后的建设工作能让国际小区顺利建成！');

-- ----------------------------
-- Table structure for tb_score
-- ----------------------------
DROP TABLE IF EXISTS `tb_score`;
CREATE TABLE `tb_score`  (
  `cId` int(0) NOT NULL COMMENT '课程Id',
  `sId` int(0) NOT NULL COMMENT '学生Id',
  `score` tinyint(0) NULL DEFAULT NULL COMMENT '学生成绩',
  INDEX `fk_c2`(`cId`) USING BTREE,
  INDEX `fk_s2`(`sId`) USING BTREE,
  CONSTRAINT `fk_c2` FOREIGN KEY (`cId`) REFERENCES `tb_course` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_s2` FOREIGN KEY (`sId`) REFERENCES `tb_student` (`sId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_score
-- ----------------------------

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `sId` int(0) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `sAccount` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生账户',
  `sPwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生密码',
  `sName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生姓名',
  `sBirth` date NULL DEFAULT NULL COMMENT '学生生日',
  `sInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '学生信息',
  `sAge` tinyint(0) NULL DEFAULT NULL COMMENT '学生姓名',
  `sSex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生性别',
  `sImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生照片',
  `isMarvelous` tinyint(0) NULL DEFAULT 0 COMMENT '学生是否是优秀校友',
  PRIMARY KEY (`sId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (1, '200901001', '123456', '小崔', '1998-12-23', '小崔是一个人', 24, '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FspLDj_--XpBqINebeCaOkE-MOfW', 1);
INSERT INTO `tb_student` VALUES (2, '200901002', '654321', '小果', '2022-09-22', '小果是一匹海象', 20, '女', 'http://ria4omh4f.hn-bkt.clouddn.com/FiQkAjHyyQBp9C6AhqkNN-2hkxhT', 1);
INSERT INTO `tb_student` VALUES (4, '200910003', '123456', '党章', '2001-01-01', '端午期间工地开工皮卡', 21, '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', 1);
INSERT INTO `tb_student` VALUES (5, '200910004', '123456', '小明', '2001-01-09', '端午期间工地开工皮卡', 21, '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', 0);
INSERT INTO `tb_student` VALUES (6, '200910005', '123456', '小牛', '2001-01-09', '端午期间工地开工皮卡', 21, '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', 0);
INSERT INTO `tb_student` VALUES (7, '200910006', '123456', '小王', '2001-01-09', '端午期间工地开工皮卡', 21, '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', 1);
INSERT INTO `tb_student` VALUES (8, '200910007', '123456', '晓晓', '2001-01-09', '端午期间工地开工皮卡', 21, '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', 1);
INSERT INTO `tb_student` VALUES (9, '200910008', '123456', '小朱', '2001-01-09', '端午期间工地开工皮卡', 21, '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FiTduDyUbHh6KdIIVaYEPehwzQ89', 0);

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `tId` int(0) NOT NULL AUTO_INCREMENT COMMENT '教师id',
  `tAccount` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师账号',
  `tPwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师密码',
  `tName` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师姓名',
  `tBirth` date NULL DEFAULT NULL COMMENT '教师生日',
  `tInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '教师信息',
  `tSex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师性别',
  `tImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师图片',
  `tPhone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师手机号',
  `tBankAccount` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师工资卡号',
  `tSalary` decimal(10, 2) NULL DEFAULT NULL COMMENT '教师工资',
  `tTitle` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师头衔',
  PRIMARY KEY (`tId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (1, '10000001', '123456', '张三', NULL, NULL, '男', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_teacher` VALUES (2, '10000002', '123456', '李四', '2022-09-16', '数学老师', '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FtWIavuK9vk0dgxavodB8C0SsL96', '16579823246', '13245656688745631', 5200.00, '沙口骨干特级教师');
INSERT INTO `tb_teacher` VALUES (3, '10000003', '123456', '王五', '2022-09-13', '英语老师', '女', 'http://ria4omh4f.hn-bkt.clouddn.com/FuVQpB1-AldvLvjV8vtrDD-BKohh', '79551326589', '45467894131348798', 2810.00, '沙口骨干特级教师');
INSERT INTO `tb_teacher` VALUES (7, '10000004', '123456', '麻六', '2022-09-28', '体育老师', '男', 'http://ria4omh4f.hn-bkt.clouddn.com/FggKdbtPrvfrvB2o39tsF01IEMFz', '16579825953', '45467899861348798', 1500.00, '沙口教师');

SET FOREIGN_KEY_CHECKS = 1;
