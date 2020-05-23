package com.generic.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import com.config.BaseConfig;
import com.page.object.model.PropertyPage;
import com.util.Highlighter;
import com.util.TakeAppScreenShot;
import com.util.Wait;

public class HomeSelection extends BaseLogin{
	public static void selectProperty() throws Throwable {
		getLogin();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyPage pf = new PropertyPage(driver);
		bgColor="green";
		pf.getLocationName().sendKeys(BaseConfig.getconfig("loationName"));
	//	TakeAppScreenShot.captureScreenShot(driver, "Location Name");
		Highlighter.getcolor(driver, pf.getLocationName(),bgColor, "red");
		//Color bg = new Color(128, 128, 0, 0.0);
		
		
		Highlighter.getcolor(driver, pf.getSearchSubmit(), bgColor, "red");
		TakeAppScreenShot.captureScreenShot(driver, "Search");
		
		pf.getSearchSubmit().click();
		
		String [] prices;		
		List<Integer> intPrices = new ArrayList<>();
		
		for(int i=0; i<pf.getHomePrices().size();i++) {
			
			prices = pf.getHomePrices().get(i).getText().split(" ");
			intPrices.add(Integer.parseInt(prices[0].replace("£","").replace(",","").trim()));			
		}
		System.out.println("House Prices: "+intPrices);
		Collections.sort(intPrices);
		//System.out.println("House Prices Sorted Asc: "+intPrices);
		Collections.reverse(intPrices);
		System.out.println("House Prices Descending Sorted: "+intPrices);
		
		new Wait().getExplicitWaitVisible(driver, pf.getHomePrices().get(4));
		pf.getHomePrices().get(4).click();
		
		if(pf.getPropertyLogo().isDisplayed()) {
			System.out.println("Logo is available");
		}
		else {
			System.out.println("Logo is not available");
		}
		System.out.println("Agent Name is: "+pf.getAgentName().getText());
		System.out.println("Agent Phone Number: "+pf.getAgentPhoneNum().getText());
		
		TakeAppScreenShot.captureScreenShot(driver, "Agent Details");
		
		Actions logout = new Actions(driver);
		logout.moveToElement(pf.getMyZooplaBtn()).build().perform();
		Highlighter.getcolor(driver, pf.getMyZooplaBtn(),bgColor, "red");
		
		
		Highlighter.getcolor(driver, pf.getSignOutBtn(), bgColor, "red");
		TakeAppScreenShot.captureScreenShot(driver, "Log Out");
		pf.getSignOutBtn().click();
		
		driver.quit();
		
	}

}
