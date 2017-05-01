
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player{
    

   Deck hand;
   Deck compHand;
   Deck oppCard;
   Deck discard;
   Deck prob1;
   Deck prob2;
   Deck prob3;
   Deck prob4;
   Deck prob5;
   // boolean[] isProbSequence;//This is an array where each element represents 1 of the probs. It is used 
   int[] cardsNeeded;
   
   public Player(){
       this.hand = new Deck(10);
       this.compHand = new Deck (10);
       this.oppCard = new Deck(0);
       this.prob1 = new Deck(1);
       this.discard = new Deck(1);
       this.prob2 = new Deck(1);
       this.prob3 = new Deck(1);
       this.prob4 = new Deck(1);
       this.prob5 = new Deck(1);
       this.cardsNeeded = new int[52];
       
   }
   
   //public static void begin(Game g
    
   public static void start(Game game)throws Exception{
       game.player.refreshCardsNeeded();
       
       System.out.println("Player.start has been invoked");
       pickCard(game);
       Player player = game.player;
       player.refreshCardsNeeded();
       int seqIndex = 0;
       int length = 0;
        //Deck deck;
       if(player.compHand.cards.length>=2){
           seqIndex =  Deck.checkSequence(player.compHand);//The integer is the place where the sequence starts.
           if(seqIndex>=0){
               length = Deck.seqLength(player.compHand,seqIndex);
                
           }
                
       }
        
       Deck deck = new Deck(length);
       for(int i = 0;i<length;i++){
           deck.cards[i] = player.compHand.cards[seqIndex];
       }
               
       if(length>=2){
           for(int i = 1;i<=5;i++){
               if(player.prob1.cards.length==0){
                   player.prob1.copyDeck(deck);
               }
               
               else if(player.prob2.cards.length==0){
                   player.prob2.copyDeck(deck);
               }
               
               else if(player.prob3.cards.length==0){
                   player.prob3.copyDeck(deck);
               }
                
               else if(player.prob4.cards.length==0){
                   player.prob4.copyDeck(deck);
               }
                
               else if(player.prob5.cards.length==0){
                   player.prob5.copyDeck(deck);
               }
                
           }
            
       }
       
       game.player.refreshProbabilities();
       
        
       
   }
   
    
   public static void pickCard(Game game)throws Exception{
       
       Card card = game.player.discard.cards[0];
       game.player.compHand = Deck.makeBigger(game.player.compHand);
       if(game.player.cardsNeeded[card.suit*13+card.rank]>=1){
           game.player.compHand.cards[10] = card;
           //Card.printCard(card);
           
           //System.out.println("CARD (discard)");
       }
       else{
           game.player.compHand.cards[10] = game.pack.cards[0];
           //Deck.printDeck(game.player.compHand);
           //System.out.println("CARD(pack)");
           //Card.printCard(game.pack.cards[0]);
           game.pack.cards[0] = null;
           Deck.makeSmaller(game.pack);
            
       }
       
       for(int i = 0;i<52;i++){
           System.out.println(game.player.cardsNeeded[i]);
       }
       Deck forIndex = new Deck();
       game.player.compHand = Deck.insertionSort(game.player.compHand);
       Deck.printDeck(game.player.compHand);
       System.out.println();
       System.out.println("This was the discard card");
       Card.printCard(card);
       System.out.println(game.player.cardsNeeded[Deck.findIndex(forIndex,card)]);
       System.out.println("This was the pack card");
       Card.printCard(game.player.compHand.cards[10]);
       System.out.println(game.player.cardsNeeded[Deck.findIndex(forIndex,game.player.compHand.cards[10])]);
   }
   
   
   public void refreshCardsNeeded()throws Exception{
       Deck deck = new Deck();
       for(int i = 0; i<52;i++){
           cardsNeeded[i] = 0;
       }
       
       Deck hand1= new Deck(compHand.cards.length+prob1.cards.length+prob2.cards.length+prob3.cards.length+prob4.cards.length+prob5.cards.length);
       compHand = Deck.makeSmaller(compHand);
       //Deck.printDeck (hand);
       prob1 = Deck.makeSmaller(prob1);
       prob2 = Deck.makeSmaller(prob2);
       prob3 = Deck.makeSmaller(prob3);
       prob4 = Deck.makeSmaller(prob4);
       prob5 = Deck.makeSmaller(prob5);

       
       //hand1.copyDeck(hand);
       int w = 0;
       for(int n = 0;n<compHand.cards.length;n++){
           hand1.cards[w] = compHand.cards[n];
           w++;
       }
        
       for(int n = 0; n<prob1.cards.length; n++){
           hand1.cards[w] = prob1.cards[n];
           w++;
       }
        
       for(int n = 0; n<prob2.cards.length; n++){
           hand1.cards[w] = prob2.cards[n];
           w++;
       }
       
       for(int n = 0; n<prob3.cards.length; n++){
           hand1.cards[w] = prob3.cards[n];
           w++;
       }
       
       for(int n = 0; n<prob4.cards.length; n++){
           hand1.cards[w] = prob4.cards[n];
           w++;
       }
       
       for(int n = 0; n<prob5.cards.length; n++){
           hand1.cards[w] = prob5.cards[n];
           w++;
       }
       hand1 = Deck.insertionSort(hand1);
       //Deck.printDeck(hand1);
       //System.out.println();
       //System.out.println();
       //System.out.println();
       hand1 = Deck.makeSmaller(hand1);
       //Deck.printDeck(hand1);
        
       int[] hist1 = Deck.deckHist(deck);
       //for(int i = 0;i<52;i++){
            //System.out.println(hist1[i]);
        //}
       int[] hist2 = Deck.deckHist(compHand);
       //REDO THE KING,TWO,ACE,QUEEN PART AGAIN WITH A SERIES OF IFS
       for(int i = 0; i<52; i++){
           Card card1 = deck.cards[i];
           for(int n = 0; n<compHand.cards.length; n++){
               Card card2 = compHand.cards[n];
               int difference = Math.abs(((card1.suit*13)+card1.rank)-((card2.suit*13)+card2.rank));
               if(difference>0&&difference<=2){
                   cardsNeeded[i]++;
               }
                
               //The nested if block is to ensure that cardsNeeded does not get incremented when a king or a queen of a lower suit are being compared with an ace or a two of the next suit 
               /*if(card1.rank <= 2){   
                   if(((card1.suit*13)+card1.rank>((card2.suit*13)+card2.rank))){
                       if((Math.abs(((card1.suit*13)+card1.rank)-((card2.suit*13)+card2.rank)))<=2)   cardsNeeded[i]--;
                   }
               }*/
           
               if(prob1.cards.length<=4 && prob2.cards.length<=4 && prob3.cards.length<=4 && prob4.cards.length<=4 && prob5.cards.length<=4){ 
                  if(card1.rank == card2.rank && card1.suit != card2.suit)   cardsNeeded[i]++;
               }
           }
       }
        
       //for(int i = 0;i<52;i++){
           //Deck q = new Deck();
           //System.out.println(q.cards[i].rank+" of "+q.cards[i].suit+"     "+cardsNeeded[i]);
        //}
           
   }
   
   //WORK IN PROGRESS
   public void refreshProbabilities()throws Exception{
       //for(int i = 0; i < prob1.cards.length; i++){
           
       for(int n = 0; n < compHand.cards.length; n++){
           
           Card card = compHand.cards[n];
           prob1 = Deck.makeSmaller(prob1);  
           if(prob1.cards.length<=1)   break;
           prob1 = Deck.makeBigger(prob1);
           Deck deck = new Deck (prob1.cards.length);
           deck.copyDeck(prob1);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob1);
           int with = Deck.isPartOfSequence(deck);
           if(with<=without){
               if(Deck.findRangeLength(prob1)<=4){
                   prob1 = Deck.makeBigger(prob1);
                   prob1.cards[prob1.cards.length-1] = card;
                   compHand.cards[n] = null;
                   compHand = Deck.makeSmaller(compHand);
               }
               else   break; 
           }              
       }
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob2 = Deck.makeSmaller(prob2); 
           if(prob2.cards.length<=1)   break;
           prob2 = Deck.makeBigger(prob2);
           Deck deck = new Deck (prob2.cards.length);
           deck.copyDeck(prob2);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob2);
           int with = Deck.isPartOfSequence(deck);
           if(with<=without){
               if(Deck.findRangeLength(prob2)<=4){
                   prob2 = Deck.makeBigger(prob2);
                   prob2.cards[prob2.cards.length-1] = card;
                   compHand.cards[n] = null;
                   compHand = Deck.makeSmaller(compHand);
               }
               else   break; 
           }              
       }
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob3 = Deck.makeSmaller(prob3);
           if(prob3.cards.length<=1)   break;
           prob3 = Deck.makeBigger(prob3);
           Deck deck = new Deck (prob3.cards.length);
           deck.copyDeck(prob3);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob3);
           int with = Deck.isPartOfSequence(deck);
           if(with<=without){
               if(Deck.findRangeLength(prob3)<=4){
                   prob3 = Deck.makeBigger(prob3);
                   prob3.cards[prob3.cards.length-1] = card;
                   compHand.cards[n] = null;
                   compHand = Deck.makeSmaller(compHand);
               }
               else   break; 
           }              
       }
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob4 = Deck.makeSmaller(prob4);   
           if(prob4.cards.length<=1)   break;
           prob4 = Deck.makeBigger(prob4);
           Deck deck = new Deck (prob4.cards.length);
           deck.copyDeck(prob4);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob4);
           int with = Deck.isPartOfSequence(deck);
           if(with<=without){
               if(Deck.findRangeLength(prob4)<=4){
                   prob4 = Deck.makeBigger(prob4);
                   prob4.cards[prob4.cards.length-1] = card;
                   compHand.cards[n] = null;
                   compHand = Deck.makeSmaller(compHand);
               }
               else   break; 
           }              
       }
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob5 = Deck.makeSmaller(prob5); 
           if(prob5.cards.length<=1)   break;
           prob5 = Deck.makeBigger(prob5);
           Deck deck = new Deck (prob5.cards.length);
           deck.copyDeck(prob5);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob5);
           int with = Deck.isPartOfSequence(deck);
           if(with<=without){
               if(Deck.findRangeLength(prob5)<=4){
                   prob5 = Deck.makeBigger(prob5);
                   prob5.cards[prob5.cards.length-1] = card;
                   compHand.cards[n] = null;
                   compHand = Deck.makeSmaller(compHand);
               }
               else   break; 
           }              
       }
       Deck.printDeck (prob1);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob2);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob3);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob4);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob5);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck(compHand);
       //}
       
   }
       
   
    
   public static void main(String []args)throws Exception{
       
       Player n = new Player();
       n.compHand.cards[0] = new Card(0,1);
       n.prob1.cards[0] = new Card(0,2);
       n.prob2.cards[0] = new Card(0,3);
       n.prob3.cards[0] = new Card(0,4);
       n.prob4.cards[0] = new Card(0,5);
       n.prob5.cards[0] = new Card(0,6);
       n.refreshCardsNeeded();
        
   }
        
  }
