package p1;

public class SuncoinManager {
	
	private int suncoins;
	public static int INISUN = 50;
	
	public SuncoinManager(){
		this.suncoins = INISUN;
	}
	
	public int num (){
		return this.suncoins;
	}
	
	public void add(int n){
		this.suncoins += n;
	}
}
