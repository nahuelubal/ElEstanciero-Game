# **EL ESTANCIERO**
>_TPI-LAB III---GRUPO 4_

## Consigna

**Semana 3**: Entregar un documento en formato markdown en el repositorio, en
la carpeta /docs, con todas las interfaces detectadas, que métodos proveerán
y que abstracción pretenden representar.


## Technological tools used
The tools and the technological applications used for this project are:
- Google Docs 
- Discord
- Git y GitHub


# **Interfaces**

---

## IBox

IBox interface represents the box behaviors. It does not matter the type, the box works any way. Therefore, the interface orders all box to implement a functionality every time a player lands on it. Example: We have two boxes, one of Province Type and other of Prison Type. When the player lands on either of them, it is sufficient to call the actionBox() method of the box to trigger its action without checking its data type.

| Methods                 | 
|-------------------------|
|       actionBox()       |

---

## ICard

The ICard interface represents the behavior of all the cards both fate and luck. Both types of cards can force the player to pay the bank, get paid, move, go to jail, etc. When a player must take a card, this will trigger this behavior without consulting his card type.

| Methods                 | 
|-------------------------|
|       actionCard()       |

---
## IPlayer

The players can be of two kinds, user or bot. The user as the bot have the same behaviors, it means, both buy and sell properties, improve provinces, negotiate. The difference is that the user makes natural decisions but the bot must decide through certain conditions. In conclusion, both classes must know what to do but each one must know how to do it. For this, the IPlayer Interface provides the necessary methods for the player´s behaviors and the players in charge of implementing them.

| Methods |
| ----------------- |
| buyProperty(Deed property)|
| sellProperty(Deed property)|
| addRanch(Deed property, integer quantity)|
| negotiate(Player player, Deed property, Integer amount)|

---

## IDeed

The Deeds can be of three types, province, company or railway, and each one obtains the rent of the ranch in a different way. The IDeed Interface provides the method responsible for returning rent and each one of the classes will implement the interface to return the rent according to its conditions.

| Methods |
|---|
| getRent() |