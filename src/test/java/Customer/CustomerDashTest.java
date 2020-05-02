package Customer;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import TestBase.AppiumBase3;
import Utils.AcceptBid;
import Utils.AdminLogin;
import Utils.ApproveDriverRequest;
import Utils.CancelTrip;
import Utils.CreateTrip;
import Utils.CustomerDashboard;
import Utils.CustomerEndTrip;
import Utils.CustomerStartTrip;
import Utils.DisassociateDriver;
import Utils.DisplayBidsStatus;
import Utils.FOCancelBid;
import Utils.FOWithdrawBid;
import Utils.GIMSignIn;
import Utils.GIMSignUp;
import Utils.GetBidDetails;
import Utils.GetBookedTripDetails;
import Utils.GetListOfBids;
import Utils.GetListOfBidsForTrip;
import Utils.GetListofTrips;
import Utils.GetTrip;
import Utils.PartnerEndTrip;
import Utils.PartnerStartTrip;
import Utils.PendingUser_Listing;
import Utils.Randomgenerator;
import Utils.SendBid;
import Utils.SubmitTruckApplication;
import Utils.TestData;
import Utils.Timing;
import Utils.Trip_Action;
import Utils.TruckCertificates;
import Utils.TruckDetailsSave;
import Utils.TruckInfo;
import Utils.TruckListing;
import Utils.TruckPhotos;
import Utils.Truck_Action;
import Utils.UpdatePersonalInfo;
import Utils.User_Action;
import Utils.Verify;
import io.appium.java_client.android.AndroidDriver;

@Listeners(Utils.TestMethodListener.class)
public class CustomerDashTest {
	
	AndroidDriver driver;
	
	WebDriverWait wait;
	TestData testdata;
	GIMSignUp customersignup = new GIMSignUp();
	GIMSignUp partnersignup = new GIMSignUp();
	AdminLogin adminlogin = new AdminLogin();
	PendingUser_Listing userlist = new PendingUser_Listing();
	User_Action useraction = new User_Action();
	GIMSignIn usersignin = new GIMSignIn();
	CreateTrip newTrip = new CreateTrip();
	GetListofTrips triplist = new GetListofTrips();
	CancelTrip  canceltrip = new CancelTrip();
	SendBid placebid = new SendBid();
	TruckDetailsSave savetruck = new TruckDetailsSave();
	TruckInfo addtruckinfo = new TruckInfo();
	TruckCertificates addtruckcertificate = new TruckCertificates();
	TruckPhotos addtruckphotos = new TruckPhotos();
	SubmitTruckApplication trucksubmission = new SubmitTruckApplication();
	TruckListing trucklist = new TruckListing();
	Truck_Action truckaction =  new Truck_Action();
	ApproveDriverRequest approvedriver = new ApproveDriverRequest();
	DisassociateDriver disassociatedriver = new DisassociateDriver();
	GetListOfBids bidlisting = new GetListOfBids();
	GetListOfBidsForTrip tripbidlisting = new GetListOfBidsForTrip();
	GetBookedTripDetails bookedtripdetails = new GetBookedTripDetails();
	GetBidDetails biddetails = new GetBidDetails();
	DisplayBidsStatus bidstatus = new DisplayBidsStatus();
	FOWithdrawBid fowithdrawbid = new FOWithdrawBid();
	FOCancelBid focancelbid = new FOCancelBid();
	AcceptBid  acceptbid = new AcceptBid();
	GetTrip gettrip = new GetTrip();
	CustomerStartTrip customerstarttrip = new CustomerStartTrip();
	CustomerEndTrip customerendtrip = new CustomerEndTrip();
	PartnerStartTrip partnerstarttrip = new PartnerStartTrip();
	PartnerEndTrip partnerendtrip = new PartnerEndTrip();
	CustomerDashboard customerdashboard = new CustomerDashboard();
	UpdatePersonalInfo editprofile = new UpdatePersonalInfo();
	Trip_Action tripaction = new Trip_Action();
	static Randomgenerator rand = new Randomgenerator();
	int tripid;
	int bidid;
	int truckid;
	String customer_authToken;
	String driverid;
	public static String customermobileno = rand.generateRandomNumber(11);
	public static String partnermobileno = rand.generateRandomNumber(11);
	public static String drivermobileno = rand.generateRandomNumber(11);
	public String partner_authToken;
	public String partner_authToken1;
	
	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("udid", "192.168.129.101:5555");
		//capabilities.setCapability("isHeadless", true);
		capabilities.setCapability("udid", testdata.properties.getProperty("devicess"));
		capabilities.setCapability("deviceName", testdata.properties.getProperty("devicess"));
		
