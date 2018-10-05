package p1;

public enum  Level{
	EASY, HARD, INSANE;
	
	public int numZombies(){
		int num = 0;
		switch(this){
		case EASY: num = 3;
		case HARD:  num = 5;
		case INSANE: num = 10;
		}
		return num;
	}
	public double frec(){
		double num = 0.0;
		switch(this){
		case EASY: num = 0.1;
		case HARD:  num = 0.2;
		case INSANE: num = 0.3;
		}
		return num;
	}
}

