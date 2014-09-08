-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 08, 2014 at 05:55 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pos`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE IF NOT EXISTS `branch` (
  `BranchID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Location` varchar(256) NOT NULL,
  `ManagerID` int(11) NOT NULL,
  PRIMARY KEY (`BranchID`),
  KEY `BranchID` (`BranchID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`BranchID`, `Name`, `Location`, `ManagerID`) VALUES
(1, 'Monsurabad', 'Monsurabad', 2);

-- --------------------------------------------------------

--
-- Table structure for table `branchrefund`
--

CREATE TABLE IF NOT EXISTS `branchrefund` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `BranchID` int(11) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `branchrefund`
--

INSERT INTO `branchrefund` (`TransactionID`, `BranchID`, `Date`) VALUES
(1, 1, '2014-09-01 13:52:47'),
(2, 1, '2014-09-01 13:56:37'),
(3, 1, '2014-09-01 14:05:47'),
(4, 1, '2014-09-01 14:10:08'),
(5, 1, '2014-09-01 14:11:28'),
(6, 1, '2014-09-01 14:19:28'),
(7, 1, '2014-09-01 14:20:33'),
(8, 1, '2014-09-01 14:30:32');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `EmployeeID` int(11) NOT NULL AUTO_INCREMENT,
  `BranchID` int(11) DEFAULT NULL,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(256) NOT NULL,
  `Salary` decimal(7,0) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Password` varchar(40) NOT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EmployeeID`, `BranchID`, `Name`, `Address`, `Salary`, `Type`, `Password`) VALUES
(1, 3, 'Fahim', 'Monsurabad', '20000', 'Main manager', 'fahim'),
(2, 1, 'Moin', 'mirpur', '20000', 'Manager', 'moin'),
(3, NULL, 'Rony', 'gazipur', '20000', 'Manager', 'rony'),
(4, 1, 'Rakib', 'mohammedpur', '10000', 'Salesman', 'rakib');

-- --------------------------------------------------------

--
-- Table structure for table `monsurabad_sales`
--

CREATE TABLE IF NOT EXISTS `monsurabad_sales` (
  `TransactionID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `TotalPrice` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monsurabad_sales`
--

INSERT INTO `monsurabad_sales` (`TransactionID`, `ProductID`, `Quantity`, `TotalPrice`) VALUES
(6, 3, 5, 1250),
(6, 3, 10, 2500),
(7, 3, 5, 1250),
(7, 3, 10, 2500),
(8, 3, 2, 500),
(8, 3, 2, 500),
(9, 3, 1, 250),
(10, 3, 5, 1250),
(11, 3, 5, 1250),
(12, 3, 10, 2500),
(12, 3, 5, 1250),
(13, 3, 5, 1250),
(13, 3, 10, 2500);

-- --------------------------------------------------------

--
-- Table structure for table `monsurabad_storage`
--

CREATE TABLE IF NOT EXISTS `monsurabad_storage` (
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monsurabad_storage`
--

INSERT INTO `monsurabad_storage` (`ProductID`, `Quantity`) VALUES
(3, -40);

-- --------------------------------------------------------

--
-- Table structure for table `monsurabad_transaction_sales`
--

CREATE TABLE IF NOT EXISTS `monsurabad_transaction_sales` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeID` int(11) DEFAULT NULL,
  `TotalAmount` int(11) DEFAULT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `monsurabad_transaction_sales`
--

INSERT INTO `monsurabad_transaction_sales` (`TransactionID`, `EmployeeID`, `TotalAmount`, `Date`) VALUES
(6, 2, 0, '2014-09-03 13:59:24'),
(7, 2, 0, '2014-09-03 14:10:01'),
(8, 2, 0, '2014-09-03 14:13:52'),
(9, 2, 0, '2014-09-03 14:19:25'),
(10, 3, 0, '2014-09-03 14:25:00'),
(11, 2, 0, '2014-09-03 14:27:32'),
(12, 2, 0, '2014-09-03 14:30:49'),
(13, 2, 3750, '2014-09-03 16:14:32');

-- --------------------------------------------------------

--
-- Table structure for table `monsurabad_transaction_supply`
--

CREATE TABLE IF NOT EXISTS `monsurabad_transaction_supply` (
  `TransactionID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monsurabad_transaction_supply`
--

INSERT INTO `monsurabad_transaction_supply` (`TransactionID`, `ProductID`, `Quantity`) VALUES
(2, 3, 20),
(3, 3, 10),
(3, 3, 20),
(4, 3, 15),
(5, 3, 25),
(6, 3, 10),
(7, 3, 10),
(8, 3, 10),
(8, 3, 15);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `SupplierID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Price` int(11) NOT NULL,
  `Description` varchar(256) NOT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductID`, `SupplierID`, `Name`, `Price`, `Description`) VALUES
(3, 1, 'Mutton', 250, 'pr/kg');

-- --------------------------------------------------------

--
-- Table structure for table `reportemployee`
--

CREATE TABLE IF NOT EXISTS `reportemployee` (
  `EmployeeID` int(11) NOT NULL,
  `ManagerID` int(11) NOT NULL,
  `Report` varchar(256) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reportemployee`
--

INSERT INTO `reportemployee` (`EmployeeID`, `ManagerID`, `Report`, `date`, `Status`) VALUES
(3, 2, 'Good', '2014-09-05 16:54:50', 'R');

-- --------------------------------------------------------

--
-- Table structure for table `storage_main`
--

CREATE TABLE IF NOT EXISTS `storage_main` (
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `storage_main`
--

INSERT INTO `storage_main` (`ProductID`, `Quantity`) VALUES
(3, 30);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `SupplierID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(256) DEFAULT NULL,
  `Description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`SupplierID`, `Name`, `Address`, `Description`) VALUES
(2, 'BYNNY', 'Banany', 'Good'),
(3, 'Haris', 'Mirpur', 'Good');

-- --------------------------------------------------------

--
-- Table structure for table `supply`
--

CREATE TABLE IF NOT EXISTS `supply` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TotalAmount` int(11) NOT NULL,
  `SupplierID` int(11) NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `supply`
--

INSERT INTO `supply` (`TransactionID`, `Date`, `TotalAmount`, `SupplierID`) VALUES
(1, '2014-08-30 13:33:08', 0, 2),
(2, '2014-08-30 13:36:34', 0, 2),
(3, '2014-08-30 13:38:11', 0, 2),
(4, '2014-08-30 13:43:15', 0, 3),
(5, '2014-08-30 13:48:35', 0, 2),
(6, '2014-08-30 13:58:56', 0, 2),
(7, '2014-08-30 14:02:08', 0, 2),
(8, '2014-08-30 14:03:14', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_supply_main`
--

CREATE TABLE IF NOT EXISTS `transaction_supply_main` (
  `TransactionID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `TotalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_supply_main`
--

INSERT INTO `transaction_supply_main` (`TransactionID`, `ProductID`, `Quantity`, `TotalPrice`) VALUES
(5, 3, 20, 5000),
(5, 3, 10, 2500),
(5, 3, 10, 2500),
(6, 3, 10, 2500),
(6, 3, 20, 5000),
(7, 3, 5, 1250),
(7, 3, 15, 3750),
(8, 3, 5, 1250),
(8, 3, 10, 2500),
(8, 3, 10, 2500);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
