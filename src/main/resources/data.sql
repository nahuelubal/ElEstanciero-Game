CREATE TABLE USERS
(
    id_user  INT AUTO_INCREMENT,
    username VARCHAR(64)  NOT NULL,
    pass     VARCHAR(256) NOT NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (id_user)
);

CREATE TABLE DIFFICULTIES
(
    id_difficulty INT AUTO_INCREMENT,
    difficulty    VARCHAR(64) NOT NULL,
    CONSTRAINT PK_DIFFICULTIES PRIMARY KEY (id_difficulty)
);

CREATE TABLE GAMES
(
    id_game       INT AUTO_INCREMENT,
    id_user       INT NOT NULL,
    id_difficulty INT NOT NULL,
    round_number  INT,
    CONSTRAINT PK_GAMES PRIMARY KEY (id_game),
    CONSTRAINT FK_GAMES__USERS FOREIGN KEY (id_user)
        REFERENCES USERS (id_user),
    CONSTRAINT FK_GAMES__DIFFICULTIES FOREIGN KEY (id_difficulty)
        REFERENCES DIFFICULTIES (id_difficulty)
);

CREATE TABLE DECKS_OF_CARDS
(
    id_deck_of_card INT AUTO_INCREMENT,
    id_game         INT NOT NULL,
    CONSTRAINT PK_DECKS_OF_CARDS PRIMARY KEY (id_deck_of_card),
    CONSTRAINT FK_DECKS_OF_CARDS__GAMES FOREIGN KEY (id_game)
        REFERENCES GAMES (id_game)
);

CREATE TABLE BANKS
(
    id_bank         INT AUTO_INCREMENT,
    id_game         INT NOT NULL,
    available_money INT NOT NULL,
    CONSTRAINT PK_BANKS PRIMARY KEY (id_bank),
    CONSTRAINT FK_BANKS_GAMES FOREIGN KEY (id_game)
        REFERENCES GAMES (id_game)
);

CREATE TABLE BOX_TYPES
(
    id_box_type INT AUTO_INCREMENT,
    box_type    VARCHAR(50) NOT NULL,
    CONSTRAINT PK_BOX_TYPES PRIMARY KEY (id_box_type)
);

CREATE TABLE BOXES
(
    id_box      INT AUTO_INCREMENT,
    id_box_type INT          NOT NULL,
    description VARCHAR(200) NOT NULL,
    CONSTRAINT PK_BOXES PRIMARY KEY (id_box),
    CONSTRAINT FK_BOXES__BOX_TYPES FOREIGN KEY (id_box_type)
        REFERENCES BOX_TYPES (id_box_type)
);

CREATE TABLE CARD_TYPES
(
    id_card_type INT AUTO_INCREMENT,
    card_type    VARCHAR(50),
    CONSTRAINT PK_CARD_TYPES PRIMARY KEY (id_card_type)
);

CREATE TABLE CARDS
(
    id_card          INT AUTO_INCREMENT,
    id_card_type     INT          NOT NULL,
    is_destiny       BIT          NOT NULL,
    card_description VARCHAR(200) NOT NULL,
    card_value       INT NULL,
    id_box           INT NULL,
    CONSTRAINT PK_CARDS PRIMARY KEY (id_card),
    CONSTRAINT FK_CARDS__CARD_TYPES FOREIGN KEY (id_card_type)
        REFERENCES CARD_TYPES (id_card_type),
    CONSTRAINT FK_CARDS__BOXES FOREIGN KEY (id_box)
        REFERENCES BOXES (id_box)
);

CREATE TABLE CARDS_BY_DECKS
(
    id_card_by_deck INT AUTO_INCREMENT,
    id_deck_of_card INT NOT NULL,
    id_card         INT NOT NULL,
    CONSTRAINT PK_CARDS_BY_DECKS PRIMARY KEY (id_card_by_deck),
    CONSTRAINT FK_CARDS_BY_DECKS__DECKS_OF_CARDS FOREIGN KEY (id_deck_of_card)
        REFERENCES DECKS_OF_CARDS (id_deck_of_card),
    CONSTRAINT FK_CARDS_BY_DECKS__CARDS FOREIGN KEY (id_card)
        REFERENCES CARDS (id_card)
);

