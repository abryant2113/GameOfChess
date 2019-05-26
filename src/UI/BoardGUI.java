package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BoardGUI extends JPanel
{
    private BoardListener listener;
    private JButton[][] board;

    BoardGUI()
    {
        initComponents();
    }

    private void initComponents()
    {
        this.setPreferredSize(new Dimension(800, 300));
        this.setMinimumSize(new Dimension(800, 300));
        this.setLayout(new GridLayout(8, 8));

        board = new JButton[8][8];

        listener = new BoardListener();

        boolean whiteTile = false;

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
                    board[i][j].setBackground(Color.BLACK);
                    whiteTile = true;
                }

                board[i][j].putClientProperty("row", i);
                board[i][j].putClientProperty("col", j);
                board[i][j].putClientProperty("rank", "King");
                board[i][j].addActionListener(listener);

                this.add(board[i][j]);
            }
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
