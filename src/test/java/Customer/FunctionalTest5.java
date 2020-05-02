package Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
import Partner.*;

import com.google.common.collect.ImmutableMap;

import TestBase.AppiumBase;
import TestBase.AppiumBase3;
import Utils.TestData;
import Utils.Verify;
import Utils.Trip_Action;
import Utils.GetListofTrips;
import Utils.GIMSignIn;
import Utils.PendingUser_Listing;
import Utils.User_Action;
import Utils.AdminLogin;
import Utils.Timing;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import Utils.Randomgenerator;
import Utils.GetOTP;
import Utils.OnCamera;
import Utils.CreateTrip;

import Customer.HomeScreen;
import Customer.MobielVerificationScreen;
import Customer.PersonalInformationScreen;
import Customer.ProfileManagementScreen;
import Customer.NIDScreen;

@SuppressWarnings("rawtypes")
@Listeners(Utils.TestMethodListener.class)
public class FunctionalTest5 extends AppiumBase3 {

	AndroidDriver driver;
	TestData testdata;
	int tripid=0;
	int completetripid;
	String customer_authToken;
	

	public static final String mobileno = "011"+new Randomgenerator().generateRandomNumber(8);
	public static final String invitedmobno = "011"+new Randomgenerator().generateRandomNumber(8);
	
	public WebDriverWait wait;

	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("udid", "192.168.129.101:5555");
		//capabilities.setCapability("isHeadless", true);
		capabilities.setCapability("udid", testdata.properties.getProperty("devicess"));
		capabilities.setCapability("deviceName", testdata.properties.getProperty("devicess"));
		capabilities.setCapability("newCommandTimeout", 2400);
		//capabilities.setCapability("AUTOMATION_NAME", "UiAutomator2");
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
		capabilities.setCapability("automationName", "uiautomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4727/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.resetApp();
		wait = new WebDriverWait(driver, 10);
	}

