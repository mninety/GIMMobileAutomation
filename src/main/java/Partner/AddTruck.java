package Partner;

import java.text.MessageFormat;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import Utils.Randomgenerator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class AddTruck {
	
AndroidDriver driver;
	
	//Constructor
	public AddTruck(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//Page Constants
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/spinnerRegNoType")
	public WebElement RegNoType;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/spinnerRegNoSubType")
	public WebElement RegNoSubType;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/regNoPartOne_ET")
	public WebElement RegNoPartOne;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/regNoPartTwo_ET")
	public WebElement RegNoPartTwo;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/registration_date_ET")
	public WebElement RegistrationCertificateDate;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement OKButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/buttonNext")
	public WebElement NextButton;
	
	@AndroidFindBy(xpath="//*[@id='iv_fulltruck']")
	public WebElement TruckRegistrationPhoto;
	
	@AndroidFindBy(id="iv_back_photo")
	public WebElement TruckRegistrationBackPhoto;
	
	@AndroidFindBy(id="iv_front_plate")
	public WebElement TruckFrontWithNumberPlatePhoto;
	
	//Page Methods
	
	public void setRegNoType() {
		RegNoType.click();
		driver.findElementByXPath("//android.widget.CheckedTextView[2]").click();
		
	}
	
	public void setRegNoSubType() {
		RegNoSubType.click();
		driver.findElementByXPath("//android.widget.CheckedTextView[3]").click();
		
		
	}
	
	public void setRegNoPartOne(String regnop1) {
		RegNoPartOne.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", regnop1)));
	}
	
	public void setRegNoPartTwo(String regnop2) {
		RegNoPartTwo.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", regnop2)));
		
	}
	
	public void setRegistrationCertificateDate() {
		RegistrationCertificateDate.click();
		OKButton.click();
	}
	
	public void clickNextButton() {
		NextButton.click();
	}
	
	public void clickTruckRegistrationPhoto() {
		TruckRegistrationPhoto.click();
	}
	
	public void clickTruckRegistrationBackPhoto() {
		TruckRegistrationBackPhoto.click();
	}
	
	public void clickTruckFrontWithNumberplatePhoto() {
		TruckFrontWithNumberPlatePhoto.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
}

