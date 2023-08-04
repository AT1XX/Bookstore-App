/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.scene.control.CheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
/**
 *
 * @author Karishab
 */
public class Books {
    private String bookName;
    private double price;
    private CheckBox select;

    public Books(String bookName, double price) {
        this.bookName = bookName;
        this.price = price;
        this.select = new CheckBox();
    }

    
    public String getBookName() {
        return this.bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public CheckBox getSelect() {
        return this.select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}