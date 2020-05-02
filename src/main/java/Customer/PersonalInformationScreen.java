package Customer;

import java.text.MessageFormat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class PersonalInformationScreen {
	
AndroidDriver driver;
	
	//Constructor
	public PersonalInformationScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//Page Constants
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/edit_text_password")
	public WebElement PasswordField;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/edit_text_email")
	public WebElement EmailField;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/edit_text_agent_referral_code")
	public WebElement ReferralCodeField;
	
	@AndroidFindBy(id="com.ejogajog.serviceseeker:id/button_personal_info_next")
	public WebElement NextButton;
	
	//Page Methods
	public void setPassword(String password) {
		//PasswordField.clear();
		//PasswordField.sendKeys(password);
		PasswordField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", password)));
		
	}
	
	public void setEmail(String email) {
		//EmailField.sendKeys(email);
		EmailField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", email)));
		
	}
	
	public void setReferralCode(String refcode) {
		//ReferralCodeField.sendKeys(refcode);
		ReferralCodeField.click();
		driver.executeScript("mobile: shell", ImmutableMap.of("command", MessageFormat.format("input text {0}", refcode)));
		
	}
	
	public void ClickNextButton() {
		NextButton.click();
	}

}
