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
        Income modIncome = allTable.getSelectionModel().getSelectedItem();
        if (event.getSource().equals(modbt) && modbt.getText().equals("MODIFY")){
            addbt.setDisable(true);
            delBtn.setDisable(true);
            typecolumn.setValue(allTable.getSelectionModel().getSelectedItem().getType());
            commentF.setText(allTable.getSelectionModel().getSelectedItem().getInformation());
            amountField.setText(allTable.getSelectionModel().getSelectedItem().getAmount());
            modbt.setText("Save");
        }
        else if (event.getSource().equals(modbt) && modbt.getText().equals("Save")){
            try {
                SQLConnect db = new SQLConnect();
                Connection connection = db.openDatabase();
                ListControl dbControl = new ListControl(connection);
                modIncome.setType(typecolumn.getValue().toString());

                if (modIncome.getType().equals("Income")){
                    newAmount.setTotalAmount(0,Double.parseDouble(modIncome.getAmount()));
                    newAmount.setTotalAmount(1,Double.parseDouble(amountField.getText()));
                }
                if (modIncome.getType().equals("Expense")){
                    newAmount.setTotalAmount(1,Double.parseDouble(modIncome.getAmount()));
                    newAmount.setTotalAmount(0,Double.parseDouble(amountField.getText()));
                }
                modIncome.setAmount(amountField.getText());
                modIncome.setInformation(commentF.getText());
                System.out.println(dbControl.updateList(modIncome));
                setAmountfield();
                modbt.setText("MODIFY");
                addbt.setDisable(false);
                delBtn.setDisable(false);
                commentF.clear();
                amountField.clear();
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
            in = new Income(incomeList.get(i).getType(),incomeList.get(i).getInformation(),Double.parseDouble(incomeList.get(i).getAmount())); // แก้ตรงนี้ด้วย
            in.setID(incomeList.get(i).getID());
            if (in.getType().equals("Income")) {
                newAmount.setTotalAmount(1,Double.parseDouble(in.getAmount()));
            }if (in.getType().equals("Expense")){
                newAmount.setTotalAmount(0,Double.parseDouble(in.getAmount()));
            }
            data.add(in);
        }
        setAmountfield();
//        totalbalance.setStyle("-fx-text-fill: green");
//        totalbalance.setText(newAmount.getTotalAmount()+"");

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
                    SQLConnect db1 = new SQLConnect();
                    Connection connection1 = db1.openDatabase();
                    ListControl addDBControl1 = new ListControl(connection1);

                    Income in = new Income(type,commentF.getText(),Double.parseDouble(amountField.getText()));
                    dbControl.addList(in);
                    int newId = addDBControl1.getLastID();
                    in.setID(newId);
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
