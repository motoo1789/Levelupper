package youser;

public class Youser {

	private static Youser youser = new Youser();
	private int level = 0;
	private int nextLevel = 0;
	private int barnum = 0;


	private Youser() {

	}

	public static Youser getInstance() {
		return youser;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setNextLevel(int nextlevel) {
		this.nextLevel = nextlevel;
	}

	public void setBarNum(int num) {
		this.barnum = num;
	}

	public int getLevel() {
		return level;
	}

	public int getnextLevel() {
		return nextLevel;
	}

	public int getBarnum() {
		return barnum;
	}


}
