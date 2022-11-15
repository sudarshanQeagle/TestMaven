package tests.rest;

import java.io.File;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC004_UpdateIncident extends RESTAssuredBase{
	
	@BeforeTest//Reporting
	public void setValues() {
		testCaseName = "Update Incident";
		testDescription = "Update Incident Request and Verify";
		nodes = "incident";
		authors = "Sudarshan";
		category = "REST";
		//dataProvider
		dataFileName = "TC003";
		dataFileType = "JSON";
	}

	@Test(dataProvider = "fetchData",dependsOnMethods = "tests.rest.TC001_CreateIncident.createIncident")
	public void createCR(File file) {		
				
		Response response = putWithBodyParam(file, "incident/"+sysID);
		
		verifyContentType(response, "application/json");
		
		verifyResponseCode(response, 200);
		
		response.prettyPrint();
		
	
	}


}




















