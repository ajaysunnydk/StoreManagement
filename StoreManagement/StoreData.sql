create database store;
use store;
create table regdetails (uname varchar(200) NOT NULL, paasword varchar(200) NOT NULL , role varchar(200) default "user");
create table items (itemId int(20) primary key NOT NULL, itemName varchar(200) , itemQty int(20) );

CREATE TABLE IF NOT EXISTS `store`.`cart` (
  `idcart` INT NOT NULL,
  `itemId` INT NOT NULL,
  `itemName` VARCHAR(200) NOT NULL,
  `itemQty` INT NOT NULL,
  PRIMARY KEY (`idcart`),
  INDEX `itemId_idx` (`itemId` ASC) VISIBLE,
  CONSTRAINT `itemId`
    FOREIGN KEY (`itemId`)
    REFERENCES `store`.`items` (`itemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB ;

insert into regdetails values ("host","host@123","admin");
select * from regdetails;
