/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkbar;
import java.util.*;
/**
 *
 * @author Kamil Pyska
 */
public class Customer extends Thread {

    private final TableForMeals tableForMeals;
    private final List<String> eatenMeals;

    public Customer(TableForMeals tableForMeals) {
        this.tableForMeals = tableForMeals;
        this.eatenMeals = new LinkedList<>();
    }

    public void eatMeal(String meal){
        this.eatenMeals.add(meal);
    }
    
    public List<String> getEatenMealsList() {
        return this.eatenMeals;
    }

    public void run() {
        try {
            while (true) {
                this.tableForMeals.takeMeal(this);
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " skończył jeść posiłki.\n");
        }
    }
}
