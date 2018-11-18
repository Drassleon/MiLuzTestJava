package pe.com.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BienvenidaPage {
	
	private By pestañaProveedores = By.xpath("/html/body/header/nav/div/ul[1]/li[2]/a");
	private WebDriver webDriver = null;
	
	public BienvenidaPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void hacerClicPestañaProveedores() throws Exception{
		this.webDriver.findElement(pestañaProveedores).click();
		Thread.sleep(2000);
	}
	
	public WebDriver getWebDriver() {
		return this.webDriver;
	}
}