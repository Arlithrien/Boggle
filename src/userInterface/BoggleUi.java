/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Board;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultStyledDocument;


/**
 *
 * @author chris
 */
public final class BoggleUi {
    //declare member variables
   private JFrame jFrame;
   private JMenuBar jMenuBar;
   private JMenu boggle;
   private JMenuItem newGame, exit;
   private JButton[][] jButton;
   private JButton jButton2, jButton3;
   private JLabel jLabel;
   private JLabel jLabel2, jLabel3;
   private JPanel jPanel, jPanel2, jPanel3;
   private JScrollPane jScrollPane;
   private JTextPane jTextPane;
   private JTextField jTextField;
   private Board board;
//   public ResetGameListener reset;
   private int counter;
   //timer
   private Timer timer;
   private int minutes = 3;
   private int seconds = 0;
   private int score = 0;
   public static ArrayList<String> wordsList = new ArrayList();
   private static String currentWord;
    
   
    
    
    
    
    //custom consructor that receieves parameter type Board class
    public BoggleUi(Board ui){
        
        //sets member variable Board to parameter passed in
        board = ui;
        
        setupTimer();
        
        
        //call method initcomponents
        initComponents();
    }
    
    //lays out the user interface
    public void initComponents(){
        //initializes jframe size, close operation, and layout (BorderLayout)
        jFrame = new JFrame("Boggle");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(800,600));
        jFrame.setMinimumSize(new Dimension(800,600));
        jFrame.setMaximumSize(new Dimension(800,600));
        jFrame.setLayout(new BorderLayout());
        
        //calls method to set up JMenuBar
        createMenuBar();
        
        //////////////////////////////////////////////////////////////////////
       
        //JPanel to hold the current word created by user (FLowLayout)
        jPanel = new JPanel();
        jFrame.add(jPanel, BorderLayout.SOUTH);
        TitledBorder jPanelt = new TitledBorder("Current Word");
        TitledBorder jPanelt2 = new TitledBorder("Score");
        jPanel.setBorder(jPanelt);
        jLabel = new JLabel("");
        jButton2 = new JButton("Submit Word");
        jButton2.setPreferredSize(new Dimension(250, 75));
        jLabel2 = new JLabel("" + score);
        
        
        jPanel.add(jLabel, FlowLayout.LEFT);
        jLabel.setPreferredSize(new Dimension(350, 75));
        jLabel2.setPreferredSize(new Dimension(150, 75));
        jLabel.setBorder(jPanelt);
        jLabel2.setBorder(jPanelt2);
        
        jPanel.add(jButton2, FlowLayout.CENTER);
        jPanel.add(jLabel2, FlowLayout.RIGHT);
        
        //////////////////////////////////////////////////////////////////////
     
       //JPanel that holds JButtons of dice (Gridlayout)
       jPanel2 = new JPanel();
       TitledBorder gridt = new TitledBorder("Boggle Board");
       jPanel2.setBorder(gridt);
       jFrame.add(jPanel2, BorderLayout.WEST);
       
       jPanel2.setLayout(new GridLayout(4,4));
       jButton = new JButton[4][4];
       counter = 0;
       for(int i = 0; i<4; i++){
            for(int j =0; j<4; j++){
                jButton[i][j] = new JButton();
                jButton[i][j].setPreferredSize(new Dimension(125, 100));
                jButton[i][j].setText(board.getGameData().get(counter));
                jPanel2.add(jButton[i][j]);
                counter++;
                
            }
       }
     /////////////////////////////////////////////////////////////////
      //JPanel that holds JTextPane, JScrollPane, JLabel & Button (BoxLayout)
        jPanel3 = new JPanel();
        TitledBorder jPanel3t = new TitledBorder("Enter Words Found");
        jFrame.add(jPanel3, BorderLayout.EAST);
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
        jPanel3.setPreferredSize(new Dimension(280,600));
        
        //sets up JTextPane
        jTextPane = new JTextPane();
        
        
        
        
       
        jTextPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        
        //sets up JScrollPane for text pane
        jScrollPane = new JScrollPane(jTextPane);
        jScrollPane.setMaximumSize(new Dimension(280, 270));
        TitledBorder jTextPanet = new TitledBorder("Enter Words Found");
        jPanel3.setBorder(jTextPanet);
        
                
        
        //the button to shake the dice
        jButton3 = new JButton("Shake Dice");
        jButton3.setMaximumSize(new Dimension(280, 200));
        jButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //inner class for action listener of "shake dice" button
        jButton3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                board.shakeDice();
                jLabel.setText("test");
                jLabel2.setText("0");
               
