-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2019 at 09:30 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Table structure for table `claim`
--

CREATE TABLE `claim` (
  `CL_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการเคลม',
  `CL_DATE` date NOT NULL COMMENT 'วันที่เคลม',
  `CL_REC_DATE` date NOT NULL COMMENT 'วันรับของเคลม',
  `CL_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการเคลม',
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสพนักงาน',
  `PO_ID` varchar(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการสั่งสินค้า',
  `CL_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `claim`
--

INSERT INTO `claim` (`CL_ID`, `CL_DATE`, `CL_REC_DATE`, `CL_STATUS`, `EMP_ID`, `PO_ID`, `CL_DEL`) VALUES
('C001', '2561-12-27', '2561-12-27', 'N', 'E001', 'O008', 'N'),
('C002', '2561-12-27', '2561-12-27', 'N', 'E001', 'O008', 'Y'),
('C003', '2561-12-27', '2561-12-27', 'N', 'E001', 'O008', 'Y'),
('C004', '2561-12-27', '2561-12-27', 'N', 'E001', 'O008', 'Y'),
('C005', '2561-12-28', '2561-12-28', 'N', 'E001', 'O008', 'Y'),
('C006', '2561-12-28', '2561-12-28', 'N', 'E001', 'O008', 'Y'),
('C007', '2561-12-28', '2561-12-28', 'Y', 'E001', 'O008', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `claim_list`
--

CREATE TABLE `claim_list` (
  `C_L_NUMBER` int(4) NOT NULL COMMENT 'รายการเคลมสินค้า',
  `CL_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการเคลม',
  `PO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการสั่งสินค้า',
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสสินค้า',
  `STOCK_NUMBER` int(4) NOT NULL COMMENT 'รหัสสต๊อกสินค้า',
  `C_L_UNITS` decimal(7,3) NOT NULL COMMENT 'จำนวนที่ส่งเคลม',
  `C_L_CAUSE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'สาเหตุการเคลม',
  `C_L_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการเคลม',
  `C_L_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `claim_list`
--

INSERT INTO `claim_list` (`C_L_NUMBER`, `CL_ID`, `PO_ID`, `PRO_ID`, `STOCK_NUMBER`, `C_L_UNITS`, `C_L_CAUSE`, `C_L_STATUS`, `C_L_DEL`) VALUES
(1, 'C001', 'O008', 'P103', 4, '15.000', '12121', 'N', 'N'),
(2, 'C002', 'O008', 'P104', 5, '15.000', '111', 'N', 'Y'),
(3, 'C003', 'O008', 'P104', 5, '15.000', '11', 'N', 'Y'),
(4, 'C004', 'O008', 'P104', 5, '15.000', '1', 'N', 'Y'),
(5, 'C005', 'O008', 'P104', 5, '15.000', '12121', 'N', 'Y'),
(6, 'C006', 'O008', 'P104', 5, '15.000', '121', 'N', 'Y'),
(7, 'C007', 'O008', 'P104', 5, '15.000', '12121', 'Y', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `claim_receive`
--

CREATE TABLE `claim_receive` (
  `CR_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `CL_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `CR_DATE` date NOT NULL,
  `CR_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `CR_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `claim_receive`
--

INSERT INTO `claim_receive` (`CR_ID`, `EMP_ID`, `CL_ID`, `CR_DATE`, `CR_STATUS`, `CR_DEL`) VALUES
('c001', 'E001', 'C001', '2561-12-27', 'N', 'N'),
('c002', 'E001', 'C002', '2561-12-27', 'N', 'Y'),
('c003', 'E001', 'C003', '2561-12-27', 'N', 'Y'),
('c004', 'E001', 'C004', '2561-12-27', 'N', 'Y'),
('c005', 'E001', 'C005', '2561-12-28', 'N', 'Y'),
('c006', 'E001', 'C006', '2561-12-28', 'N', 'Y'),
('c007', 'E001', 'C007', '2561-12-28', 'Y', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `claim_rec_list`
--

CREATE TABLE `claim_rec_list` (
  `CRL_NUMBER` int(4) NOT NULL COMMENT 'รายการรับเคลมสินค้า',
  `CR_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสรับเคลมสินค้า',
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสสินค้า',
  `CRL_UNITS` decimal(7,3) NOT NULL COMMENT 'จำนวนที่รับเคลม',
  `CRL_CURRENT` decimal(7,3) NOT NULL COMMENT 'จำนวนที่รับเคลมไปแล้ว',
  `CRL_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการรับเคลม',
  `CRL_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `claim_rec_list`
--

INSERT INTO `claim_rec_list` (`CRL_NUMBER`, `CR_ID`, `PRO_ID`, `CRL_UNITS`, `CRL_CURRENT`, `CRL_STATUS`, `CRL_DEL`) VALUES
(1, 'c001', 'P103', '15.000', '0.000', 'N', 'N'),
(2, 'c002', 'P104', '15.000', '0.000', 'N', 'Y'),
(3, 'c003', 'P104', '15.000', '0.000', 'N', 'Y'),
(4, 'c004', 'P104', '15.000', '0.000', 'N', 'Y'),
(5, 'c005', 'P104', '15.000', '0.000', 'N', 'Y'),
(6, 'c006', 'P104', '15.000', '0.000', 'N', 'Y'),
(7, 'c007', 'P104', '15.000', '15.000', 'Y', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสพนักงาน',
  `POS_ID` char(2) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสตำแหน่ง',
  `EMP_PASS` varchar(25) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสผ่าน',
  `EMP_FNAME` varchar(35) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อพนักงาน',
  `EMP_LNAME` varchar(35) COLLATE utf8_unicode_ci NOT NULL COMMENT 'นามสกุลพนักงาน',
  `EMP_PHONE` char(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'เบอร์โทรพนักงาน',
  `EMP_ADDRESS` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ที่อยู่พนักงาน',
  `EMP_EMAIL` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'อีเมลพนักงาน',
  `EMP_SALARY` int(2) NOT NULL DEFAULT '40' COMMENT 'รายได้ต่อชั่วโมง',
  `EMP_START` date DEFAULT NULL COMMENT 'วันเริ่มทำงาน',
  `EMP_AGE` int(2) NOT NULL COMMENT 'อายุ',
  `EMP_GENDER` varchar(6) COLLATE utf8_unicode_ci NOT NULL COMMENT 'เพศ',
  `EMP_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EMP_ID`, `POS_ID`, `EMP_PASS`, `EMP_FNAME`, `EMP_LNAME`, `EMP_PHONE`, `EMP_ADDRESS`, `EMP_EMAIL`, `EMP_SALARY`, `EMP_START`, `EMP_AGE`, `EMP_GENDER`, `EMP_DEL`) VALUES
('E001', '00', 'ADMIN', 'ADMIN', 'NAJA', '0899994939', 'DULCE-COMPANY', 'ADMIN@DULCE.COM', 40, '2018-11-04', 25, 'Male', 'N'),
('E002', '02', '1234', 'Chaichana', 'Sudjairak', '0853997206', '159/77 Sriracha, Chonburi, Thailand, 20110', '60160055@go.buu.ac.th', 50, '2018-11-08', 20, 'Male', 'N'),
('E003', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E004', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E005', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E006', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E007', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E008', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E009', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E010', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E011', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E012', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E013', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E014', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E015', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E016', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E017', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E018', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E019', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E020', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E021', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E022', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E023', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'Y'),
('E024', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'N'),
('E025', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 50, '2018-11-09', 13, 'Male', 'N'),
('E026', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'N'),
('E027', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'N'),
('E028', '05', '1234', '1', '2', '1', 'settest', 'setest@com', 0, '2018-11-09', 13, 'Male', 'N'),
('E029', '02', '1234', 'ไก่จ๋า', 'ได้ยินไหมว่าเสียงใคร', 'sagsa', 'gasgasga', 'gasgas', 40, '2018-11-19', 50, 'Female', 'N'),
('E030', '05', '1234', 'adsgad', 'gdagadg', 'dgadgad', 'dgdagadh', 'gdagad', 40, '2018-11-19', 25, 'Male', 'N'),
('E031', '09', 'fsaf', 'gsagsa', 'gsagsa', 'safsa', 'fsafasf', 'fasfasf', 40, '2018-11-19', 50, 'Male', 'N'),
('E032', '02', 'safas', 'asfsafsa', 'fsafasf', 'adgda', 'adgadgad', 'gadgadg', 40, '2018-11-19', 20, 'Male', 'N'),
('E033', '02', 'sagsag', 'sagsag', 'sagas', 'sasag', 'sagasg', 'asgsag', 40, '2018-11-19', 58, 'Female', 'N'),
('E034', '02', 'safsa', 'asgsag', 'sagsag', 'sdbs', 'fshhfsdhsf', 'bfgfah', 40, '2018-11-19', 60, 'Male', 'N'),
('E035', '02', 'asfsa', 'safsa', 'fsafsa', 'afasg', 'gsagas', 'asgas', 40, '2018-11-19', 58, 'Female', 'N'),
('E036', '03', 'asfsaf', 'asfsa', 'fsafasf', 'safsag', 'tghsfhfs', 'sagsag', 40, '2018-11-19', 50, 'Female', 'N'),
('E037', '04', 'asfas', 'fsafas', 'fsafas', 'dag', 'adgadg', 'dagadg', 40, '2018-11-19', 25, 'Male', 'Y'),
('E038', '02', 'fsaf', 'safsafas', 'asfsaf', 'hfgha', 'fhsfhsfh', 'sfhha', 40, '2018-11-19', 50, 'Male', 'N'),
('E039', '02', 'SAFSAF', 'ASFAS', 'SAFASF', 'SAGFSAF', 'SAGASG', 'SAGASG', 40, '2018-11-19', 50, 'Female', 'N'),
('E040', '02', '124', 'MR.K', 'ด่วย', '0123456789', 'asgsaga', 'ฟหเฟหเ@gami.com', 40, '2018-11-19', 20, 'Male', 'Y'),
('E041', '04', '1234', 'หัวเห็ด', 'ผจญภัย', '0123456789', 'asfsagfsagsagsa', 'dsahadh@ghaga.com', 44, '2018-11-19', 20, 'Male', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `emp_position`
--

CREATE TABLE `emp_position` (
  `POS_ID` char(2) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสตำแหน่ง',
  `POS_NAME` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อตำแหน่ง',
  `POS_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `emp_position`
--

INSERT INTO `emp_position` (`POS_ID`, `POS_NAME`, `POS_DEL`) VALUES
('00', 'Owner', 'N'),
('01', 'Manager', 'N'),
('02', 'Headchef', 'N'),
('03', 'Chef', 'N'),
('04', 'Cashier', 'N'),
('05', 'Waiter', 'N'),
('09', 'Apprentice', 'N'),
('10', 'Wait A Minutez', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `ingredient`
--

CREATE TABLE `ingredient` (
  `ING_NUMBER` int(4) NOT NULL,
  `MENU_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `ING_UNITS` decimal(7,3) NOT NULL,
  `ING_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ingredient`
--

INSERT INTO `ingredient` (`ING_NUMBER`, `MENU_ID`, `PRO_ID`, `ING_UNITS`, `ING_DEL`) VALUES
(1, 'M001', 'P101', '0.010', 'N'),
(2, 'M001', 'P151', '0.100', 'Y'),
(3, 'M002', 'P101', '0.015', 'N'),
(4, 'M003', 'P103', '0.050', 'N'),
(5, 'M004', 'P103', '0.050', 'N'),
(6, 'M001', 'P006', '0.005', 'Y'),
(7, 'M002', 'P151', '0.005', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `MENU_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสเมนู',
  `MENU_NAME` varchar(25) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อเมนู',
  `MENU_PRICE` int(4) NOT NULL COMMENT 'ราคา',
  `M_T_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสหมวดหมู่',
  `MENU_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`MENU_ID`, `MENU_NAME`, `MENU_PRICE`, `M_T_ID`, `MENU_DEL`) VALUES
('M001', 'กาแฟเย็น', 35, '01', 'N'),
('M002', 'กาแฟร้อน', 30, '00', 'N'),
('M003', 'ชาเขียวปั่น', 40, '02', 'N'),
('M004', 'ชาเขียวเย็น', 35, '01', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `menu_backup`
--

CREATE TABLE `menu_backup` (
  `MENU_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสเมนู',
  `MENU_NAME` varchar(25) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อเมนู',
  `MENU_PRICE` int(4) NOT NULL COMMENT 'ราคา',
  `M_T_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสหมวดหมู่',
  `MENU_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `menu_backup`
--

INSERT INTO `menu_backup` (`MENU_ID`, `MENU_NAME`, `MENU_PRICE`, `M_T_ID`, `MENU_DEL`) VALUES
('0001', 'Espresso', 35, '00', 'N'),
('0002', 'Cappuccino', 35, '00', 'N'),
('0003', 'Americano', 35, '00', 'N'),
('0004', 'Latte', 35, '00', 'N'),
('0005', 'Mocha', 35, '00', 'N'),
('0026', 'Milk', 35, '00', 'N'),
('0051', 'Espresso', 45, '01', 'N'),
('0052', 'Cappuccino', 45, '01', 'N'),
('0053', 'Americano', 45, '01', 'N'),
('0054', 'Latte', 45, '01', 'N'),
('0055', 'Mocha', 45, '01', 'N'),
('0056', 'Macchiato', 45, '01', 'N'),
('0076', 'Milk', 45, '01', 'N'),
('0077', 'Pink Milk', 45, '01', 'N'),
('0079', 'Thai Tea', 45, '01', 'N'),
('0080', 'Green Tea', 45, '01', 'N'),
('0081', 'Jasmine Tea', 45, '01', 'N'),
('0083', 'Rose Tea', 45, '01', 'N'),
('0087', 'Chocolate', 45, '01', 'N'),
('0089', 'Red Lime Soda', 45, '01', 'N'),
('0101', 'Espresso', 55, '02', 'N'),
('0102', 'Cappuccino', 55, '02', 'N'),
('0103', 'Americano', 55, '02', 'N'),
('0104', 'Latte', 55, '02', 'N'),
('0105', 'Mocha', 55, '02', 'N'),
('0126', 'Milk', 55, '02', 'N'),
('0127', 'Pink Milk', 55, '02', 'N'),
('0129', 'Thai Tea', 55, '02', 'N'),
('0130', 'Green Tea', 55, '02', 'N'),
('0132', 'Chocolate', 55, '02', 'N'),
('0134', 'Strawberry Yogurt', 55, '02', 'N'),
('0136', 'Apple Yogurt', 55, '02', 'N'),
('0139', 'Melon Milk', 55, '02', 'N'),
('0140', 'Lime', 55, '02', 'N'),
('0142', 'Mixberry Yogurt', 55, '02', 'N'),
('0201', 'Crape Cake', 75, '03', 'N'),
('0202', 'Vanilla Cake', 65, '03', 'N'),
('0203', 'Chocolate Cake', 65, '03', 'N'),
('0204', 'Matcha Cake', 75, '03', 'N'),
('0205', 'Strawberry Cake', 75, '03', 'N'),
('0251', 'Corn Pie', 35, '03', 'N'),
('0252', 'Pineapple Pie', 35, '03', 'N'),
('0253', 'Coconut Pie', 35, '03', 'N'),
('0254', 'Chicken Pie', 35, '03', 'N'),
('0301', 'Cookies', 50, '03', 'N'),
('0302', 'Honey Toast', 105, '03', 'N'),
('0303', 'Strawberry Toast', 25, '03', 'N'),
('0304', 'Chocolate Toast', 25, '03', 'N'),
('0306', 'Cream Toast', 25, '03', 'N'),
('0307', 'Waffle', 105, '03', 'N'),
('0326', 'Vanilla', 15, '07', 'N'),
('0327', 'Chocolate', 15, '07', 'N'),
('0328', 'Matcha', 15, '07', 'N'),
('0329', 'Thai Tea', 15, '07', 'N'),
('0330', 'Coffee', 15, '07', 'N'),
('0331', 'Strawberry', 15, '07', 'N'),
('0332', 'Milk', 15, '07', 'N'),
('0333', 'Chocolate chips', 15, '07', 'N'),
('0351', 'French Fries', 50, '05', 'N'),
('0352', 'Chicken Wings', 75, '05', 'N'),
('0353', 'Onion Rings', 50, '05', 'N'),
('0354', 'Fishstick', 75, '05', 'N'),
('0355', 'ชาไทยไม่ใช่ชาลาว', 100, '00', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `menu_type`
--

CREATE TABLE `menu_type` (
  `M_T_ID` char(2) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสหมวดหมู่',
  `M_T_NAME` varchar(25) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อหมวดหมู่',
  `M_T_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `menu_type`
--

INSERT INTO `menu_type` (`M_T_ID`, `M_T_NAME`, `M_T_DEL`) VALUES
('00', 'ร้อน', 'N'),
('01', 'เย็น', 'N'),
('02', 'ปั่น', 'N'),
('03', 'ของหวาน', 'N'),
('04', 'จานหลัก', 'N'),
('05', 'ขนมขบเคี้ยว', 'N'),
('06', 'ของโรยหน้า', 'N'),
('07', 'ไอศกรีม', 'N'),
('08', 'FAPZ', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `ordering`
--

CREATE TABLE `ordering` (
  `ORD_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `T_ID` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `ORD_DATE` datetime NOT NULL,
  `ORD_TOTAL` decimal(9,2) NOT NULL,
  `ORD_PAYTYPE` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORD_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ordering`
--

INSERT INTO `ordering` (`ORD_ID`, `EMP_ID`, `T_ID`, `ORD_DATE`, `ORD_TOTAL`, `ORD_PAYTYPE`, `ORD_DEL`) VALUES
('O001', 'E001', '01', '2019-01-20 16:13:22', '30.00', '', 'N'),
('O002', 'E001', '02', '2019-01-21 12:00:44', '65.00', 'บัตรเครดิต', 'N'),
('O003', 'E001', '02', '2019-01-22 00:45:07', '35.00', 'บัตรเครดิต', 'N'),
('O004', 'E001', '02', '2019-01-22 00:46:53', '35.00', 'บัตรเครดิต', 'N'),
('O005', 'E001', '02', '2019-01-22 00:48:30', '35.00', 'บัตรเครดิต', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `order_menu_list`
--

CREATE TABLE `order_menu_list` (
  `OM_ID` int(4) NOT NULL,
  `ORD_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `MENU_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `OM_UNITS` int(4) NOT NULL,
  `OM_PRICE` decimal(9,2) NOT NULL,
  `OM_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `OM_PROMOTION` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OM_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `order_menu_list`
--

INSERT INTO `order_menu_list` (`OM_ID`, `ORD_ID`, `MENU_ID`, `OM_UNITS`, `OM_PRICE`, `OM_STATUS`, `OM_PROMOTION`, `OM_DEL`) VALUES
(1, 'O001', 'M002', 1, '30.00', 'Y', 'กาแฟร้อน 1 แถม 1', 'N'),
(2, 'O001', 'M002', 1, '0.00', 'N', 'กาแฟร้อน 1 แถม 1', 'N'),
(3, 'O001', 'M001', 1, '35.00', 'N', 'null', 'N'),
(4, 'O002', 'M001', 1, '35.00', 'Y', 'null', 'N'),
(5, 'O002', 'M002', 1, '30.00', 'Y', 'null', 'N'),
(6, 'O003', 'M001', 1, '35.00', 'Y', 'null', 'N'),
(7, 'O004', 'M001', 1, '35.00', 'Y', 'null', 'N'),
(8, 'O005', 'M001', 1, '35.00', 'Y', 'null', 'N'),
(9, 'O005', 'M001', 1, '35.00', 'Y', 'null', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `order_payment`
--

CREATE TABLE `order_payment` (
  `OP_NUMBER` int(4) NOT NULL,
  `PO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `V_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `OP_DATE` date NOT NULL,
  `OP_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `OP_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `order_payment`
--

INSERT INTO `order_payment` (`OP_NUMBER`, `PO_ID`, `V_ID`, `OP_DATE`, `OP_STATUS`, `OP_DEL`) VALUES
(1, 'O001', 'V001', '2561-11-24', 'N', 'N'),
(2, 'O002', 'V002', '2561-11-24', 'N', 'N'),
(3, 'O003', 'V001', '2561-11-24', 'N', 'Y'),
(4, 'O004', 'V001', '2561-11-28', 'N', 'N'),
(5, 'O005', 'V001', '2561-12-13', 'N', 'N'),
(6, 'O006', 'V002', '2561-12-13', 'Y', 'N'),
(7, 'O008', 'V001', '2018-12-16', 'Y', 'N'),
(8, 'O009', 'V003', '2018-12-16', 'Y', 'N'),
(9, 'O010', 'V001', '2019-01-17', 'N', 'N'),
(10, 'O011', 'V001', '2019-01-01', 'Y', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `order_promotion`
--

CREATE TABLE `order_promotion` (
  `O_PN_NUMBER` int(4) NOT NULL,
  `ORD_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `PN_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `O_PN_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสสินค้า',
  `V_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสผู้จัดจำหน่าย',
  `PRO_NAME` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อสินค้า',
  `PRO_PRICE` decimal(5,0) NOT NULL COMMENT 'ราคาสินค้า(ต้นทุนต่อหน่วย)',
  `PRO_UNITS` decimal(7,3) NOT NULL COMMENT 'จำนวนรวมทั้งหมดทีมีอยู่',
  `PRO_UNITS_TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL COMMENT 'หน่วยของวัตถุดิบ',
  `PRO_MIN` int(3) NOT NULL COMMENT 'จำนวนน้อยสุดที่ต้องมีคงคลัง',
  `PRO_LIST_ID` char(2) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสประเภทของสินค้า',
  `PRO_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PRO_ID`, `V_ID`, `PRO_NAME`, `PRO_PRICE`, `PRO_UNITS`, `PRO_UNITS_TYPE`, `PRO_MIN`, `PRO_LIST_ID`, `PRO_DEL`) VALUES
('P001', 'V002', 'เครื่องชงกาแฟ', '29999', '0.000', '11', 1, '02', 'N'),
('P002', 'V002', 'เครื่องตีครีม', '2499', '0.000', '11', 1, '02', 'N'),
('P003', 'V002', 'เครื่องบดเมล็ดกาแฟ', '8300', '0.000', '11', 1, '02', 'N'),
('P004', 'V002', 'เหยือกสตรีมนม', '289', '0.000', '10', 1, '02', 'N'),
('P005', 'V002', 'เครื่องขึ้นครีม', '1290', '0.000', '11', 1, '02', 'N'),
('P006', 'V002', 'ผงโกโก้โรยหน้า', '149', '0.000', '01', 1, '04', 'N'),
('P007', 'V002', 'เครื่องทำน้ำแข็ง', '40000', '0.000', '11', 1, '02', 'N'),
('P008', 'V002', 'แก้วกระดาษ', '2', '0.000', '03', 1, '03', 'N'),
('P009', 'V003', 'ช้อนชา', '15', '0.000', '10', 10, '03', 'N'),
('P010', 'V005', 'ส้อมเค้ก', '1', '0.000', '10', 30, '03', 'N'),
('P011', 'V002', 'ช้อนตักน้ำแข็ง', '10', '0.000', '10', 5, '03', 'N'),
('P012', 'V002', 'ช้อนคนกาแฟ', '2', '0.000', '10', 30, '03', 'N'),
('P013', 'V002', 'แก้วสแตนเลส ไซส์ S', '40', '0.000', '03', 5, '03', 'N'),
('P014', 'V002', 'แก้วพลาสติก ไซส์ S', '1', '0.000', '03', 50, '03', 'N'),
('P015', 'V002', 'แก้วสแตนเลส ไซส์ M', '45', '0.000', '03', 5, '03', 'N'),
('P016', 'V002', 'แก้วพลาสติก ไซส์ M', '2', '0.000', '03', 50, '03', 'N'),
('P017', 'V002', 'แก้วสแตนเลส ไซส์ L', '60', '0.000', '03', 5, '03', 'N'),
('P018', 'V002', 'แก้วพลาสติก ไซส์ L', '2', '0.000', '03', 50, '03', 'N'),
('P019', 'V002', 'ถ้วยตวง', '100', '0.000', '10', 1, '01', 'N'),
('P020', 'V002', 'เทอร์มอมิเตอร์', '500', '0.000', '10', 1, '01', 'N'),
('P021', 'V002', 'ผ้ากันเปื้อน', '100', '0.000', '10', 3, '01', 'N'),
('P022', 'V002', 'หัวเชื้อทำความสะอาด', '560', '0.000', '06', 1, '01', 'N'),
('P023', 'V002', 'น้ำยาทำความสะอาดเครื่องบด', '1290', '0.000', '06', 1, '01', 'N'),
('P101', 'V001', 'เมล็ดกาแฟ', '450', '2.930', '01', 1, '04', 'N'),
('P102', 'V001', 'ผงโกโก้', '190', '0.000', '01', 1, '04', 'N'),
('P103', 'V001', 'ผงชาเขียว', '190', '0.000', '01', 1, '04', 'N'),
('P104', 'V001', 'ผงเลม่อน', '290', '15.000', '01', 1, '04', 'N'),
('P105', 'V001', 'ผงชาไทย', '210', '0.000', '01', 1, '04', 'N'),
('P108', 'V001', 'โยเกิร์ตรสธรรมชาติ', '15', '0.000', '03', 12, '04', 'N'),
('P109', 'V001', 'เฮลซ์บลูบอยกลิ่นสละ', '43', '0.000', '06', 2, '04', 'N'),
('P110', 'V001', 'เฮลซ์บลูบอยกลิ่นครีมโซดา', '43', '0.000', '06', 2, '04', 'N'),
('P111', 'V001', 'ผงชามะลิ', '199', '0.000', '01', 1, '04', 'N'),
('P112', 'V001', 'ผงชากุหลาบ', '250', '0.000', '01', 1, '04', 'N'),
('P115', 'V001', 'น้ำเชื่อมกลิ่นบลูฮาวาย', '200', '0.000', '09', 1, '04', 'N'),
('P116', 'V001', 'น้ำเชื่อมคาราเมล', '180', '0.000', '09', 1, '04', 'N'),
('P117', 'V001', 'น้ำเชื่อมสตรอเบอรี่', '180', '0.000', '09', 1, '04', 'N'),
('P119', 'V001', 'น้ำเชื่อม', '60', '0.000', '09', 1, '04', 'N'),
('P122', 'V001', 'น้ำเมล่อนเข้มข้น', '200', '0.000', '09', 1, '04', 'N'),
('P123', 'V001', 'น้ำแอปเปิ้ลเข้มข้น', '200', '0.000', '09', 1, '04', 'N'),
('P124', 'V001', 'น้ำมิกซ์เบอรี่เข้มข้น', '200', '0.000', '09', 1, '04', 'N'),
('P125', 'V001', 'น้ำสตอเบอรี่เข้มข้น', '200', '0.000', '09', 1, '04', 'N'),
('P126', 'V001', 'โยเกิร์ตกลิ่นสตรอเบอรี่', '15', '0.000', '03', 12, '04', 'N'),
('P127', 'V001', 'โยเกิร์ตกลิ่นบลูเบอรี่', '14', '0.000', '03', 12, '04', 'N'),
('P128', 'V001', 'โยเกิร์ตกลิ่นมิกซ์เบอรี่', '14', '0.000', '03', 12, '04', 'N'),
('P151', 'V001', 'นมจืด', '120', '0.990', '09', 2, '04', 'N'),
('P152', 'V001', 'นมข้นหวาน', '150', '0.000', '09', 1, '04', 'N'),
('P153', 'V001', 'ขนมปัง', '25', '15.000', '10', 3, '04', 'N'),
('P154', 'V001', 'น้ำผึ้งแท้', '150', '0.000', '06', 1, '04', 'N'),
('P155', 'V001', 'เฮฟวี่ครีม', '150', '0.000', '04', 2, '04', 'N'),
('P156', 'V001', 'ช้อคโกแลต', '45', '0.000', '12', 5, '04', 'N'),
('P201', 'V003', 'ไอศกรีมรสนม', '239', '0.000', '04', 1, '04', 'N'),
('P202', 'V003', 'ไอศกรีมรสวนิลา', '239', '0.000', '04', 1, '04', 'N'),
('P203', 'V003', 'ไอศกรีมรสมัฉฉะ', '239', '0.000', '04', 1, '04', 'N'),
('P204', 'V003', 'ไอศกรีมรสชาไทย', '239', '0.000', '04', 1, '04', 'N'),
('P205', 'V003', 'ไอศกรีมรสกาแฟ', '239', '0.000', '04', 1, '04', 'N'),
('P206', 'V003', 'ไอศกรีมรสสตรอเบอรี่', '239', '0.000', '04', 1, '04', 'N'),
('P207', 'V003', 'ไอศกรีมรสช้อคโกแลตชิฟ', '239', '0.000', '04', 1, '04', 'N'),
('P208', 'V003', 'ไอศกรีมรสช้อคโกแลต', '239', '0.000', '04', 1, '04', 'N'),
('P226', 'V004', 'เครปเค้ก', '359', '0.000', '05', 1, '04', 'N'),
('P227', 'V004', 'เค้กวนิลา', '359', '0.000', '05', 1, '04', 'N'),
('P228', 'V004', 'เค้กช้อคโกแลต', '359', '0.000', '05', 1, '04', 'N'),
('P229', 'V004', 'เค้กมัฉฉะ', '359', '0.000', '05', 1, '04', 'N'),
('P230', 'V004', 'เค้กสตรอเบอรี่', '359', '0.000', '05', 1, '04', 'N'),
('P231', 'V004', 'พายข้าวโพด', '259', '0.000', '05', 1, '04', 'N'),
('P232', 'V004', 'พายแอปเปิ้ล', '259', '0.000', '05', 1, '04', 'N'),
('P233', 'V004', 'พายมะพร้าว', '259', '0.000', '05', 1, '04', 'N'),
('P234', 'V004', 'พายไก่', '199', '0.000', '05', 1, '04', 'N'),
('P235', 'V004', 'คุ๊กกี้', '5', '0.000', '07', 20, '04', 'N'),
('P251', 'V001', 'เนย', '159', '0.000', '05', 1, '04', 'N'),
('P252', 'V001', 'ไข่', '4', '0.000', '08', 24, '04', 'N'),
('P253', 'V001', 'แป้ง', '48', '0.000', '01', 1, '04', 'N'),
('P259', 'V001', 'เฟรนฟรายสำเร็จรูป', '150', '0.000', '01', 1, '04', 'N'),
('P260', 'V001', 'ปลาแท่ง', '150', '0.000', '01', 1, '04', 'N'),
('P261', 'V001', 'เกลือสมุทร', '40', '0.000', '01', 1, '04', 'N'),
('P262', 'V001', 'น้ำตาล', '50', '0.000', '01', 1, '04', 'N'),
('P301', 'V001', 'หอมหัวใหญ่', '50', '0.000', '01', 6, '04', 'N'),
('P351', 'V001', 'ปีกไก่', '90', '0.000', '01', 2, '04', 'N'),
('P352', 'V001', 'ไข่', '4', '0.000', '08', 1, '04', 'N'),
('P353', 'V001', 'ไก่', '50', '0.000', '01', 2, '04', 'N'),
('P354', 'V001', 'ข้าวสวย', '150', '0.000', '01', 1, '04', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `product_list`
--

CREATE TABLE `product_list` (
  `PRO_LIST_ID` char(2) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสประเภทของสินค้า',
  `PRO_LIST_NAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อประเภทของสินค้า'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product_list`
--

INSERT INTO `product_list` (`PRO_LIST_ID`, `PRO_LIST_NAME`) VALUES
('01', 'เครื่องครัว'),
('02', 'เครื่องชง'),
('03', 'ภาชนะ'),
('04', 'วัตถุดิบ');

-- --------------------------------------------------------

--
-- Table structure for table `product_unit_list`
--

CREATE TABLE `product_unit_list` (
  `PRO_UNITS_TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `PRO_UNITS_TYPE_NAME` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `PRO_UNITS_TYPE_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product_unit_list`
--

INSERT INTO `product_unit_list` (`PRO_UNITS_TYPE`, `PRO_UNITS_TYPE_NAME`, `PRO_UNITS_TYPE_DEL`) VALUES
('01', 'กิโลกรัม', 'N'),
('02', 'กรัม', 'N'),
('03', 'แก้ว', 'N'),
('04', 'กล่อง', 'N'),
('05', 'ก้อน', 'N'),
('06', 'ขวด', 'N'),
('07', 'ชิ้น', 'N'),
('08', 'ฟอง', 'N'),
('09', 'ลิตร', 'N'),
('10', 'อัน', 'N'),
('11', 'เครื่อง', 'N'),
('12', 'แท่ง', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `PN_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `PN_NAME` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `PN_S_DATE` date NOT NULL,
  `PN_E_DATE` date NOT NULL,
  `PN_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`PN_ID`, `PN_NAME`, `PN_S_DATE`, `PN_E_DATE`, `PN_DEL`) VALUES
('P001', 'กาแฟเย็นลด 50%', '2019-01-17', '2019-01-31', 'N'),
('P002', 'กาแฟร้อน 1 แถม 1', '2019-01-17', '2019-01-31', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `promotion_menu`
--

CREATE TABLE `promotion_menu` (
  `PM_ID` int(4) NOT NULL,
  `PN_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `MENU_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `PM_DISCOUNT` decimal(4,2) NOT NULL DEFAULT '0.00',
  `PM_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `promotion_menu`
--

INSERT INTO `promotion_menu` (`PM_ID`, `PN_ID`, `MENU_ID`, `PM_DISCOUNT`, `PM_DEL`) VALUES
(1, 'P001', 'M001', '0.50', 'N'),
(2, 'P002', 'M002', '1.00', 'N'),
(3, 'P002', 'M002', '0.00', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `pro_order`
--

CREATE TABLE `pro_order` (
  `PO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการสั่งสินค้า',
  `PO_DATE` date NOT NULL COMMENT 'วันที่สั่งสินค้า',
  `PO_PRICE` decimal(9,2) NOT NULL COMMENT 'ราคาสุทธิ',
  `PO_REC_DATE` date NOT NULL COMMENT 'วันที่รับสินค้า',
  `PO_PAY_DATE` date NOT NULL COMMENT 'วันที่จ่ายเงิน',
  `PO_UNITS` int(3) NOT NULL COMMENT 'จำนวนรายการที่สั่ง',
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสพนักงาน',
  `V_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสผู้จัดจำหน่าย',
  `PO_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pro_order`
--

INSERT INTO `pro_order` (`PO_ID`, `PO_DATE`, `PO_PRICE`, `PO_REC_DATE`, `PO_PAY_DATE`, `PO_UNITS`, `EMP_ID`, `V_ID`, `PO_DEL`) VALUES
('O001', '2018-11-24', '20760.00', '2561-11-24', '2561-11-24', 6, 'E001', 'V001', 'Y'),
('O002', '2018-11-24', '8300.00', '2561-11-24', '2561-11-24', 1, 'E001', 'V002', 'N'),
('O003', '2018-11-24', '6750.00', '2561-11-24', '2561-11-24', 1, 'E001', 'V001', 'Y'),
('O004', '2018-11-25', '6750.00', '2561-11-30', '2561-11-28', 1, 'E001', 'V001', 'N'),
('O005', '2018-12-13', '2850.00', '2561-12-13', '2561-12-13', 1, 'E001', 'V001', 'N'),
('O006', '2018-12-13', '29999.00', '2561-12-13', '2561-12-13', 1, 'E001', 'V002', 'N'),
('O007', '2018-12-14', '600.00', '2561-12-21', '2561-12-15', 1, 'E001', 'V003', 'N'),
('O008', '2018-12-16', '7200.00', '2018-12-16', '2018-12-16', 2, 'E001', 'V001', 'N'),
('O009', '2018-12-16', '10755.00', '2018-12-16', '2018-12-16', 3, 'E001', 'V003', 'N'),
('O010', '2019-01-01', '9600.00', '2019-01-31', '2019-01-17', 2, 'E001', 'V001', 'N'),
('O011', '2019-01-01', '45000.00', '2019-01-09', '2019-01-01', 1, 'E001', 'V001', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `pro_order_list`
--

CREATE TABLE `pro_order_list` (
  `POL_NUMBER` int(4) NOT NULL COMMENT 'รายการสั่งสินค้า',
  `PO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการสั่ง',
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสสินค้า',
  `PRO_UNITS` int(4) NOT NULL COMMENT 'จำนวนที่สั่ง',
  `PRO_PRICE` decimal(7,2) NOT NULL COMMENT 'ราคารวม',
  `POL_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pro_order_list`
--

INSERT INTO `pro_order_list` (`POL_NUMBER`, `PO_ID`, `PRO_ID`, `PRO_UNITS`, `PRO_PRICE`, `POL_DEL`) VALUES
(1, 'O001', 'P102', 15, '190.00', 'Y'),
(2, 'O001', 'P105', 15, '210.00', 'Y'),
(3, 'O001', 'P101', 15, '450.00', 'Y'),
(4, 'O001', 'P103', 15, '190.00', 'Y'),
(5, 'O001', 'P351', 48, '45.00', 'Y'),
(6, 'O001', 'P123', 15, '200.00', 'Y'),
(7, 'O002', 'P003', 1, '8300.00', 'N'),
(8, 'O003', 'P101', 15, '450.00', 'Y'),
(9, 'O004', 'P101', 15, '450.00', 'N'),
(10, 'O005', 'P103', 15, '190.00', 'N'),
(11, 'O006', 'P001', 1, '29999.00', 'N'),
(12, 'O007', 'P009', 15, '40.00', 'N'),
(13, 'O008', 'P103', 15, '190.00', 'N'),
(14, 'O008', 'P104', 15, '290.00', 'N'),
(15, 'O009', 'P201', 15, '239.00', 'N'),
(16, 'O009', 'P203', 15, '239.00', 'N'),
(17, 'O009', 'P208', 15, '239.00', 'N'),
(18, 'O010', 'P101', 15, '450.00', 'N'),
(19, 'O010', 'P102', 15, '190.00', 'N'),
(20, 'O011', 'P101', 100, '450.00', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `pro_receive`
--

CREATE TABLE `pro_receive` (
  `PR_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการรับสินค้า',
  `PR_DATE` date NOT NULL COMMENT 'วันที่รับสินค้า',
  `PR_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการรับสินค้า',
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสพนักงาน',
  `PO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการสั่งสินค้า',
  `PR_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pro_receive`
--

INSERT INTO `pro_receive` (`PR_ID`, `PR_DATE`, `PR_STATUS`, `EMP_ID`, `PO_ID`, `PR_DEL`) VALUES
('R001', '2561-11-24', 'Y', 'E001', 'O001', 'Y'),
('R002', '2561-11-24', 'N', 'E001', 'O002', 'N'),
('R003', '2561-11-24', 'N', 'E001', 'O003', 'N'),
('R004', '2561-11-30', 'N', 'E001', 'O004', 'N'),
('R005', '2561-12-13', 'N', 'E001', 'O005', 'N'),
('R006', '2561-12-13', 'N', 'E001', 'O006', 'N'),
('R007', '2561-12-21', 'Y', 'E001', 'O007', 'N'),
('R008', '2018-12-16', 'Y', 'E001', 'O008', 'N'),
('R009', '2018-12-16', 'Y', 'E001', 'O009', 'N'),
('R010', '2019-01-31', 'N', 'E001', 'O010', 'N'),
('R011', '2019-01-09', 'Y', 'E001', 'O011', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `pro_rec_list`
--

CREATE TABLE `pro_rec_list` (
  `PRL_NUMBER` int(4) NOT NULL COMMENT 'รายการรับสินค้า',
  `PR_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการรับสินค้า',
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสสินค้า',
  `PRL_UNITS` int(4) NOT NULL COMMENT 'จำนวนทั้งหมดที่รับ',
  `PRL_PRICE` decimal(7,2) NOT NULL COMMENT 'ราคารวมทั้งหมด',
  `PRL_CURRENT` int(11) NOT NULL DEFAULT '0' COMMENT 'จำนวนที่รับแล้ว',
  `PRL_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการรับ',
  `PRL_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pro_rec_list`
--

INSERT INTO `pro_rec_list` (`PRL_NUMBER`, `PR_ID`, `PRO_ID`, `PRL_UNITS`, `PRL_PRICE`, `PRL_CURRENT`, `PRL_STATUS`, `PRL_DEL`) VALUES
(1, 'R001', 'P102', 15, '190.00', 0, 'N', 'Y'),
(2, 'R001', 'P105', 15, '210.00', 0, 'Y', 'Y'),
(3, 'R001', 'P101', 15, '450.00', 0, 'N', 'Y'),
(4, 'R001', 'P103', 15, '190.00', 0, 'N', 'Y'),
(5, 'R001', 'P351', 48, '45.00', 0, 'N', 'Y'),
(6, 'R001', 'P123', 15, '200.00', 0, 'N', 'Y'),
(7, 'R002', 'P003', 1, '8300.00', 0, 'N', 'N'),
(8, 'R003', 'P101', 15, '450.00', 0, 'N', 'Y'),
(9, 'R004', 'P101', 15, '450.00', 0, 'N', 'N'),
(10, 'R005', 'P103', 15, '190.00', 0, 'N', 'N'),
(11, 'R006', 'P001', 1, '29999.00', 1, 'Y', 'N'),
(12, 'R007', 'P009', 15, '40.00', 15, 'Y', 'N'),
(13, 'R008', 'P103', 15, '190.00', 15, 'Y', 'N'),
(14, 'R008', 'P104', 15, '290.00', 15, 'Y', 'N'),
(15, 'R009', 'P201', 15, '239.00', 15, 'Y', 'N'),
(16, 'R009', 'P203', 15, '239.00', 15, 'Y', 'N'),
(17, 'R009', 'P208', 15, '239.00', 15, 'Y', 'N'),
(18, 'R010', 'P101', 15, '450.00', 0, 'N', 'N'),
(19, 'R010', 'P102', 15, '190.00', 0, 'N', 'N'),
(20, 'R011', 'P101', 100, '450.00', 100, 'Y', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `rep_order`
--

CREATE TABLE `rep_order` (
  `REP_NUMBER` int(4) NOT NULL,
  `OM_ID` int(4) NOT NULL,
  `REP_DATE` datetime NOT NULL,
  `REP_DETAIL` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REP_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rep_order`
--

INSERT INTO `rep_order` (`REP_NUMBER`, `OM_ID`, `REP_DATE`, `REP_DETAIL`, `REP_DEL`) VALUES
(1, 1, '2019-01-23 02:26:03', 'กเกฟฟ้ก', 'N'),
(2, 1, '2019-01-23 02:28:05', '', 'N'),
(3, 1, '2019-01-23 02:29:09', '12121', 'N'),
(4, 1, '2019-01-23 02:30:41', '', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `salary_payment`
--

CREATE TABLE `salary_payment` (
  `SP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการจ่ายเงิน',
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสพนักงาน',
  `SP_DATE` date NOT NULL COMMENT 'วันที่จ่ายเงิน',
  `SP_S_PEROID` date NOT NULL COMMENT 'ช่วงเวลาเริ่มการคิดเงินเดือน',
  `SP_E_PEROID` date NOT NULL COMMENT 'ช่วงเวลาสิ้นสุดการคิดเงินเดือน',
  `SP_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N' COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `salary_payment`
--

INSERT INTO `salary_payment` (`SP_ID`, `EMP_ID`, `SP_DATE`, `SP_S_PEROID`, `SP_E_PEROID`, `SP_DEL`) VALUES
('S001', 'E001', '2018-11-18', '2018-11-01', '2018-11-15', 'N'),
('S002', 'E002', '2018-11-18', '2018-11-01', '2018-11-15', 'N'),
('S003', 'E024', '2018-11-18', '2018-11-01', '2018-11-15', 'N'),
('S004', 'E025', '2018-11-18', '2018-11-01', '2018-11-15', 'N'),
('S005', 'E026', '2018-11-18', '2018-11-01', '2018-11-15', 'N'),
('S006', 'E027', '2018-11-18', '2018-11-01', '2018-11-15', 'N'),
('S007', 'E028', '2018-11-18', '2018-11-01', '2018-11-15', 'N'),
('S008', 'E001', '2018-11-19', '2018-11-15', '2018-11-30', 'N'),
('S009', 'E002', '2018-11-19', '2018-11-15', '2018-11-30', 'N'),
('S010', 'E024', '2018-11-19', '2018-11-15', '2018-11-30', 'N'),
('S011', 'E025', '2018-11-19', '2018-11-15', '2018-11-30', 'N'),
('S012', 'E026', '2018-11-19', '2018-11-15', '2018-11-30', 'N'),
('S013', 'E027', '2018-11-19', '2018-11-15', '2018-11-30', 'N'),
('S014', 'E028', '2018-11-19', '2018-11-15', '2018-11-30', 'N'),
('S015', 'E001', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S016', 'E002', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S017', 'E024', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S018', 'E025', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S019', 'E026', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S020', 'E027', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S021', 'E028', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S022', 'E029', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S023', 'E030', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S024', 'E031', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S025', 'E032', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S026', 'E033', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S027', 'E034', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S028', 'E035', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S029', 'E036', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S030', 'E038', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S031', 'E039', '2019-01-21', '2019-01-15', '2019-01-31', 'N'),
('S032', 'E041', '2019-01-21', '2019-01-15', '2019-01-31', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `SC_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการลงเวลา',
  `SC_DATE` date NOT NULL COMMENT 'วันที่',
  `SCS_ID` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสกะเวลา',
  `SC_EMPLIMIT` int(1) NOT NULL DEFAULT '1' COMMENT 'จำนวนพนักงานสูงสุด',
  `SC_EMPCUR` int(1) NOT NULL DEFAULT '0' COMMENT 'จำนวนพนักงานปัจจุบัน',
  `SC_LEAVE` int(1) NOT NULL DEFAULT '0' COMMENT 'จำนวนพนักงานที่ลา',
  `SC_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N' COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`SC_ID`, `SC_DATE`, `SCS_ID`, `SC_EMPLIMIT`, `SC_EMPCUR`, `SC_LEAVE`, `SC_DEL`) VALUES
('E001', '2018-11-18', '1', 1, 0, 1, 'Y'),
('E002', '2018-11-18', '2', 1, 0, 0, 'N'),
('E003', '2018-11-19', '1', 1, 1, 0, 'N'),
('E004', '2018-11-19', '2', 1, 0, 0, 'N'),
('E005', '2018-12-13', '1', 1, 0, 0, 'N'),
('E006', '2018-12-13', '2', 5, 0, 0, 'N'),
('E007', '2018-12-14', '1', 5, 1, 0, 'N'),
('E008', '2018-12-14', '2', 3, 0, 0, 'N'),
('E009', '2019-01-22', '1', 1, 1, 0, 'N');

-- --------------------------------------------------------

--
-- Table structure for table `schedule_list`
--

CREATE TABLE `schedule_list` (
  `SL_NUMBER` decimal(5,0) NOT NULL COMMENT 'รายการพนักงานลงเวลา',
  `SC_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการลงเวลา',
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสพนักงาน',
  `SL_STATUS` char(1) COLLATE utf8_unicode_ci DEFAULT 'Y' COMMENT 'สถานะการเข้างาน',
  `SL_CAUSE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'สาเหตุการลา',
  `SL_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `schedule_list`
--

INSERT INTO `schedule_list` (`SL_NUMBER`, `SC_ID`, `EMP_ID`, `SL_STATUS`, `SL_CAUSE`, `SL_DEL`) VALUES
('1', 'E001', 'E001', 'Y', NULL, 'N'),
('2', 'E001', 'E025', 'Y', NULL, 'N'),
('3', 'E003', 'E001', 'Y', 'NULL', 'N'),
('4', 'E003', 'E025', 'Y', 'NULL', 'N'),
('5', 'E003', 'E025', 'Y', 'NULL', 'N'),
('6', 'E007', 'E001', 'N', 'NULL', 'N'),
('7', 'E009', 'E001', 'Y', 'NULL', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `sc_shift`
--

CREATE TABLE `sc_shift` (
  `SCS_ID` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสกะเวลา',
  `SCS_NAME` varchar(25) COLLATE utf8_unicode_ci NOT NULL COMMENT 'เวลา'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sc_shift`
--

INSERT INTO `sc_shift` (`SCS_ID`, `SCS_NAME`) VALUES
('1', '08.00-15.30'),
('2', '15.00-22.30');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `STOCK_NUMBER` int(4) NOT NULL COMMENT 'รายการเก็บสินค้า',
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสสินค้า',
  `STOCK_EXP` date NOT NULL COMMENT 'วันหมดอายุ',
  `STOCK_STARTDATE` date NOT NULL COMMENT 'วันที่รับสินค้าเข้า',
  `STOCK_UNITS` decimal(7,3) NOT NULL COMMENT 'จำนวนการรับเข้า',
  `PO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการสั่งสินค้า',
  `STOCK_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`STOCK_NUMBER`, `PRO_ID`, `STOCK_EXP`, `STOCK_STARTDATE`, `STOCK_UNITS`, `PO_ID`, `STOCK_DEL`) VALUES
(1, 'P001', '2018-12-13', '2018-12-14', '1.000', '', 'N'),
(2, 'P009', '2018-12-14', '2018-12-14', '1.000', '', 'N'),
(3, 'P009', '2018-12-14', '2018-12-14', '14.000', '', 'N'),
(4, 'P103', '2018-12-16', '2018-12-16', '15.000', 'O008', 'Y'),
(5, 'P104', '2018-12-16', '2018-12-16', '15.000', 'O008', 'Y'),
(6, 'P201', '2018-12-16', '2018-12-16', '15.000', 'O009', 'N'),
(7, 'P203', '2018-12-16', '2018-12-16', '8.000', 'O009', 'N'),
(8, 'P203', '2018-12-16', '2018-12-16', '7.000', 'O009', 'N'),
(9, 'P208', '2018-12-16', '2018-12-16', '15.000', 'O009', 'N'),
(10, 'P104', '2018-12-31', '2018-12-30', '15.000', 'O008', 'N'),
(11, 'P101', '2019-01-31', '2019-01-16', '1.000', '', 'N'),
(12, 'P101', '2019-01-31', '2019-01-18', '1.000', '', 'N'),
(13, 'P101', '2019-01-30', '2019-01-16', '0.945', '', 'N'),
(14, 'P151', '2019-01-31', '2019-01-19', '0.995', '', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `tablez`
--

CREATE TABLE `tablez` (
  `T_ID` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `T_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `T_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tablez`
--

INSERT INTO `tablez` (`T_ID`, `T_STATUS`, `T_DEL`) VALUES
('01', 'Y', 'N'),
('02', 'N', 'N'),
('03', 'N', 'N'),
('04', 'N', 'N'),
('05', 'N', 'N'),
('06', 'N', 'N'),
('07', 'N', 'N'),
('08', 'N', 'N'),
('09', 'N', 'N'),
('10', 'N', 'N'),
('11', 'N', 'N'),
('12', 'N', 'N'),
('14', 'N', 'N'),
('15', 'N', 'N'),
('16', 'N', 'N'),
('17', 'N', 'N'),
('55', 'N', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `V_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสบริษัทผู้จัดจำหน่าย',
  `V_NAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อบริษัทผู้จัดจำหน่าย',
  `V_ADDRESS` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ที่อยู่',
  `V_PHONE` char(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'เบอร์โทรศัพท์',
  `V_EMAIL` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'อีเมล',
  `V_ACC_NUM` char(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสบัญชี',
  `V_CONTACT` varchar(35) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ชื่อผู้ติดต่อ',
  `V_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`V_ID`, `V_NAME`, `V_ADDRESS`, `V_PHONE`, `V_EMAIL`, `V_ACC_NUM`, `V_CONTACT`, `V_DEL`) VALUES
('V001', 'CP', 'American Dairy Queen Corporation\r\n7505 Metro Blvd.\r\nMinneapolis, MN 55439-0286', '9528300200', '-', '123456789', 'MR.JOHN', 'N'),
('V002', 'Dairy Queen', 'American Dairy Queen Corporation\r\n7505 Metro Blvd.\r\nMinneapolis, MN 55439-0286', '9528300200', '-', '123456789', 'MR.JOHN', 'N'),
('V003', 'ร้านโชว์ห่วย', 'American Dairy Queen Corporation\r\n7505 Metro Blvd.\r\nMinneapolis, MN 55439-0286', '9528300200', '-', '123456789', 'MR.JOHN', 'N'),
('V004', 'Dairy Queen', 'American Dairy Queen Corporation\r\n7505 Metro Blvd.\r\nMinneapolis, MN 55439-0286', '9528300200', '-', '123456789', 'MR.JOHN', 'Y'),
('V005', 'Dairy Queen', 'American Dairy Queen Corporation\r\n7505 Metro Blvd.\r\nMinneapolis, MN 55439-0286', '9528300200', '-', '123456789', 'MR.JOHN', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `waste`
--

CREATE TABLE `waste` (
  `W_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `W_TOTAL` int(3) NOT NULL,
  `W_DATE` date NOT NULL,
  `W_TOTAL_PRICE` decimal(6,2) NOT NULL,
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `W_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `waste_list`
--

CREATE TABLE `waste_list` (
  `WL_NUMBER` int(4) NOT NULL,
  `WL_UNIT` decimal(3,2) NOT NULL,
  `WL_CAUSE` varchar(100) COLLATE utf8_unicode_ci DEFAULT 'ของเสีย',
  `WL_PRICE` decimal(6,2) DEFAULT NULL,
  `WL_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `PRO_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `W_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `WL_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `worktime`
--

CREATE TABLE `worktime` (
  `W_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสการเข้า/ออกงาน',
  `EMP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL COMMENT 'รหัสพนักงาน',
  `W_CLOCKIN` time NOT NULL COMMENT 'เวลาที่เข้างาน',
  `W_CLOCKOUT` time DEFAULT NULL COMMENT 'เวลาที่ออกงาน',
  `W_DATE` date NOT NULL COMMENT 'วันที่',
  `W_TOTALTIME` decimal(2,1) NOT NULL DEFAULT '0.0' COMMENT 'จำนวนเวลาทั้งหมด',
  `W_STATUS` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N' COMMENT 'สถานะการเข้างาน',
  `W_DEL` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'สถานะการลบ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `worktime`
--

INSERT INTO `worktime` (`W_ID`, `EMP_ID`, `W_CLOCKIN`, `W_CLOCKOUT`, `W_DATE`, `W_TOTALTIME`, `W_STATUS`, `W_DEL`) VALUES
('W009', 'E025', '08:28:34', '15:29:11', '2018-11-18', '7.0', 'Y', 'N'),
('W010', 'E001', '08:50:17', '15:32:10', '2018-11-19', '0.0', 'Y', 'N'),
('W012', 'E025', '08:28:34', '15:29:11', '2018-11-19', '7.0', 'Y', 'N'),
('W013', 'E025', '08:28:34', '15:29:11', '2018-11-19', '7.0', 'Y', 'N'),
('W014', 'E001', '08:00:32', '15:32:10', '2019-01-22', '7.5', 'Y', 'N');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `claim`
--
ALTER TABLE `claim`
  ADD PRIMARY KEY (`CL_ID`),
  ADD UNIQUE KEY `CL_ID` (`CL_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`),
  ADD KEY `PO_ID` (`PO_ID`);

--
-- Indexes for table `claim_list`
--
ALTER TABLE `claim_list`
  ADD PRIMARY KEY (`C_L_NUMBER`),
  ADD UNIQUE KEY `C_L_NUMBER` (`C_L_NUMBER`),
  ADD KEY `CL_ID` (`CL_ID`),
  ADD KEY `PO_ID` (`PO_ID`),
  ADD KEY `PRO_ID` (`PRO_ID`),
  ADD KEY `STOCK_NUMBER` (`STOCK_NUMBER`);

--
-- Indexes for table `claim_receive`
--
ALTER TABLE `claim_receive`
  ADD PRIMARY KEY (`CR_ID`),
  ADD UNIQUE KEY `CR_ID` (`CR_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`),
  ADD KEY `CL_ID` (`CL_ID`);

--
-- Indexes for table `claim_rec_list`
--
ALTER TABLE `claim_rec_list`
  ADD PRIMARY KEY (`CRL_NUMBER`),
  ADD UNIQUE KEY `CRL_NUMBER` (`CRL_NUMBER`),
  ADD KEY `CR_ID` (`CR_ID`),
  ADD KEY `PRO_ID` (`PRO_ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EMP_ID`),
  ADD UNIQUE KEY `EMP_ID` (`EMP_ID`),
  ADD KEY `POS_ID` (`POS_ID`);

--
-- Indexes for table `emp_position`
--
ALTER TABLE `emp_position`
  ADD PRIMARY KEY (`POS_ID`);

--
-- Indexes for table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`ING_NUMBER`),
  ADD UNIQUE KEY `ING_NUMBER` (`ING_NUMBER`),
  ADD KEY `MENU_ID` (`MENU_ID`),
  ADD KEY `PRO_ID` (`PRO_ID`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`MENU_ID`),
  ADD UNIQUE KEY `MENU_ID` (`MENU_ID`),
  ADD KEY `M_T_ID` (`M_T_ID`);

--
-- Indexes for table `menu_backup`
--
ALTER TABLE `menu_backup`
  ADD PRIMARY KEY (`MENU_ID`),
  ADD UNIQUE KEY `MENU_ID` (`MENU_ID`),
  ADD KEY `M_T_ID` (`M_T_ID`);

--
-- Indexes for table `menu_type`
--
ALTER TABLE `menu_type`
  ADD PRIMARY KEY (`M_T_ID`),
  ADD UNIQUE KEY `M_T_ID` (`M_T_ID`);

--
-- Indexes for table `ordering`
--
ALTER TABLE `ordering`
  ADD PRIMARY KEY (`ORD_ID`),
  ADD UNIQUE KEY `ORD_ID` (`ORD_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`),
  ADD KEY `T_ID` (`T_ID`);

--
-- Indexes for table `order_menu_list`
--
ALTER TABLE `order_menu_list`
  ADD PRIMARY KEY (`OM_ID`),
  ADD UNIQUE KEY `OM_ID` (`OM_ID`),
  ADD KEY `ORD_ID` (`ORD_ID`),
  ADD KEY `MENU_ID` (`MENU_ID`);

--
-- Indexes for table `order_payment`
--
ALTER TABLE `order_payment`
  ADD PRIMARY KEY (`OP_NUMBER`),
  ADD UNIQUE KEY `OP_NUMBER` (`OP_NUMBER`),
  ADD KEY `ORDER_PAYMENT_ibfk_1` (`PO_ID`),
  ADD KEY `ORDER_PAYMENT_ibfk_2` (`V_ID`);

--
-- Indexes for table `order_promotion`
--
ALTER TABLE `order_promotion`
  ADD PRIMARY KEY (`O_PN_NUMBER`),
  ADD UNIQUE KEY `OP_NUMBER` (`O_PN_NUMBER`),
  ADD KEY `ORD_ID` (`ORD_ID`),
  ADD KEY `PN_ID` (`PN_ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`PRO_ID`),
  ADD UNIQUE KEY `PRO_ID` (`PRO_ID`),
  ADD KEY `V_ID` (`V_ID`),
  ADD KEY `PRO_LIST_ID` (`PRO_LIST_ID`),
  ADD KEY `PRO_UNITS_TYPE` (`PRO_UNITS_TYPE`);

--
-- Indexes for table `product_list`
--
ALTER TABLE `product_list`
  ADD PRIMARY KEY (`PRO_LIST_ID`),
  ADD UNIQUE KEY `PRO_LIST_ID` (`PRO_LIST_ID`);

--
-- Indexes for table `product_unit_list`
--
ALTER TABLE `product_unit_list`
  ADD PRIMARY KEY (`PRO_UNITS_TYPE`),
  ADD UNIQUE KEY `PRODUCT_UNITS_TYPE` (`PRO_UNITS_TYPE`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`PN_ID`),
  ADD UNIQUE KEY `PN_ID` (`PN_ID`);

--
-- Indexes for table `promotion_menu`
--
ALTER TABLE `promotion_menu`
  ADD PRIMARY KEY (`PM_ID`),
  ADD UNIQUE KEY `PM_ID` (`PM_ID`),
  ADD KEY `PN_ID` (`PN_ID`),
  ADD KEY `MENU_ID` (`MENU_ID`);

--
-- Indexes for table `pro_order`
--
ALTER TABLE `pro_order`
  ADD PRIMARY KEY (`PO_ID`),
  ADD UNIQUE KEY `PO_ID` (`PO_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`),
  ADD KEY `V_ID` (`V_ID`);

--
-- Indexes for table `pro_order_list`
--
ALTER TABLE `pro_order_list`
  ADD PRIMARY KEY (`POL_NUMBER`),
  ADD UNIQUE KEY `POL_NUMBER` (`POL_NUMBER`),
  ADD KEY `PO_ID` (`PO_ID`),
  ADD KEY `PRO_ID` (`PRO_ID`);

--
-- Indexes for table `pro_receive`
--
ALTER TABLE `pro_receive`
  ADD PRIMARY KEY (`PR_ID`),
  ADD UNIQUE KEY `PR_ID` (`PR_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`),
  ADD KEY `PO_ID` (`PO_ID`) USING BTREE;

--
-- Indexes for table `pro_rec_list`
--
ALTER TABLE `pro_rec_list`
  ADD PRIMARY KEY (`PRL_NUMBER`),
  ADD UNIQUE KEY `PRL_NUMBER` (`PRL_NUMBER`),
  ADD KEY `PRO_ID` (`PRO_ID`),
  ADD KEY `PRO_REC_LIST_ibfk_1` (`PR_ID`);

--
-- Indexes for table `rep_order`
--
ALTER TABLE `rep_order`
  ADD PRIMARY KEY (`REP_NUMBER`),
  ADD UNIQUE KEY `REP_NUMBER` (`REP_NUMBER`),
  ADD KEY `OM_ID` (`OM_ID`);

--
-- Indexes for table `salary_payment`
--
ALTER TABLE `salary_payment`
  ADD PRIMARY KEY (`SP_ID`),
  ADD UNIQUE KEY `SP_ID` (`SP_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`SC_ID`),
  ADD UNIQUE KEY `SC_ID` (`SC_ID`),
  ADD KEY `SCS_ID` (`SCS_ID`);

--
-- Indexes for table `schedule_list`
--
ALTER TABLE `schedule_list`
  ADD PRIMARY KEY (`SL_NUMBER`),
  ADD UNIQUE KEY `SL_NUMBER` (`SL_NUMBER`),
  ADD KEY `EMP_ID` (`EMP_ID`),
  ADD KEY `SCHEDULE_LIST_ibfk_1` (`SC_ID`);

--
-- Indexes for table `sc_shift`
--
ALTER TABLE `sc_shift`
  ADD PRIMARY KEY (`SCS_ID`),
  ADD UNIQUE KEY `SCS_ID` (`SCS_ID`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`STOCK_NUMBER`),
  ADD UNIQUE KEY `STOCK_NUMBER` (`STOCK_NUMBER`),
  ADD KEY `PRO_ID` (`PRO_ID`);

--
-- Indexes for table `tablez`
--
ALTER TABLE `tablez`
  ADD PRIMARY KEY (`T_ID`),
  ADD UNIQUE KEY `T_ID` (`T_ID`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`V_ID`),
  ADD UNIQUE KEY `V_ID` (`V_ID`);

--
-- Indexes for table `waste`
--
ALTER TABLE `waste`
  ADD PRIMARY KEY (`W_ID`),
  ADD UNIQUE KEY `W_ID` (`W_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`);

--
-- Indexes for table `waste_list`
--
ALTER TABLE `waste_list`
  ADD PRIMARY KEY (`WL_NUMBER`),
  ADD UNIQUE KEY `WL_NUMBER` (`WL_NUMBER`),
  ADD KEY `PRO_ID` (`PRO_ID`),
  ADD KEY `W_ID` (`W_ID`);

--
-- Indexes for table `worktime`
--
ALTER TABLE `worktime`
  ADD PRIMARY KEY (`W_ID`),
  ADD UNIQUE KEY `W_ID` (`W_ID`),
  ADD KEY `EMP_ID` (`EMP_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `claim`
--
ALTER TABLE `claim`
  ADD CONSTRAINT `CLAIM_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`),
  ADD CONSTRAINT `CLAIM_ibfk_2` FOREIGN KEY (`PO_ID`) REFERENCES `pro_order` (`PO_ID`);

--
-- Constraints for table `claim_list`
--
ALTER TABLE `claim_list`
  ADD CONSTRAINT `CLAIM_LIST_ibfk_1` FOREIGN KEY (`CL_ID`) REFERENCES `claim` (`CL_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `CLAIM_LIST_ibfk_2` FOREIGN KEY (`PO_ID`) REFERENCES `pro_order` (`PO_ID`),
  ADD CONSTRAINT `CLAIM_LIST_ibfk_3` FOREIGN KEY (`STOCK_NUMBER`) REFERENCES `stock` (`STOCK_NUMBER`),
  ADD CONSTRAINT `CLAIM_LIST_ibfk_4` FOREIGN KEY (`PRO_ID`) REFERENCES `product` (`PRO_ID`);

--
-- Constraints for table `claim_receive`
--
ALTER TABLE `claim_receive`
  ADD CONSTRAINT `CLAIM_RECEIVE_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`),
  ADD CONSTRAINT `CLAIM_RECEIVE_ibfk_2` FOREIGN KEY (`CL_ID`) REFERENCES `claim` (`CL_ID`);

--
-- Constraints for table `claim_rec_list`
--
ALTER TABLE `claim_rec_list`
  ADD CONSTRAINT `CLAIM_REC_LIST_ibfk_1` FOREIGN KEY (`CR_ID`) REFERENCES `claim_receive` (`CR_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `CLAIM_REC_LIST_ibfk_2` FOREIGN KEY (`PRO_ID`) REFERENCES `product` (`PRO_ID`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `EMPLOYEE_ibfk_1` FOREIGN KEY (`POS_ID`) REFERENCES `emp_position` (`POS_ID`);

--
-- Constraints for table `ingredient`
--
ALTER TABLE `ingredient`
  ADD CONSTRAINT `INGREDIENT_ibfk_1` FOREIGN KEY (`MENU_ID`) REFERENCES `menu` (`MENU_ID`),
  ADD CONSTRAINT `INGREDIENT_ibfk_2` FOREIGN KEY (`PRO_ID`) REFERENCES `product` (`PRO_ID`);

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `MENU_ibfk_1` FOREIGN KEY (`M_T_ID`) REFERENCES `menu_type` (`M_T_ID`);

--
-- Constraints for table `ordering`
--
ALTER TABLE `ordering`
  ADD CONSTRAINT `ORDERING_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`),
  ADD CONSTRAINT `ORDERING_ibfk_2` FOREIGN KEY (`T_ID`) REFERENCES `tablez` (`T_ID`);

--
-- Constraints for table `order_menu_list`
--
ALTER TABLE `order_menu_list`
  ADD CONSTRAINT `ORDER_MENU_LIST_ibfk_1` FOREIGN KEY (`ORD_ID`) REFERENCES `ordering` (`ORD_ID`),
  ADD CONSTRAINT `ORDER_MENU_LIST_ibfk_2` FOREIGN KEY (`MENU_ID`) REFERENCES `menu` (`MENU_ID`);

--
-- Constraints for table `order_payment`
--
ALTER TABLE `order_payment`
  ADD CONSTRAINT `ORDER_PAYMENT_ibfk_1` FOREIGN KEY (`PO_ID`) REFERENCES `pro_order` (`PO_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `ORDER_PAYMENT_ibfk_2` FOREIGN KEY (`V_ID`) REFERENCES `vendor` (`V_ID`) ON DELETE CASCADE;

--
-- Constraints for table `order_promotion`
--
ALTER TABLE `order_promotion`
  ADD CONSTRAINT `ORDER_PROMOTION_ibfk_1` FOREIGN KEY (`ORD_ID`) REFERENCES `ordering` (`ORD_ID`),
  ADD CONSTRAINT `ORDER_PROMOTION_ibfk_2` FOREIGN KEY (`PN_ID`) REFERENCES `promotion` (`PN_ID`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `PRODUCT_ibfk_1` FOREIGN KEY (`V_ID`) REFERENCES `vendor` (`V_ID`),
  ADD CONSTRAINT `PRODUCT_ibfk_2` FOREIGN KEY (`PRO_LIST_ID`) REFERENCES `product_list` (`PRO_LIST_ID`),
  ADD CONSTRAINT `PRODUCT_ibfk_3` FOREIGN KEY (`PRO_UNITS_TYPE`) REFERENCES `product_unit_list` (`PRO_UNITS_TYPE`);

--
-- Constraints for table `promotion_menu`
--
ALTER TABLE `promotion_menu`
  ADD CONSTRAINT `PROMOTION_MENU_ibfk_1` FOREIGN KEY (`PN_ID`) REFERENCES `promotion` (`PN_ID`),
  ADD CONSTRAINT `PROMOTION_MENU_ibfk_2` FOREIGN KEY (`MENU_ID`) REFERENCES `menu` (`MENU_ID`);

--
-- Constraints for table `pro_order`
--
ALTER TABLE `pro_order`
  ADD CONSTRAINT `PRO_ORDER_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`),
  ADD CONSTRAINT `PRO_ORDER_ibfk_2` FOREIGN KEY (`V_ID`) REFERENCES `vendor` (`V_ID`);

--
-- Constraints for table `pro_order_list`
--
ALTER TABLE `pro_order_list`
  ADD CONSTRAINT `PRO_ORDER_LIST_ibfk_1` FOREIGN KEY (`PO_ID`) REFERENCES `pro_order` (`PO_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `PRO_ORDER_LIST_ibfk_2` FOREIGN KEY (`PRO_ID`) REFERENCES `product` (`PRO_ID`);

--
-- Constraints for table `pro_receive`
--
ALTER TABLE `pro_receive`
  ADD CONSTRAINT `PRO_RECEIVE_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`),
  ADD CONSTRAINT `PRO_RECEIVE_ibfk_2` FOREIGN KEY (`PO_ID`) REFERENCES `pro_order` (`PO_ID`);

--
-- Constraints for table `pro_rec_list`
--
ALTER TABLE `pro_rec_list`
  ADD CONSTRAINT `PRO_REC_LIST_ibfk_1` FOREIGN KEY (`PR_ID`) REFERENCES `pro_receive` (`PR_ID`),
  ADD CONSTRAINT `PRO_REC_LIST_ibfk_2` FOREIGN KEY (`PRO_ID`) REFERENCES `product` (`PRO_ID`);

--
-- Constraints for table `salary_payment`
--
ALTER TABLE `salary_payment`
  ADD CONSTRAINT `SALARY_PAYMENT_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`);

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `SCHEDULE_ibfk_1` FOREIGN KEY (`SCS_ID`) REFERENCES `sc_shift` (`SCS_ID`);

--
-- Constraints for table `schedule_list`
--
ALTER TABLE `schedule_list`
  ADD CONSTRAINT `SCHEDULE_LIST_ibfk_1` FOREIGN KEY (`SC_ID`) REFERENCES `schedule` (`SC_ID`),
  ADD CONSTRAINT `SCHEDULE_LIST_ibfk_2` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`);

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `STOCK_ibfk_1` FOREIGN KEY (`PRO_ID`) REFERENCES `product` (`PRO_ID`);

--
-- Constraints for table `waste`
--
ALTER TABLE `waste`
  ADD CONSTRAINT `WASTE_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`);

--
-- Constraints for table `waste_list`
--
ALTER TABLE `waste_list`
  ADD CONSTRAINT `WASTE_LIST_ibfk_1` FOREIGN KEY (`PRO_ID`) REFERENCES `product` (`PRO_ID`),
  ADD CONSTRAINT `WASTE_LIST_ibfk_2` FOREIGN KEY (`W_ID`) REFERENCES `waste` (`W_ID`);

--
-- Constraints for table `worktime`
--
ALTER TABLE `worktime`
  ADD CONSTRAINT `WORKTIME_ibfk_1` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
