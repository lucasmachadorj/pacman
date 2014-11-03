/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import prolog.Logica;

/**
 * Represents the maze that appears on screen. Creates the maze data using a 2D
 * array of Cell objects, and renders the maze on screen.
 *
 */
public final class Maze extends JPanel {
    
    Logica logica = new Logica();
    
    final static int CELL                = 20;
//    private final int      ghostInitialColumn  =16;//= 13;
//    private final int      ghostInitialRow     =16;//= 21;
    private int      lives               = 1;
    private final String   map                 = "src/pacman/levels/level2.txt/";
//    private final int      pacmanInitialColumn =  7;
//    private final int      pacmanInitialRow    =  21;
    private final int      score               = 0;
    private final Ghost    blinky;
    private Cell[][] cells;
    private final Ghost    clyde;
    private final Ghost    inky;
    public Pacman    pacman;
    private final Ghost    pinky;
    private int      tileHeight;
    private int      tileWidth;
   

    public Maze() {
        createCellArray(map);
        setPreferredSize(new Dimension(CELL * tileWidth, CELL * tileHeight));
        pacman = new Pacman(this, 3,logica);
        inky   = new Ghost(this, "inky.png",new IntelForte(logica,2));
        blinky = new Ghost( this, "blinky.png", new IntelForte(logica,0));
        pinky  = new Ghost( this, "pinky.png",new IntelFraca(logica,3));
        clyde  = new Ghost( this, "clyde.png",new IntelFraca2(logica,1));

        // Start ghosts first
        inky.start();
        blinky.start();
        pinky.start();
        clyde.start();
        pacman.start();

        /*
         * Key Listeners
         */
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent k) {
                switch (k.getKeyCode()) {
                case (KeyEvent.VK_KP_DOWN) :
                case (KeyEvent.VK_DOWN) :
                    pacman.setDirection('d');

                    break;

                case (KeyEvent.VK_KP_UP) :
                case (KeyEvent.VK_UP) :
                    pacman.setDirection('u');

                    break;

                case (KeyEvent.VK_KP_RIGHT) :
                case (KeyEvent.VK_RIGHT) : 
                        pacman.setDirection('r');

                    break;

                case (KeyEvent.VK_KP_LEFT) :
                case (KeyEvent.VK_LEFT) :
                    pacman.setDirection('l');

                    break;
                }
            }
        });
       
       // checkCollision();
        repaint();
    }

    /**
     * Reads from the map file and create the two dimensional array
     */
    private void createCellArray(String mapFile) {

        // Scanner object to read from map file
        Scanner           fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        // Attempt to load the maze map file
        try {
            fileReader = new Scanner(new File(mapFile));

            while (true) {
                String line = null;

                try {
                    line = fileReader.nextLine();
                } catch (Exception eof) {

                    // throw new A5FatalException("Could not read resource");
                }

                if (line == null) {
                    break;
                }

                lineList.add(line);
            }

            tileHeight = lineList.size();
            tileWidth  = lineList.get(0).length();

            // Create the cells
            cells = new Cell[tileHeight][tileWidth];

            for (int row = 0; row < tileHeight; row++) {
                String line = lineList.get(row);

                for (int column = 0; column < tileWidth; column++) {
                    char type = line.charAt(column);

                    cells[row][column] = new Cell(column, row, type);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Maze map file not found");
        }
    }

    /**
     * Generic paint method Iterates through each cell/tile in the 2D array,
     * drawing each in the appropriate location on screen
     *
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, tileWidth * CELL, tileHeight * CELL);

        // Outer loop loops through each row in the array
        for (int row = 0; row < tileHeight; row++) {

            // Inner loop loops through each column in the array
            for (int column = 0; column < tileWidth; column++) {
                cells[row][column].drawBackground(g);
            }
        }

        // Pacman.drawScore(g);
        pacman.drawPacman(g);
        inky.drawGhost(g);
        blinky.drawGhost(g);
        clyde.drawGhost(g);
        pinky.drawGhost(g);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getScore() {
        return pacman.getScore();
    }

    public int getLives() {
        return pacman.getLives();
    }

    public void setEdible() {
        inky.deadly                = false;
        blinky.deadly              = false;
        pinky.deadly               = false;
        clyde.deadly               = false;
        inky.edibleLifeRemaining   = inky.edibleLifetime;
        blinky.edibleLifeRemaining = blinky.edibleLifetime;
        pinky.edibleLifeRemaining  = pinky.edibleLifetime;
        clyde.edibleLifeRemaining  = clyde.edibleLifetime;
        System.out.println("OMNOMNOM!");
    }

    public void checkCollision() {
//        if (pinky.deadly && (pinky.getCol() == pacman.getCol()) && (pinky.getRow() == pacman.getRow())) {
//            System.out.println("Pacman eaten by Pinky!");
//            loseLife();
//        } else if (inky.deadly && (inky.getCol() == pacman.getCol()) && (inky.getRow() == pacman.getRow())) {
//            System.out.println("Pacman eaten by Inky!");
//            loseLife();
//        } else if (blinky.deadly && (blinky.getCol() == pacman.getCol()) && (blinky.getRow() == pacman.getRow())) {
//            System.out.println("Pacman eaten by Blinky!");
//            loseLife();
//        } else if (clyde.deadly && (clyde.getCol() == pacman.getCol()) && (clyde.getRow() == pacman.getRow())) {
//            System.out.println("Pacman eaten by Clyde!");
//            loseLife();
//        }
        
        
        if(logica.colisao() || verificaTroca(blinky.getMov(),pacman.getMov()) || verificaTroca(pinky.getMov(),pacman.getMov())
                || verificaTroca(inky.getMov(),pacman.getMov()) || verificaTroca(clyde.getMov(),pacman.getMov()))
        {
             loseLife();
        }
        
        
    }

    public void loseLife() {
        lives--;
        PacmanGUI.newDisp();    // TODO - This doesn't appear to update lives

        // TODO - Need to integrate an actual death.
        if (lives <= 0) {
            inky.endgame();
            blinky.endgame();
            pinky.endgame();
            clyde.endgame();
            pacman.endgame();
            System.out.println("Game Over!");
        }
    }
    
    public boolean verificaTroca(Movimento mov1,Movimento mov2)
    {
        if(mov1.getX1()==mov2.getX2() && mov1.getY1()==mov2.getY2() && mov1.getX2()== mov2.getX1() && mov1.getY2()==mov2.getY1())
        {
            return true;
        }
        return false;
    }
}
