USE master
GO
DROP DATABASE TPI_EL_ESTANCIERO
GO
CREATE DATABASE TPI_EL_ESTANCIERO
GO
USE TPI_EL_ESTANCIERO
GO
CREATE TABLE USERS (
                       id_user INT IDENTITY (1,1),
                       username VARCHAR(64) NOT NULL,
                       pass VARCHAR(256) NOT NULL,
                       CONSTRAINT PK_USERS PRIMARY KEY (id_user)
);

CREATE TABLE DIFFICULTIES (
                              id_difficulty INT IDENTITY (1,1),
                              difficulty VARCHAR(64) NOT NULL,
                              CONSTRAINT PK_DIFFICULTIES PRIMARY KEY (id_difficulty)
);

CREATE TABLE GAMES (
                       id_game INT IDENTITY (1,1),
                       id_user INT NOT NULL,
                       id_difficulty INT NOT NULL,
                       round_number INT,
                       CONSTRAINT PK_GAMES PRIMARY KEY (id_game),
                       CONSTRAINT FK_GAMES__USERS FOREIGN KEY (id_user)
                           REFERENCES USERS (id_user),
                       CONSTRAINT FK_GAMES__DIFFICULTIES FOREIGN KEY (id_difficulty)
                           REFERENCES DIFFICULTIES (id_difficulty)
);

CREATE TABLE DECKS_OF_CARDS (
                                id_deck_of_card INT IDENTITY (1,1),
                                id_game INT NOT NULL,
                                CONSTRAINT PK_DECKS_OF_CARDS PRIMARY KEY (id_deck_of_card),
                                CONSTRAINT FK_DECKS_OF_CARDS__GAMES FOREIGN KEY (id_game)
                                    REFERENCES GAMES (id_game)
);

CREATE TABLE BANKS (
                       id_bank INT IDENTITY (1,1),
                       id_game INT NOT NULL,
                       available_money INT NOT NULL,
                       CONSTRAINT PK_BANKS PRIMARY KEY (id_bank),
                       CONSTRAINT FK_BANKS_GAMES FOREIGN KEY (id_game)
                           REFERENCES GAMES (id_game)
);

CREATE TABLE BOX_TYPES (
                           id_box_type INT IDENTITY (1,1),
                           box_type VARCHAR(50) NOT NULL,
                           CONSTRAINT PK_BOX_TYPES PRIMARY KEY (id_box_type)
);

CREATE TABLE BOXES (
                       id_box INT IDENTITY (1,1) ,
                       id_box_type INT NOT NULL,
                       CONSTRAINT PK_BOXES PRIMARY KEY (id_box),
                       CONSTRAINT FK_BOXES__BOX_TYPES FOREIGN KEY (id_box_type)
                           REFERENCES BOX_TYPES (id_box_type)
);

CREATE TABLE CARD_TYPES (
                            id_card_type INT IDENTITY (1,1) ,
                            card_type VARCHAR(50),
                            CONSTRAINT PK_CARD_TYPES PRIMARY KEY (id_card_type)
);

CREATE TABLE CARDS (
                       id_card INT IDENTITY (1,1),
                       id_card_type INT NOT NULL,
                       is_destiny BIT NOT NULL,
                       card_description VARCHAR(200) NOT NULL,
                       card_value INT NULL,
                       id_box INT NULL,
                       CONSTRAINT PK_CARDS PRIMARY KEY (id_card),
                       CONSTRAINT FK_CARDS__CARD_TYPES FOREIGN KEY (id_card_type)
                           REFERENCES CARD_TYPES (id_card_type),
                       CONSTRAINT FK_CARDS__BOXES FOREIGN KEY (id_box)
                           REFERENCES BOXES (id_box)
);

CREATE TABLE CARDS_BY_DECKS (
                                id_card_by_deck INT IDENTITY (1,1),
                                id_deck_of_card INT NOT NULL,
                                id_card INT NOT NULL,
                                CONSTRAINT PK_CARDS_BY_DECKS PRIMARY KEY (id_card_by_deck),
                                CONSTRAINT FK_CARDS_BY_DECKS__DECKS_OF_CARDS FOREIGN KEY (id_deck_of_card)
                                    REFERENCES DECKS_OF_CARDS (id_deck_of_card),
                                CONSTRAINT FK_CARDS_BY_DECKS__CARDS FOREIGN KEY (id_card)
                                    REFERENCES CARDS (id_card)
);

CREATE TABLE TAXES (
                       id_tax INT identity (1,1),
                       id_box INT NOT NULL,
                       tax_description VARCHAR(50) NOT NULL,
                       amount INT NOT NULL,
                       CONSTRAINT PK_TAXES PRIMARY KEY (id_tax),
                       CONSTRAINT FK_TAXES__BOXES FOREIGN KEY (id_box)
                           REFERENCES BOXES (id_box)
);

CREATE TABLE DEEDS (
                       id_deed INT IDENTITY (1,1),
                       id_box INT NOT NULL,
                       deed_name VARCHAR(50) NOT NULL,
                       purchase_value INT NOT NULL,
                       mortgage_value INT NOT NULL,
                       CONSTRAINT PK_DEEDS PRIMARY KEY (id_deed),
                       CONSTRAINT FK_DEEDS__BOXES FOREIGN KEY (id_box)
                           REFERENCES BOXES (id_box)
);

