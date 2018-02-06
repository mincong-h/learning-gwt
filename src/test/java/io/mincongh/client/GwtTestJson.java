package io.mincongh.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.junit.client.GWTTestCase;
import io.mincongh.shared.R;

/**
 * Tests basic GWT usage with JSON. It includes:
 * <ul>
 * <li>Parsing JSON</li>
 * <li>Mash-ups with JSON and JSNI</li>
 * </ul>
 *
 * @author Mincong Huang
 */
public class GwtTestJson extends GWTTestCase {

  @Override
  public String getModuleName() {
    return R.JUNIT_MODULE;
  }

  /**
   * Tests the JSON object parsing.
   *
   * <p>The returned value is defined as
   * {@code T extends JavaScriptObject}, which means that using any
   * subtypes T of {@link JavaScriptObject} will compile
   * successfully, including itself. {@link JavaScriptObject} uses
   * native methods, written in JSNI: JavaScript Native Interface.
   */
  public void testJsonObject() throws Exception {
    Card card = JsonUtils.safeEval("{\"id\":1}");
    assertEquals(1, card.getId());
  }

  /**
   * Tests the JSON array parsing.
   *
   * <p>Note that the payload must be evaluated to an array, not a
   * primitive or a string.
   */
  public void testJsonArray() throws Exception {
    JsArray<Card> array = JsonUtils.safeEval("[{\"id\":1},{\"id\":2}]");
    assertEquals(1, array.get(0).getId());
    assertEquals(2, array.get(1).getId());
  }

  /**
   * Tests {@link JsArrayInteger}, a simple wrapper around a
   * homogeneous native array of integer values.
   */
  public void testJsonArrayInteger() throws Exception {
    JsArrayInteger array = JsonUtils.safeEval("[1,2,3]");
    assertEquals(1, array.get(0));
    assertEquals(2, array.get(1));
    assertEquals(3, array.get(2));
  }

  /**
   * Tests {@link RequestBuilder}, a builder for constructing HTTP
   * requests.
   */
  public void testRequestBuilder() throws Exception {
    String url = GWT.getModuleBaseURL() + R.JSON_URL;
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
    builder.sendRequest(null, new RequestCallback() {
      @Override
      public void onResponseReceived(Request request, Response response) {
        assertEquals(200, response.getStatusCode());
        JsonUtils.safeEval(response.getText());
        finishTest();
      }

      @Override
      public void onError(Request request, Throwable exception) {
        throw new IllegalStateException("Failed to process HTTP request", exception);
      }
    });
    delayTestFinish(500);
  }

  private static class Card extends JavaScriptObject {

    protected Card() {
      // Required by GWT
    }

    public final native int getId() /*-{
      return this.id;
    }-*/;

  }

}
