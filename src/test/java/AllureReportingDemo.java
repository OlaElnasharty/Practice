
import io.qameta.allure.*;
import utils.BrowserSetupUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Allure Reporting Demo -> Test Google home page")
public class AllureReportingDemo {
	WebDriver driver;

	@BeforeTest
	@Step("Initializing the browser and navigating to Google Home Page")
	public void setup() {
		driver = BrowserSetupUtil.setupDriver("remote", "http://localhost:4444/wd/hub", "chrome");
		driver.manage().window().maximize();
		driver.get("https://www.google.com/?ncr");
	}

	@Test
	@Description("Validating the Google home page URL")
	@Severity(SeverityLevel.BLOCKER)
	@Step("Validate the current URL")
	public void getURL() {
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, "https://www.google.com/?ncr", "URL validation failed!");
	}

	@Test
	@Description("Validating the Google home page title")
	@Severity(SeverityLevel.CRITICAL)
	@Step("Validate the page title")
	public void getTitle() {
		String pageTitle = driver.getTitle();
		assertTrue(pageTitle.contains("Goo"), "Title validation failed!");
	}

	@AfterClass
	@Step("Closing the browser")
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
