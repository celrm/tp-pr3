package logic;

public enum  Level{
	EASY, HARD, INSANE;
	
	public int numZombies(){
		int num = 0;
		switch(this){
		case EASY: num = 3; break;
		case HARD:  num = 5; break;
		case INSANE: num = 10; break;
		}
		return num;
	}
	public double frec(){
		double num = 0.0;
		switch(this){
		case EASY: num = 0.1; break;
		case HARD:  num = 0.2; break;
		case INSANE: num = 0.3; break;
		}
		return num;
	}
	
	public String toString(){
		String str;
		switch(this){
		case EASY: str = "EASY"; break;
		case HARD:  str = "HARD" ; break;
		case INSANE: str = "INSANE"; break;
		default: str = "EASY"; break;
		}
		return str;
	}
}