	@Test(priority=2)
	public void SignupTest() throws IOException, InterruptedException {
		setUp();
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
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		
		personalinformationscreen.setEmail(new Randomgenerator().generateRandomemailID());
		personalinformationscreen.setPassword("pass01");
		driver.navigate().back();
		//personalinformationscreen.setReferralCode("018" + new Randomgenerator().generateRandomNumber(8));
		//driver.navigate().back();
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
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		try{
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		try{
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		try{
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		
		//nidscreen.ClickCameraShutter();
		//driver.findElementById("com.android.attachcamera:id/shutter_button").click();
		Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		Thread.sleep(3000);
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		Thread.sleep(2500);
		}
		catch(Exception e) {
			nidscreen.ClickDoneButton();	
		}
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
		Thread.sleep(3000);
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}catch(Exception e) {
		nidscreen.ClickDoneButton();
		}
		nidscreen.ClickOkButton();
		
		nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.ClickFinishAndCreateAccountButton();
		Verify.verifyEquals(nidscreen.TextViewTitle.getText(), "SUCCESS",
				"Account creation success message validation failed");
		//Verify.verifyEquals(nidscreen.TextViewDescription.getText().contains("Your account has been successfully created. You can now create trips to move your goods."),
			//	true, "Detailed success Message validation failed");
		nidscreen.ClickSuccessOKButton();
		try {
		mytripsscreen.clickBurgerMenu();
		}catch(Exception e) {
			try {
			driver.findElementByAccessibilityId("Navigate up").click();
			}catch(Exception e1) {
				driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
						
			}
			}
		//driver.findElementByAccessibilityId("Navigate up").click();
		//driver.findElementByXPath("//android.widget.ImageButton[@content-desc='\"‎‏‎‎‎‎‎‏‎‏‏‏‎‎‎‎‎‏‎‎‏‎‎‎‎‏‏‏‏‏‎‏‏‎‏‏‎‎‎‎‏‏‏‏‏‏‏‎‏‏‏‏‏‎‏‎‎‏‏‎‏‎‎‎‎‎‏‏‏‎‏‎‎‎‎‎‏‏‎‏‏‎‎‏‎‏‎‏‏‏‏‏‎‎Navigate up‎‏‎‎‏‎\"‎‏‎‎‏‎']").click();
		//driver.findElementById("toolbar").click();
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

	@Ignore(value="3")
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
		//Thread.sleep(10000);
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();
		
	}
	
	@Test(priority=4)
	public void CreateTrip_ForApprovedUser() throws IOException, InterruptedException {
		//setUp();
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
		Thread.sleep(2500);
		
		mytripsscreen.clickCreateTripIcon_Otrips();
		createtripscreen.clickPickUpDateField();
		
		createtripscreen.OKButton.click();;
		createtripscreen.clickPickUpTimeField();
		/*String hours = driver.findElementById("android:id/hours").getText();
		System.out.println(hours);
		int hour = Integer.parseInt(hours);
		int neededhour = 0;
		
		if(hour==1) {
			neededhour = 3;
		}else if(hour==2) {
			neededhour = 4;
		}else if(hour==3) {
			neededhour = 5;
		}else if(hour==5) {
			neededhour = 7;
		}else if(hour==6) {
			neededhour = 8;
		}else if(hour==7) {
			neededhour = 9;
		}else if(hour==8) {
			neededhour = 10;
		}else if(hour==9) {
			neededhour = 11;
		}else if(hour==10) {
			neededhour = 12;
		}else if(hour==11) {
			neededhour = 1;
		}else if(hour==12) {
			neededhour = 2;
		}*/
		/*
		String mins = driver.findElementById("android:id/minutes").getText();
		System.out.println(mins);
		int min = Integer.parseInt(mins);
		int neededmins=0;
		
		if(min<1) {
			neededmins = 10;
			
		}
		else if(min<=5) {
			neededmins = 15;
		}
		else if(min<=10) {
			neededmins = 20;
		}
		else if(min<=15) {
			neededmins = 25;
		}
		else if(min<=20) {
			neededmins = 30;
		}
		else if(min<=25) {
			neededmins = 35;
		}
		else if(min<=30) {
			neededmins = 40;
		}
		else if(min<=35) {
			neededmins = 45;
		}
		else if(min<=40) {
			neededmins =  50;
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
		*/
		
		createtripscreen.OKButton.click();
		//Thread.sleep(1500);
		//createtripscreen.NextButton.click();
		
		try {
		
		driver.findElementByXPath("//android.widget.Button[@text='Next']").click();
		
		}
		catch(Exception e) {
			driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
			
		}
		try {
		createtripscreen.clickAllowPermissions();
		}catch(Exception e) {
			
		}
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		Thread.sleep(3500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		Thread.sleep(3800);
		createtripscreen.clickMapsNextButton();
		Thread.sleep(1500);
		//driver.navigate().back();
		
		/*
		driver.findElementById("et_pickup_address1").clear();
		driver.findElementById("et_pickup_address1").sendKeys("99 Gulshan Avenue");
		
		driver.findElementById("text_district_name").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna");
		Verify.verifyEquals(driver.findElementById("viewTextDriverName").getText(), "Barguna", "District search validation failed");
		driver.findElementById("viewTextDriverName").click();
		
		driver.findElementById("text_thana_name").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("text_postoffice_name").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("edit_text_pa_zip_code").click();
		driver.findElementById("text_user_filter_input").sendKeys("1350");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "1350", "zipcode search validation failed");
		driver.findElementById("viewText").click();
		
		Verify.verifyEquals(driver.findElementById("text_thana_name").getText(), "Dhamrai", "Thana autopopulation failed after selecting zipcode");
		Verify.verifyEquals(driver.findElementById("text_postoffice_name").getText(), "Dhamrai", "Post office autopopulation failed after selecting zipcode");
		
		nidscreen.scrollTo("NEXT");
		
		driver.findElementById("edit_text_da_email").clear();
		driver.findElementById("edit_text_da_email").sendKeys("10 Gulshan Avenue");
		
		driver.findElementById("text_district_name_dropoff").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna");
		Verify.verifyEquals(driver.findElementById("viewTextDriverName").getText(), "Barguna", "District search validation failed");
		driver.findElementById("viewTextDriverName").click();
		
		driver.findElementById("text_thana_name_dropoff").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("text_postoffice_name_dropoff").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("spinner_da_zip_code").click();
		driver.findElementById("text_user_filter_input").sendKeys("1350");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "1350", "zipcode search validation failed");
		driver.findElementById("viewText").click();
		
		Verify.verifyEquals(driver.findElementById("text_thana_name_dropoff").getText(), "Dhamrai", "Thana autopopulation failed after selecting zipcode");
		Verify.verifyEquals(driver.findElementById("text_postoffice_name_dropoff").getText(), "Dhamrai", "Post office autopopulation failed after selecting zipcode");
		
		//Thread.sleep(1500);
		//driver.findElementById("button_next").click();
		//nidscreen.scrollTo("NEXT");
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		nidscreen.scrollTo("NEXT");
		driver.findElementByXPath("//android.widget.Button[@text='NEXT']").click();
		
		
		driver.findElementById("text_skip").click();
		*/
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_truck_type").click();
		List<WebElement> trucktypelist = driver.findElementsById("com.ejogajog.serviceseeker:id/spi_drop_down_tv");
		trucktypelist.get(1).click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_size_type").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		try {	
		driver.findElementById("com.ejogajog.serviceseeker:id/text_truck_good_type").click();
		}catch(Exception e) {
			
			//driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ejogajog.serviceseeker:id/text_truck_good_type\")").click();
			driver.findElementByXPath("//android.widget.EditText[@text='TYPES OF GOODS *']").click();	
		}
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("Next");
		driver.findElementById("com.ejogajog.serviceseeker:id/image_view_camera_img").click();
		nidscreen.ClickCameraSelection();
		Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		Thread.sleep(3000);
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();	
		}catch(Exception e) {
		nidscreen.ClickDoneButton();
		}
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("Next");
		
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CREATE THE TRIP\")").click();
		Thread.sleep(1500);
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText().length(), 115, "Detailed success Message failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		
		//mytripsscreen.clickBurgerMenu();
		//driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,36][69,120]']")).click();
		driver.findElementByAccessibilityId("Navigate up").click();
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();	
		
		
		
	}
	
	@Test(priority=5)
	public void getTripID_AndAcceptTrip_ByAdmin() throws IOException {
		
		GIMSignIn usersignin = new GIMSignIn();
		GetListofTrips triplist = new GetListofTrips();
		AdminLogin adminlogin = new AdminLogin();
		Trip_Action tripaction = new Trip_Action();
		
		usersignin.SignInAPI(null, null, mobileno, "pass01", 5);
		customer_authToken = usersignin.getauthToken();
		triplist.GetListofTripsAPI(customer_authToken, 2, 1, 10);
		tripid = Integer.parseInt(triplist.getTripID());		
		System.out.println("Trip id is: *******"+tripid);
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		
		tripaction.ApproveTrip(adminlogin.getauthToken(), tripid);
		
	}
	
	
	@Test(priority=14)
	public void AcceptBid() throws InterruptedException, IOException {
		
		HomeScreen homescreen = new HomeScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		
		homescreen.clickLoginButton();
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		Thread.sleep(5000);
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTED\")").click();
	    
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS RECEIVED\")").click();
	    
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1 BID\")").click();
	    
		
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,500\")").click();
	    nidscreen.scrollTo("ACCEPT BID");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACCEPT BID\")").click();
		Verify.verifyEquals( driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Are you\")").getText(),"Are you sure you want to accept this bid?", "You want to accept bid dialog failed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();  
	
	}
	
	@Test(priority=15)
	public void StartTrip_ByCustomer() throws IOException, InterruptedException, SQLException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
			
		//Amend the pickupdatetime to a current datetime in trip_details DB table
			/*	Connection  conn = DriverManager.getConnection(testdata.properties.getProperty("DB"),
						testdata.properties.getProperty("dbusername"), testdata.properties.getProperty("dbpassword"));
			*/
			/*	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
		        Statement st =  conn.createStatement();

		        st.executeUpdate("update `trip_details` set `pickup_date_time_utc` = '"+new Timing().getCurrentDateTimeUTC()+"' where `id` = "+tripid+";"); 
				
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {
				conn.close();
		        System.out.println("Check if the DB query is executed and connection is closed: "+conn.isClosed());
				}
			*/
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TO START\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"START TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, START\")").click();
		
	}
	
	@Test(priority=16)
	public void CompleteTrip_ByCustomer() {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"COMPLETE THIS TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, END\")").click();
		
	}
	
	@Test(priority=17)
	public void SubmitMyRating_DefaultRatings() {
		
		NIDScreen nidscreen =  new NIDScreen(driver);
		
		nidscreen.scrollTo("SUBMIT MY RATING");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"SUBMIT MY RATING\")").click();
		driver.navigate().back();
	}
	
	
	@Test(priority=18)
	public void LaunchDashboard_VerifyCompletedTrip_isDisplayed_WithoutAnyFilters() throws ParseException, IOException {
		//setUp();
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		//mytripsscreen.clickBurgerMenu();
		//driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,36][69,120]']")).click();		
		driver.findElementByAccessibilityId("Navigate up").click();
		
		profile.MyDashboardLink();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "1", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("1,500"), true, "Total amount validation failed");
	}
	
	@Test(priority=19)
	public void VerifyTappingOnTriponDashboard_LandsUser_Onto_TripDetailsScreen() throws IOException {
		completetripid = 1000000+tripid;
		driver.findElementById("menu_icon").click();
		driver.navigate().back();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+completetripid+"\")").click();
		driver.navigate().back();
		
		driver.findElementById("menu_icon").click();
		driver.navigate().back();
		
		//mytripsscreen.clickBurgerMenu();
		/*
		driver.findElementById("iv_drawer_icon").click();
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();
		*/
	}
	
	@Test(priority=20)
	public void CreateTrip_And_CancelTrip() throws IOException, InterruptedException {
		setUp();
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		CreateTripScreen createtripscreen = new CreateTripScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		
		mytripsscreen.clickCreateTripIcon();
		createtripscreen.clickPickUpDateField();
			
		createtripscreen.OKButton.click();;
		createtripscreen.clickPickUpTimeField();
		/*String hours = driver.findElementById("android:id/hours").getText();
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
			neededmins = 10;
			
		}
		else if(min<=5) {
			neededmins = 15;
		}
		else if(min<=10) {
			neededmins = 20;
		}
		else if(min<=15) {
			neededmins = 25;
		}
		else if(min<=20) {
			neededmins = 30;
		}
		else if(min<=25) {
			neededmins = 35;
		}
		else if(min<=30) {
			neededmins = 40;
		}
		else if(min<=35) {
			neededmins = 45;
		}
		else if(min<=40) {
			neededmins =  50;
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
		*/
		
		createtripscreen.OKButton.click();
		createtripscreen.NextButton.click();
		Thread.sleep(1500);
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		Thread.sleep(3500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		Thread.sleep(2500);
		createtripscreen.clickMapsNextButton();
		
		//driver.findElementById("text_skip").click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_truck_type").click();
		List<WebElement> trucktypelist = driver.findElementsById("com.ejogajog.serviceseeker:id/spi_drop_down_tv");
		trucktypelist.get(1).click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_size_type").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		
		
		driver.findElementById("com.ejogajog.serviceseeker:id/text_truck_good_type").click();
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("Next");
			
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CREATE THE TRIP\")").click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTED\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"TOTAL 1 TRIPS\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Gulshan\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CANCEL THIS TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, CANCEL\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YOUR TRIP HAS BEEN\")").getText().length(), 28, "Trip cancellation validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
	
	}
	
	@Test(priority=21)
	public void CreateTrip_ForPartnerToBid_AndThen_WithdrawBid() throws IOException, InterruptedException {
		
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		CreateTripScreen createtripscreen = new CreateTripScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		
		mytripsscreen.clickCreateTripIcon();
		createtripscreen.clickPickUpDateField();
			
		createtripscreen.OKButton.click();;
		createtripscreen.clickPickUpTimeField();
		/*String hours = driver.findElementById("android:id/hours").getText();
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
			neededmins = 10;
			
		}
		else if(min<=5) {
			neededmins = 15;
		}
		else if(min<=10) {
			neededmins = 20;
		}
		else if(min<=15) {
			neededmins = 25;
		}
		else if(min<=20) {
			neededmins = 30;
		}
		else if(min<=25) {
			neededmins = 35;
		}
		else if(min<=30) {
			neededmins = 40;
		}
		else if(min<=35) {
			neededmins = 45;
		}
		else if(min<=40) {
			neededmins =  50;
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
		*/
		
		createtripscreen.OKButton.click();
		createtripscreen.NextButton.click();
		Thread.sleep(1500);
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		Thread.sleep(3500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		Thread.sleep(2500);
		createtripscreen.clickMapsNextButton();
		
		//driver.findElementById("text_skip").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_truck_type").click();
		List<WebElement> trucktypelist = driver.findElementsById("com.ejogajog.serviceseeker:id/spi_drop_down_tv");
		trucktypelist.get(1).click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_size_type").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/text_truck_good_type").click();
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("Next");
			
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CREATE THE TRIP\")").click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		
		//driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
		driver.findElementByAccessibilityId("Navigate up").click();
		
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();	
		
		FunctionalTest5.this.getTripID_AndAcceptTrip_ByAdmin();
		
	}
	
	
	@Test(priority=24)
	public void AcceptPlacedBid_ByPartner() throws InterruptedException, IOException {
		
		/*NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTED\")").click();
	    
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS RECEIVED\")").click();
	    
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1 BID\")").click();
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,500\")").click();
	    nidscreen.scrollTo("ACCEPT BID");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACCEPT BID\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();  
	*/
		FunctionalTest5.this.AcceptBid();
	}
	
	@Test(priority=29)
	public void AcceptNewPlacedBid_ByPartner() throws InterruptedException, IOException {
		setUp();
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTED\")").click();
	    
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS RECEIVED\")").click();
	    
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1 BID\")").click();
	    
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,500\")").click();
	    nidscreen.scrollTo("ACCEPT BID");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACCEPT BID\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();  
	
	//	FunctionalTest5.this.AcceptBid();
	}
	
	@Test(priority=31)
	public void ValidateAmendedDriverTruck_ForBookedTrip() throws IOException {
		setUp();
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Gulshan\")").click();
		
		nidscreen.scrollTo("CANCEL THIS TRIP");
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/text_name_driver").getText(), Partner.FunctionalTest2.drivername, "Amended driver validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_truck_reg_number").getText(), "DHAKA METRO-TA-"+Partner.FunctionalTest2.regnop3+"-"+Partner.FunctionalTest2.regnop4);
	
		driver.navigate().back();
	}
	
	@Test(priority=36)
	public void ValidateCustomerlistedNotifications_AreDisplayed() throws IOException {
		
		NIDScreen nidscreen = new NIDScreen(driver);
		
		driver.findElementById("navigation_notifications").click();
		nidscreen.scrollTo("Bid Cancelled");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid Cancelled\")").getText().contains("Bid Cancelled"), true, "notification validation failed");
		nidscreen.scrollTo("Bid Received");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid Received\")").getText().contains("Bid Received"), true, "notification validation failed");
		
		nidscreen.scrollTo("truck has changed to");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"started\")").getText().contains("started"), true, "notification validation failed");
		
		nidscreen.scrollTo("started");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"completed\")").getText().contains("completed"), true, "notification validation failed");
		
		nidscreen.scrollTo("completed");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"truck has changed to\")").getText().contains("truck has changed to"), true, "notification validation failed");
		
	}
	
	@Test(priority=37)
	public void CreateNewTrip() throws IOException, InterruptedException {
		
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		CreateTripScreen createtripscreen = new CreateTripScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		
		mytripsscreen.clickCreateTripIcon();
		createtripscreen.clickPickUpDateField();
			
		createtripscreen.OKButton.click();
		createtripscreen.clickPickUpTimeField();
		
		createtripscreen.OKButton.click();
		createtripscreen.NextButton.click();
		Thread.sleep(1500);
		//new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		Thread.sleep(3500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		Thread.sleep(2500);
		createtripscreen.clickMapsNextButton();
		
		//driver.findElementById("text_skip").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_truck_type").click();
		List<WebElement> trucktypelist = driver.findElementsById("com.ejogajog.serviceseeker:id/spi_drop_down_tv");
		trucktypelist.get(1).click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_size_type").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		
		
		driver.findElementById("com.ejogajog.serviceseeker:id/text_truck_good_type").click();
		
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("Next");
			
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CREATE THE TRIP\")").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText().length(), 115, "Detailed success Message failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		
		driver.findElementByAccessibilityId("Navigate up").click();
		
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();	
		
		FunctionalTest5.this.getTripID_AndAcceptTrip_ByAdmin();
		
	}
	
	@Test(priority=39)
	public void ValidateBidReceivedNotification_whenTapped_LandsUser_onto_BidListScreen() throws IOException, InterruptedException {
		//setUp();
		HomeScreen homescreen = new HomeScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		
		homescreen.clickLoginButton();
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"My Trips\")").click();
		
		driver.findElementById("navigation_notifications").click();
		//nidscreen.scrollTo("Bid Received");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Bid Received\")").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"532\")").getText().contains("532"), true, "validation failed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,500\")").click();
	    nidscreen.scrollTo("ACCEPT BID");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACCEPT BID\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();  
	
	}
	
	@Test(priority=41)
	public void ValidateTripStartedNotification_WhenTapped_LandsUser_Onto_BookingDetailsScreen_Foruser_havingOption_toCompleteTrip() throws IOException {
		setUp();
		NIDScreen nidscreen = new  NIDScreen(driver);
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"My Trips\")").click();
		
		driver.findElementById("navigation_notifications").click();
		nidscreen.scrollTo("started");
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"started\")").getText().contains("started"), true, "validation failed");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"started\")").click();
		
		nidscreen.scrollTo("COMPLETE THIS TRIP");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"COMPLETE THIS TRIP\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES, END\")").click();
		
	}
	
	@Test(priority=44)
	public void Validation_InvitingAlreadyRegisteredCustomer_Tobe_Customer() throws IOException {
		
		driver.findElementById("Navigate up").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Referrals\")").click();
		driver.findElementById("menu_invitation").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/et_mobile_number").sendKeys(mobileno);
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_invite_as_customer").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText().contains("Customer"), true, "Mobilenumber already registered as Customer validation failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();
		
	}
	
	@Test(priority=46)
	public void InviteUser_ToJoinAs_Customer() throws IOException {
		//setUp();
		NIDScreen nidscreen = new NIDScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		
		driver.findElementById("menu_invitation").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/et_mobile_number").sendKeys(invitedmobno);
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_invite_as_customer").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText(), "Your invitation has been successfully sent", "Mobilenumber already registered as Customer validation failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_no_of_not_reg").getText(), "1", "Pending invitation count validation failed");
		driver.findElementById("navigation_notifications").click();
		
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"My Trips\")").click();
		
		try {
			mytripsscreen.clickBurgerMenu();
			}catch(Exception e) {
				try {
				driver.findElementByAccessibilityId("Navigate up").click();
				}catch(Exception e1) {
					driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
							
				}
				}
			//driver.findElementByAccessibilityId("Navigate up").click();
			//driver.findElementByXPath("//android.widget.ImageButton[@content-desc='\"‎‏‎‎‎‎‎‏‎‏‏‏‎‎‎‎‎‏‎‎‏‎‎‎‎‏‏‏‏‏‎‏‏‎‏‏‎‎‎‎‏‏‏‏‏‏‏‎‏‏‏‏‏‎‏‎‎‏‏‎‏‎‎‎‎‎‏‏‏‎‏‎‎‎‎‎‏‏‎‏‏‎‎‏‎‏‎‏‏‏‏‏‎‎Navigate up‎‏‎‎‏‎\"‎‏‎‎‏‎']").click();
			//driver.findElementById("toolbar").click();
			nidscreen.scrollTo("Logout");
			profile.LogoutLink();
			profile.clickYesLogoutButton();	
			
		
	}
	
	@Test(priority=48)
	public void Validation_RegisterasNewCustomer_ByEnteringReferralCode_ofExistingCustomer_WhileRegistering() throws IOException, InterruptedException {
		setUp();
		HomeScreen homescreen = new HomeScreen(driver);
		MobielVerificationScreen mobileverificationscreen = new MobielVerificationScreen(driver);
		GetOTP otp = new GetOTP();
		PersonalInformationScreen personalinformationscreen = new PersonalInformationScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		homescreen.clickSignUpButton();
		mobileverificationscreen.setMobileNumber(invitedmobno);
		mobileverificationscreen.TickconditionsCheckbox();
		mobileverificationscreen.ClickSendVerificationSMSButton();
		
		otp.OTPAPI(invitedmobno);
		mobileverificationscreen.setPinNumber(otp.getOTP());
		mobileverificationscreen.ClickNextButton();
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		
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
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		try{
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		try{
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		try{
			nidscreen.ClickAllowPermission();
			}
		catch(Exception e) {
			
		}
		
		//nidscreen.ClickCameraShutter();
		//driver.findElementById("com.android.attachcamera:id/shutter_button").click();
		Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		Thread.sleep(3000);
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		Thread.sleep(2500);
		}
		catch(Exception e) {
			nidscreen.ClickDoneButton();	
		}
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
		Thread.sleep(3000);
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();
		}catch(Exception e) {
		nidscreen.ClickDoneButton();
		}
		nidscreen.ClickOkButton();
		
		nidscreen.scrollTo("FINISH AND CREATE MY ACCOUNT");
		nidscreen.ClickFinishAndCreateAccountButton();
		/*Verify.verifyEquals(nidscreen.TextViewTitle.getText(), "SUCCESS",
				"Account creation success message validation failed");
		Verify.verifyEquals(nidscreen.TextViewDescription.getText().contains("Your account has been successfully created. You can now create trips to move your goods."),
				true, "Detailed success Message validation failed");
		*/
		nidscreen.ClickSuccessOKButton();
		try {
		mytripsscreen.clickBurgerMenu();
		}catch(Exception e) {
			try {
			driver.findElementByAccessibilityId("Navigate up").click();
			}catch(Exception e1) {
				driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
						
			}
			}
		//driver.findElementByAccessibilityId("Navigate up").click();
		//driver.findElementByXPath("//android.widget.ImageButton[@content-desc='\"‎‏‎‎‎‎‎‏‎‏‏‏‎‎‎‎‎‏‎‎‏‎‎‎‎‏‏‏‏‏‎‏‏‎‏‏‎‎‎‎‏‏‏‏‏‏‏‎‏‏‏‏‏‎‏‎‎‏‏‎‏‎‎‎‎‎‏‏‏‎‏‎‎‎‎‎‏‏‎‏‏‎‎‏‎‏‎‏‏‏‏‏‎‎Navigate up‎‏‎‎‏‎\"‎‏‎‎‏‎']").click();
		//driver.findElementById("toolbar").click();
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();	
		
		AdminLogin adminlogin = new AdminLogin();
		PendingUser_Listing userlist = new PendingUser_Listing();
		User_Action useraction = new User_Action();
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		userlist.Pending_Customers_List(adminlogin.getauthToken(), 1, 10);
		//Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 5, userlist.getUserID());
		
		new HomeScreen(driver).clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		driver.findElementById("navigation_notifications").click();
		
		//mytripsscreen.clickBurgerMenu();
		try {
			mytripsscreen.clickBurgerMenu();
			}catch(Exception e) {
				try {
				driver.findElementByAccessibilityId("Navigate up").click();
				}catch(Exception e1) {
					driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
							
				}
				}
			
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Referrals\")").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_no_of_reg").getText(), "1", "Registered users count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_no_of_not_reg").getText(), "0", "Pending invitation count validation failed");
	
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_mobile_no").getText(), invitedmobno, "Invited user registered validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_reg_as").getText(), "CUSTOMER", "User registered as partner validation failed");
		
		driver.findElementById("navigation_notifications").click();
		//mytripsscreen.clickBurgerMenu();
		try {
			mytripsscreen.clickBurgerMenu();
			}catch(Exception e) {
				try {
				driver.findElementByAccessibilityId("Navigate up").click();
				}catch(Exception e1) {
					driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
							
				}
				}
			
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();	
		
		
		
	}
	
	@Test(priority=49)
	public void Createnewtrip_withreferredcustomer() throws InterruptedException, IOException {
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
		GIMSignIn usersignin = new GIMSignIn();
		GetListofTrips triplist = new GetListofTrips();
		Trip_Action tripaction = new Trip_Action();
	
		new HomeScreen(driver).clickLoginButton();
		loginscreen.setMobileNumber(invitedmobno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		Thread.sleep(2500);
		mytripsscreen.clickCreateTripIcon_Otrips();
		createtripscreen.clickPickUpDateField();
		
		createtripscreen.OKButton.click();;
		createtripscreen.clickPickUpTimeField();
		/*String hours = driver.findElementById("android:id/hours").getText();
		System.out.println(hours);
		int hour = Integer.parseInt(hours);
		int neededhour = 0;
		
		if(hour==1) {
			neededhour = 3;
		}else if(hour==2) {
			neededhour = 4;
		}else if(hour==3) {
			neededhour = 5;
		}else if(hour==5) {
			neededhour = 7;
		}else if(hour==6) {
			neededhour = 8;
		}else if(hour==7) {
			neededhour = 9;
		}else if(hour==8) {
			neededhour = 10;
		}else if(hour==9) {
			neededhour = 11;
		}else if(hour==10) {
			neededhour = 12;
		}else if(hour==11) {
			neededhour = 1;
		}else if(hour==12) {
			neededhour = 2;
		}*/
		/*
		String mins = driver.findElementById("android:id/minutes").getText();
		System.out.println(mins);
		int min = Integer.parseInt(mins);
		int neededmins=0;
		
		if(min<1) {
			neededmins = 10;
			
		}
		else if(min<=5) {
			neededmins = 15;
		}
		else if(min<=10) {
			neededmins = 20;
		}
		else if(min<=15) {
			neededmins = 25;
		}
		else if(min<=20) {
			neededmins = 30;
		}
		else if(min<=25) {
			neededmins = 35;
		}
		else if(min<=30) {
			neededmins = 40;
		}
		else if(min<=35) {
			neededmins = 45;
		}
		else if(min<=40) {
			neededmins =  50;
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
		*/
		
		createtripscreen.OKButton.click();
		//Thread.sleep(1500);
		//createtripscreen.NextButton.click();
		
		try {
		
		driver.findElementByXPath("//android.widget.Button[@text='Next']").click();
		}
		catch(Exception e) {
			driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
			
		}
		try {
		createtripscreen.clickAllowPermissions();
		}catch(Exception e) {
			
		}
		new TouchAction(driver).press(PointOption.point(115, 650)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(115, 350)).release().perform();
		
		Thread.sleep(3500);
		createtripscreen.setPickUpAddressButton();
		
		createtripscreen.DropOffAddressField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text '10 Gulshan Avenue, Dhaka'"));
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		createtripscreen.setDropOffAddressButton();
		Thread.sleep(3800);
		createtripscreen.clickMapsNextButton();
		Thread.sleep(1500);
		//driver.navigate().back();
		
		/*
		driver.findElementById("et_pickup_address1").clear();
		driver.findElementById("et_pickup_address1").sendKeys("99 Gulshan Avenue");
		
		driver.findElementById("text_district_name").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna");
		Verify.verifyEquals(driver.findElementById("viewTextDriverName").getText(), "Barguna", "District search validation failed");
		driver.findElementById("viewTextDriverName").click();
		
		driver.findElementById("text_thana_name").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("text_postoffice_name").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("edit_text_pa_zip_code").click();
		driver.findElementById("text_user_filter_input").sendKeys("1350");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "1350", "zipcode search validation failed");
		driver.findElementById("viewText").click();
		
		Verify.verifyEquals(driver.findElementById("text_thana_name").getText(), "Dhamrai", "Thana autopopulation failed after selecting zipcode");
		Verify.verifyEquals(driver.findElementById("text_postoffice_name").getText(), "Dhamrai", "Post office autopopulation failed after selecting zipcode");
		
		nidscreen.scrollTo("NEXT");
		
		driver.findElementById("edit_text_da_email").clear();
		driver.findElementById("edit_text_da_email").sendKeys("10 Gulshan Avenue");
		
		driver.findElementById("text_district_name_dropoff").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna");
		Verify.verifyEquals(driver.findElementById("viewTextDriverName").getText(), "Barguna", "District search validation failed");
		driver.findElementById("viewTextDriverName").click();
		
		driver.findElementById("text_thana_name_dropoff").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("text_postoffice_name_dropoff").click();
		driver.findElementById("text_user_filter_input").sendKeys("Barguna Sadar");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "Barguna Sadar", "Thana search validation failed");
		driver.findElementById("viewText").click();
		
		driver.findElementById("spinner_da_zip_code").click();
		driver.findElementById("text_user_filter_input").sendKeys("1350");
		Verify.verifyEquals(driver.findElementById("viewText").getText(), "1350", "zipcode search validation failed");
		driver.findElementById("viewText").click();
		
		Verify.verifyEquals(driver.findElementById("text_thana_name_dropoff").getText(), "Dhamrai", "Thana autopopulation failed after selecting zipcode");
		Verify.verifyEquals(driver.findElementById("text_postoffice_name_dropoff").getText(), "Dhamrai", "Post office autopopulation failed after selecting zipcode");
		
		//Thread.sleep(1500);
		//driver.findElementById("button_next").click();
		//nidscreen.scrollTo("NEXT");
		//driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"NEXT\")").click();
		nidscreen.scrollTo("NEXT");
		driver.findElementByXPath("//android.widget.Button[@text='NEXT']").click();
		
		
		driver.findElementById("text_skip").click();
		*/
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_truck_type").click();
		List<WebElement> trucktypelist = driver.findElementsById("com.ejogajog.serviceseeker:id/spi_drop_down_tv");
		trucktypelist.get(1).click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/spinner_size_type").click();
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		try {	
		driver.findElementById("com.ejogajog.serviceseeker:id/text_truck_good_type").click();
		}catch(Exception e) {
			
			//driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ejogajog.serviceseeker:id/text_truck_good_type\")").click();
			driver.findElementByXPath("//android.widget.EditText[@text='TYPES OF GOODS *']").click();	
		}
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 20"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 66"));
		
		nidscreen.scrollTo("Next");
		driver.findElementById("com.ejogajog.serviceseeker:id/image_view_camera_img").click();
		nidscreen.ClickCameraSelection();
		Thread.sleep(3000);
		driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 27"));
		Thread.sleep(3000);
		try {
		driver.findElementById("com.android.camera2:id/done_button").click();	
		}catch(Exception e) {
		nidscreen.ClickDoneButton();
		}
		nidscreen.ClickOkButton();
		nidscreen.scrollTo("Next");
		
		driver.findElementById("com.ejogajog.serviceseeker:id/create_trip_btn").click();
		nidscreen.scrollTo("CREATE THE TRIP");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"CREATE THE TRIP\")").click();
		Thread.sleep(1500);
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/textViewDescription").getText().length(), 115, "Detailed success Message failed");
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();	
		driver.findElementByAccessibilityId("Navigate up").click();
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();	
		//FunctionalTest5.this.getTripID_AndAcceptTrip_ByAdmin();

		usersignin.SignInAPI(null, null, invitedmobno, "pass01", 5);
		customer_authToken = usersignin.getauthToken();
		triplist.GetListofTripsAPI(customer_authToken, 2, 1, 10);
		tripid = Integer.parseInt(triplist.getTripID());		
		System.out.println("Trip id is: *******"+tripid);
		
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		
		tripaction.ApproveTrip(adminlogin.getauthToken(), tripid);
		
	
	}
	
	@Test(priority=51)
	public void ReferredCustomer_completetrip_ByAcceptingBid_And_ValidateEarningsforFirstSuccessfulTripCompletion() throws IOException, InterruptedException, SQLException {
		HomeScreen homescreen = new HomeScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		
		homescreen.clickLoginButton();
		loginscreen.setMobileNumber(invitedmobno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		Thread.sleep(5000);
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BOOKED\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"REQUESTED\")").click();
	    
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"BIDS RECEIVED\")").click();
	    
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1 BID\")").click();
	    
		
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"1,500\")").click();
	    nidscreen.scrollTo("ACCEPT BID");
	    driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"ACCEPT BID\")").click();
		Verify.verifyEquals( driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Are you\")").getText(),"Are you sure you want to accept this bid?", "You want to accept bid dialog failed");
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"YES\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/button_ok").click();  
	
		FunctionalTest5.this.StartTrip_ByCustomer();
		FunctionalTest5.this.CompleteTrip_ByCustomer();
		FunctionalTest5.this.SubmitMyRating_DefaultRatings();
		driver.navigate().back();
		
		driver.findElementById("navigation_notifications").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"You are rewarded Taka 50\")").isDisplayed(), true, "Reward notification validation failed");
		
		try {
			mytripsscreen.clickBurgerMenu();
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
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_amount_earned").getText(), "50", "Total amount earned validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_amount_paid").getText(), "0", "Total amount paid validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_outstanding").getText(), "0", "Total outstanding amount validation failed");
		driver.findElementById("navigation_notifications").click();
		
		try {
			mytripsscreen.clickBurgerMenu();
			}catch(Exception e) {
				try {
				driver.findElementByAccessibilityId("Navigate up").click();
				}catch(Exception e1) {
					driver.findElement(By.xpath ("//android.widget.ImageButton[@bounds='[0,48][112,160]']")).click();
							
				}
				}
		
		nidscreen.scrollTo("Logout");
		profile.LogoutLink();
		profile.clickYesLogoutButton();	
		
	}
	
	@Test(priority=52)
	public void validateearnings_for_firstcompletedtrip_referre() {
		
		HomeScreen homescreen = new HomeScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		
		homescreen.clickLoginButton();
		loginscreen.setMobileNumber(mobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		driver.findElementById("navigation_notifications").click();
		Verify.verifyEquals(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"You are rewarded Taka 50\")").isDisplayed(), true, "Reward notification validation failed");
		
		try {
			mytripsscreen.clickBurgerMenu();
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
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_amount_earned").getText(), "50", "Total amount earned validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_amount_paid").getText(), "0", "Total amount paid validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_outstanding").getText(), "0", "Total outstanding amount validation failed");
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
