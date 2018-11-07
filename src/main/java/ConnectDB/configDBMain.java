package ConnectDB;

import Model.Income;

import java.sql.Connection;
import java.util.ArrayList;

public class configDBMain {
    public static void main(String[] args) {
        SQLConnect db = new SQLConnect();
        Connection connection = db.openDatabase();
        ListControl addDBControl = new ListControl(connection);



//        Income r1 = new Income("Income","Miniso",9000);
//        r1.setID(8);
//        System.out.println(addDBControl.updateList(r1));
//
//        SQLConnect db1 = new SQLConnect();
//        Connection connection1 = db1.openDatabase();
//        ListControl addDBControl1 = new ListControl(connection1);
//        System.out.println(addDBControl1.getLastID());

//        ArrayList<Room> roomList = rcb.readRoom();
//        for (Room i:roomList) {
//            System.out.println(i);
//
//        }


        /////////////////////////////////////////////////////// เอาไว้อ่านข้อมูลจาก database

//        ArrayList<Income> userList = addDBControl.readAccount();
//        for (Income i: userList) {
//            System.out.println(i);
//        }
    }


}
