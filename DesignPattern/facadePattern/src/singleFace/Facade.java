package singleFace;

import singleFace.food.Humbuger;
import singleFace.food.Pizza;
import singleFace.food.PotatoChips;

/**
 * FileName: Facade
 * Author: yeyang
 * Date: 2018/7/3 14:20
 */
public class Facade {
    private Humbuger humbuger = null;
    private Pizza pizza = null;
    private PotatoChips potatoChips = null;
    public Facade(){
        this.humbuger = new Humbuger();
        this.pizza = new Pizza();
        this.potatoChips = new PotatoChips();
    }

    public void orderFood(String type){
        switch (type){
            case "Home":getHomeFood();break;
            case "sanke":getSankeFood();break;
            case "elec":getElecFood();break;
            default:System.out.println("本店暂无需求套餐");
        }
    }


    private void getHomeFood() {
        humbuger.eated();
        pizza.eated();
        potatoChips.eated();
    }

    private void getSankeFood() {
        humbuger.eated();
        potatoChips.eated();
    }
    private void getElecFood(){
        humbuger.eated();
        pizza.eated();
    }
}
