package io.mincongh.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Mincong Huang
 */
public class Hello extends Composite {

  interface HelloUiBinder extends UiBinder<Widget, Hello> {

  }

  private static HelloUiBinder ourUiBinder = GWT.create(HelloUiBinder.class);

  public Hello() {
    super.initWidget(ourUiBinder.createAndBindUi(this));
  }

}
