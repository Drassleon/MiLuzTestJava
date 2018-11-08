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
import pe.com.test.system.testlink.util.VisorTestlink;

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
	
	/*@Test(dataProvider = "DatosDeEntrada")
	public void insertarCategoria(String casoPrueba, String urlInicial,String usuario,
			String clave, String nombre,String telefono,Boolean dolares,Boolean bolivianos,String valorEsperado, String urlTestlink, String keyTestlink,
			String idTestCaseInternoTestlink, String idTestCaseExternoTestlink, 
			String idTestPlanTestlink, String idBuildTestLink, String nombreBuildTestLink) {
		try {
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion(usuario, clave);
			this.bienvenidaPage.hacerClicProviders();
			this.providerPage.hacerClicBotonNuevo();
			this.providerPage.escribirCampoNombre(nombre.trim());
			this.providerPage.escribirCampoTelefono(telefono.trim());
			if(dolares==true)
			{
				this.providerPage.hacerClicDolares();
			}
			else
			{
				this.providerPage.hacerClicBoliviano();
			}
			}catch(Exception e)
		{
				System.out.println(e.getStackTrace());
		}
	}*/
	@Test
	public void insertarCategoria_Criterio01() {
		try {
			this.providerPage.ingresarPaginaProviders("http://localhost:8080/proveedor/listar");
			this.providerPage.hacerClicBotonNuevo();
			this.providerPage.escribirCampoNombre("Samsung");
			this.providerPage.escribirCampoTelefono("123456789");
			//if(dolares==true)
			//{
				this.providerPage.hacerClicDolares();
			//}
			//else
			//{
			//	this.providerPage.hacerClicBoliviano();
			//}
				this.providerPage.hacerClicBotonGuardar();
		}catch(Exception e)
		{
			System.out.println(e.getStackTrace());
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
