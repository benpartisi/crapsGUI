/* Class:   CrapsGUI
 * Author:  Ben Partida-Silva
 * Purpose: This java program is a GUI implementation of the craps game
 *          
 *
*/

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class CrapsGUI extends JFrame 
{
   private JLabel totalMoneytext;         // label for totla money
   private JTextField totalMoneyfield;     // text field for total players money
   
   private JButton placeBet;              // button to add money
   private JTextField yourBetfield;       // text field for total bet
   
   private JTextField yourPointsfield;      // text field for player points
   
   private JButton roll;                     // button rollTotalto roll the dice
   
   private JButton rollAgain;             // Button to roll again
   
   private JButton resetBet;              // button to reset bet
   
   private JTextField rollTotaltextField;          // text field for roll total
   
   private JTextField gameStatus;         // text field for game status
   
   private JLabel dice_1;                 // to display a dice
   private JLabel dice_2;                 // to display a dice
   private JLabel gifStatus;              // for future use
   
   private JPanel panelFrame;             // my panel frame
   private JPanel leftPanel;              // left panel
   private JPanel rightPanel;             // right panel
   
   private int total = 100;               // int to hold the total mone
   private int rollTotal = 0;             // int to hold the roll total
   private int myPoint = 0;               // int to hold the point
   private int bet = 0;                   // int to hold the bet
   private int die1 = 0;                  // int to hold the die 1 value
   private int die2 = 0;                  // int to hold the die 2 value
   // constatant for the file paths
   private final String FILE_PREFIX = "images/d_";
   private final String FILE_SUFFIX = ".png";
   
   // constants representing winning dice rolls
   private final int LUCKY_SEVEN = 7;
   private final int YO_LEVEN = 11;

   // constants representing losing dice rolls
   private final int SNAKE_EYES = 2;
   private final int TREY = 3;
   private final int BOX_CARS = 12;
   private final int CRAPS = 7;
   
   
   private Random randomObject = new Random();  // for random numbers
   
   // Icon for dice 1 image
   Icon dice1 = new ImageIcon( FILE_PREFIX + die1 + FILE_SUFFIX );
   // Icon for dice 2 image
   Icon dice2 = new ImageIcon( FILE_PREFIX + die2 + FILE_SUFFIX );
   // Icon for still frame roll button
   Icon animated_icon_1 = new ImageIcon( getClass().getResource( "images/1_double-dice.gif" ) );
   // Icon for animated roll button
   Icon animated_icon_2 = new ImageIcon( getClass().getResource( "images/2_double-dice.gif" ) );
   // Icon for the default dice button
   Icon redDice = new ImageIcon( getClass().getResource("images/red_dice.png"));
   // for win gig
   Icon win = new ImageIcon( getClass().getResource("images/win.gif"));
   // for future use
   Icon nope = new ImageIcon( getClass().getResource("images/nope.gif"));

   // constructor
   public CrapsGUI()
   {
      super( "CrapsGUI" );    //makes the frame

      
      panelFrame = new JPanel();    // makes a panel to use as a frame
      // the left panel
      leftPanel = new JPanel();
      // the right panel
      rightPanel = new JPanel();
      rightPanel.setBackground(Color.GREEN);
      
      
      // JLabel constructor with a string argument
      totalMoneytext = new JLabel( "Your Total Money");
      totalMoneytext.setToolTipText( "this is your total money" );
      
      
      // construct textfield for the total money field
      totalMoneyfield = new JTextField( "You have: $ 100", 7 );
      totalMoneyfield.setEditable( false ); // disable editing
      
      
      placeBet = new JButton( "Click to make a $5 bet"); // button with text
      
      resetBet = new JButton( "Reset Bet");     // reset the bet
      
      
      // construct textfield with default text 
      yourBetfield = new JTextField( "Your bet is: $ 0", 7 );
      yourBetfield.setEditable( false ); // disable editing
      
      
      roll = new JButton( "Click to roll", animated_icon_1 ); // set image
      roll.setRolloverIcon( animated_icon_2 ); // set rollover image
      roll.setEnabled( true );   // clickable to start
      
      
      // roll again button
      rollAgain = new JButton( "roll again"); 
      rollAgain.setEnabled( false );   // not clickable to start
      
      
      // construct textfield with default text 
      rollTotaltextField = new JTextField( "You rolled a: ", 7 );
      rollTotaltextField.setEditable( false ); // disable editing


      // construct textfield with default text 
      yourPointsfield = new JTextField( "Your points are: ", 5 );
      yourPointsfield.setEditable( false ); // disable editing
      
      // construct textfield with default text 
      gameStatus = new JTextField( "Place a bet to start", 5 );
      gameStatus.setEditable( false ); // disable editing
      
      dice_1 = new JLabel();        // makes a lable to hold a dice
      dice_1.setIcon(redDice);      // sets an image
      
      dice_2 = new JLabel();        // makes a label to hold a dice
      dice_2.setIcon(redDice);      // sets the image
      
      gifStatus = new JLabel();     // for gif status

      //adds components to the panels

      leftPanel.setLayout(new BoxLayout( leftPanel, BoxLayout.Y_AXIS));
      leftPanel.add(totalMoneytext) ;

      leftPanel.add(totalMoneyfield);

      leftPanel.add(placeBet);
      
      leftPanel.add(resetBet);

      leftPanel.add(yourBetfield);

      leftPanel.add(roll);
      
      leftPanel.add(rollAgain);

      leftPanel.add(rollTotaltextField);
      
      leftPanel.add(yourPointsfield);
      
      leftPanel.add(gameStatus);
      
      
      rightPanel.add(dice_1);
      rightPanel.add(dice_2);
      rightPanel.add(gifStatus);     //for gif status
      
      //sets the layout for the panel frame
      panelFrame.setLayout( new BorderLayout());
      // adds the panels to the JFrame
      panelFrame.add(leftPanel, BorderLayout.WEST);   // adds left panel 
      panelFrame.add(rightPanel, BorderLayout.CENTER);   // ads the right panel
      
      
      super.add(panelFrame);     // ads the panel frame to the Jframe
      
      
      // bet button 
      betButtonHandler betHandler = new betButtonHandler();
      placeBet.addActionListener( betHandler );
      // reset bet button
      resetButtonHandler resetHandler = new resetButtonHandler();
      resetBet.addActionListener( resetHandler );
      // roll button
      rollButtonHandler rollHandler = new rollButtonHandler();
      roll.addActionListener( rollHandler );
      // roll again button
      rollAgainButtonHandler rollagainHandler = new rollAgainButtonHandler();
      rollAgain.addActionListener( rollagainHandler );
      
   }
   
   // action listener for the bet button
   private class betButtonHandler implements ActionListener 
   {
      public void actionPerformed( ActionEvent event )
      {
         if ( total > 0 )
         {
         bet = bet +5;
         
         total = total - 5;
         
         totalMoneyfield.setText("You have: $ " + total);
         
         yourBetfield.setText("Your bet is: $ " +  bet);
         }
         else
         {
            gameStatus.setForeground(Color.RED);
            gameStatus.setText( "YOU ARE OUT OF MONEY" );
         }

      } // end method actionPerformed
   }
   
   // action listener for the reset bet button
   private class resetButtonHandler implements ActionListener 
   {
      public void actionPerformed( ActionEvent event )
      {
         bet = 0;
         
         yourBetfield.setText("Your bet is: $ " +  bet);

      } // end method actionPerformed
   }
   
   // action listener for the roll bitton
   private class rollButtonHandler implements ActionListener 
   {
      public void actionPerformed( ActionEvent event )
      {  
         resetBet.setEnabled( false );
         if( bet > 0 && checkBank() )
         {

            gifStatus.setIcon( null );
            rightPanel.repaint();

            int sumOfDice = rollDice(); // roll dice

            // check results of the first dice roll
            switch ( sumOfDice )
            {
               // win on first roll
               case LUCKY_SEVEN:
               case YO_LEVEN:
                  gameStatus.setForeground(Color.RED);
                  gameStatus.setText( "YOU WIN!!!" );
                  total = total + bet;
                  totalMoneyfield.setText("You have: $ " + total);
                  dice_1.setIcon( dice_image( die1 ));
                  dice_2.setIcon( dice_image( die2 ));
                  gifStatus.setIcon( win );
                  resetBet.setEnabled( true );
                  
                  break;

               // lose on first roll
               case SNAKE_EYES:
               case TREY:
               case BOX_CARS:
                  gameStatus.setForeground(Color.RED);
                  gameStatus.setText( "SORRY YOU LOSE." );
                  
                  resetBet.setEnabled( true );
                  
                  total = total - bet;
                  
                  break;

               // remember point in instance variable
               default:

                  // set the point and output result
                  myPoint = sumOfDice;

                  rollTotaltextField.setText("You rolled a: " + myPoint);
                  yourPointsfield.setText("Your points are: " + myPoint);
                  gameStatus.setForeground(Color.BLACK);
                  gameStatus.setText( "Roll again!" );



                  // show the dice images
                  dice_1.setIcon( dice_image( die1 ));
                  dice_2.setIcon( dice_image( die2 ));

                  rightPanel.repaint();

                  // change the state of the JButtons
                  roll.setEnabled( false );
                  rollAgain.setEnabled( true );
                  
                  
                  
                  //placeBet.setEnabled(false);    // turn off the place bet
                  //resetBet.setEnabled(false);    // trun off the reset bet
                  

            } // end switch statement
         } // end of if
         else
         {
            rightPanel.repaint();
            gameStatus.setForeground(Color.RED);
            gameStatus.setText( "PLACE A BET!!" );
            roll.setEnabled( true );
            rollAgain.setEnabled( false );
            
         } // end of else

      } // end method actionPerformed
   }
   
   // action listener for the roll again button
   private class rollAgainButtonHandler implements ActionListener 
   {
      public void actionPerformed( ActionEvent event )
      {
         resetBet.setEnabled( false );
         if( checkBank() )
         {
            rightPanel.repaint();
            int sumOfDice = rollDice();  // roll dice

            dice_1.setIcon( dice_image( die1 ));
            dice_2.setIcon( dice_image( die2 ));

            rollTotaltextField.setText("You rolled a: " + sumOfDice);
            yourPointsfield.setText("Your points are: " + myPoint);

            // determine outcome of roll, player matches point
            if ( sumOfDice == myPoint )
            {  
               gameStatus.setForeground(Color.RED);
               gameStatus.setText( "YOU WIN!!!" );
               total = total + bet;
               totalMoneyfield.setText("You have: $ " + total);
               rollAgain.setEnabled( false );
               roll.setEnabled( true );
               gifStatus.setIcon( win );
               resetBet.setEnabled( true );

            } // end of if
            // determine outcome of roll, player loses
            else if ( sumOfDice == CRAPS )
            {
               gameStatus.setForeground(Color.RED);
               gameStatus.setText( "SORRY YOU LOSE." );
               total = total - bet;
               totalMoneyfield.setText("You have: $ " + total);
               rollAgain.setEnabled( false );
               roll.setEnabled( true );
               resetBet.setEnabled( true );

            } // end of else
         
         } //end of if
         
         else
         {
            rightPanel.repaint();
            gameStatus.setForeground(Color.RED);
            gameStatus.setText( "OUT OF MONEY!!" );
            roll.setEnabled( true );
            rollAgain.setEnabled( false );
            
         } // end of else

      } // end method actionPerformed
   } // end of action listener
   
   // roll method
   private int rollDice()
   {
      // generate random die values
      die1 = 1 + randomObject.nextInt( 6 );
      die2 = 1 + randomObject.nextInt( 6 );
      
      //make a switch for the dice
      
      // display the dice images
      dice_1.setIcon( dice1 );
      dice_2.setIcon( dice2 );
      
      return die1 + die2; // return sum of dice values
      
   } // end method rollDice
   
   // method to pick the image to use on dice
   private Icon dice_image( int numb )
   {
   
      Icon d_1 = new ImageIcon( getClass().getResource("images/d_1.png"));
      Icon d_2 = new ImageIcon( getClass().getResource("images/d_2.png"));
      Icon d_3 = new ImageIcon( getClass().getResource("images/d_3.png"));
      Icon d_4 = new ImageIcon( getClass().getResource("images/d_4.png"));
      Icon d_5 = new ImageIcon( getClass().getResource("images/d_5.png"));
      Icon d_6 = new ImageIcon( getClass().getResource("images/d_6.png"));
         
      switch ( numb )
      {

         case 1:
               return d_1;

         case 2:
               return  d_2;

         case 3:
               return d_3;

         case 4:
               return d_4;

         case 5:
               return d_5;
         
         default:
            return d_6;
               
      }

   }
   
   // method to check if player has money
   private boolean checkBank()
   {
      boolean bank;
      
      if ( total > 0 )
      {
         bank = true;
         return bank;
      }
      else
      {
         bank = false;
         return bank;
      }
   }
   

   // main method
   public static void main( String[] args )
   { 
      CrapsGUI craps = new CrapsGUI(); // create CrapsGUI
      craps.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      craps.setSize( 600, 400 ); // set frame size
      craps.setVisible( true ); // display frame
   } // end main

   
   
}