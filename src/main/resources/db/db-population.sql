INSERT INTO DIFFICULTIES (difficulty)
VALUES
    ('Hard'),
    ('Normal'),
    ('Easy');

INSERT INTO BOX_TYPES (box_type)
VALUES
    ('Province'),
    ('Company'),
    ('Railway'),
    ('Card'),
    ('Tax'),
    ('Prize'),
    ('Jail'),
    ('Rest'),
    ('Free parking'),
    ('Go to Jail'),
    ('Exit');

INSERT INTO BOXES (id_box_type)
VALUES
    (1),
    (1),
    (1),
    (5),
    (1),
    (1),
    (6),
    (2),
    (1),
    (4),
    (1),
    (3),
    (1),
    (7),
    (4),
    (2),
    (1),
    (3),
    (1),
    (1),
    (8),
    (3),
    (1),
    (1),
    (4),
    (1),
    (3),
    (9),
    (1),
    (1),
    (2),
    (1),
    (1),
    (1),
    (10),
    (4),
    (1),
    (4),
    (1),
    (1),
    (5);

INSERT INTO CARD_TYPES (card_type)
VALUES
    ('Pay'),
    ('Move'),
    ('Earn'),
    ('Exit from jail');

INSERT INTO CARDS (id_card_type,is_destiny,card_description,card_value,id_box)
VALUES
    (3,'true','5% interest on mortgage bonds. Earn 500',500,null),
    (3,'true','Tax refund. Earn 400',400,null),
    (1,'true','Pay your insurance policy with 1000',1000,null),
    (3,'true','Error in the Banks calculations. Earn 4,000',4000,null),
    (1,'true','Pharmacy Expenses. Pay 1,000',1000,null),
    (3,'true','Won a second beauty prize. Earn 200',200,null),
    (3,'true','Won an agricultural competition. Earn 2,000',2000,null),
    (3,'true','You inherit 2,000',2000,null),
    (3,'true','For sale of shares. Earn 1,000',1000,null),
    (3,'true','Is your birthday. Collect 200!',200,null),
    (4,'true','With this card, you leave the Police Station. Keep it until you use it or sell it',null,null),
    (2,'true','Follow until the exit',null,null),
    (2,'true','Go back to Formosa South Zone',null,null),
    (1,'true','Pay 200 fine or raise a LUCK card',null,null),
    (2,'true','Go straight to prison',null,null),
    (3,'false','Won the big one. Earn 10,000',10000,null),
    (1,'false','Speeding ticket. Pay 300',300,null),
    (1,'false','Pay 3,000 for school expenses',3000,null),
    (3,'false','Won in the races. Earn 3,000',3000,null),
    (3,'false','Collect 1,000 for bank interest',1000,null),
    (1,'false','Traffic police ticket. Pay 400',400,null), --
    (1,'false','Your properties have to be repaired. Pay the Bank 500 for each farm and 2,500 for each ranch',null,null),
    (1,'false','For purchasing seed, pay the Bank 800 for each farm. 4000 for each ranch',null,null),
    (2,'false','Take a walk to the Winery. If you go through the exit, earn 5,000',5000,null),
    (2,'false','Continue to Santa Fé, North Zone. If you go through the exit, earn 5,000',5000,null),
    (2,'false','Continue to Salta, North Zone. If you go through the exit, earn 5,000',5000,null),
    (2,'false','Go straight to prison',null,null),
    (2,'false','Follow until the exit',null,null),
    (2,'false','Go back three steps',null,null),
    (2,'false','Continue to Buenos Aires, North Zone',null,null),
    (4,'false','Habeas Corpus granted. With this card, you leave the Police Station free of charge. Keep it or sell it',null,null);

INSERT INTO TAXES (id_box,tax_description,amount)
VALUES
    (4,'Rental tax',5000),
    (41,'Sales tax',2000);

INSERT INTO DEEDS (id_box,deed_name,purchase_value,mortgage_value)
VALUES
    (1,'Province',1000,500),
    (2,'Province',1000,500),
    (3,'Province',1200,600),
    (5,'Province',2000,1000),
    (6,'Province',2200,1100),
    (9,'Province',2600,1300),
    (11,'Province',2600,1300),
    (13,'Province',3000,1500),
    (17,'Province',3400,1700),
    (19,'Province',3400,1700),
    (20,'Province',3800,1900),
    (23,'Province',4200,2100),
    (24,'Province',4200,2100),
    (26,'Province',4600,2300),
    (29,'Province',5000,2500),
    (30,'Province',5400,2700),
    (32,'Province',6000,3000),
    (33,'Province',6000,3000),
    (34,'Province',6400,3200),
    (37,'Province',7000,3500),
    (39,'Province',7000,3500),
    (40,'Province',7400,3700),
    (8,'Company',3800,1900),
    (16,'Company',3800,1900),
    (31,'Company',5000,1900),
    (12,'Railway',3600,1900),
    (18,'Railway',3600,1800),
    (22,'Railway',3600,1800),
    (27,'Railway',3600,1700);

