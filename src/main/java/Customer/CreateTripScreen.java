package Customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

@SuppressWarnings("rawtypes")
public class CreateTripScreen {

AndroidDriver driver;
	
	//Constructor
	public CreateTripScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Page Constants
	
	@FindBy(id="com.ejogajog.serviceseeker:id/pick_up_date_ET")
	public WebElement PickUpDateField;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/pick_up_time_ET")
	public WebElement PickUpTimeField;
	
	@FindBy(id="android:id/button1")
	public WebElement OKButton;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/button_next")
	public WebElement NextButton;
	
	@FindBy(id="com.android.packageinstaller:id/permission_allow_button")
	public WebElement AllowPermissions;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/et_pickup_address")
	public WebElement PickUpAddressField;

	@FindBy(id="com.ejogajog.serviceseeker:id/et_drop_off_location")
	public WebElement DropOffAddressField;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/bt_action_status")
	public WebElement MapsNextButton;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/iv_mapLocation")
	public WebElement MapPin;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/buttonCurrentLocation")
	public WebElement CurrentLocationButton;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/bt_action_status")
	public WebElement SetPickUpAddressButton;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/bt_action_status")
	public WebElement SetDropOffAddressButton;
	
	@FindBy(id="com.ejogajog.serviceseeker:id/imageViewPickUpClear")
	public WebElement ClearPickUpAddressField;
	//Page Methods
	
	public void clickPickUpDateField() {
		PickUpDateField.click();
	}
	
	public void clickPickUpTimeField() {
		PickUpTimeField.click();
	}
	
	public void clickAllowPermissions() {
		AllowPermissions.click();
	}
	
	public void editPickUpAddress(String pickUpAddress) {
		//PickUpAddressField.clear();
		PickUpAddressField.sendKeys(pickUpAddress);
	}
	
	public void editDropOffAddress(String dropOffAddress) {
		//DropOffAddressField.clear();
		DropOffAddressField.sendKeys(dropOffAddress);
	}
	
	public void setPickUpAddressButton() {
		SetPickUpAddressButton.click();
	}
	
	public void setDropOffAddressButton() {
		SetDropOffAddressButton.click();
	}
	
	public void clickMapsNextButton() {
		MapsNextButton.click();
	}
	
	public void clickCurrentLocationButton() {
		CurrentLocationButton.click();
	}
	
	public void clickClearPickUpAddressField() {
		ClearPickUpAddressField.click();
	}
	
	
	
}
