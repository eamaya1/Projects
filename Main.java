/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package amayaebertprojet1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
/**
 *
 * @author ebertamaya
 */
public class Main {

    public static double cartTotal = 0.0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
  
        Scanner sc = new Scanner(System.in);
        
        //CREATING THE BOOKSTORE ITSELF WITH PRESET PRODUCTS
        Bookstore bookStore = new Bookstore();
  
        //LOOP CONDITIONALS
        boolean exit = false;
        boolean purchaseExit = false;
        double cartTotal = 0;
        
        //ARRAYLISTS FOR INVENTORY AND CART
        ArrayList <Product> cart = new ArrayList<Product>();
        ArrayList <Product> inventory = bookStore.getProducts();
        
        while(exit != true)
        {
            //WELCOMING GUEST TO BOOKSTORE PANEL
            System.out.println("***************************************");
            System.out.println("Welcome to the online bookstore! ");
            System.out.println("Select from the following options:");
            System.out.println("Enter 1 to make a purchase.");
            System.out.println("Enter 2 to create a basic membership.");
            System.out.println("Enter 3 to create a premium membership.");
            System.out.println("Enter 4 to see your total.");
            System.out.println("Enter 5 to make a payment.");
            System.out.println("Enter 6 to see the items in your cart.");
            System.out.println("Enter 7 to sort items from cheapest to most expensive.");
            System.out.println();
            
            System.out.println("MANAGER FUNCTIONS");
            System.out.println("Enter 8 to restock product");
            System.out.println("Enter 9 to see inventory value");
            System.out.println("Enter 10 to exit.");
            System.out.println("***************************************");
            
            //VARIABLE FOR USER SELECTION
            int num = sc.nextInt();
        
        switch (num) {
            case 1:
                while(purchaseExit != true)
                {
                    //PURCHASE MENU: PRINTS OUT INVENTORY AND GIVES OPTIONS BASED ON ID OF PRODUCT
                    System.out.println("Here is our inventory:");
                    
                    for(Product p : inventory){
                        System.out.println(p.getTitle() + " $" + p.getPrice() + " ID NUM: " + p.getId());
                    }

                    System.out.println("Enter which you'd like to purchase by id or enter -1 to see cart and exit" );
                    int input = sc.nextInt();
                    

                    //ADDS OBJECT TO CART BASED ON ID SELECTED
                    for(int k = 0; k < inventory.size(); k++){
                        if(input == inventory.get(k).getId()){
                            cart.add(inventory.get(k));
                            inventory.remove(k);
                        }
                    }
                    //EXIT INPUT
                    if(input == -1){                       
                        System.out.println("");
                        System.out.println("Here are the items in your cart!");
                        for(Product p : cart){
                            System.out.println(p.getTitle());
                        }
                        
                        for(Product p : cart){
                            cartTotal += p.getPrice();
                        }
                        cartTotal = cartTotal * 100;
                        cartTotal = (double)((int) cartTotal);
                        cartTotal = cartTotal /100;
                        System.out.println("Your cart total is $" + cartTotal);
                            purchaseExit = true;
                        }
                        Thread.sleep(3000);
                    }
                  
                    break;    
                    
            case 2:   
                System.out.println("Okay lets create your basic membership");
                System.out.println("Please enter your name");
                String memberName = sc.next();
                System.out.println("Great! Thank you for joining our bookstores base membership!");
                
                Members newMem;
                Thread.sleep(2000);
                bookStore.getBasicMemList().add(new Members(memberName));
                System.out.println("You have been added to our basic free membership! See your name here:");
                for(int i = 0; i < bookStore.getBasicMemList().size(); i++)
                   {
                       System.out.println((i + 1) + ". " + bookStore.getBasicMemName(i));
                   }
                Thread.sleep(3000);
                break;
                
            case 3:
                System.out.println("Okay lets make your premium membership.");
                System.out.println("The premium membership comes with a $5 fee which will be added to your cart total.");
                System.out.println("Please enter your name.");
                
                String premMemberName = sc.next();
                
                Members newPremMem;
                bookStore.getPremMemList().add(new Members(premMemberName));
                System.out.println("You have been added to our premium membership! See your name here:");
                for(int i = 0; i < bookStore.getPremMemList().size(); i++)
                {
                    System.out.println((i + 1) + ". " + bookStore.getPremMemName(i));
                }
                
                cartTotal += 5.0;
                System.out.println("$5 will be added to your cart total.");
                Thread.sleep(3000);
                break;
        
            case 4:
                cartTotal = cartTotal * 100;
                cartTotal = (double)((int) cartTotal);
                cartTotal = cartTotal /100;
                System.out.println("Your total is: $" + cartTotal);
                Thread.sleep(3000);
                break;
        
            case 5:
                System.out.println("You owe $" + cartTotal);
                System.out.println("How much would you like to pay?");
                double payment = sc.nextDouble();
                cartTotal = cartTotal - payment;
                System.out.println("Your new total is " + cartTotal);
                break;
        
            case 6:
                System.out.println("Here are the items in your cart:");
                for(Product p : cart){
                    p.display();
                }
                Thread.sleep(3000);
                System.out.println();
                break;
                
            case 7:
                System.out.println("Inventory listed through price");
                Collections.sort(inventory);
                for(Product p : inventory){
                    System.out.println(p.getTitle() + " " + p.getPrice());
                }
                break;           
              
            case 8:
                System.out.println("Enter the ID of the item that you'd like to restock.");
                int restockId = sc.nextInt();
                
                System.out.println("Enter the amount you'd like to restock");
                int restockAmount = sc.nextInt();
                
                System.out.println(bookStore.restockProduct(restockId, restockAmount));
                System.out.println("Items successfully restocked! See here:");
                
                for(Product p : inventory){
                    p.display();
                }
                break;
       
            case 9:
                System.out.println("Inventory Value placed at $" + bookStore.inventoryValue());
                Thread.sleep(2000);
                break;
                
            case 10:
                if(cartTotal == 0){
                    System.out.println("Thank you for shopping with us! Have a good day!");
                    exit = true;
                }
                else{
                    System.out.println("Please enter 5 so that you can pay for the items.");
                    Thread.sleep(5000);                  
                }
                break;
            }
        }
        
    }
}   


