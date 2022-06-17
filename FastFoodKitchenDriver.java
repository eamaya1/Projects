/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ebertamaya
 */
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;

public class FastFoodKitchenDriver{


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

        ArrayList<BurgerOrder> completedOrders = new ArrayList<BurgerOrder>();
        ArrayList<BurgerOrder> cancelledOrders = new ArrayList<BurgerOrder>();
        ArrayList <BurgerOrder> placedOrders = new ArrayList<BurgerOrder>();
        
        int hamSold = 0;
        int cheeseBurSold = 0;
        int vegBurSold = 0;
        int sodaSold = 0;
        
        FastFoodKitchen kitchen = new FastFoodKitchen(); //Creating kitchen. Orders are preloaded through external file.
        
        Scanner sc = new Scanner(System.in);
        FileOutputStream fs = new FileOutputStream("CancelledOrders.txt"); //Cancelled orders file 
        FileOutputStream fos = new FileOutputStream("CompletedOrders.txt"); //Completed orders file
        FileOutputStream pos = new FileOutputStream("EndOfDay.txt"); //End of day report file
        FileOutputStream burOrdTwo = new FileOutputStream("burgerOrders2.csv"); //csv writer
        
        PrintWriter outFS; // Prints to cancelled order file
        outFS = new PrintWriter(fs, true); //PrintWriter takes output stream and appends it 
        outFS.println("----CANCELLED ORDERS LIST----");
        
        
        PrintWriter oFS; // Prints to completed order file
        oFS = new PrintWriter(fos, true);
        oFS.println("----COMPLETED ORDER LIST----");
        
        PrintWriter endOfDay; //Prints to end of day file
        PrintWriter burOrdTwoWriter; //Prints to csv file at end of day
        
        burOrdTwoWriter = new PrintWriter(burOrdTwo, true);
        burOrdTwoWriter.println("OrderID,numHamburgers,numCheeseburgers,numVeggieburgers,numSodas,toGo");
        
        boolean ready; //Variable for menu selections
        int order; //Loop variable
        
