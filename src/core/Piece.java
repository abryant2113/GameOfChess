package core;

public class Piece {

    private int playerID;
    private int xLocation;
    private int yLocation;
    private int moveCount;
    private char pieceRank;

    public Piece(int playerID, char pieceRank, int xLocation, int yLocation)
    {
        this.playerID = playerID;
        this.pieceRank = pieceRank;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.moveCount = 0;
    }

    public void printPieceData()
    {
        System.out.println("This is Player " + Integer.toString(this.playerID) + "'s  piece with a rank of "
                + this.pieceRank + " at location [" + this.yLocation + "][" + this.xLocation + "]");
    }

    public char getPieceRank()
    {
        return pieceRank;
    }

    public void setPieceRank(char pieceRank)
    {
        this.pieceRank = pieceRank;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
    }

    public int getyLocation()
    {
        return yLocation;
    }

    public void setyLocation(int yLocation)
    {
        this.yLocation = yLocation;
    }

    public int getxLocation()
    {
        return xLocation;
    }

    public void setxLocation(int xLocation)
    {
        this.xLocation = xLocation;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void incrementMoveCount() {
        this.moveCount++;
    }
}
