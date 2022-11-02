import java.util.ArrayList;

public class EggManager{

	//卵は最大9個まで持てる．卵を取得するとeggにtrueが代入され，
	//移動するたびに,eggDistanceに1.0kmずつ加算される．
	//3km移動するとランダムでモンスターが孵る
    ArrayList<Egg> eggs;
    ArrayList<Monster> zukan;
    Integer pocket = 0;

    public EggManager(Integer pocket, ArrayList<Monster> zukan){
        this.pocket = pocket;
        this.zukan = zukan;
		this.eggs = new ArrayList<Egg>();
    }

    public ArrayList<Monster> nurtureEggs(){
		ArrayList<Monster> hatched = new ArrayList<Monster>(); // 生まれたモンスター
        ArrayList<Egg> hatchedEgg = new ArrayList<Egg>(); // 卵の殻
		for(Egg egg : eggs){

			Monster surprise = egg.nurture(); // 卵を育てる
			if(surprise == null) continue;

			hatched.add(surprise);
            hatchedEgg.add(egg);
		}
        for(Egg rmEgg : hatchedEgg){
            eggs.remove(rmEgg);
        }

		return hatched;
	}

    public void addEgg(int egg){

        for (int i = 0; i < egg; i++){
		    //egg[]に10個以上卵がない場合は新しい卵データをセットする
		    if(this.eggs.size() >= pocket) break;
            
		    this.eggs.add(new Egg(3.0, this.zukan));
        }
	}
}

class Egg{
    double lifeTime = 0.0;
    double distance = 0.0;
	ArrayList<Monster> zukan;

    public Egg(double distance, ArrayList<Monster> zukan){
        this.distance = distance;
		this.zukan = zukan;
    }
    
    public Monster nurture(){
        this.lifeTime += 1.0;
        if(lifeTime >= distance){
            return whoAmI();
        }
        return null;
    }

    private Monster whoAmI(){
		int index = (int)(this.zukan.size()*Math.random());
		return this.zukan.get(index);
    }
}
