package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.*;
import java.lang.reflect.Field;

/**
 * Player
 * Game player object; players have a name, color, position on the board, seen
 * cards, and a current hand that they possess. They have the ability
 * to draw a card through the updateHand function.
 * DATE: 4/10/2023
 * 
 * @author Keenan Schott
 * @author Finn Burns
 */
public abstract class Player {
    private String name;
    private String color;
    private int row;
    private int column;
    private int x;
    private int y;
    private ArrayList<Card> hand;
    private Set<Card> seenCards;

    /**
     * Player()
     * Paramaterized constructor.
     * 
     * @param inName   The input name.
     * @param inColor  The input color.
     * @param inRow    The input row.
     * @param inColumn The input column.
     */
    public Player(String inName, String inColor, int inRow, int inColumn) {
        super();
        name = inName;
        color = inColor;
        row = inRow;
        column = inColumn;
        hand = new ArrayList<Card>(); // allocate space
        seenCards = new HashSet<Card>();
    }

    /**
     * draw()
     * Draw the player token, which is a circle, and color code it accordingly to
     * each player.
     * 
     * @param g      The Graphics object.
     * @param x      The x coordinate.
     * @param y      The y coordinate.
     * @param width  The cell width.
     * @param height The cell height.
     */
    public void draw(Graphics g, int x, int y, int width, int height, ArrayList<Player> players) {
        this.x = x; // update physical locations on the board
        this.y = y;
        Color color; // color object to store color we retrieve from player data
        try {
            Field field = Class.forName("java.awt.Color").getField(getColor()); // get color from String
            color = (Color) field.get(null);
        } catch (Exception e) { // if not valid color
            color = null; // failed to convert; return null
        }
        // if the player is in a room, offset them to the right and up depending on location in list
        if (Board.getInstance().getCell(this.getRow(), this.getColumn()).isRoomCenter()) {
            this.x = x + (width / 12) * players.indexOf(this) * 2; // always offset to the right
            if (players.indexOf(this) % 2 == 0) { // if at an even location in the list
                this.y = y + (height / 6); // offset up
            } 
        }
        g.setColor(Color.BLACK); // border color
        g.drawOval(this.x, this.y, width, height); // draw oval with radii width and height
        g.setColor(color); // filler color
        g.fillOval(this.x, this.y, width, height);
    }

    /**
     * updateHand()
     * Update a player's hand.
     * 
     * @param card The card to add.
     */
    public void updateHand(Card card) {
        hand.add(card);
    }

    /**
     * updateSeen()
     * Update seen cards set.
     * 
     * @param seenCard The card to be seen.
     */
    public void updateSeen(Card seenCard) {
        seenCards.add(seenCard);
    }

    /**
     * disproveSuggestion()
     * The method to use when disproving a suggestion.
     * 
     * @param suggestion The suggestion to be disproven.
     */
    abstract public Card disproveSuggestion(Solution suggestion);

    // all getters and setterss
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Set<Card> getSeenCards() {
        return seenCards;
    }

    public void setHand(ArrayList<Card> inHand) {
        hand = inHand;
    }

    public void setSeenCards(Set<Card> seen) {
        seenCards = seen;
    }

    public void setLocation(int row, int col) {
        this.row = row;
        this.column = col;
    }
}
