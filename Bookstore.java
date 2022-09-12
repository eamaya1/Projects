/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package amayaebertprojet1;

/**
 *
 * @author ebertamaya
 */
import java.util.ArrayList;
public class Bookstore implements BookstoreSpecification{

    /**
     * @param args the command line arguments
     */
      
    //Member Lists
    private static ArrayList<Members> basicMembers = new ArrayList<Members>();
    private static ArrayList<Members> premiumMembers = new ArrayList<Members>();
    
    //STOCK TRACKERS
    private static int booksInStock = 0;
    private static int CDInStock = 0;
    private static int DVDInStock = 0;
    
    ArrayList <Product> products = new ArrayList<Product>();

    
    public Bookstore()
    {
        
        Book book1;
        Book book2;
        products.add(book1 = new Book("To Kill a Mockingbird", 10.50, 1));
        booksInStock++;
        products.add(book2 = new Book("Diary of a Wimpy Kid", 7.50, 2));        
        booksInStock++;
        
        CD cd1;
        CD cd2;
        products.add(cd1 = new CD("PEMFBA", 5.99, 3));        
        CDInStock++;
        products.add(cd2 = new CD("The Beatles", 6.99, 4));        
        CDInStock++;

        DVD dvd1;
        DVD dvd2;
        products.add(dvd1 = new DVD("Avengers", 10.99, 5));
        DVDInStock++;
        products.add(dvd2 = new DVD("Star Wars", 12.99, 6));
        DVDInStock++; 
        
        
        Members basMember1;
        Members basMember2;      
        basicMembers.add(basMember1 = new Members("James"));
        basicMembers.add(basMember2 = new Members("Miles"));
        
        Members premMember1;
        Members premMember2;
        premiumMembers.add(premMember1 = new Members("Kemba"));
        premiumMembers.add(premMember2 = new Members("LaFrance"));
        
    }
    
    //******************
    //***PRODUCT METHODS
    //******************
    
    public ArrayList getProducts(){
        return products;
    }
    
    public ArrayList getBooks(){
        ArrayList<Book> books = new ArrayList();
        for(Product p : products){
            if(p instanceof Book){
                books.add((Book)p);
            }
        }
        return books;
    }
    
    public ArrayList getCDs(){
        ArrayList<CD> cds = new ArrayList();
        for(Product p : products){
            if(p instanceof CD){
                cds.add(((CD)p));
            }
        }
        return cds;
    }
    
    public ArrayList getDVDs(){
        ArrayList<DVD> dvds = new ArrayList();
        for(Product p : products){
            if(p instanceof DVD){
                dvds.add(((DVD)p));
            }
        }
        return dvds;
    }
    
    public int getId(int index){
        return products.get(index).getId();
    }
    
    //******************
    //***BOOK METHODS***
    //******************
    public void addBook(String initTitle, double initPrice, int id) {
        Book newBook;
        products.add(newBook = new Book(initTitle, initPrice, id));
        booksInStock++;
    }

    public static int getBooksInStock() {
        return booksInStock;
    }
    
    //****************
    //***CD METHODS***
    //****************
    public void addCD(String initTitle, double initPrice, int id)
    {
        CD newCD;
        products.add(newCD = new CD(initTitle, initPrice, id));
        CDInStock++;
    }
    
    public static int getCDsInStock() {
        return CDInStock;
    }
    
    

    //***************
    //*DVD METHODS***
    //***************
    public void addDVD(String initTitle, double initPrice, int id)
    {
        DVD newDVD;
        products.add(newDVD = new DVD(initTitle, initPrice, id));
        DVDInStock++;
    }
    
    public static int getDVDInStock()
    {
        return DVDInStock;
    }
    
    //************************
    //***MEMBERSHIP METHODS***
    //************************
    
    public ArrayList getBasicMemList()
    {
        return basicMembers;
    }
    
    public String getBasicMemName(int index)
    {
        return basicMembers.get(index).getBasName();
    }
    
    public ArrayList getPremMemList()
    {
        return premiumMembers;
    }
    
    public String getPremMemName(int index)
    {
        return premiumMembers.get(index).getPremName();
    }
    
    //***************************
    //***INTERFACE IMPLEMENTATION
    //***************************

    @Override
    public int restockProduct(int productId, int amount) {
        for(int i = 0; i < 5; i++){
            if(products.get(i).getId() == productId){
                for(int j = 0; j < amount; j++){
                products.add(products.get(i));
                }
            }
        }
        return 1;
    }

    @Override
    public double inventoryValue() {
        double inValue = 0;
        for(Product p : products){
            inValue += p.getPrice();
        }
        inValue = inValue * 100;
        inValue = (double)((int) inValue);
        inValue = inValue /100;
        
        
        return inValue;
    }
    
    
}
