package Customer;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class MyTripsScreen {

AndroidDriver driver;
	
	//Constructor
	public MyTripsScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//Page Constants
	@AndroidFindBy(id="Open Drawer")
	public WebElement BurgerMenu;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/buttonCancel")
	public WebElement NoCancelButton;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/text_welcome_msg")
	public WebElement NewUserWelcomeMessageonRequestedTab;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/text_create_trip_msg")
	public WebElement NewUserWelcomeMessageonRequestedTab1;
	
	@AndroidFindBy(xpath="//androidx.appcompat.app.ActionBar$Tab[@index='0']")
	public WebElement RequestedTab;
	
	@AndroidFindBy(xpath="//androidx.appcompat.app.ActionBar$Tab[@index='1']")
	public WebElement BookedTab;
	
	@AndroidFindBy(xpath="//androidx.appcompat.app.ActionBar$Tab[@index='2']")
	public WebElement LiveTab;
	
	@AndroidFindBy(xpath="//androidx.appcompat.app.ActionBar$Tab[@index='3']")
	public WebElement HistoryTab;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/overlay_view")
	public WebElement CreateTripIcon_Otrips;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/navigation_create_trip")
	public WebElement CreateTripIcon;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/navigation_mytrips")
	public WebElement MyTripsIcon;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/navigation_notifications")
	public WebElement NotificationsIcon;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/view_constraint_bids_received")
	public WebElement RequestedTrips_BidsReceivedFilter;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/view_constraint_waiting_bids")
	public WebElement RequestedTrips_WaitingForBidsFilter;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/tv_waiting_bids_trip_count")
	public WebElement TotalTripCount_OnTripsWaitingForBid;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/tv_waiting_bids_updated_time")
	public WebElement UpdatedTimeStamp_WaitingForBidsFilter;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/tv_received_trip_count")
	public WebElement TotapTripCount_OnTripsBidsReceived;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/tv_received_updated_time")
	public WebElement UpdatedTimeStamp_BidsReceivedFilter;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/tv_heading_waiting")
	public WebElement WaitingForBidsFilter_label;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/tv_heading_received")
	public WebElement BidsReceivedFilter_label;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/appBar")
	public WebElement MyTrips_headerlabel;
	
	
	
	//Page Methods
	
	public void clickBurgerMenu() {
		BurgerMenu.click();
	}
	
	public void clickNoCancelButton() {
		NoCancelButton.click();
	}
	
	public void clickLogOut() {
		new TouchAction(driver).press(point(100, 1818)).release().perform();
		//new TouchAction(driver).press(538, 1576).release().perform();	
	}
	
	public void clickRequestedTab() {
		RequestedTab.click();
	}
	
	public void clickBookedTab() {
		BookedTab.click();
	}
	
	public void clickLiveTab() {
		LiveTab.click();
	}
	
	public void clickHistoryTab() {
		HistoryTab.click();
	}
	
	public void clickCreateTripIcon_Otrips() {
		CreateTripIcon_Otrips.click();
	}
	
	public void clickCreateTripIcon() {
		CreateTripIcon.click();
	}
	
	public void clickMyTripsIcon() {
		MyTripsIcon.click();
	}
	
	public void clickNotificationsIcon() {
		NotificationsIcon.click();
	}
	
	public void clickBidsReceivedFilter() {
		RequestedTrips_BidsReceivedFilter.click();
	}
	
	public void clickWaitingforBidsFilter() {
		RequestedTrips_WaitingForBidsFilter.click();
	}
	
	
	
	
	
	
	
	
	
}
