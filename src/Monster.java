import java.util.ArrayList;

public class Monster{
    private String name = "";
    private Integer rare = 0;

    Monster(String _name, Integer _rare){
        name = _name;
        rare = _rare;
    }

    public String getName(){
        return name;
    }

    public Integer getRare(){
        return rare;
    }

    public boolean catchMonster(boolean fruit){
        int r = (int)(6*Math.random());//0~5までの数字をランダムに返す

        if(fruit) r = r * 2;
        
        if(this.rare <= r){
            return true;
        }else{
            return false;
        }
    }



    static public Monster random(ArrayList<Monster> monsterZukan){
        int m = (int)(monsterZukan.size()*Math.random());//monsterZukanからランダムにモンスターを出す
        return monsterZukan.get(m);
    }
}