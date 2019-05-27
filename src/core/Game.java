package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    public ArrayList<Point> highlightMoves(JButton[][] board, Piece selectedPiece)
    {
        ArrayList<Point> potentialMoveList = new ArrayList<>();

        int currentPlayer = selectedPiece.getPlayerID();
        int curX, curY;

        switch (selectedPiece.getPieceRank())
        {
            case 'P':

                for (int i = 0; i < Constants.PAWN_HORIZONTAL.length; i++)
                {
                    curX = selectedPiece.getxLocation() + Constants.PAWN_HORIZONTAL[i];
                    curY = selectedPiece.getyLocation() + Constants.PAWN_VERTICAL[i];

                    if (inBounds(curX, curY) && spotCheck(currentPlayer, board[curY][curX]))
                    {
                        potentialMoveList.add(new Point(curX, curY));
                    }
                }
                break;

            case 'K':
                break;
            case 'N':
                break;
            case 'Q':
                break;
            case 'R':
                break;
            case 'B':
                break;
        }
        return potentialMoveList;
    }

    private boolean inBounds(int x, int y)
    {
        return x > -1 && x < 7 && y > -1 && y < 7;
    }

    private boolean spotCheck(int currentPlayer, JButton potentialMoves)
    {
        return containsEnemyPiece(currentPlayer, potentialMoves) || containsNoPiece(potentialMoves);
    }

    private boolean containsEnemyPiece(int currentPlayer, JButton potentialMove)
    {
        Piece checkPiece = (Piece)potentialMove.getClientProperty("piece");

        return checkPiece.getPlayerID() != currentPlayer;
    }

    public boolean containsNoPiece(JButton potentialMove)
    {
        Piece checkPiece = (Piece)potentialMove.getClientProperty("piece");

        return checkPiece.getPlayerID() == Constants.EMPTY_SPOT;
    }

}