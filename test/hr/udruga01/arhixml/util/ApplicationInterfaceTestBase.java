package hr.udruga01.arhixml.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Class is used as a base class for testing application user interface.
 * <p>
 * Every class that have test cases which must test aplication user interface
 * should extend this class. Class has <code>@BeforeClass</code> and
 * <code>@AfterClass</code> defined.
 * <p>
 * <code>@BeforeClass</code> is used to initialize embedded servlet container
 * and start it. It is also use to initialize Selenium {@link WebDriver} and
 * start the web browser. <code>@AfterClass</code> is used to stop Selenium
 * which also quits the web browser and to stop emmbeded serlvet container.
 * <p>
 * The above described steps are necessary in order to fully prepare all the
 * prerequisites for testing application user interface.
 */
public abstract class ApplicationInterfaceTestBase {
    private static final int PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String RESOURCE_BASE = "WebContent";
    private static final String BASE_URL = "http://localhost:8080/ArhiXML";

    private static Server server;
    protected static WebDriverBackedSelenium selenium;

    /**
     * This method is automatically called by JUnit framework before testing.
     * It will initialize and run the embedded serlvet container. It also initializes
     * Selenium framework and runs web browser.
     * 
     * @throws Exception
     *             Exception is thrown if embedded servlet container failed to
     *             start.
     */
    @BeforeClass
    public static void suiteSetup() throws Exception {
        server = new Server(PORT);

        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(CONTEXT_PATH);
        webApp.setResourceBase(RESOURCE_BASE);
        webApp.setServer(server);

        server.setHandler(webApp);
        server.start();

        WebDriver driver = new FirefoxDriver();
        selenium = new WebDriverBackedSelenium(driver, BASE_URL);
        selenium.open("/");
        selenium.waitForPageToLoad("20000");
    }

    /**
     * This method is automatically called by JUnit framework after tests are executed.
     * This way we clean up the testing environment. It will stop Selenium and embedded
     * servlet container.
     */
    @AfterClass
    public static void suiteTeardown() throws Exception {
        if (selenium != null) {
            selenium.stop();
        }
        if (server != null) {
            server.stop();
        }
    }
}
