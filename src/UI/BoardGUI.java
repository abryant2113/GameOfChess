package UI;

import core.Constants;
import core.Game;
import core.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class BoardGUI extends JPanel
{
    private JButton[][] board;
    private JButton selectedButton;
    private Game game;

    BoardGUI(Game game)
    {
        selectedButton = null;
        this.game = game;
        initComponents();
    }

    private void initComponents()
    {
        BoardListener listener = new BoardListener();
        boolean whiteTile = false;
        char [][] gameState = this.game.getBoard();

        this.setPreferredSize(new Dimension(800, 300));
        this.setMinimumSize(new Dimension(800, 300));
        this.setLayout(new GridLayout(8, 8));

        board = new JButton[8][8];

        for (int i = 0; i < 8; i++)
        {
            // provides the alternating color scheme that are on chessboards
            whiteTile = !whiteTile;

            for (int j = 0; j < 8; j++)
            {
                board[i][j] = new JButton();

                if (whiteTile)
                {
                    board[i][j].setBackground(Color.WHITE);
                    whiteTile = false;
                }
                else
                {
                    board[i][j].setBackground(Color.ORANGE);
                    whiteTile = true;
                }

                if (gameState[i][j] != '-')
                {
                   chooseImage(board[i][j], gameState[i][j]);
                }

                if (i == 0 || i == 1)
                {
                    board[i][j].putClientProperty("piece", new Piece(Constants.PLAYER_TWO, gameState[i][j], j, i));
                }
                else if (i == 6 || i == 7)
                {
                    board[i][j].putClientProperty("piece", new Piece(Constants.PLAYER_ONE, gameState[i][j], j, i));
                }
                else
                {
                    board[i][j].putClientProperty("piece", new Piece(Constants.EMPTY_SPOT, gameState[i][j], j, i));
                }

                board[i][j].addActionListener(listener);

                this.add(board[i][j]);
            }
        }
    }

    private ImageIcon imageResize(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }

    private void chooseImage(JButton curButton, char curChar)
    {
        switch(curChar)
        {
            case 'R':
                curButton.setIcon(imageResize(new ImageIcon(getClass().getResource("/images/rook.png"))));
                break;
            case 'K':
                curButton.setIcon(imageResize(new ImageIcon(getClass().getResource("/images/king.png"))));
                break;
            case 'B':
                curButton.setIcon(imageResize(new ImageIcon(getClass().getResource("/images/bishop.png"))));
                break;
            case 'Q':
                curButton.setIcon(imageResize(new ImageIcon(getClass().getResource("/images/queen.png"))));
                break;
            case 'N':
                curButton.setIcon(imageResize(new ImageIcon(getClass().getResource("/images/knight.png"))));
                break;
            case 'P':
                curButton.setIcon(imageResize(new ImageIcon(getClass().getResource("/images/pawn.png"))));
                break;
        }
    }

    private boolean isValidSelection()
    {
        return selectedButton.getIcon() != null;
    }

    private void swapPlaces (Piece selectedPiece, Piece destinationPiece)
    {
        int xTemp = selectedPiece.getxLocation();
        int yTemp = selectedPiece.getyLocation();

        selectedPiece.setxLocation(destinationPiece.getxLocation());
        selectedPiece.setyLocation(destinationPiece.getyLocation());

        destinationPiece.setxLocation(xTemp);
        destinationPiece.setyLocation(yTemp);
    }

    private void removeHighlighting()
    {
        if (game.getPotentialMoveList() == null)
        {
           return;
        }

        for(Point p : game.getPotentialMoveList())
        {
            if (colorOrange(p))
            {
                board[p.y][p.x].setBackground(Color.ORANGE);
            }
            else
            {
                board[p.y][p.x].setBackground(Color.WHITE);
            }
        }
    }

    private boolean colorOrange(Point p)
    {
        return (p.y % 2 != 0 && p.x % 2 == 0) || (p.y % 2 == 0 && p.x % 2 != 0);
    }

    private class BoardListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Logic to handle movement will go here
            if (e.getSource() instanceof JButton)
            {
                if(selectedButton != null)
                {
                    JButton destinationButton = (JButton)e.getSource();

                    removeHighlighting();

                    Piece selectedPiece = (Piece)selectedButton.getClientProperty("piece");
                    Piece destinationPiece = (Piece)destinationButton.getClientProperty("piece");

                    if (!game.isValidMove(selectedPiece, destinationPiece))
                    {
                        JOptionPane.showMessageDialog(null, Constants.INVALID_MOVE_STRING);
                        selectedButton = null;
                        return;
                    }

                    ImageIcon test = (ImageIcon)selectedButton.getIcon();

                    destinationButton.setIcon(test);

                    selectedButton.setIcon(null);

                    selectedPiece.incrementMoveCount();

                    game.updateState(selectedPiece, destinationPiece);

                    game.printBoardState();

                    swapPlaces(selectedPiece, destinationPiece);

                    selectedButton.putClientProperty("piece", destinationPiece);
                    destinationButton.putClientProperty("piece", selectedPiece);

                    selectedButton = null;
                }
                else
                {
                    selectedButton = (JButton) e.getSource();

                    if (!isValidSelection())
                    {
                        selectedButton = null;
                        JOptionPane.showMessageDialog(null, Constants.INVALID_MOVE_STRING);
                    }
                    else
                    {
                        Piece selectedPiece = (Piece)selectedButton.getClientProperty("piece");
                        game.highlightMoves(board, selectedPiece);

                        for (Point p : game.getPotentialMoveList())
                        {
                            board[p.y][p.x].setBackground(Color.GREEN);
                        }
                    }
                }
            }
        }
    }
}