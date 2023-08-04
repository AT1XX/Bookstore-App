package project;//Set this to your package name


/**
 *
 * @author r29islam
 */
public class Customer {

    private String username;
    private String password;
    private int points;
    private state status;

    public Customer (String username, String password, int points){
        
        this.username = username;
        this.password = password;
        this.points = points;
    }

    public String getUsername(){
        
        return username;
    }

    public void setUsername(String username){
        
        this.username = username;
    }

    public String getPassword(){
        
        return password;
    }

    public void setPassword(String password){
        
        this.password = password;
    }

    public int getPoints(){
        
        return points;
    }
    public void setPoints(int points){
        this.points = points;
    }

    public String getStatus(int p){
        String statusString;
        if(p < 1000){
            
            status = new silver();
            statusString = "Silver";
            
        }else{
            
            status = new gold();
            statusString = "Gold";
        }
        return statusString;
    }

    public void setStatus(state status){
        this.status = status;
    }

    public int buy(double totalCost, int points){
        
        return status.buy(totalCost, points);
    }

     public double getDiscount(double totalCost) {
        
        return status.getDiscount(totalCost);
    }
     
    public int redeem(double totalCost){
        
        points = status.redeem(totalCost);
        return points;
    }

   


}