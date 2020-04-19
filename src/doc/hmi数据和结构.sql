/*
 Navicat Premium Data Transfer

 Source Server         : hmi
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : hmi

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 19/04/2020 12:12:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alarm_info
-- ----------------------------
DROP TABLE IF EXISTS `alarm_info`;
CREATE TABLE `alarm_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `TRIGGER_DB` int(0) DEFAULT NULL COMMENT '触发DB 报警是由哪个地址触发的',
  `TRIGGER_OFFSET` int(0) DEFAULT NULL COMMENT '触发DB偏移 报警是由哪个地址触发的',
  `TRIGGER_BIT` int(0) DEFAULT NULL COMMENT '触发位 报警是由哪个地址触发的',
  `ALARM_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '警报类型 E：ERROR，W:WARNING,I:INFO',
  `ALARM_GROUP` int(0) DEFAULT NULL COMMENT '报警组 0--100',
  `ACTIVE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '激活 0未激活 1激活',
  `ALARM_INFO` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '警报内容 ',
  `ALARM_HELP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '警报提示 ',
  `ALARM_STATUS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '警报状态 0待确认 1已确认',
  `ALARM_TIME` datetime(0) DEFAULT NULL COMMENT '警报发生时间',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报警信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of alarm_info
-- ----------------------------
INSERT INTO `alarm_info` VALUES (1, 100, 1, 2, 'E', 3, '0', 'INFOOOOOO', '手工排除', '1', NULL, 'chensubei', 'chensubei', '2020-03-27 23:13:55', '2020-03-27 23:16:20', '0');

-- ----------------------------
-- Table structure for his_alarm_info
-- ----------------------------
DROP TABLE IF EXISTS `his_alarm_info`;
CREATE TABLE `his_alarm_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `ALARM_ID` bigint(0) NOT NULL COMMENT '警报信息ID',
  `ALARM_START_TIME` datetime(0) DEFAULT NULL COMMENT '警报开始时间',
  `ALARM_STOP_TIME` datetime(0) DEFAULT NULL COMMENT '警报结束时间',
  `ALARM_STATUS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '警报状态 0历史 1当前',
  `ALARM_CFM_STATUS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '警报确认状态 0已确认 1待确认 ',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '历史报警信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_alarm_info
-- ----------------------------
INSERT INTO `his_alarm_info` VALUES (1, 10032, '2020-03-27 15:00:55', '2020-03-27 15:00:55', '1', '0', 'chensubei', 'chensubei', '2020-03-27 23:00:55', '2020-03-27 23:03:14', '0');

-- ----------------------------
-- Table structure for pressure_curve
-- ----------------------------
DROP TABLE IF EXISTS `pressure_curve`;
CREATE TABLE `pressure_curve`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `PRESS_DATA_ID` bigint(0) DEFAULT NULL COMMENT '位置/压力曲线ID 压装数据表ID',
  `RECORD_NO` int(0) DEFAULT NULL COMMENT '位置点序号 采集的序列号。1-N递增',
  `POSITION` decimal(10, 4) DEFAULT NULL COMMENT '位置 mm',
  `PRESS_FORCE` decimal(10, 4) DEFAULT NULL COMMENT '压力 KN',
  `CUR_SPEED` decimal(10, 4) DEFAULT NULL COMMENT '当前速度',
  `PRESS_DATE` decimal(20, 0) DEFAULT NULL COMMENT '压装时间点',
  `HANDLE_DATE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据所属日期',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '压力曲线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pressure_curve
-- ----------------------------
INSERT INTO `pressure_curve` VALUES (1, 1, 2, 1.1000, 2.1000, 0.0000, 20200409232605003, '20200409', 'chensubei', 'chensubei', '2020-03-27 22:11:33', '2020-03-27 22:11:33', '0');
INSERT INTO `pressure_curve` VALUES (2, 1, 3, 3.5000, 6.1000, 0.0000, 20200409232605113, '20200409', 'SYS', 'SYS', '2020-04-09 23:33:26', '2020-04-09 23:33:26', '0');
INSERT INTO `pressure_curve` VALUES (3, 1, 2, 7.2000, 3.5000, 0.0000, 20200409232605226, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (4, 1, 2, 3.2000, 7.5000, 0.0000, 20200409232605333, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (5, 1, 2, 8.1000, 3.5000, 0.0000, 20200409232605445, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (6, 1, 2, 4.4000, 9.5000, 0.0000, 20200409232605589, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (7, 1, 2, 6.4000, 4.5000, 0.0000, 20200409232605631, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (8, 1, 2, 2.8000, 5.5000, 0.0000, 20200409232605703, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (9, 1, 2, 7.3000, 6.5000, 0.0000, 20200409232605822, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (10, 1, 2, 3.5000, 2.5000, 0.0000, 20200409232605983, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');
INSERT INTO `pressure_curve` VALUES (11, 1, 2, 5.2000, 1.5000, 0.0000, 20200409232606073, '20200409', 'SYS', 'SYS', '2020-04-09 23:35:28', '2020-04-09 23:35:33', '0');

-- ----------------------------
-- Table structure for pressure_data
-- ----------------------------
DROP TABLE IF EXISTS `pressure_data`;
CREATE TABLE `pressure_data`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` bigint(0) DEFAULT NULL COMMENT '产品表ID',
  `RECORD_ID` bigint(0) DEFAULT NULL COMMENT '位置/压力曲线ID',
  `PRODUCT_NO` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品二维码',
  `PRESS_RESULT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '压装结果',
  `MAX_PRESS` decimal(8, 0) DEFAULT NULL COMMENT '最大压力值',
  `POSITION_OF_MAX_PRESS` decimal(8, 0) DEFAULT NULL COMMENT '最大压力时位移',
  `START_DATE` decimal(20, 0) DEFAULT NULL COMMENT '开始时间',
  `END_DATE` decimal(20, 0) DEFAULT NULL COMMENT '结束时间',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品压装数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pressure_data
-- ----------------------------
INSERT INTO `pressure_data` VALUES (1, 1, 1, 'product0001', '1', 100, 100, 20200409232605003, 20200409232606083, 'chensubei', 'chensubei', '2020-03-27 21:53:05', '2020-03-27 21:54:34', '0');
INSERT INTO `pressure_data` VALUES (2, 2, 1, 'product0002', '1', 100, 100, 20200409232607003, 20200409232608083, 'chensubei', 'chensubei', '2020-03-27 21:53:05', '2020-03-27 21:54:34', '0');
INSERT INTO `pressure_data` VALUES (3, 3, 1, 'product0003', '0', 100, 100, 20200409232608003, 20200409232609083, 'chensubei', 'chensubei', '2020-03-27 21:53:05', '2020-03-27 21:54:34', '0');

-- ----------------------------
-- Table structure for pressure_program
-- ----------------------------
DROP TABLE IF EXISTS `pressure_program`;
CREATE TABLE `pressure_program`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` bigint(0) NOT NULL COMMENT '产品表ID',
  `PRODUCT_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品代码',
  `STEP_1` int(0) NOT NULL COMMENT '步骤_1 0-127',
  `PROGRAM_TYPE_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_1 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_1` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_1',
  `SPEED_1` decimal(8, 3) DEFAULT NULL COMMENT '速度_1',
  `ALARM_DEAL_TYPE_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_1 1：停止；2：返回原点；4：继续',
  `POSITION_1` decimal(8, 3) DEFAULT NULL COMMENT '位置_1',
  `PROTECT_PRESS_1` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_1',
  `PRESS_1` decimal(8, 3) DEFAULT NULL COMMENT '压力_1',
  `PROTECT_POSITION_1` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_1',
  `PROTECT_TIME_1` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值_1 timer类型',
  `STEP_2` int(0) NOT NULL COMMENT '步骤_3 0-127',
  `PROGRAM_TYPE_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_3 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_2` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_3',
  `SPEED_2` decimal(8, 3) DEFAULT NULL COMMENT '速度_3',
  `ALARM_DEAL_TYPE_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_3 1：停止；2：返回原点；4：继续',
  `POSITION_2` decimal(8, 3) DEFAULT NULL COMMENT '位置_3',
  `PROTECT_PRESS_2` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_3',
  `PRESS_2` decimal(8, 3) DEFAULT NULL COMMENT '压力_3',
  `PROTECT_POSITION_2` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_3',
  `PROTECT_TIME_2` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值_3 timer类型',
  `STEP_3` int(0) NOT NULL COMMENT '步骤_3 0-127',
  `PROGRAM_TYPE_3` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_3 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_3` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_3',
  `SPEED_3` decimal(8, 3) DEFAULT NULL COMMENT '速度_3',
  `ALARM_DEAL_TYPE_3` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_3 1：停止；2：返回原点；4：继续',
  `POSITION_3` decimal(8, 3) DEFAULT NULL COMMENT '位置_3',
  `PROTECT_PRESS_3` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_3',
  `PRESS_3` decimal(8, 3) DEFAULT NULL COMMENT '压力_3',
  `PROTECT_POSITION_3` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_3',
  `PROTECT_TIME_3` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值_3 timer类型',
  `STEP_4` int(0) NOT NULL COMMENT '步骤_4 0-127',
  `PROGRAM_TYPE_4` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_4 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_4` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_4',
  `SPEED_4` decimal(8, 3) DEFAULT NULL COMMENT '速度_4',
  `ALARM_DEAL_TYPE_4` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_4 1：停止；2：返回原点；4：继续',
  `POSITION_4` decimal(8, 3) DEFAULT NULL COMMENT '位置_4',
  `PROTECT_PRESS_4` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_4',
  `PRESS_4` decimal(8, 3) DEFAULT NULL COMMENT '压力_4',
  `PROTECT_POSITION_4` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_4',
  `PROTECT_TIME_4` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值 timer类型_4',
  `STEP_5` int(0) NOT NULL COMMENT '步骤_5 0-127',
  `PROGRAM_TYPE_5` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_5 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_5` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_5',
  `SPEED_5` decimal(8, 3) DEFAULT NULL COMMENT '速度_5',
  `ALARM_DEAL_TYPE_5` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_5 1：停止；2：返回原点；4：继续',
  `POSITION_5` decimal(8, 3) DEFAULT NULL COMMENT '位置_5',
  `PROTECT_PRESS_5` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_5',
  `PRESS_5` decimal(8, 3) DEFAULT NULL COMMENT '压力_5',
  `PROTECT_POSITION_5` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_5',
  `PROTECT_TIME_5` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值_5 timer类型',
  `STEP_6` int(0) NOT NULL COMMENT '步骤_6 0-127',
  `PROGRAM_TYPE_6` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_6 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_6` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_6',
  `SPEED_6` decimal(8, 3) DEFAULT NULL COMMENT '速度_6',
  `ALARM_DEAL_TYPE_6` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_6 1：停止；2：返回原点；4：继续',
  `POSITION_6` decimal(8, 3) DEFAULT NULL COMMENT '位置_6',
  `PROTECT_PRESS_6` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_6',
  `PRESS_6` decimal(8, 3) DEFAULT NULL COMMENT '压力_6',
  `PROTECT_POSITION_6` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_6',
  `PROTECT_TIME_6` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值_6 timer类型',
  `STEP_7` int(0) NOT NULL COMMENT '步骤_7 0-127',
  `PROGRAM_TYPE_7` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_7 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_7` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_7',
  `SPEED_7` decimal(8, 3) DEFAULT NULL COMMENT '速度_7',
  `ALARM_DEAL_TYPE_7` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_7 1：停止；2：返回原点；4：继续',
  `POSITION_7` decimal(8, 3) DEFAULT NULL COMMENT '位置_7',
  `PROTECT_PRESS_7` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_7',
  `PRESS_7` decimal(8, 3) DEFAULT NULL COMMENT '压力_7',
  `PROTECT_POSITION_7` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_7',
  `PROTECT_TIME_7` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值_7 timer类型',
  `STEP_8` int(0) NOT NULL COMMENT '步骤_8 0-127',
  `PROGRAM_TYPE_8` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '程序类型_8 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `PROGRAM_VALUE_8` decimal(8, 3) DEFAULT NULL COMMENT '程序参数值_8',
  `SPEED_8` decimal(8, 3) DEFAULT NULL COMMENT '速度_8',
  `ALARM_DEAL_TYPE_8` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理方式_8 1：停止；2：返回原点；4：继续',
  `POSITION_8` decimal(8, 3) DEFAULT NULL COMMENT '位置_8',
  `PROTECT_PRESS_8` decimal(8, 3) DEFAULT NULL COMMENT '保护压力值_8',
  `PRESS_8` decimal(8, 3) DEFAULT NULL COMMENT '压力_8',
  `PROTECT_POSITION_8` decimal(8, 3) DEFAULT NULL COMMENT '保护位置值_8',
  `PROTECT_TIME_8` decimal(10, 0) DEFAULT NULL COMMENT '保压时间值 timer类型_8',
  `ERRAND_TYPE_1` int(0) DEFAULT NULL COMMENT '窗口类型_10:disable;1-12:见：公差窗口判据说明',
  `POSITION_MIN_1` decimal(8, 3) DEFAULT NULL COMMENT '位置下限_1',
  `POSITION_MAX_1` decimal(8, 3) DEFAULT NULL COMMENT '位置上限_1',
  `PRESS_MIN_1` decimal(8, 3) DEFAULT NULL COMMENT '压力下限_1',
  `PRESS_MAX_1` decimal(8, 3) DEFAULT NULL COMMENT '压力上限_1',
  `ERRAND_TYPE_2` int(0) DEFAULT NULL COMMENT '窗口类型_20:disable;1-12:见：公差窗口判据说明',
  `POSITION_MIN_2` decimal(8, 3) DEFAULT NULL COMMENT '位置下限_2',
  `POSITION_MAX_2` decimal(8, 3) DEFAULT NULL COMMENT '位置上限_2',
  `PRESS_MIN_2` decimal(8, 3) DEFAULT NULL COMMENT '压力下限_2',
  `PRESS_MAX_2` decimal(8, 3) DEFAULT NULL COMMENT '压力上限_2',
  `ERRAND_TYPE_3` int(0) DEFAULT NULL COMMENT '窗口类型_30:disable;1-12:见：公差窗口判据说明',
  `POSITION_MIN_3` decimal(8, 3) DEFAULT NULL COMMENT '位置下限_3',
  `POSITION_MAX_3` decimal(8, 3) DEFAULT NULL COMMENT '位置上限_3',
  `PRESS_MIN_3` decimal(8, 3) DEFAULT NULL COMMENT '压力下限_3',
  `PRESS_MAX_3` decimal(8, 3) DEFAULT NULL COMMENT '压力上限_3',
  `ERRAND_TYPE_4` int(0) DEFAULT NULL COMMENT '窗口类型_40:disable;1-12:见：公差窗口判据说明',
  `POSITION_MIN_4` decimal(8, 3) DEFAULT NULL COMMENT '位置下限_4',
  `POSITION_MAX_4` decimal(8, 3) DEFAULT NULL COMMENT '位置上限_4',
  `PRESS_MIN_4` decimal(8, 3) DEFAULT NULL COMMENT '压力下限_4',
  `PRESS_MAX_4` decimal(8, 3) DEFAULT NULL COMMENT '压力上限_4',
  `ERRAND_TYPE_5` int(0) DEFAULT NULL COMMENT '窗口类型_50:disable;1-12:见：公差窗口判据说明',
  `POSITION_MIN_5` decimal(8, 3) DEFAULT NULL COMMENT '位置下限_5',
  `POSITION_MAX_5` decimal(8, 3) DEFAULT NULL COMMENT '位置上限_5',
  `PRESS_MIN_5` decimal(8, 3) DEFAULT NULL COMMENT '压力下限_5',
  `PRESS_MAX_5` decimal(8, 3) DEFAULT NULL COMMENT '压力上限_5',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '压装程序表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pressure_program
-- ----------------------------
INSERT INTO `pressure_program` VALUES (1, 1, 'prodCode', 1, '1', 1.100, 1.200, '1', 1.300, 1.400, 1.500, 1.600, 1, 2, '2', 2.200, 2.200, '2', 2.300, 2.400, 2.500, 2.600, 2, 3, '3', 3.100, 3.200, '3', 3.300, 3.400, 3.500, 3.600, 3, 4, '4', 4.100, 4.200, '4', 4.300, 4.400, 4.500, 4.600, 4, 5, '5', 5.100, 5.200, '5', 5.300, 5.400, 5.500, 5.600, 5, 6, '6', 6.100, 6.200, '6', 6.300, 6.400, 6.500, 6.600, 6, 7, '7', 7.100, 7.200, '7', 7.300, 7.400, 7.500, 7.600, 7, 8, '8', 8.800, 8.200, '8', 8.300, 8.400, 8.500, 8.600, 8, 0, 1.000, 9.100, 99.100, NULL, 2, 2.000, 9.200, 2.200, 99.200, 3, 2.000, 9.300, 3.300, 99.300, 4, 2.000, 9.400, 4.400, 99.400, 5, 2.000, 9.500, 5.500, 99.500, 'chensubei', 'chensubei', '2020-03-28 00:32:50', '2020-03-28 00:40:26', '0');
INSERT INTO `pressure_program` VALUES (2, 1, 'prodCode', 1, '1', 1.100, 1.200, '1', 1.300, 1.400, 1.500, 1.600, 1, 2, '2', 2.200, 2.200, '2', 2.300, 2.400, 2.500, 2.600, 2, 3, '3', 3.100, 3.200, '3', 3.300, 3.400, 3.500, 3.600, 3, 4, '4', 4.100, 4.200, '4', 4.300, 4.400, 4.500, 4.600, 4, 5, '5', 5.100, 5.200, '5', 5.300, 5.400, 5.500, 5.600, 5, 6, '6', 6.100, 6.200, '6', 6.300, 6.400, 6.500, 6.600, 6, 7, '7', 7.100, 7.200, '7', 7.300, 7.400, 7.500, 7.600, 7, 8, '8', 8.800, 8.200, '8', 8.300, 8.400, 8.500, 8.600, 8, 0, 1.000, 9.100, 1.100, 99.100, 2, 2.000, 9.200, 2.200, 99.200, 3, 2.000, 9.300, 3.300, 99.300, 4, 2.000, 9.400, 4.400, 99.400, 5, 2.000, 9.500, 5.500, 99.500, 'c', 'chensubei', '2020-03-28 00:36:21', '2020-03-28 00:44:33', '0');
INSERT INTO `pressure_program` VALUES (3, 1, 'prodCode', 1, '1', 1.100, 1.200, '1', 1.300, 1.400, 1.500, 1.600, 1, 2, '2', 2.200, 2.200, '2', 2.300, 2.400, 2.500, 2.600, 2, 3, '3', 3.100, 3.200, '3', 3.300, 3.400, 3.500, 3.600, 3, 4, '4', 4.100, 4.200, '4', 4.300, 4.400, 4.500, 4.600, 4, 5, '5', 5.100, 5.200, '5', 5.300, 5.400, 5.500, 5.600, 5, 6, '6', 6.100, 6.200, '6', 6.300, 6.400, 6.500, 6.600, 6, 7, '7', 7.100, 7.200, '7', 7.300, 7.400, 7.500, 7.600, 7, 8, '8', 8.800, 8.200, '8', 8.300, 8.400, 8.500, 8.600, 8, 0, 1.000, 9.100, 1.100, 99.100, 2, 2.000, 9.200, 2.200, 99.200, 3, 2.000, 9.300, 3.300, 99.300, 4, 2.000, 9.400, 4.400, 99.400, 5, 2.000, 9.500, 5.500, 99.500, 'chensubei', 'chensubei', '2020-03-28 00:40:59', '2020-03-28 00:40:59', '0');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `PRODUCT_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `PRODUCT_TYPE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品类型',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES (1, 'TestProduct', 'TYPE_A', 'chensubei', 'chensubei', '2020-03-25 19:21:32', '2020-03-25 19:23:46', '0');

-- ----------------------------
-- Table structure for property_config
-- ----------------------------
DROP TABLE IF EXISTS `property_config`;
CREATE TABLE `property_config`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `PROP_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名',
  `PROP_VALUE` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性值',
  `PROP_GROUP` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分组',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `DESCRIPTION` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of property_config
-- ----------------------------
INSERT INTO `property_config` VALUES (5, 'plc.server.ip', '192.168.1.1', 'plc.server', 'chensubei', '2020-03-05 22:14:52', '2020-03-05 22:14:59', '0', 'chensubei', 'PLC服务器IP');
INSERT INTO `property_config` VALUES (6, 'plc.server.rock', '2', 'plc.server', 'chensubei', '2020-03-05 22:17:20', '2020-03-05 22:17:25', '0', 'chensubei', 'PLC服务器机架号');
INSERT INTO `property_config` VALUES (7, 'plc.server.slot', '1', 'plc.server', 'chensubei', '2020-03-05 22:18:20', '2020-03-05 22:18:23', '0', 'chensubei', 'PLC服务器插槽号');

-- ----------------------------
-- Table structure for public_errand
-- ----------------------------
DROP TABLE IF EXISTS `public_errand`;
CREATE TABLE `public_errand`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `PRODUCT_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品代码',
  `ERRAND_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '窗口类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END',
  `POSITION_MIN` decimal(8, 3) NOT NULL COMMENT '位置下限',
  `POSITION_MAX` decimal(8, 3) NOT NULL COMMENT '位置上限',
  `PRESS_MIN` decimal(8, 3) NOT NULL COMMENT '压力下限',
  `PRESS_MAX` decimal(8, 3) NOT NULL COMMENT '压力上限',
  `ALARM_RESPONSE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报警反应 1：停止；2：返回原点；4：继续',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公差窗口' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_parameter
-- ----------------------------
DROP TABLE IF EXISTS `system_parameter`;
CREATE TABLE `system_parameter`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `MAX_DISTANCE` decimal(8, 3) NOT NULL COMMENT '压机最大行程',
  `MAX_FORCE` decimal(8, 3) NOT NULL COMMENT '压机最大压力',
  `MAX_SPEED` decimal(8, 3) NOT NULL COMMENT '压机最大速度',
  `DEFAULT_BACK_SPEED` decimal(8, 3) NOT NULL COMMENT '默认回程速度',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_parameter
-- ----------------------------
INSERT INTO `system_parameter` VALUES (1, 1.100, 3.100, 2.100, 4.100, 'chensubei', 'chensubei', '2020-03-27 22:50:57', '2020-03-27 22:52:19', '0');

-- ----------------------------
-- Table structure for tag_type
-- ----------------------------
DROP TABLE IF EXISTS `tag_type`;
CREATE TABLE `tag_type`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `DATA_TYPE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据类型',
  `DATA_LENGTH` int(0) DEFAULT NULL COMMENT '数据长度',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '变量类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag_type
-- ----------------------------
INSERT INTO `tag_type` VALUES (1, 'BOOL', 1, 'sys', 'sys', '2020-03-28 11:25:25', '2020-03-28 11:25:25', '0');
INSERT INTO `tag_type` VALUES (2, 'BYTE', 1, 'sys', 'sys', '2020-03-28 11:25:25', '2020-03-28 11:25:25', '0');
INSERT INTO `tag_type` VALUES (3, 'CHAR', 1, 'sys', 'sys', '2020-03-28 11:25:25', '2020-03-28 11:25:25', '0');
INSERT INTO `tag_type` VALUES (4, 'DATE', 2, 'sys', 'sys', '2020-03-28 11:25:25', '2020-03-28 11:25:25', '0');
INSERT INTO `tag_type` VALUES (5, 'DINT', 4, 'sys', 'sys', '2020-03-28 11:25:25', '2020-03-28 11:25:25', '0');
INSERT INTO `tag_type` VALUES (6, 'DWORD', 4, 'sys', 'sys', '2020-03-28 11:25:25', '2020-03-28 11:25:25', '0');
INSERT INTO `tag_type` VALUES (7, 'INT', 2, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (8, 'LINT', 4, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (9, 'LREAL', 8, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (10, 'LWORD', 8, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (11, 'REAL', 4, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (12, 'SINT', 1, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (13, 'TIME', 4, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (14, 'UINT', 2, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (15, 'ULINT', 4, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (16, 'USINT', 1, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');
INSERT INTO `tag_type` VALUES (17, 'WORD', 2, 'sys', 'sys', '2020-03-28 11:25:26', '2020-03-28 11:25:26', '0');

-- ----------------------------
-- Table structure for tags_info
-- ----------------------------
DROP TABLE IF EXISTS `tags_info`;
CREATE TABLE `tags_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `TAG_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '变量名 ',
  `TAG_EN_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '变量英文名',
  `TAG_TYPE_ID` bigint(0) DEFAULT NULL COMMENT '变量类型ID',
  `TAG_TYPE_DES` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据类型 ',
  `TAG_AREA` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据区 Ox81:input;Ox82:output;Ox83:bitmenory;Ox84:db;Ox1c:conunters;Ox1d:timers',
  `TAG_AREA_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DB_NO` int(0) DEFAULT NULL COMMENT '数据块地址',
  `ADDRESS` int(0) DEFAULT NULL COMMENT '地址',
  `TAG_BIT` int(0) DEFAULT NULL COMMENT '位',
  `TAG_GROUP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分组',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 495 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'IO变量表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tags_info
-- ----------------------------
INSERT INTO `tags_info` VALUES (3, '零件号', 'productNo', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 0, 0, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (4, '压头选择', 'indenterChoice', 2, 'BYTE', 'Ox84', 'DB300.DBB', 300, 2, 0, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (5, '选择正压', 'choicePositive', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 3, 0, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (6, '选择反压', 'choiceNegative', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 3, 1, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (7, '光栅屏蔽', 'rasterClose', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 3, 2, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (8, '安全门屏蔽', 'safeDoorClose', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 3, 3, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (9, '蜂鸣器屏蔽', 'buzzerClose', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 3, 4, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (10, '预留1_0', 'reserve1x0', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 0, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (11, '预留1_1', 'reserve1x1', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 1, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (12, '预留1_2', 'reserve1x2', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 2, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (13, '预留1_3', 'reserve1x3', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 3, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (14, '预留1_4', 'reserve1x4', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 4, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (15, '预留1_5', 'reserve1x5', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 5, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (16, '预留1_6', 'reserve1x6', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 6, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (17, '预留1_7', 'reserve1x7', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 7, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (18, '预留1_8', 'reserve1x8', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 8, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (19, '预留1_9', 'reserve1x9', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 9, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (20, '预留1_10', 'reserve1x10', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 10, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (21, '预留1_11', 'reserve1x11', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 11, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (22, '预留1_12', 'reserve1x12', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 12, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:19', '2020-03-28 17:20:19', '0');
INSERT INTO `tags_info` VALUES (23, '预留1_13', 'reserve1x13', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 13, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (24, '预留1_14', 'reserve1x14', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 14, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (25, '预留1_15', 'reserve1x15', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 4, 15, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (26, '预留1_16', 'reserve1x16', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 0, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (27, '预留1_17', 'reserve1x17', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 1, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (28, '预留1_18', 'reserve1x18', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 2, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (29, '预留1_19', 'reserve1x19', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 3, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (30, '预留1_20', 'reserve1x20', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 4, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:20', '2020-03-28 17:20:20', '0');
INSERT INTO `tags_info` VALUES (31, '预留1_21', 'reserve1x21', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 5, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:21', '2020-03-28 17:20:21', '0');
INSERT INTO `tags_info` VALUES (32, '预留1_22', 'reserve1x22', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 6, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:21', '2020-03-28 17:20:21', '0');
INSERT INTO `tags_info` VALUES (33, '预留1_23', 'reserve1x23', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 7, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:21', '2020-03-28 17:20:21', '0');
INSERT INTO `tags_info` VALUES (34, '预留1_24', 'reserve1x24', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 8, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:21', '2020-03-28 17:20:21', '0');
INSERT INTO `tags_info` VALUES (35, '预留1_25', 'reserve1x25', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 9, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:21', '2020-03-28 17:20:21', '0');
INSERT INTO `tags_info` VALUES (36, '预留1_26', 'reserve1x26', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 10, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (37, '预留1_27', 'reserve1x27', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 11, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (38, '预留1_28', 'reserve1x28', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 12, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (39, '预留1_29', 'reserve1x29', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 13, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (40, '预留1_30', 'reserve1x30', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 14, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (41, '预留1_31', 'reserve1x31', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 5, 15, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (42, '预留2_0', 'reserve2x0', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 8, 0, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (43, '预留2_1', 'reserve2x1', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 8, 1, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (44, '预留2_2', 'reserve2x2', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 8, 2, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (45, '预留2_3', 'reserve2x3', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 8, 3, 'equipment_operation', 'SYS', 'SYS', '2020-03-28 17:20:22', '2020-03-28 17:20:22', '0');
INSERT INTO `tags_info` VALUES (46, '在线', 'onLine', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 18, 0, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (47, '运行中', 'running', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 18, 1, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (48, '手动操作', 'humanModel', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 18, 2, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (49, '自动操作', 'autoModel', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 18, 3, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (50, '系统报警', 'systemAlarm', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 18, 4, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (51, '安全报警', 'safeAlarm', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 18, 5, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (52, '预留1_0', 'reserve1x0', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 0, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (53, '预留1_1', 'reserve1x1', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 1, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:30', '2020-03-28 17:33:30', '0');
INSERT INTO `tags_info` VALUES (54, '预留1_2', 'reserve1x2', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 2, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (55, '预留1_3', 'reserve1x3', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 3, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (56, '预留1_4', 'reserve1x4', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 4, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (57, '预留1_5', 'reserve1x5', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 5, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (58, '预留1_6', 'reserve1x6', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 6, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (59, '预留1_7', 'reserve1x7', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 7, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (60, '预留1_8', 'reserve1x8', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 8, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (61, '预留1_9', 'reserve1x9', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 9, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (62, '预留1_10', 'reserve1x10', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 10, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (63, '预留1_11', 'reserve1x11', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 11, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:31', '2020-03-28 17:33:31', '0');
INSERT INTO `tags_info` VALUES (64, '预留1_12', 'reserve1x12', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 12, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (65, '预留1_13', 'reserve1x13', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 13, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (66, '预留1_14', 'reserve1x14', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 14, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (67, '预留1_15', 'reserve1x15', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 20, 15, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (68, '预留1_16', 'reserve1x16', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 0, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (69, '预留1_17', 'reserve1x17', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 1, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (70, '预留1_18', 'reserve1x18', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 2, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (71, '预留1_19', 'reserve1x19', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 3, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (72, '预留1_20', 'reserve1x20', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 4, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (73, '预留1_21', 'reserve1x21', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 5, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (74, '预留1_22', 'reserve1x22', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 6, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:32', '2020-03-28 17:33:32', '0');
INSERT INTO `tags_info` VALUES (75, '预留1_23', 'reserve1x23', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 7, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (76, '预留1_24', 'reserve1x24', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 8, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (77, '预留1_25', 'reserve1x25', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 9, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (78, '预留1_26', 'reserve1x26', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 10, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (79, '预留1_27', 'reserve1x27', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 11, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (80, '预留1_28', 'reserve1x28', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 12, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (81, '预留1_29', 'reserve1x29', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 13, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (82, '预留1_30', 'reserve1x30', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 14, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (83, '预留1_31', 'reserve1x31', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 21, 15, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (84, '预留2_0', 'reserve2x0', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 24, 0, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (85, '预留2_1', 'reserve2x1', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 24, 1, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (86, '预留2_2', 'reserve2x2', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 24, 2, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (87, '预留2_3', 'reserve2x3', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 24, 3, 'equipment_status', 'SYS', 'SYS', '2020-03-28 17:33:33', '2020-03-28 17:33:33', '0');
INSERT INTO `tags_info` VALUES (88, '警告字1_0', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 34, 0, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (89, '警告字1_1', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 34, 1, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (90, '警告字1_2', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 34, 2, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (91, '警告字1_3', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 34, 3, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (92, '错误字1_0', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 42, 0, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (93, '错误字1_1', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 42, 1, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (94, '错误字1_2', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 42, 2, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (95, '错误字1_3', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 42, 3, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (96, '状态字1_0', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 50, 0, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:09', '2020-03-28 17:46:09', '0');
INSERT INTO `tags_info` VALUES (97, '状态字1_1', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 50, 1, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:10', '2020-03-28 17:46:10', '0');
INSERT INTO `tags_info` VALUES (98, '状态字1_2', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 50, 2, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:10', '2020-03-28 17:46:10', '0');
INSERT INTO `tags_info` VALUES (99, '状态字1_3', NULL, 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 50, 3, 'equipment_alarm', 'SYS', 'SYS', '2020-03-28 17:46:10', '2020-03-28 17:46:10', '0');
INSERT INTO `tags_info` VALUES (100, '输入_0', 'input0', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 58, 0, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:01', '2020-03-28 17:50:01', '0');
INSERT INTO `tags_info` VALUES (101, '输入_1', 'input1', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 58, 1, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:01', '2020-03-28 17:50:01', '0');
INSERT INTO `tags_info` VALUES (102, '输入_2', 'input2', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 58, 2, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:01', '2020-03-28 17:50:01', '0');
INSERT INTO `tags_info` VALUES (103, '输入_3', 'input3', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 58, 3, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:01', '2020-03-28 17:50:01', '0');
INSERT INTO `tags_info` VALUES (104, '输入_4', 'input4', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 58, 4, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:02', '2020-03-28 17:50:02', '0');
INSERT INTO `tags_info` VALUES (105, '输出_0', 'output0', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 68, 0, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:02', '2020-03-28 17:50:02', '0');
INSERT INTO `tags_info` VALUES (106, '输出_1', 'output1', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 68, 1, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:02', '2020-03-28 17:50:02', '0');
INSERT INTO `tags_info` VALUES (107, '输出_2', 'output2', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 68, 2, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:02', '2020-03-28 17:50:02', '0');
INSERT INTO `tags_info` VALUES (108, '输出_3', 'output3', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 68, 3, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:02', '2020-03-28 17:50:02', '0');
INSERT INTO `tags_info` VALUES (109, '输出_4', 'output4', 17, 'WORD', 'Ox84', 'DB300.DBW', 300, 68, 4, 'equipment_io_status', 'SYS', 'SYS', '2020-03-28 17:50:02', '2020-03-28 17:50:02', '0');
INSERT INTO `tags_info` VALUES (110, '机械寻零', 'machine2Zero', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 188, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:53', '2020-03-28 18:15:53', '0');
INSERT INTO `tags_info` VALUES (111, '伺服使能', 'servoEnable', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 188, 1, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:53', '2020-03-28 18:15:53', '0');
INSERT INTO `tags_info` VALUES (112, '伺服停止', 'servoStop', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 188, 2, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:53', '2020-03-28 18:15:53', '0');
INSERT INTO `tags_info` VALUES (113, '返回工作原点', 'return2Orignal', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 188, 3, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:54', '2020-03-28 18:15:54', '0');
INSERT INTO `tags_info` VALUES (114, '点动上', 'pointMoveUp', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 188, 4, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:54', '2020-03-28 18:15:54', '0');
INSERT INTO `tags_info` VALUES (115, '点动下', 'pointMoveDown', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 188, 5, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:54', '2020-03-28 18:15:54', '0');
INSERT INTO `tags_info` VALUES (116, '点动速度', 'pointMoveSpeed', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 190, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:54', '2020-03-28 18:15:54', '0');
INSERT INTO `tags_info` VALUES (117, '单步位置', 'setpPosition', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 194, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:54', '2020-03-28 18:15:54', '0');
INSERT INTO `tags_info` VALUES (118, '单步速度', 'stepSpeed', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 198, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:54', '2020-03-28 18:15:54', '0');
INSERT INTO `tags_info` VALUES (119, '单步保护压力', 'stepProtectforce', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 202, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (120, '单步向上', 'stepUp', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 206, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (121, '单步向下', 'stopDown', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 206, 1, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (122, '预留1_0', 'reserve1x0', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (123, '预留1_1', 'reserve1x1', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 1, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (124, '预留1_2', 'reserve1x2', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 2, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (125, '预留1_3', 'reserve1x3', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 3, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (126, '预留1_4', 'reserve1x4', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 4, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (127, '预留1_5', 'reserve1x5', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 5, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (128, '预留1_6', 'reserve1x6', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 6, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (129, '预留1_7', 'reserve1x7', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 7, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (130, '预留1_8', 'reserve1x8', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 8, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (131, '预留1_9', 'reserve1x9', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 9, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (132, '预留1_10', 'reserve1x10', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 10, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:55', '2020-03-28 18:15:55', '0');
INSERT INTO `tags_info` VALUES (133, '预留1_11', 'reserve1x11', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 11, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (134, '预留1_12', 'reserve1x12', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 12, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (135, '预留1_13', 'reserve1x13', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 13, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (136, '预留1_14', 'reserve1x14', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 14, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (137, '预留1_15', 'reserve1x15', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 208, 15, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (138, '预留1_16', 'reserve1x16', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (139, '预留1_17', 'reserve1x17', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 1, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (140, '预留1_18', 'reserve1x18', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 2, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (141, '预留1_19', 'reserve1x19', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 3, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (142, '预留1_20', 'reserve1x20', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 4, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:56', '2020-03-28 18:15:56', '0');
INSERT INTO `tags_info` VALUES (143, '预留1_21', 'reserve1x21', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 5, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:57', '2020-03-28 18:15:57', '0');
INSERT INTO `tags_info` VALUES (144, '预留1_22', 'reserve1x22', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 6, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:57', '2020-03-28 18:15:57', '0');
INSERT INTO `tags_info` VALUES (145, '预留1_23', 'reserve1x23', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 7, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:57', '2020-03-28 18:15:57', '0');
INSERT INTO `tags_info` VALUES (146, '预留1_24', 'reserve1x24', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 8, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:57', '2020-03-28 18:15:57', '0');
INSERT INTO `tags_info` VALUES (147, '预留1_25', 'reserve1x25', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 9, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:57', '2020-03-28 18:15:57', '0');
INSERT INTO `tags_info` VALUES (148, '预留1_26', 'reserve1x26', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 10, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:58', '2020-03-28 18:15:58', '0');
INSERT INTO `tags_info` VALUES (149, '预留1_27', 'reserve1x27', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 11, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:58', '2020-03-28 18:15:58', '0');
INSERT INTO `tags_info` VALUES (150, '预留1_28', 'reserve1x28', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 12, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:58', '2020-03-28 18:15:58', '0');
INSERT INTO `tags_info` VALUES (151, '预留1_29', 'reserve1x29', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 13, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:58', '2020-03-28 18:15:58', '0');
INSERT INTO `tags_info` VALUES (152, '预留1_30', 'reserve1x30', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 14, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:58', '2020-03-28 18:15:58', '0');
INSERT INTO `tags_info` VALUES (153, '预留1_31', 'reserve1x31', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 209, 15, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (154, '预留1_32', 'reserve1x32', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (155, '预留1_33', 'reserve1x33', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 1, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (156, '预留1_34', 'reserve1x34', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 2, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (157, '预留1_35', 'reserve1x35', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 3, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (158, '预留1_36', 'reserve1x36', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 4, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (159, '预留1_37', 'reserve1x37', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 5, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (160, '预留1_38', 'reserve1x38', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 6, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (161, '预留1_39', 'reserve1x39', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 7, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (162, '预留1_40', 'reserve1x40', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 8, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:15:59', '2020-03-28 18:15:59', '0');
INSERT INTO `tags_info` VALUES (163, '预留1_41', 'reserve1x41', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 9, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (164, '预留1_42', 'reserve1x42', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 10, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (165, '预留1_43', 'reserve1x43', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 11, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (166, '预留1_44', 'reserve1x44', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 12, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (167, '预留1_45', 'reserve1x45', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 13, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (168, '预留1_46', 'reserve1x46', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 14, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (169, '预留1_47', 'reserve1x47', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 210, 15, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (170, '预留1_48', 'reserve1x48', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (171, '预留1_49', 'reserve1x49', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 1, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (172, '预留1_50', 'reserve1x50', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 2, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (173, '预留1_51', 'reserve1x51', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 3, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (174, '预留1_52', 'reserve1x52', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 4, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:00', '2020-03-28 18:16:00', '0');
INSERT INTO `tags_info` VALUES (175, '预留1_53', 'reserve1x53', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 5, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (176, '预留1_54', 'reserve1x54', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 6, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (177, '预留1_55', 'reserve1x55', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 7, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (178, '预留1_56', 'reserve1x56', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 8, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (179, '预留1_57', 'reserve1x57', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 9, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (180, '预留1_58', 'reserve1x58', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 10, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (181, '预留1_59', 'reserve1x59', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 11, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (182, '预留1_60', 'reserve1x60', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 12, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (183, '预留1_61', 'reserve1x61', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 13, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (184, '预留1_62', 'reserve1x62', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 14, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (185, '预留1_63', 'reserve1x63', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 211, 15, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (186, '预留2_0', 'reserve2x0', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 0, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:01', '2020-03-28 18:16:01', '0');
INSERT INTO `tags_info` VALUES (187, '预留2_1', 'reserve2x1', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 1, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (188, '预留2_2', 'reserve2x2', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 2, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (189, '预留2_3', 'reserve2x3', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 3, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (190, '预留2_4', 'reserve2x4', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 4, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (191, '预留2_5', 'reserve2x5', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 5, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (192, '预留2_6', 'reserve2x6', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 6, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (193, '预留2_7', 'reserve2x7', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 7, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (194, '预留2_8', 'reserve2x8', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 8, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (195, '预留2_9', 'reserve2x9', 6, 'DWORD', 'Ox84', 'DB300.DBD', 300, 216, 9, 'servo_operation', 'SYS', 'SYS', '2020-03-28 18:16:02', '2020-03-28 18:16:02', '0');
INSERT INTO `tags_info` VALUES (196, '伺服寻零完成', 'toZeroFinish', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 256, 0, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:54', '2020-03-28 18:27:54', '0');
INSERT INTO `tags_info` VALUES (197, '伺服在工作原点', 'atOrignal', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 256, 1, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:54', '2020-03-28 18:27:54', '0');
INSERT INTO `tags_info` VALUES (198, '伺服准备好', 'ready', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 256, 2, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:54', '2020-03-28 18:27:54', '0');
INSERT INTO `tags_info` VALUES (199, '机械寻零中', 'toZeroing', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 256, 3, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (200, '返回工作原点中', 'toOrignaling', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 256, 4, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (201, '预留_0', 'reserve0', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 0, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (202, '预留_1', 'reserve1', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 1, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (203, '预留_2', 'reserve2', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 2, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (204, '预留_3', 'reserve3', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 3, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (205, '预留_4', 'reserve4', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 4, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (206, '预留_5', 'reserve5', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 5, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:55', '2020-03-28 18:27:55', '0');
INSERT INTO `tags_info` VALUES (207, '预留_6', 'reserve6', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 6, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (208, '预留_7', 'reserve7', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 7, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (209, '预留_8', 'reserve8', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 8, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (210, '预留_9', 'reserve9', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 9, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (211, '预留_10', 'reserve10', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 10, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (212, '预留_11', 'reserve11', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 11, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (213, '预留_12', 'reserve12', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 12, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (214, '预留_13', 'reserve13', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 13, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (215, '预留_14', 'reserve14', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 14, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (216, '预留_15', 'reserve15', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 258, 15, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (217, '预留_16', 'reserve16', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 0, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:56', '2020-03-28 18:27:56', '0');
INSERT INTO `tags_info` VALUES (218, '预留_17', 'reserve17', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 1, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:57', '2020-03-28 18:27:57', '0');
INSERT INTO `tags_info` VALUES (219, '预留_18', 'reserve18', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 2, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:57', '2020-03-28 18:27:57', '0');
INSERT INTO `tags_info` VALUES (220, '预留_19', 'reserve19', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 3, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:57', '2020-03-28 18:27:57', '0');
INSERT INTO `tags_info` VALUES (221, '预留_20', 'reserve20', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 4, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:57', '2020-03-28 18:27:57', '0');
INSERT INTO `tags_info` VALUES (222, '预留_21', 'reserve21', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 5, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:57', '2020-03-28 18:27:57', '0');
INSERT INTO `tags_info` VALUES (223, '预留_22', 'reserve22', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 6, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:57', '2020-03-28 18:27:57', '0');
INSERT INTO `tags_info` VALUES (224, '预留_23', 'reserve23', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 7, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:58', '2020-03-28 18:27:58', '0');
INSERT INTO `tags_info` VALUES (225, '预留_24', 'reserve24', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 8, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:58', '2020-03-28 18:27:58', '0');
INSERT INTO `tags_info` VALUES (226, '预留_25', 'reserve25', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 9, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:58', '2020-03-28 18:27:58', '0');
INSERT INTO `tags_info` VALUES (227, '预留_26', 'reserve26', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 10, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:58', '2020-03-28 18:27:58', '0');
INSERT INTO `tags_info` VALUES (228, '预留_27', 'reserve27', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 11, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:58', '2020-03-28 18:27:58', '0');
INSERT INTO `tags_info` VALUES (229, '预留_28', 'reserve28', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 12, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:58', '2020-03-28 18:27:58', '0');
INSERT INTO `tags_info` VALUES (230, '预留_29', 'reserve29', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 13, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:59', '2020-03-28 18:27:59', '0');
INSERT INTO `tags_info` VALUES (231, '预留_30', 'reserve30', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 14, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:59', '2020-03-28 18:27:59', '0');
INSERT INTO `tags_info` VALUES (232, '预留_31', 'reserve31', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 259, 15, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:59', '2020-03-28 18:27:59', '0');
INSERT INTO `tags_info` VALUES (233, '预留_32', 'reserve32', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 0, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:59', '2020-03-28 18:27:59', '0');
INSERT INTO `tags_info` VALUES (234, '预留_33', 'reserve33', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 1, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:59', '2020-03-28 18:27:59', '0');
INSERT INTO `tags_info` VALUES (235, '预留_34', 'reserve34', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 2, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:59', '2020-03-28 18:27:59', '0');
INSERT INTO `tags_info` VALUES (236, '预留_35', 'reserve35', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 3, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:27:59', '2020-03-28 18:27:59', '0');
INSERT INTO `tags_info` VALUES (237, '预留_36', 'reserve36', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 4, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:00', '2020-03-28 18:28:00', '0');
INSERT INTO `tags_info` VALUES (238, '预留_37', 'reserve37', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 5, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:00', '2020-03-28 18:28:00', '0');
INSERT INTO `tags_info` VALUES (239, '预留_38', 'reserve38', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 6, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:00', '2020-03-28 18:28:00', '0');
INSERT INTO `tags_info` VALUES (240, '预留_39', 'reserve39', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 7, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:00', '2020-03-28 18:28:00', '0');
INSERT INTO `tags_info` VALUES (241, '预留_40', 'reserve40', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 8, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:00', '2020-03-28 18:28:00', '0');
INSERT INTO `tags_info` VALUES (242, '预留_41', 'reserve41', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 9, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (243, '预留_42', 'reserve42', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 10, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (244, '预留_43', 'reserve43', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 11, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (245, '预留_44', 'reserve44', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 12, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (246, '预留_45', 'reserve45', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 13, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (247, '预留_46', 'reserve46', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 14, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (248, '预留_47', 'reserve47', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 260, 15, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (249, '预留_48', 'reserve48', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 0, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (250, '预留_49', 'reserve49', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 1, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (251, '预留_50', 'reserve50', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 2, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (252, '预留_51', 'reserve51', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 3, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:01', '2020-03-28 18:28:01', '0');
INSERT INTO `tags_info` VALUES (253, '预留_52', 'reserve52', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 4, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (254, '预留_53', 'reserve53', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 5, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (255, '预留_54', 'reserve54', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 6, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (256, '预留_55', 'reserve55', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 7, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (257, '预留_56', 'reserve56', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 8, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (258, '预留_57', 'reserve57', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 9, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (259, '预留_58', 'reserve58', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 10, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (260, '预留_59', 'reserve59', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 11, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (261, '预留_60', 'reserve60', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 12, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (262, '预留_61', 'reserve61', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 13, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:02', '2020-03-28 18:28:02', '0');
INSERT INTO `tags_info` VALUES (263, '预留_62', 'reserve62', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 14, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:03', '2020-03-28 18:28:03', '0');
INSERT INTO `tags_info` VALUES (264, '预留_63', 'reserve63', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 261, 15, 'servo_status', 'SYS', 'SYS', '2020-03-28 18:28:03', '2020-03-28 18:28:03', '0');
INSERT INTO `tags_info` VALUES (265, '最大位置', 'maxPositon', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 266, 0, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:03', '2020-03-28 18:32:03', '0');
INSERT INTO `tags_info` VALUES (266, '最大速度', 'maxSpeed', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 270, 0, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:03', '2020-03-28 18:32:03', '0');
INSERT INTO `tags_info` VALUES (267, '最大压力', 'maxForce', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 274, 0, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:03', '2020-03-28 18:32:03', '0');
INSERT INTO `tags_info` VALUES (268, '默认回零速度', 'default2ZeroSpeed', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 278, 0, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:03', '2020-03-28 18:32:03', '0');
INSERT INTO `tags_info` VALUES (269, '预留_0', 'reserve0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 282, 0, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:03', '2020-03-28 18:32:03', '0');
INSERT INTO `tags_info` VALUES (270, '预留_1', 'reserve1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 282, 1, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:03', '2020-03-28 18:32:03', '0');
INSERT INTO `tags_info` VALUES (271, '预留_2', 'reserve2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 282, 2, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:03', '2020-03-28 18:32:03', '0');
INSERT INTO `tags_info` VALUES (272, '预留_3', 'reserve3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 282, 3, 'servo_limit_set', 'SYS', 'SYS', '2020-03-28 18:32:04', '2020-03-28 18:32:04', '0');
INSERT INTO `tags_info` VALUES (273, '当前位置', 'curPosition', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 298, 0, 'curve_data', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '0');
INSERT INTO `tags_info` VALUES (274, '当前压力', 'curForce', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 302, 0, 'curve_data', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '0');
INSERT INTO `tags_info` VALUES (275, '当前速度', 'curSpeed', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 306, 0, 'curve_data', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '1');
INSERT INTO `tags_info` VALUES (276, '预留_0', 'reserve1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 310, 0, 'curve_data', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '1');
INSERT INTO `tags_info` VALUES (277, '预留_1', 'reserve0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 314, 1, 'curve_data', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '1');
INSERT INTO `tags_info` VALUES (278, '预留_2', 'reserve2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 318, 2, 'curve_data', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '1');
INSERT INTO `tags_info` VALUES (279, 'OK', 'ok', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 322, 0, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:16', '2020-03-28 18:40:16', '0');
INSERT INTO `tags_info` VALUES (280, 'NOK', 'nok', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 322, 1, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:16', '2020-03-28 18:40:16', '0');
INSERT INTO `tags_info` VALUES (281, '压装完成', 'finish', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 322, 2, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:16', '2020-03-28 18:40:16', '0');
INSERT INTO `tags_info` VALUES (282, '曲线记录中', 'curveRecording', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 322, 3, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:16', '2020-03-28 18:40:16', '0');
INSERT INTO `tags_info` VALUES (283, '预留_0', 'reserve0', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 0, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:16', '2020-03-28 18:40:16', '0');
INSERT INTO `tags_info` VALUES (284, '预留_1', 'reserve1', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 1, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:16', '2020-03-28 18:40:16', '0');
INSERT INTO `tags_info` VALUES (285, '预留_2', 'reserve2', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 2, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:16', '2020-03-28 18:40:16', '0');
INSERT INTO `tags_info` VALUES (286, '预留_3', 'reserve3', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 3, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:17', '2020-03-28 18:40:17', '0');
INSERT INTO `tags_info` VALUES (287, '预留_4', 'reserve4', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 4, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:17', '2020-03-28 18:40:17', '0');
INSERT INTO `tags_info` VALUES (288, '预留_5', 'reserve5', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 5, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:17', '2020-03-28 18:40:17', '0');
INSERT INTO `tags_info` VALUES (289, '预留_6', 'reserve6', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 6, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:17', '2020-03-28 18:40:17', '0');
INSERT INTO `tags_info` VALUES (290, '预留_7', 'reserve7', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 7, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:17', '2020-03-28 18:40:17', '0');
INSERT INTO `tags_info` VALUES (291, '预留_8', 'reserve8', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 8, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:17', '2020-03-28 18:40:17', '0');
INSERT INTO `tags_info` VALUES (292, '预留_9', 'reserve9', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 9, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:17', '2020-03-28 18:40:17', '0');
INSERT INTO `tags_info` VALUES (293, '预留_10', 'reserve10', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 10, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (294, '预留_11', 'reserve11', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 11, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (295, '预留_12', 'reserve12', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 12, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (296, '预留_13', 'reserve13', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 13, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (297, '预留_14', 'reserve14', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 14, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (298, '预留_15', 'reserve15', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 15, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (299, '预留_16', 'reserve16', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 0, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (300, '预留_17', 'reserve17', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 1, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (301, '预留_18', 'reserve18', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 2, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (302, '预留_19', 'reserve19', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 3, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (303, '预留_20', 'reserve20', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 4, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:18', '2020-03-28 18:40:18', '0');
INSERT INTO `tags_info` VALUES (304, '预留_21', 'reserve21', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 5, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (305, '预留_22', 'reserve22', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 6, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (306, '预留_23', 'reserve23', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 7, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (307, '预留_24', 'reserve24', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 8, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (308, '预留_25', 'reserve25', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 9, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (309, '预留_26', 'reserve26', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 10, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (310, '预留_27', 'reserve27', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 11, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (311, '预留_28', 'reserve28', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 12, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (312, '预留_29', 'reserve29', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 13, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (313, '预留_30', 'reserve30', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 14, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (314, '预留_31', 'reserve31', 1, 'BOOL', 'Ox84', 'DB300.DBX', 300, 324, 15, 'curve_status', 'SYS', 'SYS', '2020-03-28 18:40:19', '2020-03-28 18:40:19', '0');
INSERT INTO `tags_info` VALUES (315, 'Step[0]', 'setp0', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 328, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:34', '2020-03-28 18:46:34', '0');
INSERT INTO `tags_info` VALUES (316, '方式选择', 'programType0', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 330, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:34', '2020-03-28 18:46:34', '0');
INSERT INTO `tags_info` VALUES (317, '数值', 'programValue0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 332, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:34', '2020-03-28 18:46:34', '0');
INSERT INTO `tags_info` VALUES (318, '速度值', 'speed0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 336, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:34', '2020-03-28 18:46:34', '0');
INSERT INTO `tags_info` VALUES (319, '报警处理方式', 'alarmDealType0', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 340, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:34', '2020-03-28 18:46:34', '0');
INSERT INTO `tags_info` VALUES (320, '位置/保护压力值', 'protectPress0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 342, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:34', '2020-03-28 18:46:34', '0');
INSERT INTO `tags_info` VALUES (321, '压力/保护位置值', 'protectPosition0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 346, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:34', '2020-03-28 18:46:34', '0');
INSERT INTO `tags_info` VALUES (322, '保压时间值', 'protectTime0', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 350, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (323, 'Step[1]', 'setp1', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 354, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (324, '方式选择', 'programType1', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 356, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (325, '数值', 'programValue1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 358, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (326, '速度值', 'speed1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 362, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (327, '报警处理方式', 'alarmDealType1', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 366, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (328, '位置/保护压力值', 'protectPress1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 368, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (329, '压力/保护位置值', 'protectPosition1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 372, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:35', '2020-03-28 18:46:35', '0');
INSERT INTO `tags_info` VALUES (330, '保压时间值', 'protectTime1', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 376, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (331, 'Step[2]', 'setp2', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 380, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (332, '方式选择', 'programType2', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 382, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (333, '数值', 'programValue2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 384, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (334, '速度值', 'speed2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 388, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (335, '报警处理方式', 'alarmDealType2', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 392, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (336, '位置/保护压力值', 'protectPress2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 394, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (337, '压力/保护位置值', 'protectPosition2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 398, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (338, '保压时间值', 'protectTime2', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 402, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:36', '2020-03-28 18:46:36', '0');
INSERT INTO `tags_info` VALUES (339, 'Step[3]', 'setp3', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 406, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (340, '方式选择', 'programType3', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 408, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (341, '数值', 'programValue3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 410, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (342, '速度值', 'speed3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 414, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (343, '报警处理方式', 'alarmDealType3', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 418, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (344, '位置/保护压力值', 'protectPress3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 420, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (345, '压力/保护位置值', 'protectPosition3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 424, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (346, '保压时间值', 'protectTime3', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 428, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (347, 'Step[4]', 'setp4', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 432, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:37', '2020-03-28 18:46:37', '0');
INSERT INTO `tags_info` VALUES (348, '方式选择', 'programType4', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 434, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (349, '数值', 'programValue4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 436, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (350, '速度值', 'speed4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 440, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (351, '报警处理方式', 'alarmDealType4', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 444, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (352, '位置/保护压力值', 'protectPress4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 446, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (353, '压力/保护位置值', 'protectPosition4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 450, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (354, '保压时间值', 'protectTime4', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 454, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (355, 'Step[5]', 'setp5', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 458, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (356, '方式选择', 'programType5', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 460, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (357, '数值', 'programValue5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 462, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:38', '2020-03-28 18:46:38', '0');
INSERT INTO `tags_info` VALUES (358, '速度值', 'speed5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 466, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (359, '报警处理方式', 'alarmDealType5', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 470, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (360, '位置/保护压力值', 'protectPress5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 472, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (361, '压力/保护位置值', 'protectPosition5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 476, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (362, '保压时间值', 'protectTime5', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 480, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (363, 'Step[6]', 'setp6', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 484, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (364, '方式选择', 'programType6', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 486, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (365, '数值', 'programValue6', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 488, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (366, '速度值', 'speed6', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 492, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (367, '报警处理方式', 'alarmDealType6', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 496, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:39', '2020-03-28 18:46:39', '0');
INSERT INTO `tags_info` VALUES (368, '位置/保护压力值', 'protectPress6', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 498, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (369, '压力/保护位置值', 'protectPosition6', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 502, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (370, '保压时间值', 'protectTime6', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 506, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (371, 'Step[7]', 'setp7', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 510, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (372, '方式选择', 'programType7', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 512, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (373, '数值', 'programValue7', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 514, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (374, '速度值', 'speed7', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 518, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (375, '报警处理方式', 'alarmDealType7', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 522, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (376, '位置/保护压力值', 'protectPress7', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 524, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (377, '压力/保护位置值', 'protectPosition7', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 528, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (378, '保压时间值', 'protectTime7', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 532, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (379, 'Step[8]', 'setp8', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 536, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:40', '2020-03-28 18:46:40', '0');
INSERT INTO `tags_info` VALUES (380, '方式选择', 'programType8', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 538, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (381, '数值', 'programValue8', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 540, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (382, '速度值', 'speed8', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 544, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (383, '报警处理方式', 'alarmDealType8', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 548, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (384, '位置/保护压力值', 'protectPress8', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 550, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (385, '压力/保护位置值', 'protectPosition8', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 554, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (386, '保压时间值', 'protectTime8', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 558, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (387, 'Step[9]', 'setp9', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 562, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (388, '方式选择', 'programType9', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 564, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (389, '数值', 'programValue9', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 566, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (390, '速度值', 'speed9', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 570, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:41', '2020-03-28 18:46:41', '0');
INSERT INTO `tags_info` VALUES (391, '报警处理方式', 'alarmDealType9', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 574, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (392, '位置/保护压力值', 'protectPress9', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 576, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (393, '压力/保护位置值', 'protectPosition9', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 580, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (394, '保压时间值', 'protectTime9', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 584, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (395, 'Step[10]', 'setp10', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 588, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (396, '方式选择', 'programType10', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 590, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (397, '数值', 'programValue10', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 592, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (398, '速度值', 'speed10', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 596, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:42', '2020-03-28 18:46:42', '0');
INSERT INTO `tags_info` VALUES (399, '报警处理方式', 'alarmDealType10', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 600, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (400, '位置/保护压力值', 'protectPress10', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 602, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (401, '压力/保护位置值', 'protectPosition10', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 606, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (402, '保压时间值', 'protectTime10', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 610, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (403, 'Step[11]', 'setp11', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 614, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (404, '方式选择', 'programType11', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 616, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (405, '数值', 'programValue11', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 618, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (406, '速度值', 'speed11', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 622, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:43', '2020-03-28 18:46:43', '0');
INSERT INTO `tags_info` VALUES (407, '报警处理方式', 'alarmDealType11', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 626, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (408, '位置/保护压力值', 'protectPress11', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 628, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (409, '压力/保护位置值', 'protectPosition11', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 632, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (410, '保压时间值', 'protectTime11', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 636, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (411, 'Step[12]', 'setp12', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 640, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (412, '方式选择', 'programType12', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 642, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (413, '数值', 'programValue12', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 644, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (414, '速度值', 'speed12', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 648, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (415, '报警处理方式', 'alarmDealType12', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 652, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (416, '位置/保护压力值', 'protectPress12', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 654, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:44', '2020-03-28 18:46:44', '0');
INSERT INTO `tags_info` VALUES (417, '压力/保护位置值', 'protectPosition12', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 658, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (418, '保压时间值', 'protectTime12', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 662, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (419, 'Step[13]', 'setp13', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 666, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (420, '方式选择', 'programType13', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 668, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (421, '数值', 'programValue13', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 670, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (422, '速度值', 'speed13', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 674, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (423, '报警处理方式', 'alarmDealType13', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 678, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (424, '位置/保护压力值', 'protectPress13', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 680, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:45', '2020-03-28 18:46:45', '0');
INSERT INTO `tags_info` VALUES (425, '压力/保护位置值', 'protectPosition13', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 684, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (426, '保压时间值', 'protectTime13', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 688, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (427, 'Step[14]', 'setp14', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 692, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (428, '方式选择', 'programType14', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 694, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (429, '数值', 'programValue14', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 696, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (430, '速度值', 'speed14', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 700, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (431, '报警处理方式', 'alarmDealType14', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 704, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (432, '位置/保护压力值', 'protectPress14', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 706, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (433, '压力/保护位置值', 'protectPosition14', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 710, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (434, '保压时间值', 'protectTime14', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 714, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (435, 'Step[15]', 'setp15', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 718, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:46', '2020-03-28 18:46:46', '0');
INSERT INTO `tags_info` VALUES (436, '方式选择', 'programType15', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 720, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:47', '2020-03-28 18:46:47', '0');
INSERT INTO `tags_info` VALUES (437, '数值', 'programValue15', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 722, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:47', '2020-03-28 18:46:47', '0');
INSERT INTO `tags_info` VALUES (438, '速度值', 'speed15', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 726, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:47', '2020-03-28 18:46:47', '0');
INSERT INTO `tags_info` VALUES (439, '报警处理方式', 'alarmDealType15', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 730, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:47', '2020-03-28 18:46:47', '0');
INSERT INTO `tags_info` VALUES (440, '位置/保护压力值', 'protectPress15', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 732, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:47', '2020-03-28 18:46:47', '0');
INSERT INTO `tags_info` VALUES (441, '压力/保护位置值', 'protectPosition15', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 736, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:47', '2020-03-28 18:46:47', '0');
INSERT INTO `tags_info` VALUES (442, '保压时间值', 'protectTime15', 13, 'TIME', 'Ox84', 'DB300.DBD', 300, 740, 0, 'curve_program', 'SYS', 'SYS', '2020-03-28 18:46:47', '2020-03-28 18:46:47', '0');
INSERT INTO `tags_info` VALUES (443, '公差窗口[0]', 'errand0', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 744, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (444, '序号', 'errand0', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 744, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (445, '窗口类型', 'errandType0', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 746, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (446, '位置下限', 'positionMin0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 748, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (447, '位置上限', 'positionMax0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 752, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (448, '压力下限', 'pressMin0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 756, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (449, '压力上限', 'pressMax0', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 760, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (450, '报警反应', 'alarmOperation0', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 764, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (451, '公差窗口[1]', 'errand1', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 766, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (452, '序号', 'errand1', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 766, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:53', '2020-03-28 18:54:53', '0');
INSERT INTO `tags_info` VALUES (453, '窗口类型', 'errandType1', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 768, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (454, '位置下限', 'positionMin1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 770, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (455, '位置上限', 'positionMax1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 774, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (456, '压力下限', 'pressMin1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 778, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (457, '压力上限', 'pressMax1', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 782, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (458, '报警反应', 'alarmOperation1', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 786, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (459, '公差窗口[2]', 'errand2', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 788, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (460, '序号', 'errand2', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 788, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (461, '窗口类型', 'errandType2', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 790, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (462, '位置下限', 'positionMin2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 792, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (463, '位置上限', 'positionMax2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 796, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:54', '2020-03-28 18:54:54', '0');
INSERT INTO `tags_info` VALUES (464, '压力下限', 'pressMin2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 800, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (465, '压力上限', 'pressMax2', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 804, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (466, '报警反应', 'alarmOperation2', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 808, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (467, '公差窗口[3]', 'errand3', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 810, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (468, '序号', 'errand3', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 810, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (469, '窗口类型', 'errandType3', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 812, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (470, '位置下限', 'positionMin3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 814, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (471, '位置上限', 'positionMax3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 818, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:55', '2020-03-28 18:54:55', '0');
INSERT INTO `tags_info` VALUES (472, '压力下限', 'pressMin3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 822, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:56', '2020-03-28 18:54:56', '0');
INSERT INTO `tags_info` VALUES (473, '压力上限', 'pressMax3', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 826, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:56', '2020-03-28 18:54:56', '0');
INSERT INTO `tags_info` VALUES (474, '报警反应', 'alarmOperation3', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 830, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:56', '2020-03-28 18:54:56', '0');
INSERT INTO `tags_info` VALUES (475, '公差窗口[4]', 'errand4', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 832, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:56', '2020-03-28 18:54:56', '0');
INSERT INTO `tags_info` VALUES (476, '序号', 'errand4', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 832, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:56', '2020-03-28 18:54:56', '0');
INSERT INTO `tags_info` VALUES (477, '窗口类型', 'errandType4', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 834, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:56', '2020-03-28 18:54:56', '0');
INSERT INTO `tags_info` VALUES (478, '位置下限', 'positionMin4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 836, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (479, '位置上限', 'positionMax4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 840, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (480, '压力下限', 'pressMin4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 844, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (481, '压力上限', 'pressMax4', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 848, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (482, '报警反应', 'alarmOperation4', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 852, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (483, '公差窗口[5]', 'errand5', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 854, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (484, '序号', 'errand5', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 854, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (485, '窗口类型', 'errandType5', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 856, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (486, '位置下限', 'positionMin5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 858, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:57', '2020-03-28 18:54:57', '0');
INSERT INTO `tags_info` VALUES (487, '位置上限', 'positionMax5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 862, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:58', '2020-03-28 18:54:58', '0');
INSERT INTO `tags_info` VALUES (488, '压力下限', 'pressMin5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 866, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:58', '2020-03-28 18:54:58', '0');
INSERT INTO `tags_info` VALUES (489, '压力上限', 'pressMax5', 11, 'REAL', 'Ox84', 'DB300.DBD', 300, 870, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:58', '2020-03-28 18:54:58', '0');
INSERT INTO `tags_info` VALUES (490, '报警反应', 'alarmOperation5', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 874, 0, 'curve_errand', 'SYS', 'SYS', '2020-03-28 18:54:58', '2020-03-28 18:54:58', '0');
INSERT INTO `tags_info` VALUES (491, '零件号', 'productNo', 7, 'INT', 'Ox84', 'DB300.DBW', 300, 0, 0, 'curve_data', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');
INSERT INTO `tags_info` VALUES (492, '输入2', 'shuru21', 11, 'BOOL', 'Ox84', 'DB300.DBX', 300, 0, 1, 'test', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '0');
INSERT INTO `tags_info` VALUES (493, '数值1', 'value-11', 11, 'BOOL', 'Ox84', 'DB300.DBX', 300, 0, 4, 'test', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '0');
INSERT INTO `tags_info` VALUES (494, '数值4', 'value-4', 11, 'INT', 'Ox84', 'DB300.DBW', 300, 1, 4, 'test', 'SYS', 'SYS', '2020-03-28 18:36:17', '2020-03-28 18:36:17', '1');
INSERT INTO `tags_info` VALUES (495, '曲线记录开始', 'curveRecording', 7, 'BOOL', 'Ox84', 'DB300.DBX', 300, 322, 4, 'curve_data', 'SYS', 'SYS', '2020-03-28 17:20:18', '2020-03-28 17:20:18', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `USER_PASSWORD` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `ROLE_ID` bigint(0) DEFAULT NULL COMMENT '权限ID',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1, 'chensubei', 'chensubei', '2020-03-26 09:45:40', '2020-03-25 18:34:50', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户组 0:默认,1操作者,2工艺人员,3维修人员,4管理员',
  `ROLE_DESCRIPTION` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户组',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0否1是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 'admin', '管理员,全权限', 'chensubei', 'chensubei', '2020-03-25 19:08:23', '2020-03-27 21:37:49', '0');
INSERT INTO `user_role` VALUES (2, 'operator', '操作员', 'chensubei', 'chensubei', '2020-03-25 19:08:23', '2020-03-25 19:08:23', '0');
INSERT INTO `user_role` VALUES (3, 'worker', '工艺人员', 'chensubei', 'chensubei', '2020-03-25 19:08:23', '2020-03-25 19:08:23', '0');
INSERT INTO `user_role` VALUES (4, 'matin', '维修人员', 'chensubei', 'chensubei', '2020-03-25 19:08:23', '2020-03-25 19:08:23', '0');

SET FOREIGN_KEY_CHECKS = 1;
