package io.mincongh.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import io.mincongh.shared.R;

/**
 * A simple test case for coverage measure.
 *
 * @author Mincong Huang
 */
public class GwtTestCoverage extends GWTTestCase {

  @Override
  public String getModuleName() {
    return R.JUNIT_MODULE;
  }

  public void testFactorial() {
    assertEquals(1, computeFactorial(0));
    assertEquals(1, computeFactorial(1));
    assertEquals(2, computeFactorial(2));
    assertEquals(6, computeFactorial(3));
  }

  private int computeFactorial(int number) {
    if (number <= 1) {
      return 1;
    }
    return number * computeFactorial(number - 1);
  }

}
