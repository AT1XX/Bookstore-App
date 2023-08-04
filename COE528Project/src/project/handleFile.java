/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.io.*;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*    
/**
 *
 * @author Raidah Nazimuddin
 */
/* 
public class handleFile {


    private String username;
    private String password;
    private int points;
    private String bookName;
    private double bookPrice;
    
    File file;
    FileReader out; 
    FileWriter in; 
    BufferedWriter write;
    BufferedReader read; 
    
    public ObservableList<Books> book = FXCollections.observableArrayList();
    public ObservableList<Customer> customers = FXCollections.observableArrayList();
    
   
    public void addCustomer(String username, String password, int points) {
        customers.add(new Customer(username, password, points));
        try { 
        PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter("customers.txt", true)));
        print.println(username + ", " + password + ", " + points);
        print.flush();
        print.close();
        }
        catch (IOException e) {  
          System.out.println(e);
        }
    }
    

    public void deleteCustomer(Customer customer) throws FileNotFoundException, IOException{
       
    }   
    
    public void addBook(String title, double price, int quantity) {
        book.add(new Books(title, price));
        try { 
            PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter("books.txt", true)));
            print.println(title + ", " + price);
            print.flush();
            print.close();
        }
        catch (IOException e) {  
          System.out.println(e);
        }
    
    }
    
    public ObservableList<Customer> readCustomers (){

        try{
            BufferedReader read = new BufferedReader(new FileReader("customers.txt"));
            
            String line = read.readLine();
            while (line != null) {
       
                String i[] = line.split(", ");
                username = i[0];
                password = i[1];
                points = Integer.parseInt(i[2]);
                customers.add(new Customer(username, password, points));
                
                line = read.readLine();
            }
            read.close();
            
        } catch (Exception e){
            System.out.println("Invalid data!");
        } 

        return customers;
    }
    
    
    
    
    public ObservableList<Books> readBook (){
        
        try{
            BufferedReader read = new BufferedReader(new FileReader("books.txt"));
            
            String line = read.readLine();
            while (line != null) {
                
                String i[] = line.split(", ");
                bookName = i[0];
                bookPrice = Double.parseDouble(i[1]);             
                book.add(new Books(bookName, bookPrice));
                 
                line = read.readLine();
            }
            read.close();
            
        } catch (Exception e){
            System.out.println("Invalid data!");
        } 

        return book;
    }
    
    
    
    public double purchaseBook(Books book) throws IOException
    {
        double total;
        total = book.getPrice();
        
        return total;
    }
    
     public void deleteBooks(String bookName, double bookPrice) throws IOException{         
        
        out = new FileReader("books.txt");
        read = new BufferedReader(out);
 
        String thisLine;
        String data = "";
        String newLine;
        while((thisLine = read.readLine()) != null){
            data += thisLine + "\r\n";
        }
        read.close();
        newLine = data.replaceAll(bookName, "Deleted");
        in = new FileWriter("books.txt");
        in.write(newLine);
        in.close();   
    }
    
  } */