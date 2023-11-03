package com.capgemini.Biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PruebasBiblioteca {
	private WebDriver driver;
	
	@BeforeEach
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
		assertEquals("Libros", driver.getTitle());
	}
	
	@AfterEach
	public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
	}
}
