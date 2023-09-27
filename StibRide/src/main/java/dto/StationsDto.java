package dto;

public class StationsDto extends Dto<Integer>{
    private String name;
    /**
     * Creates a new instance of <code>Dto</code> with the key of the data.
     *
     * @param key key of the data.
     */
    public StationsDto(Integer key) {
        super(key);
    }

    public StationsDto(Integer key, String name){
        super(key);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
