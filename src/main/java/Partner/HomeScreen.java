package Partner;

import static io.appium.java_client.touch.offset.PointOption.point;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class HomeScreen {
	
	AndroidDriver driver;
	
	//Constructor
	public HomeScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//Page Constants
	@AndroidFindBy(id="sign_in_screen_button")
	public WebElement LoginButton;
	
	@AndroidFindBy(id="create_my_account_button")
	public WebElement CreateMyAccountButton;
	
	
	//Page Methods
	public void clickSignUpButton() {
		//new TouchAction(driver).press(point(538, 1576)).release().perform();
		//new TouchAction(driver).press(538, 1576).release().perform();
		CreateMyAccountButton.click();
	}
	
	public void clickLoginButton() {
		//new TouchAction(driver).press(point(521, 1785)).release().perform();
		LoginButton.click();
	}

}
