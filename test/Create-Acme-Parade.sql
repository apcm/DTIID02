-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Parade
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor_boxes`
--

DROP TABLE IF EXISTS `actor_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_boxes` (
  `actor` int(11) NOT NULL,
  `boxes` int(11) NOT NULL,
  UNIQUE KEY `UK_6n6psqivvjho155qcf9kjvv1h` (`boxes`),
  CONSTRAINT `FK_6n6psqivvjho155qcf9kjvv1h` FOREIGN KEY (`boxes`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_boxes`
--

LOCK TABLES `actor_boxes` WRITE;
/*!40000 ALTER TABLE `actor_boxes` DISABLE KEYS */;
INSERT INTO `actor_boxes` VALUES (17,26),(17,27),(17,28),(17,29),(17,30);
/*!40000 ALTER TABLE `actor_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_social_profiles`
--

DROP TABLE IF EXISTS `actor_social_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_social_profiles` (
  `actor` int(11) NOT NULL,
  `social_profiles` int(11) NOT NULL,
  UNIQUE KEY `UK_4suhrykpl9af1ubs85ycbyt6q` (`social_profiles`),
  CONSTRAINT `FK_4suhrykpl9af1ubs85ycbyt6q` FOREIGN KEY (`social_profiles`) REFERENCES `social_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_social_profiles`
--

LOCK TABLES `actor_social_profiles` WRITE;
/*!40000 ALTER TABLE `actor_social_profiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor_social_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ban` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `polarity_score` double NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `administratorUK_r2b0o8w4fq9jh0iwma405gx6t` (`flag_spam`),
  KEY `FK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (17,0,'C/ Wednesday, nº 1','\0','admin1@','\0','','administrator1','+34 654456248','http://wwww.photo1.com',0,'Exposito',16);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area_pictures`
--

DROP TABLE IF EXISTS `area_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area_pictures` (
  `area` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_s2y5bun5v8b608aoptnxfuelm` (`area`),
  CONSTRAINT `FK_s2y5bun5v8b608aoptnxfuelm` FOREIGN KEY (`area`) REFERENCES `area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_pictures`
--

LOCK TABLES `area_pictures` WRITE;
/*!40000 ALTER TABLE `area_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `area_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `predefined` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (26,0,'in box',''),(27,0,'notification box',''),(28,0,'out box',''),(29,0,'trash box',''),(30,0,'spam box','');
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box_descendants`
--

DROP TABLE IF EXISTS `box_descendants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box_descendants` (
  `box` int(11) NOT NULL,
  `descendants` int(11) NOT NULL,
  UNIQUE KEY `UK_74qw56098ug0l89hu661fgyue` (`descendants`),
  KEY `FK_hkmtr3h9lbvxff4km6aq1056b` (`box`),
  CONSTRAINT `FK_hkmtr3h9lbvxff4km6aq1056b` FOREIGN KEY (`box`) REFERENCES `box` (`id`),
  CONSTRAINT `FK_74qw56098ug0l89hu661fgyue` FOREIGN KEY (`descendants`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box_descendants`
--

LOCK TABLES `box_descendants` WRITE;
/*!40000 ALTER TABLE `box_descendants` DISABLE KEYS */;
/*!40000 ALTER TABLE `box_descendants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box_messages`
--

DROP TABLE IF EXISTS `box_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box_messages` (
  `box` int(11) NOT NULL,
  `messages` int(11) NOT NULL,
  KEY `FK_acfjrqu1jeixjmv14c0386o0s` (`messages`),
  KEY `FK_e6boieojekgfg919on0dci4na` (`box`),
  CONSTRAINT `FK_e6boieojekgfg919on0dci4na` FOREIGN KEY (`box`) REFERENCES `box` (`id`),
  CONSTRAINT `FK_acfjrqu1jeixjmv14c0386o0s` FOREIGN KEY (`messages`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box_messages`
--

LOCK TABLES `box_messages` WRITE;
/*!40000 ALTER TABLE `box_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `box_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brotherhood`
--

DROP TABLE IF EXISTS `brotherhood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brotherhood` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ban` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `polarity_score` double NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `stablishment_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  `inception_record` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `brotherhoodUK_r2b0o8w4fq9jh0iwma405gx6t` (`flag_spam`),
  KEY `FK_oku65kpdi3ro8ta0bmmxdkidt` (`area`),
  KEY `FK_sjfyfb5d8xhpsobrv2wyfd5nt` (`inception_record`),
  KEY `FK_j7wkl7fdmmjo3c5wa21wo8nl` (`user_account`),
  CONSTRAINT `FK_j7wkl7fdmmjo3c5wa21wo8nl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_oku65kpdi3ro8ta0bmmxdkidt` FOREIGN KEY (`area`) REFERENCES `area` (`id`),
  CONSTRAINT `FK_sjfyfb5d8xhpsobrv2wyfd5nt` FOREIGN KEY (`inception_record`) REFERENCES `inception_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brotherhood`
--

LOCK TABLES `brotherhood` WRITE;
/*!40000 ALTER TABLE `brotherhood` DISABLE KEYS */;
/*!40000 ALTER TABLE `brotherhood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brotherhood_legal_records`
--

DROP TABLE IF EXISTS `brotherhood_legal_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brotherhood_legal_records` (
  `brotherhood` int(11) NOT NULL,
  `legal_records` int(11) NOT NULL,
  UNIQUE KEY `UK_pwjfopj2ajphd007jcik7cd3i` (`legal_records`),
  KEY `FK_s52agmq573hbkr2ieclnhr471` (`brotherhood`),
  CONSTRAINT `FK_s52agmq573hbkr2ieclnhr471` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`),
  CONSTRAINT `FK_pwjfopj2ajphd007jcik7cd3i` FOREIGN KEY (`legal_records`) REFERENCES `legal_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brotherhood_legal_records`
--

LOCK TABLES `brotherhood_legal_records` WRITE;
/*!40000 ALTER TABLE `brotherhood_legal_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `brotherhood_legal_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brotherhood_link_records`
--

DROP TABLE IF EXISTS `brotherhood_link_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brotherhood_link_records` (
  `brotherhood` int(11) NOT NULL,
  `link_records` int(11) NOT NULL,
  UNIQUE KEY `UK_dmyld4v9efqnis04yfqk5kevi` (`link_records`),
  KEY `FK_mr4s8qyhy9jrykyq1s2pyxaos` (`brotherhood`),
  CONSTRAINT `FK_mr4s8qyhy9jrykyq1s2pyxaos` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`),
  CONSTRAINT `FK_dmyld4v9efqnis04yfqk5kevi` FOREIGN KEY (`link_records`) REFERENCES `link_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brotherhood_link_records`
--

LOCK TABLES `brotherhood_link_records` WRITE;
/*!40000 ALTER TABLE `brotherhood_link_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `brotherhood_link_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brotherhood_period_records`
--

DROP TABLE IF EXISTS `brotherhood_period_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brotherhood_period_records` (
  `brotherhood` int(11) NOT NULL,
  `period_records` int(11) NOT NULL,
  UNIQUE KEY `UK_ouibt186arutxkd3ukpevy7k8` (`period_records`),
  KEY `FK_4in9a89yk3usr3rg01s3vov0t` (`brotherhood`),
  CONSTRAINT `FK_4in9a89yk3usr3rg01s3vov0t` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`),
  CONSTRAINT `FK_ouibt186arutxkd3ukpevy7k8` FOREIGN KEY (`period_records`) REFERENCES `period_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brotherhood_period_records`
--

LOCK TABLES `brotherhood_period_records` WRITE;
/*!40000 ALTER TABLE `brotherhood_period_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `brotherhood_period_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brotherhood_urls`
--

DROP TABLE IF EXISTS `brotherhood_urls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brotherhood_urls` (
  `brotherhood` int(11) NOT NULL,
  `urls` varchar(255) DEFAULT NULL,
  KEY `FK_btl3cy9sptr0ug8wa8mmjk5sh` (`brotherhood`),
  CONSTRAINT `FK_btl3cy9sptr0ug8wa8mmjk5sh` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brotherhood_urls`
--

LOCK TABLES `brotherhood_urls` WRITE;
/*!40000 ALTER TABLE `brotherhood_urls` DISABLE KEYS */;
/*!40000 ALTER TABLE `brotherhood_urls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ban` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `polarity_score` double NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chapterUK_r2b0o8w4fq9jh0iwma405gx6t` (`flag_spam`),
  KEY `UK_g1jjg80txjhuvgdkb84om9q9p` (`area`),
  KEY `FK_j0iwie78xmrf4kapbyfbgl8uo` (`user_account`),
  CONSTRAINT `FK_j0iwie78xmrf4kapbyfbgl8uo` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_g1jjg80txjhuvgdkb84om9q9p` FOREIGN KEY (`area`) REFERENCES `area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cvv` int(11) NOT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation`
--

DROP TABLE IF EXISTS `customisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `bannerurl` varchar(255) DEFAULT NULL,
  `fare` double DEFAULT NULL,
  `finder_duration` int(11) DEFAULT NULL,
  `phone_number_country_code` varchar(255) DEFAULT NULL,
  `results_number` int(11) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `vat_percentage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation`
--

LOCK TABLES `customisation` WRITE;
/*!40000 ALTER TABLE `customisation` DISABLE KEYS */;
INSERT INTO `customisation` VALUES (18,0,'https://i.ibb.co/dGyn5JP/banner.jpg',15,24,'+34 ',10,'Acme Madruga',21);
/*!40000 ALTER TABLE `customisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_credit_card_makes`
--

DROP TABLE IF EXISTS `customisation_credit_card_makes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_credit_card_makes` (
  `customisation` int(11) NOT NULL,
  `credit_card_makes` varchar(255) DEFAULT NULL,
  KEY `FK_my1ddakb8b5rim2xvdvl6sxit` (`customisation`),
  CONSTRAINT `FK_my1ddakb8b5rim2xvdvl6sxit` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_credit_card_makes`
--

LOCK TABLES `customisation_credit_card_makes` WRITE;
/*!40000 ALTER TABLE `customisation_credit_card_makes` DISABLE KEYS */;
INSERT INTO `customisation_credit_card_makes` VALUES (18,'VISA'),(18,'MCARD'),(18,'AMEX'),(18,'DINNERS'),(18,'FLY');
/*!40000 ALTER TABLE `customisation_credit_card_makes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_message_priorities`
--

DROP TABLE IF EXISTS `customisation_message_priorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_message_priorities` (
  `customisation` int(11) NOT NULL,
  `message_priorities` varchar(255) DEFAULT NULL,
  KEY `FK_bduoja9slht9cjylhvg8r0w6i` (`customisation`),
  CONSTRAINT `FK_bduoja9slht9cjylhvg8r0w6i` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_message_priorities`
--

LOCK TABLES `customisation_message_priorities` WRITE;
/*!40000 ALTER TABLE `customisation_message_priorities` DISABLE KEYS */;
INSERT INTO `customisation_message_priorities` VALUES (18,'LOW'),(18,'HIGH'),(18,'NEUTRAL');
/*!40000 ALTER TABLE `customisation_message_priorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_negative_words`
--

DROP TABLE IF EXISTS `customisation_negative_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_negative_words` (
  `customisation` int(11) NOT NULL,
  `negative_words` varchar(255) DEFAULT NULL,
  KEY `FK_opq3ccp6xd91eiboxkjkgb8fw` (`customisation`),
  CONSTRAINT `FK_opq3ccp6xd91eiboxkjkgb8fw` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_negative_words`
--

LOCK TABLES `customisation_negative_words` WRITE;
/*!40000 ALTER TABLE `customisation_negative_words` DISABLE KEYS */;
INSERT INTO `customisation_negative_words` VALUES (18,'NOT'),(18,'BAD'),(18,'HORRIBLE'),(18,'AVERAGE'),(18,'DISASTER'),(18,'NO'),(18,'MALO'),(18,'HORRIBLE'),(18,'MEDIOCRE'),(18,'DESASTRE');
/*!40000 ALTER TABLE `customisation_negative_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_positive_words`
--

DROP TABLE IF EXISTS `customisation_positive_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_positive_words` (
  `customisation` int(11) NOT NULL,
  `positive_words` varchar(255) DEFAULT NULL,
  KEY `FK_hp9mp69hiihs6rx43l3vv0l4t` (`customisation`),
  CONSTRAINT `FK_hp9mp69hiihs6rx43l3vv0l4t` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_positive_words`
--

LOCK TABLES `customisation_positive_words` WRITE;
/*!40000 ALTER TABLE `customisation_positive_words` DISABLE KEYS */;
INSERT INTO `customisation_positive_words` VALUES (18,'GOOD'),(18,'FANTASTIC'),(18,'EXCELENT'),(18,'GREAT'),(18,'AMAZING'),(18,'TERRIFIC'),(18,'BEAUTIFUL'),(18,'BUENO'),(18,'FANTASTICO'),(18,'EXCELENTE'),(18,'GENIAL'),(18,'MARAVILLOSO'),(18,'ESTUPENDO'),(18,'BEAUTIFUL');
/*!40000 ALTER TABLE `customisation_positive_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_spam_words`
--

DROP TABLE IF EXISTS `customisation_spam_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_spam_words` (
  `customisation` int(11) NOT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  KEY `FK_c6m5a0x35im5g3hgaim7j4lpu` (`customisation`),
  CONSTRAINT `FK_c6m5a0x35im5g3hgaim7j4lpu` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_spam_words`
--

LOCK TABLES `customisation_spam_words` WRITE;
/*!40000 ALTER TABLE `customisation_spam_words` DISABLE KEYS */;
INSERT INTO `customisation_spam_words` VALUES (18,'SEX'),(18,'VIAGRA'),(18,'CIALIS'),(18,'NIGERIA'),(18,'ONE MILLION'),(18,'YOU\'VE BEEN SELECTED'),(18,'SEXO'),(18,'UN MILLON'),(18,'HAS SIDO SELECCIONADO');
/*!40000 ALTER TABLE `customisation_spam_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_welcome_message`
--

DROP TABLE IF EXISTS `customisation_welcome_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_welcome_message` (
  `customisation` int(11) NOT NULL,
  `welcome_message` varchar(255) DEFAULT NULL,
  KEY `FK_ggfbvqpspf4ep654ea9j0bs2c` (`customisation`),
  CONSTRAINT `FK_ggfbvqpspf4ep654ea9j0bs2c` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_welcome_message`
--

LOCK TABLES `customisation_welcome_message` WRITE;
/*!40000 ALTER TABLE `customisation_welcome_message` DISABLE KEYS */;
INSERT INTO `customisation_welcome_message` VALUES (18,'Welcome to Acme Parade! The site to organise your parades.'),(18,'¡Bienvenidos a Acme Parade! Tu sitio para organizar desfiles.');
/*!40000 ALTER TABLE `customisation_welcome_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrolement`
--

DROP TABLE IF EXISTS `enrolement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrolement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `drop_out_moment` datetime DEFAULT NULL,
  `enrol_moment` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `brotherhood` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_hnsa7uttnpce05hy545ghtkjo` (`status`,`drop_out_moment`),
  KEY `FK_gh833la0uib12m1slsexb03rr` (`brotherhood`),
  KEY `FK_gimq8q1gf3vqbg8cfvattws4a` (`position`),
  CONSTRAINT `FK_gimq8q1gf3vqbg8cfvattws4a` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_gh833la0uib12m1slsexb03rr` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrolement`
--

LOCK TABLES `enrolement` WRITE;
/*!40000 ALTER TABLE `enrolement` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrolement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `end_date` date DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_c5dgh3ryrfhvda88ni0b72o4e` (`keyword`,`start_date`,`end_date`),
  KEY `FK_rbjlwj1bksuusd3142rf62xyt` (`area`),
  CONSTRAINT `FK_rbjlwj1bksuusd3142rf62xyt` FOREIGN KEY (`area`) REFERENCES `area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_parades`
--

DROP TABLE IF EXISTS `finder_parades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_parades` (
  `finder` int(11) NOT NULL,
  `parades` int(11) NOT NULL,
  KEY `FK_a7t9ojmirwd7ijfq42ffessn5` (`parades`),
  KEY `FK_8pff5xgqq7qfh2ciyx24p67sp` (`finder`),
  CONSTRAINT `FK_8pff5xgqq7qfh2ciyx24p67sp` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_a7t9ojmirwd7ijfq42ffessn5` FOREIGN KEY (`parades`) REFERENCES `parade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_parades`
--

LOCK TABLES `finder_parades` WRITE;
/*!40000 ALTER TABLE `finder_parades` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_parades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `float`
--

DROP TABLE IF EXISTS `float`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `float` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `brotherhood` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bmjnirgvwerdv604sfiusq45v` (`brotherhood`),
  CONSTRAINT `FK_bmjnirgvwerdv604sfiusq45v` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `float`
--

LOCK TABLES `float` WRITE;
/*!40000 ALTER TABLE `float` DISABLE KEYS */;
/*!40000 ALTER TABLE `float` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `float_pictures`
--

DROP TABLE IF EXISTS `float_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `float_pictures` (
  `float` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_dp4g3ry840d4yqsjkifnm8q3t` (`float`),
  CONSTRAINT `FK_dp4g3ry840d4yqsjkifnm8q3t` FOREIGN KEY (`float`) REFERENCES `float` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `float_pictures`
--

LOCK TABLES `float_pictures` WRITE;
/*!40000 ALTER TABLE `float_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `float_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brotherhood` int(11) NOT NULL,
  `inception_record` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ft70pl0noyt3c2bufgsyepbbe` (`inception_record`),
  KEY `FK_cqm844lynupi3gfm0yjcinrlt` (`brotherhood`),
  CONSTRAINT `FK_ft70pl0noyt3c2bufgsyepbbe` FOREIGN KEY (`inception_record`) REFERENCES `inception_record` (`id`),
  CONSTRAINT `FK_cqm844lynupi3gfm0yjcinrlt` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_legal_records`
--

DROP TABLE IF EXISTS `history_legal_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_legal_records` (
  `history` int(11) NOT NULL,
  `legal_records` int(11) NOT NULL,
  UNIQUE KEY `UK_4cwwxrddivsxn1pdy0vhb1ywj` (`legal_records`),
  KEY `FK_6q06x59ns3cq8hmomnvifnrl` (`history`),
  CONSTRAINT `FK_6q06x59ns3cq8hmomnvifnrl` FOREIGN KEY (`history`) REFERENCES `history` (`id`),
  CONSTRAINT `FK_4cwwxrddivsxn1pdy0vhb1ywj` FOREIGN KEY (`legal_records`) REFERENCES `legal_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_legal_records`
--

LOCK TABLES `history_legal_records` WRITE;
/*!40000 ALTER TABLE `history_legal_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_legal_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_link_records`
--

DROP TABLE IF EXISTS `history_link_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_link_records` (
  `history` int(11) NOT NULL,
  `link_records` int(11) NOT NULL,
  UNIQUE KEY `UK_bg9ynbbfa8vmxt2d8o0ih813c` (`link_records`),
  KEY `FK_i0x3qbnon99dx62swyxjpscdi` (`history`),
  CONSTRAINT `FK_i0x3qbnon99dx62swyxjpscdi` FOREIGN KEY (`history`) REFERENCES `history` (`id`),
  CONSTRAINT `FK_bg9ynbbfa8vmxt2d8o0ih813c` FOREIGN KEY (`link_records`) REFERENCES `link_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_link_records`
--

LOCK TABLES `history_link_records` WRITE;
/*!40000 ALTER TABLE `history_link_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_link_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_period_records`
--

DROP TABLE IF EXISTS `history_period_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_period_records` (
  `history` int(11) NOT NULL,
  `period_records` int(11) NOT NULL,
  UNIQUE KEY `UK_1butqdd9tg7u8ts0qq1gbsk4g` (`period_records`),
  KEY `FK_2n92fjhwuxbhgbrv5g4in69n2` (`history`),
  CONSTRAINT `FK_2n92fjhwuxbhgbrv5g4in69n2` FOREIGN KEY (`history`) REFERENCES `history` (`id`),
  CONSTRAINT `FK_1butqdd9tg7u8ts0qq1gbsk4g` FOREIGN KEY (`period_records`) REFERENCES `period_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_period_records`
--

LOCK TABLES `history_period_records` WRITE;
/*!40000 ALTER TABLE `history_period_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_period_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inception_record`
--

DROP TABLE IF EXISTS `inception_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inception_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inception_record`
--

LOCK TABLES `inception_record` WRITE;
/*!40000 ALTER TABLE `inception_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `inception_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inception_record_photos`
--

DROP TABLE IF EXISTS `inception_record_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inception_record_photos` (
  `inception_record` int(11) NOT NULL,
  `photos` varchar(255) DEFAULT NULL,
  KEY `FK_lgtjmb9yttfdlcia3lkdv6fhk` (`inception_record`),
  CONSTRAINT `FK_lgtjmb9yttfdlcia3lkdv6fhk` FOREIGN KEY (`inception_record`) REFERENCES `inception_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inception_record_photos`
--

LOCK TABLES `inception_record_photos` WRITE;
/*!40000 ALTER TABLE `inception_record_photos` DISABLE KEYS */;
/*!40000 ALTER TABLE `inception_record_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legal_record`
--

DROP TABLE IF EXISTS `legal_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legal_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `vat_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legal_record`
--

LOCK TABLES `legal_record` WRITE;
/*!40000 ALTER TABLE `legal_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `legal_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legal_record_applicable_laws`
--

DROP TABLE IF EXISTS `legal_record_applicable_laws`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legal_record_applicable_laws` (
  `legal_record` int(11) NOT NULL,
  `applicable_laws` varchar(255) DEFAULT NULL,
  KEY `FK_gory5m0vbph8i5npyxus8kxrf` (`legal_record`),
  CONSTRAINT `FK_gory5m0vbph8i5npyxus8kxrf` FOREIGN KEY (`legal_record`) REFERENCES `legal_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legal_record_applicable_laws`
--

LOCK TABLES `legal_record_applicable_laws` WRITE;
/*!40000 ALTER TABLE `legal_record_applicable_laws` DISABLE KEYS */;
/*!40000 ALTER TABLE `legal_record_applicable_laws` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_record`
--

DROP TABLE IF EXISTS `link_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `link_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_record`
--

LOCK TABLES `link_record` WRITE;
/*!40000 ALTER TABLE `link_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `link_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ban` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `polarity_score` double NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `finder` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mjoa5yw12sbvknx53x7fu5a1g` (`finder`),
  KEY `memberUK_r2b0o8w4fq9jh0iwma405gx6t` (`flag_spam`),
  KEY `FK_porqrqrfw70onhs6pelgepxhu` (`user_account`),
  CONSTRAINT `FK_porqrqrfw70onhs6pelgepxhu` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_mjoa5yw12sbvknx53x7fu5a1g` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_enrolements`
--

DROP TABLE IF EXISTS `member_enrolements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_enrolements` (
  `member` int(11) NOT NULL,
  `enrolements` int(11) NOT NULL,
  UNIQUE KEY `UK_suykc6yw9rfaiggu8651v6xrk` (`enrolements`),
  KEY `FK_6kdmawbomysqsug8qg5stmpg6` (`member`),
  CONSTRAINT `FK_6kdmawbomysqsug8qg5stmpg6` FOREIGN KEY (`member`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_suykc6yw9rfaiggu8651v6xrk` FOREIGN KEY (`enrolements`) REFERENCES `enrolement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_enrolements`
--

LOCK TABLES `member_enrolements` WRITE;
/*!40000 ALTER TABLE `member_enrolements` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_enrolements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_requests`
--

DROP TABLE IF EXISTS `member_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_requests` (
  `member` int(11) NOT NULL,
  `requests` int(11) NOT NULL,
  UNIQUE KEY `UK_j6fqoub4vhqsqdbgmhe22hbad` (`requests`),
  KEY `FK_pxtv78fqjxxvsxd4dd6x9k37m` (`member`),
  CONSTRAINT `FK_pxtv78fqjxxvsxd4dd6x9k37m` FOREIGN KEY (`member`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_j6fqoub4vhqsqdbgmhe22hbad` FOREIGN KEY (`requests`) REFERENCES `request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_requests`
--

LOCK TABLES `member_requests` WRITE;
/*!40000 ALTER TABLE `member_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` longtext,
  `broadcast` bit(1) NOT NULL,
  `flag_spam` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipients`
--

DROP TABLE IF EXISTS `message_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipients` (
  `message` int(11) NOT NULL,
  `recipients` int(11) NOT NULL,
  KEY `FK_1odmg2n3n487tvhuxx5oyyya2` (`message`),
  CONSTRAINT `FK_1odmg2n3n487tvhuxx5oyyya2` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipients`
--

LOCK TABLES `message_recipients` WRITE;
/*!40000 ALTER TABLE `message_recipients` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parade`
--

DROP TABLE IF EXISTS `parade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parade` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `departure_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) DEFAULT NULL,
  `reject_reason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `brotherhood` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5yuq215wrvxtjvi0gjynv32g7` (`ticker`),
  KEY `UK_73wy829yjujfjnb2vu6g02vdr` (`final_mode`,`departure_date`),
  KEY `FK_kdqyp1ypu4jvmvlxb5d1cvrx3` (`brotherhood`),
  CONSTRAINT `FK_kdqyp1ypu4jvmvlxb5d1cvrx3` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parade`
--

LOCK TABLES `parade` WRITE;
/*!40000 ALTER TABLE `parade` DISABLE KEYS */;
/*!40000 ALTER TABLE `parade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parade_floats`
--

DROP TABLE IF EXISTS `parade_floats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parade_floats` (
  `parade` int(11) NOT NULL,
  `floats` int(11) NOT NULL,
  KEY `FK_nmgwe8ou0qop1ocyigwv0a2xq` (`floats`),
  KEY `FK_cct51fcgo0xvooajhnwu0txl4` (`parade`),
  CONSTRAINT `FK_cct51fcgo0xvooajhnwu0txl4` FOREIGN KEY (`parade`) REFERENCES `parade` (`id`),
  CONSTRAINT `FK_nmgwe8ou0qop1ocyigwv0a2xq` FOREIGN KEY (`floats`) REFERENCES `float` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parade_floats`
--

LOCK TABLES `parade_floats` WRITE;
/*!40000 ALTER TABLE `parade_floats` DISABLE KEYS */;
/*!40000 ALTER TABLE `parade_floats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parade_segments`
--

DROP TABLE IF EXISTS `parade_segments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parade_segments` (
  `parade` int(11) NOT NULL,
  `segments` int(11) NOT NULL,
  KEY `FK_eg9uttr6lao2q7opct8ul68lb` (`segments`),
  KEY `FK_auqm7ipebqt3u6e4j6sw668wo` (`parade`),
  CONSTRAINT `FK_auqm7ipebqt3u6e4j6sw668wo` FOREIGN KEY (`parade`) REFERENCES `parade` (`id`),
  CONSTRAINT `FK_eg9uttr6lao2q7opct8ul68lb` FOREIGN KEY (`segments`) REFERENCES `segment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parade_segments`
--

LOCK TABLES `parade_segments` WRITE;
/*!40000 ALTER TABLE `parade_segments` DISABLE KEYS */;
/*!40000 ALTER TABLE `parade_segments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period_record`
--

DROP TABLE IF EXISTS `period_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `end_year` int(11) DEFAULT NULL,
  `start_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period_record`
--

LOCK TABLES `period_record` WRITE;
/*!40000 ALTER TABLE `period_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `period_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period_record_photos`
--

DROP TABLE IF EXISTS `period_record_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period_record_photos` (
  `period_record` int(11) NOT NULL,
  `photos` varchar(255) DEFAULT NULL,
  KEY `FK_64mj91qg7ncf1qybk31wfbqtp` (`period_record`),
  CONSTRAINT `FK_64mj91qg7ncf1qybk31wfbqtp` FOREIGN KEY (`period_record`) REFERENCES `period_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period_record_photos`
--

LOCK TABLES `period_record_photos` WRITE;
/*!40000 ALTER TABLE `period_record_photos` DISABLE KEYS */;
/*!40000 ALTER TABLE `period_record_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `position_eng` varchar(255) DEFAULT NULL,
  `position_esp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (19,0,'SECRETARY','SECRETARIO'),(20,0,'PRESIDENT','PRESIDENTE'),(21,0,'VICE PRESIDENT','VICEPRESIDENTE'),(22,0,'TREASURE','TESORERO'),(23,0,'HISTORIAN','HISTORIADOR'),(24,0,'FUNDRAISER','PROMOTOR'),(25,0,'OFFICER','VOCAL');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procession`
--

DROP TABLE IF EXISTS `procession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procession` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `departure_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `brotherhood` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sc8hr69nciikog00mms5616f8` (`ticker`),
  KEY `UK_om1hwmkxerywxb2gbm9o31hwf` (`final_mode`,`departure_date`),
  KEY `FK_k1aiqpf52p76km7ua07nlt1go` (`brotherhood`),
  CONSTRAINT `FK_k1aiqpf52p76km7ua07nlt1go` FOREIGN KEY (`brotherhood`) REFERENCES `brotherhood` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procession`
--

LOCK TABLES `procession` WRITE;
/*!40000 ALTER TABLE `procession` DISABLE KEYS */;
/*!40000 ALTER TABLE `procession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procession_floats`
--

DROP TABLE IF EXISTS `procession_floats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procession_floats` (
  `procession` int(11) NOT NULL,
  `floats` int(11) NOT NULL,
  KEY `FK_h2i386rvrj0r7gc5bicglm0ab` (`floats`),
  KEY `FK_3kw2qfesi92aqi13ubidr6ffv` (`procession`),
  CONSTRAINT `FK_3kw2qfesi92aqi13ubidr6ffv` FOREIGN KEY (`procession`) REFERENCES `procession` (`id`),
  CONSTRAINT `FK_h2i386rvrj0r7gc5bicglm0ab` FOREIGN KEY (`floats`) REFERENCES `float` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procession_floats`
--

LOCK TABLES `procession_floats` WRITE;
/*!40000 ALTER TABLE `procession_floats` DISABLE KEYS */;
/*!40000 ALTER TABLE `procession_floats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proclaim`
--

DROP TABLE IF EXISTS `proclaim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proclaim` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `chapter` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1k8gj5092fv7q0e4ikqlc30g` (`chapter`),
  CONSTRAINT `FK_1k8gj5092fv7q0e4ikqlc30g` FOREIGN KEY (`chapter`) REFERENCES `chapter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proclaim`
--

LOCK TABLES `proclaim` WRITE;
/*!40000 ALTER TABLE `proclaim` DISABLE KEYS */;
/*!40000 ALTER TABLE `proclaim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `column_position` int(11) NOT NULL,
  `reject_reason` varchar(255) DEFAULT NULL,
  `row_position` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `parade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_h6ruiv83vx0ks7uomdagcp6ur` (`status`,`row_position`,`column_position`),
  KEY `FK_69jhubt9asf03c38l84e802le` (`parade`),
  CONSTRAINT `FK_69jhubt9asf03c38l84e802le` FOREIGN KEY (`parade`) REFERENCES `parade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `segment`
--

DROP TABLE IF EXISTS `segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `segment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `arrive_time` datetime DEFAULT NULL,
  `dest_latitude` double DEFAULT NULL,
  `dest_longitude` double DEFAULT NULL,
  `orig_latitude` double DEFAULT NULL,
  `orig_longitude` double DEFAULT NULL,
  `segment_order` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `segment`
--

LOCK TABLES `segment` WRITE;
/*!40000 ALTER TABLE `segment` DISABLE KEYS */;
/*!40000 ALTER TABLE `segment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_profile`
--

DROP TABLE IF EXISTS `social_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `social_network` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_profile`
--

LOCK TABLES `social_profile` WRITE;
/*!40000 ALTER TABLE `social_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ban` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `polarity_score` double NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sponsorUK_r2b0o8w4fq9jh0iwma405gx6t` (`flag_spam`),
  KEY `FK_du2w5ldt8rvlvxvtr7vyxk7g3` (`user_account`),
  CONSTRAINT `FK_du2w5ldt8rvlvxvtr7vyxk7g3` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor_sponsorships`
--

DROP TABLE IF EXISTS `sponsor_sponsorships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor_sponsorships` (
  `sponsor` int(11) NOT NULL,
  `sponsorships` int(11) NOT NULL,
  UNIQUE KEY `UK_mkn1illtvx1wgme9mwxxh03ki` (`sponsorships`),
  KEY `FK_4h7hvvjylf3tk4ibb6c09s8pj` (`sponsor`),
  CONSTRAINT `FK_4h7hvvjylf3tk4ibb6c09s8pj` FOREIGN KEY (`sponsor`) REFERENCES `sponsor` (`id`),
  CONSTRAINT `FK_mkn1illtvx1wgme9mwxxh03ki` FOREIGN KEY (`sponsorships`) REFERENCES `sponsorship` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor_sponsorships`
--

LOCK TABLES `sponsor_sponsorships` WRITE;
/*!40000 ALTER TABLE `sponsor_sponsorships` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsor_sponsorships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship`
--

DROP TABLE IF EXISTS `sponsorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `targeturl` varchar(255) DEFAULT NULL,
  `credit_card` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1c71pwhg9slb986j8kl7uul1` (`credit_card`),
  CONSTRAINT `FK_b1c71pwhg9slb986j8kl7uul1` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship`
--

LOCK TABLES `sponsorship` WRITE;
/*!40000 ALTER TABLE `sponsorship` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsorship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship_parades`
--

DROP TABLE IF EXISTS `sponsorship_parades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship_parades` (
  `sponsorship` int(11) NOT NULL,
  `parades` int(11) NOT NULL,
  KEY `FK_fx2myyo7bnnxn49wtrrc69kp4` (`parades`),
  KEY `FK_hd11lfhqa75uy92vji62opmck` (`sponsorship`),
  CONSTRAINT `FK_hd11lfhqa75uy92vji62opmck` FOREIGN KEY (`sponsorship`) REFERENCES `sponsorship` (`id`),
  CONSTRAINT `FK_fx2myyo7bnnxn49wtrrc69kp4` FOREIGN KEY (`parades`) REFERENCES `parade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship_parades`
--

LOCK TABLES `sponsorship_parades` WRITE;
/*!40000 ALTER TABLE `sponsorship_parades` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsorship_parades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (16,0,'21232f297a57a5a743894a0e4a801fc3','admin');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (16,'ADMIN');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-28 21:07:35
