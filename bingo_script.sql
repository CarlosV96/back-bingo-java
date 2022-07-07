-- -----------------------------------------------------
-- Schema bingo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS game_bingo DEFAULT CHARACTER SET utf8 ;
USE game_bingo ;

-- -----------------------------------------------------
-- Table lobby`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lobby (
  idlobby INT NOT NULL AUTO_INCREMENT,
  jugador_lobby VARCHAR(45) NULL,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (idlobby))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS game (
  idgame INT NOT NULL AUTO_INCREMENT,
  jugador_game VARCHAR(45) NULL,
  PRIMARY KEY (idgame))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table winner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS winner (
  idwinner INT NOT NULL AUTO_INCREMENT,
  winner VARCHAR(45) NULL,
  PRIMARY KEY (idwinner))
ENGINE = InnoDB;
