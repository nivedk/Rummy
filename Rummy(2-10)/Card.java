
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card{
    public int suit, rank;
    
    /**
     * this is a non-parameterised constructor that creates a null Card Object.
     */
    public Card () {
        this.suit = 0;
        this.rank = 0;
    }
    
    /**
     * this is a parameterised constructor that assigns the given value to the Card object
     */
    public Card (int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    /**
     * This method prints the card given to it in a user friendly format.
     */
    public static void printCard (Card c) throws Exception{
        if(c != null){
            char[] suits = { '♣', '♦', '♥', '♠' };
            String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6","7", "8", "9", "10", "Jack", "Queen", "King" };
            Interface.slowPrint(ranks[c.rank] + " of " + suits[c.suit]);
        }
        else Interface.slowPrint("null");
    }
    
    public static int compareCard(Card c1, Card c2){
        if(c2 == null)return 1;
        if(c1 == null)return -1;
        if (c1.suit > c2.suit) return 1;
        if (c1.suit < c2.suit) return -1;
        if (c1.rank > c2.rank) return 1;
        if (c1.rank < c2.rank) return -1;
        return 0;
    }
    
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
    
    public static String parseString(Card card){
        String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6","7", "8", "9", "10", "Jack", "Queen", "King" };
        return ranks[card.rank]+suits[card.suit];
    }
    
    
        
}
