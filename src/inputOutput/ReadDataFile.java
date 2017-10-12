/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */


public class ReadDataFile implements IReadDataFile{
    
    public ArrayList<String> getFileData() {
        return fileData;
    }
       
     private Scanner buffer;
     private String fileName;
     private ArrayList<String> fileData;

           
    public ReadDataFile(String dataFileName)
    {
        fileName = dataFileName;
        fileData = new ArrayList();
                
    }
    
    
    
    @Override
    public void populateData()
    {
        try {
            URL url = getClass().getResource(fileName);
            File dataFileName = new File(url.toURI());
            buffer = new Scanner(dataFileName);
            
             
            while(buffer.hasNext())
                fileData.add(buffer.next());  
    } catch (FileNotFoundException | URISyntaxException ex) {
                Logger.getLogger(ReadDataFile.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        }

}