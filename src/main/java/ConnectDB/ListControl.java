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
            String sqlText = "INSERT INTO Income VALUES (?,?,?,?)";
            PreparedStatement prepare = connection.prepareStatement(sqlText);
            prepare.setString(2,newList.getType());
            prepare.setString(3,newList.getInformation());
            prepare.setString(4,newList.getAmount());

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
                inFlow = new Income(resultSet.getString(2)
                        , resultSet.getString(3)
                        , Double.parseDouble(resultSet.getString(4)));
                inFlow.setID(resultSet.getInt(1));

                incomeArray.add(inFlow);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            SQLConnect.closeAllConfigure(resultSet,stmt,connection);
        }
        return incomeArray;
    }

    public boolean updateList(Income list){
        boolean updateResult = false;
        try{
            String sqlText = "UPDATE Income SET type=?,info=?,value=? WHERE id=?";
            PreparedStatement prepare = connection.prepareStatement(sqlText);
            prepare.setString(1,list.getType());
            prepare.setString(2,list.getInformation());
            prepare.setDouble(3,Double.parseDouble(list.getAmount()));
            prepare.setInt(4,list.getID());

            if (prepare.executeUpdate() == 1){
                updateResult = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            SQLConnect.closeAllConfigure(resultSet,stmt,connection);
        }
        return updateResult;
    }

    public int getLastID(){
        int lastID = 0;
        try{
            stmt = connection.createStatement();
            String query = "SELECT * FROM Income ORDER BY id DESC LIMIT 1;";
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                lastID = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            SQLConnect.closeAllConfigure(resultSet,stmt,connection);
        }
        return lastID;

    }

}
