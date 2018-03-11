package io.mincongh.client;

import com.google.gwt.junit.client.GWTTestCase;
import io.mincongh.shared.R;

/**
 * Tests JSNI: JavaScript Native Interface.
 *
 * @author Mincong Huang
 */
public class JsniTestMatching extends GWTTestCase {

  @Override
  public String getModuleName() {
    return R.JUNIT_MODULE;
  }

  /**
   * Tests type mismatch between the code inside the JSNI method and
   * the Java declaration. The GWT generated interface code caught
   * the problem at runtime in development mode. When running in
   * production mode, you will not see an exception. JavaScript’s
   * dynamic typing obscures this kind of problem.
   */
  public void testBadExample() throws Exception {
    try {
      badExample();
      fail("You should run this test in development mode. When"
          + " running in production mode, you will not see an exception.");
    } catch (Exception e) {
      String expected = "JS value of type string, expected int";
      String actual = e.getMessage();
      assertTrue(actual, actual.contains(expected));
    }
  }

  private static native int badExample() /*-{
    return "Not A Number";
  }-*/;

  /**
   * Tests Java method invocation from JavaScript.
   *
   * <p>Calling Java methods from JavaScript is somewhat similar to
   * calling Java methods from C code in JNI. In particular, JSNI
   * borrows the JNI mangled method signature approach to distinguish
   * among overloaded methods. JavaScript calls into Java methods are
   * of the following form:
   * <pre>
   * [instance-expr.]@class-name::method-name(param-signature)(arguments)
   * </pre>
   */
  public void testJavaMethodInvocations() throws Exception {
    assertEquals(1, new TopLevel().getInt());
    assertEquals(1, newTopLevel().getInt());

    assertEquals(2, new TopLevel(2).getInt());
    assertEquals(3, new TopLevel(3).getInt());
    assertEquals(2, newTopLevel(2).getInt());
    assertEquals(3, newTopLevel(3).getInt());

    assertEquals(1F, new TopLevel.StaticInner().getFloat());
    assertEquals(2F, new TopLevel.StaticInner(2F).getFloat());
    assertEquals(1F, newTopLevelStaticInner().getFloat());
    assertEquals(2F, newTopLevelStaticInner(2F).getFloat());

    TopLevel instance = new TopLevel();
    assertEquals('a', instance.new InstanceInner().getChar());
    assertEquals('b', instance.new InstanceInner('b').getChar());
    assertEquals('a', newInstanceInner(instance).getChar());
    assertEquals('b', newInstanceInner(instance, 'b').getChar());
  }

  private native TopLevel newTopLevel() /*-{
    return @io.mincongh.client.TopLevel::new()();
  }-*/;

  private native TopLevel newTopLevel(int myInt) /*-{
    return @io.mincongh.client.TopLevel::new(I)(myInt);
  }-*/;

  private native TopLevel.StaticInner newTopLevelStaticInner() /*-{
    return @io.mincongh.client.TopLevel.StaticInner::new()();
  }-*/;

  private native TopLevel.StaticInner newTopLevelStaticInner(float myFloat) /*-{
    return @io.mincongh.client.TopLevel.StaticInner::new(F)(myFloat);
  }-*/;

  // IntelliJ: incorrect inspection
  private native TopLevel.InstanceInner newInstanceInner(TopLevel topLevel) /*-{
    return @io.mincongh.client.TopLevel.InstanceInner::new(Lio/mincongh/client/TopLevel;)(topLevel);
  }-*/;

  // IntelliJ: incorrect inspection
  private native TopLevel.InstanceInner newInstanceInner(TopLevel topLevel, char myChar) /*-{
    return @io.mincongh.client.TopLevel.InstanceInner::new(Lio/mincongh/client/TopLevel;C)(topLevel,
        myChar);
  }-*/;

  public void testExportedStaticMethod() throws Exception {
    exportMethod();
    assertEquals(3, applyExportedMethod(1, 1.5F, 2));
  }

  /**
   * Exports the static method to an external, globally visible
   * JavaScript name that can be referenced by the hand-crafted
   * JavaScript code.
   *
   * <p>Note that when there're several method-parameters, the type
   * signatures are <b>combined together</b>, without any separator.
   */
  private static native void exportMethod() /*-{
    $wnd.computeLoanInterest = $entry(
        @io.mincongh.client.JsniTestMatching.MyUtility::computeLoanInterest(IFI));
  }-*/;

  private static native int applyExportedMethod(int amount, float interestRate, int term) /*-{
    return $wnd.computeLoanInterest(amount, interestRate, term);
  }-*/;

  private static class MyUtility {

    private static native int computeLoanInterest(int amount, float interestRate, int term) /*-{
      return Math.floor(amount * interestRate * term);
    }-*/;

  }

}
