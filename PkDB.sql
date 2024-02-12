CREATE database IF NOT EXISTS `pokemon`;
use pokemon;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abilities`
--

DROP TABLE IF EXISTS `abilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abilities` (
  `abil_id` int NOT NULL AUTO_INCREMENT,
  `abil_name` varchar(79) NOT NULL,
  PRIMARY KEY (`abil_id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abilities`
--

LOCK TABLES `abilities` WRITE;
/*!40000 ALTER TABLE `abilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `abilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `att_def_hp`
--

DROP TABLE IF EXISTS `att_def_hp`;
/*!50001 DROP VIEW IF EXISTS `att_def_hp`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `att_def_hp` AS SELECT 
 1 AS `pok_id`,
 1 AS `pok_name`,
 1 AS `b_atk`,
 1 AS `b_def`,
 1 AS `b_hp`,
 1 AS `b_speed`,
 1 AS `b_sp_atk`,
 1 AS `b_sp_def`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `base_stats`
--

DROP TABLE IF EXISTS `base_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base_stats` (
  `b_hp` int DEFAULT NULL,
  `b_atk` int DEFAULT NULL,
  `b_def` int DEFAULT NULL,
  `b_sp_atk` int DEFAULT NULL,
  `b_sp_def` int DEFAULT NULL,
  `b_speed` int DEFAULT NULL,
  `stats_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`stats_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_stats`
--

