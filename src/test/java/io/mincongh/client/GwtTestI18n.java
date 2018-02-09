package io.mincongh.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import io.mincongh.shared.R;

/**
 * @author Mincong Huang
 */
public class GwtTestI18n extends GWTTestCase {

  @Override
  public String getModuleName() {
    return R.TEST_I18N_MODULE;
  }

  public void testPropertiesFileDiscovery() throws Exception {
    Messages messages = GWT.create(Messages.class);
    assertEquals("Ajouter", messages.addButton());
  }

}
