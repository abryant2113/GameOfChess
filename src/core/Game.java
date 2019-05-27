package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game
{
    private ArrayList<Point> potentialMoveList;
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

    public void printBoardState()
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

    public void highlightMoves(JButton[][] board, Piece selectedPiece)
    {
        setPotentialMoveList(new ArrayList<>());

        int curX, curY;

        switch (selectedPiece.getPieceRank())
        {
            case 'P':

                for (int i = 0; i < Constants.PAWN_HORIZONTAL.length; i++)
                {
                    curX = selectedPiece.getxLocation() + Constants.PAWN_HORIZONTAL[i];
                    curY = selectedPiece.getyLocation() + Constants.PAWN_VERTICAL[i];

                    if (inBounds(curX, curY) && spotCheck(selectedPiece, board[curY][curX]))
                    {
                        getPotentialMoveList().add(new Point(curX, curY));
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
    }

    private boolean inBounds(int x, int y)
    {
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

    public void updateState(Piece selectedPiece, Piece destinationPiece)
    {
        board[selectedPiece.getyLocation()][selectedPiece.getxLocation()] = destinationPiece.getPieceRank();
        board[destinationPiece.getyLocation()][destinationPiece.getxLocation()] = selectedPiece.getPieceRank();
    }
    private boolean validPawnMove(Piece selectedPiece, JButton potentialMove)
    {
        Piece destinationPiece = (Piece) potentialMove.getClientProperty("piece");

        if (destinationPiece.getyLocation() == selectedPiece.getyLocation() - 2 && board[destinationPiece.getyLocation()][destinationPiece.getxLocation()] == '-'
        && board[destinationPiece.getyLocation() + 1][destinationPiece.getxLocation()] == '-' && selectedPiece.getMoveCount() == 0)
        {
            return true;
        }

        if (containsNoPiece(potentialMove) && destinationPiece.getyLocation() == selectedPiece.getyLocation() - 1 &&
                destinationPiece.getxLocation() == selectedPiece.getxLocation())
        {
            return true;
        }
        else if(containsEnemyPiece(selectedPiece.getPlayerID(), potentialMove) &&
                destinationPiece.getyLocation() == selectedPiece.getyLocation() - 1 &&
                ((selectedPiece.getxLocation() + 1 == destinationPiece.getxLocation()) ||
                        selectedPiece.getxLocation() - 1 == destinationPiece.getxLocation()))
        {
            return true;
        }
        return false;
    }

    private boolean spotCheck(Piece selectedPiece, JButton potentialMove)
    {
        int currentPlayer = selectedPiece.getPlayerID();

        if (selectedPiece.getPieceRank() == 'P')
        {
            return validPawnMove(selectedPiece, potentialMove);
        }
        return containsEnemyPiece(currentPlayer, potentialMove) || containsNoPiece(potentialMove);
    }

    private boolean containsEnemyPiece(int currentPlayer, JButton potentialMove)
    {
        Piece checkPiece = (Piece)potentialMove.getClientProperty("piece");
        return (checkPiece.getPlayerID() != currentPlayer) && (checkPiece.getPlayerID() != Constants.EMPTY_SPOT);
    }

    public boolean containsNoPiece(JButton potentialMove)
    {
        Piece checkPiece = (Piece)potentialMove.getClientProperty("piece");
        return checkPiece.getPlayerID() == Constants.EMPTY_SPOT;
    }

    public boolean isValidMove(Piece destinationPiece)
    {
        for (Point p : potentialMoveList)
        {
            if (p.x == destinationPiece.getxLocation() && p.y == destinationPiece.getyLocation())
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Point> getPotentialMoveList()
    {
        return potentialMoveList;
    }

    public void setPotentialMoveList(ArrayList<Point> potentialMoveList)
    {
        this.potentialMoveList = potentialMoveList;
    }
}