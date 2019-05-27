package core;

public class Game
{
    private char board[][];

    public Game()
    {
        board = new char[8][8];
        initComponents();
        printBoardState();
    }

    private void initComponents()
    {
        // list the pawns one the board
        for (int i = 0; i < 8; i++)
        {
            board[1][i] = 'P';
            board[6][i] = 'P';
        }

        for (int k = 2; k < 6; k++)
        {
            for (int l = 0; l < 8; l++)
            {
                board[k][l] = '-';
            }
        }

        // places the higher tier pieces
        board[7][0] = board[7][7] = board[0][0] = board[0][7] = 'R';
        board[7][1] = board[7][6] = board[0][1] = board[0][6] = 'N';
        board[7][2] = board[7][5] = board[0][2] = board[0][5] = 'B';
        board[7][3] = board[0][3] = 'Q';
        board[7][4] = board[0][4] = 'K';
    }

    private void printBoardState()
    {
        System.out.println("Current board state:");

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public char[][] getBoard()
    {
        return board;
    }
}