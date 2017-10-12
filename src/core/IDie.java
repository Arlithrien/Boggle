/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author chris
 */
public interface IDie {
    
    
    public static final int NUMBER_OF_SIDES = 6;
    
    public void displayLetters();
    
    public void addLetter(String letter);
    
    public String rollDie();
}
