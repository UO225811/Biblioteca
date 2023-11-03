package com.capgemini.Biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PruebasBiblioteca {
	private WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src.test.resources.chromedriver.chromedriver.exe");
		driver = new ChromeDriver(new ChromeOptions());
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}

	@Test
	void testLogIn() {
		WebElement searchBox = this.driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys("covid argentina");
		searchBox.submit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("Dosis de refuerzo vacuna COVID-19", driver.getTitle());
	}
}
