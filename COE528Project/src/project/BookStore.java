/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

/**
 *
 * @author AT
 */
public class BookStore extends Application {
    
    //Login things
    TextField tUsername = new TextField();
    TextField tPassword = new TextField();
    
    Label lWelcome = new Label("Welcome to the BookStore App");
    Label lUser = new Label("Username:");
    Label lPass = new Label("Password:");
    
    Button bLogin = new Button("Login");
    Label error = new Label();
    Label inputError1 = new Label();
    
    //Owner things
    Button bBooks = new Button("Books");
    Button bCustomers = new Button("Customers");
    Button bOwnerLogout = new Button("Logout");
    Button bBookAdd = new Button("Add");
    Button bBookDelete = new Button("Delete");
    Button bBookBack = new Button("Back");
    Button bCustomerAdd = new Button("Add");
    Button bCustomerDelete = new Button("Delete");
    Button bCustomerBack = new Button("Back");
    //Labeling
    Label lBookName = new Label("Name:");
    Label lBookPrice = new Label("Price:");
    Label lCustomerUser = new Label("Username");
    Label lCustomerPass = new Label("Password:");
    //Text
    TextField tBookName = new TextField();
    TextField tBookPrice = new TextField();
    TextField tCustomerName = new TextField();
    TextField tCustomerPass = new TextField();
    
    //Customer buttons
    Button bCustomerBuy = new Button("Buy");
    Button bCustomerRedeemBuy = new Button("Redeem points & Buy");
    Button bLogout = new Button("Logout");
    //Label
    Label redeem = new Label();
    

    //Book and customer data
    ArrayList<Books> books = new ArrayList<Books>();
    String bookName;
    double bookPrice;
    ArrayList<Customer> customers = new ArrayList<Customer>();
    int points1;
    double totalCost;
    String username1;
    String password1;
    String status;
    

    

    private TableView<Customer> tableCustomer = new TableView<Customer>();
    private final ObservableList<Customer> customerData = FXCollections.observableArrayList();
    private TableView<Books> tableBook = new TableView<Books>();
    private TableView<Books> tableBookCheck = new TableView<Books>();
    private final ObservableList<Books> bookData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(new Scene(loginScreen(), 600, 300));
        primaryStage.show();
        try {
            BufferedReader read = new BufferedReader(new FileReader("customers.txt"));

            String line = read.readLine();
            while (line != null) {
                String i[] = line.split(", ");
                username1 = i[0];
                password1 = i[1];
                points1 = Integer.parseInt(i[2]);
                customers.add(new Customer(username1, password1, points1));
                
                line = read.readLine();
            }
            
            read.close();

        } 
        catch (Exception e) {
            System.out.println("Invalid error");
        }

        for (Customer c : customers) {
            customerData.add(c);
        }

        try {
            BufferedReader read = new BufferedReader(new FileReader("books.txt"));

            String line = read.readLine();
            while (line != null) {
                String i[] = line.split(", ");
                bookName = i[0];
                bookPrice = Double.parseDouble(i[1]);

                books.add(new Books(bookName, bookPrice));

                line = read.readLine();
            }
            
            read.close();

        } 
        catch (Exception e) {
            System.out.println("Invalid error");
        }

        for (Books b : books) {
            bookData.add(b);
        }

