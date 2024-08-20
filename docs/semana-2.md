# **EL ESTANCIERO**
_TPI-LAB III---GRUPO 4_

---

## Consigna

**Semana 2**: Entregar un documento en formato markdown en el repositorio, en
la carpeta /docs, con la explicación de la experiencia de usuario que se
desarrollará, incluyendo los menús de navegación, acciones, eventos y
presentaciones que se usarán.


## Herramientas tecnológicas utilizadas
Las herramientas y aplicaciones tecnológicas utilizadas para este proyecto son:

- Google Docs
- Discord
- Git y GitHub


## SCREEN 1: Login
| OPTION     | ACTION                               |
|------------|--------------------------------------|
| UserName   | Input text                           |
| Password   | Input text                           |
| 1. Log In  | Select Option (go to principal menu) |
| 2. Sign Up | Select Option (go to principal menu) |

## SCREEN 2: Principal Menu
| OPTION              | ACTION                                        |
|---------------------|-----------------------------------------------|
| 1. New Game         | Select Option (go to new game options)        |
| 2. List Game Saved  | Select Option (go to list game saved options) |
| 3. Settings         | Select Option (go to settings options)        |
| 4. Quit Game        | Select Option (go to quit game options)       |
| 5. Back             | Select Option (go to login)                   |

#### SCREEN 2.1.1: Choose Difficulty
| OPTION    | ACTION                                 |
|-----------|----------------------------------------|
| 1. Hard   | Select Option (go to Player Color)     |
| 2. Normal | Select Option  (go to Player Color)    |
| 3. Easy   | Input number   (go to Player Color)    |
| 4. Back   | Select Option (go to new game options) |

#### SCREEN 2.1.2: Player Color
| OPTION                    | ACTION                                            |
|---------------------------|---------------------------------------------------|
| Enum color to choose      | Select Option  (select color, go to Money to win) |

#### SCREEN 2.1.3: Money to win
| OPTION             | ACTION                    |
|--------------------|---------------------------|
| Clarification text | Select option (yes or no) |
| Amount:            | Input Number              |

### SCREEN 2.2: List Game Saved Option
| OPTION             | ACTION                                |
|--------------------|---------------------------------------|
| 1. List Game Saved | Select Option (go to list game saved) |
| 2. Back            | Select Option (go to principal menu)  |

#### SCREEN 2.2.1: List Game Saved
| OPTION         | ACTION                                        |
|----------------|-----------------------------------------------|
| 1. Load Game   | Select Option (yes or no)                     |
| 2. Delete Game | Select Option (yes or no)                     |
| 3. Back        | Select Option (go to list game saved options) |

### SCREEN 2.3: Settings Option
| OPTION             | ACTION                                              |
|--------------------|-----------------------------------------------------|
| 1. Rules Game      | Show Rules Game                                     |
| 2. Change Password | Select Option (go to Screen Change Password Option) |
| 3. Back            | Select Option (yes or no)                           |

### SCREEN 2.3.2: Change Password Option
| OPTION                  | ACTION                          |
|-------------------------|---------------------------------|
| 1. Current Password     | Input text for current password |
| 2. New Password         | Input text for new password     |


### SCREEN 2.4: Quit Game Option
| OPTION                                     | ACTION                     |
|--------------------------------------------|----------------------------|
| Are you sure you want to quit the game?    | Select option (yes or no)  |
## SCREEN 3: Game Board

### SCREEN 3.0: Active Game Option
| OPTION           | ACTION                                                             |
|------------------|--------------------------------------------------------------------|
| 1. Roll Dice     | Select Option (Always shown in this menu)                          |
| 2. Sell Property | Select Option (Only if the user has at least one property to sell) |
| 3. Buy Farms     | Select Option (Only if the user has at least one entire province)  |
| 4. Buy Ranch     | Select Option (Only if the user has at least one entire province)  |
| 5. Quit Game     | Select Option (Message Game Saved Automatically)                   |

#### SCREEN 3.0.1: Box Types
When the player roll dices he/she will fall on only one of this kind boxes

