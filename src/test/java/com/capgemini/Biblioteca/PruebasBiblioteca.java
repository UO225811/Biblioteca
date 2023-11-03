package com.capgemini.Biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
	private WebDriver driver;

	@BeforeAll
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "src/test/resources/edgedriver/msedgedriver.exe");
		driver = new EdgeDriver(new EdgeOptions());
		driver.manage().window().maximize();
		driver.get("http://localhost:8080");
	}

//	@Test
//	void testLogIn() {
//		WebElement username = this.driver.findElement(By.name("username"));
//		username.clear();
//		username.sendKeys("user1@email.com");
//
//		WebElement password = this.driver.findElement(By.name("password"));
//		password.clear();
//		password.sendKeys("1234");
//
//		password.submit();
//		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//		assertEquals("http://localhost:8080/home", driver.getCurrentUrl());
//	}

//	@Test
//	void testSignUp() {
//		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/li[1]/a")).click();
//		driver.findElement(By.name("email")).sendKeys("user2@email.com");
//		driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("user2");
//		driver.findElement(By.name("password")).sendKeys("1234");
//		driver.findElement(By.xpath("/html/body/div/form/div[4]/div/button")).click();
//		assertEquals("http://localhost:8080/home", driver.getCurrentUrl()); //Redirige a login
//	}

	@Test
	void pedirPrestadoYDevolver() {
		WebElement username = this.driver.findElement(By.name("username"));
		username.clear();
		username.sendKeys("user1@email.com");

		WebElement password = this.driver.findElement(By.name("password"));
		password.clear();
		password.sendKeys("1234");

		password.submit();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		// ir a libros
		driver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/li[1]/div/a[1]/span")).click();

		// solicitar préstamos
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.findElement(By.cssSelector(
				"body > section > div > table > tbody > tr:nth-child(3) > td:nth-child(7) > div > div > div > a"))
				.click();

		// ir a préstamos
		driver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/li[1]/div/a[2]/span")).click();

		// devolver
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.findElement(By.cssSelector("body > section > div > table > tbody > tr > td:nth-child(4) > a")).click();

		assertEquals("http://localhost:8080/leases", driver.getCurrentUrl());

//		//logout
//		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/li[2]/form/input[1]"));
//		assertEquals("http://localhost:8080/home", driver.getCurrentUrl());
	}

	@Test
	void testLogOut() {
		WebElement logout = this.driver
				.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/li[2]/form/input[1]"));
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
