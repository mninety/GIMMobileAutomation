package Partner;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import TestBase.AppiumBase2;
import Utils.PendingUser_Listing;
import Utils.TruckListing;
import Utils.Truck_Action;
import Utils.User_Action;
import Utils.AdminLogin;
import Utils.GIMSignIn;
import Utils.GIMSignUp;
import Utils.AcceptBid;
import Utils.GetBidDetails;
import Utils.GetListOfBidsForTrip;
import Utils.SendBid;
import Utils.CancelTrip;
import Utils.CustomerDashboard;
import Utils.CustomerEndTrip;
import Utils.CustomerStartTrip;
import Utils.GetListofTrips;
import Utils.ApproveDriverRequest;
import Utils.DisassociateDriver;
import Utils.DisplayBidsStatus;
import Utils.FOCancelBid;
import Utils.FOWithdrawBid;
import Utils.GetListOfBids;
import Utils.CreateTrip;
import Utils.GetBookedTripDetails;
import Utils.GetListofUnbiddedTrips;
import Utils.GetOTP;
import Utils.GetTrip;
import Utils.SubmitTruckApplication;
import Utils.TruckCertificates;
import Utils.TruckDetailsSave;
import Utils.TruckInfo;
import Utils.TruckPhotos;
import Utils.Randomgenerator;
import Utils.TestData;
import Utils.Timing;
import Utils.Tracker_Action;
import Utils.Verify;
import io.appium.java_client.android.AndroidDriver;

public class TrackerTest extends AppiumBase2{
	
	AndroidDriver driver;
	WebDriverWait wait;
	String partnermobileno;
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
	GetListofUnbiddedTrips getlistofunbiddedtrips = new GetListofUnbiddedTrips();
	GetBookedTripDetails bookedtripdetails = new GetBookedTripDetails();
	GetBidDetails biddetails = new GetBidDetails();
	DisplayBidsStatus bidstatus = new DisplayBidsStatus();
	FOWithdrawBid fowithdrawbid = new FOWithdrawBid();
	FOCancelBid focancelbid = new FOCancelBid();
	AcceptBid  acceptbid = new AcceptBid();
	GetTrip gettrip = new GetTrip();
	CustomerStartTrip customerstarttrip = new CustomerStartTrip();
	CustomerEndTrip customerendtrip = new CustomerEndTrip();
	CustomerDashboard customerdashboard = new CustomerDashboard();
	Tracker_Action addTracker = new Tracker_Action();
	Randomgenerator rand = new Randomgenerator();
	int tripid;
	int bidid;
	int truckid;
	String customer_authToken;
	String driverid;
	String customermobileno;
	String partner_authToken;
	String partner_authToken1;
	
	public void AddTrackerstoApprovedTrucks_ViaAPI() throws IOException {
		
				testdata = new TestData();
				String file = testdata.properties.getProperty("imgfile");
					
				//create  a new partner
				customersignup.byroleId(3);
				adminlogin.adminLogInAPI(null, null, testdata.properties.getProperty("adminemail"),
						testdata.properties.getProperty("adminpassword"));
				userlist.Pending_FleetOwners_List(adminlogin.getauthToken(), 1, 10);
				//Approve parter by admin
				useraction.ApproveUser(adminlogin.getauthToken(), 3, userlist.getUserID());
				String partnerid = userlist.getUserID();
				partnermobileno = userlist.getUserMobileno();
				System.out.println("Partner mobile no is:"+partnermobileno);
				//Sign in as a partner
				usersignin.SignInAPI(null, null, userlist.getUserMobileno(), customersignup.password, 3);
				
				partner_authToken = usersignin.getauthToken();
				for(int i=0; i<20;  i++) {
				//Add a truck and submit for approval	
				addtruckinfo.AddTruckInfoAPI_FieldValidation(usersignin.getauthToken(), null, 
						1, 1, 10, 10, 10, 0, 0, "", 0);
				addtruckcertificate.MobileApp_AddtruckFitnessCertificatePhotoOnly(usersignin.getauthToken(), addtruckinfo.getTruckId(), false, file);
				truckid = addtruckinfo.getTruckId();
				addtruckphotos.addtruckFrontWithNumberPlate(usersignin.getauthToken(), addtruckinfo.getTruckId(), file);
				trucksubmission.SubmitTruck(usersignin.getauthToken(), addtruckinfo.getTruckId());
			
				//Approve truck as admin
				truckaction.ApproveTruck(adminlogin.getauthToken(), addtruckinfo.getTruckId());
				//create a new driver to associate for above partner
				
				// Add tracker to approved truck
				addTracker.AddTruckTrackerAPI(adminlogin.getauthToken(), addtruckinfo.getTruckId(), "M2M", "123456",
						rand.generateRandomName(), rand.generateRandomName(), rand.generateRandomNumber(5));
				
				}
		
	}
	
	public void setUp() throws IOException {
		testdata = new TestData();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("udid", "192.168.129.101:5555");
		//capabilities.setCapability("isHeadless", true);
		capabilities.setCapability("udid", testdata.properties.getProperty("deviceName"));
		capabilities.setCapability("deviceName", testdata.properties.getProperty("deviceName"));
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
	
	
	
	@Test
	public void LaunchTracker() throws IOException {
		
		AddTrackerstoApprovedTrucks_ViaAPI();
		setUp();
		
		HomeScreen homescreen = new HomeScreen(driver);
		MobielVerificationScreen mobileverificationscreen = new MobielVerificationScreen(driver);
		GetOTP otp = new GetOTP();
		PersonalInformationScreen personalinformationscreen = new PersonalInformationScreen(driver);
		NIDScreen nidscreen = new NIDScreen(driver);
		TrucksScreen trucksscreen = new TrucksScreen(driver);
		ProfileManagementScreen profile = new ProfileManagementScreen(driver);
		
		
		homescreen.clickLoginButton();
		LoginScreen loginscreen = new LoginScreen(driver);
		loginscreen.setMobileNumber(partnermobileno);
		loginscreen.setPassword("pass01");
		driver.navigate().back();
		loginscreen.clickLoginButton();
		trucksscreen.clickBurgerMenu();
		
		driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Tracker\")").click();
		
		
		
		
		
		
	}
	
	
	
	
	

}
