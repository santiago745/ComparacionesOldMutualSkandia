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



public class UtilsMobile extends UtilsMobileTwo {	

	
	public UtilsMobile(String fecha) {
		super(fecha);
		// TODO Auto-generated constructor stub
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