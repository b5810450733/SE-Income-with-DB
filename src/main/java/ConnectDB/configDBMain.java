package ConnectDB;

import Model.Income;

import java.sql.Connection;
import java.util.ArrayList;

public class configDBMain {
    public static void main(String[] args) {
        SQLConnect db = new SQLConnect();
        Connection connection = db.openDatabase();
        ListControl addDBControl = new ListControl(connection);

//        Income r1 = new Income("Income","Bag",1500);
//        System.out.println(addDBControl.addList(r1));
//        ArrayList<Room> roomList = rcb.readRoom();
//        for (Room i:roomList) {
//            System.out.println(i);
//
//        }

//        User user = new User("000","admin","Admin",
//                "Superadmin","8888888888","adminHR@hr.com","adminadmin");
//        System.out.println(userDBControl.addUser(user));

        /////////////////////////////////////////////////////// เอาไว้อ่านข้อมูลจาก database

        ArrayList<Income> userList = addDBControl.readAccount();
        for (Income i: userList) {
            System.out.println(i);
        }
    }


}
