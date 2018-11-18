package pe.com.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.system.selenium.driver.MiLuzDriver;

public class IniciarSesionPage {

	private By cajaUsuario = By.id("username"); 
	private By cajaClave = By.id("password");
	private By botonIniciarSesion = By.xpath("/html/body/div/div/div[2]/form/div[3]/input");
	private By botonCerrarSesion = By.xpath("//*[@id=\"logoutForm\"]/button");
	private WebDriver webDriver = null;
	
	public IniciarSesionPage(String navegador, boolean remoto) {
		this.webDriver = MiLuzDriver.inicializarDriver(navegador, remoto);
	}
	
	public void ingresarPaginaIniciarSesion(String url) throws Exception{
		this.webDriver.get(url);
	}
	
	public void iniciarSesion(String usuario, String clave) throws Exception{
		this.webDriver.findElement(cajaUsuario).clear();
		this.webDriver.findElement(cajaUsuario).sendKeys(usuario);
		this.webDriver.findElement(cajaClave).clear();
		this.webDriver.findElement(cajaClave).sendKeys(clave);
		this.webDriver.findElement(botonIniciarSesion).click();
	}
	
	public void cerrarSesion() throws Exception {
		this.webDriver.findElement(botonCerrarSesion).click();
	}
	
	public void cerrarPagina() throws Exception{
		MiLuzDriver.cerrarPagina(this.webDriver);
	}
	
	public WebDriver getWebDriver() {
		return this.webDriver;
	}
}