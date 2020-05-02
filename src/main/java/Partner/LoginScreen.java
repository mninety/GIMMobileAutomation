package Partner;

import java.text.MessageFormat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginScreen {

AndroidDriver driver;
	
	//Constructor
	public LoginScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//Page Constants
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/mobile_number_ET")
	public WebElement MobileNumberField;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/password_ET")
	public WebElement PasswordField;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/sign_in_button")
	public WebElement LoginButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Forgotten Password?']")
	public WebElement ForgotPasswordlink;
	
	//Page Methods
	public void setMobileNumber(String mobileno) {
		//MobileNumberField.clear();
		//MobileNumberField.sendKeys(mobileno);
		//MobileNumberField.setValue(mobileno);
		MobileNumberField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", mobileno)));
		
	}
	
	public void setPassword(String password) {
		//PasswordField.clear();
		//PasswordField.sendKeys(password);
		PasswordField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", password)));
		
	}
	
	public void clickLoginButton() {
		LoginButton.click();
	}
	
	public void clickForgotPasswordlink() {
		ForgotPasswordlink.click();
	}
		
	
}
