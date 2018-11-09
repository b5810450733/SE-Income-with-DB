package Controller;

import ConnectDB.ListControl;
import ConnectDB.SQLConnect;
import Model.Amount;
import Model.Income;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class IndexController implements Showlist{
    @FXML
    public Label indexBalance;
    public Button enter;
    public Amount amount = new Amount();

    public Amount getAmount() {
        return amount;
    }

    public void initialize(){
        try {
            showList();
            setIndexBalance();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void handleEnter(ActionEvent event){
        if (event.getSource().equals(enter)){
            Stage stage = (Stage) enter.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Account.fxml")) ;

            try {
                stage.setScene(new Scene(loader.load(),640,480));
                stage.setTitle("Account Page");

                AccountController controller = (AccountController) loader.getController();

                stage.show();
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }

    }

    public void setIndexBalance(){
        indexBalance.setText(String.valueOf(amount.getTotalAmount()));
    }

    @Override
    public void showList() throws NullPointerException{
        SQLConnect db = new SQLConnect();
        Connection connection = db.openDatabase();
        ListControl dbControl = new ListControl(connection);
        ArrayList<Income> incomeList = dbControl.readAccount();
        Income in;
        for (int i = 0; i < incomeList.size() ; i++) {
            in = new Income(incomeList.get(i).getType(),incomeList.get(i).getInformation(),Double.parseDouble(incomeList.get(i).getAmount()));
            if (in.getType().equals("Income")) {
                amount.setTotalAmount(1,Double.parseDouble(in.getAmount()));
            }if (in.getType().equals("Expense")){
                amount.setTotalAmount(0,Double.parseDouble(in.getAmount()));
            }
        }
    }
}
