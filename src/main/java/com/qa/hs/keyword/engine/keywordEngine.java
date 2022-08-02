package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class keywordEngine {

	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	String  locatorName=null;
	String	locatorValue=null;
	public  Base  base;
	public WebElement element;

	public final String SCENARIO_SHEET_PATH="C:\\Users\\ASK\\eclipse-workspace\\JavaProjects\\keywordDrivenhubspot\\src\\main\\java\\com\\qa\\hs\\keyword\\scenario\\hubspot.scenarios.xlsx";

	public  void startExecution(String sheetName) {

		FileInputStream  file =null;

		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		int k=0;

		for(int i=0;i<sheet.getLastRowNum();i++) {
			try {
				String locatorType= sheet.getRow(i+1).getCell(k+1).toString().trim() ;
				String locatorValue= sheet.getRow(i+1).getCell(k+2).toString().trim() ;
				//			if(!locatorColVal.equalsIgnoreCase("NA")) {
				//				locatorName = locatorColVal.split("=")[0].trim();
				//				locatorValue = locatorColVal.split("=")[1].trim();
				//			}
				String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
				String value = sheet.getRow(i+1).getCell(k+4).toString().trim();

				switch (action) {
				case "open browser":
					base = new Base();
					prop = base.init_proprties();
					if(value.isEmpty() || value.equals("NA") ) {
						driver = base.init_driver(prop.getProperty("browser"));
					}else {

						driver= base.init_driver(value);
					}
					break;
				case "enter url":

					if(value.isEmpty() || value.equals("NA") ) {
						driver.get(prop.getProperty("url"));
					}else {

						driver.get(value);
					}
					break;
				case "quit":
					driver.quit();
					break;	
				default:
					break;
				}

				switch (locatorType) {
				case "id":
					element=driver.findElement(By.id(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);	
					}else  if(action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}
					locatorName=null;
					break;

				case "xpath":
					element=driver.findElement(By.xpath(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);	
					}else  if(action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						System.out.println(element.isDisplayed());
					}else if(action.equalsIgnoreCase("getText")) {
						System.out.println("Text from element: "+element.getText());
					}
					locatorName=null;
					break;

				case "className":
					element=driver.findElement(By.className(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);	
					}else  if(action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						System.out.println(element.isDisplayed());
					}else if(action.equalsIgnoreCase("getText")) {
						System.out.println("Text from element: "+element.getText());
						
					}
					locatorName=null;
					break;

				case "name":
					element=driver.findElement(By.name(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);	
					}else  if(action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}else if(action.equalsIgnoreCase("getText")) {
						
						System.out.println("Text from element: "+element.getText());
						
					}
					locatorName=null;
					break;
					
				case "cssSelector":
					element=driver.findElement(By.cssSelector(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);	
					}else  if(action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}else if(action.equalsIgnoreCase("getText")) {
						
						System.out.println("Text from element: "+element.getText());
						
					}
					locatorName=null;
					break;
					
				case "linkText":
					element=driver.findElement(By.linkText(locatorValue));
					element.click();
					locatorName=null;
					break;
					
				case "partialLinkText":
					element=driver.findElement(By.partialLinkText(locatorValue));
					element.click();
					locatorName=null;
					break;
					
				case "tagName":
					element=driver.findElement(By.id(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);	
					}else  if(action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}else if(action.equalsIgnoreCase("getText")) {
						
						System.out.println("Text from element: "+element.getText());
						
					}
					locatorName=null;
					break;
					
		


				default:
					break;
				}
			}catch(Exception e) {
			}
		}

	}
}

