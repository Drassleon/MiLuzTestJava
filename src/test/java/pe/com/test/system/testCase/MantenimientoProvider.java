package pe.com.test.system.testCase;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.system.selenium.dataManager.Excel;
import pe.com.test.system.selenium.page.ProvidersPage;
import pe.com.test.system.selenium.util.Utilitario;
import pe.com.test.system.testlink.util.MiLuzTestlink;

public class MantenimientoProvider {

	private ProvidersPage providerPage;
	private String rutaCarpetaError="D:\\SoftwareExperimentos\\CapturasPantalla\\Categorias";
	private Integer idNavegadorTestlink;
	private String nombreNavegadorTestlink;
	
	@BeforeTest
	@Parameters({"navegador","remoto","testlinkIdNavegador","testlinkNombreNavegador"})
	public void antesDelTest(String navegador, int remoto, int testlinkIdNavegador,
			String testlinkNombreNavegador) {
		this.providerPage = new ProvidersPage(navegador, remoto==1);
		this.idNavegadorTestlink= testlinkIdNavegador;
		this.nombreNavegadorTestlink = testlinkNombreNavegador;
	}
	
	@DataProvider(name="DatosDeEntrada")
	public static Object[][] datosDeEntrada(ITestContext contexto){
		String rutaArchivo = contexto.getCurrentXmlTest().getParameter("rutaArchivoExcel");
		return Excel.leerExcel(rutaArchivo);
	}
	//Insertar Coca Cola Company como proveedor con pago en dolares
	/*@Test(dataProvider = "DatosDeEntrada")
	public void insertarCategoriaCriterio01(
			String casoPrueba,
			String urlInicial,
			String nombre,
			String telefono,
			String dolares,
			String bolivianos,
			String valorEsperado, 
			String urlTestlink, 
			String keyTestlink,
			String idTestCaseInternoTestlink, 
			String idTestCaseExternoTestlink, 
			String idTestPlanTestlink, 
			String idBuildTestLink, 
			String nombreBuildTestLink) {
		try {
			this.providerPage.ingresarPaginaProviders(urlInicial);
			this.providerPage.hacerClicBotonNuevo();
			this.providerPage.escribirCampoNombre(nombre);
			this.providerPage.escribirCampoTelefono(telefono);
			if(dolares!="")
			{
				this.providerPage.hacerClicDolares();
			}
			else
			{
				this.providerPage.hacerClicBolivianos();
			}
				this.providerPage.hacerClicBotonGuardar();
				String valorObtenido = this.providerPage.mensajeAlerta();
				
				Assert.assertEquals(valorObtenido, "Proveedor guardado con éxito");
				MiLuzTestlink.reportarCasoDePrueba(
						urlTestlink, 
						keyTestlink, 
						Integer.parseInt(idTestCaseInternoTestlink),
						Integer.parseInt(idTestCaseExternoTestlink), 
						Integer.parseInt(idTestPlanTestlink),
						true,
						Integer.parseInt(idBuildTestLink), 
						nombreBuildTestLink, 
						"Se ejecuto correctamente", 
						this.idNavegadorTestlink, 
						this.nombreNavegadorTestlink);
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			MiLuzTestlink.reportarCasoDePrueba(
					urlTestlink, 
					keyTestlink, 
					Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), 
					Integer.parseInt(idTestPlanTestlink),
					false,
					Integer.parseInt(idBuildTestLink), 
					nombreBuildTestLink, 
					"Se ejecuto correctamente", 
					this.idNavegadorTestlink, 
					this.nombreNavegadorTestlink);
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}*/
	//Insertar Sin excel
	@Test
	public void ainsertarCategoria_Criterio01() {
		try {
			this.providerPage.ingresarPaginaProviders("http://localhost:8080/proveedor/listar");
			this.providerPage.hacerClicBotonNuevo();
			this.providerPage.escribirCampoNombre("Samsung");
			this.providerPage.escribirCampoTelefono("123456789");
			//if(dolares!="")
			//{
				this.providerPage.hacerClicDolares();
			//}
			//else
			//{
			//	this.providerPage.hacerClicBoliviano();
			//}
				this.providerPage.hacerClicBotonGuardar();
				String valorObtenido = this.providerPage.mensajeAlerta();
				
				Assert.assertEquals(valorObtenido, "Proveedor creado con éxito!");
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}
	@Test
	public void binsertarCategoria_Criterio02() {
		try {
			this.providerPage.ingresarPaginaProviders("http://localhost:8080/proveedor/listar");
			this.providerPage.hacerClicBotonNuevo();
			this.providerPage.escribirCampoNombre("");
			this.providerPage.escribirCampoTelefono("");

			this.providerPage.hacerClicBotonGuardar();
			String valorObtenido = this.providerPage.mensajeAlerta();

			Assert.assertEquals(valorObtenido, "El nombre de proveedor no puede ser vacío");
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}
	
	@Test
	public void ceditarCategoria_Criterio01() {
		try {
			this.providerPage.ingresarPaginaProviders("http://localhost:8080/proveedor/listar");
			this.providerPage.escribirCajaFiltrado("Samsung");
			this.providerPage.hacerClicBotonEditar();
			this.providerPage.hacerClicBolivianos();
			this.providerPage.hacerClicBotonGuardar();
			String valorObtenido = this.providerPage.mensajeAlerta();

			Assert.assertEquals(valorObtenido, "Proveedor editado con éxito!");
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}
	
	@Test
	public void deditarCategoria_Criterio02() {
		try {
			this.providerPage.ingresarPaginaProviders("http://localhost:8080/proveedor/listar");
			this.providerPage.escribirCajaFiltrado("Samsung");
			this.providerPage.hacerClicBotonEditar();
			this.providerPage.escribirCampoNombre("");
			this.providerPage.hacerClicBotonGuardar();
			String valorObtenido = this.providerPage.mensajeAlerta();

			Assert.assertEquals(valorObtenido, "El nombre de proveedor no puede ser vacío");
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}
	//Este caso de prueba es con el nombre en blanco
	/*@Test(dataProvider = "DatosDeEntrada")
	public void insertarCategoriaCriterio02(
			String casoPrueba,
			String urlInicial,
			String nombre,
			String telefono,
			String dolares,
			String bolivianos,
			String valorEsperado, 
			String urlTestlink, 
			String keyTestlink,
			String idTestCaseInternoTestlink, 
			String idTestCaseExternoTestlink, 
			String idTestPlanTestlink, 
			String idBuildTestLink, 
			String nombreBuildTestLink) {
		try {
			this.providerPage.ingresarPaginaProviders(urlInicial);
			this.providerPage.hacerClicBotonNuevo();
			this.providerPage.escribirCampoNombre(nombre);
			this.providerPage.escribirCampoTelefono(telefono);
			if(dolares!="")
			{
				this.providerPage.hacerClicDolares();
			}
			else
			{
				this.providerPage.hacerClicBolivianos();
			}
				this.providerPage.hacerClicBotonGuardar();
				String valorObtenido = this.providerPage.mensajeAlerta();
				
				Assert.assertEquals(valorObtenido, "Proveedor guardado con éxito");
				MiLuzTestlink.reportarCasoDePrueba(
						urlTestlink, 
						keyTestlink, 
						Integer.parseInt(idTestCaseInternoTestlink),
						Integer.parseInt(idTestCaseExternoTestlink), 
						Integer.parseInt(idTestPlanTestlink),
						true,
						Integer.parseInt(idBuildTestLink), 
						nombreBuildTestLink, 
						"Se ejecuto correctamente", 
						this.idNavegadorTestlink, 
						this.nombreNavegadorTestlink);
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			MiLuzTestlink.reportarCasoDePrueba(
					urlTestlink, 
					keyTestlink, 
					Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), 
					Integer.parseInt(idTestPlanTestlink),
					false,
					Integer.parseInt(idBuildTestLink), 
					nombreBuildTestLink, 
					"Se ejecuto correctamente", 
					this.idNavegadorTestlink, 
					this.nombreNavegadorTestlink);
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}*/
	//Editar cambiando de dolares a bolivianos
	/*@Test(dataProvider = "DatosDeEntrada")
	public void editarCategoriaCriterio01(String casoPrueba,
			String urlInicial,
			String nombre,
			String telefono,
			String dolares,
			String bolivianos,
			String valorEsperado, 
			String urlTestlink, 
			String keyTestlink,
			String idTestCaseInternoTestlink, 
			String idTestCaseExternoTestlink, 
			String idTestPlanTestlink, 
			String idBuildTestLink, 
			String nombreBuildTestLink) {
		try {
			this.providerPage.ingresarPaginaProviders(urlInicial);
			this.providerPage.escribirCajaFiltrado(nombre);
			this.providerPage.hacerClicBotonEditar();
			if(dolares!="")
			{
				this.providerPage.hacerClicDolares();
			}
			else
			{
				this.providerPage.hacerClicBolivianos();
			}
				this.providerPage.hacerClicBotonGuardar();
				String valorObtenido = this.providerPage.mensajeAlerta();
				
				Assert.assertEquals(valorObtenido, valorEsperado);
				MiLuzTestlink.reportarCasoDePrueba(
						urlTestlink, 
						keyTestlink, 
						Integer.parseInt(idTestCaseInternoTestlink),
						Integer.parseInt(idTestCaseExternoTestlink), 
						Integer.parseInt(idTestPlanTestlink),
						true,
						Integer.parseInt(idBuildTestLink), 
						nombreBuildTestLink, 
						"Se ejecuto correctamente", 
						this.idNavegadorTestlink, 
						this.nombreNavegadorTestlink);
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			MiLuzTestlink.reportarCasoDePrueba(
					urlTestlink, 
					keyTestlink, 
					Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), 
					Integer.parseInt(idTestPlanTestlink),
					false,
					Integer.parseInt(idBuildTestLink), 
					nombreBuildTestLink, 
					"Se ejecuto correctamente", 
					this.idNavegadorTestlink, 
					this.nombreNavegadorTestlink);
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}*/
	//Editar cambiando el nombre del proveedor a vacio
	/*@Test(dataProvider = "DatosDeEntrada")
	public void editarCategoriaCriterio02(
			String casoPrueba,
			String urlInicial,
			String nombre,
			String telefono,
			String dolares,
			String bolivianos,
			String valorEsperado,
			String urlTestlink,
			String keyTestlink,
			String idTestCaseInternoTestlink,
			String idTestCaseExternoTestlink, 
			String idTestPlanTestlink,
			String idBuildTestLink,
			String nombreBuildTestLink) {
		try {
			this.providerPage.ingresarPaginaProviders(urlInicial);
			this.providerPage.escribirCajaFiltrado(nombre);
			this.providerPage.hacerClicBotonEditar();
			if(dolares!="")
			{
				this.providerPage.hacerClicDolares();
			}
			else
			{
				this.providerPage.hacerClicBolivianos();
			}
				this.providerPage.hacerClicBotonGuardar();
				String valorObtenido = this.providerPage.mensajeAlerta();
				
				Assert.assertEquals(valorObtenido, valorEsperado);
				MiLuzTestlink.reportarCasoDePrueba(
						urlTestlink, 
						keyTestlink, 
						Integer.parseInt(idTestCaseInternoTestlink),
						Integer.parseInt(idTestCaseExternoTestlink), 
						Integer.parseInt(idTestPlanTestlink),
						true,
						Integer.parseInt(idBuildTestLink), 
						nombreBuildTestLink, 
						"Se ejecuto correctamente", 
						this.idNavegadorTestlink, 
						this.nombreNavegadorTestlink);
				
		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());
			
			MiLuzTestlink.reportarCasoDePrueba(
					urlTestlink, 
					keyTestlink, 
					Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), 
					Integer.parseInt(idTestPlanTestlink),
					false,
					Integer.parseInt(idBuildTestLink), 
					nombreBuildTestLink, 
					"Se ejecuto correctamente", 
					this.idNavegadorTestlink, 
					this.nombreNavegadorTestlink);
			
			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}*/
	//Eliminar Coca Cola Company
	/*@Test(dataProvider = "DatosDeEntrada")
	public void eliminarCategoriaCriterio01(
			String casoPrueba,
			String urlInicial,
			String nombre,
			String telefono,
			String dolares,
			String bolivianos,
			String valorEsperado, 
			String urlTestlink, 
			String keyTestlink,
			String idTestCaseInternoTestlink, 
			String idTestCaseExternoTestlink, 
			String idTestPlanTestlink, 
			String idBuildTestLink, 
			String nombreBuildTestLink) {
		try {
			this.providerPage.ingresarPaginaProviders(urlInicial);
			this.providerPage.escribirCajaFiltrado(nombre);
			this.providerPage.hacerClicBotonEliminar();
			//this.providerPage.hacerClicBotonConfirmar();
			String valorObtenido = this.providerPage.mensajeAlerta();

			Assert.assertEquals(valorObtenido, valorEsperado);
			MiLuzTestlink.reportarCasoDePrueba(
					urlTestlink, 
					keyTestlink, 
					Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), 
					Integer.parseInt(idTestPlanTestlink),
					true,
					Integer.parseInt(idBuildTestLink), 
					nombreBuildTestLink, 
					"Se ejecuto correctamente", 
					this.idNavegadorTestlink, 
					this.nombreNavegadorTestlink);

		}catch(AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: "+e.getMessage(),
					providerPage.getWebDriver());

			MiLuzTestlink.reportarCasoDePrueba(
					urlTestlink, 
					keyTestlink, 
					Integer.parseInt(idTestCaseInternoTestlink),
					Integer.parseInt(idTestCaseExternoTestlink), 
					Integer.parseInt(idTestPlanTestlink),
					false,
					Integer.parseInt(idBuildTestLink), 
					nombreBuildTestLink, 
					"Se ejecuto correctamente", 
					this.idNavegadorTestlink, 
					this.nombreNavegadorTestlink);

			Assert.fail("Error: "+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: "+e.getMessage());
		}
	}*/


	
	@AfterTest
	public void despuesDelTest() {
		try {
			this.providerPage.cerrarPagina();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
