# **EL ESTANCIERO**
>_TPI-LAB III---GRUPO 4_

## Consigna

**Semana 5**: Entregar un documento en formato markdown en el repositorio, en
la carpeta /docs, con los patrones de diseño que se pretenden utilizar con su
explicación de porqué seleccionaron dicho patrón y qué problema pretende
resolver.


## Herramientas tecnológicas utilizadas
Las herramientas y aplicaciones tecnológicas utilizadas para este proyecto son:
- Google Docs
- Discord
- Git y GitHub


## **Patrón Strategy**
### _Por qué fue elegido:_
Permite colocar en clases separadas llamadas estrategias, los distintos comportamientos que una clase implementa en un método específico.
En los casos siguientes, tanto Box, como BotPlayer y Card, tienen distintas formas de accionar ante casos particulares que se presentan.

- **Box**: Se implementan CardBox,CompanyBox,FarmerPrizeBox,GoToJailBox, JailBox, ProvinceBox, RailwayBox, RestBox, StartBox, TaxBox , que responden de distinta manera al metodo actionBox()
-  **BotPlayer**: Se implementan las clases ConservativeBotPlayer, WellBalancedBotPlayer y AggresiveBotPlayer que responden de distinta manera a los distintos métodos que forman parte del flujo del juego
-  **Card**: Se implementan las clases PaymentCard, ChargeCard y MovementCard, que responden de distinta manera al metodo actionCard()



| Donde se aplicará | Problema a Resolver                                                        |
|-------------------|----------------------------------------------------------------------------|
| BOX               | Asignar el comportamiento de cada casilla                                  |
| BOTPLAYER         | Asignar el comportamiento de cada bot de acuerdo a la dificultad del juego |
| CARD              | Asignar el comportamiento de cada casilla                                  |

## **Patrón Factory**
### _Por qué fue elegido:_
Permite construir objetos sin la necesidad de llamar al operador new. En su lugar, se invoca a un método perteneciente a una clase fábrica, la cuál agrupa un conjunto de clases de la misma “familia” y tiene como objetivo crear las instancias de una forma limpia y flexible.
En los casos siguientes, elegimos utilizar el patrón Factory para simplificar la creación de los objetos de las distintas clases que heredan.

- **Box:**  Implementamos una clase abstracta BoxFactory.
- **Player:** Implementamos una clase abstracta PlayerFactory.
- **Card:** Implementamos una clase abstracta CardFactory.
- **Deed:** Implementamos una clase abstracta DeedFactory.


| Donde se aplicará | Problema a Resolver           |
|-------------------|-------------------------------|
| BOX               | Instanciar Objetos de IBox    |
| BOTPLAYER         | Instanciar Objetos de IPlayer |
| CARD              | Instanciar Objetos de ICard   |
| DEED              | Instanciar Objetos de IDeed   |

## **Patrón Iterator**
### _Por qué fue elegido:_
Permite que un algoritmo itere sobre una colección sin necesidad de conocer los detalles de implementación de la colección. En los siguientes casos aplicamos este patrón para separar las responsabilidades sobre el manejo de estas listas y simplificar su uso:
- **DeckOfCard:** Esta clase se encargará de la funcionalidad del listado de cartas tanto de suerte o destino. La lista será del tipo cola dado que cuando se levanta una carta se toma del inicio y cuando se devuelve, se agrega al final.
- **Boxes:** El listado de casilleros tiene una funcionalidad muy particular, se recorre la lista a partir de los números obtenidos en los dados. Con un iterator podemos recorrerlo y enmascarar la implementación para que sea más simple obtener los valores de las casillas
- **Players:** Dentro del tablero habrá un listado de jugadores ordenado según el turno. Este caso se puede implementar con una cola para obtener el jugador que sigue en el turno. Al igual que DeckOfCard con un iterator se puede simplificar su uso.

| Donde se aplicará | Problema a Resolver                 |
|-------------------|-------------------------------------|
| DECKOFCARD        | Listado de cartas destino y suerte  |
| BOXES             | Listado de casilleros en el tablero |
| PLAYERS           | Listado de jugadores según su turno |

## **Patrón Singleton**
### _Por qué fue elegido:_
Permite utilizar una única instancia de esta clase en todo el programa , para evitar que se creen nuevos objetos al momento que necesiten ser utilizados.

- **Dashboard**: Contiene un atributo static Dashboard instance que será accedido a través del método public getDashboardInstance().
- **Bank**: Contiene un atributo static Bank instance que será accedido a través del método public getBankInstance().
- **Game:** Contiene un atributo static Game instance que será accedido a través del método public getGameInstance().

| Donde se aplicará  | Problema a Resolver                                                             |
|--------------------|---------------------------------------------------------------------------------|
| DASHBOARD          | Proporcionar un punto de acceso global a esa instancia desde una única partida. |
| BANK               | Proporcionar un punto de acceso global a esa instancia desde una única partida. |
| GAME               | Proporcionar un punto de acceso global a esa instancia desde una única partida. |

## **Patrón MVC**
### _Por qué fue elegido:_
El patrón MVC se eligió para dividir la aplicación en capas bien definidas y promover la modularidad, la flexibilidad y la mantenibilidad del código. Esto tiene como resultado un desarrollo colaborativo mucho más sencillo, permitiendo a los diferentes integrantes trabajar en paralelo sobre distintas partes del proyecto, disminuyendo la posibilidad de generar conflictos o afectar al desarrollo de otro.

| Donde se aplicará | Problema a Resolver           |
|-------------------|-------------------------------|
| PROJECT          | Pretende resolver los problemas que se puedan presentar al mantener el código y desarrollar colaborativamente.    |

## **Patrón Repository**
### _Por qué fue elegido:_
El patrón Repository se seleccionó por su estrecha relación con el patrón MVC, permitiendo una clara separación entre la lógica de negocio y la lógica de acceso a datos.

| Donde se aplicará | Problema a Resolver                                                                                                                                          |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| PROJECT          | Centraliza toda la lógica de acceso a datos en un único lugar, lo que simplifica la gestión y el mantenimiento del código relacionado con el acceso a datos. |