CREATE TABLE TAXES
(
    id_tax          INT AUTO_INCREMENT,
    id_box          INT         NOT NULL,
    tax_description VARCHAR(50) NOT NULL,
    amount          INT         NOT NULL,
    CONSTRAINT PK_TAXES PRIMARY KEY (id_tax),
    CONSTRAINT FK_TAXES__BOXES FOREIGN KEY (id_box)
        REFERENCES BOXES (id_box)
);

CREATE TABLE DEEDS
(
    id_deed        INT AUTO_INCREMENT,
    id_box         INT         NOT NULL,
    deed_name      VARCHAR(50) NOT NULL,
    purchase_value INT         NOT NULL,
    mortgage_value INT         NOT NULL,
    deed_type      VARCHAR(50) NOT NULL,
    CONSTRAINT PK_DEEDS PRIMARY KEY (id_deed),
    CONSTRAINT FK_DEEDS__BOXES FOREIGN KEY (id_box)
        REFERENCES BOXES (id_box)
);

CREATE TABLE ZONES
(
    id_zone INT AUTO_INCREMENT,
    zone    VARCHAR(50) NOT NULL,
    CONSTRAINT PK_ZONES PRIMARY KEY (id_zone)
);

CREATE TABLE PROVINCES
(
    id_province        INT AUTO_INCREMENT,
    id_deed            INT         NOT NULL,
    id_zone            INT         NOT NULL,
    province_name      VARCHAR(50) NOT NULL,
    construction_value INT         NOT NULL,
    rent               INT         NOT NULL,
    rent_1_farm        INT         NOT NULL,
    rent_2_farms       INT         NOT NULL,
    rent_3_farms       INT         NOT NULL,
    rent_4_farms       INT         NOT NULL,
    rent_ranch         INT         NOT NULL,
    CONSTRAINT PK_PROVINCES PRIMARY KEY (id_province),
    CONSTRAINT FK_PROVINCES__DEEDS FOREIGN KEY (id_deed)
        REFERENCES DEEDS (id_deed),
    CONSTRAINT FK_PROVINCES__ZONES FOREIGN KEY (id_zone)
        REFERENCES ZONES (id_zone)
);

CREATE TABLE COMPANIES
(
    id_company       INT AUTO_INCREMENT,
    id_deed          INT         NOT NULL,
    company_name     VARCHAR(50) NOT NULL,
    rent_1_company   INT         NOT NULL,
    rent_2_companies INT         NOT NULL,
    rent_3_companies INT         NOT NULL,
    CONSTRAINT PK_COMPANIES PRIMARY KEY (id_company),
    CONSTRAINT FK_COMPANIES__DEEDS FOREIGN KEY (id_deed)
        REFERENCES DEEDS (id_deed)
);

CREATE TABLE RAILWAYS
(
    id_railway      INT AUTO_INCREMENT,
    id_deed         INT         NOT NULL,
    railway_name    VARCHAR(50) NOT NULL,
    rent_1_railway  INT         NOT NULL,
    rent_2_railways INT         NOT NULL,
    rent_3_railways INT         NOT NULL,
    rent_4_railways INT         NOT NULL,
    CONSTRAINT PK_RAILWAYS PRIMARY KEY (id_railway),
    CONSTRAINT FK_RAILWAYS__DEEDS FOREIGN KEY (id_deed)
        REFERENCES DEEDS (id_deed)
);

CREATE TABLE GAMES_STYLES
(
    id_game_style        INT AUTO_INCREMENT,
    game_style           VARCHAR(64) NOT NULL,
    railway_purchase     BIT         NOT NULL,
    company_purchase     BIT         NOT NULL,
    purchase_possibility INT         NOT NULL,
    CONSTRAINT PK_GAMES_STYLES PRIMARY KEY (id_game_style)
);

