/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amayaebertprojet1;

/**
 *
 * @author ebertamaya
 */
public abstract class Product implements Comparable<Product>{
    
    private String title;
    private double price;
    private int id;
    
    public double getPrice(){
        return price;
    }
    
    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public Product(String title, double price, int id){
        this.title = title;
        this.price = price;
        this.id = id;
    }
    
    public void display(){
        System.out.println("Title: " + title);
        System.out.println("Price " + price);
        System.out.println("ID: " + id);
    }
    
    @Override
    public int compareTo(Product o) {
        double fProductPrice = this.getPrice();
        double oProductPrice = o.getPrice();
        
        System.out.println("Product prices: " + fProductPrice + " " + oProductPrice);
        
        if(fProductPrice > oProductPrice){
            return 1;
        }
        else if(fProductPrice == oProductPrice){
            return 0;
        }
        
        return -1;
    }


}
    

