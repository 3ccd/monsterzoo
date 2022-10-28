import java.util.ArrayList;

public class EggManager{

	//卵は最大9個まで持てる．卵を取得するとeggにtrueが代入され，
	//移動するたびに,eggDistanceに1.0kmずつ加算される．
	//3km移動するとランダムでモンスターが孵る
    ArrayList<Egg> eggs;
    ArrayList<Monster> zukan;
    Integer pocket = 0;

    public EggManager(Integer pocket, HashSet<Monster> zukan){
        this.pocket = pocket;
        this.zukan = zukan;
		this.eggs = new ArrayList<Egg>();
    }

    public ArrayList<Egg> nurtureEggs(){
		ArrayList<Egg> hatched = new ArrayList<Egg>();
		for(Egg egg : eggs){

			Monster surprise = egg.nurture();
			if(tmp == null) continue;

			hatched.add(surprise);
			eggs.remove(surprise);
		}

		return hatched;
	}

    public void addEgg(){
		//egg[]に10個以上卵がない場合は新しい卵データをセットする
		if(this.eggs.size() >= pocket) return;

		this.eggs.add(new Egg(3.0, this.zukan));
	}
}

private class Egg{
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
		Interger index = (int)(this.zukan.size*Math.random());
		return this.zukan.get();
    }
}
