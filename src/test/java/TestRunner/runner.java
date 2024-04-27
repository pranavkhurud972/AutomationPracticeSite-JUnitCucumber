package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:BDDProject.src.test.resources.Features.myfeature.feature",          
        glue="StepDef.stepdef" , 
        tags= "@MyAccountLogin", 
        plugin = {"pretty",                      
            "html:target/html/htmlReport.html",
            "json:target/json/jsonReport.json",
            
            
            },
        monochrome=true,
        publish= true,
        dryRun=false
        )





public class runner {

	
	
}
