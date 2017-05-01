
/**
 * This is the Deck class. It has all the methods relating to the Deck object.
 * Which include:
 * Three ways of sorting, shuffling, increasing and decreasing the size of Decks, finding patterns in the Deck, Generating histograms, etc.
 * NOTE: THERE ARE SOME METHODS IN THE DECK CLASS THAT HAVE NOT BEEN INVOKED AND ARE NOT NECCESARY. HOWEVER, THEY MIGHT HAVE BEEN PART OF THE PREVIOUS ALGORITHM AND/OR MAY HELP
 * IN LATER VERSIONS AND ENHANCEMENTS OF THE GAME.
 * 
 * @author Nived.Kodavali 
 * @version Version 0.1(Basic)
 */
public class Deck{

    Card[] cards;

    /**
     * This is a parameterised constructor which creates a Deck object whick is an array of cards of desired length.
     * @param n An integer containing the size of the Deck needed.
     */
    public Deck (int n) {
        cards = new Card[n];
    }
    
    /**
     * This is a non-parameterised constructor which creates a Deck object whick is an array of cards this array is fully
     * populated and has 52 Card objects. 
     */
    public Deck () {
        cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card (suit, rank);
                index++;
            }
        }
    }
    
    /**
     * This method takes a Deck of Cards and then shuffles them up by swapping two cards at a time.
     */
    public void shuffleDeck(){
        for(int index = 0;index<cards.length;index++){
            int random =  randomInt(0, cards.length);
            swapCard(this, index, random);
        }
        //return deck;
    }
    
    /**
     * This is a non-static method which copies the larger of the two Decks onto this Deck.
     * @param a A Deck.
     */
    public void copyDeck(Deck a){
        int n;
        //n = a.cards.length;
        if(cards.length>a.cards.length)n = a.cards.length;
        else n = cards.length;
        for(int i = 0; i<n; i++){
            cards[i] = a.cards[i];
        }
    }
    
    /**
     * This is a static method where all the cards in this Deck are shifted one place backwards(So the zeroeth card becomes the oneth card and so on). 
     */
    public void moveUp(){
        for(int i = cards.length-1;i>0;i--){
            cards[i] = cards[i-1];
        }
    }
    
    /**
     * This is a static method where all the cards in this Deck are shifted one place forward(So the oneth card becomes the zeroeth card and so on). 
     */
    public void moveDown(){
        for(int i = 0;i<cards.length-1;i++){
            cards[i] = cards[i+1];
        }
    }
    
    
    /**
     * It is an instance method where this Deck which may contain null objects is modified and the null objects are 'pushed' to the back of the Deck.
     * NOTE: PRESENTLY, IT DOES NOT WORK PROPERLY, HOWEVER, IT IS NOT NEEDED IN THIS VERSION OF THE GAME.
     */
     public void nullExchange(){
        int top = cards.length-1;//4
        for(int i = 0;i<top;i++){//i = 2
            Card card = cards[i];//null
            if(card == null){
                Card card2 = cards[top];//0.3
                while(card2==null){
                   top --;
                    card2 = cards[top];
                }
                swapCard(this, i, top);
            }
        }
        //return deck;
    }
    
    /**
     * This method takes a Deck object and prints its contents in a user friendly format.
     * @param Deck object that needs to be printed.
     * 
     */
    public static void printDeck (Deck deck) throws Exception{
        for (int i=0; i<deck.cards.length; i++) {
            //if (deck.cards[i] == null) continue;
            System.out.print(i+1+") ");
            Card.printCard (deck.cards[i]);
        }
    }
    
    /**
     * This method takes a Deck of Cards and two ints. It then swaps the references of the Cards at the two given indexes.
     * @param deck A Deck in which the Cards need to be swapped.
     * @param a An int containing one of the indexes of one of the Crads that needs to be swapped.
     * @param b An int containing one of the indexes of one of the Crads that needs to be swapped.
     */
    public static void swapCard(Deck deck, int a, int b){
        //System.out.println( "swapCard"+a);
        //System.out.println("swapCard"+b);
        //if(!(deck.cards[a] == null||deck.cards[b] == null)){
                Card carda = new Card();
                carda = deck.cards[a];
                deck.cards[a] = deck.cards[b];
                deck.cards[b] = carda;
            //return deck;
           
        //}
    }
    
    /**
     * This is an overloaded version of copyDeck that is a semi modifier. It copies the contents of the second parameter Deck onto the first.
     * @param a A deck into which the contents of the other Deck needs to be copied.
     * @param a The Deck which needs to be replicated and returned.
     * @return Deck - A deck which is a copy of Deck a.
     */
    public static Deck copyDeck(Deck a, Deck b){
        a = new Deck (b.cards.length);
        for(int i = 0; i<b.cards.length; i++){
            a.cards[i] = b.cards[i];
        }
        return a;
    }
    
    /**
     * This is a recursive method that takes any two limits(one higher and one lower)and return a random integer that lie between the limits(including both of them).
     * @param low the integer that is the lower limit for the random number.
     * @param high the integer that is the higher limit for the random number.
     * @return int - a 'random' integer generated by the computer
     */
    public static int randomInt(int low, int high){
        double random = Math.random();
        double x = random*high;
        int random1 = (int)x; 
        if(random1 >= low && random1 <= high) return random1;
        else randomInt(low, high);
        return random1;
    }
    
    /**
     * This method takes a Deck and two indexes high and low to specify the range. It then creates a new Deck of length
     * high-low+1 and assigns the corresponding values of the earlier deck to the newly created Deck. It essentially 
     * replicates part of a given Deck.
     * @param deck The master deck whose subDeck is needed.
     * @param low The integer for the lower limit of the subDeck needed.
     * @param high The integer for the higher limit of the subDeck needed.
     * @return sub - The deck that is the subDeck of the master Deck as specified.
     */
    public static Deck subDeck (Deck deck, int low, int high) throws Exception{
        //System.out.println("In subDeck now");
        //System.out.println(deck.cards.length   +"   length of Deck");
        //System.out.println( high +"     high index");
        //System.out.println(low+   "     low index");
        //printDeck(deck);
        Deck sub = new Deck (high-low+1);
        for (int i = 0; i<sub.cards.length; i++) {
            sub.cards[i] = deck.cards[low+i];
        }
        return sub;
    }
    
    /**
     * This method finds the smallest card in the given Deck.
     * @param deck The Deck in which the smallest Card is to be found.
     * @param low the lower integer cnstrain above which the smallest Card is to be found.
     * @param high the higher integer cnstrain above which the smallest Card is to be found.
     */
    public static Card findLowestCard(Deck deck, int low, int high){
        Card card = deck.cards[low];
        for(int i = low+1;i<=high; i++){
            if(Card.compareCard(card,deck.cards[i])==1)card = deck.cards[i];
        }
        return card;
    }
    
    /**
     * This is a sorting method which finds the lowest Card and switches it with the Card at the first place, then the second lowest Card is switched with the Card at the
     * second place and so on.
     * @param deck The Deck that needs to be sorted.
     * @return A sorted version of the previous Deck.
     */
    public static Deck sortDeck(Deck deck){
        int index;
        for(int i = 0;i<deck.cards.length;i++){
            Card smallest = findLowestCard(deck, i,deck.cards.length-1);
            index = findIndex(deck, smallest);
            if(index == -1)break;
            swapCard(deck, index, i);
        }
        return deck;
    }
    
    /**
     * This is a sorting method that works on the algorithm of insertionSort.
     * @param deck The Deck that needs to be sorted.
     * @return A sorted version of the previous Deck.
     */
    public static Deck insertionSort(Deck deck){
        //deck = nullExchange(deck);
        for(int i = 1;i<deck.cards.length;i++){
            Card card = deck.cards[i];
            int toInsert = i;
            for(int r = i;r>0;r--){
                if(Card.compareCard(deck.cards[r-1],card)==1)toInsert = r-1;
            }
            
            for(int r = i;r>toInsert;r--){
                deck.cards[r] = deck.cards[r-1];
            }
            deck.cards[toInsert] = card;
        }
        return deck;
    }
    
    /**
     * This method takes a Deck object and eturns a integer histogram the length of the Deck where each int (-1(if nul)or 0)represents the presence of nulls.
     * @param deck The Deck which may contain nulls and whise nullHist is needed.
     */
    public static int[] nullHist(Deck deck){
        int[] nullH = new int[deck.cards.length];
        for(int i = 0;i<deck.cards.length;i++){
            if(deck.cards[i] == null)nullH[i] = -1;
        }
        return nullH;
    }
    
    /**
     * This method returns the index of a card in a deck(the index  number by which the identical element is refered. 
     * to by).
     * @param a The Deck in which the Card is to be found.
     * @param b The Card which is to be found in the Deck.
     * @return int - index of the Card in the Deck
     */
    public static int findIndex(Deck a, Card b){
        for(int index = 0;index<a.cards.length;index++){
            int q = Card.compareCard(a.cards[index], b);
            if(q==0)return index;
        }
        return -1;
    }
    
    /**
     * This is an overLoaded version of findIndex that finds the index of a Card in a standard Deck.
     * @param b The Card whose index is to be found out in a fully populated arranged Deck of Cards.
     * @return int - The index of the ghiven Card in the fully populated arranged Deck if Cards.
     */
    public static int findIndex(Card b){
        Deck a = new Deck ();
        for(int index = 0;index<a.cards.length;index++){
            int q = Card.compareCard(a.cards[index], b);
            if(q==0)return index;
        }
        return -1;
    }
        
    
    /**
     * This is a method that helps in avoiding NullPointerExceptions which occur very often when a loop traverses a deck and encounters a null object.
     * This method takes a Deck whick may contain null objects and returns a new Deck which is void of nulls.
     * @param deck A Deck object which may have null Cards which need to be removed.
     * @return deck1 - A copy of the parameter Deck except that this one does not have nulls that may have veen present in the parameter Deck.
     */
    public static Deck makeSmaller(Deck deck){
        int q = 0;
        for(int n = 0; n<deck.cards.length;n++){
            if(deck.cards[n] == null)q++;
        }
        
        //System.out.println(q);
        
        Deck deck1 = new Deck(deck.cards.length-q);
        //System.out.println(deck1.cards.length);
        q = 0;
        for(int i = 0; i<deck.cards.length;i++){
            if(deck.cards[i]==null)  continue;
            else{
                //Card.printCard(deck.cards[i]);
                deck1.cards[q] = deck.cards[i];
                q++;
            }
            
        }
        return deck1;
    }
    
    /**
     * This method takes a Deck and returns a new Deck which is bigger by one Card(an extra Card object is added at the back of the Deck).
     * @param deck A Deck which needs to be made bigger by one Crad.
     * @return deck1 - A Deck that is an exact replica of the parameter Deck except for a extra Card object in the ending.
     */
    public static Deck makeBigger(Deck deck){
        Deck deck1 = new Deck(deck.cards.length+1);
        for(int i = 0;i<deck.cards.length;i++){
            deck1.cards[i] = deck.cards[i];
        }
        return deck1;
    }
    
    /**
     * This method takes a Deck and returns a histogram which contains the frequency of each Card in the Deck.
     * @param deck A Deck whose histogram is required(frequency of each Card).
     * @return hist - an array of integers which contain values based on the frequency of the Cards in the Deck.
     */
    public static int[] deckHist(Deck deck){
        int[] hist = new int [52];
        for( int i = 0;i<deck.cards.length;i++){
            Card card = deck.cards[i];
            if(card!=null)   hist[card.suit*13+card.rank-1]++;
        }
        return hist;
    }
    
    //returns whether there is a sequence and where it starts from else -1
    /**
     * This method takes a Deck and checks whether it has a sequence in it(a set of cards where there are no gaps between the cards).
     * If it does, it returns the the index number of where it starts. However if there aren't any sequences, it returns -1;
     * @param deck A Deck that needs to be checked for a sequence.
     * @return  int.
     */
    public static int checkSequence(Deck deck){
        int[] deckH = deckHist(deck);
        int index = 0;//index of the place where the sequence starts
        int n = 0;
        for (int i = deck.cards.length-1;i>=0;i--){
            //System.out.println(i);
            if (deckH[i]>=1){
                n++;
                index = i;
                if(n==2)   break;
            }
        }
        if(n==2)   return index;
        return -1;
    }
    
    /**
     * This method is normally invoked with right after Deck.checkSequence.
     * It takes the index of where a sequence begins in a Deck and then returns the length of the sequence.
     * @param deck The Deck in which there might be a sequence.
     * @param starting The int for the first index of the sequence.
     * @return length - The integer containing the length of the sequence.
     */
    public static int seqLength(Deck deck, int starting){
        starting = findIndex(deck.cards[0]);
        int length = 0;
        int[] deckH = deckHist(deck);
        for(int i = starting;i<52;i++){
            if(deckH[i]>=1)   length++;
            else    break;
        } 
        return length;
    }
    
    //an overloaded version of seqLength which is not being used anymore
    /*/**
     * This method is normally invoked with right after Deck.checkSequence.
     * It a Deck and then returns the length of the sequence.
     *
    public static int seqLength(Deck deck){
        int starting = checkSequence(deck);
        
        if(starting == -1)   return -1;
        int length = 0;
        int[] deckH = deckHist(deck);
        for(int i = starting;i<deck.cards.length;i++){
            if(deckH[i]>=1)   length++;
            else   break;
        }
        return length;
    }
    */
    
    /**
     * This method checks if there is a sequence in the given Deck and returns a true if there is and a false if there is not.
     * @param deck The Deck that is to be checked for the sequence.
     * @return boolean - Depending upon whether there is a sequence in the Deck, a true if a sequence is present otherwise a alse.
     */
    public static boolean isSequence(Deck deck){
        int[] deckH = deckHist(deck);
        //boolean checkSequence = false;
        for(int i = 0;i<deck.cards.length-1;i++){
            if(Math.abs(findIndex(deck.cards[i]) - findIndex(deck.cards[i+1])) != 1)   return false;
        }
        return true;
    }
    
    /*
    public static int isPartOfSequence(Deck deck){
        
        deck = deck.makeSmaller(deck);
        deck = insertionSort(deck);
        int[] deckH = deckHist(deck);
        int gaps = 0;
        for(int i = 0;i<deck.cards.length-1;i++){
            if((Math.abs(findIndex(deck.cards[i]) - findIndex(deck.cards[i+1])) == 2) )  gaps++;
            if(!(Math.abs(findIndex(deck.cards[i]) - findIndex(deck.cards[i+1]))<= 2) )  return -1;
            if(deck.cards[i].rank == 13 && deck.cards[i+1].rank == 1)  return -1;
        }
        return gaps;
    }*/
    
    /**
     * This method finds the range of a deck (the number of cards between the biggest card and the smallest card).
     * @param deck The Deck which ight be part of a sequence whose range needs to be found(index of highest card in the deck - index of lowest card in the Deck.
     * @return int - The integer containing the range.
     */
    public static int findRangeLength(Deck deck){
        deck = makeSmaller(deck);
        deck = insertionSort(deck);
        if(deck.cards.length == 0)return -1;
        return(findIndex(deck.cards[deck.cards.length-1])-findIndex(deck.cards[0]));
    }
    
    /**
     * This method checks for a pair. It returns true if the parameter Deck has a pair in it.
     * A pair is a couple of Cards where both the cards have the same rank.
     * @param deck The Deck which might contain a Pair and needs to be checked.
     * @return isP - A boolean value to indicate whether there is a pair present in the Deck.
     */
    public static boolean isPair(Deck hand){
        int rankH[] = rankHist(hand);
        boolean isP = false;
        for(int i = 1;i<=13;i++)if(rankH[i]>=2)isP = true;
        return isP;
    }
    
    /**
     * This method returns a histogram containing the number of Cards of each rank are there.
     * @param deck A Deck whose rank histogram is needed.
     * @return rankH - int[] containing integers for the number of times each rank appears in the Deck.
     */
    public static int[] rankHist(Deck deck){
        int[] rankH = new int[14];
        for (int i = 0;i<deck.cards.length;i++){
            Card card = deck.cards[i];
            rankH[card.rank]++;
        }
        return rankH;
    }
    
    /**
     * This method finds the length of the deck with tha same ranks which it gets as parameters.
     * This includes a pair, a triplet, a quadruple(four of a kind).
     * @param deck A Deck object input.
     * @return - int.
     */
    public static int findSameRankSize(Deck deck){
        int rankH[] = rankHist(deck);
        int longest = 0;
        for(int i = 0;i<rankH.length;i++){
            if(rankH[i]>longest)   longest = rankH[i];
        }
        
        return longest;
    }
    
    /**
     * This method finds the rank of the cards which are part of a pair/triplet/fourOfAKind.
     * @param deck The Deck which contains the ranks.
     * @return int - The integer containing the value of the rank which is part of the set.
     */
    public static int findRank(Deck deck){
        int[] rankH = rankHist(deck);
        int longest = 0;
        int index = 0;
        for(int i = 0;i<rankH.length;i++){
            if(rankH[i]>longest){
                longest = rankH[i];
                index = i;
            }
        }
        
        return index;
    }
    
    
    /**
     * This method is yet another srting algorithm
     * This method has been written to cater to the needs of players who find it hard to visualise the Cards. Instead of returning a Deck sorted in the standard way, it returns a 
     * Deck that is sorted rank-wise.(the Aces(in suit wise order)) come first, then the deuces and so on.
     * It helps a player when he wants to make a triplet or a four of a kind in the game.
     * @param deck The Deck which needs to be sorted rank-wise.
     * @return Deck - A replica of the parameter Deck just that it is sorted rank-wise. 
     */
    public static Deck rankSort(Deck deck){
        Deck deck1 = new Deck (deck.cards.length);
        int index = 0;
        for(int i = 1;i<14;i++){
            for( int n = 0;n<4;n++){
                Card card = new Card (n,i);
                if(findIndex(deck,card)!= -1){
                    deck1.cards[index] = card;
                    index++;
                }
            }
        }
        return deck1;
    }
        
    //test code
    /*
    public static void main(String[]args)throws Exception{
        Deck deck = new Deck(5);
        deck.cards[0] = new Card(0,1);
        deck.cards[2] = new Card(0,2);
        deck.cards[4] = new Card(0,3);
        deck.nullExchange();
        printDeck(deck);
        /*Deck deck = new Deck(5);
        deck.cards[0] = new Card(0,1);
        deck.cards[2] = new Card(0,2);
        deck.cards[4] = new Card(0,3);
        printDeck(makeSmaller(deck));*/
        //deck.cards[0] = new Card(0,1);
        //deck.cards[1] = new Card(0,2);
        //deck.cards[2] = new Card(0,3);
        //deck.cards[3] = new Card(0,4);
        //deck.cards[1] = new Card(0,3);
        //printDeck(deck);
        //System.out.println(isPartOfSequence(deck));
        /*deck.cards[0] = new Card(0,1);
        deck.cards[1] = new Card(0,2);
        deck.cards[2]= new Card(0,3);
        deck.cards[3] = new Card(0,4);
        deck.cards[4] = new Card(0,6);
        deck.cards[5] = new Card(1,6);
        deck.cards[6] = new Card(2,6);
        deck.cards[7] = new Card(3,8);
        deck.cards[8] = new Card(3,9);
        deck.cards[9] = new Card(3,10);
        Deck.printDeck(deck);
        checkWin(deck);
        System.out.println("fthfgjh");
        
        
    }*/
    
}
