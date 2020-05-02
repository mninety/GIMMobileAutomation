package Partner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import Customer.FunctionalTest5;
import TestBase.AppiumBase;
import TestBase.AppiumBase2;
import Utils.TestData;
import Utils.Tracker_Action;
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
public class FunctionalTest2 extends AppiumBase2 {

	AndroidDriver driver;
	TestData testdata;

	GIMSignIn signin = new GIMSignIn();
	GIMSignUp signup = new GIMSignUp();
	public int userid;
	public int truckid;
	public String regnop1 = new Randomgenerator().generateRandomNumber(2);
	public String regnop2 = new Randomgenerator().generateRandomNumber(4);
	public static String regnop3 = new Randomgenerator().generateRandomNumber(2);
	public static String regnop4 = new Randomgenerator().generateRandomNumber(4);
	public static String regnop5 = new Randomgenerator().generateRandomNumber(2);
	public static String regnop6 = new Randomgenerator().generateRandomNumber(4);
	Tracker_Action addTracker = new Tracker_Action();
	public String admin_authToken;
	
	public final static String mobileno = "011"+new Randomgenerator().generateRandomNumber(8);
	public String mobileno1 = "011"+new Randomgenerator().generateRandomNumber(8);
	public final String invitedmobno = "011"+new Randomgenerator().generateRandomNumber(8);
	public WebDriverWait wait;
	public static String drivername = new Randomgenerator().generateRandomName();
	public final String nonregistereddrivermobileno = "011"+new Randomgenerator().generateRandomNumber(8);
	
	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("udid", "192.168.129.101:5555");
		//capabilities.setCapability("isHeadless", true);
		capabilities.setCapability("udid", testdata.properties.getProperty("devicefo"));
		capabilities.setCapability("deviceName", testdata.properties.getProperty("devicefo"));
		capabilities.setCapability("newCommandTimeout", 2400);
		//capabilities.setCapability("AUTOMATION_NAME", "UiAutomator2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("autoGrantPermissions ", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage", testdata.properties.getProperty("appPackage1"));
		capabilities.setCapability("appActivity", testdata.properties.getProperty("appActivity1"));
		capabilities.setCapability("skipUnlock", true);
		//capabilities.setCapability("no-reset",false);
		capabilities.setCapability("skipDeviceInitialization", true);
		// capabilities.setCapability("resetKeyboard", true);
		// capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("ignoreUnimportantViews", true);
		capabilities.setCapability("disableAndroidWatchers", true);
		capabilities.setCapability("systemPort", 8201);
		// capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("automationName", "uiautomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5000);
		
	}

	@Test(priority=2)
	public void SignupTest_AsPartner() throws IOException, InterruptedException {
		setUp();
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
		//personalinformationscreen.setReferralCode("019" + new Randomgenerator().generateRandomNumber(8));
		driver.navigate().back();
		personalinformationscreen.ClickNextButton();
		nidscreen.setName(new Randomgenerator().generateRandomName());
		nidscreen.setDOB();
		nidscreen.setDistrict();
		nidscreen.setNID(new Randomgenerator().generateRandomNumber(17));
		driver.navigate().back();
		nidscreen.scrollTo("Back");
		nidscreen.ClickNIDFrontPhoto();
		nidscreen.ClickCameraSelection();
		try{
			try{
					nidscreen.ClickAllowPermission();
				}catch(Exception e) 
				{
					nidscreen.ClickAllowPermission();
				}
			}
			catch(Exception e) {
			
		}
		try{
			try{
					nidscreen.ClickAllowPermission();
				}catch(Exception e) 
				{
					nidscreen.ClickAllowPermission();
				}
			}
			catch(Exception e) {
			
		}
		
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		
		//Thread.sleep(5000);
		try {
			nidscreen.ClickDoneButton();
		}catch(Exception e) {
		
			try {
			driver.findElementById("com.android.camera2:id/done_button").click();
			}catch(Exception e1) {
				try {
					driver.findElementById("com.mediatek.camera:id/btn_done").click();
				} catch (Exception e2) {
					// TODO: handle exception
					driver.findElementByAccessibilityId("OK").click();
				}
			//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();

			}	
		}
		try {
			nidscreen.ClickDoneButton();
		}catch(Exception e) {
			
		}
		nidscreen.ClickOkButton();
		Thread.sleep(2500);
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		nidscreen.ClickNIDBackPhoto();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(5000);
		try {
			nidscreen.ClickDoneButton();
		}catch(Exception e) {
			
			try {
				driver.findElementById("com.android.camera2:id/done_button").click();
				}catch(Exception e1) {
					try {
						driver.findElementById("com.mediatek.camera:id/btn_done").click();
					} catch (Exception e2) {
						// TODO: handle exception
						driver.findElementByAccessibilityId("OK").click();
					}
					//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
				}
		}
		try {
			nidscreen.ClickDoneButton();
		}
		catch(Exception e) {
			
		}
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.Set_Partner_DriverAccount_No();
		//nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.ClickCreateAccountButton();
		Verify.verifyEquals(nidscreen.TextViewTitle.getText(), "SUCCESS",
				"Account creation success message validation failed");
		Verify.verifyEquals(nidscreen.TextViewDescription.getText().contains("Your sign up request has been placed for approval."),
				true, "Detailed success Message validation failed");
		//Thread.sleep(15000);
		nidscreen.ClickSuccessOKButton();
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
	
		Thread.sleep(5000);
		try {
		trucksscreen.clickBurgerMenu();
		}catch(Exception e) {
			driver.findElementByAccessibilityId("Open Drawer").click();
		}
	
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
		admin_authToken = adminlogin.getauthToken();
		userlist.Pending_FleetOwners_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 3, userlist.getUserID());
		
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		driver.findElementByAccessibilityId("TRUCKS").click();
		truckscreen.clickAddTruckIcon();
		//addtruck.setRegNoType();
		//addtruck.setRegNoSubType();
		try {
		addtruck.setRegNoPartOne(regnop1);
		}catch(Exception e) {
			driver.navigate().back();
			nidscreen.scrollTo("DETAILS");
			addtruck.setRegNoPartOne(regnop1);
			
		}
		addtruck.setRegNoPartTwo(regnop2);
		driver.navigate().back();
		//addtruck.setRegistrationCertificateDate();
		nidscreen.scrollTo("ROUTE PERMIT");
		//addtruck.clickNextButton();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REGISTRATION\")").click();
		
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
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		nidscreen.scrollTo("TRUCK REGISTRATION BACK");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRUCK REGISTRATION BACK\")").click();
		
		//addtruck.clickTruckRegistrationBackPhoto();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
		}catch(Exception e) {
			
		}
		try{
			nidscreen.ClickOkButton();
		}catch(Exception e) {
			
		}
		nidscreen.scrollTo("FRONT");
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		
		//addtruck.clickTruckFrontWithNumberplatePhoto();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"FRONT\")").click();
		nidscreen.scrollTo("PLEASE ADD PHOTO");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLEASE ADD PHOTO\")").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {nidscreen.ClickDoneButton();	
		}
		catch(Exception e) {
			
		}
		nidscreen.ClickOkButton();
		//nidscreen.scrollTo("SUBMIT FOR APPROVAL");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUBMIT\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Your truck\")").getText().length(), 124, "Truck submitted for admin approval message validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"GOT IT\")").click();
		
		
		
	}
	
	@Test(priority=4)
	public void checkTruckinPendingTab() {
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PENDING\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA\")").getText(), "DHAKA METRO-TA-"+regnop1+"-"+regnop2);
		
	}
	
	@Test(priority=6)
	public void ApproveTruck_AndAddTracker() throws IOException, InterruptedException {
		
		System.out.println(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA\")").getText());;
		AdminLogin adminlogin = new AdminLogin();
		GIMSignIn signin = new GIMSignIn();
		TruckListing trucklist = new TruckListing();
		Truck_Action truckaction = new Truck_Action();
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		
		trucklist.PendingTruckslist(adminlogin.getauthToken(), mobileno, 1, 10);
		System.out.println("Truck id is: "+trucklist.getTruckID());
		truckid = Integer.parseInt(trucklist.getTruckID());
		truckaction.ApproveTruck(adminlogin.getauthToken(), Integer.parseInt(trucklist.getTruckID()));
		/*Thread.sleep(10000);
		// Add tracker to approved truck
		addTracker.AddTruckTrackerAPI(adminlogin.getauthToken(), truckid, "M2M", 
					testdata.properties.getProperty("Trackersecretkey"),
					testdata.properties.getProperty("Trackerusername"), 
					testdata.properties.getProperty("Trackerpassword"), 
					testdata.properties.getProperty("vehicleid"));
		
		//Delete tracker from approved truck
				//addTracker.DeleteTruckTrackerAPI(adminlogin.getauthToken(), truckid);

		//driver.findElementById("com.ejogajog.fleetowner:id/iv_drawer_icon").click();
		
		trucksscreen.clickBurgerMenu();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Tracker\")").click();
		
		Thread.sleep(10000);
		
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(532, 940)).perform();
		Thread.sleep(2000);
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OFF DUTY\")").isDisplayed(), true,  "Truck is offduty validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OFF DUTY\")").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_screen_title").getText(), "TRUCK DETAIL", "Truck detail screen is not displayed");
		driver.navigate().back();
		
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		
		driver.findElementById("iv_refresh_map").click();
		
		touchAction.tap(new PointOption().withCoordinates(532, 940)).perform();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OFF DUTY\")").isDisplayed(), true,  "Truck is offduty validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OFF DUTY\")").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_screen_title").getText(), "TRUCK DETAIL", "Truck detail screen is not displayed");
		driver.navigate().back();
		
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		driver.findElementById("iv_zoom_out").click();
		
		touchAction.tap(new PointOption().withCoordinates(532, 940)).perform();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OFF DUTY\")").isDisplayed(), true,  "Truck is offduty validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OFF DUTY\")").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_screen_title").getText(), "TRUCK DETAIL", "Truck detail screen is not displayed");
		driver.navigate().back();
		
		driver.findElementById("menu_icon").click();
		
		driver.findElementById("fab_list").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRUCKS\")").click();
		*/
	}
	
	@Test(priority=7)
	public void CheckTruckisDisplayed_inApprovedTab() {
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"APPROVED\")").click();
		System.out.println(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA\")").getText());
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DHAKA\")").getText(), "DHAKA METRO-TA-"+regnop1+"-"+regnop2);
		
	}
	
	
	/**
	 * @throws InterruptedException 
	 * 
	 * 
	 * 
	 */
	
	@Test(priority=8)
	public void SearchforNonregisteredDriver_Validation() throws IOException, InterruptedException {
		driver.findElementByAccessibilityId("DRIVERS").click();
		Thread.sleep(5000);
		driver.findElementById("com.ejogajog.fleetowner:id/menu_add_driver").click();
		driver.findElementById("com.ejogajog.fleetowner:id/edit_text_mobile").sendKeys(nonregistereddrivermobileno);
		driver.findElementById("com.ejogajog.fleetowner:id/button_invite_driver").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_driver_not_found").getText(), "YOUR REQUESTED DRIVER IS NOT REGISTERED", "driver not registered validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_driver_mobile_number").getText(), nonregistereddrivermobileno, "mobile number validation failed");
		
	}
	
	@Test(priority=9)
	public void Register_NonregisteredDriverToGIMPlatform_Validation() throws IOException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REGISTER AS NEW DRIVER\")").click();
		driver.findElementById("com.ejogajog.fleetowner:id/edit_text_user_name").sendKeys(new Randomgenerator().generateRandomName());
		driver.findElementById("com.ejogajog.fleetowner:id/edit_text_dob").click();
		driver.findElementById("android:id/button1").click();
		driver.findElementById("com.ejogajog.fleetowner:id/tv_driver_photo_label").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		nidscreen.scrollTo("Mandatory");
		driver.findElementById("com.ejogajog.fleetowner:id/tv_license_front").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		driver.findElementById("com.ejogajog.fleetowner:id/tv_license_back").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		driver.findElementById("com.ejogajog.fleetowner:id/checkbox_termOfUse").click();
		driver.findElementById("com.ejogajog.fleetowner:id/button_create_account").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/textViewDescription").getText(), "Driver sign up request has been placed for approval. They will get a confirmation SMS within 24 hours. Please contact customer service on +8809768111444 for further assistance.", "Driver registered successfully validation failed");
		driver.findElementById("com.ejogajog.fleetowner:id/button_ok").click();
		
		
	}
	
	@Test(priority=10)
	public void NewlyAddedDriver_isin_PendingState_Validation() {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"MY DRIVERS\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PENDING\")").getText(), "Pending", "Driver status validation failed");
	
	}
	
	@Test(priority=11)
	public void ApproveDriverByAdmin_and_ValidateDriverstatusonApp() throws IOException {
		
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		admin_authToken = adminlogin.getauthToken();
		userlist.Pending_Drivers_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 2, userlist.getUserID());
		driver.findElementByAccessibilityId("DRIVERS").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"MY DRIVERS\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACTIVE\")").getText(), "Active", "Driver status validation failed");
		
	}
	/**
	 * 
	 * 
	 * 
	 */
	@Test(priority=12)
	public void AcceptDriverAssociationRequest() throws IOException {
		
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
			
		signup.NewDriver_ForExistingFleetOwner(userid);
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		admin_authToken = adminlogin.getauthToken();
		userlist.Pending_Drivers_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 2, userlist.getUserID());
	
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTS\")").click();
		driver.findElementById("com.ejogajog.fleetowner:id/text_view_approve").click();
		driver.findElementById("com.ejogajog.fleetowner:id/button_place_bid").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"MY DRIVERS\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACTIVE\")").getText(), "Active", "Driver status validation failed");
		Verify.verifyEquals(driver.getPageSource().contains("PENDING"), false, "driver status validation failed");
	}
	
	@Test(priority=13)
	public void PlaceBidonTrip() throws IOException, InterruptedException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		Thread.sleep(5000);
		driver.findElementByAccessibilityId("MY TRIPS").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"AVAILABLE\")").click();
		
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"10 Gulshan\")").click();
			
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Enter total amount\")").click();
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '1500'"));
		driver.navigate().back();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Enter advance amount\")").click();
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '500'"));
		driver.navigate().back();
	    Thread.sleep(1000);
	    try {
	    	new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 1)).release().perform();
	    	new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 1)).release().perform();
			
	    	//nidscreen.scrollTo("PLACE MY BID");
	    }
	    catch(Exception e) {
	    	
	    }
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose a truck\")").click();
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		   
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Open\")").click();
	    
	    //driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose\")").click();
	    
	    for(int i=0; i<8; i++) {
	    	driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
	    }
	    
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
	    
	    Thread.sleep(7500);
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SET TRUCK LOCATION\")").click();
	    Thread.sleep(1500);
	    nidscreen.scrollTo("Select a driver");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose a driver\")").click();
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		   
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
	    //Thread.sleep(2500);
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"5.0\")").click();
	    
	    
	    
	    driver.findElementById("btn_place_my_bid").click();
	    //driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLACE MY BID\")").click();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLACE BID\")").click();
	        
	}
	
	@Test(priority=22)
	public void PlaceBid_thenWithdrawBid() throws IOException, InterruptedException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"AVAILABLE\")").click();
		
		FunctionalTest2.this.PlaceBidonTrip();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ONGOING BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Gulshan\")").click();
		      
		nidscreen.scrollTo("WITHDRAW THIS BID");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"WITHDRAW THIS BID\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, CANCEL\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUCCESS\")").getText(), "SUCCESS", "Bid withdrawn success message validation failed");
		//Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Your bid has been withdrawn.\")").getText(), "Your bid has been withdrawn.", "Bid withdrawn success message validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		//driver.findElementById("com.ejogajog.fleetowner:id/button_ok").click();
		//driver.findElementByAccessibilityId("button_ok").click();
		
	}
	
	@Test(priority=23)
	public void placeBidonTrip_ForFutureCancellation_AfterWinningBid() throws IOException, InterruptedException {
		
		FunctionalTest2.this.PlaceBidonTrip();
		
	}
	
	@Test(priority=25)
	public void CancelWonBid() {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"AVAILABLE\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"WON BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Gulshan\")").click();
		      
		nidscreen.scrollTo("CANCEL THIS TRIP");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CANCEL THIS TRIP\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, CANCEL\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CANCEL TRIP\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUCCESS\")").getText(), "SUCCESS", "Trip cancelled success message validation failed");
		//Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Your Bid has been cancelled.\")").getText(), "Your bid has been cancelled.", "Bid withdrawn success message validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
	
		
	}
	
	
	@Test(priority=26)
	public void NoBidsToDisplayValidation_ForOngoingWonLostBids() throws IOException {
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"AVAILABLE\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ONGOING BIDS\")").click();
		Verify.verifyEquals(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.ejogajog.fleetowner:id/snackbar_text")))
						.getText(),
						"No bids to display", "No ongoing bids validation failed");

		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"WON BIDS\")").click();
		Verify.verifyEquals(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.ejogajog.fleetowner:id/snackbar_text")))
						.getText(),
						"No bids to display", "No won bids validation failed");

		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"LOST BIDS\")").click();
		Verify.verifyEquals(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.ejogajog.fleetowner:id/snackbar_text")))
						.getText(),
						"No bids to display", "No lost bids validation failed");

	}
	
	@Test(priority=27)
	public void InvoicePayments_Validation() throws IOException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"HISTORY\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"INVOICE\")").click();
		
		nidscreen.scrollTo("TOTAL PAID");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,000\")").isDisplayed(), true, "Final payment validation failed");

		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,000\")").getText().contains("1,000"), true, "Final payment validation failed");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"500\")").isDisplayed(), true, "Final payment validation failed");

		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"500\")").getText().contains("500"), true, "Final payment validation failed");
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,500\")").isDisplayed(), true, "Final payment validation failed");

		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,500\")").getText().contains("1,500"), true, "Final payment validation failed");
		driver.navigate().back();		

	}
	
	@Test(priority=28)
	public void AddNewTruckAndDriver_ToUseFor_EditTruckDriver_And_PlaceBid() throws IOException, InterruptedException {
		//setUp();
		TrucksScreen truckscreen = new TrucksScreen(driver);
		AddTruck addtruck = new AddTruck(driver);
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		GIMSignIn signin = new GIMSignIn();
		TruckListing trucklist = new TruckListing();
		Truck_Action truckaction = new Truck_Action();
		
		driver.findElementByAccessibilityId("TRUCKS").click();
		truckscreen.clickAddTruckIcon();
		//addtruck.setRegNoType();
		//addtruck.setRegNoSubType();
		try {
		addtruck.setRegNoPartOne(regnop3);
		}catch(Exception e) {
			driver.navigate().back();
			nidscreen.scrollTo("DETAILS");
			addtruck.setRegNoPartOne(regnop3);
			
		}
		addtruck.setRegNoPartTwo(regnop4);
		driver.navigate().back();
		//addtruck.setRegistrationCertificateDate();
		nidscreen.scrollTo("ROUTE PERMIT");
		//addtruck.clickNextButton();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REGISTRATION\")").click();
		
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
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		nidscreen.scrollTo("TRUCK REGISTRATION BACK");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRUCK REGISTRATION BACK\")").click();
		
		//addtruck.clickTruckRegistrationBackPhoto();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
		}catch(Exception e) {
			
		}
		try{
			nidscreen.ClickOkButton();
		}catch(Exception e) {
			
		}
		nidscreen.scrollTo("FRONT");
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		
		//addtruck.clickTruckFrontWithNumberplatePhoto();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"FRONT\")").click();
		nidscreen.scrollTo("PLEASE ADD PHOTO");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLEASE ADD PHOTO\")").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {nidscreen.ClickDoneButton();	
		}
		catch(Exception e) {
			
		}
		nidscreen.ClickOkButton();
		//nidscreen.scrollTo("SUBMIT FOR APPROVAL");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUBMIT\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Your truck\")").getText().length(), 95, "Truck submitted for admin approval message validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"GOT IT\")").click();
			
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		
		trucklist.PendingTruckslist(adminlogin.getauthToken(), mobileno, 1, 10);
		System.out.println("Truck id is: "+trucklist.getTruckID());
		truckid = Integer.parseInt(trucklist.getTruckID());
		truckaction.ApproveTruck(adminlogin.getauthToken(), Integer.parseInt(trucklist.getTruckID()));
	
		signup.NewDriver_ForExistingFleetOwner(userid, drivername);
		driver.findElementByAccessibilityId("DRIVERS").click();
		driver.findElementByAccessibilityId("REQUESTS").click();
		//driver.findElementByAccessibilityId("")
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DRIVERS\")").click();
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACCEPT\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, ACCEPT\")").click();
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		admin_authToken = adminlogin.getauthToken();
		userlist.Pending_Drivers_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 2, userlist.getUserID());
		
		//driver.findElementById("com.ejogajog.fleetowner:id/button_place_bid").click();
		FunctionalTest2.this.PlaceBidonTrip();
		
	}
	
	@Test(priority=30)
	public void EditTruckAndDriver_For_BookedTrip() throws InterruptedException, IOException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"AVAILABLE\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"WON BIDS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Gulshan\")").click();
		      
		nidscreen.scrollTo("CANCEL THIS TRIP");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"EDIT\")").click();
	
		driver.findElementById("com.ejogajog.fleetowner:id/text_fake_spinner_truck_name").click();
	
		driver.findElementById("com.ejogajog.fleetowner:id/text_user_filter_input").sendKeys("DHAKA METRO-TA-"+regnop3+"-"+regnop4);
		
		driver.findElementById("com.ejogajog.fleetowner:id/text_truck_name").click();
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		//Thread.sleep(5000);
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SET TRUCK LOCATION\")").click();
		Thread.sleep(2500);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		driver.findElementById("com.ejogajog.fleetowner:id/text_user_filter_input").sendKeys(drivername);
		
		driver.findElementById("com.ejogajog.fleetowner:id/viewTextDriverName").click();
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUBMIT\")").click();
			
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_driver_name").getText(), drivername, "New driver assigned for trip validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_truck_reg_number").getText(), "DHAKA METRO-TA-"+regnop3+"-"+regnop4, "New truck assigned for trip validation failed");
		
	}
	
	@Test(priority=32)
	public void PartnerStartTrip() throws IOException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		nidscreen.scrollTo("START TRIP");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"START TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, START\")").click();
		
		
		
	}
	
	@Test(priority=33)
	public void PartnerEndTrip() {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		nidscreen.scrollTo("COMPLETE THIS TRIP");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"COMPLETE THIS TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, END\")").click();
		
		driver.navigate().back();
	}
	
	@Test(priority=34)
	public void ValidateEnglishtoBangla() throws IOException {
		//setUp();
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		ProfileManagementScreen profilescreen = new ProfileManagementScreen(driver);
		
		trucksscreen.clickBurgerMenu();
		profilescreen.ChangeLanguageLink();
		
		FunctionalTest2.this.takesScreenshot(new Randomgenerator().generateRandomName());
		
		
		
	}
	
	 public void takesScreenshot(String testName) throws IOException {
	      File screenshot = driver.getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(screenshot, new File("screenshots/" + testName + ".png"));
	}
	 
	 
	 @Test(priority=35)
	 public void ValidateBanglatoEnglish() throws IOException {
			//setUp();
		 TrucksScreen trucksscreen = new TrucksScreen(driver);
			ProfileManagementScreen profilescreen = new ProfileManagementScreen(driver);
			
			driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,36][84,120]']")).click();
			//trucksscreen.clickBurgerMenu();
			
			driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Switch to English\")").click();
			
			FunctionalTest2.this.takesScreenshot(new Randomgenerator().generateRandomName());
			
			
		}
	
	
	@Test(priority=36)
	public void ValidatePartnerListedNotifications_AreDisplayed() throws IOException {
		setUp();
		NIDScreen nidscreen = new  NIDScreen(driver);
		driver.findElementById("navigation_notifications").click();
		nidscreen.scrollTo("approved");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"approved\")").getText().contains("approved as Partner"), true, "Partner approval notification validation failed");
		nidscreen.scrollTo("completed");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"completed\")").getText().contains("completed"), true, "Partner approval notification validation failed");
		nidscreen.scrollTo("started");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"started\")").getText().contains("started"), true, "Partner approval notification validation failed");
		nidscreen.scrollTo("Accepted");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Accepted\")").getText().contains("Bid Accepted"), true, "Partner approval notification validation failed");
		nidscreen.scrollTo("New Trip");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"New Trip\")").getText().contains("New Trip"), true, "Partner approval notification validation failed");
		
	}
	
	@Test(priority=38)
	public void ValidateNewTripNotification_WhenTapped_Landsuser_onto_PlaceBidScreen() throws IOException, InterruptedException {
		setUp();
		NIDScreen nidscreen = new NIDScreen(driver);
		driver.findElementByAccessibilityId("MY TRIPS").click();
		driver.findElementById("navigation_notifications").click();
		nidscreen.scrollTo("532");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"532\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLACE MY BID\")").getText().contains("PLACE MY BID"), true, "validation failed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Enter total amount\")").click();
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '1500'"));
		driver.navigate().back();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Enter advance amount\")").click();
	    driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '500'"));
		driver.navigate().back();
	    Thread.sleep(1000);
	    try {
	    	new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 1)).release().perform();
	    	new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 1)).release().perform();
			
	    	//nidscreen.scrollTo("PLACE MY BID");
	    }
	    catch(Exception e) {
	    	
	    }
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose a truck\")").click();
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		   
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Open\")").click();
	    
	    
	    
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose truck location\")").click();
	    Thread.sleep(7500);
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SET TRUCK LOCATION\")").click();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Choose a driver\")").click();
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		   
	    //driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
	    //Thread.sleep(2500);
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"5.0\")").click();
	    
	    
	    
	    driver.findElementById("btn_place_my_bid").click();
	    //driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLACE MY BID\")").click();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLACE BID\")").click();
	
		
	}
	
	@Test(priority=40)
	public void ValidateBidAcceptedNotification_Landsuser_onto_StarttripScreen() throws IOException, InterruptedException {
		//setUp();
		NIDScreen nidscreen = new  NIDScreen(driver);
		driver.findElementByAccessibilityId("MY TRIPS").click();
		
		driver.findElementById("navigation_notifications").click();
		//nidscreen.scrollTo("Bid Accepted");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid Accepted\")").getText().contains("Bid Accepted"), true, "validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid Accepted\")").click();
		
		nidscreen.scrollTo("START TRIP");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"START TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, START\")").click();
		driver.navigate().back();
		
	}
	
	@Test(priority=42)
	public void ValidateCompleteTripNotificationWhenTapped_Landsuser_onto_TripDetailsScreen() throws IOException {
		setUp();
		NIDScreen nidscreen = new  NIDScreen(driver);
		driver.findElementByAccessibilityId("MY TRIPS").click();
		
		driver.findElementById("navigation_notifications").click();
		nidscreen.scrollTo("completed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"completed\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRIP DETAILS\")").getText().contains("TRIP DETAILS"), true, "validation failed");
		driver.navigate().back();
		
	}
	
	@Test(priority=43)
	public void Validation_InvitingAlreadyRegisteredPartner_Tobe_Partner() throws IOException {
		
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		trucksscreen.clickBurgerMenu();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Referrals\")").click();
		driver.findElementById("menu_invitation").click();
		//new FunctionalTest5();
		driver.findElementById("com.ejogajog.fleetowner:id/et_mobile_number").sendKeys(mobileno);
		driver.findElementById("com.ejogajog.fleetowner:id/btn_invite_as_partner").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/textViewDescription").getText().contains("Partner"), true, "Mobilenumber already registered as Customer validation failed");
		driver.findElementById("com.ejogajog.fleetowner:id/button_ok").click();
	
	}
	
	@Test(priority=45)
	public void Inviteuser_ToJoinAs_Partner() {
		
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		driver.findElementById("menu_invitation").click();
		driver.findElementById("com.ejogajog.fleetowner:id/et_mobile_number").sendKeys(invitedmobno);
		try {
		driver.findElementById("com.ejogajog.fleetowner:id/btn_invite_as_partner").click();
		}catch(Exception e) {
			driver.navigate().back();
			driver.findElementById("com.ejogajog.fleetowner:id/btn_invite_as_partner").click();
				
		}
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/textViewDescription").getText(), "Your invitation has been successfully sent", "Mobilenumber already registered as Customer validation failed");
		driver.findElementById("com.ejogajog.fleetowner:id/button_ok").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_no_of_not_reg").getText(), "1", "Pending invitation count validation failed");
		trucksscreen.clickBurgerMenu();
		nidscreen.scrollTo("Logout");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Logout\")").click();
		profile.clickYesLogoutButton();	
		
	}
	
	
	
	@Test(priority=47)
	public void Validation_RegisterasNewPartner_ByEnteringReferralCode_ofExistingPartner_whileRegistering() throws IOException, InterruptedException {
		
		HomeScreen homescreen = new HomeScreen(driver);
		MobielVerificationScreen mobileverificationscreen = new MobielVerificationScreen(driver);
		GetOTP otp = new GetOTP();
		PersonalInformationScreen personalinformationscreen = new PersonalInformationScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		homescreen.clickSignUpButton();
		mobileverificationscreen.setMobileNumber(invitedmobno);
		mobileverificationscreen.TickconditionsCheckbox();
		mobileverificationscreen.ClickSendVerificationSMSButton();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/snackbar_text").getText(),
				"Your OTP will expire in 15 minutes", "User not found validation failed");
		
		otp.OTPAPI(invitedmobno);
		mobileverificationscreen.setPinNumber(otp.getOTP());
		mobileverificationscreen.ClickNextButton();
		personalinformationscreen.setEmail(new Randomgenerator().generateRandomemailID());
		personalinformationscreen.setPassword("pass01");
		driver.navigate().back();
		personalinformationscreen.setReferralCode(mobileno);
		driver.navigate().back();
		personalinformationscreen.ClickNextButton();
		nidscreen.setName(new Randomgenerator().generateRandomName());
		nidscreen.setDOB();
		nidscreen.setDistrict();
		nidscreen.setNID(new Randomgenerator().generateRandomNumber(17));
		driver.navigate().back();
		nidscreen.scrollTo("Back");
		nidscreen.ClickNIDFrontPhoto();
		nidscreen.ClickCameraSelection();
		try{
			try{
					nidscreen.ClickAllowPermission();
				}catch(Exception e) 
				{
					nidscreen.ClickAllowPermission();
				}
			}
			catch(Exception e) {
			
		}
		try{
			try{
					nidscreen.ClickAllowPermission();
				}catch(Exception e) 
				{
					nidscreen.ClickAllowPermission();
				}
			}
			catch(Exception e) {
			
		}
		
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		
		//Thread.sleep(5000);
		try {
			nidscreen.ClickDoneButton();
		}catch(Exception e) {
		
			try {
			driver.findElementById("com.android.camera2:id/done_button").click();
			}catch(Exception e1) {
			driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
			}	
		}
		try {
			nidscreen.ClickDoneButton();
		}catch(Exception e) {
			
		}
		nidscreen.ClickOkButton();
		Thread.sleep(2500);
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		nidscreen.ClickNIDBackPhoto();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(5000);
		try {
			nidscreen.ClickDoneButton();
		}catch(Exception e) {
			
			try {
				driver.findElementById("com.android.camera2:id/done_button").click();
				}catch(Exception e1) {
			driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
				}
		}
		try {
			nidscreen.ClickDoneButton();
		}
		catch(Exception e) {
			
		}
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.Set_Partner_DriverAccount_No();
		//nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.ClickCreateAccountButton();
		nidscreen.ClickSuccessOKButton();
		trucksscreen.clickBurgerMenu();
			nidscreen.scrollTo("Logout");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Logout\")").click();
		profile.clickYesLogoutButton();	
		signin.SignInAPI("", "ANDROID", mobileno, "pass01", 3);
		userid = Integer.parseInt(signin.getUserID());
		System.out.println("Partner userid is: "+userid);
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		admin_authToken = adminlogin.getauthToken();
		userlist.Pending_FleetOwners_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 3, userlist.getUserID());
		
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		System.out.println(mobileno);
		System.out.println(invitedmobno);
		
		trucksscreen.clickBurgerMenu();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Referrals\")").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_no_of_reg").getText(), "1", "Registered users count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_no_of_not_reg").getText(), "0", "Pending invitation count validation failed");
	
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_mobile_no").getText(), invitedmobno, "Invited user registered validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_reg_as").getText(), "PARTNER", "User registered as partner validation failed");
		
		trucksscreen.clickBurgerMenu();
		nidscreen.scrollTo("Logout");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Logout\")").click();
		profile.clickYesLogoutButton();	
		
		
	}
	
	@Test(priority=50)
	public void placebidontrip_createdbyreferredcustomer_and_ValidateEarningsforreferredcustomer() throws IOException, InterruptedException {
		
		TrucksScreen truckscreen = new TrucksScreen(driver);
		AddTruck addtruck = new AddTruck(driver);
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		NIDScreen nidscreen = new NIDScreen(driver);
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		TruckListing trucklist = new TruckListing();
		Truck_Action truckaction = new Truck_Action();
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		admin_authToken = adminlogin.getauthToken();
		userlist.Pending_FleetOwners_List(adminlogin.getauthToken(), 1, 10);
		
		//Approve partner by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 3, userlist.getUserID());
		
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(invitedmobno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		driver.findElementByAccessibilityId("TRUCKS").click();
		truckscreen.clickAddTruckIcon();
		//addtruck.setRegNoType();
		//addtruck.setRegNoSubType();
		try {
		addtruck.setRegNoPartOne(regnop5);
		}catch(Exception e) {
			driver.navigate().back();
			nidscreen.scrollTo("DETAILS");
			addtruck.setRegNoPartOne(regnop5);
			
		}
		addtruck.setRegNoPartTwo(regnop6);
		driver.navigate().back();
		//addtruck.setRegistrationCertificateDate();
		nidscreen.scrollTo("ROUTE PERMIT");
		//addtruck.clickNextButton();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REGISTRATION\")").click();
		
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
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		nidscreen.scrollTo("TRUCK REGISTRATION BACK");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TRUCK REGISTRATION BACK\")").click();
		
		//addtruck.clickTruckRegistrationBackPhoto();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
		}catch(Exception e) {
			
		}
		try{
			nidscreen.ClickOkButton();
		}catch(Exception e) {
			
		}
		nidscreen.scrollTo("FRONT");
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		
		//addtruck.clickTruckFrontWithNumberplatePhoto();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"FRONT\")").click();
		nidscreen.scrollTo("PLEASE ADD PHOTO");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLEASE ADD PHOTO\")").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {nidscreen.ClickDoneButton();	
		}
		catch(Exception e) {
			
		}
		nidscreen.ClickOkButton();
		//nidscreen.scrollTo("SUBMIT FOR APPROVAL");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUBMIT\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Your truck\")").getText().length(), 95, "Truck submitted for admin approval message validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"GOT IT\")").click();
		
		
		
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		
		trucklist.PendingTruckslist(adminlogin.getauthToken(), invitedmobno, 1, 10);
		System.out.println("Truck id is: "+trucklist.getTruckID());
		truckid = Integer.parseInt(trucklist.getTruckID());
		truckaction.ApproveTruck(adminlogin.getauthToken(), Integer.parseInt(trucklist.getTruckID()));
		final String newdrivermobno = "011"+new Randomgenerator().generateRandomNumber(8);
		driver.findElementByAccessibilityId("DRIVERS").click();
		Thread.sleep(5000);
		driver.findElementById("com.ejogajog.fleetowner:id/menu_add_driver").click();
		driver.findElementById("com.ejogajog.fleetowner:id/edit_text_mobile").sendKeys(newdrivermobno);
		driver.findElementById("com.ejogajog.fleetowner:id/button_invite_driver").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REGISTER AS NEW DRIVER\")").click();
		driver.findElementById("com.ejogajog.fleetowner:id/edit_text_user_name").sendKeys(new Randomgenerator().generateRandomName());
		driver.findElementById("com.ejogajog.fleetowner:id/edit_text_dob").click();
		driver.findElementById("android:id/button1").click();
		driver.findElementById("com.ejogajog.fleetowner:id/tv_driver_photo_label").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		nidscreen.scrollTo("Mandatory");
		driver.findElementById("com.ejogajog.fleetowner:id/tv_license_front").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		driver.findElementById("com.ejogajog.fleetowner:id/tv_license_back").click();
		nidscreen.ClickCameraSelection();
		//Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//Thread.sleep(3000);
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		}catch(Exception e) {
			
		}
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}
		catch(Exception e) {
		}
		try {
			nidscreen.ClickDoneButton();	
			
		}catch(Exception e) {
			
		}
		try {
		nidscreen.ClickOkButton();
		}catch (Exception e) {
			
		}
		driver.findElementById("com.ejogajog.fleetowner:id/checkbox_termOfUse").click();
		driver.findElementById("com.ejogajog.fleetowner:id/button_create_account").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/textViewDescription").getText(), "Driver sign up request has been placed for approval. They will get a confirmation SMS within 24 hours. Please contact customer service on +8809768111444 for further assistance.", "Driver registered successfully validation failed");
		driver.findElementById("com.ejogajog.fleetowner:id/button_ok").click();
	
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"MY DRIVERS\")").click();
	
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		admin_authToken = adminlogin.getauthToken();
		userlist.Pending_Drivers_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 2, userlist.getUserID());
		
		FunctionalTest2.this.PlaceBidonTrip();
		
		driver.findElementById("navigation_notifications").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"You are rewarded Taka 50\")").isDisplayed(), true, "Reward notification validation failed");
		
		try {
			trucksscreen.clickBurgerMenu();
			}catch(Exception e) {
				try {
				driver.findElementByAccessibilityId("Navigate up").click();
				}catch(Exception e1) {
					driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
							
				}
				}
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Referrals\")").click();
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"HISTORY\")").click();
		}catch(Exception e) {
			driver.findElementByAccessibilityId("History").click();
		}
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_amount_earned").getText(), "50", "Total amount earned validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_amount_paid").getText(), "0", "Total amount paid validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_outstanding").getText(), "0", "Total outstanding amount validation failed");
		
		driver.findElementById("navigation_notifications").click();
		try {
			trucksscreen.clickBurgerMenu();
			}catch(Exception e) {
				try {
				driver.findElementByAccessibilityId("Navigate up").click();
				}catch(Exception e1) {
					driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
							
				}
			}
		
		nidscreen.scrollTo("Logout");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Logout\")").click();
		profile.clickYesLogoutButton();	
	
	}
	
	@Test(priority=53)
	public void ValidateEarnings_forreferre() {
		
		TrucksScreen truckscreen = new TrucksScreen(driver);
		AddTruck addtruck = new AddTruck(driver);
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		NIDScreen nidscreen = new NIDScreen(driver);
		TruckListing trucklist = new TruckListing();
		Truck_Action truckaction = new Truck_Action();
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		driver.findElementById("navigation_notifications").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"You are rewarded Taka 50\")").isDisplayed(), true, "Reward notification validation failed");
		
		try {
			truckscreen.clickBurgerMenu();
			}catch(Exception e) {
				try {
				driver.findElementByAccessibilityId("Navigate up").click();
				}catch(Exception e1) {
					driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
							
				}
				}
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Referrals\")").click();
		try {
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"HISTORY\")").click();
		}catch(Exception e) {
			driver.findElementByAccessibilityId("History").click();
		}
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_amount_earned").getText(), "50", "Total amount earned validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_total_amount_paid").getText(), "0", "Total amount paid validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.fleetowner:id/tv_outstanding").getText(), "0", "Total outstanding amount validation failed");
		
		
		
	}
	
	
	
	
}