CREATE TABLE ZONES (
                       id_zone INT IDENTITY (1,1),
                       zone VARCHAR(50) NOT NULL,
                       CONSTRAINT PK_ZONES PRIMARY KEY (id_zone)
);

CREATE TABLE PROVINCES (
                           id_province INT IDENTITY (1,1),
                           id_deed INT NOT NULL,
                           id_zone INT NOT NULL,
                           province_name VARCHAR(50) NOT NULL,
                           construction_value INT NOT NULL,
                           rent INT NOT NULL,
                           rent_1_farm INT NOT NULL,
                           rent_2_farms INT NOT NULL,
                           rent_3_farms INT NOT NULL,
                           rent_4_farms INT NOT NULL,
                           rent_ranch INT NOT NULL,
                           CONSTRAINT PK_PROVINCES PRIMARY KEY (id_province),
                           CONSTRAINT FK_PROVINCES__DEEDS FOREIGN KEY (id_deed)
                               REFERENCES DEEDS (id_deed),
                           CONSTRAINT FK_PROVINCES__ZONES FOREIGN KEY (id_zone)
                               REFERENCES ZONES (id_zone)
);

CREATE TABLE COMPANIES (
                           id_company  INT IDENTITY (1,1),
                           id_deed INT NOT NULL,
                           company_name VARCHAR(50) NOT NULL,
                           rent_1_company INT NOT NULL,
                           rent_2_companies INT NOT NULL,
                           rent_3_companies INT NOT NULL,
                           CONSTRAINT PK_COMPANIES PRIMARY KEY (id_company),
                           CONSTRAINT FK_COMPANIES__DEEDS FOREIGN KEY (id_deed)
                               REFERENCES DEEDS (id_deed),
);

CREATE TABLE RAILWAYS (
                          id_railway INT IDENTITY (1,1),
                          id_deed INT NOT NULL,
                          railway_name VARCHAR(50) NOT NULL,
                          rent_1_railway INT NOT NULL,
                          rent_2_railways INT NOT NULL,
                          rent_3_railways INT NOT NULL,
                          rent_4_railways INT NOT NULL,
                          CONSTRAINT PK_RAILWAYS PRIMARY KEY (id_railway),
                          CONSTRAINT FK_RAILWAYS__DEEDS FOREIGN KEY (id_deed)
                              REFERENCES DEEDS (id_deed),
);

CREATE TABLE GAMES_STYLES (
                              id_game_style INT IDENTITY (1,1),
                              game_style VARCHAR(64) NOT NULL,
                              railway_purchase BIT NOT NULL,
                              company_purchase BIT NOT NULL,
                              purchase_possibility INT NOT NULL,
                              CONSTRAINT PK_GAMES_STYLES PRIMARY KEY (id_game_style)
);

CREATE TABLE STYLES_BY_PROVINCES (
                                     id_style_by_province INT IDENTITY (1,1),
                                     id_game_style INT NOT NULL,
                                     id_province INT NOT NULL,
                                     CONSTRAINT PK_STYLES_BY_PROVINCES PRIMARY KEY (id_style_by_province),
                                     CONSTRAINT FK_STYLES_BY_PROVINCES__STYLES FOREIGN KEY (id_game_style)
                                         REFERENCES GAMES_STYLES (id_game_style),
                                     CONSTRAINT FK_STYLES_BY_PROVINCES__PROVINCES FOREIGN KEY (id_province)
                                         REFERENCES PROVINCES (id_province)
);

CREATE TABLE COLORS (
                        id_color INT IDENTITY (1,1),
                        color VARCHAR(20) NOT NULL,
                        CONSTRAINT PK_COLORS PRIMARY KEY (id_color)
);

CREATE TABLE PLAYERS (
                         id_player INT IDENTITY (1,1),
                         id_user INT,
                         id_game INT NOT NULL,
                         id_game_style INT,
                         id_color INT NOT NULL,
                         id_box INT NOT NULL,
                         player_order INT NOT NULL,
                         available_money INT NOT NULL,
                         is_active BIT NOT NULL,
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

CREATE TABLE TURNS (
                       id_turn INT IDENTITY (1,1),
                       id_player INT NOT NULL,
                       CONSTRAINT PK_TURNS PRIMARY KEY (id_turn),
                       CONSTRAINT FK_TURNS__PLAYERS FOREIGN KEY (id_player)
                           REFERENCES PLAYERS (id_player)
);

CREATE TABLE DEEDS_BY_PLAYERS (
                                  id_deed_by_player INT IDENTITY (1,1),
                                  id_deed INT NOT NULL,
                                  id_player INT NOT NULL,
                                  is_purchased BIT NOT NULL,
                                  is_mortgaged BIT NOT NULL,
                                  farm_quantity INT NOT NULL,
                                  has_ranch BIT NOT NULL,
                                  CONSTRAINT PK_DEEDS_BY_PLAYERS PRIMARY KEY (id_deed_by_player),
                                  CONSTRAINT FK_DEEDS_BY_PLAYERS__DEEDS FOREIGN KEY (id_deed)
                                      REFERENCES DEEDS (id_deed),
                                  CONSTRAINT FK_DEEDS_BY_PLAYERS__PLAYERS FOREIGN KEY (id_player)
                                      REFERENCES PLAYERS (id_player)
);
