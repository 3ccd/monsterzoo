public class Pocket{

	int balls = 0;
	int fruits = 0;

	public Pocket(int balls, int fruits){
		this.balls = balls;
		this.fruits = fruits;
	}

	public void addBall(int ball){
		this.balls += ball;
	}

	public void addFruits(int fruit){
		this.fruits += fruit;
	}

	public boolean useBall(){
		if(this.balls > 0){
			this.balls--;
			return true; 
		}else{
			return false;
		}
	}

	public boolean useFruit(){
		if(this.fruits > 0){
			this.fruits--;
			return true;
		}else{
			return false;
		}
	}

	public boolean ballExists(){
		if( this.balls > 0 ){
			return true;
		}else{
			return false;
		}
	}

	public void print(){
		System.out.println("手持ちのボールは"+ this.balls + "個，フルーツは"+this.fruits+"個");
	}

}