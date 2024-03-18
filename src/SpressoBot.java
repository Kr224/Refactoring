/**
   File: SpressoBot.java
   Author: Alex Brodsky
   Date: September 21, 2015
   Purpose: CSCI 1110, Assignment 4 Solution

   Description: This class specifies the SpressoBot class.
*/


/**
   This is the SpressoBot class for representing SpressoBots and their 
   behaviours on the planet DohNat.  
*/
public class SpressoBot extends TimBot { 
  /**
     This is the only constructor for this class.  This constructor
     initializes the Tibot and sets its ID and the initial amount of
     energy that it has.

     parameter: id    : ID of the TimBotA
                jolts : Initial amount of energy
   */
  public SpressoBot( int id, int jolts ) {
    super( id, jolts );
    personality = 'S';
  }


 /**
     This method is called during the Move phase of each round and
     requires the TimBot to decide whether or not to move to another
     district.  If the TimBot wishes to move, it returns, District.NORTH,
     District.EAST, District.SOUTH, or District.WEST, indicating which
     direction it wishes to move.  If it does not wish to move, it
     returns District.CURRENT.

     returns: the one of District.NORTH, District.EAST, District.SOUTH,
              District.WEST, or District.CURRENT
   */
 public int getNextMove() {
   final int SCORE_SPRESSO = 4;
   final int SCORE_BOT_SENSED = 2;
   final int SCORE_ADJACENT_BOTS = 1;
   final int CURRENT_DISTRICT = District.CURRENT;

   // Create a score for each possible move, lower scores are better
   int [] scores = new int[botsSensed.length];
   int adjacentBotsScore = 0;
   int move = CURRENT_DISTRICT;

   // If we have energy, consider moving
   if( energyLevel > 0 ) {
     // Compute scores for each possible move
     for( int i = 0; i < scores.length; i++ ) {
       scores[i] = spressoSensed[i] * SCORE_SPRESSO;
       if( ( i != CURRENT_DISTRICT ) && botsSensed[i] ) {
         scores[i] += SCORE_BOT_SENSED;
         adjacentBotsScore = SCORE_ADJACENT_BOTS;
       }
     }
     // Only the current district will have an adjacent score
     scores[CURRENT_DISTRICT] += adjacentBotsScore;

     // Find the move with the lowest score
     int min = scores[CURRENT_DISTRICT] + 1;
     for( int i = 0; i < scores.length; i++ ) {
       if( min > scores[i] ) {
         min = scores[i];
         move = i;
       }
     }

     // If the move is to another district, decrement energy level.
     if( move != CURRENT_DISTRICT ) {
       energyLevel--;
     }
   }
   return move;
 }

}
