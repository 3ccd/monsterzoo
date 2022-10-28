import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		pz = new MonsterZoo(generateMonsterZukan());

		//1000ミリ秒（1秒）ずつ止まりながらpz.move()を呼び出し続ける
		//手持ちのボールが無くなったら終了
		while(true){
			try {
				Thread.sleep(1000);
				if(pz.getBalls()>0){
					pz.move();
					System.out.println("手持ちのボールは"+pz.getBalls()+"個，フルーツは"+pz.getFruits()+"個");
					System.out.println(pz.getDistance()+"km歩いた．");
				}else{
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("ボールがなくなった！");

		for(int i=0;i<pz.getUserMonster().length;i++){
			if(pz.getUserMonster()[i]!=null){
				System.out.println(pz.getUserMonster()[i]+"を捕まえた．");
			}
		}
	}

	//テスト用のモンスターデータを登録するメソッド
	public static ArrayList<Monster> generateMonsterZukan(){
		ArrayList<Monster> monsters = new ArrayList<Monster>();

		monsters.add(new Monster("イガキン", 9));
		monsters.add(new Monster("ナマチュウ", 3));
		monsters.add(new Monster("イノウエン", 1));
		monsters.add(new Monster("リョージィ", 2));
		monsters.add(new Monster("アキモトン", 5));
		monsters.add(new Monster("ゴージマ", 4));
		monsters.add(new Monster("ゴージマ", 4)); 
		monsters.add(new Monster("チュウデン", 6)); 
		monsters.add(new Monster("ヅカホン", 8)); 
		monsters.add(new Monster("ニシムラー", 7)); 
		monsters.add(new Monster("サコーデン", 2)); 
		monsters.add(new Monster("ウッチー", 5)); 
		monsters.add(new Monster("ハヤッシー", 4)); 
		monsters.add(new Monster("キーチー", 3)); 
		monsters.add(new Monster("リョクン", 1)); 
		monsters.add(new Monster("デコポン", 6)); 
		monsters.add(new Monster("カミサン", 5)); 
		monsters.add(new Monster("シスイ", 1)); 
		monsters.add(new Monster("ジョナ", 7)); 
		monsters.add(new Monster("ギダギダ", 2)); 
		monsters.add(new Monster("ミッツー", 8)); 
		monsters.add(new Monster("ゾエサン", 5)); 
		monsters.add(new Monster("キタバー", 3)); 
		
		return monsters;
	}

}
