DELETE FROM `platos`;
DELETE FROM `restaurant`;
DELETE FROM `platos`;

INSERT INTO `restaurant`
(`id`,`nombre`,`direccion`,`telefono`)
VALUES
     (1,'El mosquito','Av. José María Moreno 907','011 4925-3340'),
     (2,'Los Chanchitos','Av. Angel Gallardo 601','011 4854-4030'),
     (3,'Dalal','José Bonifacio 499','011 15-3880-7529'),
     (4,'Pucará','Senillosa 493','011 4902-4975');

INSERT INTO `platos`
(`id`,`nombre`,`precio`,`calorias`,`restaurant_id`)
VALUES
    (1,'Vacio','2100.00','248',1),
    (2,'Bife de chorizo','2500.00','142',2),
    (3,'Shawarma','1600.00','472',3),
    (4,'Filet a la romana','1300.00','193',4),
    (5,'Papas fritas','900.00','312',4),
    (6,'Ravioles','1350.00','250',1),
    (7,'Flan','950.00','146',2),
    (8,'Ensalada tabule','1350.00','185',3),
    (9,'Baklava','1600.00','334',3),
    (10,'Risotto','1800.00','356',1),
    (11,'Parrillada para dos','3100.00','1144',2),
    (12,'Pulpo a la gallega','2900.00','575',4);



