import java.io.*;
import java.util.*;
/**
 * This is the Game class that is the central class.
 * The game object represents a real life Rummy Game object and has the neccesary attributes.
 * 
 * @author Nived.Kodavali 
 * @version 1.0(Basic)
 */

public class Game{

   //instance variables.
   /** A Deck object which contains the discard pile.**/
   Deck discard; 
   /** A Deck object which contains the pack (The pile where you draw your Cards from)**/
   Deck pack; 
   /**It is a check condition to find out when a game is over**/
   boolean status;
   /**has the player attributes such as the 'hands', different set combinations etc.**/
   Player player;
   
   /**
    * It is a non-parameterised constructor for the game object. 
    */
   public Game(){
       
      discard = new Deck (0);
      player = new Player();
      pack = new Deck(); 
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
    * This method is invohed on this Game.
    * It is the central method and the game continues till the loop in the method is iterating.
    * It is like a mediator method between the player and the computer.
    * It does the functions like asking a player the Card he/she wants to pick, the card he/she wants to play etc.
    * It also invokes the method which starts off the artificial intelligece part of the game.
    */
   public void play()throws Exception {
       pack.shuffleDeck();
       player.hand = Deck.subDeck(pack, 0, 9);
       //player.hand.cards[10] = null;
       player.compHand = Deck.subDeck(pack, 10, 19);
       //compHand.cards[10] = null;
       Deck q = new Deck(1);
       q = Deck.subDeck(pack, 20, 20);
       player.discard.cards[0] =q.cards[0];;
       pack = Deck.subDeck(pack, 21, 51);
       int index = 0;
       Scanner input = new Scanner(System.in);
           
       while(status){
           //This block is to ensure that if the pack runs out, it gets replenished by the discard pile.
           if(pack.cards.length == 0){
               pack.copyDeck(discard);
               discard = new Deck(0);
            }
           boolean inputFlag = true;
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
                       player.hand.cards[10] = player.discard.cards[0];
                       Interface.slowPrint("This is the card");
                       Card.printCard(player.discard.cards[0]);
                       player.discard.cards[0] = null;
                       player.hand = Deck.insertionSort(player.hand);
                       n = "";
                   }
                    
                   else {
                       Deck temp = player.hand; 
                       player.hand = new Deck(player.hand.cards.length+1);
                       player.hand.copyDeck(temp);
                       pack = Deck.makeSmaller(pack);
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
           
           System.out.println();
           System.out.println();
           System.out.println();
           InputStreamReader in = new InputStreamReader(System.in);
           BufferedReader br = new BufferedReader(in);
           Interface.slowPrint("Would you like to have your deck arranged rank-wise(y/n)");
           String a = br.readLine();
           if(a.equals("y"))   player.hand = Deck.rankSort(player.hand);
           else   player.hand = Deck.insertionSort(player.hand);
           Deck.printDeck(player.hand);
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
           
           index++;
           
           //if(index==15)   status = false;       
               
        }
   }
   
   /**
    * This is a security check method to ensure that a player is not bluffing.
    * When a player declares that he has won, it checks if the player really has. 
    * @param deck The hand which the Player claims to be a Rummy hand.
    */
   public static void checkWin(Deck deck)throws Exception{
       System.out.println("We have to verify that you really have won");
       Thread.sleep(2000);
       System.out.println("Please type in the NUMBER of sets you have made(2/3)");
       Scanner scanner = new Scanner(System.in);
       boolean resume = false;
       int length = scanner.nextInt();
       if(length == 2){
           Interface.slowPrint("Please type in the indexes of the Cards belonging to your first set one by one");
           Thread.sleep(500);
           Interface.slowPrint("Please type in the index of your first Card");
           scanner = new Scanner (System.in);
           int one = scanner.nextInt()-1;
           Interface.slowPrint("Please type in the index of your second Card");
           scanner = new Scanner (System.in);
           int two = scanner.nextInt()-1;
           Interface.slowPrint("Please type in the index of your third Card");
           scanner = new Scanner (System.in);
           int three = scanner.nextInt()-1;
           Interface.slowPrint("Please type in the index of your fourth Card");
           scanner = new Scanner (System.in);
           int four = scanner.nextInt()-1;
           Interface.slowPrint("Please type in the index of your fifth Card");
           scanner = new Scanner (System.in);
           int five = scanner.nextInt()-1;
           Deck set1 = new Deck(5);
           set1.cards[0] = deck.cards[one];
           set1.cards[1] = deck.cards[two];
           set1.cards[2] = deck.cards[three];
           set1.cards[3] = deck.cards[four];
           set1.cards[4] = deck.cards[five];
           int setLength = Deck.seqLength(set1,0);
           if(setLength < 5){
               System.out.print("It in not a proper set, would you like to give input again or have you made a mistake?(again/mistake)");
               scanner = new Scanner (System.in);
               String input = scanner.nextLine();
               if(input.equalsIgnoreCase("again"))    checkWin(deck);
               else{
                   Interface.slowPrint("Please resume the game");
                   resume = true;
               }
           }
           System.out.println(resume);
           
           if(!resume){
               
               Interface.slowPrint("Please type in the indexes of the Cards belonging to your second set one by one");
               Thread.sleep(500);
               Interface.slowPrint("Please type in the index of your first Card");
               scanner = new Scanner (System.in);
               one = scanner.nextInt()-1;
               Interface.slowPrint("Please type in the index of your second Card");
               scanner = new Scanner (System.in);
               two = scanner.nextInt()-1;
               Interface.slowPrint("Please type in the index of your third Card");
               scanner = new Scanner (System.in);
               three = scanner.nextInt()-1;
               Interface.slowPrint("Please type in the index of your fourth Card");
               scanner = new Scanner (System.in);
               four = scanner.nextInt()-1;
               Interface.slowPrint("Please type in the index of your fifth Card");
               scanner = new Scanner (System.in);
               five = scanner.nextInt()-1;
               Deck set2 = new Deck(5);
               set2.cards[0] = deck.cards[one];
               set2.cards[1] = deck.cards[two];
               set2.cards[2] = deck.cards[three];
               set2.cards[3] = deck.cards[four];
               set2.cards[4] = deck.cards[five];
               setLength = Deck.seqLength(set2,0);
               if(setLength < 5){
                   System.out.print("It in not a proper set, would you like to give input again or have you made a mistake?(again/mistake)");
                   scanner = new Scanner (System.in);
                   String input = scanner.nextLine();
                   if(input.equalsIgnoreCase("again"))    checkWin(deck);
                   else{
                       Interface.slowPrint("Please resume the game");
                       resume = true;
                   }
               }
           }
           System.out.println(resume);
       }
       
       else if(length == 3){
           Interface.slowPrint("Please type in the indexes of your 4-Card set");
           Thread.sleep(500);
           Interface.slowPrint("Please type in the index of your first Card");
           scanner = new Scanner (System.in);
           int one = scanner.nextInt()-1;
           Interface.slowPrint("Please type in the index of your second Card");
           scanner = new Scanner (System.in);
           int two = scanner.nextInt()-1;
           Interface.slowPrint("Please type in the index of your third Card");
           scanner = new Scanner (System.in);
           int three = scanner.nextInt()-1;
           Interface.slowPrint("Please type in the index of your fourth Card");
           scanner = new Scanner (System.in);
           int four = scanner.nextInt()-1;
           Deck set1 = new Deck(4);
           set1.cards[0] = deck.cards[one];
           set1.cards[1] = deck.cards[two];
           set1.cards[2] = deck.cards[three];
           set1.cards[3] = deck.cards[four];
           Interface.slowPrint("Is the set a sequence or a quadruple?(seq/quad)");
           scanner = new Scanner (System.in);
           String n = scanner.nextLine();
           int setLength = 0;
           if(n.equalsIgnoreCase("seq"))   setLength = Deck.seqLength(set1,0);
           else if(n.equalsIgnoreCase("quad"))   setLength = Deck.findSameRankSize(set1);
           if(setLength < 4){
               System.out.print("It in not a proper set, would you like to give input again or have you made a mistake?(again/mistake)");
               scanner = new Scanner (System.in);
               String input = scanner.nextLine();
               if(input.equalsIgnoreCase("again"))    checkWin(deck);
               else{
                   Interface.slowPrint("Please resume the game");
                   resume = true;
               }
           }
           
           if(!resume){
               Interface.slowPrint("Please type in the indexes of your first 3-Card set");
               Thread.sleep(500);
               Interface.slowPrint("Please type in the index of your first Card");
               scanner = new Scanner (System.in);
               one = scanner.nextInt()-1;
               Interface.slowPrint("Please type in the index of your second Card");
               scanner = new Scanner (System.in);
               two = scanner.nextInt()-1;
               Interface.slowPrint("Please type in the index of your third Card");
               scanner = new Scanner (System.in);
               three = scanner.nextInt()-1;
               Deck set2 = new Deck(3);
               set2.cards[0] = deck.cards[one];
               set2.cards[1] = deck.cards[two];
               set2.cards[2] = deck.cards[three];
               Interface.slowPrint("Is the set a sequence or a triplet?(seq/trip)"); 
               scanner = new Scanner (System.in);
               n = scanner.nextLine();
               setLength = 0;
               if(n.equalsIgnoreCase("seq"))   setLength = Deck.seqLength(set2,0);
               else if(n.equalsIgnoreCase("trip"))   setLength = Deck.findSameRankSize(set2);
               if(setLength < 3){
                   System.out.print("It in not a proper set, would you like to give input again or have you made a mistake?(again/mistake)");
                   scanner = new Scanner (System.in);
                   String input = scanner.nextLine();
                   if(input.equalsIgnoreCase("again"))    checkWin(deck);
                   else{
                       Interface.slowPrint("Please resume the game");
                       resume = true;
                    }
               }
               
               if(!resume){
                   Interface.slowPrint("Please type in the indexes of your second 3-Card set");
                   Thread.sleep(500);
                   Interface.slowPrint("Please type in the index of your first Card");
                   scanner = new Scanner (System.in);
                   one = scanner.nextInt()-1;
                   Interface.slowPrint("Please type in the index of your second Card");
                   scanner = new Scanner (System.in);
                   two = scanner.nextInt()-1;
                   Interface.slowPrint("Please type in the index of your third Card");
                   scanner = new Scanner (System.in);
                   three = scanner.nextInt()-1;
                   Deck set3 = new Deck(3);
                   set3.cards[0] = deck.cards[one];
                   set3.cards[1] = deck.cards[two];
                   set3.cards[2] = deck.cards[three];
                   Interface.slowPrint("Is the set a sequence or a triplet?(seq/trip)"); 
                   scanner = new Scanner (System.in);
                   n = scanner.nextLine();
                   setLength = 0;
                   if(n.equalsIgnoreCase("seq"))   setLength = Deck.seqLength(set3,0);
                   else if(n.equalsIgnoreCase("trip"))   setLength = Deck.findSameRankSize(set3);
                   if(setLength < 3){
                       System.out.print("It in not a proper set, would you like to give input again or have you made a mistake?(again/mistake)");
                       scanner = new Scanner (System.in);
                       String input = scanner.nextLine();
                       if(input.equalsIgnoreCase("again"))    checkWin(deck);
                       else{
                           Interface.slowPrint("Please resume the game");
                           resume = true;
                       }
                   }
               }
               System.out.println(resume);
           }
           /*input = scanner.nextLine();
           if(input.equalsIgnoreCase("seq")){
               
           }*/
       }
       else{
           Interface.slowPrint("Are you sure? I don't think so I am giving you another chance");
           checkWin(deck);
        }
       
       
       if(resume == false){
           Interface.slowPrint("CELEBRATE! YOU HAVE WON AGAINST THE GREATEST RUMMY PLAYER OF ALL TIME");
           Interface.printLine();
           Interface.printLine();
           Interface.printLine();
           System.exit(0);
       }
       
   }
   
   public static void checkWinComps(){
   }
   
   //Test code
   /*public static void main(String[]args)throws Exception{
       Deck deck = new Deck(10);
        deck.cards[0] = new Card(0,1);
        deck.cards[1] = new Card(0,2);
        deck.cards[2]= new Card(0,3);
        deck.cards[3] = new Card(0,4);
        deck.cards[4] = new Card(0,5);
        deck.cards[5] = new Card(1,6);
        deck.cards[6] = new Card(1,7);
        deck.cards[7] = new Card(1,8);
        deck.cards[8] = new Card(1,9);
        deck.cards[9] = new Card(1,10);
        Deck.printDeck(deck);
        checkWin(deck);
        System.out.println("fthfgjh");
    }*/
   
   
}