

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

CREATE DATABASE IF NOT EXISTS pannes;

use pannes;

-- --------------------------------------------------------

--
-- Structure de la table 'books'
--

CREATE TABLE IF NOT EXISTS Pannes (
  id int (3) NOT NULL AUTO_INCREMENT,
  idMachine varchar(16) NOT NULL,
  typeMachine varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  datePanne datetime NOT NULL,
  etat BOOL DEFAULT 0, 
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'books'
--

INSERT INTO Pannes (id, idMachine, typeMachine, name, datePanne) VALUES
(1,'AAAAAAAAAAAA0001',  'Serveur', 'Reseau', NOW()),
(2,'AAAAAAAAAAAA0001',  'Serveur', 'Reseau', NOW()),
(3,'AAAAAAAAAAAA0001',  'Serveur', 'Reseau', NOW()),
(4,'AAAAAAAAAAAA0001',  'Serveur', 'Reseau', NOW()),
(5,'AAAAAAAAAAAA0001',  'Serveur', 'Reseau', NOW()),
(6,'AAAAAAAAAAAA0001',  'Serveur', 'Reseau', NOW());

INSERT INTO Pannes (id, idMachine, typeMachine, name, datePanne,etat) VALUES
(7,'AAAAAAAAAAAA0001',  'Serveur', 'Reseau', NOW(),1); --test resolue

