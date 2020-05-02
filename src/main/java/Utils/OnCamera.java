package Utils;

import java.io.IOException;

public class OnCamera {
	
	public void clickShutter() {
	Runtime runtime = Runtime.getRuntime();
	try {
		runtime.exec("cmd.exe /c start cmd.exe /k \"adb shell input keyevent 27");
		Thread.sleep(10000);
	} catch (IOException | InterruptedException e) {
		e.printStackTrace();
	}
	}
}
