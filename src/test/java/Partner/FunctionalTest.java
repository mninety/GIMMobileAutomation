package Partner;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import TestBase.AppiumBase;
import TestBase.AppiumBase2;
import Utils.TestData;
import Utils.TruckListing;
import Utils.Truck_Action;
import Utils.Verify;
import Utils.PendingUser_Listing;
import Utils.User_Action;
import Utils.AdminLogin;
import Utils.GIMSignIn;
import Utils.GIMSignUp;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import Utils.Randomgenerator;
import Utils.GetOTP;
import Utils.OnCamera;

@SuppressWarnings("rawtypes")
@Listeners(Utils.TestMethodListener.class)
public class FunctionalTest extends AppiumBase2 {

	AndroidDriver driver;
	TestData testdata;

	GIMSignIn signin = new GIMSignIn();
	GIMSignUp signup = new GIMSignUp();
	public int userid;
	
	public String regnop1 = new Randomgenerator().generateRandomNumber(2);
	public String regnop2 = new Randomgenerator().generateRandomNumber(4);
	
	
	String mobileno = new Randomgenerator().generateRandomNumber(11);
	String mobileno1 = new Randomgenerator().generateRandomNumber(11);
	public WebDriverWait wait;

	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("udid", "192.168.129.101:5555");
		//capabilities.setCapability("isHeadless", true);
		capabilities.setCapability("udid", testdata.properties.getProperty("deviceName"));
		capabilities.setCapability("deviceName", testdata.properties.getProperty("deviceName"));
		capabilities.setCapability("newCommandTimeout", 240);
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

	@Test(priority=2)
	public void SignupTest_AsPartner() throws IOException, InterruptedException {
		
		HomeScreen homescreen = new HomeScreen(driver);
		MobielVerificationScreen mobileverificationscreen = new MobielVerificationScreen(driver);
		GetOTP otp = new GetOTP();
		PersonalInformationScreen personalinformationscreen = new PersonalInformationScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		homescreen.clickSignUpButton();
		mobileverificationscreen.setMobileNumber(mobileno);
		mobileverificationscreen.TickconditionsCheckbox();
		mobileverificationscreen.ClickSendVerificationSMSButton();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/snackbar_text").getText(),
				"Your OTP will expire in 15 minutes", "User not found validation failed");
		
		otp.OTPAPI(mobileno);
		mobileverificationscreen.setPinNumber(otp.getOTP());
		mobileverificationscreen.ClickNextButton();
		personalinformationscreen.setEmail(new Randomgenerator().generateRandomemailID());
		personalinformationscreen.setPassword("pass01");
		personalinformationscreen.setReferralCode("01" + new Randomgenerator().generateRandomNumber(9));
		driver.navigate().back();
		personalinformationscreen.ClickNextButton();
		nidscreen.setName(new Randomgenerator().generateRandomName());
		nidscreen.setDOB();
		nidscreen.setDistrict();
		nidscreen.setNID(new Randomgenerator().generateRandomNumber(17));
		driver.navigate().back();
		nidscreen.ClickNIDFrontPhoto();
		nidscreen.ClickCameraSelection();
		//nidscreen.ClickAllowPermission();
		//nidscreen.ClickAllowPermission();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		Thread.sleep(2500);
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		nidscreen.ClickNIDBackPhoto();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.Set_Partner_DriverAccount_No();
		//nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.ClickCreateAccountButton();
		Verify.verifyEquals(nidscreen.TextViewTitle.getText(), "SUCCESS",
				"Account creation success message validation failed");
		Verify.verifyEquals(nidscreen.TextViewDescription.getText(),
				"Your sign up request has been placed for approval. You will get a confirmation SMS within 24 hours.",
				"Detailed success Message validation failed");
		//Thread.sleep(15000);
		nidscreen.ClickSuccessOKButton();
		trucksscreen.clickBurgerMenu();
		Thread.sleep(1500);
		//System.out.println(profile.ProfileMenu.size());
		
		/*for(int i=0; i<9; i++) {
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		}
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		*/
		//new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		//driver.findElementById("logout").click();
		
		
		//profile.LogoutLink();
		
