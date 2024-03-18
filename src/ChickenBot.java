/**
   File: ChickenBot.java
   Author: Alex Brodsky
   Date: September 21, 2015
   Purpose: CSCI 1110, Assignment 4 Solution

   Description: This class specifies the ChickenBot class.
*/


/**
   This is the ChickenBot class for representing ChickenBots and their 
   behaviours on the planet DohNat.  
*/
public class ChickenBot extends TimBot {
  /** 
     This is the only constructor for this class.  This constructor 
     initializes the Tibot and sets its ID and the initial amount of
     energy that it has.

     parameter: id    : ID of the TimBot
                jolts : Initial amount of energy
   */
  public ChickenBot( int id, int jolts ) {
    super( id, jolts );
    personality = 'C';
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
    final int SCORE_BOT_SENSED = 2000;
    final int SCORE_ADJACENT_BOTS = 1000;
    final int CURRENT_DISTRICT = District.CURRENT;

    // Allocate a scores array to score moves. Lower score is better
    int [] scores = new int[botsSensed.length];
    int adjacentBotsScore = 0;
    int move = CURRENT_DISTRICT;

    // If we have energy, consider moving
    if( energyLevel > 0 ) {
      // Loop through all possibilities and compute the scores.
      for( int i = 0; i < scores.length; i++ ) {
        scores[i] = spressoSensed[i];
        if( ( i != CURRENT_DISTRICT ) && botsSensed[i] ) {
          scores[i] += SCORE_BOT_SENSED;
          adjacentBotsScore = SCORE_ADJACENT_BOTS;
        }
      }
      // Only the current district can have an adjacency score.
      scores[CURRENT_DISTRICT] += adjacentBotsScore;

      // Choose the move with the minimum score
      int min = scores[CURRENT_DISTRICT] + 1;
      for( int i = 0; i < scores.length; i++ ) {
        if( min > scores[i] ) {
          min = scores[i];
          move = i;
        }
      }

      // Decrement energy level if we are moving.
      if( move != CURRENT_DISTRICT ) {
        energyLevel--;
      }
    }
    return move;
  }
}
