package io.mincongh.shared;

/**
 * Utility class for resources.
 *
 * @author Mincong Huang
 */
public final class R {

  /**
   * The GWT module name for JUnit testing.
   */
  public static final String JUNIT_MODULE = "io.mincongh.StockMarketJUnit";

  /**
   * Simple URL which returns HTTP response in JSON format.
   */
  public static final String JSON_URL = "StockMarket/jsonServlet?q=ABC+DEF";

  private R() {
    // Private class, do not instantiate
  }

}
