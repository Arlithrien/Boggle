/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Chris Fleming ch980803
package boggle;

//import for our call to the JOptionPane swing function
import core.Board;
import inputOutput.ReadDataFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import userInterface.BoggleUi;

/**
 *
 * @author chris
 */
public class Boggle {
    public static ArrayList<String> diceArray;
    private static String dataFileName = "/data/" + "BoggleData.txt";
    
    public static ArrayList<String> dictArray;
    private static String dataFileDict = "/data/" + "Dictionary.txt";
    
 
    
    public static void main(String[] args) {
        //prints message in quotations to the output
        System.out.println("Welcome to Boggle!");
        
        //creates popup with the message in quotations
        JOptionPane.showMessageDialog(null,"Let's Play Boggle!");
        
        
        
        ReadDataFile data = new ReadDataFile(dataFileName);
        data.populateData();
        
        ReadDataFile dict = new ReadDataFile(dataFileDict);
        dict.populateData();
        
        Board board = new Board(data.getFileData(), dict.getFileData());
        board.populateDice();
        
        
        
    
        System.out.println("\nThere are " + dict.getFileData().size() + " elements in the dictionary.\n");
        
        //Set the ArrayList member variable that stores the Boggle data equal
        //to method call shakeDice in class Board
        diceArray = board.shakeDice();
        board.displayData();
        
        
        //instantiates an instant of class BoggleUi
        BoggleUi bogui = new BoggleUi(board);
        
        
    }
    
   
    
}
