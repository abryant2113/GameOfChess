package UI;

import javax.swing.*;
import java.awt.*;
import core.Constants;

public class ContainerGUI extends JFrame
{
    private BoardGUI board;

    public ContainerGUI()
    {
        initComponents();
    }

    private void initComponents()
    {
        this.setTitle(Constants.TITLE_STRING);
        this.setPreferredSize(new Dimension(600, 600));
        this.setMinimumSize(new Dimension(600, 600));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new BoardGUI();

        this.add(board, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