CREATE TABLE STYLES_BY_PROVINCES
(
    id_style_by_province INT AUTO_INCREMENT,
    id_game_style        INT NOT NULL,
    id_province          INT NOT NULL,
    CONSTRAINT PK_STYLES_BY_PROVINCES PRIMARY KEY (id_style_by_province),
    CONSTRAINT FK_STYLES_BY_PROVINCES__STYLES FOREIGN KEY (id_game_style)
        REFERENCES GAMES_STYLES (id_game_style),
    CONSTRAINT FK_STYLES_BY_PROVINCES__PROVINCES FOREIGN KEY (id_province)
        REFERENCES PROVINCES (id_province)
);

CREATE TABLE COLORS
(
    id_color INT AUTO_INCREMENT,
    color    VARCHAR(20) NOT NULL,
    CONSTRAINT PK_COLORS PRIMARY KEY (id_color)
);

CREATE TABLE PLAYERS
(
    id_player       INT AUTO_INCREMENT,
    id_user         INT,
    id_game         INT NOT NULL,
    id_game_style   INT,
    id_color        INT NOT NULL,
    id_box          INT NOT NULL,
    player_order    INT NOT NULL,
    available_money INT NOT NULL,
    is_active       BIT NOT NULL,
    CONSTRAINT PK_PLAYERS PRIMARY KEY (id_player),
    CONSTRAINT FK_PLAYERS__USERS FOREIGN KEY (id_user)
        REFERENCES USERS (id_user),
    CONSTRAINT FK_PLAYERS__GAMES FOREIGN KEY (id_game)
        REFERENCES GAMES (id_game),
    CONSTRAINT FK_PLAYERS__GAMES_STYLES FOREIGN KEY (id_game_style)
        REFERENCES GAMES_STYLES (id_game_style),
    CONSTRAINT FK_PLAYERS__COLORS FOREIGN KEY (id_color)
        REFERENCES COLORS (id_color),
    CONSTRAINT FK_PLAYERS__BOXES FOREIGN KEY (id_box)
        REFERENCES BOXES (id_box)
);

CREATE TABLE TURNS
(
    id_turn   INT AUTO_INCREMENT,
    id_player INT NOT NULL,
    CONSTRAINT PK_TURNS PRIMARY KEY (id_turn),
    CONSTRAINT FK_TURNS__PLAYERS FOREIGN KEY (id_player)
        REFERENCES PLAYERS (id_player)
);

CREATE TABLE DEEDS_BY_PLAYERS
(
    id_deed_by_player INT AUTO_INCREMENT,
    id_deed           INT NOT NULL,
    id_player         INT NOT NULL,
    is_purchased      BIT NOT NULL,
    is_mortgaged      BIT NOT NULL,
    farm_quantity     INT NOT NULL,
    has_ranch         BIT NOT NULL,
    CONSTRAINT PK_DEEDS_BY_PLAYERS PRIMARY KEY (id_deed_by_player),
    CONSTRAINT FK_DEEDS_BY_PLAYERS__DEEDS FOREIGN KEY (id_deed)
        REFERENCES DEEDS (id_deed),
    CONSTRAINT FK_DEEDS_BY_PLAYERS__PLAYERS FOREIGN KEY (id_player)
        REFERENCES PLAYERS (id_player)
);

INSERT INTO GAMES_STYLES (game_style, railway_purchase, company_purchase, purchase_possibility)
VALUES ('CONSERVATIVE', 'false', 'false', 20),
       ('WELL_BALANCED', 'true', 'false', 33),
       ('AGGRESSIVE', 'true', 'true', 0);

INSERT INTO COLORS (color)
VALUES ('Red'),
       ('Green'),
       ('Blue'),
       ('Yellow'),
       ('Orange'),
       ('Violet'),
       ('Black'),
       ('White');

