package Parallel;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;
import org.junit.Assert;
import com.factory.DriverFactory;
import com.pages.loginPage;
import com.pages.AccountPage;

public class AccountPageSteps {
	
	private loginPage loginPage = new loginPage(DriverFactory.getDriver());
	private AccountPage AccountPage;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable dataTable) {		
	   List<Map<String, String>> data = dataTable.asMaps();
	   String username = data.get(0).get("username");
	   String password = data.get(0).get("password");
	   DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	   AccountPage = loginPage.getCredentials(username, password);
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
	   String title = AccountPage.getAccountTitlePage();	   
	   System.out.println("Title of the accountPage is: "+title);
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable accountSectnList) {
	    List<String>expectedListInAccSectn = accountSectnList.asList();
	    System.out.print("Expected List: "+expectedListInAccSectn);
	    List<String> ActualList = AccountPage.getAccountSectnList();
	    System.out.print("Actual List is: "+ActualList);
	    Assert.assertTrue(ActualList.containsAll(expectedListInAccSectn));
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer actualCount) {
		System.out.println(actualCount);
		Assert.assertTrue(AccountPage.getCountOfAccountSectn() == actualCount);
	}


}