        tableBook.setEditable(true);
        TableColumn bookNameCol = new TableColumn("Book Name");
        TableColumn bookPriceCol = new TableColumn("Book Price");
        tableBook.getColumns().clear();
        tableBook.getColumns().addAll(bookNameCol, bookPriceCol);
        bookNameCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("bookName")
        );
        bookPriceCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("price")
        );
        tableBook.setItems(bookData);

        tableCustomer.setEditable(true);
        TableColumn customerNameCol = new TableColumn("Username");
        TableColumn customerPassCol = new TableColumn("Password");
        TableColumn customerPointsCol = new TableColumn("Points");

        customerNameCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("username")
        );
        customerPassCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("password")
        );
        customerPointsCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("points")
        );
        
        tableCustomer.setItems(customerData);
        tableCustomer.getColumns().clear();
        tableCustomer.getColumns().addAll(customerNameCol, customerPassCol, customerPointsCol);

        tableBookCheck.setEditable(true);
        TableColumn bookNameCheckCol = new TableColumn("Book Name");
        TableColumn bookPriceCheckCol = new TableColumn("Book Price");
        TableColumn selectCol = new TableColumn("Select");
        tableBookCheck.getColumns().clear();
        tableBookCheck.getColumns().addAll(bookNameCheckCol, bookPriceCheckCol, selectCol);
        bookNameCheckCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("bookName")
        );
        bookPriceCheckCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("price")
        );
        selectCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("select")
        );
        tableBookCheck.setItems(bookData);


        bLogin.setOnAction(
                
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String user = tUsername.getText();
                String pass = tPassword.getText();
                totalCost = 0;
                int verification = 0;
                
                for (Customer c : customers) {
                    if (c.getUsername().equals(user) && c.getPassword().equals(pass)) {
                        username1 = c.getUsername();
                        password1 = c.getPassword();
                        points1 = c.getPoints();
                        status = c.getStatus(points1);
                        verification++;
                    }
                }
                if (user.equals("admin") && pass.equals("admin")) {
                    
                    //Owner 
                    primaryStage.setScene(new Scene(ownerStartScreen(), 600, 400));
                    
                    //Books Button
                    bBooks.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            primaryStage.setScene(new Scene(ownerBookScreen(), 700, 700));
                        }
                    }
                    );
                    //Books Add 
                    bBookAdd.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            
                         boolean t =  true;
                         try {
            
                            Double.parseDouble(tBookPrice.getText());
                            t =  true;                           
                        } 
                        catch (NumberFormatException j) {
            
                            t = false;
                        }
                            if (t) {
                                Books book = new Books(tBookName.getText(), Double.parseDouble(tBookPrice.getText()));
                                tableBook.getItems().add(book);
                                books.add(book);
                                tBookName.clear();
                                tBookPrice.clear();
                                inputError1.setText("");
                            }
                            else {
                                inputError1.setText("Invalid input or data!");
                            }
                        }
                    }
                    );
                    //Books Delete 
                    bBookDelete.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            ObservableList<Books> allTheBooks = FXCollections.observableArrayList();
                            allTheBooks = tableBook.getItems();
                            Books oBook = tableBook.getSelectionModel().getSelectedItem();
                            allTheBooks.remove(oBook);
                            ArrayList<Books> updateBooks = new ArrayList<Books>();
                            for (Books b : books) {
                                if (!(b.getPrice() == oBook.getPrice()) && !b.getBookName().equals(oBook.getBookName())) {
                                    updateBooks.add(b);
                                }
                            }
                            books = updateBooks;
                        }
                    }
                    );
                    //Book Back
                    bBookBack.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            primaryStage.setScene(new Scene(ownerStartScreen(), 600, 400));
                        }
                    }
                    );

                    //Customer Button
                    bCustomers.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            primaryStage.setScene(new Scene(ownerCustomerScreen(), 700, 700));
                        }
                    }
                    );
                    
                    //Customers Add 
                    bCustomerAdd.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            Customer customer = new Customer(tCustomerName.getText(), tCustomerPass.getText(), 0);
                            tableCustomer.getItems().add(customer);
                            customers.add(customer);
                            tCustomerName.clear();
                            tCustomerPass.clear();
                        }
                    }
                    );
                    
                    //Customer Delete 
                    bCustomerDelete.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            ObservableList<Customer> allCustomers;
                            allCustomers = tableCustomer.getItems();
                            Customer oneCustomer = tableCustomer.getSelectionModel().getSelectedItem();
                            allCustomers.remove(oneCustomer);

                            ArrayList<Customer> updateCustomers = new ArrayList<Customer>();
                            for (Customer c : customers) {
                                if (!c.getUsername().equals(oneCustomer.getUsername()) && !c.getPassword().equals(oneCustomer.getPassword()) && !(c.getPoints() == (oneCustomer.getPoints()))) {
                                    updateCustomers.add(c);
                                }
                            }

                            customers = updateCustomers;
                        }
                    }
                    );
                    
                    //Customers Back 
                    bCustomerBack.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            primaryStage.setScene(new Scene(ownerStartScreen(), 600, 400));
                        }
                    }
                    );
                    
                    //Logout 
                    bOwnerLogout.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            primaryStage.setScene(new Scene(loginScreen(), 600, 300));
                        }
                    }
                    );
                    
                } else if (verification == 1) {
                    username1 = tUsername.getText();
                    password1 = tPassword.getText();
                    
                    // Customer 
                    primaryStage.setScene(new Scene(customerStartScreen(), 700, 700));
                    
                    bCustomerRedeemBuy.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            ObservableList<Books> allBooks = FXCollections.observableArrayList();
                            for (Books book : bookData) {
                                if (book.getSelect().isSelected()) {
                                    allBooks.add(book);
                                    totalCost += book.getPrice();
                                }
                            }
                            if (points1 >= 100 && totalCost > 0) {
                                for (int i = 0; i < customers.size(); i++) {
                                    if (customers.get(i).getUsername().equals(username1)) {
                                        state s;
                                        if (status.equals("Gold")) {
                                            s = new gold();
                                        } 
                                        else {
                                            s = new silver();
                                        }
                                        customers.get(i).setStatus(s);
                                        totalCost = customers.get(i).getDiscount(totalCost);
                                        points1 = customers.get(i).buy(totalCost, points1);
                                        status = customers.get(i).getStatus(points1);
                                        customers.get(i).setPoints(points1);
                                    }
                                }
                                bookData.removeAll(allBooks);
                                books.removeAll(allBooks);
                                primaryStage.setScene(new Scene(checkout(), 400, 500));
                            } 
                            else if (points1 < 100) {
                                redeem.setText("100 points must be accumulated to redeem.");
                            } 
                            else {
                                redeem.setText("No books selected.");
                            }
                        }
                    }
                    );
                    bCustomerBuy.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            ObservableList<Books> allBooks = FXCollections.observableArrayList();
                            for (Books book : bookData) {
                                if (book.getSelect().isSelected()) {
                                    allBooks.add(book);
                                    totalCost += book.getPrice();
                                }
                            }
                            if (totalCost > 0) {
                                for (int i = 0; i < customers.size(); i++) {
                                    if (customers.get(i).getUsername().equals(username1)) {
                                        state s;
                                        if (status.equals("Gold")) {
                                            s = new gold();
                                        } 
                                        else {
                                            s = new silver();
                                        }
                                        customers.get(i).setStatus(s);
                                        points1 += customers.get(i).redeem(totalCost); 
                                        customers.get(i).setPoints(points1);
                                        status = customers.get(i).getStatus(points1);
                                    }
                                }
                                bookData.removeAll(allBooks);
                                books.removeAll(allBooks);
                                primaryStage.setScene(new Scene(checkout(), 400, 400));
                            } 
                            else {
                                redeem.setText("No books selected!");
                            }
                        }
                    }
                    );
                    bLogout.setOnAction(
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            primaryStage.setScene(new Scene(loginScreen(), 600, 300));
                        }
                    }
                    );
                } else {
                    error.setText("Incorect username or password / Field empty");
                }
            }
        }
        );
        
         primaryStage.setOnCloseRequest(
                new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                
                try {
                    File file = new File("Books.txt");//create txt book
                    FileWriter f = new FileWriter(file, false);
                    PrintWriter p = new PrintWriter(f);

                    for (Books b : books) {
                        p.print(b.getBookName() + ", " + b.getPrice() + "\n");
                    }
                    p.close();
                } 
                catch (IOException l) {
                    System.out.println("Error!");
                    l.printStackTrace();
                }
                try {
                    File file = new File("Customers.txt");//create txt customer
                    FileWriter f = new FileWriter(file, false);
                    PrintWriter p = new PrintWriter(f);

                    for (Customer c : customers) {
                        p.print(c.getUsername() + ", " + c.getPassword() + ", " + c.getPoints() + "\n");
                    }

                    p.close();
                } catch (IOException l) {
                    System.out.println("Error!");
                    l.printStackTrace();
                }
            }
        }
        );
        
        //--------for background image that ended up not working as intended
         /*
        BackgroundImage m = new BackgroundImage(
                img,
                BackgroundRepeat.NO_Repeat,
                BackgroundRepeat.NO_Repeat,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
       
        );
        
        Background bg = new Background(m);
        root.setBorder(bg);
        
        
        */
        //Group root = new Group();
        //root.getChildren().add(m);
        
        //Scene scene1 = new Scene(root, 800, 500);
        //primaryStage.setScene(scene1);
        //primaryStage.setResizable(false);
        //primaryStage.show();//*/
    }

    
     //------------------------------------------------------------------------------------------------------grid
    
    public GridPane loginScreen() {

        GridPane gl = new GridPane();
        error.setText("");  
        tUsername.clear();
        tPassword.clear();
        
        gl.setAlignment(Pos.CENTER);
        gl.setVgap(15);
        gl.add(lWelcome, 0, 0);
        gl.add(lUser, 0, 1);
        gl.add(lPass, 0, 2);
        gl.add(tUsername, 1, 1);
        gl.add(tPassword, 1, 2);
        gl.add(error, 1, 3);
        gl.add(bLogin, 1, 5);
        return gl;
    }

    public GridPane ownerStartScreen() {
        GridPane gos = new GridPane();
        
        gos.setAlignment(Pos.CENTER);
        gos.setVgap(15);
        gos.add(bBooks, 0, 0);
        gos.add(bCustomers, 0, 1);
        gos.add(bOwnerLogout, 0, 2);
        return gos;
    }

    public GridPane ownerBookScreen() {
        GridPane gob = new GridPane();
        
        tBookName.clear();
        tBookPrice.clear();
        inputError1.setText("");
        final VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(15, 0, 0, 15));
        gob.setHgap(5);
        vbox.getChildren().addAll(tableBook);
        
        gob.setAlignment(Pos.CENTER);
        gob.setVgap(5);
        
        gob.add(vbox, 2, 0);
        gob.add(lBookName, 1, 1); 
        gob.add(lBookPrice, 1, 2);
        gob.add(tBookName, 2, 1);
        gob.add(tBookPrice, 2, 2);
        gob.add(bBookAdd, 0, 3);
        gob.add(bBookDelete, 1, 3);
        gob.add(bBookBack, 2, 3);
        gob.add(inputError1, 0, 4);
        return gob;
    }

    public GridPane ownerCustomerScreen() {
        GridPane goc = new GridPane();
        tCustomerName.clear();
        tCustomerPass.clear();
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(15, 0, 0, 15));
        vbox.getChildren().addAll(tableCustomer);
        
        goc.setAlignment(Pos.CENTER); 
        goc.setVgap(15); 
        goc.setHgap(5);
        goc.add(vbox, 2, 0);
        goc.add(lCustomerUser, 1, 1);  
        goc.add(lCustomerPass, 1, 2);
        goc.add(tCustomerName, 2, 1);
        goc.add(tCustomerPass, 2, 2);
        goc.add(bCustomerAdd, 0, 4);
        goc.add(bCustomerDelete, 1, 4);
        goc.add(bCustomerBack, 2, 4);
        return goc;
    }

    public GridPane customerStartScreen() {
        GridPane gcs = new GridPane();
        redeem.setText("");
        gcs.setVgap(15);
        gcs.setHgap(5);
        gcs.setAlignment(Pos.CENTER);
        
        Label lCustomerWelcome = new Label("Welcome " + username1 + ", you have " + points1 + " points. Your status is: " + status); 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(15, 0, 0, 15));
        vbox.getChildren().addAll(tableBookCheck);
        
        gcs.add(vbox, 0, 1);
        gcs.add(lCustomerWelcome, 0, 0); 
        gcs.add(bCustomerBuy, 0, 2);
        gcs.add(bCustomerRedeemBuy, 0, 3);
        gcs.add(bLogout, 0, 4);
        gcs.add(redeem, 0, 5);
        return gcs;
    }

    public GridPane checkout() {
        
        GridPane gch = new GridPane();
        gch.setVgap(15);
        gch.setAlignment(Pos.CENTER);
        
        Label lCustomerCost = new Label("Total Cost: " + totalCost);
        Label lCustomerPoints = new Label("Points: " + points1);
        Label lCustomerStatus = new Label("Status: " + status);
        
        gch.add(lCustomerCost, 0, 0);
        gch.add(lCustomerPoints, 0, 1);
        gch.add(lCustomerStatus, 0, 2);
        gch.add(bLogout, 0, 3);
        return gch;
    }

    
     //-------------------------------------------------------------------------------------------------------main for launch
    public static void main(String[] args) {

        launch(args);
    }

}
