package Customer;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import TestBase.AppiumBase3;
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

import Partner.FunctionalTest;

@SuppressWarnings("rawtypes")
@Listeners(Utils.TestMethodListener.class)
public class FunctionalTest3 extends AppiumBase3 {

	AndroidDriver driver;
	TestData testdata;

	String mobileno = new Randomgenerator().generateRandomNumber(11);
	public WebDriverWait wait;

	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("udid", "192.168.129.101:5555");
		//capabilities.setCapability("isHeadless", true);
		capabilities.setCapability("udid", testdata.properties.getProperty("device_geny_s8"));
		capabilities.setCapability("deviceName", testdata.properties.getProperty("device_geny_s8"));
		capabilities.setCapability("newCommandTimeout", 240);
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
		capabilities.setCapability("systemPort", 8202);
		// capabilities.setCapability("autoGrantPermissions", true);
		// capabilities.setCapability("automationName", "uiautomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4727/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);
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
		//nidscreen.ClickCameraShutter();
		//driver.findElementById("com.android.attachcamera:id/shutter_button").click();
		Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.android.camera2:id/done_button").click();
		//nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		Thread.sleep(2500);
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		nidscreen.ClickNIDBackPhoto();
		nidscreen.ClickCameraSelection();
		Thread.sleep(3000);
		//nidscreen.ClickCameraShutter();
		//driver.findElementById("com.android.attachcamera:id/shutter_button").click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.android.camera2:id/done_button").click();
		//nidscreen.ClickDoneButton();
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
		nidscreen.scrollTo("Logout");
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
		
		
		Verify.verifyEquals(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.ejogajog.serviceseeker:id/snackbar_text")))
						.getText(),
						"No such user found.", "User not found validation failed");

		
		
		
		
