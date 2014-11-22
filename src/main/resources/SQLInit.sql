CREATE TABLE IF NOT EXISTS Profession
(ID INT NOT NULL INDEX(ID), Name varchar(40),
Description varchar(250), Icon varchar(250))
 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS Ability
(ID INT NOT NULL AUTO_INCREMENT, INDEX(ID), Name varchar(40), 
Description varchar(250), Property INT, Icon varchar(250))
 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS Ability_Property
(ID INT NOT NULL AUTO_INCREMENT, INDEX(ID), Name varchar(40), 
Description varchar(250), AbilityType INT, AbilityValue INT)
 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS Profession_Ability
(ID INT NOT NULL AUTO_INCREMENT, INDEX(ID), ClassID INT, 
AbilityID INT, Modifier INT, TreePosition INT)
 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS Characters
(ID INT NOT NULL AUTO_INCREMENT, INDEX(ID), PlayerName varchar(40), 
PlayerUID varchar(250), ClassAbilities INT, FactionID INT)
 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS Factions
(ID INT NOT NULL AUTO_INCREMENT, INDEX(ID), Leader varchar(40), Grade INT, RegionsID INT)
 AUTO_INCREMENT=2;

-- Raw table imports
INSERT INTO `mcserver`.`Profession`(`Name`,`Description`, `Icon`)VALUES("Paladin","You forgot what it meant to live", "testPaladin.png");
INSERT INTO `mcserver`.`Ability`(`Name`,`Description`,`Icon`,`Property`)VALUES("Chagrin","pester them","t.png",0);
INSERT INTO `mcserver`.`Ability`(`Name`,`Description`,`Icon`,`Property`)VALUES("DeathBlade","kaboom.","textures/melee1.png",0);
