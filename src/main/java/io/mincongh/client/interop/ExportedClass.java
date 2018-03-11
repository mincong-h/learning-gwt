package io.mincongh.client.interop;

import jsinterop.annotations.JsType;

/** @author Mincong Huang */
@JsType
@SuppressWarnings("unused")
public class ExportedClass {

  private String name;

  public ExportedClass() {
    this.name = "Good morning";
  }

  public String getName() {
    return name;
  }
}
