/*
 *Author: Prajwal Dhungana
 * Date: Feb 26, 2021
 * This program takes user input in GUI form and
 * demonstrates the generation of random battleship location
 * on a board in GUI
 */

//importing necessary libraries
import java.util.*;
import javax.swing.*;

public class BattleshipBoardGenerator_PrajwalDhungana {

    //declaring necessary variables
    public static int rows = 0;
    public static int cols = 0;
    public static int shipLength = 0;

    //declaring a random instance class
    private static Random rand = new Random();

    //method to get userInput
    public static void getUserInput()
    {
        ImageIcon userInputIcon = new ImageIcon("userInput.png");

        String getRows = String.valueOf(JOptionPane.showInputDialog(null, "<html><b>"+"Enter the number of rows for your battleboard : "+"</b></html>",
                "User Input \t\t\t Prajwal Dhungana",1,userInputIcon,null,10));
        rows = Integer.parseInt(getRows);

        String getCols = String.valueOf(JOptionPane.showInputDialog(null, "<html><b>"+"Enter the number of columns for your battleboard : "+"</b></html>",
                "User Input \t\t\t Prajwal Dhungana", 1,userInputIcon,null,10));
        cols = Integer.parseInt(getCols);

        String lengthofShip = String.valueOf(JOptionPane.showInputDialog(null, "<html><b>"+"Enter the length of your ship : " + "</b></html>",
                "User Input \t\t\t Prajwal Dhungana",1,userInputIcon,null,5));
        shipLength = Integer.parseInt(lengthofShip);
    }

    //Method for displaying the general game information to the user
    public static String gameInfoScreen()
    {
        //infoToUser variable stores the message to display to the user when he first starts the program.
        String infoToUser = "This program demonstrates the " + "\n" +
                "random initialization of a " + "\n" +
                "battle ship board game with row, column, and " + "\n" +
                "ship length as user input. " + "\n\n" +
                "All the user does is clicking the buttons " + "\n" +
                "to see either board or " + "\n" +
                "exit the program ";

        return infoToUser;
    }

    //Method for creating and plotting on the gameboard
    public static char gameBoard()
    {
        String newGameboard = "<html><b>New Gameboard</b></html>\n\n";

        String[] selection ={"Show Another Board", "StopAlready!"};

        String [][] dashArray = new String[rows][cols];

        //declaring some necessary variables
        //is horizontalOrVertical generates 2 values, 0 or 1 randomly
        //battleShipCoordinateRow generates a random value from where we start drawing the battleship in a row
        //battleShipCoordinateCol generates a random value from where we start drawing the battleship in a column
        int horizontalOrVertical = rand.nextInt(2);
        int battleShipCoordinateRowX;
        int battleShipCoordinateRowY;
        int battleShipCoordinateColX;
        int battleShipCoordinateColY;

        //plotting in row form
        if (horizontalOrVertical == 1)
        {
            //battleShipCoordinateRowX is for getting the default column value from where a row needs to be plotted
            battleShipCoordinateRowX = rand.nextInt(cols);
            //battleShipCoordinateRowY is for getting the starting row in the column for plotting
            battleShipCoordinateRowY = rand.nextInt(rows-shipLength);

            //iterating for row
            for (int i = 0; i < rows; i++)
            {
                //iterating for column
                for (int j = 0; j < cols; j++)
                {
                    if (j == battleShipCoordinateRowX  && i >= battleShipCoordinateRowY && i < (battleShipCoordinateRowY + shipLength) && battleShipCoordinateRowY < rows+shipLength)
                    {
                        dashArray[i][j] = "X";
                    }

                    else
                    {
                        dashArray[i][j] = "-";
                    }
                }
            }
        }

        //plotting in column form
        else
        {
            //battleShipCoordinateColX is for getting the starting column in the row for plotting
            battleShipCoordinateColX = rand.nextInt(cols-shipLength);
            //battleShipCoordinateY is for getting the default row where a column needs to be plotted
            battleShipCoordinateColY = rand.nextInt(rows);

            //iterating for row
            for (int i = 0; i < rows; i++)
            {
                //iterating for column
                for (int j = 0; j < cols; j++)
                {
                    if(i == battleShipCoordinateColY && j >= battleShipCoordinateColX && j < (battleShipCoordinateColX + shipLength) && battleShipCoordinateColX < cols + shipLength)
                    {
                        dashArray[i][j] = "X";
                    }

                    else
                    {
                        dashArray[i][j] = "-";
                    }
                }
            }
        }

        //dashOutput is a string variable created so that it holds the output from the array
        String dashOutput = "";

        //iterating for row
        for (int i=0; i<rows;i++)
        {
            //iterating for column
            for (int j=0; j<cols; j++)
            {
                dashOutput += dashArray[i][j];
                dashOutput += "\t\t\t\t\t\t\t";
            }
            dashOutput += "\n\n";
        }

        //printing the gameboard in GUI
        ImageIcon battleshipTwoIcon = new ImageIcon("battleShip2.png");

        int choice = JOptionPane.showOptionDialog(null, newGameboard + dashOutput, "New Game Board"+"\t\t\tPrajwal Dhungana",
                1,1,battleshipTwoIcon, selection, selection[1]);

        switch (choice)
        {
            case 0:
                return 'r';

            case 1:
                return 'q';
        }

        return '?';
    }

    //begin main method
    public static void main(String[] args)
    {

        //calling gameInfoScreen method
        String message = gameInfoScreen();

        ImageIcon battleShipOneIcon = new ImageIcon("battleShip1.png");

        JOptionPane.showOptionDialog(null,message, "Battleship Board Game"+"\t\tPrajwal Dhungana",
                1,1,battleShipOneIcon, new String[] {"Ok"}, "Ok");

        //calling the user input function
        getUserInput();

        //while infinite loop
        while (true)
        {
            //calling gameboard method
            char userChoice = gameBoard();

            if (userChoice == 'q')
            {
                //printing the goodbye message in GUI

                ImageIcon goodByeIcon = new ImageIcon("goodBye.png");
                
                JOptionPane.showOptionDialog(null, "<html><b>" + "The program is closing!" + "</b></html>" + "\n" + "<html><b>" + "BYE" + "</b></html>",
                        "Good bye" + "\tPrajwal Dhungana", 1,1,goodByeIcon, new String[] {"Ok"}, "Ok");

                //force quit the program
                System.exit(0);
            }
        }
    }
    //end main method
}