		//capabilities.setCapability("udid", "f9ba909c");
		//capabilities.setCapability("deviceName", "f9ba909c");
		
		//capabilities.setCapability("udid", "d9aa151");
		//capabilities.setCapability("deviceName", "d9aa151");
		
		
		capabilities.setCapability("newCommandTimeout", 2400);
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

	
	@Test(priority=1, description="Complete a trip cycle for 9 trips and start 1 trip")
	public void CompleteTripCyclye_For9Trips_and_start1Trip() throws NumberFormatException, IOException, InterruptedException {

		// create a new customer
		testdata = new TestData();
		String file = testdata.properties.getProperty("imgfile");
		
		customersignup.byroleId_andMobileno(5, customermobileno);
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		userlist.Pending_Customers_List(adminlogin.getauthToken(), 1, 10);
		String customerid = userlist.getUserID();
		// Approve customer by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 5, userlist.getUserID());
		//customermobileno = userlist.getUserMobileno();
		// Sign in as a customer
		usersignin.SignInAPI(null, null, customermobileno, customersignup.password, 5);
		customer_authToken = usersignin.getauthToken();

		// create a new partner
		customersignup.byroleId_andMobileno(3, partnermobileno);
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		userlist.Pending_FleetOwners_List(adminlogin.getauthToken(), 1, 10);
		//partnermobileno = userlist.getUserMobileno();
		// Approve parter by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 3, userlist.getUserID());
		String partnerid = userlist.getUserID();
		// Sign in as a partner
		usersignin.SignInAPI(null, null, partnermobileno, customersignup.password, 3);

		partner_authToken = usersignin.getauthToken();

		// Add a truck and submit for approval
		addtruckinfo.AddTruckInfoAPI_FieldValidation(partner_authToken, null, 1, 1, 10, 10, 10, 0, 0, "", 0);
		truckid = addtruckinfo.getTruckId();
		
		//addtruckcertificate.MobileApp_AddTruckRegistrationPhotoOnly(partner_authToken, truckid, false, file);
		
		addtruckcertificate.Add_TruckCertificatesAPI(partner_authToken, truckid, false);
		
		
		
		//addtruckcertificate.MobileApp_AddtruckFitnessCertificatePhotoOnly(partner_authToken,
			//	truckid, false, file);
		addtruckphotos.addtruckFrontWithNumberPlate(partner_authToken, truckid, file);
		trucksubmission.SubmitTruck(usersignin.getauthToken(), truckid);

		trucklist.PendingTruckslist(adminlogin.getauthToken(), partnerid, 1, 10);
		// Approve truck as admin
		truckaction.ApproveTruck(adminlogin.getauthToken(), addtruckinfo.getTruckId());
		// create a new driver to associate for above partner
		customersignup.NewDriver_ForExistingFleetOwner(Integer.parseInt(partnerid));
		adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
				testdata.properties.getProperty("adminpassword"));
		userlist.Pending_Drivers_List(adminlogin.getauthToken(), 1, 10);
		// Approve driver by admin
		useraction.ApproveUser(adminlogin.getauthToken(), 2, userlist.getUserID());
		driverid = userlist.getUserID();
		// Approve driver request by partner
		approvedriver.ApproveDriverAPI(usersignin.getauthToken(), Integer.parseInt(driverid));

