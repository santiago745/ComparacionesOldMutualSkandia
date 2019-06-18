package apkDemo.appium;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;



public class UtilsMobile {
	
	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;

	public int waitingTime=60;
	public final Logger LOGGER = Logger.getLogger(UtilsMobile.class.getName());
	public String screenshotFolder="logs/";
	private String configFile;
	public String fecha="";
	
	public UtilsMobile(String fecha) {
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

	
	public ResourceBundle cargarPropiedades(String archivo) {
		ResourceBundle rb = ResourceBundle.getBundle(archivo);
		return rb;
	}
	
	public void screenshot(String descripcion) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(screenshotFolder+"/"+fecha+"/screenshots/"+descripcion+".png"));
		} catch (IOException e) {
			System.err.println("No se pudo tomar el pantallazo "+e.getMessage());
		}
	}
	
	 public WebElement expandRootElement(WebElement element) {
			WebElement ele = (WebElement) ((JavascriptExecutor) driver)
	.executeScript("return arguments[0].shadowRoot",element);
			return ele;
		}
	 
	 public String prettyprintxml(String dom) {
		 org.w3c.dom.Element node=null;
			try {
				node =  DocumentBuilderFactory
			    .newInstance()
			    .newDocumentBuilder()
			    .parse(new ByteArrayInputStream(dom.getBytes()))
			    .getDocumentElement();
			} catch (Exception e) {
				System.err.println("Error parseando dom "+e.getMessage());
			} 
		 
		 Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			 transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			 //initialize StreamResult with File object to save to file
			 StreamResult result = new StreamResult(new StringWriter());
			 DOMSource source = new DOMSource(node);
			 try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				System.err.println("error "+e.getMessage());
			}
			 String xmlString = result.getWriter().toString();
			 return xmlString;
		} catch (Exception e1) {
			return null;
		}
		 
	 }
	 
	 
	 
}