package Partner;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

	
	@SuppressWarnings("rawtypes")
	public class ProfileManagementScreen {
		
	AndroidDriver driver;
		
		//Constructor
		public ProfileManagementScreen(AndroidDriver driver) {
			this.driver = driver;
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
		
		//Page Constants
		@AndroidFindBy(id="com.ejogajog.fleetowner:id/iv_drawer_profile_image")
		public WebElement ProfilePhoto;
		
		@AndroidFindBy(id="com.ejogajog.fleetowner:id/tv_drawer_user_name")
		public WebElement ProfileUserName;
		
		@AndroidFindBy(id="com.ejogajog.fleetowner:id/tv_drawer_user_type")
		public WebElement ProfileType_Status;
		
		@AndroidFindBy(id="com.ejogajog.fleetowner:id/design_menu_item_text")
		public List<WebElement> ProfileMenu;
		
		@AndroidFindBy(id="com.ejogajog.fleetowner:id/notification_sound_switch")
		public WebElement ToggleNotificationButton;
		
		@AndroidFindBy(id="com.ejogajog.fleetowner:id/button_place_bid")
		public WebElement YesLogoutButton;
		
		
		//Page Methods
		public void MyProfileLink() {
			ProfileMenu.get(0).click();
		}
		
		public void MyDashboardLink() {
			ProfileMenu.get(1).click();
		}
		
		public void TrackerLink() {
			ProfileMenu.get(2).click();
		}
		
		public void ChangeLanguageLink() {
			ProfileMenu.get(4).click();
		}
		
		public void TermsofUseLink() {
			ProfileMenu.get(4).click();
		}
		
		public void PrivacyPolicyLink() {
			ProfileMenu.get(5).click();
		}
		
		public void VisitHelpCenterLink() {
			ProfileMenu.get(6).click();
		}
		
		public void LogoutLink() {
			ProfileMenu.get(7).click();
		}
		
		public void ClickNotificationButton() {
			ToggleNotificationButton.click();
		}
		
		public void clickYesLogoutButton() {
			YesLogoutButton.click();
		}
		
		
	}



