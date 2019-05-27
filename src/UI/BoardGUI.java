package UI;

import core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BoardGUI extends JPanel
{
    private JButton[][] board;
    private Game game;

    BoardGUI(Game game)
    {
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

                board[i][j].putClientProperty("row", i);
                board[i][j].putClientProperty("col", j);
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

    private class BoardListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Logic to handle movement will go here
            if (e.getSource() instanceof JButton)
            {
                JButton clickedButton = (JButton)e.getSource();
            }
        }
    }
}