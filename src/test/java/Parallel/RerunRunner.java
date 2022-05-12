package Parallel;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"@target/failRerun.txt"},
		glue = {"Parallel"},
		plugin = {"pretty", 
				  "rerun:target/failRerun.txt"},
		dryRun = false,		
		monochrome=true
		)

public class RerunRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
}
