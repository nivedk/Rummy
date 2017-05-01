/**
 * This is the Card class which has many methods related to the Card object.
 * Some of the methods it has are methods that convert a Card to a String, vice-versa,print a Card, compare Cards, etc.
 * 
 * Each Card object has two attributes.
 * suit 
 * rank
 * 
 * @author Nived.Kodavali
 * @version 1.0(Basic)
 */
public class Card{
    

    /** This integer or char represents the suit of a real life card. It can either be 0,1,2,3 (as ints) or '♣', '♦', '♥', '♠'(as chars) **/
    public int suit;
    /**This integer represents the rank of a real life card. Ranging from 1,2,3,4,5,6,7,8,9,10,11,12,13.("Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King")
     * as Strings. For covenience of usage, in cases of arrays of Cards(Decks), the 0eth element has been left. Instead, there is a place-keeper(narf)**/
    public int rank;
    
    /**
     * this is a non-parameterised constructor that creates a null Card Object.
     */
    public Card () {
        this.suit = 0;
        this.rank = 0;
    }
    
    /**
     * this is a parameterised constructor that assigns the given value to the Card object
     * @param suit The int containing the suit.
     * @param rank The int containing the rank.
     */
    public Card (int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    /**
     * Returns a string representation of this Card.
     */
    public String toString () {
        String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6","7", "8", "9", "10", "Jack", "Queen", "King" };
        return ranks[rank]+ " " + suits[suit];
    }
    
    /**
     * This method prints the card given to it in a user friendly format.
     * @param c The Card which needs to be printed in a user friendly format.
     */
    public static void printCard (Card c) throws Exception{
        if(c != null){
            char[] suits = { '♣', '♦', '♥', '♠' };
            String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6","7", "8", "9", "10", "Jack", "Queen", "King" };
            Interface.fastPrint(ranks[c.rank] + " of " + suits[c.suit]);
        }
        else Interface.fastPrint("null");
    }
    
    /**
     * This method compares two Cards and returns an integer according to whichever one is greater
     * @param c1 The first Card to be compared.
     * @param c2 The second Card to be compared.
     * @return int - 0 if the Cards are equal; 1 if the first Card is greater; 2 if the second Card is greater.
     */
    public static int compareCard(Card c1, Card c2){
        if(c2 == null)return 1;
        if(c1 == null)return -1;
        if (c1.suit > c2.suit) return 1;
        if (c1.suit < c2.suit) return -1;
        if (c1.rank > c2.rank) return 1;
        if (c1.rank < c2.rank) return -1;
        return 0;
    }
    
    /**
     * This method takes a string in form of "(rank) of (suit)". It returns a Card object that is its equivalent.
     * @param output The string that is to be converted into a Card.
     * @return Card - A Card which represents that input string.
     */
    public static Card parseCard(String output){
        String[] suit = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6","7", "8", "9", "10", "Jack", "Queen", "King" };
        int length = output.length();
        int s = output.indexOf(" ");
        String suit1 = output.substring(0, s);
        int rankNo = 0;
        for(int index = 0;index< 14;index++){
            if(suit1 == ranks[index])rankNo = index;
        }
        int r = output.indexOf(" ", s+1);
        suit1 = output.substring(r+1, length);
        int suitNo = 0;
        for(int index = 0;index<= 3;index++){
            if(suit1 == suit[index])suitNo = index;
        }
        return new Card(suitNo, rankNo);
    }
    
    /**
     * This method is the exact opposite of parseCard.
     * It takes a Card object and retruns a string in form of "(rank) of (suit)".
     * @param card A Card that needs to be converted to a String.
     * @return String - A string that represents the Card given as a parameter.
     */
    public static String parseString(Card card){
        String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6","7", "8", "9", "10", "Jack", "Queen", "King" };
        return ranks[card.rank]+suits[card.suit];
    }
      
}
