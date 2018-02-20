package io.mincongh.client;

import com.google.gwt.user.client.ui.HasText;

/**
 * @author Mincong Huang
 */
public class FakeTextContainer implements HasText {

  private String text;

  public FakeTextContainer(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
