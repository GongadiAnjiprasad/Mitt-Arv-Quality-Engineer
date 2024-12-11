package MittArv.MittArv;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MittArvScript {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver;
		String websiteURL = "https://app.mittarv.com/";
		String email = "anji.gongadi@gmail.com";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito"); // Enables incognito mode
		
		driver = new ChromeDriver(options);
		driver.get(websiteURL);
		driver.manage().window().maximize();
		
		// Step 1: Open Website and Capture Screenshot
        takeScreenshot(driver, "Step1_OpenWebsite");
        
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement googleLogo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login__socials']/img[@alt='google-logo']")));
	
		if (googleLogo.isDisplayed()) {
		    System.out.println("Element found!");
		    Thread.sleep(2000);
		 // Step 2: Click Google Login and Capture Screenshot
	        takeScreenshot(driver, "Step2_ClickGoogleLogin");
		    googleLogo.click();
		} else {
		    System.out.println("Element not visible!");
		}
		String parentId = driver.getWindowHandle();
		Set<String> parent = driver.getWindowHandles();
		for( String singleWindowHandleID : parent)
		{
			System.out.println("Window handle id is "+singleWindowHandleID);
			if(singleWindowHandleID != parentId)
			{
				driver.switchTo().window(singleWindowHandleID);
				
			}
		}
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='identifierId']"));
		emailTextBox.sendKeys(email);
		// Step 2: Click Google Login and Capture Screenshot
        takeScreenshot(driver, "Step3_EnterEmail");
		WebElement nextButton = driver.findElement(By.xpath("//span[normalize-space()='Next']"));
		// Step 2: Click Google Login and Capture Screenshot
        takeScreenshot(driver, "Step4_ClickOnNext");
		nextButton.click();
		takeScreenshot(driver, "Step5_AfterClickOnNext");
	}
	
	// Method to capture screenshot
    public static void takeScreenshot(WebDriver driver, String stepName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshots/" + stepName + ".png"));
        System.out.println("Screenshot taken for: " + stepName);
    }
}
