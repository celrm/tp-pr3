package logic;

public class SuncoinManager {
	
	private int suncoins;
	public static final int INISUN = 50;
	
	public SuncoinManager(){
		this.suncoins = INISUN;
	}
	
	public int num(){
		return this.suncoins;
	}
	
	public void add(int n){
		this.suncoins += n;
	}
}