                timer.stop();
                jLabel3.setText("3:00");
                minutes = 3;
                seconds = 0;
                timer = new Timer(1000, new timerListener());
                timer.start();         
                counter = 0;
                for(int i = 0; i<4; i++){
                    for(int j =0; j<4; j++){
                       
                        jButton[i][j].setText("");
                        jButton[i][j].setText(board.getGameData().get(counter));
                        counter++;
            }
       }
            jFrame.revalidate();
            jFrame.repaint();
            }
        
    });
           
      //adds components to jframe
        jPanel3.add(jScrollPane);
        
        jTextPane.setText("");
        jPanel3.add(jLabel3);
        jPanel3.add(jButton3);
        
     /////////////////////////////////////////////////////////////////////           
        
      //sets visibility of jframe 
     
      jFrame.pack();
      jFrame.setVisible(true);
     
     
        jButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                ////////
                
                String temp = jLabel.getText();
                if(!board.dictionaryData.contains(jLabel.getText()) ||
                wordsList.contains(jLabel.getText())){
                    JOptionPane.showMessageDialog(null, "Word not valid");
                    jLabel.setText("");
                }
                else{
                    jTextPane.setText(jTextPane.getText() + jLabel.getText() + "\n");
                    score++;
                    jLabel2.setText( "" + score);
                   // System.out.println(jTextPane.getText());
                    wordsList.add(jLabel.getText());
                    jLabel.setText("quiz");
                    
                }
            }
        });
          
       /*
                int i, j;
                //jButton = new JButton[4][4];
                //jButton.addActionListener(new ActionListener(){
                public class buttonGrid implements ActionListener{
                    @Override
                    public void actionPerformed(ActionEvent ae){
                     //   jLabel.setText(jLabel.getText() + jButton[i][j].getText());
                     jButton[i][j].setEnabled(false);
                     jButton[i][j].setText("");
                    }
                
                }
           // }*/
              //inner class action listener for newGame menu item
        newGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                board.shakeDice();
                jLabel.setText("test");
                jLabel2.setText("" + score);
                timer.stop();
                jLabel3.setText("3:00");
                minutes = 3;
                seconds = 0;
                timer = new Timer(1000, new timerListener());
                timer.start();
                
                
                counter = 0;
                for(int i = 0; i<4; i++){
                    for(int j =0; j<4; j++){
                        jButton[i][j].setText("");
                        jButton[i][j].setText(board.getGameData().get(counter));
       
                        counter++;
                
            }
       }
                jFrame.revalidate();
                jFrame.repaint();
            }
        });
        
        
        for(int i =0;i<4;i++){
            for(int j=0;j<4;j++){
                
                jButton[i][j].addActionListener(new buttonListener());
                
                        
                
                     //jButton[i][j].setText("");
                    }
                } 
          
        
        
        
        }
    
    
    
    //create menubar, menu, menuitems etc
    public JMenuBar createMenuBar(){
    
        //adds jmenubar and boggle menu components
        jMenuBar = new JMenuBar();
        jFrame.add(jMenuBar, BorderLayout.PAGE_START);
        boggle = new JMenu("Boggle");
        boggle.setMnemonic('G');
        
        //adds new game and exit to menu
        jMenuBar.add(boggle);
        newGame = new JMenuItem("New Game");
        
        newGame.setMnemonic('N');
        boggle.add(newGame);
        exit = new JMenuItem("Exit");
        
        //inner class action listener for exit menu item
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                int response = JOptionPane.showConfirmDialog(null, "Exit Boggle?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            
            if(response == JOptionPane.YES_OPTION)
                System.exit(0);
                
            
            }
        
    });
      //  exit.setActionCommand();
        
        exit.setMnemonic('E');
        boggle.add(exit);

         
     return jMenuBar;    
        
    }
    

    
    private void setupTimer(){
        
        //sets up timer
        jLabel3 = new JLabel("3:00", SwingConstants.CENTER);
        jLabel3.setFont(new Font("serif", Font.PLAIN, 50));
        jLabel3.setMaximumSize(new Dimension(280,200));
        jLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        TitledBorder jLabel3t = new TitledBorder("Time Left");
        jLabel3.setBorder(jLabel3t);

        
        timer = new Timer(1000, new timerListener());
        timer.start();
        
    }
    
    
    private class timerListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            if(seconds ==0 && minutes ==0){
                timer.stop();
            }
            else{
                if(seconds ==0){
                    seconds = 59;
                    minutes--;
                }
                else{
                    seconds--;
                }
            }
            if(seconds <10){
                String strSeconds = "0" +
                        String.valueOf(seconds);
                        jLabel3.setText(String.valueOf(minutes) + ":" +
                                strSeconds);
            }
            else{
                jLabel3.setText(String.valueOf(minutes) + ":" + 
                        String.valueOf(seconds));
            }
        }
    }
    private class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JButton buttonClicked = (JButton)e.getSource();
            
            jLabel.setText(jLabel.getText() + buttonClicked.getText());
            buttonClicked.setEnabled(false);
            for(int i =0;i<4;i++){
                for(int j=0;j<4;j++){
                    jButton[i][j].setEnabled(false);
            
                }
            }
            for(int i =0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(jButton[i][j] == buttonClicked){
                        for(int k =-1;k<2;k++){
                        jButton[i+k][j+k].setEnabled(true);
                        jButton[i+k][j].setEnabled(true);
                        jButton[i][j+k].setEnabled(true);
                        jButton[i-k][j+k].setEnabled(true);
                        
                        buttonClicked.setEnabled(false);
                        }
                    }
                        
                }
            }
            
            
            ///buttonClicked[0][0].setEnabled(false);
            
        }
    }
   
}   
    
    

     
    

//}