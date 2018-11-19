package pe.com.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import pe.com.test.system.selenium.driver.MiLuzDriver;

public class ProvidersPage {

	private WebDriver webDriver = null;
	private By botonNuevoProvider= By.xpath("/html/body/div/p[1]/a");
	private By textoNombreProvider= By.id("nombre");
	private By textoNumeroTelefono = By.id("numeroTelefonico");
	private By radioButtonBolivianos = By.id("tipoPago1");
	private By radioButtonDolares = By.id("tipoPago2");
	private By checkBoxHasDebt = By.id("existeDeuda1");
	private By botonGuardarProvider= By.xpath("/html/body/div/div/div[2]/form/div[5]/div/input");

	private By textBoxFiltrar = By.id("entradafilter");
	private By elementoFiltradoEditar = By.xpath("//*[@id=\"tablaFull\"]/tbody/tr[7]/td[6]/a");
	private By elementoFiltradoEliminar = By.xpath("//*[@id=\"tablaFull\"]/tbody/tr[11]/td[7]/a");
	private By alerta = By.xpath("/html/body/header/div");
	
	
	public ProvidersPage(String navegador, boolean remoto) {
		this.webDriver = MiLuzDriver.inicializarDriver(navegador, remoto);
		
	}
	
	public ProvidersPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	public void ingresarPaginaProviders(String url) throws Exception{
		this.webDriver.get(url);
	}
	public void hacerClicBotonNuevo()throws Exception{
		this.webDriver.findElement(botonNuevoProvider).click();
		Thread.sleep(2000);
	}
	
	public void escribirCampoNombre(String nombreProvider) throws Exception{
		this.webDriver.findElement(textoNombreProvider).clear();
		this.webDriver.findElement(textoNombreProvider).sendKeys(nombreProvider);
		Thread.sleep(2000);
	}
	
	public void escribirCampoTelefono(String phoneNumber) throws Exception{
		this.webDriver.findElement(textoNumeroTelefono).clear();
		this.webDriver.findElement(textoNumeroTelefono).sendKeys(phoneNumber);
		Thread.sleep(2000);
	}
	
	public void hacerClicBolivianos() throws Exception{
		this.webDriver.findElement(radioButtonBolivianos).click();
		Thread.sleep(2000);
	}
	
	public void hacerClicDolares() throws Exception{
		this.webDriver.findElement(radioButtonDolares).click();
		Thread.sleep(2000);

	}
	
	public String hacerClicBotonGuardar() throws Exception{
		this.webDriver.findElement(botonGuardarProvider).click();
		Thread.sleep(2000);
		return this.webDriver.findElement(alerta).getText();
	}
	
	public void hacerClicTieneDeuda() throws Exception{
		this.webDriver.findElement(checkBoxHasDebt).click();
		Thread.sleep(2000);
	}
	
	public void escribirCajaFiltrado(String proveedor) throws Exception{
		this.webDriver.findElement(textBoxFiltrar).clear();
		this.webDriver.findElement(textBoxFiltrar).sendKeys(proveedor);
		Thread.sleep(2000);
	}
	
	public void hacerClicBotonEditar() throws Exception{
		this.webDriver.findElement(elementoFiltradoEditar).click();
		Thread.sleep(2000);
	}
	
	public String hacerClicBotonEliminar() throws Exception{
		this.webDriver.findElement(elementoFiltradoEliminar).click();
		Thread.sleep(2000);
		return this.webDriver.findElement(alerta).getText();
	}
	
	public String mensajeAlerta() throws Exception{
		return this.webDriver.findElement(alerta).getText();
		}
	
	public void cerrarPagina() throws Exception{
		MiLuzDriver.cerrarPagina(this.webDriver);
	}

	public WebDriver getWebDriver() {
		return this.webDriver;
	}
}
