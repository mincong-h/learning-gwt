package io.mincongh.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import io.mincongh.shared.R;

/**
 * Tests internationalization using French as default language.
 *
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

  public void testParameterizedValue() throws Exception {
    Messages messages = GWT.create(Messages.class);
    assertEquals("Nom: Foo", messages.nameLabel("Foo"));
  }

  public void testParameterizedValues() throws Exception {
    Messages messages = GWT.create(Messages.class);
    assertEquals("Il n'y a pas d'élément.", messages.itemLabel(0));
    assertEquals("Il y a 1 élément.", messages.itemLabel(1));
    assertEquals("Il y a 2 éléments.", messages.itemLabel(2));
  }

}
