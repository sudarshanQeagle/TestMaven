package tests.selenium;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC003_CreateIncidentUsingRestAndVerifyUsingSelenium extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Search Incident (Using Selenium) After Creating Incident (Using REST Assured)";
		testDescription = "Create Incident (Using REST Assured) and Search with Selenium";
		nodes = "Incident Management";
		authors = "Shiva";
		category = "UI & API";
		dataSheetName = "TC003";

	}

	@Test(dataProvider = "fetchData")
	public void createIncident(String filter) throws InterruptedException {

		// Post the request
		Response response = RESTAssuredBase.post("incident",ContentType.JSON);

		RESTAssuredBase.verifyResponseCode(response, 201);
		
		response.prettyPrint();

		//Verify the Content by Specific Key
		incidentNumber = RESTAssuredBase.getContentWithKey(response, "result.number");

		// Selenium - find Incident
		new LoginPage(driver,test)
			.loginApp()
			.searchUsingFilter(filter)
			.clickOpen()
			.selectSearchDD("Number")
			.typeAndEnterSearch(incidentNumber);
	
	}


}





