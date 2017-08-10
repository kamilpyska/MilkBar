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
public class MilkBar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        TableForMeals tableForMeals = new TableForMeals(8);
        List<Chef> chefsList = new LinkedList<>();
        List<Customer> customersList = new LinkedList<>();
        Scanner odczyt = new Scanner(System.in);

        System.out.println("Ilu kucharzy dziś obsługuje?");
        int howManyChefs = odczyt.nextInt();

        System.out.println("Ilu klientów przyszło do baru?");
        int howManyCustomers = odczyt.nextInt();

        for (int i = 0; i < howManyChefs; i++) {
            chefsList.add(new Chef(tableForMeals));
        }

        for (int i = 0; i < howManyCustomers; i++) {
            customersList.add(new Customer(tableForMeals));
        }

        for (Chef chef : chefsList) {
            chef.start();
        }

        for (Customer customer : customersList) {
            customer.start();
        }

        Thread.sleep(1000);

        for (Chef chef : chefsList) {
            chef.interrupt();
            chef.join();
        }

        for (Customer customer : customersList) {
            customer.interrupt();
            customer.join();
        }

        for (Customer customer : customersList) {
            int howManySchabowy = 0;
            int howManyMielony = 0;
            int howManyBigos = 0;
            
            for (String meal : customer.getEatenMealsList()) {
                switch (meal) {
                    case "Schabowy":
                        howManySchabowy++;
                        break;
                    case "Mielony":
                        howManyMielony++;
                        break;
                    case "Bigos":
                        howManyBigos++;
                        break;
                }
            }
            
            int costOfSchabowy = 10 * howManySchabowy;
            int costOfMielony = 7 * howManyMielony;
            int costOfBigos = 5 * howManyBigos;
            int suma = costOfSchabowy + costOfMielony + costOfBigos;
            
            System.out.println(customer.getName() + " zjadł łącznie " + customer.getEatenMealsList().size() + " posiłków.");
            System.out.println("Schabowy...." + howManySchabowy + " x 10.00..." + costOfSchabowy);
            System.out.println("Mielony....." + howManyMielony + " x 7.00...." + costOfMielony);
            System.out.println("Bigos......." + howManyBigos + " x 5.00...." + costOfBigos);
            System.out.println("            SUMA PLN      " + suma + "\n");
        }
    }
}