		// create a trip request with future date and time
		for (int i = 0; i < 10; i++) {
			/*newTrip.NewTrip(customer_authToken,
					new Timing().FutureDateTime_UTC(Integer.valueOf(rand.generateRandomNumber(2))).toString(),
					new Timing().FutureDateTime_UTC(Integer.valueOf(rand.generateRandomNumber(2))).toString(),
					23.01, 92.01, 25.05, 89.25, rand.generateRandomName(),
					rand.generateRandomName(), 1, 1, 1, "", rand.generateRandomName(),
					testdata.properties.getProperty("image"), "Cash", 1, 1, 1);
		*/
			
		/*	newTrip.NewTrip(customer_authToken,
					new Timing().FutureDateTime_UTC(Integer.valueOf(rand.generateRandomNumber(2))).toString(),
					new Timing().FutureDateTime_UTC(Integer.valueOf(rand.generateRandomNumber(2))).toString(),
					rand.getRandomDoubleNumberInRange(23.01, 23.10), rand.getRandomDoubleNumberInRange(92.01, 92.10), 
					rand.getRandomDoubleNumberInRange(25.04, 25.08), rand.getRandomDoubleNumberInRange(89.21, 89.25), rand.generateRandomName(),
					rand.generateRandomName(), 1, 1, 1, "", rand.generateRandomName(),
					testdata.properties.getProperty("image"), "Cash", 1, 1, 1);
		*/
			
			
			System.out.println(new Timing().FutureDateTime_UTC(rand.getRandomNumberInRange(11, 15)).toString());
			if(i<3) {
			newTrip.NewTrip(customer_authToken,
					new Timing().FutureDateTime_UTC(10).toString(),
					new Timing().FutureDateTime_UTC(10).toString(),
					rand.getRandomDoubleNumberInRange(23.01, 23.10), rand.getRandomDoubleNumberInRange(92.01, 92.10), 
					rand.getRandomDoubleNumberInRange(25.04, 25.08), rand.getRandomDoubleNumberInRange(89.21, 89.25), rand.generateRandomName(),
					rand.generateRandomName(), 1, 1, 1, "", rand.generateRandomName(),
					testdata.properties.getProperty("image"), "Cash", 1, 1, 1);
			}else {
				newTrip.NewTrip(customer_authToken,
						new Timing().FutureDateTime_UTC(10).toString(),
						new Timing().FutureDateTime_UTC(10).toString(),
						rand.getRandomDoubleNumberInRange(23.01, 23.10), rand.getRandomDoubleNumberInRange(92.01, 92.10), 
						rand.getRandomDoubleNumberInRange(25.04, 25.08), rand.getRandomDoubleNumberInRange(89.21, 89.25), rand.generateRandomName(),
						rand.generateRandomName(), 1, 1, 2, "", rand.generateRandomName(),
						testdata.properties.getProperty("image"), "Cash", 1, 1, 1);
					
			}
				
			triplist.GetListofTripsAPI(customer_authToken, 2, 1, 10);
			tripid = Integer.parseInt(triplist.getTripID());
			
			//approve trip by admin
			tripaction.ApproveTrip(adminlogin.getauthToken(), tripid);
			
			// Place a bid for the above trip
			placebid.SendBidAPI(partner_authToken, tripid, "132145", "500", addtruckinfo.getTruckId(),
					Integer.parseInt(driverid), "25 Gulshan Avenue, Dhaka");
			bidid = Integer.parseInt(placebid.getBidID());
			// Bid successful validations
			Verify.verifyEquals(placebid.getMessage(), "Your bid has been placed successfully.",
					"Response message validation failed");
			Verify.verifyEquals(placebid.getResponseCode(), "200", "Response code validation failed");
			Verify.verifyEquals(placebid.getSuccessValue(), "true", "Success value validation failed");
			Verify.verifyEquals(placebid.getBidID().isEmpty(), false, "Bid id validation failed");

			// Customer acccepts the bid
			acceptbid.acceptBidAPI(customer_authToken, bidid);
			Thread.sleep(15000);
			// Start trip
			if(i==9) {
			customerstarttrip.StartTripAPI(customer_authToken, tripid);
			}
			else {
			customerstarttrip.StartTripAPI(customer_authToken, tripid);
			customerendtrip.EndTripAPI(customer_authToken, tripid);
				}
			System.out.println("Trip finished count:***************" + i);
			
			}
			
		
	}
	
	@Test(priority=2)
	public void VerifyTotalTripsCount_And_TotalAmount_WhenUser_Landsonto_DashboardScreen() throws IOException, InterruptedException {
		setUp();
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		HomeScreen homescreen = new HomeScreen(driver);
		LoginScreen loginscreen = new LoginScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		NIDScreen nidscreen =  new NIDScreen(driver);
		
		homescreen.clickLoginButton();
		loginscreen.setMobileNumber(customermobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		
		mytripsscreen.clickBurgerMenu();
		profile.MyDashboardLink();
	
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
			
	}
	
	@Test(priority=3, description="Validate if relevant trips and correct Total trip count/amount is displayed when we "
			+ "Open filter -> Tap on Filter BUtton without using any from/to date/goods type values")
	public void TaponFilterButton_WithoutSettingFromToDatesGoodsTypeValues_ValidateTripCount_TripAmount() {
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
		
	}
	
	
	
	@Test(priority=4, description="Validate if relevant trips and correct Total trip count/amount is displayed when we open filter"
			+ "Tap on clear button -> Tap on Filter button without setting any from/todate/goodstype values")
	public void TapOnClearFilterAndFilterButtons_WithoutSettingFromToDatesGoodsTypevalues_ValidateTripCount_TripAmount() throws IOException {
		
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_clear").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
		
	}
	
	@Test(priority=5, description="Validate if relevant trips and correct Total trip count/amount is displayed when From date is set to current date")
	public void SetFromDateToCurrentDate_ValidateTripCount_TripAmount() throws IOException, InterruptedException {
		
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/select_from_date").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
			
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
		
	}
	
	@Test(priority=6, description="Validate if relevant trips and correct Total trip count/amount is displayed when To Date is set to current date")
	public void SetToDateToCurrentDate_ValidateTripCount_TripAmount()
	{
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/select_to_date").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "0", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("0"), true, "Total amount validation failed");
	
	}
	
	@Test(priority=7, description="Validate if relevant trips and correct Total trip count/amount is displayed when From date is set to past date")
	public void SetFromDateToPastDate_ValidateTripCount_TripAmount() throws IOException {
		
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/select_from_date").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
		
	}
	
	@Test(priority=8, description="Validate if relevant trips and correct Total trip count/amount is displayed when To Date is set to past date")
	public void SetToDateToPastDate_ValidateTripCount_TripAmount()
	{
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/select_to_date").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "0", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("0"), true, "Total amount validation failed");
	
	}
	
	
	@Test(priority=9, description="Validate if 0 trips are displayed when irrelevant goods type is searched for")
	public void IrrelvantGoodsTypeSearch_ByFilter() throws IOException {
		
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/et_goods_type").sendKeys("asdfasdf");
		driver.navigate().back();
		driver.navigate().back();
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
		
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "0", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("0"), true, "Total amount validation failed");
		
		
	}
	
	@Test(priority=10, description="Validate if 0 trips are displayed when there are no completed in relevant from/to date range")
	public void ValidateIfNoTripsAreDisplayed_WhenRelevant_DateRangeis_selected() throws IOException {
		
		driver.findElementById("menu_icon").click();
		driver.findElementById("com.ejogajog.serviceseeker:id/select_from_date").click();
		for(int i=0; i<10; i++) {

			driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 19"));
			
		}	
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/select_to_date").click();
		for(int i=0; i<9; i++) {

			driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 19"));
			
		}
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"OK\")").click();
		
		driver.findElementById("com.ejogajog.serviceseeker:id/btn_search").click();
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "0", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("0"), true, "Total amount validation failed");
		
	}
	
	@Test(priority=22, description="Verify increase in total trip count and total amount after trip is ended by partner")
	public void Verify_IncreaseinTotalTripCount_TotalAmount_AfterTrip_EndedByPartner() {
		
		MyTripsScreen mytripsscreen = new MyTripsScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
	
		mytripsscreen.clickBurgerMenu();
		profile.MyDashboardLink();
	
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_total_trips_count").getText(), "9", "Total trip count validation failed");
		Verify.verifyEquals(driver.findElementById("com.ejogajog.serviceseeker:id/tv_trips_amount").getText().contains("1,189,305"), true, "Total amount validation failed");
		
	}
	
	
	
	
	
	
	@Ignore(value="Complete a trip which is in Live state -> Navigate"
			+ "to Dashboard and check if Totalamount/Trip count are updated as expected")
	public void CompleteTripWhicis_inLiveState_ValidateDashboard_TotalAMount_TripCount_Areupdated() throws IOException {
		
		setUp();
		System.out.println(driver.findElementById("nav_button_mytrips").isDisplayed());
		
		System.out.println(driver.findElementById("nav_button_mytrips").isEnabled());
		
		driver.findElementById("nav_button_mytrips").click();
		
		
		
		
		//driver.findElementByAccessibilityId("nav_button_mytrips").click();
		//driver.findElementById("nav_button_mytrips").click();
		
		driver.findElementById("nav_button_notifications").click();
		
		System.out.println(driver.findElementById("nav_button_mytrips").isDisplayed());
		
		System.out.println(driver.findElementById("nav_button_mytrips").isEnabled());
		
		driver.findElementById("nav_button_mytrips").click();
		
		
		
		//driver.findElementById("com.ejogajog.serviveseeker:id/nav_button_mytrips").click();
		driver.findElementByAccessibilityId("nav_button_mytrips").click();		
		
	}
	
	
	

}
