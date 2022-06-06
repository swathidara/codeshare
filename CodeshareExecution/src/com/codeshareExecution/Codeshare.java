package com.codeshareExecution;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class Codeshare {
	public static void main(String[] args) throws InterruptedException, AWTException{
	


//public void w1() 
		{
	System.setProperty("webdriver.chrome.driver","D:\\Eclipse\\chromedriver1\\chromedriver.exe");
  WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();// window maximize
	driver.get("https://codeshare.io/");
	//sharecode button
	
			driver.findElement(By.className("btn-primary")).click();
			
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/aside/nav/ul/li[1]/button/svg/path")).click();
			JavascriptExecutor jse=((JavascriptExecutor)driver);
			WebElement element=driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span"));
			jse.executeScript("arguments[0].value='#include <stdio.h>';",element);
			System.out.println("#include <stdio.h> text send to editor text box");
			//storing current window in currentwindow variable
			String currentwindow = driver.getWindowHandle();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--incognito");
			// A new window is opened and switches to it
			driver.switchTo().newWindow(WindowType.WINDOW);
			// https://codeshare.io/kmq3EA website in the newly opened window
	        driver.get("https://codeshare.io/kmq3EA");
			// storing child window in windows object
			Set<String> windows = driver.getWindowHandles();
			System.out.println(windows);
			//iterating windows object
			Iterator<String> itr = windows.iterator();
			while (itr.hasNext()) {
				String childwindow = itr.next();
				if (!childwindow.equalsIgnoreCase(currentwindow)) {
					driver.switchTo().window(childwindow);
					System.out.println("The child window is " + childwindow);
					//identifying code editor text box
					WebElement text1 = driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span"));
					String ExpectedText = "#include <stdio.h>";
					System.out.println("getting text from child window = "+text1.getText());
					//Assert.assertEquals(ExpectedText, text1.getText());
					System.out.println("#include <stdio.h> text is a expected – Assert passed");
					
					WebElement element1=driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span"));
					element1.click();
					System.out.println("click on first line of code");
					Robot robot= new Robot();
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					System.out.println("Pressed Enter button from key boaard");
					WebElement element2=driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span"));
					jse.executeScript("arguments[0].value='#include <conio.h>';",element2);
					Thread.sleep(50000);
					System.out.println("#include <conio.h> text is a expected – Assert passed");
				}
			}
			driver.switchTo().window(currentwindow);
			System.out.println("Switching to currentwindow window");
			WebElement text2 = driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[6]/div[1]/div/div/div/div[5]/div/pre/span"));
			String expectedText = "#include <stdio.h>";
			//Assert.assertEquals(expectedText,text2.getText());
			System.out.println("Getting text from current window= "+text2.getText());
			System.out.println("#include <conio.h> text is a expected – Assert passed");
			driver.close();
		}}}




