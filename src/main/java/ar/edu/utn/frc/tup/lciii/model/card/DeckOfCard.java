    package ar.edu.utn.frc.tup.lciii.model.card;

    import lombok.Data;

    import java.util.*;

    @Data
    public class DeckOfCard {

        private Queue<AbstractCard> cards;
        private List<AbstractCard> transitionalCards;

        public DeckOfCard() {
            cards = new LinkedList<>();
            transitionalCards = new ArrayList<>();
        }

        /**
         * Mezcla el mazo
         */
        public void shuffleDeck(){
            List<AbstractCard> shuffledDeck = (List<AbstractCard>) this.cards;
            Collections.shuffle(shuffledDeck);
            this.cards = new LinkedList<>(shuffledDeck);
        }

        /**
         * Saca una carta del mazo y la elimina del mismo,
         * a su vez esa carta eliminada, se agrega a un historial de cartas sacadas
         *
         * @return retorna la primera carta que saca del mazo
         */
        public AbstractCard takeCard(){
            AbstractCard card = cards.poll();
            if (card != null) {
                transitionalCards.add(card);
            }
            return card;
        }

        /**
         * La carta que saca del mazo la vuelve a colocar al final del mismo y se
         * elimina del historial de cartas sacadas, ya que volvio al mazo principal.
         * Se utiliza de esta manera, ya que puede haber cartas que se sacaron del mazo y se las queda el jugador
         * por ende no la regresa, como la carta de "salir de la carcel". Una vez utilizada la misma, ya puede
         * regresarla al mazo principal, pasandola por parametro.
         */
        public void putCard(AbstractCard card){
            cards.add(card);
            transitionalCards.remove(card);
        }

        public void putHabeasCorpus(){
            AbstractCard card = transitionalCards.get(0);
            cards.add(card);
            transitionalCards.remove(0);
        }
    }