INSERT INTO DIFFICULTIES (difficulty)
VALUES ('HARD'),
       ('MEDIUM'),
       ('EASY');

INSERT INTO BOX_TYPES (box_type)
VALUES ('PROVINCE_PROPERTY'),
       ('COMPANY_PROPERTY'),
       ('RAILWAY_PROPERTY'),
       ('CARD'),
       ('TAX_BOX'),
       ('FARMER_PRIZE'),
       ('JAIL_BOX'),
       ('REST_BOX'),
       ('FREE_PARKING'),
       ('GO_TO_JAIL'),
       ('START_BOX');

INSERT INTO BOXES (id_box_type, description)
VALUES (11, 'Start'),
       (1, 'South Formosa'),
       (1, 'Center Formosa'),
       (1, 'North Formosa'),
       (5, 'Rental Tax'),
       (1, 'South Rio Negro'),
       (1, 'North Rio Negro'),
       (6, 'Farmer Prize'),
       (2, 'Oil Company'),
       (1, 'South Salta'),
       (4, 'Destiny Card'),
       (1, 'Center Salta'),
       (3, 'Belgrano Railway'),
       (1, 'North Salta'),
       (7, 'Jail'),--15
       (4, 'Lucky Card'),
       (2, 'Winery Company'),
       (1, 'South Mendoza'),
       (3, 'San Martin Railway'),
       (1, 'Center Mendoza'),
       (1, 'North Mendoza'),
       (8, 'Rest'),
       (3, 'Mitre Railway'),
       (1, 'South Santa Fe'),
       (1, 'Center Santa Fe'),
       (4, 'Destiny Card'),
       (1, 'North Santa Fe'),
       (3, 'Urquiza Railway'),
       (9, 'Free Parking'),
       (1, 'South Tucumán'),
       (1, 'North Tucumán'),
       (2, 'Ingenuity Company'),
       (1, 'South Córdoba'),
       (1, 'Center Córdoba'),
       (1, 'North Córdoba'),
       (10, 'Go To Jail'),--36
       (4, 'Lucky Card'),
       (1, 'South Buenos Aires'),
       (4, 'Destiny Card'),
       (1, 'Center Buenos Aires'),
       (1, 'North Buenos Aires'),
       (5, 'Sales Tax');

INSERT INTO CARD_TYPES (card_type)
VALUES ('PAY'),
       ('PAY_UPGRADE'),
       ('MOVE_BY_BOX'),
       ('MOVE_BY_QUANTITY'),
       ('CHARGE'),
       ('EXIT_FROM_JAIL');

