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

 Date: 25/04/2020 20:44:37
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
-- Table structure for his_alarm_info
-- ----------------------------
DROP TABLE IF EXISTS `his_alarm_info`;
CREATE TABLE `his_alarm_info`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `ALARM_ID` bigint(0) NOT NULL COMMENT '警报信息ID',
  `ALARM_START_TIME` datetime(0) DEFAULT NULL COMMENT '警报开始时间',
  `ALARM_STOP_TIME` datetime(0) DEFAULT NULL COMMENT '警报结束时间',
  `ALARM_INFO` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '警报内容 ',
  `ALARM_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '警报类型 E：ERROR，W:WARNING,I:INFO',
  `ALARM_STATUS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '警报状态 0历史 1当前',
  `ALARM_CFM_STATUS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '警报确认状态 0已确认 1待确认 ',
  `CREATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标志(0否1是)',
  `TRIGGER_DB` int(0) DEFAULT NULL COMMENT '触发DB 报警是由哪个地址触发的',
  `TRIGGER_OFFSET` int(0) DEFAULT NULL COMMENT '触发DB偏移 报警是由哪个地址触发的',
  `TRIGGER_BIT` int(0) DEFAULT NULL COMMENT '触发位 报警是由哪个地址触发的',
  `ALARM_GROUP` int(0) DEFAULT NULL COMMENT '报警组 0--100',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '历史报警信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pressure_curve
-- ----------------------------
DROP TABLE IF EXISTS `pressure_curve`;
CREATE TABLE `pressure_curve`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `RECORD_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '位置/压力曲线ID 压装数据表RECORD_ID',
  `PRODUCT_ID` int(0) DEFAULT NULL COMMENT '产品ID',
  `RECORD_NO` bigint(0) DEFAULT NULL COMMENT '位置点序号 采集的序列号。1-N递增',
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
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `IDX_RECORD_ID`(`RECORD_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25040 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '压力曲线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pressure_data
-- ----------------------------
DROP TABLE IF EXISTS `pressure_data`;
CREATE TABLE `pressure_data`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` bigint(0) DEFAULT NULL COMMENT '产品表ID',
  `RECORD_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '位置/压力曲线ID',
  `PRODUCT_NO` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品二维码',
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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品压装数据表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 496 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'IO变量表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

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

SET FOREIGN_KEY_CHECKS = 1;
