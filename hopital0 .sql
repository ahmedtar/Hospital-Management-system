-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 12 jan. 2020 à 02:07
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `hopital0`
--

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

CREATE TABLE `chambre` (
  `id` int(10) NOT NULL,
  `enService` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chambre`
--

INSERT INTO `chambre` (`id`, `enService`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `lit`
--

CREATE TABLE `lit` (
  `id` int(10) NOT NULL,
  `enService` tinyint(1) NOT NULL,
  `chambre` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lit`
--

INSERT INTO `lit` (`id`, `enService`, `chambre`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
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
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `cne`, `nom`, `prenom`, `sexe`, `age`, `numTel`, `adresse`, `specialisation`, `enservice`) VALUES
(4, 'EE213655', 'smiytTbiba', 'kniytha', 'homme', 20, '0632302864', 'N 33 bis marrakech', 'radio', 1),
(6, 'EEnsjnjsvd', 'smiytTbiba', 'kniytha', 'homme', 25, '0632302877', 'N 33 bis marrakech', 'radio', 1),
(10, 'kwkn', 'nskn', 'kndekn', 'homme', 25, 'fmkm', 'kdmk', 'ksdnkn', 1);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
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
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `lit`, `cne`, `nom`, `prenom`, `sexe`, `age`, `numTel`, `adresse`, `maladie`, `dateEntree`, `dateSortie`, `medecin`) VALUES
(4, 1, 'EE213655', 'ahmed', 'elatrouz', 'Homme', 25, '0632355864', 'N 33 bis marrakech', 'cancer', '10/11/2020', '15/04/2004', 4),
(40, NULL, 'EE123456', 'ahmed', 'elatrouz', 'homme', 20, '0635555864', 'N 33 bis marrakech', 'cancer', '09/10/2013', NULL, NULL),
(43, NULL, 'ksnk', 'ksnk', 'nsfkn', 'Homme', 15, '64499797', 'mdamlmlmsdvk', 'haha', '10/01/2020', '01/05/2021', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(10) NOT NULL,
  `login` varchar(15) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `estActif` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `nom`, `prenom`, `password`, `estActif`) VALUES
(1, 'hooola', 'Ahmed', 'El Atrouz', 'ahmed1234', 0),
(2, 'ippo', 'dafali', 'youssef', 'dafali12345', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `lit`
--
ALTER TABLE `lit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chambre` (`chambre`);

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numTel` (`numTel`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numTel` (`numTel`),
  ADD UNIQUE KEY `lit` (`lit`),
  ADD KEY `clé étrangère medecin-patient` (`medecin`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `lit`
--
ALTER TABLE `lit`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `lit`
--
ALTER TABLE `lit`
  ADD CONSTRAINT `lit_ibfk_1` FOREIGN KEY (`chambre`) REFERENCES `chambre` (`id`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `clé étrangère medecin-patient` FOREIGN KEY (`medecin`) REFERENCES `medecin` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`lit`) REFERENCES `lit` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
