import ConnectDB.ListControl;
import ConnectDB.SQLConnect;
import Controller.IndexController;
import Model.Amount;
import Model.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

public class AccountTest {
    IndexController index;
    Income income,income2,income3;

    @BeforeEach
    void setUp() {
        index = new IndexController();
        income = new Income("Income","phone",20000);
        income2 = new Income("Income","pen",300);
        income3 = new Income("Expense","food",1000);
    }

    public ListControl openDB(){
        SQLConnect db = new SQLConnect();
        Connection connection = db.openDatabase();
        ListControl addDBControl = new ListControl(connection);
        return addDBControl;
    }

    @Test
    void addIncome(){
        assertTrue(openDB().addList(income));
        assertTrue(openDB().addList(income2));
        assertTrue(openDB().addList(income3));
    }

    @Test
    void getTotalamount(){
        index.showList();
        assertEquals(572300,index.getAmount().getTotalAmount());
    }
}
