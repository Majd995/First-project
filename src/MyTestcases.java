
import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.print.DocFlavor.STRING;
import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyTestcases {
//majd
	WebDriver driver = new ChromeDriver();
	String mywebsite = "https://automationteststore.com/";

	String[] firstNames = { "amal", "majd", "noha", "ayat", "yosra", "alaa", "sawsan", "Rama" };
	String[] LastNames = { "Khaled", "ahmad", "Mohammad", "abdullah", "yazan", "omar" };

	Random rand = new Random();
	String GlobalUserName = "";
	String GlobalUserNameForTheLogin = "";
	String Password = "MajdSa123!@#";

	@BeforeTest
	public void mySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(mywebsite);
	}

	@Test(priority = 1)
	public void creatanaccount() throws InterruptedException {

		int RandomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int RandomIndexForTheLastNames = rand.nextInt(LastNames.length);

		String UserFirstName = firstNames[RandomIndexForTheFirstName];
		String UserLastName = LastNames[RandomIndexForTheLastNames];

		int randomNumberForTheEmail = rand.nextInt(564548);
		String domainName = "@gmail.com";

		String email = UserFirstName + UserLastName + randomNumberForTheEmail + domainName;

		driver.findElement(By.linkText("Login or register")).click();

		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		SignUpButton.click();
		Thread.sleep(2000);

		WebElement firstnameinpute = driver.findElement(By.id("AccountFrm_firstname"));
		firstnameinpute.sendKeys(UserFirstName);

		GlobalUserName = UserFirstName;

		System.out.println(GlobalUserName);
		WebElement lastnameinpute = driver.findElement(By.id("AccountFrm_lastname"));
		lastnameinpute.sendKeys(UserLastName);
		WebElement EMail = driver.findElement(By.id("AccountFrm_email"));
		EMail.sendKeys(email);
		WebElement address = driver.findElement(By.id("AccountFrm_address_1"));
		address.sendKeys("ammav city - tlaa al ali");
		WebElement city = driver.findElement(By.id("AccountFrm_city"));
		city.sendKeys("Capital city");

		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));
		Select selector2 = new Select(CountryInput);
		int randomCountry = rand.nextInt(1, 240);
		selector2.selectByIndex(randomCountry);

		Thread.sleep(3000);

		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector = new Select(ZoneIdInput);
		int randomState = rand.nextInt(1, 6);
		selector.selectByIndex(randomState);

		WebElement PostalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		PostalCodeInput.sendKeys("13310");

		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		String LocalUserName = UserFirstName + UserLastName + randomNumberForTheEmail;
		LoginNameInput.sendKeys(LocalUserName);
		GlobalUserNameForTheLogin = LocalUserName;
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys(Password);

		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPasswordInput.sendKeys(Password);

		WebElement AgreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		AgreeCheckBox.click();

		WebElement ContinueButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		ContinueButton.click();
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void LJogin() throws InterruptedException {
		WebElement Usernav = driver.findElement(By.id("customernav"));
		Actions action = new Actions(driver);
		action.moveToElement(Usernav).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Not " + GlobalUserName + "? Logoff")).click();
	}

	@Test(priority = 3)
	public void login() {
		System.out.println(GlobalUserNameForTheLogin);
		driver.findElement(By.linkText("Login or register")).click();

		WebElement LogInInput = driver.findElement(By.id("loginFrm_loginname"));
		LogInInput.sendKeys(GlobalUserNameForTheLogin);

		WebElement PasswordInput = driver.findElement(By.id("loginFrm_password"));
		PasswordInput.sendKeys(Password);

		WebElement loginBotton = driver.findElement(By.xpath("//button[@title='Login']"));
		loginBotton.click();

	}

	@Test(priority = 4)

	public void AddItemToTheCart() throws InterruptedException {

		String[] WebsitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65," };

		int randomindex = rand.nextInt(WebsitesForTheItems.length);
		driver.get(WebsitesForTheItems[randomindex]);

		WebElement LestOfItem = driver.findElement(By.cssSelector(".thumbnails.row"));
		int totalnumberofitem = LestOfItem.findElements(By.tagName("li")).size();
		int randomindexfortheitem = rand.nextInt(totalnumberofitem);
		Thread.sleep(2000);
		LestOfItem.findElements(By.tagName("li")).get(randomindexfortheitem).click();
		
		
	 WebElement  Contenar = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));	
		
 int numberOfproducts = Contenar.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		
int randomIndexForTheproduc=rand.nextInt(numberOfproducts);
Contenar.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomIndexForTheproduc).click();
		Thread.sleep(02000);
		WebElement ULList =(driver.findElement(By.className("productpagecart")));
		int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size(); 
		
	if(LiItem>0) {
		driver.get(mywebsite);
		
		System.out.println("sorry the item out of the stock ");
		String ExpectedResult="https://automationteststore.com/";
		 String ActualResult =driver.getCurrentUrl();
		 org.testng.Assert.assertEquals(ActualResult , ExpectedResult,"soso");
	}else {
			driver.findElement(By.className("cart")).click();}
	
	String ActualResult = driver.findElement(By.className("heading1")).getText();
			String ExpectedResult= "Shopping Cart";
	org.testng.Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase());
	boolean ExpectedValueForCheckOut = true;
	boolean ActualValueForCheckOut = driver.findElement(By.id("cart_checkout1")).isDisplayed();
	org.testng.Assert.assertEquals(ActualValueForCheckOut, ExpectedValueForCheckOut, "soso hi");
		}	
		
		
	}
	
	

