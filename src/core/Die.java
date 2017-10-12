/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author chris
 */
public class Die implements IDie
{
    private ArrayList dieSides = new ArrayList();
 
    

    @Override
    public void addLetter(String letter) {
        
        
            dieSides.add(letter);
        
    }
    
        @Override
    public void displayLetters() {
        //for(Object letter : dieSides.get(letter))
        for(int i=0;i<NUMBER_OF_SIDES;i++)
            System.out.print(dieSides.get(i) + " ");
        System.out.println(" ");
            
    }
    
    /*
    Implement method rollDie to do the following;
          a. Randomly select one of the six letters of the die that will be used as the game data
          b. Return that value 
    */

    
    @Override
    public String rollDie() {
        Random rando = new Random();
        int roll = 0 + rando.nextInt(5);
        return (String) dieSides.get(roll);
    }
    
}
