package AppHooks;

import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.utilities.configReader;
import com.factory.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class applicationHooks 
{
   private WebDriver driver;
   private DriverFactory driverFactory;
   private static configReader configReader;
   Properties prop;
   
   @Before(order=0)
   public void getProperty()
   {
	   configReader = new configReader();
	   prop = configReader.init_prop();
   }
   
   @Before(order=1)
   public void launchBrowser()
   {
	   String browserName = prop.getProperty("browser");
	   driverFactory = new DriverFactory();
	   driver = driverFactory.init_driver(browserName);  // we need to store this in driver ref coz it will give null when we will call After ann.
   }
   
   @After(order=0)
   public void quitBrowser()
   {
	   driver.quit();
   }
   
   @After(order=1)
   public void tearDown(Scenario scenario) //takes screenshot of failed scenarios using scenario object concept
   {
	   if(scenario.isFailed())
	   {
		   String scenarioName = scenario.getName().replaceAll(" ", "_");
		   byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		   scenario.attach(sourcePath, "image/png", scenarioName);
		   
	   }
   }
}
