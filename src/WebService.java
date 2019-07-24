import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebService {
	
	public static void main(String args []) {
		File file = new File("C:/Selenium/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.localist.co.nz");

		excuteJavascript(driver,"document.getElementById('search-combo-what').value='City or Suburb'");
		excuteJavascript(driver,"$('.search-combo__btn').click();");
		scrape(driver);
	}
	
	public static void excuteJavascript(WebDriver driver, String script){
		WebElement element = (WebElement)((JavascriptExecutor)driver).executeScript(script);
	}
	
	public static void scrape(WebDriver driver){
		WebElement element = driver.findElement(By.className("js-results-map-datum"));
		String result = element.getAttribute("innerHTML");
		System.out.println(result);
	}
}
