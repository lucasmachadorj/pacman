/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author lucasmachado
 */
public class Cell {
    
    final int CELL = 20;
    protected char type;
    protected int x,y;
    
    public Cell(int x, int y, char type){
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public char getType(){
        return type;
    }
    
    public void drawBackground(Graphics g){
        int xBase = 0;
        int yBase = 0;
        
        switch(type){
            case 'e':
                g.setColor(Color.WHITE);
                g.fillRect(x*CELL, y*CELL + CELL/2 - 10, CELL, 3);
                
                break;
            
            case 'h':
                g.setColor(Color.green);
                g.fillRect(x * CELL, y * CELL + CELL / 2 - 1, CELL, 3);
            
            case 'v':
                g.setColor(Color.green);
                g.fillRect(x * CELL + CELL / 2 - 1, y * CELL, 3, CELL);

                break;
                
            case '1' :    // northeast corner
            xBase = x * CELL - CELL / 2;
            yBase = y * CELL + CELL / 2;
            drawCorner(g, xBase, yBase);

            break;
                
            case '2' :    // northwest corner
            xBase = x * CELL + CELL / 2;
            yBase = y * CELL + CELL / 2;
            drawCorner(g, xBase, yBase);

            break;

        case '3' :    // southeast corner
            xBase = x * CELL - CELL / 2;
            yBase = y * CELL - CELL / 2;
            drawCorner(g, xBase, yBase);

            break;

        case '4' :    // southwest corner
            xBase = x * CELL + CELL / 2;
            yBase = y * CELL - CELL / 2;
            drawCorner(g, xBase, yBase);

            break;

        case 'o' :
            break;    // empty navigable cell

        case 'd' :    // navigable cell with pill
            g.setColor(Color.WHITE);
            g.fillRect(x * CELL + CELL / 2 - 1, y * CELL + CELL / 2 - 1, 3, 3);

            break;

        case 'p' :    // navigable cell with power pellet
            g.setColor(Color.PINK);
            g.fillOval(x * CELL + CELL / 2 - 7, y * CELL + CELL / 2 - 7, 13, 13);

            break;

        case 'x' :    // empty non-navigable cell
        case 'g' :    // the Corral
        default :
            break;
        }
    }
    
    /*
     * Draw 3px rounded corner
     *
     */
    public void drawCorner(Graphics g, int xBase, int yBase) {
        Graphics2D g2      = (Graphics2D) g;
        Rectangle  oldClip = g.getClipBounds();

        g2.setClip(x * CELL, y * CELL, CELL, CELL);
        g2.setColor(Color.GREEN);

        Shape oval = new Ellipse2D.Double(xBase, yBase, CELL, CELL);

        g2.setStroke(new BasicStroke(3));
        g2.draw(oval);
        g2.setClip(oldClip);
    }
    
}
