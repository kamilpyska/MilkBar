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
public class Chef extends Thread {

    private final TableForMeals tableForMeals;
    private final List<String> menu;
    private final Random r;

    public Chef(TableForMeals table) {
        this.tableForMeals = table;
        this.menu = new ArrayList();
        this.r = new Random();
        menu.add("Schabowy");
        menu.add("Mielony");
        menu.add("Bigos");
    }

    public void run() {
        try {
            while (true) {
                this.tableForMeals.serveMeal(menu.get(r.nextInt(menu.size())));
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " przerwał pracę.\n");
        }
    }

}
