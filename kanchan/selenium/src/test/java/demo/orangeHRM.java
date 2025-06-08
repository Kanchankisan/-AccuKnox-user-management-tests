package demo;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Assert;

public class orangeHRM {
	
	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver ;
	
	@BeforeTest
	public void setup()
	{
		System.out.println("Before Test executed");
		// TODO Auto-generated method stub
		driver=new ChromeDriver();
		

		//maximise windows
		driver.manage().window().maximize();

		//open url
		driver.get(baseUrl);

		//timer i kept as 60 you can keep 40
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60)); //60 seconds
		
	}
	
	@Test(priority = 1, enabled=true)
	public void doLoginWithInvalidCredential() throws InterruptedException
	{
		//find username and enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		//find password and enter invalid password 
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1234");//wrong password

		//login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();


		String message_expected = "Invalid credentials";

		String message_actual = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();

		Assert.assertTrue(message_actual.contains(message_expected));

		Assert.assertEquals(message_expected , message_actual);
		

		Thread.sleep(1500);
	}
	
	
	@Test(priority =2, enabled=true)
	public void loginTestWithValidCredential()
	{
		//find username and enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		//find password and enter password admin123
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		//login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		
		String pageTitle = driver.getTitle();

		/*	if (pageTitle.equals("OrangeHRM")) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Login failed!");
		}*/

		//Assert.assertEquals("OrangeHRM", pageTitle);	
	}
	

	@Test(priority =3, enabled=true)
	public void addEmployee() throws InterruptedException, IOException
	{
		//   //span[text()='PIM']
		//     //a[text()='Add Employee']

		///    //input[@placeholder='First Name']

		//     //input[@placeholder='Last Name']

		//    //button[normalize-space()='Save']
		Thread.sleep(5000);


		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")).click();

		//find Add employee and click on Add Employee option
		driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();

		//enter first name
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("kanchan");

		//enter last name
		driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")).sendKeys("0391");
		
		//enter employee id
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("ghute");


		//////////////////////Add Image////////////////////////////////////////////

		//add image
		//driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")).click();


		Thread.sleep(5000);//pause of 5 seconds

		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		
		Thread.sleep(8000);//pause of 8 seconds
}
	
	@Test(priority =4, enabled=false)
	public void viewEmployee()throws InterruptedException, IOException
	{

		driver.findElement(By.xpath("//li[@class='oxd-topbar-body-nav-tab --visited']")).click();
		driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys("kanchan");
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels vertically
        js.executeScript("window.scrollBy(0, 500);");
        
	}
	@Test(priority = 5, enabled = true)
	public void searchEmployeeByName() throws InterruptedException {
	    
		

	    driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']")).click();
	    driver.findElement(By.xpath("//li[@class='oxd-topbar-body-nav-tab --visited']")).click();

		Thread.sleep(3000);//pause of 8 seconds

	    WebElement nameInput = new WebDriverWait(driver, Duration.ofSeconds(10))
	            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Type for hints...'])[1]")));
	    nameInput.sendKeys("kanchan");

	    driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
	    
		Thread.sleep(3000);//pause of 8 seconds

	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels vertically
        js.executeScript("window.scrollBy(0, 500);");

		Thread.sleep(6000);//pause of 8 seconds


	    /*List<WebElement> messages = new WebDriverWait(driver, Duration.ofSeconds(10))
	            .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='oxd-text oxd-text--span']")));

		Thread.sleep(2000)	;

	    Assert.assertFalse(messages.isEmpty(), "No result message found.");
	    String messageActual = messages.get(0).getText();
	    System.out.println("Search Result Message: " + messageActual);


	    Assert.assertTrue(messageActual.contains("Record Found") || messageActual.contains("Radha"),
	        "Expected search result was not found.");
	   */
		
	    
	}

	
	@Test(priority =6, enabled=true)
	public void searchEmployeeById() throws InterruptedException
	{

		String empId = "0391";
		//String message_actual ="";
		


		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']")).click();

		//Select Employee List
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();

		//enter empoyee id
		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys(empId);

		
		Thread.sleep(8000);//pause of 8 seconds


		//Click the search button.
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		Thread.sleep(2000)	;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels vertically
        js.executeScript("window.scrollBy(0, 500);");

		
		Thread.sleep(6000)	;

	}

	@Test(priority=9, enabled=true)
	public void applyLeave() throws InterruptedException
	{
		/*
		//find username and enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		//find password and enter password admin123
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		//login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		
		*/
		//click on leave menu
		driver.findElement(By.linkText("Leave")).click();
		Thread.sleep(8000);

		//click on Apply menu
		driver.findElement(By.linkText("Apply")).click();
		
		//click on leave type drop down
		//driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
		//driver.findElement(By.xpath("//span[normalize-space()='More']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Assign Leave']")).click();
		
		
		driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys("kanchan ghute");

		//select CAN-FMLA option from leave type dropdown
		driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();
		
		//enter from date
		driver.findElement(By.xpath("//div[@class='oxd-date-input']/input")).sendKeys("2024-08-04");
		
		
		//enter comment
		driver.findElement(By.tagName("textarea")).sendKeys("This is my personal leave");
		Thread.sleep(3000);
		
		
		Thread.sleep(4000);

		
		//click on Apply button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		Thread.sleep(5000);
		
		
	}
		

	
	@Test(priority=8, enabled=true)
	public void deleteEmployee() throws InterruptedException
	{
		Thread.sleep(5000);

		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		//Select Employee List
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();

		//enter employee name
		driver.findElements(By.tagName("input")).get(1).sendKeys("kanchan");

		//driver.findElement(By.tagName("input")).get(1).sendKeys("Nesta");
		Thread.sleep(5000);


		//Click the search button.
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();


		Thread.sleep(3000);
		///////////////////Delete/////////////////////////

	    JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels vertically
        js.executeScript("window.scrollBy(0, 500);");


		//click on delete icon of the record
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();

		Thread.sleep(5000);

		//click on yes, delete messaage button
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();
		Thread.sleep(5000);

		//check for message "No Record Found"
		String msg = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).getText();

		Assert.assertEquals(msg, "No Records Found");

		Thread.sleep(5000);

	
		
	}

	private void logIn() {
		// TODO Auto-generated method stub
		//find username and enter username "Admin"
				driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

				//find password and enter password admin123
				driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

				//login button click
				driver.findElement(By.xpath("//button[@type='submit']")).submit();


		
	}

	private void logOut() {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

	}
	@AfterTest
	public void tearDown() throws InterruptedException
	{

		//	logOut();

		Thread.sleep(5000);//wait for 5 secs before quit
		driver.close();
		driver.quit();

	}
}
