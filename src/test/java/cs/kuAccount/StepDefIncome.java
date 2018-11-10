package cs.kuAccount;

import Model.Amount;
import Model.Income;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefIncome {
    Income income;
    Amount amount;

    @Before
    public void initial(){
        income = new Income();
        amount = new Amount();
    }

    @Given("A user has (.*) for income")
    public void a_user_has_for_income (int newincome){
        amount.setTotalAmount(1,newincome);
    }

    @When("I add income amount (.*)")
    public void i_add_income(int lastincome){
        income = new Income("Income","duck",lastincome);
        amount.addAmount(Double.parseDouble(income.getAmount()));
    }

    @When("I spend money amount (.*)")
    public void i_spend_money(int newspend){
        income = new Income("Expense","pen",newspend);
        amount.decreseAmount(newspend);
    }

    @When("I want to check my current income is that (.*)")
    public void i_want_to_check_my_current_income (int newestincome){
        assertEquals(newestincome,amount.getTotalAmount());
    }

    @Then("I have total income (.*)")
    public void i_have_total_income(int totalincome){
        assertEquals(totalincome,amount.getTotalAmount());
    }

    @Then("The system shows my current income amount (.*)")
    public void system_show_my_current_income(int currentIn){
        assertEquals(currentIn,amount.getTotalAmount());
    }

    @Then("The system shows my current expense amount (.*)")
    public void system_show_my_current_expense(int currentOut){
        assertEquals(currentOut,amount.getLastExpense());
    }

    @And("My expense is (.*)")
    public void my_expense_amount(int expense){
        assertEquals(expense,amount.getLastExpense());
    }

}
