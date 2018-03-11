package io.mincongh.client.interop;

import jsinterop.annotations.JsType;

/**
 * Imported class from native JavaScript.
 *
 * <p>Steps:
 *
 * <pre>
 * ExportedClass.java
 *     |
 *     | export
 *     |
 *     V
 * ExportedClass.js
 *     |
 *     | import
 *     |
 *     V
 * ImportedClass.java
 * </pre>
 *
 * @author Mincong Huang
 */
@JsType(isNative = true, name = "ExportedClass")
@SuppressWarnings("unused")
public class ImportedClass {

  public native String getName();
}
