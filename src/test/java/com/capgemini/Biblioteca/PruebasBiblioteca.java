package com.capgemini.Biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PruebasBiblioteca {
	private static WebDriver driver;
	
	@BeforeAll
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
		driver = new EdgeDriver(new EdgeOptions());
		driver.manage().window().maximize();
		driver.get("http://localhost:8080");
	}
	
	@Test
	void testLogIn() {
		WebElement username = this.driver.findElement(By.name("username"));
		username.clear();
		username.sendKeys("grogu@email.com");
		
		WebElement password = this.driver.findElement(By.name("password"));
		password.clear();
		password.sendKeys("1234");
	
		password.submit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("http://localhost:8080/home", driver.getCurrentUrl());
	}
	
	@Test
	void testLogOut() {
		WebElement logout = this.driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/li[2]/form/input[1]"));
		logout.click();
		assertEquals("http://localhost:8080/login", driver.getCurrentUrl());
	}
	
	@AfterAll
	public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
	}
}
