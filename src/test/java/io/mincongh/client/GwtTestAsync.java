package io.mincongh.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import io.mincongh.shared.R;

/**
 * GWT’s JUnit integration provides special support for testing
 * functionality that cannot be executed in straight-line code.
 * For example, you might want to make an RPC call to a server and
 * then validate the response. To support this use case, GWTTestCase
 * has extended the TestCase API. The two key methods are
 * GWTTestCase.delayTestFinish(int) and GWTTestCase.finishTest().
 *
 * @author Mincong Huang
 */
public class GwtTestAsync extends GWTTestCase {

  @Override
  public String getModuleName() {
    return R.JUNIT_MODULE;
  }

  public void testTimer() {
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      @Override
      public void run() {
        // do some validation logic

        // tell the test system the test is now done
        finishTest();
      }
    };

    // Set a delay period significantly longer than the
    // event is expected to take.
    delayTestFinish(500);

    // Schedule the event and return control to the test system.
    timer.schedule(100);
  }

}