LOCK TABLES `base_stats` WRITE;
/*!40000 ALTER TABLE `base_stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `habitat_view`
--

DROP TABLE IF EXISTS `habitat_view`;
/*!50001 DROP VIEW IF EXISTS `habitat_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `habitat_view` AS SELECT 
 1 AS `pok_id`,
 1 AS `pok_name`,
 1 AS `hab_name`,
 1 AS `pok_height`,
 1 AS `pok_weight`,
 1 AS `capture_rate`,
 1 AS `base_happiness`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `moves`
--

DROP TABLE IF EXISTS `moves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moves` (
  `move_id` int NOT NULL AUTO_INCREMENT,
  `move_name` varchar(79) NOT NULL,
  `type_id` int NOT NULL,
  `move_power` smallint DEFAULT NULL,
  `move_pp` smallint DEFAULT NULL,
  `move_accuracy` smallint DEFAULT NULL,
  PRIMARY KEY (`move_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `moves_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `types` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=622 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moves`
--

LOCK TABLES `moves` WRITE;
/*!40000 ALTER TABLE `moves` DISABLE KEYS */;
/*!40000 ALTER TABLE `moves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `pok_abilities`
--

DROP TABLE IF EXISTS `pok_abilities`;
/*!50001 DROP VIEW IF EXISTS `pok_abilities`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `pok_abilities` AS SELECT 
 1 AS `pok_id`,
 1 AS `pok_name`,
 1 AS `abil_name`,
 1 AS `is_hidden`,
 1 AS `pok_base_experience`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `pokemon`
--

DROP TABLE IF EXISTS `pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pok_name` varchar(79) NOT NULL,
  `pok_height` int DEFAULT NULL,
  `pok_weight` int DEFAULT NULL,
  `pok_base_experience` int DEFAULT NULL,
  `stats_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stats_id` (`stats_id`),
  CONSTRAINT `fk_stats_id` FOREIGN KEY (`stats_id`) REFERENCES `base_stats` (`stats_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1028 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon_abilities`
--

DROP TABLE IF EXISTS `pokemon_abilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon_abilities` (
  `pok_id` int NOT NULL,
  `abil_id` int NOT NULL,
  `is_hidden` tinyint(1) NOT NULL,
  `slot` int NOT NULL,
  PRIMARY KEY (`pok_id`,`slot`),
  KEY `abil_id` (`abil_id`),
  KEY `ix_pokemon_abilities_is_hidden` (`is_hidden`),
  CONSTRAINT `pokemon_abilities_ibfk_1` FOREIGN KEY (`pok_id`) REFERENCES `pokemon` (`id`),
  CONSTRAINT `pokemon_abilities_ibfk_2` FOREIGN KEY (`abil_id`) REFERENCES `abilities` (`abil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon_abilities`
--

LOCK TABLES `pokemon_abilities` WRITE;
/*!40000 ALTER TABLE `pokemon_abilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon_abilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon_evolution`
--

DROP TABLE IF EXISTS `pokemon_evolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon_evolution` (
  `evol_id` int NOT NULL AUTO_INCREMENT,
  `evolved_species_id` int NOT NULL,
  `evol_minimum_level` int DEFAULT NULL,
  PRIMARY KEY (`evol_id`),
  KEY `evolved_species_id` (`evolved_species_id`),
  CONSTRAINT `pokemon_evolution_ibfk_1` FOREIGN KEY (`evolved_species_id`) REFERENCES `pokemon_evolution_matchup` (`pok_id`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon_evolution`
--

LOCK TABLES `pokemon_evolution` WRITE;
/*!40000 ALTER TABLE `pokemon_evolution` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon_evolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon_evolution_matchup`
--

DROP TABLE IF EXISTS `pokemon_evolution_matchup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon_evolution_matchup` (
  `pok_id` int NOT NULL AUTO_INCREMENT,
  `evolves_from_species_id` int DEFAULT NULL,
  `hab_id` int DEFAULT NULL,
  `gender_rate` int NOT NULL,
  `capture_rate` int NOT NULL,
  `base_happiness` int NOT NULL,
  PRIMARY KEY (`pok_id`),
  KEY `evolves_from_species_id` (`evolves_from_species_id`),
  KEY `habitat_id` (`hab_id`),
  CONSTRAINT `poke_fk` FOREIGN KEY (`pok_id`) REFERENCES `pokemon` (`id`),
  CONSTRAINT `pokemon_evolution_matchup_ibfk_6` FOREIGN KEY (`hab_id`) REFERENCES `pokemon_habitats` (`hab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=722 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon_evolution_matchup`
--

LOCK TABLES `pokemon_evolution_matchup` WRITE;
/*!40000 ALTER TABLE `pokemon_evolution_matchup` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon_evolution_matchup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon_habitats`
--

DROP TABLE IF EXISTS `pokemon_habitats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon_habitats` (
  `hab_id` int NOT NULL,
  `hab_name` varchar(79) NOT NULL,
  `hab_descript` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`hab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon_habitats`
--

LOCK TABLES `pokemon_habitats` WRITE;
/*!40000 ALTER TABLE `pokemon_habitats` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon_habitats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon_move_methods`
--

DROP TABLE IF EXISTS `pokemon_move_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon_move_methods` (
  `method_id` int NOT NULL,
  `method_name` varchar(79) NOT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon_move_methods`
--

LOCK TABLES `pokemon_move_methods` WRITE;
/*!40000 ALTER TABLE `pokemon_move_methods` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon_move_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon_moves`
--

DROP TABLE IF EXISTS `pokemon_moves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon_moves` (
  `pok_id` int NOT NULL,
  `version_group_id` int NOT NULL,
  `move_id` int NOT NULL,
  `method_id` int NOT NULL,
  `level` int NOT NULL,
  PRIMARY KEY (`pok_id`,`version_group_id`,`move_id`,`method_id`,`level`),
  KEY `ix_pokemon_moves_level` (`level`),
  KEY `ix_pokemon_moves_version_group_id` (`version_group_id`),
  KEY `ix_pokemon_moves_move_id` (`move_id`),
  KEY `ix_pokemon_moves_pokemon_move_method_id` (`method_id`),
  KEY `ix_pokemon_moves_pokemon_id` (`pok_id`),
  CONSTRAINT `pokemon_moves_ibfk_1` FOREIGN KEY (`pok_id`) REFERENCES `pokemon` (`id`),
  CONSTRAINT `pokemon_moves_ibfk_2` FOREIGN KEY (`move_id`) REFERENCES `moves` (`move_id`),
  CONSTRAINT `pokemon_moves_ibfk_3` FOREIGN KEY (`version_group_id`) REFERENCES `version_groups` (`version_id`),
  CONSTRAINT `pokemon_moves_ibfk_4` FOREIGN KEY (`method_id`) REFERENCES `pokemon_move_methods` (`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon_moves`
--

LOCK TABLES `pokemon_moves` WRITE;
/*!40000 ALTER TABLE `pokemon_moves` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon_moves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon_types`
--

DROP TABLE IF EXISTS `pokemon_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon_types` (
  `pok_id` int NOT NULL,
  `type_id` int NOT NULL,
  `slot` int NOT NULL,
  `pt_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pt_id`),
  KEY `type_id` (`type_id`),
  KEY `pok_id` (`pok_id`),
  CONSTRAINT `pokemon_types_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `types` (`type_id`),
  CONSTRAINT `pokemon_types_ibfk_2` FOREIGN KEY (`pok_id`) REFERENCES `pokemon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1538 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon_types`
--

LOCK TABLES `pokemon_types` WRITE;
/*!40000 ALTER TABLE `pokemon_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemon_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `top_10_view`
--

DROP TABLE IF EXISTS `top_10_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `top_10_view` (
  `pok_id` int DEFAULT NULL,
  `pok_name` int DEFAULT NULL,
  `b_atk` int DEFAULT NULL,
  `b_def` int DEFAULT NULL,
  `b_hp` int DEFAULT NULL,
  `b_speed` int DEFAULT NULL,
  `b_sp_atk` int DEFAULT NULL,
  `b_sp_def` int DEFAULT NULL,
  `TOTAL` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `top_10_view`
--

LOCK TABLES `top_10_view` WRITE;
/*!40000 ALTER TABLE `top_10_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `top_10_view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_efficacy`
--

DROP TABLE IF EXISTS `type_efficacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_efficacy` (
  `damage_type_id` int NOT NULL,
  `target_type_id` int NOT NULL,
  `damage_factor` int NOT NULL,
  PRIMARY KEY (`damage_type_id`,`target_type_id`),
  KEY `target_type_id` (`target_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_efficacy`
--

LOCK TABLES `type_efficacy` WRITE;
/*!40000 ALTER TABLE `type_efficacy` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_efficacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `types` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(79) NOT NULL,
  `damage_type_id` int DEFAULT NULL,
  PRIMARY KEY (`type_id`),
  KEY `damage_type_idx` (`damage_type_id`),
  CONSTRAINT `damage_type` FOREIGN KEY (`damage_type_id`) REFERENCES `type_efficacy` (`damage_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `version_groups`
--

DROP TABLE IF EXISTS `version_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `version_groups` (
  `version_id` int NOT NULL AUTO_INCREMENT,
  `version_name` varchar(79) NOT NULL,
  `order` int DEFAULT NULL,
  PRIMARY KEY (`version_id`),
  UNIQUE KEY `identifier` (`version_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version_groups`
--

LOCK TABLES `version_groups` WRITE;
/*!40000 ALTER TABLE `version_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `version_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `att_def_hp`
--

/*!50001 DROP VIEW IF EXISTS `att_def_hp`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `att_def_hp` AS select 1 AS `pok_id`,1 AS `pok_name`,1 AS `b_atk`,1 AS `b_def`,1 AS `b_hp`,1 AS `b_speed`,1 AS `b_sp_atk`,1 AS `b_sp_def` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `habitat_view`
--

/*!50001 DROP VIEW IF EXISTS `habitat_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `habitat_view` AS select 1 AS `pok_id`,1 AS `pok_name`,1 AS `hab_name`,1 AS `pok_height`,1 AS `pok_weight`,1 AS `capture_rate`,1 AS `base_happiness` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `pok_abilities`
--

/*!50001 DROP VIEW IF EXISTS `pok_abilities`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pok_abilities` AS select 1 AS `pok_id`,1 AS `pok_name`,1 AS `abil_name`,1 AS `is_hidden`,1 AS `pok_base_experience` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;