		//new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		//driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[8]/android.widget.CheckedTextView").click();
		//scrollTo("Logout");
		nidscreen.scrollTo("Logout");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Logout\")").click();
		profile.clickYesLogoutButton();	
		signin.SignInAPI("", "ANDROID", mobileno, "pass01", 3);
		userid = Integer.parseInt(signin.getUserID());
		System.out.println("Partner userid is: "+userid);
		
	}

	
	@Ignore(value="300")
	public void SignupTest_AsDriver_ANDPartner() throws IOException, InterruptedException {
		
		
		HomeScreen homescreen = new HomeScreen(driver);
		MobielVerificationScreen mobileverificationscreen = new MobielVerificationScreen(driver);
		GetOTP otp = new GetOTP();
		PersonalInformationScreen personalinformationscreen = new PersonalInformationScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		homescreen.clickSignUpButton();
		mobileverificationscreen.setMobileNumber(mobileno1);
		mobileverificationscreen.TickconditionsCheckbox();
		mobileverificationscreen.ClickSendVerificationSMSButton();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/snackbar_text").getText(),
				"Your OTP will expire in 15 minutes", "User not found validation failed");
		
		otp.OTPAPI(mobileno1);
		mobileverificationscreen.setPinNumber(otp.getOTP());
		mobileverificationscreen.ClickNextButton();
		personalinformationscreen.setEmail(new Randomgenerator().generateRandomemailID());
		personalinformationscreen.setPassword("pass01");
		personalinformationscreen.setReferralCode("01" + new Randomgenerator().generateRandomNumber(9));
		driver.navigate().back();
		personalinformationscreen.ClickNextButton();
		nidscreen.setName(new Randomgenerator().generateRandomName());
		nidscreen.setDOB();
		nidscreen.setDistrict();
		nidscreen.setNID(new Randomgenerator().generateRandomNumber(17));
		driver.navigate().back();
		nidscreen.ClickNIDFrontPhoto();
		nidscreen.ClickCameraSelection();
		//nidscreen.ClickAllowPermission();
		//nidscreen.ClickAllowPermission();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		Thread.sleep(2500);
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		nidscreen.ClickNIDBackPhoto();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		
		nidscreen.Set_Partner_DriverAccount_Yes();
		nidscreen.SetDriverLicenseNumber(new Randomgenerator().generateRandomNumber(15));
		driver.navigate().back();
		nidscreen.setDriverLicenseExpiryDate();
		nidscreen.clickDriverLicenseOKButton();
		nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		
		nidscreen.clickDriverLicenseFrontPhoto();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		
		nidscreen.clickDriverLicenseBackPhoto();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		
		nidscreen.ClickCreateAccountButton();
		Verify.verifyEquals(nidscreen.TextViewTitle.getText(), "SUCCESS",
				"Account creation success message validation failed");
		Verify.verifyEquals(nidscreen.TextViewDescription.getText(),
				"Your sign up request has been placed for approval. You will get a confirmation SMS within 24 hours.",
				"Detailed success Message validation failed");
		nidscreen.ClickSuccessOKButton();
		trucksscreen.clickBurgerMenu();
		Thread.sleep(1500);
		//System.out.println(profile.ProfileMenu.size());
		
		for(int i=0; i<9; i++) {
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		}
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		//new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		//driver.findElementById("logout").click();
		
		
		//profile.LogoutLink();
		
		//new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		//driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[8]/android.widget.CheckedTextView").click();
		profile.clickYesLogoutButton();	
		
	}
	
	
	
	@Test(priority=1)
	public void LoginTest_InvalidUser() throws IOException {
		
		setUp();
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		
		Verify.verifyEquals(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.ejogajog.fleetowner:id/snackbar_text")))
						.getText(),
						"No such user found.", "User not found validation failed");

		
		
		
		
		//Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/snackbar_text").getText(),
			//	"No such user found.", "User not found validation failed");
		driver.navigate().back();
		
	}
	
	@Test(priority=3)
	public void AddNewTruck_withMandtoryFields_ToActivePartner() throws IOException, InterruptedException {
		
		TrucksScreen truckscreen = new TrucksScreen(driver);
		AddTruck addtruck = new AddTruck(driver);
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		userlist.Pending_FleetOwners_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 3, userlist.getUserID());
		
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		truckscreen.clickAddTruckIcon();
		addtruck.setRegNoType();
		addtruck.setRegNoSubType();
		addtruck.setRegNoPartOne(regnop1);
		addtruck.setRegNoPartTwo(regnop2);
		addtruck.setRegistrationCertificateDate();
		nidscreen.scrollTo("NEXT");
		addtruck.clickNextButton();
		//Thread.sleep(15000);
		System.out.println(driver.getPageSource());
		try {
			System.out.println("Before finding element");
			driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRUCK REGISTRATION\")").click();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		//addtruck.clickTruckRegistrationPhoto();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Truck Registration Back\")").click();
		
		//addtruck.clickTruckRegistrationBackPhoto();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("NEXT");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		
		//addtruck.clickTruckFrontWithNumberplatePhoto();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"FRONT WITH NUMBER\")").click();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("SUBMIT FOR APPROVAL");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUBMIT FOR APPROVAL\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Your truck\")").getText(), "Your truck information has been sent\n" + 
				" for approval. You will get a\n" + 
				" confirmation after approval.");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"GOT IT\")").click();
		
		
		
	}
	
	@Test(priority=4)
	public void checkTruckinPendingTab() {
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PENDING\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA-THA\")").getText(), "DHAKA-THA-"+regnop1+"-"+regnop2);
		
	}
	
	@Test(priority=5)
	public void ApproveTruck() throws IOException {
		
		System.out.println(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA\")").getText());;
		AdminLogin adminlogin = new AdminLogin();
		GIMSignIn signin = new GIMSignIn();
		TruckListing trucklist = new TruckListing();
		Truck_Action truckaction = new Truck_Action();
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		
		trucklist.PendingTruckslist(adminlogin.getauthToken(), mobileno, 1, 10);
		System.out.println("Truck id is: "+trucklist.getTruckID());
		truckaction.ApproveTruck(adminlogin.getauthToken(), Integer.parseInt(trucklist.getTruckID()));
		
	}
	
	@Test(priority=6)
	public void CheckTruckisDisplayed_inApprovedTab() {
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"APPROVED\")").click();
		System.out.println(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA-THA\")").getText());
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA-THA\")").getText(), "DHAKA-THA-"+regnop1+"-"+regnop2);
		
	}
	
	@Test(priority=7)
	public void AcceptDriverAssociationRequest() throws IOException {
		
		signup.NewDriver_ForExistingFleetOwner(userid);
		driver.findElementByAccessibilityId("DRIVERS").click();
		driver.findElementByAccessibilityId("REQUESTS").click();
		//driver.findElementByAccessibilityId("")
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DRIVERS\")").click();
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"APPROVE\")").click();
		driver.findElementById("com.ejogajog.fleetowner:id/button_place_bid").click();
	}
	
	@Test(priority=8)
	public void CheckNewTrip_PushNotification_PlaceBid() throws IOException, InterruptedException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		Thread.sleep(55000);
		
		
		driver.openNotifications();
	    List<WebElement> allnotifications=driver.findElements(By.id("android:id/title"));

	    for (WebElement webElement : allnotifications) {
	        System.out.println(webElement.getText());
	        if(webElement.getText().contains("New Trip")){
	            System.out.println("Notification found");
	            break;
	        }
	    }
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"New Trip\")").click();
	    Thread.sleep(1000);
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Enter total amount\")").sendKeys("1500");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Enter advance amount\")").sendKeys("500");
	    nidscreen.scrollTo("PLACE MY BID");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose a truck\")").click();
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		   
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose truck location\")").click();
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SET TRUCK LOCATION\")").click();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose a driver\")").click();
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		   
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLACE MY BID\")").click();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLACE BID\")").click();
	        
	}
	
	@Test(priority=11)
	public void CheckBidAcceptedNotification() throws InterruptedException {
		
		Thread.sleep(25000);
		driver.openNotifications();
	    List<WebElement> allnotifications=driver.findElements(By.id("android:id/title"));

	    for (WebElement webElement : allnotifications) {
	        System.out.println(webElement.getText());
	        if(webElement.getText().contains("Bid Accepted")){
	            System.out.println("Notification found");
	            break;
	        }
	    }

		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid Accepted\")").isDisplayed(), true, "Bid accepted notification failed");
		
	}
	
	@Test(priority=13)
	public void CheckTripCancelledNotification() throws InterruptedException {
		
		Thread.sleep(10000);
		driver.openNotifications();
	    List<WebElement> allnotifications=driver.findElements(By.id("android:id/title"));

	    for (WebElement webElement : allnotifications) {
	        System.out.println(webElement.getText());
	        if(webElement.getText().contains("Trip Cancelled")){
	            System.out.println("Notification found");
	            break;
	        }
	    }

		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Trip Cancelled\")").isDisplayed(), true, "Bid cancelled notification failed");
	
	}
	
	
	
	
	
}
