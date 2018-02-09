package io.mincongh.client;

import com.google.gwt.i18n.client.LocalizableResource.Generate;

// IntelliJ: IntelliJ GWT plugin should inspect the properties file
@Generate(format = "com.google.gwt.i18n.server.PropertyCatalogFactory")
public interface Messages extends com.google.gwt.i18n.client.Messages {

  @DefaultMessage("Enter your name")
  String nameField();

  @DefaultMessage("Add")
  String addButton();

  @DefaultMessage("Random Data")
  String randomData();

}
