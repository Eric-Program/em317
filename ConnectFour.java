



import java.util.Scanner;



public class ConnectFour {
    public static void printBoard(char[][] array)
    {

        //starts printing at last row, first row is the last row printed.
        for (int i =array.length-1 ; i>= 0;i--)
        {
            for (int j =0; j< array[i].length; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
    public static void initializeBoard(char[][] array)
    {
        for (int i =0; i<array.length;i++)
        {
            for (int j =0; j< array[i].length; j++) {
                array[i][j] = '-';
            }
        }
    }
    public static int insertChip(char[][] array, int col, char chipType)
    {
        int rowFound;
        rowFound = -10;

        for (int i =0; i<array.length; ++i) {
            //start at [0][col] if '-' then go up to [1][col] up to [array.length-1][col]
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                rowFound = i ;

                //rowFound updating with i is not working BUT piece is placed
                break;
            }
        }
        return rowFound ;
    //change to row that chip is placed in
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        //check if 4 up or down or left or right are same chipType and that it fits the boundary
        int aRows = array.length;
        //aRows is number of rows
        int aLength = array[row].length;
        int match = 1;
        boolean check = false;
        if (aRows-row > 1) {
            for (int i = row+1; i < aRows; ++i) {
                // iterating up until limit of board.
                if (array[i][col] != chipType) {
                    break;

                } else {
                    match++;
                }
            }
        }
        if (row!= 0) {
            for (int i = row-1; i >= 0; --i) {
                // iterating down until limit of board.
                if (array[i][col] != chipType) {
                    break;

                } else {
                    match++;

                }
            }
        }
        if (match < 4) {
            match = 1;
            //reset match to use for checking left/right win condition
        } else {
            check = true;

        }
        if (aLength-col >1) {
            for (int j = col+1; j < aLength; ++j) {
                if (array[row][j] != chipType) {
                    break;
                } else {
                    match++;

                }
            }
        }
        if (col !=0) {
            for (int j = col-1; j >= 0; --j) {
                if (array[row][j] != chipType) {
                    break;
                } else {
                    match++;

                }
            }
        }


        if (match>=4) {
            check = true;
        }


        return check;
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        char chipType = 'a';

        System.out.println("What would you like the height of the board to be?");
        int rInput = scnr.nextInt();
        System.out.println("What would you like the length of the board to be?");
        int lInput = scnr.nextInt();
        //r input is rows, l input is columns
        char[][] cBoard= new char[rInput][lInput];
         //cBoard for Connect board
        //fill board with '-'
        initializeBoard(cBoard);
        printBoard(cBoard);
        boolean gameOver = false;
        System.out.println("\nPlayer 1: x");
        System.out.println("Player 2: o\n");

        while (!gameOver)
        {
            chipType = 'x';
            System.out.println("Player 1: Which column would you like to choose?") ;
            int oneC = scnr.nextInt();
           //onceC is player one choice, column entered is modified to fit index
            int oneR =insertChip(cBoard, oneC, chipType);
            printBoard(cBoard);
            //System.out.println(oneR);
            if (oneR == -10) {
                gameOver = true;
                chipType = 'l';
                break;
            }

            //System.out.println("row:"+oneR);

            gameOver =checkIfWinner( cBoard, oneC, oneR, chipType);
            if (gameOver) {
                continue;
            }


            chipType = 'o';
            System.out.println("Player 2: Which column would you like to choose?");
            int twoC = scnr.nextInt();
           int twoR= insertChip(cBoard, twoC, chipType);
           printBoard(cBoard);
            if (twoR == -10) {
                chipType = 'l';
                gameOver = true;
                break;
            }
            //System.out.println("row:"+oneR);
            //System.out.println("row:"+twoR);
           gameOver = checkIfWinner(cBoard, twoC, twoR, chipType);


        }
        //Conditions to determine winner, last time chipType updated used as counter
        if ( chipType == 'x') {
            System.out.println("Player 1 won the game!");
        }
        else if (chipType == 'o') {
            System.out.println("Player 2 won the game!");

        }
        else if (chipType == 'l'){
            System.out.println("Draw. Nobody wins. ");
        }



    }
}
