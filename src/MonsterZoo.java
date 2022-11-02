import java.util.HashSet;
import java.util.ArrayList;

public class MonsterZoo {
	double distance=0.0;//歩いた距離
	Pocket pocket = new Pocket(10, 0);

	//ユーザがGetしたモンスター一覧
	ArrayList<Monster> userMonster = new ArrayList<Monster>();

	//モンスター図鑑．モンスターの名前とレア度(0.0~9.0)がそれぞれの配列に保存されている
	//レア度が高いほうが捕まえにくい
	ArrayList<Monster> monsterZukan;

	EggManager em;

	public MonsterZoo(ArrayList<Monster> zukan){
		this.monsterZukan = zukan;
		this.em = new EggManager(9, monsterZukan);
	}

	//呼び出すと1km distanceが増える
	boolean move(){
		if(!this.pocket.ballExists()){
			System.out.println("ボールがなくなった！");
			return false;
		}

		this.distance++;

		int flg1 = (int)(Math.random()*10);//0,1の場合はズーstation，7~9の場合はモンスター
		if(flg1<=1){
			System.out.println("ズーstationを見つけた！");
			findZooStation();
		}else if(flg1>=7){
			findMonster();
		}

		updateEggs();

		return true;
		
	}

	private void updateEggs(){
		ArrayList<Monster> hatched = em.nurtureEggs();
		if(hatched.size() > 0){
			for(Monster mon : hatched){
				System.out.println("卵が孵った！");
				System.out.println(mon.getName()+"が産まれた！");
			}
		}
	}

	private void findZooStation(){
		int b=(int)(Math.random()*3);//ball,fruits,eggがランダムに出る
		int f=(int)(Math.random()*2);
		int e=(int)(Math.random()*2);
		System.out.println("ボールを"+b+"個，"+"フルーツを"+f+"個"+"卵を"+e+"個Getした！");

		this.pocket.addBall(b);
		this.pocket.addFruits(f);

		if(e>=1){//卵を1つ以上Getしたら
			em.addEgg();
		}
	}

	private void findMonster(){
		Monster monster = Monster.random(this.monsterZukan);
		String name = monster.getName();

		System.out.println(name + "が現れた！");

		for(int i=0; this.pocket.useBall(); i++){//捕まえる or 3回ボールを投げるまで繰り返す
			if( i >= 3 )break;

			boolean isCatch = false;
			if(this.pocket.useFruit()){
				System.out.println("フルーツを投げた！捕まえやすさが倍になる！");
				isCatch = monster.catchMonster(true);
			}else{
				isCatch = monster.catchMonster(false);
			}

			System.out.println(name + "にボールを投げた");


			if(isCatch){//monsterRare[m]の値がr以下の場合
				System.out.println(name + "を捕まえた！");
				userMonster.add(monster);
				break;//ボール投げ終了
			}else{
				System.out.println(name + "に逃げられた！");
			}
		}
	}

	public void printStatus(){
		this.pocket.print();
		System.out.println(this.distance+"km歩いた．");
	}

	public void printUserMonsters(){
		for (Monster monster : userMonster){
			System.out.println(monster.getName()+"を捕まえた．");
		}
	}
}

class Pocket{

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