        while (kitchen.getNumOrdersPending() != 0) {
            // see what the user wants to do
            System.out.println("Please select from the following menu of options, by typing a number:");
            System.out.println("\t 1. Order food");
            System.out.println("\t 2. Cancel an order");
            System.out.println("\t 3. Show number of orders currently pending");
            System.out.println("\t 4. Complete order");
            System.out.println("\t 5. Check on order");
            System.out.println("\t 6. To display your order");
            System.out.println("\t 7. Exit and finalize day");
            System.out.println("\t 8. Check remaining orders to be made");

            int num = sc.nextInt();
            switch (num) {
                case 1:
                    try{
                        System.out.println("How many hamburgers do you want?");
                        int ham = sc.nextInt();
                        System.out.println("How many cheeseburgers do you want?");
                        int cheese = sc.nextInt();
                        System.out.println("How many veggieburgers do you want?");
                        int veggie = sc.nextInt();
                        System.out.println("How many sodas do you want?");
                        int sodas = sc.nextInt();
                        System.out.println("Is your order to go? (Y/N)");
                        char letter = sc.next().charAt(0);
                        boolean TOGO = false;
                        if (letter == 'Y' || letter == 'y') {
                            TOGO = true;
                        }
                        int orderNum = kitchen.addOrder(ham, cheese, veggie, sodas, TOGO);
                        placedOrders.add(kitchen.getLastOrder());
                        System.out.println("Thank you. Your order number is " + orderNum);

                        System.out.println();
                        break;
                    } catch(InputMismatchException ex) {
                        System.out.println("Invalid entry, please try again.");
                    }
                case 2:                   
                    //outFS = new PrintWriter(fs, true); //PrintWriter takes output stream and appends it 
                    
                    System.out.println("Enter the Order Number of the order you'd like to cancel or -1 to exit.");
                    int choice; //Variable for order number of order to be cancelled or -1 to exit selection                      
                    
                    outFS.println("----CANCELLED ORDERS LIST----");
                    try{
                        choice = sc.nextInt();
                        
                        if(choice > 0){ //Cancelling
                        outFS.println("Order ID: " + kitchen.getOrder(choice).getOrderNum());
                        outFS.println("Num of Hamburgers: " + kitchen.getOrder(choice).getNumHamburger());
                        outFS.println("Num of Cheeseburgers: " + kitchen.getOrder(choice).getNumCheeseburgers());
                        outFS.println("Num of Veggieburgers: " + kitchen.getOrder(choice).getNumVeggieburgers());
                        outFS.println("Num of Sodas: " + kitchen.getOrder(choice).getNumSodas());
                        
                        cancelledOrders.add(kitchen.getOrder(choice)); //Adding cancelled order to list to print out in finalized day
                        
                        kitchen.cancelOrder(choice); //Actually cancelling the order 
                        System.out.println("Thank you, The order has been cancelled.");
                        System.out.println();
  
                        } 
                        if(choice == -1) { //Exiting
                            System.out.println("Exit Successful.");
                            break;
                        }
                    } catch(InputMismatchException ex) {
                        System.out.println("Invalid entry entered. Please enter an integer ");
                        sc.next();
                        break;
                    }  catch(IndexOutOfBoundsException ex) {
                        System.out.println("Order number isn't in order list. Try again");
                        sc.next();
                        break;
                    }   catch(Exception ex) {
                        System.out.println("Problem with order. Try again.");
                        sc.next();
                        break;
                    }     

                case 3:
                    System.out.println("There are " + kitchen.getNumOrdersPending() + " pending orders");
                    break;
                case 4:
                    System.out.println("Enter order number to complete or -1 to exit");
                   // oFS = new PrintWriter(fos, true);
                   // oFS.println("----COMPLETED ORDER LIST----");
                    
                    try { //Error checking                        
                        order = sc.nextInt();
                        if(order > 0){
                            oFS.println("Order ID: " + kitchen.getOrderList().get(order - 1).getOrderNum());
                            oFS.println("Num of Hamburgers: " + kitchen.getLastOrder().getNumHamburger());
                            oFS.println("Num of Cheeseburgers: " + kitchen.getLastOrder().getNumCheeseburgers());
                            oFS.println("Num of Veggieburgers: " + kitchen.getLastOrder().getNumVeggieburgers());
                            oFS.println("Num of Sodas: " + kitchen.getLastOrder().getNumSodas());
                            
                            
                            completedOrders.add(kitchen.getOrder(order));
                            hamSold += kitchen.getOrder(order).getNumHamburger();
                            cheeseBurSold += kitchen.getOrder(order).getNumCheeseburgers();
                            vegBurSold += kitchen.getOrder(order).getNumVeggieburgers();
                            sodaSold += kitchen.getOrder(order).getNumSodas();
                            
                            kitchen.completeSpecificOrder(order);
                            break;
                        }
                        if(order == -1){
                            System.out.println("Exit successful.");
                            break;
                        }
                        
                    } catch(InputMismatchException ex) {
                        System.out.println("Did not enter a valid integer to check order number. Try again.");
                        sc.next();
                        break;
                    } catch(IndexOutOfBoundsException ex) {
                        System.out.println("Invalid order number. Try again.");
                        sc.next();
                        break;
                    }

                case 5:                    
                    try{
                        System.out.println("What is your order number?");
                        order = sc.nextInt();
                        ready = kitchen.isOrderDone(order);
                        if (ready) {
                            System.out.println("Sorry, no order with this number was found.");
                            Thread.sleep(2000);
                        } else {
                            System.out.println("No, it's not ready, but it should be up soon. Sorry for the wait.");
                            Thread.sleep(2000);
                        }
                        System.out.println();
                        break;
                    } catch(InputMismatchException ex) {
                        System.out.println("Error. Please enter an integer number.");
                        sc.next();
                        break;
                    }
                case 6:
                    try{
                        System.out.println("Enter order number to see total order");
                        choice = sc.nextInt();
                        kitchen.orderCallOut(kitchen.getOrderList().get(choice - 1));
                        break;
                    } catch(InputMismatchException ex) {
                        System.out.println("Invalid entry entered. Please enter an integer number to check.");
                        sc.next();
                        break;
                    } catch(IndexOutOfBoundsException ex) {
                        System.out.println("Order number doesn't exist.");
                        sc.next();
                        break;
                    }                    
                case 7:
                    endOfDay = new PrintWriter(pos, true); //Writes to endofday.txt
                    endOfDay.println("****END OF DAY REPORT****");
                    endOfDay.println();
                    
                    endOfDay.println("****PLACED ORDERS"); //These next lines will print placed orders taken from input 1 on the menu
                    endOfDay.println();
                    for(BurgerOrder b : placedOrders) {
                        endOfDay.println("\tOrder ID: " + b.getOrderNum() + "\n \tNumber of Hamburgers: " + b.getNumHamburger()
                            + "\n \tNumber of Cheeseburgers: " + b.getNumCheeseburgers()
                            + "\n \tNumber of Veggieburgers: " + b.getNumVeggieburgers()
                            + "\n \tNumber of Sodas: " + b.getNumSodas());
                        endOfDay.println();

                    }
                    
                    endOfDay.println("****CANCELLED ORDERS****"); //These next few lines of code will print the cancelled order details in end of day file
                    endOfDay.println();
                    for(BurgerOrder b : cancelledOrders) {
                        endOfDay.println("\tOrder ID: " + b.getOrderNum() + "\n \tNumber of Hamburgers: " + b.getNumHamburger()
                            + "\n \tNumber of Cheeseburgers: " + b.getNumCheeseburgers()
                            + "\n \tNumber of Veggieburgers: " + b.getNumVeggieburgers()
                            + "\n \tNumber of Sodas: " + b.getNumSodas());
                        endOfDay.println();
                    }
                    
                    endOfDay.println("****COMPLETED ORDERS****"); //These next few lines of code will print the completed order details in end of day file
                    endOfDay.println();
                    for(BurgerOrder b : completedOrders) {
                        endOfDay.println("\tOrder ID: " + b.getOrderNum() + "\n \tNumber of Hamburgers: " + b.getNumHamburger()
                            + "\n \tNumber of Cheeseburgers: " + b.getNumCheeseburgers()
                            + "\n \tNumber of Veggieburgers: " + b.getNumVeggieburgers()
                            + "\n \tNumber of Sodas: " + b.getNumSodas());
                        
                        endOfDay.println();
//                        endOfDay.println("STATS FOR TODAY");
//
//                        endOfDay.println("\tHamburgers sold: " + hamSold + "\n \tCheeseburgers sold: " + cheeseBurSold +
//                                "\n\tVeggieburgers sold: " +vegBurSold + "\n\tSodas sold: " + sodaSold);
//                        
                    }
                    
                    endOfDay.println("----STATS FOR TODAY----");
                    endOfDay.println("\tHamburgers sold: " + hamSold + "\n \tCheeseburgers sold: " + cheeseBurSold +
                                "\n\tVeggieburgers sold: " +vegBurSold + "\n\tSodas sold: " + sodaSold);


                    for(BurgerOrder b : kitchen.getOrderList()) {
                        burOrdTwoWriter.println(b.getOrderNum() + "," + b.getNumHamburger() + "," + b.getNumCheeseburgers() + ","
                        + b.getNumVeggieburgers() + "," + b.getNumSodas() + "," + b.isOrderToGo());
                    }
                    
                    System.exit(0);
                    break;
                default:
                    System.out.println("Sorry, but you need to enter a 1, 2, 3, 4, 5, 6, or a 7");
                    
                case 8:
                    System.out.println("Here are the remaining orders to be made");
                    choice = sc.nextInt();
                    kitchen.orderCallOut(kitchen.getOrderList().get(choice - 1));
                    break;


            } //end switch

        } //end while loop 
        
    } // end main
}// end class

