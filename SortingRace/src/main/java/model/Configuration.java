package model;

public enum Configuration {
    VERY_EASY(100),EASY(1000),NORMAL(10000),HARD(100000);
    private int value;
    private Configuration(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