INSERT INTO CARDS (id_card_type, is_destiny, card_description, card_value, id_box)
VALUES (5, 'true', '5% interest on mortgage bonds. Earn 500', 500, null),
       (6, 'true', 'With this card, you leave the Police Station. Keep it until you use it or sell it', 0, null),
       (3, 'true', 'Go straight to jail', 0, 15),
       (5, 'true', 'Tax refund. Earn 400', 400, null),
       (1, 'true', 'Pay your insurance policy with 1.000', 1000, null),
       (5, 'true', 'Won an agricultural competition. Earn 2.000', 2000, null),
       (5, 'true', 'Error in the Banks calculations. Earn 4.000', 4000, null),
       (1, 'true', 'Pharmacy Expenses. Pay 1.000', 1000, null),
       (5, 'true', 'Won a second beauty prize. Earn 200', 200, null),
       (5, 'true', 'Is your birthday. Collect 200!', 200, null),
       (5, 'true', 'Won an agricultural competition. Earn 2.000', 2000, null),
       (5, 'true', 'You inherit 2.000', 2000, null),
       (5, 'true', 'For sale of shares. Earn 1.000', 1000, null),
       (3, 'true', 'Follow until the exit', 0, 1),
       (3, 'true', 'Go back to Formosa South Zone', null, 2),
       (1, 'true', 'Pay 200 penalty ticket or raise a LUCK card', 0, null),
       (3, 'false', 'Go straight to jail', 0, 15),
       (5, 'false', 'Won the big one. Earn 10.000', 10000, null),
       (3, 'false', 'Take a walk to the Winery. If you go through the exit, earn 5.000', 5000, 17),
       (3, 'false', 'Continue to Buenos Aires, North Zone', 0, 41),
       (3, 'false', 'Continue to Salta, North Zone. If you go through the exit, earn 5.000', 5000, 14),
       (1, 'false', 'Speeding ticket. Pay 300', 300, null),
       (5, 'false', 'Won in the races. Earn 3.000', 3000, null),
       (5, 'false', 'Collect 1.000 for bank interest', 1000, null),
       (3, 'false', 'Follow until the exit', 0, 1),
       (1, 'false', 'Pay 3.000 for school expenses', 3000, null),
       (4, 'false', 'Go back three steps', 3, null),
       (1, 'false', 'Traffic police ticket. Pay 400', 400, null),
       (1, 'false', 'Your properties have to be repaired. Pay the Bank 500 for each farm and 2,500 for each ranch',
        0, null),
       (2, 'false', 'For purchasing seed, pay the Bank 800 for each farm. 4.000 for each ranch', 0, null),
       (3, 'false', 'Continue to Santa Fe, North Zone. If you go through the exit, earn 5.000', 5000, 27),
       (6, 'false',
        'Habeas Corpus granted. With this card, you leave the Police Station free of charge. Keep it or sell it', 0,
        null);

INSERT INTO TAXES (id_box, tax_description, amount)
VALUES (5, 'Rental tax', 5000),
       (42, 'Sales tax', 2000);

INSERT INTO DEEDS (id_box, deed_name, purchase_value, mortgage_value, deed_type)
VALUES (2, 'South Formosa', 1000, 500, 'PROVINCE'),
       (3, 'Center Formosa', 1000, 500, 'PROVINCE'),
       (4, 'North Formosa', 1200, 600, 'PROVINCE'),
       (6, 'South Rio Negro', 2000, 1000, 'PROVINCE'),
       (7, 'North Rio Negro', 2200, 1100, 'PROVINCE'),
       (10, 'South Salta', 2600, 1300, 'PROVINCE'),
       (12, 'Center Salta', 2600, 1300, 'PROVINCE'),
       (14, 'North Salta', 3000, 1500, 'PROVINCE'),
       (18, 'South Mendoza', 3400, 1700, 'PROVINCE'),
       (20, 'Center Mendoza', 3400, 1700, 'PROVINCE'),
       (21, 'North Mendoza', 3800, 1900, 'PROVINCE'),
       (24, 'South Santa Fe', 4200, 2100, 'PROVINCE'),
       (25, 'Center Santa Fe', 4200, 2100, 'PROVINCE'),
       (27, 'North Santa Fe', 4600, 2300, 'PROVINCE'),
       (30, 'South Tucumán', 5000, 2500, 'PROVINCE'),
       (31, 'North Tucumán', 5400, 2700, 'PROVINCE'),
       (33, 'South Córdoba', 6000, 3000, 'PROVINCE'),
       (34, 'Center Córdoba', 6000, 3000, 'PROVINCE'),
       (35, 'North Córdoba', 6400, 3200, 'PROVINCE'),
       (38, 'South Buenos Aires', 7000, 3500, 'PROVINCE'),
       (40, 'Center Buenos Aires', 7000, 3500, 'PROVINCE'),
       (41, 'North Buenos Aires', 7400, 3700, 'PROVINCE'),
       (9, 'Oil Company', 3800, 1900, 'COMPANY'),
       (17, 'Winery Company', 3800, 1900, 'COMPANY'),
       (32, 'Ingenuity Company', 5000, 1900, 'COMPANY'),
       (13, 'Belgrano Railway', 3600, 1900, 'RAILWAY'),
       (19, 'San Martin Railway', 3600, 1800, 'RAILWAY'),
       (23, 'Mitre Railway', 3600, 1800, 'RAILWAY'),
       (28, 'Urquiza Railway', 3600, 1700, 'RAILWAY');

