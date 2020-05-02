package Partner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class TrucksScreen {

AndroidDriver driver;
	
	//Constructor
	public TrucksScreen(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//Page Constants
	@AndroidFindBy(id="Open Drawer")
	public WebElement BurgerMenu;
	
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/menu_add_truck")
	public WebElement AddTruckIcon;
	
	@AndroidFindBy(id="APPROVED")
	public WebElement ApprovedTab;
	
	@AndroidFindBy(id="PENDING")
	public WebElement PendingTab;
	
	@AndroidFindBy(id="DRAFT")
	public WebElement DraftTab;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/approvedBtnApprove")
	public WebElement ApprovedButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/expiredBtnExpired")
	public WebElement ExpiredButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/pendingBtnPending")
	public WebElement PendingButton;
	
	@AndroidFindBy(id="com.ejogajog.fleetowner:id/rejectedBtnPending")
	public WebElement RejectedButton;
	
	//Page Methods
	
	public void clickBurgerMenu() {
		BurgerMenu.click();
	}
	
	public void clickAddTruckIcon() {
		AddTruckIcon.click();
	}
	
	public void clickApprovedTab() {
		ApprovedTab.click();
	}
	
	public void clickDraftTab() {
		DraftTab.click();
	}
	
	public void clickApprovedSubTabButton() {
		ApprovedButton.click();
	}
	
	
	
	public void clickPendingTab() {
		PendingTab.click();
	}
	
	public void clickExpiredSubTabButton() {
		ExpiredButton.click();
	}
	
	public void clickPendingSubTabButton() {
		PendingButton.click();
	}
	
	public void clickRejectedSubTabButton() {
		RejectedButton.click();
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
}
