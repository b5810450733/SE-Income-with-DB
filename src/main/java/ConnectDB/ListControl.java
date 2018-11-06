package ConnectDB;

import Model.Income;

import java.sql.*;
import java.util.ArrayList;

public class ListControl {
    private Connection connection;
    private ResultSet resultSet = null;
    private Statement stmt = null;

    public ListControl(Connection connection) {
        this.connection = connection;
    }

    public boolean addList(Income list){
        boolean addResult = false;
        try {
            Income newList = list;
            String sqlText = "INSERT INTO Income VALUES (?,?,?)";
            PreparedStatement prepare = connection.prepareStatement(sqlText);
            prepare.setString(1,newList.getType());
            prepare.setString(2,newList.getInformation());
            prepare.setString(3,newList.getAmount());

            if (prepare.executeUpdate() == 1){
                addResult = true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            SQLConnect.closeAllConfigure(resultSet,stmt,connection);
        }
        return addResult;
    }

    public ArrayList<Income> readAccount(){ // Review User //
        ArrayList<Income> incomeArray = new ArrayList<>();
        Income inFlow = null;
        try{
            stmt = connection.createStatement();
            String query = "SELECT * FROM Income";
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                inFlow = new Income(resultSet.getString(1)
                        , resultSet.getString(2)
                        , Double.parseDouble(resultSet.getString(3)));

                incomeArray.add(inFlow);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            SQLConnect.closeAllConfigure(resultSet,stmt,connection);
        }
        return incomeArray;
    }

}