INSERT INTO ZONES (zone)
VALUES ('South'),
       ('Center'),
       ('North');

INSERT INTO PROVINCES (id_deed, id_zone, province_name, construction_value, rent, rent_1_farm, rent_2_farms,
                       rent_3_farms, rent_4_farms, rent_ranch)
VALUES (1, 1, 'FORMOSA', 1000, 40, 200, 600, 1700, 3000, 4750),
       (2, 2, 'FORMOSA', 1000, 40, 200, 600, 1700, 3000, 4750),
       (3, 3, 'FORMOSA', 1000, 80, 400, 800, 3400, 6000, 9500),
       (4, 1, 'RIO NEGRO', 1000, 110, 570, 1700, 5150, 7600, 9500),
       (5, 3, 'RIO NEGRO', 1000, 150, 750, 2000, 5700, 8500, 11500),
       (6, 1, 'SALTA', 1500, 200, 1000, 2800, 8500, 12000, 14200),
       (7, 2, 'SALTA', 1500, 200, 1000, 2800, 8500, 12000, 14200),
       (8, 3, 'SALTA', 1500, 230, 1150, 3400, 9500, 13000, 17000),
       (9, 1, 'MENDOZA', 2000, 250, 1350, 3800, 10500, 14200, 18000),
       (10, 2, 'MENDOZA', 2000, 250, 1350, 3800, 10500, 14200, 18000),
       (11, 3, 'MENDOZA', 2000, 300, 1500, 4200, 11500, 15000, 19000),
       (12, 1, 'SANTA FE', 2500, 350, 1700, 4750, 13000, 16000, 20000),
       (13, 2, 'SANTA FE', 2500, 350, 1700, 4750, 13000, 16000, 20000),
       (14, 3, 'SANTA FE', 2500, 400, 2000, 5750, 14000, 17000, 21000),
       (15, 1, 'TUCUMAN', 3000, 400, 2200, 6000, 15000, 18000, 21000),
       (16, 3, 'TUCUMAN', 3000, 450, 2400, 6800, 16000, 19500, 23000),
       (17, 1, 'CORDOBA', 3000, 500, 2500, 6500, 17000, 21000, 24000),
       (18, 2, 'CORDOBA', 3000, 450, 2400, 6800, 16000, 19500, 23000),
       (19, 3, 'CORDOBA', 3000, 550, 2850, 8500, 19000, 23000, 27000),
       (20, 1, 'BUENOS AIRES', 4000, 650, 3300, 9500, 22000, 25000, 30000),
       (21, 2, 'BUENOS AIRES', 4000, 650, 3300, 9500, 22000, 25000, 30000),
       (22, 3, 'BUENOS AIRES', 4000, 1000, 4000, 12000, 26000, 31000, 36000);

INSERT INTO COMPANIES (id_deed, company_name, rent_1_company, rent_2_companies, rent_3_companies)
VALUES (23, 'Oil Company', 100, 200, 300),
       (24, 'Winery', 100, 200, 300),
       (25, 'Sugar Company', 100, 200, 300);

INSERT INTO RAILWAYS (id_deed, railway_name, rent_1_railway, rent_2_railways, rent_3_railways, rent_4_railways)
VALUES (26, 'General. Belgrano', 500, 1000, 2000, 4000),
       (27, 'General S. Martin', 500, 1000, 2000, 4000),
       (28, 'General B. Mitre', 500, 1000, 2000, 4000),
       (29, 'General Urquiza', 500, 1000, 2000, 4000);

INSERT INTO USERS (username, pass)
VALUES ('sa', 'sa');