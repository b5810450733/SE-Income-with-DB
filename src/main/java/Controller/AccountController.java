package Controller;

import ConnectDB.ListControl;
import ConnectDB.SQLConnect;
import Model.Amount;
import Model.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AccountController implements Showlist{
    @FXML
    TableColumn<Income, String> typetable;

    @FXML
    TableColumn<Income, String> informtable;

    @FXML
    TableColumn<Income, String> amounttable;

    @FXML
    TableView<Income> allTable;

    @FXML
    private ComboBox typecolumn;

    @FXML
    private TextField amountField;

    @FXML
    private Button addbt;

    @FXML
    private Button modbt,delBtn;

    @FXML
    private TextField commentF;

    @FXML
    private Label totalbalance;

    private ObservableList<String> typ = FXCollections.observableArrayList("Income","Expense");
    private ObservableList<Income> data =FXCollections.observableArrayList();
    private Income in;
    private Amount newAmount = new Amount();

    @FXML
    public void initialize(){
        showAlllist();
        //allTable.setEditable(true);
        typetable.setCellFactory(TextFieldTableCell.forTableColumn());
        informtable.setCellFactory(TextFieldTableCell.forTableColumn());
        amounttable.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void setAmountfield(){
        totalbalance.setStyle("-fx-text-fill: green");
        totalbalance.setText(newAmount.getTotalAmount()+"");
    }

    @FXML
    public void handleModbtn(ActionEvent event){
        int countMod = 1;
        Income modIncome = allTable.getSelectionModel().getSelectedItem();
        if (event.getSource().equals(modbt) && countMod == 1){
            addbt.setDisable(true);
            delBtn.setDisable(true);
            typecolumn.setValue(allTable.getSelectionModel().getSelectedItem().getType());
            commentF.setText(allTable.getSelectionModel().getSelectedItem().getInformation());
            amountField.setText(allTable.getSelectionModel().getSelectedItem().getAmount());
            modbt.setText("Save");
            countMod++;
        }if (event.getSource().equals(modbt) && countMod == 2){
            try {
//                modIncome.setType(typecolumn.getValue().toString());
//                modIncome.setAmount(amountField.getText());
//                modIncome.setInformation(commentF.getText());
                Income demo = new Income(typecolumn.getValue().toString(),commentF.getText(),Double.parseDouble(amountField.getText()));
                for (int i = 0; i < data.size() ; i++) {
                    if (data.get(i).equals(allTable.getSelectionModel().getSelectedItem())){
                        data.set(i,demo);
                    }
                }
                System.out.println(demo);
                System.out.println(countMod);
                System.out.println("Complete");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void showList() throws NullPointerException{
        SQLConnect db = new SQLConnect();
        Connection connection = db.openDatabase();
        ListControl dbControl = new ListControl(connection);
        ArrayList<Income> incomeList = dbControl.readAccount();
        for (Income i :incomeList) {
            System.out.print(i);
        }
        for (int i = 0; i < incomeList.size() ; i++) {
            in = new Income(incomeList.get(i).getType(),incomeList.get(i).getInformation(),Double.parseDouble(incomeList.get(i).getAmount()));
            if (in.getType().equals("Income")) {
                newAmount.setTotalAmount(1,Double.parseDouble(in.getAmount()));
            }if (in.getType().equals("Expense")){
                newAmount.setTotalAmount(0,Double.parseDouble(in.getAmount()));
            }
            data.add(in);
        }
        totalbalance.setStyle("-fx-text-fill: green");
        totalbalance.setText(newAmount.getTotalAmount()+"");

    }

    @FXML
    public void showAlllist(){
        typecolumn.setValue("Choose Type");
        typecolumn.setItems(typ);
        ///
        typetable.setCellValueFactory(cellData->cellData.getValue().typeProperty());
        informtable.setCellValueFactory(cellData->cellData.getValue().informationProperty());
        amounttable.setCellValueFactory(cellData->cellData.getValue().amountProperty());
        allTable.setItems(data);
        totalbalance.setText(newAmount.getTotalAmount()+"");//show the last input of column
        try {
            showList();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void handleAddbtn(ActionEvent event){
        Button b = (Button) event.getSource();
        if (b.equals(addbt)){
            SQLConnect db = new SQLConnect();
            Connection connection = db.openDatabase();
            ListControl dbControl = new ListControl(connection);
            String type = typecolumn.getValue().toString();
            try{
                if (!type.equals("Choose Type")){
                    if (type.equals("Income")){
                        newAmount.setTotalAmount(1,Double.parseDouble(amountField.getText()));
                    }
                    if (type.equals("Expense")){
                        newAmount.setTotalAmount(0,Double.parseDouble(amountField.getText()));
                    }
                    Income in = new Income(type,commentF.getText(),Double.parseDouble(amountField.getText()));
                    dbControl.addList(in);
                    data.add(in);
                    amountField.clear();
                    commentF.clear();
                    setAmountfield();
                }else {
                    amountField.setText("Please choose type.");
                }
            }catch (NumberFormatException e){
                System.out.println("Please re-check the input.");
            }
        }
    }




}
