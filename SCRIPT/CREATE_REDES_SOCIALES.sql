CREATE TABLE `apisociales`.`tbl_info_redes_sociales` ( 
`id` INT NOT NULL AUTO_INCREMENT, 
`red_social` VARCHAR(100) NULL , 
`fecha_ingreso` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP , 
`informacion` TEXT NULL , PRIMARY KEY (`id`)) 