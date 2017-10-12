/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public interface IBoard {
    
   
    int NUMBER_OF_DICE = 16;
    int GRID = 4;
    
    
    void populateDice();
    
    
    ArrayList shakeDice();
    
}
