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
public class MobielVerificationScreen {
	
	AndroidDriver driver;
	
	//Constructor
	public MobielVerificationScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//Page Constants
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/edit_text_mobile_number")
	public WebElement MobileNumberField;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/checkbox_termOfUse")
	public WebElement ConditionsCheckBox;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/button_verification")
	public WebElement SendVerificationSMSButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/edit_text_mobile_number")
	public WebElement PinNumberField;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/button_next")
	public WebElement NextButton;
	
	//Page Methods
	public void setMobileNumber(String mobileno) {
		//MobileNumberField.clear();
		//MobileNumberField.sendKeys(mobileno);
		MobileNumberField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", mobileno)));
		
	}
	
	public void TickconditionsCheckbox() {
		ConditionsCheckBox.click();
	}
	
	public void ClickSendVerificationSMSButton() {
		SendVerificationSMSButton.click();
	}
	
	public void setPinNumber(String pin_no) {
		//PinNumberField.sendKeys(pin_no);
		PinNumberField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", pin_no)));
		
	}
	
	public void ClickNextButton() {
		NextButton.click();
	}
	
	
}
