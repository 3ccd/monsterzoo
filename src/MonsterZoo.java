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
		this.em = new EggManager(9, this.monsterZukan);
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
		System.out.println("ズーstationを見つけた！");

		ZooStation.find(this.pocket, this.em);


	}

	private void findMonster(){
		Monster monster = Monster.random(this.monsterZukan);
		String name = monster.getName();

		System.out.println(name + "が現れた！");

		for(int i=0; i < 3; i++){//捕まえる or 3回ボールを投げるまで繰り返す
			if(! this.pocket.useBall())break;

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

class ZooStation{
	static public void find(Pocket poc, EggManager em){
		int b=(int)(Math.random()*3);//ball,fruits,eggがランダムに出る
		int f=(int)(Math.random()*2);
		int e=(int)(Math.random()*2);

		poc.addBall(b);
		poc.addFruits(f);
		em.addEgg(e);

		System.out.println("ボールを"+b+"個，"+"フルーツを"+f+"個"+"卵を"+e+"個Getした！");
	}
}
