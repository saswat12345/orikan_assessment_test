import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeTestOrikan {
	WebDriver driver;
    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
 
    @Test
    void checkIssuesValidAndInvalid() {
		String email_address_error_text = "//label[text()='Email Address']//parent::div/div/div";
		String password_error_text = "//label[text()='Password']//parent::div/div/div";
		String password_error_text_field = "//label[text()='Password']//parent::div/input";
		String confirm_password_error_text = "//label[text()='Confirm Password']//parent::div/div/div";
		String next_button = "//button[text()='Next']";
		
        driver.get("https://orikan-ui-automation-test.azurewebsites.net/");
		
		// Issue 1 verification
		Assert.assertFalse(driver.findElement(By.xpath(next_button)).isDisplayed());
		
		driver.findElement(By.xpath(next_button)).click();
		
		Assert.assertTrue(driver.findElement(By.xpath(email_address_error_text)).getText() == "Email Address is required");
		Assert.assertTrue(driver.findElement(By.xpath(password_error_text)).getText() == "Password is required.");
		Assert.assertTrue(driver.findElement(By.xpath(confirm_password_error_text)).getText() == "Confirm Password is required.");
		
		
		// Issue 2 verification
		driver.findElement(By.xpath(password_error_text_field)).sendKeys("1");
		Assert.assertTrue(driver.findElements(By.xpath(password_error_text)).size() > 0);
		
    }
 
    @AfterTest
    void teardown() {
       // Close the driver
        driver.quit();
    }

}
