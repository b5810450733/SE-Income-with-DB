package cs.kuAccount;

import Model.Income;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefIncome {
    Income income;

    @Before
    public void initial(){
        income = new Income();
    }

    @Given("A user has (.*) for income")
    public void a_user_has_for_income (int newincome){
        income.moneyIn(newincome);
    }

    @When("I add income amount (.*)")
    public void i_add_income(int lastincome){
        income.moneyIn(lastincome);
    }

    @When("I spend money amount (.*)")
    public void i_spend_money(int newspend){
        income.moneyOut(newspend);
    }

    @When("I want to check my current income is that (.*)")
    public void i_want_to_check_my_current_income (int newestincome){
        assertEquals(newestincome,income.getMoneyInt());
    }

    @Then("I have total income (.*)")
    public void i_have_total_income(int totalincome){
        assertEquals(totalincome,income.getIncomeMoney());
    }

    @Then("The system shows my current income amount (.*)")
    public void system_show_my_current_income(int currentIn){
        assertEquals(currentIn,income.getMoneyInt());
    }

    @Then("The system shows my current expense amount (.*)")
    public void system_show_my_current_expense(int currentOut){
        assertEquals(currentOut,income.getMonOut());
    }

    @And("My expense is (.*)")
    public void my_expense_amount(int expense){
        assertEquals(expense,income.getMonOut());
    }

}