		//Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/snackbar_text").getText(),
			//	"No such user found.", "User not found validation failed");
		driver.navigate().back();
		
	}

	@Test(priority=3)
	public void LoginTest_validUser() throws IOException, InterruptedException {
		
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		HomeScreen homescreen = new HomeScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		NIDScreen nidscreen =  new NIDScreen(driver);
		
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
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();
		
	}
	
	@Test(priority=4)
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
			
		//driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		
		createtripscreen.OKButton.click();;
		createtripscreen.clickPickUpTimeField();
		//driver.findElementByAccessibilityId("11").click();
		String hours = driver.findElementById("android:id/hours").getText();
		System.out.println(hours);
		int hour = Integer.parseInt(hours);
		int neededhour = 0;
		
		if(hour==1) {
			neededhour = 2;
		}else if(hour==2) {
			neededhour = 3;
		}else if(hour==3) {
			neededhour = 4;
		}else if(hour==5) {
			neededhour = 6;
		}else if(hour==6) {
			neededhour = 7;
		}else if(hour==7) {
			neededhour = 8;
		}else if(hour==8) {
			neededhour = 9;
		}else if(hour==9) {
			neededhour = 10;
		}else if(hour==10) {
			neededhour = 11;
		}else if(hour==11) {
			neededhour = 12;
		}else if(hour==12) {
			neededhour = 1;
		}
		
		String mins = driver.findElementById("android:id/minutes").getText();
		System.out.println(mins);
		int min = Integer.parseInt(mins);
		int neededmins=0;
		
		if(min<1) {
			neededmins = 5;
			
		}
		else if(min<=5) {
			neededmins = 10;
		}
		else if(min<=10) {
			neededmins = 15;
		}
		else if(min<=15) {
			neededmins = 20;
		}
		else if(min<=20) {
			neededmins = 25;
		}
		else if(min<=25) {
			neededmins = 30;
		}
		else if(min<=30) {
			neededmins = 35;
		}
		else if(min<=35) {
			neededmins = 40;
		}
		else if(min<=40) {
			neededmins =  45;
		}
		else if(min<=45) {
			neededmins = 50;
		}
		else if(min<=50) {
			neededmins = 55;
		}
		else if(min<=55) {
			neededmins = 0;
			driver.findElementById("android:id/hours").click();
			driver.findElementByAccessibilityId(String.valueOf(neededhour)).click();
		}
		else if(min<=59) {
			neededmins = 5;
			driver.findElementById("android:id/hours").click();
			driver.findElementByAccessibilityId(String.valueOf(neededhour)).click();
		}
		
		System.out.println(neededmins);
		String needmin = String.valueOf(neededmins);
		System.out.println(needmin);
		
		driver.findElementById("android:id/minutes").click();
		Thread.sleep(5000);
		driver.findElementByAccessibilityId(needmin).click();
		
		
		createtripscreen.OKButton.click();
		createtripscreen.NextButton.click();
		try {
		createtripscreen.clickAllowPermissions();
		}catch(Exception e) {
			
		}
		//driver.executeScript("mobile: shell", ImmutableMap.of("command", "input swipe 500 1000 3000 400"));
		//driver.executeScript("mobile: shell", ImmutableMap.of("command", "input swipe 100 100 100 100"));
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		Thread.sleep(3500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		createtripscreen.clickMapsNextButton();
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_truck_type").click();
		List<WebElement> trucktypelist = driver.findElementsById("com.ejogajog.serviceseeker:id/spi_drop_down_tv");
		trucktypelist.get(1).click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_size_type").click();
		//nidscreen.scrollTo("35.0 TON");
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		
		
		
		/*
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='Length']").click();		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Length\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"7.5\")").click();
		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='7.5']").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Width\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"5.5\")").click();
		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='Width']").click();		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='5.5']").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Height\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"2.0\")").click();
		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='Height']").click();		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='2.0']").click();
		*/
		driver.findElementById("com.ejogajog.serviceseeker:id/text_truck_good_type").click();
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TYPES OF GOODS *\")").click();
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("DONE");
		driver.findElementById("com.ejogajog.serviceseeker:id/image_view_camera_img").click();
		nidscreen.ClickCameraSelection();
		Thread.sleep(3000);
		//nidscreen.ClickCameraShutter();
		//driver.findElementById("com.android.attachcamera:id/shutter_button").click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.android.camera2:id/done_button").click();	
		
		//nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementById("com.ejogajog.serviceseeker:id/button_create_trip").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewTitle").getText(), "SUCCESS", "Trip creation success message missing");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText().contains("Your new trip has been created successfully. You can manage this and other trips using My Trips section."), true, "Detailed success Message failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		
		//mytripsscreen.clickBurgerMenu();
		//nidscreen.scrollTo("Logout");
		//profile.LogoutLink();
		//profile.clickYesLogoutButton();
		
	}
	
	@Test(priority=9)
	public void checkbidreceivednotification() throws IOException, InterruptedException{
			Thread.sleep(25000);
			driver.openNotifications();
		    List<WebElement> allnotifications=driver.findElements(By.id("android:id/title"));

		    for (WebElement webElement : allnotifications) {
		        System.out.println(webElement.getText());
		        if(webElement.getText().contains("Bid received")){
		            System.out.println("Notification found");
		            break;
		        }
		    }

			Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid received\")").isDisplayed(), true, "Bid received notification failed");
		  
	}
	
	@Test(priority=10)
	public void AcceptBid() throws InterruptedException, IOException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid received\")").click();
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1500\")").click();
	    nidscreen.scrollTo("ACCEPT BID");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACCEPT BID\")").click();
		Verify.verifyEquals( driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Are you\")").getText(),"Are you sure you want to accept this bid?", "You want to accept bid dialog failed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES\")").click();
		//Thread.sleep(3500);
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();  
	}
	
	@Test(priority=12)
	public void CancelTrip_AfterBidAccepted() throws IOException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"100\")").click();
		nidscreen.scrollTo("CANCEL TRIP");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CANCEL TRIP\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"DO YOU\")").getText(), "DO YOU WANT TO CANCEL THIS TRIP?", "Cancel trip yes or no dialog failed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, CANCEL\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"PLEASE\")").getText(), "PLEASE SELECT YOUR REASON FOR CANCELLING", "Cancel trip reason dialog failed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CANCEL TRIP\")").click();
		
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YOUR\")").getText(), "YOUR TRIP HAS BEEN CANCELLED", "Cancel trip yes or no dialog failed");
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();
	}
	
	@Test(priority=14)
	public void StartTrip() throws IOException, InterruptedException {
		
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
			
		//mytripsscreen.clickCreateTripIcon_Otrips();
		driver.findElementById("com.ejogajog.serviceseeker:id/navigation_create_trip").click();
		createtripscreen.clickPickUpDateField();
			
		//driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		
		createtripscreen.OKButton.click();;
		createtripscreen.clickPickUpTimeField();
		//driver.findElementByAccessibilityId("11").click();
		String hours = driver.findElementById("android:id/hours").getText();
		System.out.println(hours);
		int hour = Integer.parseInt(hours);
		int neededhour = 0;
		
		if(hour==1) {
			neededhour = 2;
		}else if(hour==2) {
			neededhour = 3;
		}else if(hour==3) {
			neededhour = 4;
		}else if(hour==5) {
			neededhour = 6;
		}else if(hour==6) {
			neededhour = 7;
		}else if(hour==7) {
			neededhour = 8;
		}else if(hour==8) {
			neededhour = 9;
		}else if(hour==9) {
			neededhour = 10;
		}else if(hour==10) {
			neededhour = 11;
		}else if(hour==11) {
			neededhour = 12;
		}else if(hour==12) {
			neededhour = 1;
		}
		
		String mins = driver.findElementById("android:id/minutes").getText();
		System.out.println(mins);
		int min = Integer.parseInt(mins);
		int neededmins=0;
		
		if(min<1) {
			neededmins = 5;
			
		}
		else if(min<=5) {
			neededmins = 10;
		}
		else if(min<=10) {
			neededmins = 15;
		}
		else if(min<=15) {
			neededmins = 20;
		}
		else if(min<=20) {
			neededmins = 25;
		}
		else if(min<=25) {
			neededmins = 30;
		}
		else if(min<=30) {
			neededmins = 35;
		}
		else if(min<=35) {
			neededmins = 40;
		}
		else if(min<=40) {
			neededmins =  45;
		}
		else if(min<=45) {
			neededmins = 50;
		}
		else if(min<=50) {
			neededmins = 55;
		}
		else if(min<=55) {
			neededmins = 0;
			driver.findElementById("android:id/hours").click();
			driver.findElementByAccessibilityId(String.valueOf(neededhour)).click();
		}
		else if(min<=59) {
			neededmins = 5;
			driver.findElementById("android:id/hours").click();
			driver.findElementByAccessibilityId(String.valueOf(neededhour)).click();
		}
		
		System.out.println(neededmins);
		String needmin = String.valueOf(neededmins);
		System.out.println(needmin);
		
		driver.findElementById("android:id/minutes").click();
		Thread.sleep(5000);
		driver.findElementByAccessibilityId(needmin).click();
		
		
		createtripscreen.OKButton.click();
		createtripscreen.NextButton.click();
		try {
		createtripscreen.clickAllowPermissions();
		}catch(Exception e) {
			
		}
		//driver.executeScript("mobile: shell", ImmutableMap.of("command", "input swipe 500 1000 3000 400"));
		//driver.executeScript("mobile: shell", ImmutableMap.of("command", "input swipe 100 100 100 100"));
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		Thread.sleep(3500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		createtripscreen.clickMapsNextButton();
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_truck_type").click();
		List<WebElement> trucktypelist = driver.findElementsById("com.ejogajog.serviceseeker:id/spi_drop_down_tv");
		trucktypelist.get(1).click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_size_type").click();
		//nidscreen.scrollTo("35.0 TON");
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		
		
		
		/*
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='Length']").click();		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Length\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"7.5\")").click();
		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='7.5']").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Width\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"5.5\")").click();
		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='Width']").click();		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='5.5']").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Height\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"2.0\")").click();
		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='Height']").click();		
		//driver.findElementByXPath("//android.widget.CheckedTextView[@text='2.0']").click();
		*/
		driver.findElementById("com.ejogajog.serviceseeker:id/text_truck_good_type").click();
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TYPES OF GOODS *\")").click();
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("DONE");
		driver.findElementById("com.ejogajog.serviceseeker:id/image_view_camera_img").click();
		nidscreen.ClickCameraSelection();
		Thread.sleep(3000);
		//nidscreen.ClickCameraShutter();
		//driver.findElementById("com.android.attachcamera:id/shutter_button").click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.android.camera2:id/done_button").click();	
		
		//nidscreen.ClickDoneButton();
		nidscreen.ClickOkButton();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementById("com.ejogajog.serviceseeker:id/button_create_trip").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewTitle").getText(), "SUCCESS", "Trip creation success message missing");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText().contains("Your new trip has been created successfully. You can manage this and other trips using My Trips section."), true, "Detailed success Message failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		
		//mytripsscreen.clickBurgerMenu();
		//nidscreen.scrollTo("Logout");
		//profile.LogoutLink();
		//profile.clickYesLogoutButton();
		
		new Partner.FunctionalTest().CheckNewTrip_PushNotification_PlaceBid();
		FunctionalTest3.this.checkbidreceivednotification();
		FunctionalTest3.this.AcceptBid();
		
		
	}
	
	
	
}
