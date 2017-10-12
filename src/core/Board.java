/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Random;
;



/**
 *
 * @author chris
 */

public class Board implements IBoard{


    private final ArrayList<String> diceData;
    public final ArrayList<String> dictionaryData;
    private final ArrayList<Die> gameDice;
    
    //add member variable of class ArrayList using class string,
    //as the data type for the ArrayList to store the game data
        
        ArrayList<String> gameData = new ArrayList();
        ArrayList<Integer> flag = new ArrayList();
    
    
    public Board(ArrayList<String> data, ArrayList<String> dict){
        diceData = data;
        
        dictionaryData = dict;
        Die die = new Die();
        gameDice = new ArrayList();
        
        
    }
    
    
    @Override
    public void populateDice() {
        Die die;
        int count = 0;
        
        for(int j=0; j< NUMBER_OF_DICE; j++){
            die = new Die(); 
            for(int i=0; i< Die.NUMBER_OF_SIDES; i++){
                die.addLetter(diceData.get(count)); 
                count++;
            }
            System.out.print("Die " + j + ": ");
            die.displayLetters(); 
            gameDice.add(die);
                              
        }
    }
    
    //implement the method shakeDice to do the following;
    // a. loop through 16 dice
    //      i. randomly select one of the 16 dice
    //      ii. call method rollDie in class Die
    //      iii. Store the returned value in the ArrayList created above
    //      for the game data
    //      iv. Be sure to use each die only once, you will have to keep track
    //      of which die was used
    @Override
    public ArrayList shakeDice() {
        Random rando = new Random();
           gameData = new ArrayList();
           flag = new ArrayList();
           
        //loop through 16 dice
        for(int i=0; i < NUMBER_OF_DICE;){
         
            //randomly select one of the 16 dice
            int n = rando.nextInt(NUMBER_OF_DICE);

            //checks if selected die has been used yet
            if(!flag.contains(n)) {
                Die die = gameDice.get(n);
                String letter = die.rollDie();
                gameData.add(letter);
                flag.add(n);
                i++;
            } 
                      
        }
              
         return gameData;
    }

    //generate a getter for the game data member variable
    public ArrayList<String> getGameData() {
        return gameData;
    }
    
    
    
    public void displayData(){
        System.out.println("Boggle board");
        for(int i = 0; i< gameData.size(); i++){
            if(i == 4 || i ==8 || i ==12){
                System.out.println();
            }
        System.out.print(gameData.get(i) + " ");
        }
        System.out.println("");
    }
   

   
    
    
    
    
       
        
          
}

    
        
    
    



