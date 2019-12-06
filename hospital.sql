-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 06 déc. 2019 à 21:29
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
-- Base de données :  `hospital`
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
-- Structure de la table `departement`
--

CREATE TABLE `departement` (
  `id` int(10) NOT NULL,
  `nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id`, `nom`) VALUES
(17, 'chirurgie'),
(18, 'Geriatrie Autonomie'),
(19, 'Médecine - Femme Mère Enfant');

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
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `cne` varchar(8) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `age` int(3) NOT NULL,
  `numTel` varchar(10) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `specialisation` varchar(30) NOT NULL,
  `departement` int(30) NOT NULL,
  `enservice` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `nom`, `prenom`, `cne`, `sexe`, `age`, `numTel`, `adresse`, `specialisation`, `departement`, `enservice`) VALUES
(4, 'smiytTbiba', 'kniytha', '', 'femme', 20, '0632302864', 'N 33 bis marrakech', 'radio', 17, 0),
(14, 'daou', 'abdeer', 'EE825427', 'home', 21, '063741126', 'Marrakech', '9alb wa charaiin', 17, 1),
(16, 'daoufa', 'marouan', 'EE825427', 'home', 21, '0637426', 'Marrakech', '9alb wa charaiin', 17, 1);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` int(10) NOT NULL,
  `lit` int(10) DEFAULT NULL,
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

INSERT INTO `patient` (`id`, `lit`, `nom`, `prenom`, `sexe`, `age`, `numTel`, `adresse`, `maladie`, `dateEntree`, `dateSortie`, `medecin`) VALUES
(4, 1, 'ahmed', 'elatrouz', 'homme', 20, '0632355864', 'N 33 bis marrakech', 'cancer', 'Sun Oct 10 00:00:00 WET 1999', 'Sun Oct 10 00:00:00 WET 1999', 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

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
  ADD KEY `departement` (`departement`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numTel` (`numTel`),
  ADD UNIQUE KEY `lit` (`lit`),
  ADD KEY `clé étrangère medecin-patient` (`medecin`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `departement`
--
ALTER TABLE `departement`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `lit`
--
ALTER TABLE `lit`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `lit`
--
ALTER TABLE `lit`
  ADD CONSTRAINT `lit_ibfk_1` FOREIGN KEY (`chambre`) REFERENCES `chambre` (`id`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD CONSTRAINT `medecin_ibfk_1` FOREIGN KEY (`departement`) REFERENCES `departement` (`id`) ON UPDATE CASCADE;

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