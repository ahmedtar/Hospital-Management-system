-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2020 at 09:13 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hopital0`
--

-- --------------------------------------------------------

--
-- Table structure for table `chambre`
--

CREATE TABLE `chambre` (
  `id` int(10) NOT NULL,
  `enService` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chambre`
--

INSERT INTO `chambre` (`id`, `enService`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `lit`
--

CREATE TABLE `lit` (
  `id` int(10) NOT NULL,
  `enService` tinyint(1) NOT NULL,
  `chambre` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lit`
--

INSERT INTO `lit` (`id`, `enService`, `chambre`) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 1, 2),
(4, 1, 2),
(5, 1, 3),
(6, 1, 3),
(7, 1, 4),
(8, 1, 4),
(9, 1, 5),
(10, 1, 5),
(11, 1, 6),
(12, 1, 6),
(13, 1, 7),
(14, 1, 7),
(15, 1, 8),
(16, 1, 8),
(17, 1, 9),
(18, 1, 9),
(19, 1, 10),
(20, 1, 10),
(21, 1, 11),
(22, 1, 11),
(23, 1, 12),
(24, 1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `medecin`
--

CREATE TABLE `medecin` (
  `id` int(10) NOT NULL,
  `cne` varchar(10) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `age` int(3) NOT NULL,
  `numTel` varchar(10) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `specialisation` varchar(30) NOT NULL,
  `enservice` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medecin`
--

INSERT INTO `medecin` (`id`, `cne`, `nom`, `prenom`, `sexe`, `age`, `numTel`, `adresse`, `specialisation`, `enservice`) VALUES
(4, 'EE213655', 'smiytTbiba', 'kniytha', 'homme', 20, '0632302864', 'N 33 bis marrakech', 'radio', 1),
(6, 'EEnsjnjsvd', 'smiytTbiba', 'kniytha', 'femme', 30, '0632302877', 'N 33 bis marrakech', 'radio', 0),
(10, 'aklhe', 'aejzrh', 'azleh', 'homme', 10, 'azleh', 'azlkeh', 'azlke', 1);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(10) NOT NULL,
  `lit` int(10) DEFAULT NULL,
  `cne` varchar(10) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `age` int(5) NOT NULL,
  `numTel` varchar(10) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `maladie` varchar(30) NOT NULL,
  `dateEntree` varchar(30) DEFAULT NULL,
  `dateSortie` varchar(30) DEFAULT NULL,
  `medecin` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `lit`, `cne`, `nom`, `prenom`, `sexe`, `age`, `numTel`, `adresse`, `maladie`, `dateEntree`, `dateSortie`, `medecin`) VALUES
(4, 1, 'EE213655', 'ahmed', 'elatrouz', 'Homme', 25, '0632355864', 'N 33 bis marrakech', 'cancer', '10/11/2020', '15/04/2004', 4),
(40, 10, 'EE123456', 'ahmed', 'elatrouz', 'homme', 20, '0635555864', 'N 33 bis marrakech', 'cancer', '09/10/2013', NULL, NULL),
(43, 3, 'ksnk', 'ksnk', 'nsfkn', 'Homme', 15, '64499797', 'mdamlmlmsdvk', 'haha', '10/01/2020', '01/05/2021', NULL),
(44, 21, 'lit', 'lit', '', 'Homme', 20, '', '', '', '11/01/2020', '11/01/2020', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(10) NOT NULL,
  `login` varchar(15) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `estAdmin` tinyint(1) NOT NULL,
  `estActif` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `nom`, `prenom`, `password`, `estAdmin`, `estActif`) VALUES
(1, 'hooola', 'ahmed', 'kniyti', 'password', 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lit`
--
ALTER TABLE `lit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chambre` (`chambre`);

--
-- Indexes for table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numTel` (`numTel`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numTel` (`numTel`),
  ADD UNIQUE KEY `lit` (`lit`),
  ADD KEY `clé étrangère medecin-patient` (`medecin`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `lit`
--
ALTER TABLE `lit`
  ADD CONSTRAINT `lit_ibfk_1` FOREIGN KEY (`chambre`) REFERENCES `chambre` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `clé étrangère medecin-patient` FOREIGN KEY (`medecin`) REFERENCES `medecin` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`lit`) REFERENCES `lit` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
