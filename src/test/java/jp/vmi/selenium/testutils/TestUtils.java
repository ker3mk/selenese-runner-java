package jp.vmi.selenium.testutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.vmi.selenium.webdriver.ChromeDriverFactory;
import jp.vmi.selenium.webdriver.FirefoxDriverFactory;
import jp.vmi.selenium.webdriver.HtmlUnitDriverFactory;
import jp.vmi.selenium.webdriver.IEDriverFactory;
import jp.vmi.selenium.webdriver.PhantomJSDriverFactory;
import jp.vmi.selenium.webdriver.SafariDriverFactory;

/**
 * Utility for Test.
 */
public final class TestUtils {

    private TestUtils() {
        // no operation
    }

    /**
     * Get script file.
     *
     * @param name script name. (without extension)
     * @return script file path.
     */
    public static String getScriptFile(String name) {
        String html = "/selenese/" + name + ".html";
        URL resource = TestUtils.class.getResource(html);
        if (resource == null)
            throw new RuntimeException(new FileNotFoundException(html));
        try {
            return new File(resource.toURI()).getCanonicalPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate parameter list for JUnit parameterized test.
     *
     * @param params parameters.
     * @return list of single element array of each paramter.
     */
    public static <T> List<Object[]> toParamList(T... params) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (T param : params)
            list.add(new Object[] { param });
        return list;
    }

    /**
     * Get WebDriverFactory's for JUnit parameterized test.
     *
     * @return list of single element array of WebDriverFactory.
     */
    public static List<Object[]> getWebDriverFactories() {
        return toParamList(
            new HtmlUnitDriverFactory()
            , new FirefoxDriverFactory()
            , new ChromeDriverFactory()
            , new PhantomJSDriverFactory()
            , new IEDriverFactory()
            , new SafariDriverFactory());
    }
}