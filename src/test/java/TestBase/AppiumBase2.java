package TestBase;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppiumBase2 {
	
	@BeforeTest
	public void startServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			if(System.getProperty("os.name").contains("Windows")) 
			{
				runtime.exec("cmd.exe /c start cmd.exe /k \"appium --relaxed-security -p 4725 -bp 4726 \"");
				Thread.sleep(10000);
			}
			else if(System.getProperty("os.name").contains("Mac")) 
			{
				runtime.exec("/bin/bash -c appium --relaxed-security -p 4725 -bp 4726");
				Thread.sleep(10000);
			}
		} 
		catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void stopServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
