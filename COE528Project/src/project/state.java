/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 * @author AT
 */
public abstract class state {

    public abstract int buy(double totalCost, int points);

    public abstract int redeem(double totalCost);

    public abstract double getDiscount(double totalCost);
}