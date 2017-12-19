package iti.ehu.ristorante;


public class Dish {

    private int id;
    private int dishType; // 1: Primero;   2: Segundo   3: Tercero
    private String name;
    private Float price;

    public Dish(){}

    public Dish(int id, int dishType, String name, Float price) {
        this.id = id;
        this.dishType = dishType;
        this.name = name;
        this.price = price;
    }

    public Dish(int dishType, String name, Float price){
        this.dishType = dishType;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishType() {
        return dishType;
    }

    public void setDishType(int dishType) {
        this.dishType = dishType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String toString(){
        return this.dishType+";"+this.name+";"+this.price;
    }

}
