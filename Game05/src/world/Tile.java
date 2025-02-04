/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author samuel
 */
public class Tile {
    
    public static BufferedImage TILE_NULL = Game.spritesheet.getSprite(0, 16, 16, 16);
    public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
    public static BufferedImage TILE_FLOOR_FLOWER = Game.spritesheet.getSprite(32, 48, 16, 16);
    public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);
    
    private BufferedImage sprite;
    public int x,y;
    
    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    
    public void render(Graphics g) {
        g.drawImage(sprite, x-Camera.x, y-Camera.y, null);
    }
}
