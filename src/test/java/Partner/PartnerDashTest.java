package Partner;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Customer.CustomerDashTest;
import Partner.HomeScreen;
import Partner.LoginScreen;
import Partner.NIDScreen;
import Partner.ProfileManagementScreen;
import TestBase.AppiumBase2;
import Utils.GIMSignIn;
import Utils.GIMSignUp;
import Utils.Randomgenerator;
import Utils.TestData;
import Utils.Verify;
import io.appium.java_client.android.AndroidDriver;

@Listeners(Utils.TestMethodListener.class)
public class PartnerDashTest {
	
	AndroidDriver driver;
	TestData testdata;

	GIMSignIn signin = new GIMSignIn();
	GIMSignUp signup = new GIMSignUp();
	public int userid;
	
	public String regnop1 = new Randomgenerator().generateRandomNumber(2);
	public String regnop2 = new Randomgenerator().generateRandomNumber(4);
	
	
	public String mobileno = new Randomgenerator().generateRandomNumber(11);
	public String mobileno1 = new Randomgenerator().generateRandomNumber(11);
	public WebDriverWait wait;

	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("udid", "192.168.129.101:5555");
		//capabilities.setCapability("isHeadless", true);
		capabilities.setCapability("udid", testdata.properties.getProperty("devicemi"));
		capabilities.setCapability("deviceName", testdata.properties.getProperty("devicemi"));
		capabilities.setCapability("newCommandTimeout", 2400);
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("autoGrantPermissions ", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage", testdata.properties.getProperty("appPackage1"));
		capabilities.setCapability("appActivity", testdata.properties.getProperty("appActivity1"));
		capabilities.setCapability("skipUnlock", true);
		capabilities.setCapability("skipDeviceInitialization", true);
		// capabilities.setCapability("resetKeyboard", true);
		// capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("ignoreUnimportantViews", true);
		capabilities.setCapability("disableAndroidWatchers", true);
		capabilities.setCapability("systemPort", 8201);
		// capabilities.setCapability("autoGrantPermissions", true);
		// capabilities.setCapability("automationName", "uiautomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5000);
	}
	
	@Test(priority=12, description="Verify total,available and live trucks count when the user lands on the truck dashboard screen")
	public void Verify_Total_Live_AvailableTruckCount() throws IOException, InterruptedException {
		
		setUp();
		CustomerDashTest custdash = new CustomerDashTest();
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		System.out.println("Partner mobile no is ***********"+custdash.partnermobileno);
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(custdash.partnermobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		driver.navigate().back();
	
		trucksscreen.clickBurgerMenu();
		Thread.sleep(1500);
		//nidscreen.scrollTo("Logout");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"My Dashboard\")").click();
		
		Verify.verifyEquals(driver.findElementById("tv_total_trucks").getText(), "1", "Total truck count validation failed");
		Verify.verifyEquals(driver.findElementById("tv_live_trucks").getText(), "1","Onduty truck count validation failed");
		Verify.verifyEquals(driver.findElementById("tv_available_trucks").getText(), "0", "Available truck count validation failed");
		
	}
		
	@Test(priority=13, description="Verify Total trips count and total amount")	
	public void Verify_TotalTripsCount_And_Amount() {	
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRIP STATUS\")").click();
		Verify.verifyEquals(driver.findElementById("tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
		
	}	
	
	@Test(priority=14, description="Verify dashboard filter by invalid registration number")
	public void Verify_DashboardFilter_ByInvalidRegistrationnumber() {
		
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.fleetowner:id/et_reg_number").sendKeys("asdfasdf");
		driver.navigate().back();
		driver.findElementById("com.ejogajog.fleetowner:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("tv_total_trips_count").getText(), "0", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("tv_trips_amount").getText().contains("0"), true, "Total amount validation failed");
	
	}	
	
	@Test(priority=15, description="Set fromDate to currentDate and filter to validate total trips and total amount")
	public void Verify_DashboardFilter_BySetting_FromDate_to_CurrentDate() throws InterruptedException { 
		driver.findElementById("menu_icon").click();
		
		driver.findElementById("com.ejogajog.fleetowner:id/select_from_date").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.fleetowner:id/btn_search").click();
		Thread.sleep(2500);
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
		
	}	
	
	@Test(priority=16, description="End a live trip as partner and validate the trip count and trip amount")
	public void EndLiveTrip_AsPartner_And_ValidateTotalTripCount_TotalTripAmount() {
	
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		
		driver.findElementByAccessibilityId("MY TRIPS").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"LIVE\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRIP NO\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"COMPLETE THIS TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, END\")").click();
		
		driver.navigate().back();
		
		trucksscreen.clickBurgerMenu();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"My Dashboard\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRIP STATUS\")").click();
		
		Verify.verifyEquals(driver.findElementById("tv_total_trips_count").getText(), "10", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("tv_trips_amount").getText().contains("1,321,450"), true, "Total amount validation failed");
	}
	
	
	@Test(priority=17, description="After trip complettion successfully, Validate total tripcount and  trip amount by filtering from pickupdate set to current date")
	public void AfterTripCompletion_FilterBy_FromPickUpDateSettoCurrentDate_ValidateTotalTripCount_TotalTripAmount() {
	
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.fleetowner:id/select_from_date").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.fleetowner:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_trips_count").getText(), "10", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_trips_amount").getText().contains("1,321,450"), true, "Total amount validation failed");
	}
	
	@Test(priority=18, description="After trip completion successfully, Validate total trip count and trip amount by filtering to pickupdate set to current date")
	public void AfterTripCompletion_FilterBy_ToPickDateSettoCurrentDate_ValidateTotalTripCount_TotalTripAmount() {
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.fleetowner:id/select_to_date").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.fleetowner:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_trips_count").getText(), "0", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_trips_amount").getText().contains("0"), true, "Total amount validation failed");
	}
	
	
	@Test(priority=19, description="Tap on Filter Search without selecting any filter attributes and Validate total trip count and total trip amount")
	public void FilterSearch_Without_Selecting_AnyFilterAttributes_And_ValidateTotalTripCount_TotalAmount() {
	
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.fleetowner:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_trips_count").getText(), "10", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_trips_amount").getText().contains("1,321,450"), true, "Total amount validation failed");
	}	
		
	
	@Test(priority=20, description="Clearfilter without selecting any filter attributes and tap on search filter to validate total trip count and total amount")
	public void ClearFilter_WithoutSelectin_AnyFilterAttributes_Search_And_ValidateTotalTripCount_And_TotalAmount() {
	
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.fleetowner:id/btn_clear").click();
		driver.findElementById("com.ejogajog.fleetowner:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_trips_count").getText(), "10", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_trips_amount").getText().contains("1,321,450"), true, "Total amount validation failed");	
	
	}
	
	@Test(priority=21, description="Verify Truck status After trip completed successfully")
	public void VerifyTruckStatus_AfterTripCompletionSuccessfully() {
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRUCK STATUS\")").click();
		
		Verify.verifyEquals(driver.findElementById("tv_total_trucks").getText(), "1", "Total truck count validation failed");
		Verify.verifyEquals(driver.findElementById("tv_live_trucks").getText(), "0","Onduty truck count validation failed");
		Verify.verifyEquals(driver.findElementById("tv_available_trucks").getText(), "1", "Available truck count validation failed");
	
	}
	
	
	
	

}
