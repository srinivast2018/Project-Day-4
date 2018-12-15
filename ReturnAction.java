package com.ibm.groceries;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ibm.groceriespages.PageLogin;
import com.ibm.groceriespages.ReturnActionsPage;
import com.ibm.groceriespages.PageDashboard;
import com.ibm.initialization.WebDriverLaunch;
import com.ibm.utilities.PropertiesFileHandler;
import jdk.nashorn.internal.runtime.PrototypeObject;


public class ReturnAction extends WebDriverLaunch{
	
	@Test(priority=1, testName="CheckDeleteProduct",groups="low")
	//@Test(testName="CheckDeleteProduct",enabled=false)
	public void searchReturnAction()throws IOException,InterruptedException
	{
		String url=data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
		String searchKeyword=data.get("searchForKeyword");
		String searchMessage=data.get("searchDispalyMessage");
		String noMatchMessage=data.get("noMatchDisplayMessage");
		
		//Launching the web site for atozgroceries
		driver.get(url);
			
		PageLogin login = new PageLogin(driver, wait);
		//To enter email address and password and clickon login button
		login.enterEmailAddress(userName);
		login.enterPassword(password);
		login.clickOnLogin();
		Assert.assertTrue(driver.findElement(By.partialLinkText("Logout")).isDisplayed());
		
		PageDashboard dashboard=new PageDashboard(driver, wait);
		//calling method to click on System link
		dashboard.clickOnsystem();
		
		//calling method to click on Returns link
		dashboard.clickOnReturns();
		
		//Calling method to click on Return Actions link
		dashboard.clickOnRetrunActions();
		
		
		ReturnActionsPage action=new ReturnActionsPage(driver,wait);
		//Calling method to search for keyword 
		String pageSource=action.searchAction(searchKeyword);
		//String pageSource2=pageSource.compareToIgnoreCase(searchKeyword);
		
		pageSource=pageSource.toLowerCase();
		searchKeyword=searchKeyword.toLowerCase();
		//System.out.println("Page source is "+pageSource);
		
		if(pageSource.contains(searchKeyword))
		{
		System.out.println(searchMessage);
		Assert.assertTrue(pageSource.contains(searchKeyword));
		}
		else if(pageSource.contains(noMatchMessage))
		{
			System.out.println(noMatchMessage);
			Assert.assertTrue(pageSource.contains(noMatchMessage));
		}
	}
	

}