| Box Type              | ACTION                                                                                                                                                                   |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1. Property           | Select option (go to property menu). The properties can be companies, railway or province.                                                                               |
| 2. Prize              | Bank pays the bounty to the player                                                                                                                                       |
| 3. Tax                | The player must pay to the bank. He goes to the Payment Menu.                                                                                                            |
| 4. Lucky/Destiny Card | The app will show the card message and then will give the options depending on the card type. The card can make the player pay, earn money, go to jail, etc.             |
| 5. Jail               | No menu at the first turn. Inform the player went to jail. On the second turn the user goes to Jail Menu                                                                 |
| 6. Go to Jail         | Move the player to the Jail Box. Idem 5. Jail                                                                                                                            |
| 7. Rest               | No menu at the first turn. Inform the player to rest. At the second turn the app asks the player if he wants to keep resting (only for 2 turns). The user must roll dice |
| 8. Free Parking       | Explain to the user that he will not be paid while he stays in the box                                                                                                   |
| 9. Exit               | The bank gives to the player 5000 when he/she goes trough this box, it's not necessary to stay in this.                                                                  |

#### SCREEN 3.0.2: PROPERTY MENU
If the player goes to a property box it has a few options. In case the property has no owner the user will be able to buy it.

| OPTION           | ACTION                                                                                                  |
|------------------|---------------------------------------------------------------------------------------------------------|
| 1. Buy Property  | The property will appear in the user’s property list. The game continues.                               |
| 2. Sell Property | Property list will be shown to the user and he will be able to select one and decide what to do with it |
| 3. Finish turn   | Next player plays                                                                                       |
In case the property has an owner, the player will have to pay him. Shows the payment Menu.
Finally, if the user owns the property of the box, the turn automatically ends.

#### SCREEN 3.0.3: PAYMENT MENU

The user will have to pay in many instances of the game.
Those are:
- When the user buys a property
- When the user has to pay a rent or a tax
- The user picks a Luck or Destiny Card
- The user goes to jail

In those cases this menu will be shown:

| Option                                                                                                      | Action                                                                       |
|-------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| 1. Pay $(money) to (bank or other player)(This option will be shown when the user has enough money to pay.) | The money will be discounted from the user account. Then the game continues. |
| 2. Sell a property (In case the user can’t pay)                                                             | This will show a list of properties to sell and its amounts.                 |
| 3. Mortgage a property (In case the user can’t pay)                                                         | This will show a list of properties to mortgage and its amounts.             |

#### SCREEN 3.0.4: REST MENU

if the player is at rest after having been on the previous turn, he will see this menu

| Option                                              | Action                      |
|-----------------------------------------------------|-----------------------------|
| 1. Do you want to keep resting?                     | Select option (yes or no)   |

#### SCREEN 3.0.4: JAIL MENU

if the player is at jail after having been on the previous turn, he will see this menu

| Option                                             | Action                                                                  |
|----------------------------------------------------|-------------------------------------------------------------------------|
| 1. Use card (only if the player has Habeas Corpus) | This will cause the player to come out and roll the dice                |
| 2. Pay bail (Bail Amount)                          | This will pay the bank the bail amount and let the player roll the dice |
| 3. Roll Dice                                       | If the player gets a double on the dice, the player gets out of jail    |

## SCREEN 4: SHOW DATA GAME
| Show on Screen | Item                      |
|----------------|---------------------------|
| Players Data   | NickName                  |
|                | Money                     |
|                | Province List             |
|                | Company List              |
|                | Railway List              |
| Bank           | Money                     |

## SCREEN 5 : LOG GAME

| Shows on Screen the history of the game, example:player 2 bought South Cordoba. | Log                                                                                            |
|---------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|
|                                                                                 | Buy                                                                                            |
|                                                                                 | Pay                                                                                            |
|                                                                                 | Bankrupt player                                                                                |
|                                                                                 | Winner player                                                                                  |
| Property list of the user                                                       | The list will be ordered by type, name and zone.Every item will have its name, zone and color. |

            


