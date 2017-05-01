------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE:    RUMMY
------------------------------------------------------------------------
PURPOSE OF PROJECT:    

10th INTERNAL ASSESMENT
To implement the Java that has been learnt in the past two years.
To learn how to think better and to learn (very important) how to debug methodically
------------------------------------------------------------------------
VERSION or DATE:  1.0(Basic)
------------------------------------------------------------------------ 
HOW TO START THIS PROJECT:    
Right click on the Interface class(box) >> void main(String[[]args)
------------------------------------------------------------------------ 
AUTHOR:    NIVED.KODAVALI
------------------------------------------------------------------------
USER INSTRUCTIONS:

To avoid exceptions due to invalid input, the code has been error-proofed in areas where it is necessary to type in words, however in places where single characters are given as 
input, please restrict to the correct input.

In the present version of the game, there might be some bugs. Mainly errors that are caused during exceptional cases. Measures have been taken to avoid them and the code has been 
tested a number of times, however, due to time constrains it has not been checked enough number of times and there might be some bugs in the game.
There is an exceptional error that pops out rarely, in the fixSequence method of the Player class. 
It is related to the incrementation of the variable totalGap. Since the source of the error was mysterious and could not be tracked, Rishi Sir asked me to leave it.

There is a lot of test code and many print statements which have been commented out. Please ignore them. However, some useful comments have also been included.

IMPORTANT: THE USER IS URGED TO GIVE FEEDBACK, COMPLAINTS, COMMENTS, HELP, SUGGESTIONS etc. THIS IS IMPORTANT TO ENSURE THAT LATER VERIONS OF THE GAME ARE MADE BETTER.

For detailed doccumentation, please go to the taskbar >> Tools >> Project Doccumentation
------------------------------------------------------------------------
PRESENT LIMITATIONS OF THE GAME:

Unlike the normal Rummy the user might be aware of, This version of Rummy has some limitations. Some of them are:

1)The Ace can not be used as part of a set with the King.
2)There are no jokers in the present version due to time constrain, in later versions, jokers will be included. 
3)Input provided must be as per format (as metioned) the code handles incorrect format only in a few places so please ensure that such errors do not take place.
4)Due to time constrain, in the present version the computer does not recognise all the cases where it might have won. So even if you win, you can,t be too sure that you have won
  before the Computer!!.


------------------------------------------------------------------------
LATER ENHANCEMENTS OF THE GAME: 
                                                               
There shall be more enhacements in the later versions of the games. Some are:-
1)Making it more user-friendly.                                                                
  By making input output easier.                                                                
  By displaying the cards in a visually pleasing way making it easier to play.
  Maybe by making a gui interface.
                                                                                                                                  
2)In the present version of the game, the computer does everything till sorting the cards into probabilities etc. However, in the present version of the game, the sorting happens 
  every time. has a few bugs. Due to this, thecomputer gets rid of its bad cards and picks up new Cards etc, but due to no transactions happening, it may not win the game too many 
  times in its present stage. However, in later versions, Cards will be also exchanged among the probability decks to ensure that the best combination is made in order to win the 
  game.There was another better way(algorithm) which would have made the computer smarter, however, it has a few bugs, so due tothe time constrain etc, it was dropped out in the 
  middle. The present version does the job but not so efficiently. Part of the code is still there (commented out before refreshProbabilities(Deck deck)(in the Player class)). 
  However, other parts of it hae been deleted. 
  
 3)Encode the Ace-King property of the game.
 
 4)Include Jokers. 
  
                                                                                                                                  
------------------------------------------------------------------------  

This is a game called Rummy that is enjoyed by children and adults alike.
A base skeletal version has been made here which is called basic Rummy. It does not have jokers etc like the normal Rummy which is played. 
This version is a 10 card two player one where one player plays against the computer. 
The computer using a set of algorithm based programs decides which card topick, how to arrange its cards for a win and it decides what to throw finally.
It has an array of cards called cardsNeeded to help in deciding how important a cards.
Based on the priority of a card, it does such transactions.
Since it is a basic version, the computer does not have the ability to predict the opponent's cards and play its cards accordingly etc. 
It does not use its memory of the discard Cards etc to decide whether to not expect a card etc.
In later versions, including there, with the help of cardsNeeded, it will decide the best combination, etc.
These will be inculcated in later versions.
In the present version, the rules are as follows

(1)Rummy is a 2 player card game where each player gets 10 cards each.
(2)The aim of the game is to be the first person to make: 
   One set of 4 cards and two sets of 3 cards
   OR
   two sets of 5 cards
(3)A set can either be:
   (i) a sequence of 3,4 or 5 cards of a suit. For example:
     6 of ♠, 7 of ♠, 8 of ♠ is a sequence.
      OR
   (ii) a Three of a Kind or a four of a kind. For example:
       8 of ♥, 8 of ♦, 8 of ♣ is a three of a kind. Similarly, when there are four, it is a four of a kind.
   (iii)an Ace can either be used as a card higher that a king or a card lower that a two in a set. However, it can't be used as both
(4)The game starts with a player (YOU!!) picking a card from the discard pile or the pack of Cards.The discard pile consists of the cards that are thrown at the end of turns.
       In the beginning, a random card is taken as the discard pile.
       The pack of Cards is the unused pile.
(5)At a point, a player can have only 10 cards. So when a Card is picked, a Card has to be thrown.
   The Card that has been thrown, joins the discard pile.
(6)By repeatedly picking and throwing Cards, the player has to make sets.
   Sets are made by picking up Cards that may make a set and throwing Cards that are not part of prospective set.
   
Unlike the rummy you might be aware of, this basic version does not have jokers. Another exception is that an Ace can only be used as part of a set with a two, three etc.
In CAN NOT be used with a King, Queen etc.
-----------------------------------------------------------------------
FUNCTIONING OF THE PLAYER (artificial intelligence) CLASS.
  
STEP 1- cardsNeeded[] is generated. 
------
STEP 2- with the help of cardsNeeded, the computer decides whether to pick the discard Card or a Card from the Pack.
------
STEP 3- refreshProbabilities is invoked.
------
STEP 4- refreshProbabilities invokes fixSequence and fixFourOfAKind alternatingly. 
------
STEP 5- fixSequence traverses the array from the back and puts the first prospective sequence it finds into one of the probability decks.
------
STEP 6- fixSFourOfAKind traverses the array from the front and puts the first prospective pair or triplet or fourOfAKind it finds into one of the probability decks.
------
STEP 7- this happens a few times.
------
STEP 8- the computer decides whick card to throw upto a certain level with the help of cardsNeeded(It will throw the Card which is not needed).
------
STEP 9- after if all the cards are needed, the computer sacrifices its smallest probanility Deck.
------
STEP 10- it merges the probalities into the compHand
------
STEP 11- END.
------

-------------------------------------------------------------------
WHAT I LEARNT FROM THIS PROJECT.

I learnt many important things while doing this project. Some of them are:-
1)To go step by step otherwise it is hard to trace back anything.
2)Something may seem simple in the beginning bot as you delve deeper, it becomes harder and harder.
3)How to debug the code.
*******************************************************************************************************************************************************************************