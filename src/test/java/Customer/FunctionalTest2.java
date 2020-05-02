package Customer;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import TestBase.AppiumBase;
import TestBase.AppiumBase2;
import Utils.TestData;
import Utils.Verify;
import Utils.PendingUser_Listing;
import Utils.User_Action;
import Utils.AdminLogin;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import Utils.Randomgenerator;
import Utils.GetOTP;
import Utils.OnCamera;

//
@SuppressWarnings("rawtypes")
@Listeners(Utils.TestMethodListener.class)
public class FunctionalTest2 extends AppiumBase2 {

	AndroidDriver driver;
	TestData testdata;

	String mobileno = new Randomgenerator().generateRandomNumber(11);

	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("udid", "d9aa151");
		capabilities.setCapability("deviceName", "d9aa151");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("autoGrantPermissions ", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage", testdata.properties.getProperty("appPackage"));
		capabilities.setCapability("appActivity", testdata.properties.getProperty("appActivity"));
		capabilities.setCapability("skipUnlock", true);
		capabilities.setCapability("skipDeviceInitialization", true);
		// capabilities.setCapability("resetKeyboard", true);
		// capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("ignoreUnimportantViews", true);
		capabilities.setCapability("disableAndroidWatchers", true);
		// capabilities.setCapability("autoGrantPermissions", true);
		// capabilities.setCapability("automationName", "uiautomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority=2)
	public void SignupTest() throws IOException, InterruptedException {

		HomeScreen homescreen = new HomeScreen(driver);
		MobielVerificationScreen mobileverificationscreen = new MobielVerificationScreen(driver);
		GetOTP otp = new GetOTP();
		PersonalInformationScreen personalinformationscreen = new PersonalInformationScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		homescreen.clickSignUpButton();
		mobileverificationscreen.setMobileNumber(mobileno);
		mobileverificationscreen.TickconditionsCheckbox();
		mobileverificationscreen.ClickSendVerificationSMSButton();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/snackbar_text").getText(),
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
		nidscreen.ClickCreateAccountButton();
		Verify.verifyEquals(nidscreen.TextViewTitle.getText(), "SUCCESS",
				"Account creation success message validation failed");
		Verify.verifyEquals(nidscreen.TextViewDescription.getText(),
				"Your sign up request has been placed for approval. You will get a confirmation SMS within 24 hours.",
				"Detailed success Message validation failed");
		nidscreen.ClickSuccessOKButton();
		mytripsscreen.clickBurgerMenu();
		profile.LogoutLink();
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
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/snackbar_text").getText(),
				"No such user found.", "User not found validation failed");
		driver.navigate().back();

	}

	@Test(priority=3)
	public void LoginTest_validUser() throws IOException, InterruptedException {

		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		HomeScreen homescreen = new HomeScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		homescreen.clickLoginButton();
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		//mytripsscreen.clickRequestedTab();
		Verify.verifyEquals(mytripsscreen.NewUserWelcomeMessageonRequestedTab.getText(),
				"Welcome! Please create a \n new trip to get started.", "Create a new trip message validation failed");
		Verify.verifyEquals(mytripsscreen.NewUserWelcomeMessageonRequestedTab1.getText(),
				"Once created, all of your trip requests \n will appear here.", "Create a new trip message validation failed");
		/*mytripsscreen.clickBookedTab();
		Verify.verifyEquals(mytripsscreen.NewUserMessageonRelevantTabs.getText(), 
				"No Booked Trips", "No booked trips message validation failed");
		mytripsscreen.clickLiveTab();
		Verify.verifyEquals(mytripsscreen.NewUserMessageonRelevantTabs.getText(),
				"No Live Trips", "No live trips message validation failed");
		mytripsscreen.clickHistoryTab();
		Verify.verifyEquals(mytripsscreen.NewUserMessageonRelevantTabs.getText(),
				"No History", "No history message validation failed");
		*/
		mytripsscreen.clickBurgerMenu();
		profile.LogoutLink();
		profile.clickYesLogoutButton();

	}
	
	@Test(priority=5)
	public void CreateTrip_ForApprovedUser() throws IOException, InterruptedException {

		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		HomeScreen homescreen = new HomeScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		CreateTripScreen createtripscreen = new CreateTripScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		OnCamera camera = new OnCamera();
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		//Admin approving the  user
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		userlist.Pending_Customers_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 5, userlist.getUserID());
		
		homescreen.clickLoginButton();
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		mytripsscreen.clickCreateTripIcon_Otrips();
		createtripscreen.clickPickUpDateField();
			
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		
		createtripscreen.OKButton.click();;
		createtripscreen.clickPickUpTimeField();
		driver.findElementByAccessibilityId("11").click();
		createtripscreen.OKButton.click();
		createtripscreen.NextButton.click();
		try {
		createtripscreen.clickAllowPermissions();
		}catch(Exception e) {
			
		}
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input swipe 500 1000 3000 400"));
		Thread.sleep(1500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		createtripscreen.clickMapsNextButton();
		driver.findElementById("com.ejogajog.serviceseeker:id/ed_truck_type").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='Special Purpose Vehicle']").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/ed_size_type").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='null TON']").click();
		
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='Length']").click();		
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='7.5']").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='Width']").click();		
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='5.5']").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='Height']").click();		
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='2.0']").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/ed_truck_good_type").click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("DONE");
		driver.findElementById("com.ejogajog.serviceseeker:id/iv_camera_img").click();
		nidscreen.ClickCameraSelection();
		nidscreen.ClickCameraShutter();
		nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementById("com.ejogajog.serviceseeker:id/bt_submit_appro").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewTitle").getText(), "SUCCESS", "Trip creation success message missing");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText(), "Your new trip has been created successfully. You can manage this and other trips using    My Trips section.", "Detailed success Message failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		
		mytripsscreen.clickBurgerMenu();
		profile.LogoutLink();
		profile.clickYesLogoutButton();
		
	}

}
