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
public class TableForMeals {

    private final List<String> mealsOnTable;
    private final int tableCapacity;

    public TableForMeals(int tableCapacity) {
        this.tableCapacity = tableCapacity;
        this.mealsOnTable = new LinkedList();
    }

    public synchronized void serveMeal(String meal) throws InterruptedException {
        String name = Thread.currentThread().getName();
        System.out.format("%s próbuje położyć nowy posiłek na stole.\n", name);
        waitForPlaceOnTable();
        this.mealsOnTable.add(meal);
        System.out.format("%s położył nowy posiłek na stole.\n", name);
        notifyAll();
    }

    public synchronized void takeMeal(Customer customer) throws InterruptedException {
        String name = Thread.currentThread().getName();
        System.out.format("%s chciałby odebrać posiłek.\n", name);
        waitForMealOnTable();
        System.out.format("%s odbiera posiłek.\n", name);
        customer.eatMeal(this.mealsOnTable.get(0));
        this.mealsOnTable.remove(this.mealsOnTable.get(0));
        notifyAll();
    }

    private synchronized void waitForMealOnTable() throws InterruptedException {
        while (this.mealsOnTable.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " czeka na posiłek.");
            wait();
        }
    }

    private synchronized void waitForPlaceOnTable() throws InterruptedException {
        while (mealsOnTable.size() == tableCapacity) {
            System.out.println(Thread.currentThread().getName() + " czeka na miejsce na stole.");
            wait();
        }
    }
}
