
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
}