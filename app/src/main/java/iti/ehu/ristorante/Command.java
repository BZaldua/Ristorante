package iti.ehu.ristorante;

import java.util.ArrayList;

public class Command {

    private int id;
    private ArrayList<Dish> dishList;

    public Command(){}

    public Command(ArrayList<Dish> d){
        this.dishList = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishListString() {
        String text = "";
        for(Dish d:dishList){
            text += d.getDishType()+","+d.getName()+","+d.getPrice()+"/";
        }
        return text;
    }

    public Dish getDish(String name) {
        String text = "";
        for(Dish d:dishList){
            if (d.getName().equals(name))
                return d;
        }
        return null;
    }

    public void setDish(ArrayList<Dish> dishList) {
        this.dishList = dishList;
    }

    public Float getTotalPrice(){
        Float total = 0f;
        for(Dish d:dishList){
            total+=d.getPrice();
        }
        return total;
    }
}
