package core;

import UI.ContainerGUI;

public class GameOfChess
{
    public static void main(String [] args)
    {
        Game game = new Game();
        ContainerGUI gameFrame = new ContainerGUI(game);
    }
}