INSERT INTO ZONES (zone)
VALUES
    ('South'),
    ('Center'),
    ('North');

INSERT INTO PROVINCES (id_deed,id_zone,province_name,construction_value,rent,rent_1_farm,rent_2_farms,rent_3_farms,rent_4_farms,rent_ranch)
VALUES
    (1,1,'FORMOSA',1000,40,200,600,1700,3000,4750),
    (2,2,'FORMOSA',1000,40,200,600,1700,3000,4750),
    (3,3,'FORMOSA',1000,80,400,1200,3400,6000,9500),
    (4,1,'RIO NEGRO',1500,110,570,1700,5150,7600,9500),
    (5,3,'RIO NEGRO',1500,150,750,2000,5700,8500,11500),
    (6,1,'SALTA',1500,200,1000,2800,5800,12000,14200),
    (7,2,'SALTA',1500,200,1000,2800,5800,12000,14200),
    (8,3,'SALTA',1500,230,1150,3400,9500,13000,17000),
    (9,1,'MENDOZA',2000,250,1350,3800,10500,14200,18000),
    (10,2,'MENDOZA',2000,250,1350,3800,10500,14200,18000),
    (11,3,'MENDOZA',2000,300,1500,4200,11500,15000,19000),
    (12,1,'SANTA FE',2500,350,1700,4750,1300,15000,20000),
    (13,2,'SANTA FE',2500,350,1700,4750,1300,15000,20000),
    (14,3,'SANTA FE',2500,400,2000,5750,14000,17000,21000),
    (15,1,'TUCUMAN',3000,400,2200,6000,15000,18000,21000),
    (16,3,'TUCUMAN',3000,450,2400,6800,15000,19500,23000),
    (17,1,'CORDOBA',3000,500,2500,6500,17000,21000,24000),
    (18,2,'CORDOBA',3000,450,2400,6800,16000,19500,23000),
    (19,3,'CORDOBA',3000,550,2850,8500,19000,23000,27000),
    (20,1,'BUENOS AIRES',4000,650,3300,9500,22000,25000,30000),
    (21,2,'BUENOS AIRES',4000,650,3300,9500,22000,25000,30000),
    (22,3,'BUENOS AIRES',4000,1000,4000,12000,25000,31000,35000);

INSERT INTO COMPANIES (id_deed,company_name,rent_1_company,rent_2_companies,rent_3_companies)
VALUES
    (23,'Petrol factory',100,200,300),
    (24,'Winery',100,200,300),
    (25,'Sugar factory',100,200,300);

INSERT INTO RAILWAYS (id_deed,railway_name,rent_1_railway,rent_2_railways,rent_3_railways,rent_4_railways)
VALUES
    (26,'General. Belgrano',500,1000,2000,4000),
    (27,'General S. Martin',500,1000,2000,4000),
    (28,'General B. Mitre',500,1000,2000,4000),
    (29,'General Urquiza',500,1000,2000,4000);

INSERT INTO COLORS (color)
VALUES
    ('Red'),
    ('Green'),
    ('Blue'),
    ('Yellow'),
    ('Orange'),
    ('Violet'),
    ('Black'),
    ('White');
INSERT INTO GAMES_STYLES (game_style,railway_purchase,company_purchase,purchase_possibility)
VALUES
    ('Conservative','false','false',20),
    ('Well_balanced','true','false',33),
    ('Aggressive','true','true',0);

INSERT INTO STYLES_BY_PROVINCES (id_game_style,id_province)
VALUES
    (1,1),
    (1,2),
    (1,3),
    (1,4),
    (1,5),
    (1,6),
    (1,7),
    (1,8),
    (2,9),
    (2,10),
    (2,11),
    (2,12),
    (2,13),
    (2,14),
    (2,15),
    (2,16),
    (3,15),
    (3,16),
    (3,17),
    (3,18),
    (3,19),
    (3,20),
    (3,21),
    (3,22);