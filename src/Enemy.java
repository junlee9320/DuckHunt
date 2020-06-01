//ENEMY CLASS
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Enemy {
    
    private Image img; //img file name 
    private int x, y; //x and y coordinates
    private int vx, vy; //velocities 
    private int timeleft; //time left until enemy despawns
    public boolean isDead; //whether enemy is alive or dead
    
    public Enemy() {
        x = (int)(Math.random()*1175); //values keep enemy within the screen
        y = (int)(Math.random()*600); //values keep enemy within the screen
        timeleft = 2; //2 sec??
        img = getImage("enemy.png"); //enemy image
        init(x, y); //calls init method which initializes the enemy object
        isDead = false; //all enemies start off as not dead aka alive
    }
    
    public int getx(){ //get x-coordinate of enemy object
        return x;
    }
    public int gety(){ //get y-coordinate of enemy object
        return y;
    }
    public boolean getIsDead(){ //return whether or not enemy is dead
        return isDead;
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, tx, null);
    }

    private void init(double a, double b) { //initializes the enemy object
        tx.setToTranslation(a, b);
        tx.scale(1, 1);
    }
    
    public void move() { //use in case we want the enemies to move
        tx.translate(vx,vy);
        x+=vx;
        y+=vy;
        tx.setToTranslation(x,y);
        
    }
    
    private AffineTransform tx = AffineTransform.getTranslateInstance(x, x);
    
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Enemy.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }
    
    public void despawn() {
        isDead = true; //links to main class
    }
    
    public void checkSpawn() {
        //checks whether enemy is in the time limit
        if (timeleft==0) {
            despawn(); //despawn if time limit is crossed
        }
    }
    
}
