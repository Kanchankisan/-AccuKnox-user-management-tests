package demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class InformationEditTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    // Gender radio buttons
    @FindBy(className = "oxd-radio-input")
    public List<WebElement> radioBtn;

    // All buttons (used for Save actions)
    @FindBy(tagName = "button")
    public List<WebElement> allButtons;

    // All dropdown inputs (Nationality, Blood Group, etc.)
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdowns;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get(baseUrl);

        PageFactory.initElements(driver, this);
    }

    @Test(priority = 1)
    public void validLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']"))).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }

    @Test(priority = 2)
    public void editEmployeeDetails() throws InterruptedException {
        // Navigate to PIM > Employee List
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Employee List']"))).click();

        // Search for employee named "kanchan"
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        nameInput.sendKeys("kanchan");

        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

        // Click edit icon
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='oxd-icon bi-pencil-fill']"))).click();

        Thread.sleep(2000);

        selectGender();
        selectBloodGroup();
        selectNationality();
        selectMaritalStatus();
        selectdate();


        // Scroll down if needed
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500);");
        Thread.sleep(2000);
    }

    public void selectdate() {
		// TODO Auto-generated method stub
    	  
    	  //click on License Expiry Date
    		WebElement dateInput = driver.findElement(By.xpath("(//i)[10]"));
    	    // Set the date (format: YYYY-MM-DD)
    	    dateInput.sendKeys("2025/30/06");
    		driver.findElement(By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]")).sendKeys("2025/30/06");
    	    
		
	}

	public void selectMaritalStatus() throws InterruptedException {
        WebElement selectMaritalStatus = wait.until(ExpectedConditions.elementToBeClickable(dropdowns.get(1)));
        selectMaritalStatus.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(text(),'Single')])[1]")));
        option.click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(allButtons.get(1))).click(); // Save Nationality
        Thread.sleep(3000);
    }
    
	public void selectGender() throws InterruptedException {
        Thread.sleep(3000);
wait.until(ExpectedConditions.elementToBeClickable(radioBtn.get(0))).click(); // Select Male
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(allButtons.get(1))).click(); // Save Gender
        Thread.sleep(1000);
    }

    public void selectBloodGroup() throws InterruptedException {
        Thread.sleep(3000);
 WebElement bloodDropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdowns.get(2)));
        bloodDropdown.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//span[text()='O+']")));
        option.click();
        driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[7]")).sendKeys("O+");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(allButtons.get(2))).click(); // Save Blood Group
        Thread.sleep(1000);
    }

    public void selectNationality() throws InterruptedException {
        Thread.sleep(3000);
WebElement nationalityDropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdowns.get(0)));
        nationalityDropdown.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//span[text()='Indian']")));
        option.click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(allButtons.get(3))).click(); // Save Nationality
        Thread.sleep(3000);
    }
    
  
    
    

    public void savethechanges() throws InterruptedException
	{
		//save button click
		driver.findElement(By.xpath("((//button[@type='submit'][normalize-space()='Save'])[1]")).submit();
        Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@type='submit'][normalize-space()='Save'])[2]")).submit();
	}

	/*@AfterTest
	public void tearDown() throws InterruptedException
	{

		//	logOut();

		Thread.sleep(5000);//wait for 5 secs before quit
		driver.close();
		driver.quit();

	}*/

    }

