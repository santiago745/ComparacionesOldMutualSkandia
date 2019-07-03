package apkDemo.appium;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class UtilsMobileTwo {
	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;

	public int waitingTime=60;
	public final Logger LOGGER = Logger.getLogger(UtilsMobile.class.getName());
	public String screenshotFolder="logs/";
	private String configFile;
	public String fecha="";
	
	public UtilsMobileTwo(String fecha) {
		configFile="./recursos.properties";
		Properties mainProperties = new Properties();

	    FileInputStream file;
	    try {
			file = new FileInputStream(configFile);
			mainProperties.load(file);
		    file.close();
		} catch (Exception e1) {
			System.err.println("No pude cargar archivo de propiedades "+e1.getMessage());
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
    	capabilities.setCapability("deviceName", mainProperties.getProperty("deviceName"));
    	capabilities.setCapability("app", System.getProperty("user.dir")+mainProperties.getProperty("app"));
    	capabilities.setCapability("platformName", mainProperties.getProperty("platformName"));
    	capabilities.setCapability("platformVersion", mainProperties.getProperty("platformVersion"));
    	capabilities.setCapability("noReset", mainProperties.getProperty("noReset"));
    	try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			System.err.println("Error inicializando driver en server appium "+e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		this.fecha=fecha;
		
	}

}
