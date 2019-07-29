package io.mincongh.client;

import com.google.gwt.junit.client.GWTTestCase;
import io.mincongh.shared.R;

/**
 * Test GWT JSNI.
 *
 * @author Mincong Huang
 */
public class GwtTestJsni extends GWTTestCase {

  @Override
  public String getModuleName() {
    return R.JUNIT_MODULE;
  }

  /**
   * JSNI method compilation error with javascript regular expression
   *
   * @see <a href="https://github.com/gwtproject/gwt/issues/9665"></a>
   */
  public void testGwtIssue9665() {
    methodWithRegularExpression();
  }

  public static native void methodWithRegularExpression() /*-{
    // Compilation error:
    // var reg = /^[\\w+/_-]+[=]{0,2}$/
    var reg = new RegExp("/^[\\w+/_-]+[=]{0,2}$/");
    console.log("reg:" + reg)
  }-*/;
}
