import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
    ArrayList<Enemy> enemyList = new ArrayList<>(); //arraylist of all enemies
    //using an arraylist helps keep track of how many enemies exist at a certain point in time
    //and allows modifications in that number easily
    int mouseX, mouseY; //x and y positions of the mouse
    
    int points = 0;
	int fireradius = 50;
	int timercount;
	int temptimer;
	int duration;
	boolean timerend = false;
	boolean timestopped = false;
	boolean incrad = false;
	
	String info = "i am info-chan";
    
    public void paint(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < enemyList.size(); i++) { 
            enemyList.get(i).paint(g); //paint every object in the enemyList arraylist
            //aka paint all the existing enemies
        }
        g.drawString(("Time Left: ") + Integer.toString(timercount), 1010, 50);
		if(timerend) {
			g.drawString("oof out of time", 1010, 200);
		}
		g.drawString(("Temptimer: ") + Integer.toString(temptimer), 1010, 100);
		
		g.drawOval(mouseX-(fireradius/2), mouseY-(fireradius-(fireradius/4)), fireradius, fireradius);
		//needs editing
		
		g.drawString(info, 1010, 150);
    }
    
    public void update() {
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).checkSpawn(); //checks whether the spawned enemy is valid (is within the time limit)
            if (enemyList.get(i).getIsDead()==true) {
                enemyList.remove(i); //removes the enemy object from the enemy arraylist
                //in case it has passed the time limit and is 'dead'
            }
        }
        if(timestopped) {
			temptimer++;
			if(temptimer>=duration) {
				timestopped = false;
				temptimer=0;
				info = "i am info-chan";
			}
		}else if(!timestopped) {
			if(timercount<=0) {
				timerend = true;
			}else {
				timercount--;
			}
		}
		
		if(incrad) {
			temptimer++;
			if(temptimer>=duration) {
				incrad = false;
				temptimer=0;
				fireradius = 50;
				info = "i am info-chan";
			}
		}
    }
    
    public static void main(String[] args) {
        Main m = new Main();
    }

    public Main() {
        JFrame f = new JFrame();
        f.setTitle("Robot Tower Defense"); //not really robot tower defense
        f.setSize(1440, 900);
        f.setResizable(false);
        f.addKeyListener(this);
        f.addMouseListener(this);
        f.addMouseMotionListener(this);
        
        enemyList.add(new Enemy()); //enemy #1
        enemyList.add(new Enemy()); //enemy #2
        //add as many enemies needed here
        
        timercount = 1000;
        
        f.add(this);
        t = new Timer(17, this);
        t.start();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true); //hi
        
    }    
    Timer t;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
        
    }
    
    @Override    
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //modifies the mouseX and mouseY variables every time the mouse is moved
    	mouseX = e.getX();
		mouseY = e.getY();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("X: " + mouseX + "  Y: " + mouseY); //print mouse x/y values for testing purposes
        for (int i = 0; i < enemyList.size(); i++) { //iterates through all enemies in enemyList
            //check if the mouse is clicked on the enemy
            if (mouseX>=enemyList.get(i).getx()&&mouseX<(enemyList.get(i).getx()+100)){ //if mouseX lies within enemy's x-range
                if (mouseY>=enemyList.get(i).gety()&&mouseY<=(enemyList.get(i).gety()+100)) { //and if mouseY lies with enemy's y-range
                    enemyList.get(i).despawn(); //despawn that particular enemy (removes it from the arraylist)
                }                
            }
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == 82) {
			info = "time stop powerup activated";
			TimeStop();
		}
		
		if(e.getKeyCode() == 84) {
			info = "increased radius powerup activated";
			IncRadius();
		}
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    public void ClearScreen() {
		points += enemyList.size();
		enemyList.clear();
	}
	
	public void TimeStop() {
		duration = 200;
		timestopped = true;
	}
	
	public void IncRadius() {
		duration = 150;
		fireradius = 400;
		incrad = true;
	}
}

