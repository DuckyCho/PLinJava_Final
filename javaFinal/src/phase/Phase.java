package phase;

public class Phase {
	private int phase;
	private static Phase instance = new Phase();
	
	private Phase(){
		this.phase = 0;
	}
	
	public static Phase getInstance(){
		return Phase.instance;
	}
	
	public void nextPhase(){
		this.phase++;
	}
	
	public int getPhase(){
		return this.phase;
	}
}
