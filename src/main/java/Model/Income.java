package Model;

import javafx.beans.property.SimpleStringProperty;

public class Income {
    public SimpleStringProperty amount; // รวม
    public SimpleStringProperty type; // ประเภท
    public SimpleStringProperty information; // ข้อมูล

    public Income(String type, String comment, double income) {
        this.type = new SimpleStringProperty(type);
        this.information = new SimpleStringProperty(comment);
        this.amount = new SimpleStringProperty(String.valueOf(income));
    }

    public Income() {
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getInformation() {
        return information.get();
    }

    public SimpleStringProperty informationProperty() {
        return information;
    }

    public void setInformation(String information) {
        this.information.set(information);
    }

    @Override
    public String toString() {
        return "Type = "+getType()+"Comment = "+getInformation()+"Amount = "+getAmount();
    }
}
