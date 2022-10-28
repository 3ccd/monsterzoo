import java.util.HashSet;
import java.util.ArrayList;

public class MonsterZoo {
	double distance=0.0;//歩いた距離
	int balls=10;//モンスターを捕まえられるボールの数
	int fruits=0;//ぶつけるとモンスターが捕まえやすくなるフルーツ

	//ユーザがGetしたモンスター一覧
	String userMonster[] = new String[100];

	//モンスター図鑑．モンスターの名前とレア度(0.0~9.0)がそれぞれの配列に保存されている
	//レア度が高いほうが捕まえにくい
	HashSet<Monster> monsterZukan;

	EggManager em = new EggManager(9, monsterZukan);

	public MonsterZoo(ArrayList<Monster> zukan){
		this.monsterZukan = zukan;
	}

	//呼び出すと1km distanceが増える
	void move(){
		this.distance++;

		int flg1 = (int)(Math.random()*10);//0,1の場合はズーstation，7~9の場合はモンスター
		if(flg1<=1){
			System.out.println("ズーstationを見つけた！");
			findZooStation();
		}else if(flg1>=7){
			findMonster();
		}

		updateEggs();
		
	}

	private updateEggs(){
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

		this.balls=this.balls+b;
		this.fruits=this.fruits+f;

		if(e>=1){//卵を1つ以上Getしたら
			em.addEgg();
		}
	}

	private void findMonster(){
		int m = (int)(this.monsterZukan.length*Math.random());//monsterZukanからランダムにモンスターを出す
		System.out.println(this.monsterZukan[m]+"が現れた！");
		for(int i=0;i<3&&this.balls>0;i++){//捕まえる or 3回ボールを投げるまで繰り返す
			int r = (int)(6*Math.random());//0~5までの数字をランダムに返す
			if(this.fruits>0){
				System.out.println("フルーツを投げた！捕まえやすさが倍になる！");
				this.fruits--;
				r = r * 2;
			}
			System.out.println(this.monsterZukan[m]+"にボールを投げた");
			this.balls--;
			if(this.monsterRare[m]<=r){//monsterRare[m]の値がr以下の場合
				System.out.println(this.monsterZukan[m]+"を捕まえた！");
				for(int j=0;j<userMonster.length;j++){
					if(this.userMonster[j]==null){
						this.userMonster[j]=this.monsterZukan[m];
						break;
					}
				}
				break;//ボール投げ終了
			}else{
				System.out.println(this.monsterZukan[m]+"に逃げられた！");
			}
		}
	}

	

	public double getDistance() {
		return distance;
	}

	public int getBalls() {
		return balls;
	}

	public int getFruits() {
		return fruits;
	}

	public String[] getUserMonster() {
		return userMonster;
	}
}
