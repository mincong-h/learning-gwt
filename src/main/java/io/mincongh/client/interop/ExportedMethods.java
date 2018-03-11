package io.mincongh.client.interop;

import jsinterop.annotations.JsMethod;

/**
 * Exports Java methods to JavaScript.
 *
 * @author Mincong Huang
 */
@SuppressWarnings("unused")
public class ExportedMethods {

  @JsMethod
  public static String sayHello(String name) {
    return "Hello, " + name;
  }
}
