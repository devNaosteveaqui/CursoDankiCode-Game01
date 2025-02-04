package pacman.entities;


import pacman.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import pacman.world.Camera;
import pacman.world.Node;
import pacman.world.Vector2i;
import pacman.world.World;
import java.util.Random;

/**
 *
 * @author samuel
 */
public class Entity {
    
    public static final BufferedImage CHERRY_SPRITE = Game.spritesheet.getSprite(0, 16, 16, 16);
    public static final BufferedImage ENEMY_SPRITE = Game.spritesheet.getSprite(96, 0, 16, 16);
    public static final BufferedImage ENEMY_GHOST_SPRITE = Game.spritesheet.getSprite(112, 0, 16, 16);
    
    public double x;
    public double y;
    protected int z;
    protected int width;
    protected int height;
    protected double speed;
    public int depth;
    
    private BufferedImage sprite;
    protected List<Node> path;
    
    public static Random rand = new Random();
    
    public static Comparator<Entity> nodeSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity e0, Entity e1) {
            if(e1.depth < e0.depth) {
                return +1;
            }
            if(e1.depth > e0.depth) {
                return -1;
            }
            return 0;
        }
    };
    public Entity(double x, double y, int width, int height,double speed, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        
        depth = 0;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public int getX() {
        return (int) this.x;
    }
    public int getY() {
        return (int) this.y;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void update() {}
    
    public double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }
    public void updateCamera() {
        Camera.x = Camera.clamp(this.getX() - Game.WIDTH/2, 0, World.WIDTH*16 - Game.WIDTH);
        Camera.y = Camera.clamp(this.getY() - Game.HEIGHT/2, 0, World.HEIGHT*16 - Game.HEIGHT);
    }
    public void followPath(List<Node> path) {
        if(path != null) {
            if(path.size() > 0) {
                Vector2i target = path.get(path.size()-1).tile;
                //xprev = x;
                //yprev = y;
                
                if(x < target.x*16) {
                    x++;
                } else if(x > target.x*16) {
                    x--;
                }
                
                if(y < target.y*16){
                    y++;
                } else if(y > target.y*16) {
                    y--;
                }
                
                if(x == target.x*16 && y == target.y*16) {
                    path.remove(path.size()-1);
                }
            }
        }
    }
    
    public void render(Graphics g) {
        g.drawImage(sprite, this.getX()-Camera.x, this.getY()-Camera.y, null);
    }
    public static boolean isColidding(Entity e1, Entity e2) {
        Rectangle e1Mask = new Rectangle(e1.getX(),e1.getY(),e1.getWidth(),e1.getHeight());
        Rectangle e2Mask = new Rectangle(e2.getX(),e2.getY(),e2.getWidth(),e2.getHeight());
        
        return (e1Mask.intersects(e2Mask) && e1.z == e2.z);
    }
}

