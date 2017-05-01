
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.util.*;
public class Game{

   //Game object contains
   Deck discard; 
   Deck pack; 
   //Deck compHand;
   
   boolean status;//(It is a check condition to find out when a game is over)
   Player player;//(has the player attributes such as trying to predict opponent's hand, different set combinations etc.)
   
   /**
    * It is a non-parameterised constructor for the game object. 
    */
   public Game(){
       
      //iscard = new Deck (0);
      player = new Player();
      pack = new Deck(); 
      //compHand = new Deck (10);
      player.hand = new Deck (10);
      status = true;
      
       
   }
   
   /**
    * This method begins the game. It creates a Game object 
    */
   public static void begin()throws Exception{
       Game game = new Game();
       game.play();
   }
   
   /**
    * This method is the central method and the game continues till the loop in the method is iterating.
    * It is like a mediator method between the player and the computer.
    * It does the functions like asking a player the Card he/she wants to pick, the card he/she wants to play etc.
    * It also invokes the method which starts off the artificial intelligece part of the game.
    */
   public void play()throws Exception {
       pack = Deck.shuffleDeck(pack);
       player.hand = Deck.subDeck(pack, 0, 9);
       //Deck.printDeck(player.hand);
       //player.hand.cards[10] = null;
       player.compHand = Deck.subDeck(pack, 10, 19);
       //compHand.cards[10] = null;
       Deck q = new Deck(1);
       q = Deck.subDeck(pack, 20, 20);
       player.discard.cards[0] =q.cards[0];
       //Card.printCard(player.discard.cards[0]);
       pack = Deck.subDeck(pack, 21, 51);
       //Deck.printDeck(player.hand);
       //Deck.printDeck(compHand);
       //Deck.printDeck(discard);
       //Deck.printDeck(pack);
       
       Scanner input = new Scanner(System.in);
           boolean inputFlag = true;
       while(status){
           Interface.slowPrint("These are your Cards");
           Deck.insertionSort(player.hand);
           Deck.printDeck(player.hand);
           Interface.printLine();
           Interface.slowPrint("__________      ___________");
           Interface.slowPrint("|        |      |         |");
           Interface.slowPrint("|        |      |         |");
           String parse = Card.parseString(player.discard.cards[0]);
           Interface.slowPrint("|  PACK  |      | DISCARD |");
           Interface.slowPrint("|        |      |"+parse+"|");
           Interface.slowPrint("|________|      |_________|");
           Interface.slowPrint("Where would you like to pick your card from?(pack/discard)");
           //Scanner input = new Scanner(System.in);
           //boolean inputFlag = true;
           while (inputFlag) {
               System.out.println ("---------Input Please--------------");
               String n= input.nextLine();
               while (n.equalsIgnoreCase("discard")|| n.equalsIgnoreCase("pack")) {
                   inputFlag = false; 
                   //n = input.next();
                   if(n.equalsIgnoreCase("discard")){
                       Deck temp = player.hand; 
                       player.hand = new Deck(player.hand.cards.length+1);
                       player.hand.copyDeck(temp);
                       /*System.out.println();
                       System.out.println();
                       Deck.printDeck(player.hand);
                       System.out.println();
                       System.out.println();*/
                       player.hand.cards[10] = player.discard.cards[0];
                       Interface.slowPrint("This is the card");
                       Card.printCard(player.discard.cards[0]);
                       player.discard.cards[0] = null;
                       //System.out.println ("------------------------------------------> In The Discard Block");
                       //player.discard = Deck.makeSmaller(discard);
                       player.hand = Deck.insertionSort(player.hand);
                       n = "";
                   }
                    
                   else {
                       //System.out.println ("------------------------------------------> In The Pack Block");
                       Deck temp = player.hand; 
                       player.hand = new Deck(player.hand.cards.length+1);
                       player.hand.copyDeck(temp);
                       player.hand.cards[10] = pack.cards[0];
                       Interface.slowPrint("This is the card");
                       Card.printCard(pack.cards[0]);
                       pack.cards[0] = null;
                       pack = Deck.makeSmaller(pack);
                       n= "";
                   }
               }
               if (inputFlag)   Interface.slowPrint("Wrong input. Please choose between pack and discard");
               else break;
           }
           
           //Card.printCard(pack.cards[0]); 
           //Card.printCard(discard.cards[0]); 
           System.out.println();
           System.out.println();
           System.out.println();
           player.hand = Deck.insertionSort(player.hand);
           Interface.slowPrint("Which card would you like to throw?");
           Deck.printDeck (player.hand);
           Interface.slowPrint("Please give the index of the card(for the 1st card, give one. For the second card give 2");
           int r = input.nextInt();
           //discard.moveDown();
           Deck temp = player.discard;
           player.discard = new Deck (temp.cards.length+1);
           player.discard.copyDeck(temp);
           player.discard.moveUp();
           player.discard.cards[0] = player.hand.cards[r-1];
           player.hand.cards[r-1] = null;
           player.hand = Deck.insertionSort(player.hand);
           player.hand = Deck.makeSmaller(player.hand);
           Interface.slowPrint("Now these are your cards");
           Deck.printDeck(player.hand);
           Interface.slowPrint("Have you won??(y/n)");
           Scanner  scanner = new Scanner(System.in);
           String n = scanner.nextLine();
           //String n = input.nextLine();
           if(n.equalsIgnoreCase("y"))   checkWin(player.hand);
           Player.start(this);
           
        
           
                status = false;       
               
        }
   }
   
   /**
    * This is a security check method to ensure that a player is not bluffing.
    * When a player declares that he has won, it checks if the player really has. 
    */
   public static void checkWin(Deck deck){
       
   }
   
   
}