package io.mincongh.client.interop;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.junit.client.GWTTestCase;
import io.mincongh.shared.R;
import jsinterop.annotations.JsType;

/**
 * JsInterop is one of the core features of GWT 2.8. As the name suggests, JsInterop is a way of
 * interoperating Java with JavaScript. It offers a better way of communication between the two
 * using annotations instead of having to write JavaScript in your classes (using JSNI).
 *
 * @author Mincong Huang
 */
public class GwtTestJsInterop extends GWTTestCase {
  @Override
  public void gwtSetUp() {
    String js = "var ns = { Person: function() { this.sayHello = function() { return 'Hi'; }; } }";
    ScriptInjector.fromString(js).setWindow(ScriptInjector.TOP_WINDOW).inject();
  }

  @Override
  public String getModuleName() {
    return R.JUNIT_MODULE;
  }

  public void testJsni() {
    assertEquals("Hi", sayHello());
  }

  private native String sayHello() /*-{
    var p = new $wnd.ns.Person();
    return p.sayHello();
  }-*/;

  public void testJsType() {
    Person p = new Person();
    String words = p.sayHello();
    assertEquals("Hi", words);
  }

  @JsType(namespace = "ns", isNative = true)
  private static class Person {
    public native String sayHello();
  }
}
