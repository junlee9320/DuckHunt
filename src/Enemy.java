
public class Enemy {
	
	private String sprite; //img file name
	private int x, y; //coords
	private int timeleft; //time left until despawns
	
	public Enemy(int max, int min, String img) {
		x = (int)(Math.random()*max-min)+min; //random coords
		y = (int)(Math.random()*max-min)+min;
		timeleft = 2; //2 sec?
	}
}
