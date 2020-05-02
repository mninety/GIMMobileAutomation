package Partner;

import java.text.MessageFormat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class NIDScreen {

	
	AndroidDriver driver;
	
	//Constructor
	public NIDScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//Page Constants
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/edit_text_user_name")
	public WebElement NameField;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/edit_text_dob")
	public WebElement DOBField;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement DOBOkButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/drop_down_tv")
	public WebElement DistrictDropdownField;
	
	//@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Barguna']")
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@index=1]")
	public WebElement DistrictSelection;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/national_id_ET")
	public WebElement NIDField;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/id_front")
	public WebElement NIDFrontPhoto;
		
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/select_photo_camera")
	public WebElement cameraSelection;
	
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
	public WebElement allowPermissionButton;
	
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.GridView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.ImageView")
	public WebElement deviceCamera;
	
	@AndroidFindBy(id="Shutter button")
	public WebElement CameraShutter;
	
	@AndroidFindBy(id="com.android.camera:id/inten_done_apply")
	public WebElement DoneButton;
	
	@AndroidFindBy(id="com.android.camera:id/intent_done_retry")
	public WebElement RetakeButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/btnok")
	public WebElement OkButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/id_back")
	public WebElement NIDBackPhoto;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/button_create_account")
	public WebElement CreateAccountButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/textViewTitle")
	public WebElement TextViewTitle;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/textViewDescription")
	public WebElement TextViewDescription;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/button_ok")
	public WebElement SuccessOKButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/rg_driver_yes")
	public WebElement DriverYes;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/rg_driver_no")
	public WebElement DriverNo;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/edit_text_number")
	public WebElement DriverLicenseNumber;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/dl_expiry_ET1")
	public WebElement DriverLicenseExpiryDate;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement DriverLicense_OKButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/dl_front")
	public WebElement DriverLicenseFrontPhoto;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/dl_back")
	public WebElement DriverLicenseBackPhoto;
	
	
	//Page Methods
	public void setName(String name) {
		//NameField.clear();
		//NameField.sendKeys(name);
		NameField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", name)));
		
	}
	
	public void setDOB() {
		DOBField.click();
		DOBOkButton.click();
	}
	
	public void setDistrict() {
		DistrictDropdownField.click();
		DistrictSelection.click();
	}
	
	public void setNID(String NID) {
		//NIDField.clear();
		//NIDField.sendKeys(NID);
		  NIDField.click();
		  driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", NID)));
			
	}
	
	public void ClickNIDFrontPhoto() {
		NIDFrontPhoto.click();
	}
	
	public void ClickCameraSelection() {
		cameraSelection.click();
	}
	
	public void ClickAllowPermission() {
		allowPermissionButton.click();
	}
	
	public void ClickDeviceCamera() {
		deviceCamera.click();
	}
	
	public void ClickCameraShutter() {
		CameraShutter.click();
	}
	
	public void ClickDoneButton() {
		DoneButton.click();
	}
	
	public void ClickOkButton() {
		OkButton.click();
	}
	
	public void ClickNIDBackPhoto() {
		NIDBackPhoto.click();
	}
	
	public void ClickRetakeButton() {
		RetakeButton.click();
	}
	
	public void ClickCreateAccountButton() {
		CreateAccountButton.click();
	}
	
	public void scrollTo(String text)
	{                
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
		
	}
	
	public void ClickSuccessOKButton() {
		SuccessOKButton.click();
	}
	
	public void Set_Partner_DriverAccount_Yes() {
		DriverYes.click();
	}
	
	public void Set_Partner_DriverAccount_No() {
		DriverNo.click();
	}
	
	public void SetDriverLicenseNumber(String driverlicensenumber) {
		DriverLicenseNumber.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", driverlicensenumber)));
			
	}
	
	public void setDriverLicenseExpiryDate() {
		DriverLicenseExpiryDate.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command","input keyevent 22"));
		driver.executeScript("mobile: shell", ImmutableMap.of("command","input keyevent 20"));
		
	}
	
	public void clickDriverLicenseOKButton() {
		DriverLicense_OKButton.click();
	}
	
	public void clickDriverLicenseFrontPhoto() {
		DriverLicenseFrontPhoto.click();
	}
	
	public void clickDriverLicenseBackPhoto() {
		DriverLicenseBackPhoto.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
