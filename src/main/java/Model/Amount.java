package Model;

public class Amount {
    private double totalAmount;
    private double totalExpense;
    private double lastExpense;

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
        if (i > totalAmount){
            lastExpense = totalAmount ;
            totalAmount = 0;
        }else {
            this.totalAmount -= i;
            this.totalExpense += i;
            lastExpense = i;
        }
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getLastExpense() {
        return lastExpense;
    }
}
