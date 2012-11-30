package fr.todooz.selenium;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TodoozTest {
	private WebDriver webDriver;

	@Before
	public void init() {
		// création du pilote firefox
		webDriver = new FirefoxDriver();
	}

	@After
	public void close() {
		// fermeture du navigateur
		webDriver.close();
	}
	
	@Test
	public void home() {
	   // on navigue vers la home
		webDriver.navigate().to("http://localhost:8080");
		// on vérifie le title
		Assert.assertEquals(webDriver.getTitle(),"Todooz");
	   
	}
}
