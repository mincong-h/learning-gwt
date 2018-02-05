package io.mincongh.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * <h2>Benefits of using overlay types.</h2>
 *
 * <p>Using an overlay type creates a normal looking Java type that
 * you can interact with using code completion, refactoring, and
 * compile-time checking. Yet, you also have the flexibility of
 * interacting with arbitrary JavaScript objects, which makes it
 * simpler to access JSON services using RequestBuilder (which you'll
 * do in the next section).
 *
 * <p>GWT now understands that any instance of StockData is a true
 * JavaScript object that comes from outside this GWT module. YOu can
 * interact with it exactly as it exists in JavaScript. In this
 * example, you can access directly the JSON fields you know exists:
 * this.Price and this.Change.
 *
 * <p>Because the methods on overlay types can be statically resolved
 * by the GWT compiler, they are candidates for automatic inlining.
 * Inlined code runs significantly faster. This makes it possible for
 * the GWT compiler to create highly-optimized JavaScript for your
 * application's client-side code.
 *
 * @author Mincong Huang
 * @see <a href="http://www.gwtproject.org/doc/latest/tutorial/JSON.html"> Retrieving JSON</a>
 */
public class StockData extends JavaScriptObject {

  protected StockData() {
    // Overlay types always have protected, zero-argument constructors.
  }

  public final native String getSymbol() /*-{
    return this.symbol;
  }-*/;

  public final native double getPrice() /*-{
    return this.price;
  }-*/;

  public final native double getChange() /*-{
    return this.change;
  }-*/;

  public final native double getChangePercent() /*-{
    return 100.00 * getChange() / getPrice();
  }-*/;

}
