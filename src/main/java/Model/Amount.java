package Model;

public class Amount {
    private double totalAmount;

    public Amount() {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(int type,double totalAmount) {
        if (type == 1){
            addAmount(totalAmount);
        }if (type == 0){
            decreseAmount(totalAmount);
        }
    }

    public void addAmount(double i){
        this.totalAmount += i;
    }

    public void decreseAmount(double i){
        this.totalAmount -= i;
    }
}
