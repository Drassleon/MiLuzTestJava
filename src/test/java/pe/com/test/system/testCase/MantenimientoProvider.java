package pe.com.test.system.testCase;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.system.selenium.dataManager.Excel;
import pe.com.test.system.selenium.page.BienvenidaPage;
import pe.com.test.system.selenium.page.IniciarSesionPage;
import pe.com.test.system.selenium.page.ProvidersPage;
import pe.com.test.system.selenium.util.Utilitario;
import pe.com.test.system.testlink.util.MiLuzTestlink;

public class MantenimientoProvider {

	private IniciarSesionPage iniciarSesionPage;
	private BienvenidaPage bienvenidaPage;
	private ProvidersPage providerPage;
	private String rutaCarpetaError="C:\\SoftwareExperimentos\\CapturasPantalla\\Proveedores";
	private Integer idNavegadorTestlink;
	private String nombreNavegadorTestlink;
	
	@BeforeTest
	@Parameters({"navegador","remoto","testlinkIdNavegador","testlinkNombreNavegador"})
	public void antesDelTest(String navegador, int remoto, int testlinkIdNavegador,
			String testlinkNombreNavegador) {
		this.iniciarSesionPage = new IniciarSesionPage(navegador, remoto == 1);
		this.bienvenidaPage = new BienvenidaPage(this.iniciarSesionPage.getWebDriver());
		this.providerPage = new ProvidersPage(this.bienvenidaPage.getWebDriver());
		this.idNavegadorTestlink= testlinkIdNavegador;
		this.nombreNavegadorTestlink = testlinkNombreNavegador;
	}
	
	@DataProvider(name="DatosDeEntradaInsertar")
	public static Object[][] datosDeEntradaInsertar(ITestContext contexto){
		String rutaArchivo = contexto.getCurrentXmlTest().getParameter("rutaArchivoExcel");
		return Excel.leerExcel(rutaArchivo, 0);
	}
	
	@DataProvider(name="DatosDeEntradaEditar")
	public static Object[][] datosDeEntradaEditar(ITestContext contexto){
		String rutaArchivo = contexto.getCurrentXmlTest().getParameter("rutaArchivoExcel");
		return Excel.leerExcel(rutaArchivo, 1);
	}
	
	@DataProvider(name="DatosDeEntradaEliminar")
	public static Object[][] datosDeEntradaEliminar(ITestContext contexto){
		String rutaArchivo = contexto.getCurrentXmlTest().getParameter("rutaArchivoExcel");
		return Excel.leerExcel(rutaArchivo, 2);
	}
	
	@Test(dataProvider = "DatosDeEntradaInsertar")
	public void a_insertarCategoria(String casoPrueba, String urlInicial, String usuario, String clave, 
			String nombre, String numeroTelefono, String valorEsperado, String urlTestlink, String keyTestlink, String idTestCaseInternoTestlink,
			String idTestCaseExternoTestlink, String idTestPlanTestlink, String idBuildTestlink,
			String nombreBuildTestlink) {
		try {
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion(usuario, clave);
			
			this.bienvenidaPage.hacerClicPestañaProveedores();
			
			this.providerPage.hacerClicBotonNuevo();
			this.providerPage.escribirCampoNombre(nombre.trim());
			this.providerPage.escribirCampoTelefono(numeroTelefono.trim());
			
			this.providerPage.hacerClicBolivianos();
			this.providerPage.hacerClicTieneDeuda();
			
			String valorObtenido = this.providerPage.hacerClicBotonGuardar();
			
			Assert.assertEquals(valorObtenido, valorEsperado);
			
			this.iniciarSesionPage.cerrarSesion();
			
			/*
			MiLuzTestLink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), Integer.parseInt(idTestPlanTestlink), true, 
					Integer.parseInt(idBuildTestlink), nombreBuildTestlink, "Se ejecuto correctamente", 
					this.idNavegadorTestlink, this.nombreNavegadorTestlink);
					*/
		}
		catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: " + e.getMessage(),
					providerPage.getWebDriver());
			
			/*
			MiLuzTestLink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), Integer.parseInt(idTestPlanTestlink), false, 
					Integer.parseInt(idBuildTestlink), nombreBuildTestlink, "Error:"+e.getMessage(), 
					this.idNavegadorTestlink, this.nombreNavegadorTestlink);
					*/
			
			Assert.fail(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}
	
	@Test(dataProvider = "DatosDeEntradaEditar")
	public void b_editarCategoria(String casoPrueba, String urlInicial, String usuario, String clave, 
			String nombreFiltrado, String valorEsperado, String urlTestlink, String keyTestlink, String idTestCaseInternoTestlink,
			String idTestCaseExternoTestlink, String idTestPlanTestlink, String idBuildTestlink,
			String nombreBuildTestlink) {
		try {
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion(usuario, clave);
			
			this.bienvenidaPage.hacerClicPestañaProveedores();
			
			this.providerPage.escribirCajaFiltrado(nombreFiltrado.trim());
			this.providerPage.hacerClicBotonEditar();
			this.providerPage.hacerClicBolivianos();
			
			String valorObtenido = this.providerPage.hacerClicBotonGuardar();
			
			Assert.assertEquals(valorObtenido, valorEsperado);
			
			this.iniciarSesionPage.cerrarSesion();
			
			/*
			MiLuzTestLink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), Integer.parseInt(idTestPlanTestlink), true, 
					Integer.parseInt(idBuildTestlink), nombreBuildTestlink, "Se ejecuto correctamente", 
					this.idNavegadorTestlink, this.nombreNavegadorTestlink);
					*/
		}
		catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: " + e.getMessage(),
					providerPage.getWebDriver());
			
			/*
			MiLuzTestLink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), Integer.parseInt(idTestPlanTestlink), false, 
					Integer.parseInt(idBuildTestlink), nombreBuildTestlink, "Error:"+e.getMessage(), 
					this.idNavegadorTestlink, this.nombreNavegadorTestlink);
					*/
			
			Assert.fail(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}
	
	@Test(dataProvider = "DatosDeEntradaEliminar")
	public void c_eliminarCategoria(String casoPrueba, String urlInicial, String usuario, String clave, 
			String nombreFiltrado, String valorEsperado, String urlTestlink, String keyTestlink, String idTestCaseInternoTestlink,
			String idTestCaseExternoTestlink, String idTestPlanTestlink, String idBuildTestlink,
			String nombreBuildTestlink) {
		try {
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion(usuario, clave);
			
			this.bienvenidaPage.hacerClicPestañaProveedores();
			
			this.providerPage.escribirCajaFiltrado(nombreFiltrado.trim());
			
			String valorObtenido = this.providerPage.hacerClicBotonEliminar();;
			
			Assert.assertEquals(valorObtenido, valorEsperado);
			
			this.iniciarSesionPage.cerrarSesion();
			
			/*
			MiLuzTestLink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), Integer.parseInt(idTestPlanTestlink), true, 
					Integer.parseInt(idBuildTestlink), nombreBuildTestlink, "Se ejecuto correctamente", 
					this.idNavegadorTestlink, this.nombreNavegadorTestlink);
					*/
		}
		catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: " + e.getMessage(),
					providerPage.getWebDriver());
			
			/*
			MiLuzTestLink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), Integer.parseInt(idTestPlanTestlink), false, 
					Integer.parseInt(idBuildTestlink), nombreBuildTestlink, "Error:"+e.getMessage(), 
					this.idNavegadorTestlink, this.nombreNavegadorTestlink);
					*/
			
			Assert.fail(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}
	
	@AfterTest
	public void despuesDelTest() {
		try {
			this.providerPage.cerrarPagina();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
