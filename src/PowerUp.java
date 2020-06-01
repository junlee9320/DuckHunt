
public class PowerUp {
	
	private int duration;
	private boolean WipeUsed, RadiusUsed, TimeUsed;

	public PowerUp(int difficulty) {
		
		WipeUsed = false;
		RadiusUsed = false;
		TimeUsed = false;
		
		switch(difficulty) {
			case 0:
				duration = 15;
				break;
			case 1:
				duration = 10;
				break;
			case 2:
				duration = 5;
				break;
		}
	}

	public void useScreenWipe() {
		//clears the arraylist of enemies
		WipeUsed = true;
	}
	
	public void useLargerRadius() {
		//increase shot radius
		RadiusUsed = true;
	}
	
	public void useSlowTime() {
		//stop game timer and despawn timer
		TimeUsed = true;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isWipeUsed() {
		return WipeUsed;
	}

	public void setWipeUsed(boolean wipeUsed) {
		WipeUsed = wipeUsed;
	}

	public boolean isRadiusUsed() {
		return RadiusUsed;
	}

	public void setRadiusUsed(boolean radiusUsed) {
		RadiusUsed = radiusUsed;
	}

	public boolean isTimeUsed() {
		return TimeUsed;
	}

	public void setTimeUsed(boolean timeUsed) {
		TimeUsed = timeUsed;
	}
}