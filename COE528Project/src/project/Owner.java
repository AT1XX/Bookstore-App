/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

//******************ignore this class******************************

/*


/**
 *
 * @author Raidah Nazimuddin
 */
/*
public class Owner {
    
    private ArrayList<Customer> customerarrList;
    private ArrayList<Books> booksarrList;
    
    private static owner instance;
    
    private String username;
    private String password;

    public static owner getInstance() {
        if(instance == null){
            instance = new owner();
        }
        return instance;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String userName) {
        this.username = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String passWord) {
        this.password = passWord;
    }
    
    public void addBooks(String bookName, double bookPrice) {
        booksarrList.add(new Books(bookName, bookPrice));
    }
       
    public void deleteBooks(Books bo) {
        for(int i = 0; i < booksarrList.size(); i++){
            if(booksarrList.get(i).getBookName().equals(bo.getBookName())){
                booksarrList.remove(i);
            }
        }
    }
    
    public ArrayList<Books> getBooks() {
        ArrayList<Books> books = new ArrayList<Books>();
        for(int i = 0; i < booksarrList.size(); i++){
            books.add(booksarrList.get(i));
        }
        return books;
    }
    
    public void addCustomer(String username, String password) {
        customerarrList.add(new Customer(username, password));
    }
    
    public void deleteCustomer(Customer cu) {
        for(int i = 0; i < customerarrList.size(); i++){
              if(customerarrList.get(i).getUsername().equals(cu.getUsername())){
                  customerarrList.remove(i);
                }
            }
    }

    public static Customer Login(String username, String password) {
        Customer c = Customer.getInstance();
        if(username.equals("admin") && password.equals("admin")){
            return owner.getInstance();
        }
        for(int i = 0; i < c.getCustomers().size(); i++){
            if(c.getCustomer().get(i).getUsername().equals(username)){
                if(c.getCustomer().get(i).getPassword().equals(password)){
                    return c.getCustomer().get(i);
                }
            }
        }
        return null;
    }
    
    public static void Logout() {
        Customer cus = Customer.getInstance();
        ArrayList<Customer> tcus = cus.getCustomers();
        
        owner inv = owner.getInstance();
        ArrayList<Books> tbo = inv.getBooks();
        
        try{
            BufferedWriter cusW = new BufferedWriter(new FileWriter("customers.txt"));
            for(int i = 0; i < tcus.size(); i++){
                Customer c = tcus.get(i);
                cusW.write(c.getUsername() + "," + c.getPassword() + "," + c.getPoints()+"\n");
            }
            
            BufferedWriter boW = new BufferedWriter(new FileWriter("books.txt"));
            for(int i = 0; i < tbo.size(); i++){
                Books b = tbo.get(i);
                boW.write(b.getBookName() + "," + b.getBookPrice() + "\n");
            }
            
            cusW.close();
            boW.close();
        }
        catch(IOException e){};
        
        
    }
    
}*/