package io.mincongh.client;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;
import io.mincongh.shared.FieldVerifier;
import io.mincongh.shared.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockMarket implements EntryPoint {

  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  private static final String JSON_URL = "http://127.0.0.1:8888/" + R.JSON_URL;

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final StockServiceAsync stockService = GWT.create(StockService.class);

  private final Messages messages = GWT.create(Messages.class);

  private final ListDataProvider<Stock> dataProvider = new ListDataProvider<>();

  private final ListDataProvider<StockData> randomDataProvider = new ListDataProvider<>();

  private final CellTable<Stock> stockTable = new CellTable<>();

  private final CellTable<StockData> randomStockTable = new CellTable<>();

  private final Hello hello = new Hello();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    initStockTable();
    initRandomStockTable();
    final Button addButton = new Button(messages.addButton());
    final TextBox nameField = new TextBox();
    nameField.setText("11, Google, 1.0, 2");
    final Label errorLabel = new Label();
    final TextBox randomDataInput = new TextBox();
    randomDataInput.setText(JSON_URL);
    final Button randomDataButton = new Button(messages.randomData());

    // We can add style names to widgets
    addButton.addStyleName("sendButton");

    // Add widgets to the RootPanel
    RootPanel.get("stockContainer").add(stockTable);
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(addButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("helloInput").add(hello);
    RootPanel.get("randomDataUrl").add(randomDataInput);
    RootPanel.get("randomDataButton").add(randomDataButton);
    RootPanel.get("randomDataResponse").add(randomStockTable);

    randomDataButton.addClickHandler(event -> {
      String url = randomDataInput.getText();
      RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
      try {
        builder.sendRequest(null, new RequestCallback() {
          @Override
          public void onResponseReceived(Request request, Response response) {
            if (200 == response.getStatusCode()) {
              JsArray<StockData> array = JsonUtils.safeEval(response.getText());
              List<StockData> list = new ArrayList<>();
              for (int i = 0; i < array.length(); i++) {
                list.add(array.get(i));
              }
              randomDataProvider.setList(list);
              randomDataProvider.refresh();
              randomStockTable.redraw();
            } else {
              Window.alert("Could not retrieve JSON (" + response.getStatusCode() + ")");
            }
          }

          @Override
          public void onError(Request request, Throwable exception) {
            Window.alert("Could not retrieve JSON");
          }
        });
      } catch (RequestException e) {
        Window.alert("Could not retrieve JSON");
      }
    });

    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();

    // We can set the id of a widget by accessing its Element
    final Label requestLabel = new Label("None");
    final Label responseLabel = new Label("None");
    RootPanel.get("request").add(requestLabel);
    RootPanel.get("response").add(responseLabel);

    // Create a handler for the addButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {

      /**
       * Fired when the user clicks on the addButton.
       */
      @Override
      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      @Override
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      private void updateDate() {
        RootPanel.get("lastUpdate").clear();
        RootPanel.get("lastUpdate").add(new Label(getDateStr()));
      }

      private native String getDateStr() /*-{
        var date = new Date();
        return date.toLocaleString("en-US");
      }-*/;

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = nameField.getText();
        if (!FieldVerifier.isValidName(textToServer)) {
          errorLabel.setText("Please enter at least four characters");
          return;
        }

        // Then, we send the input to the server.
        requestLabel.setText(textToServer);
        responseLabel.setText("");
        stockService.addStock(textToServer, new AsyncCallback<String>() {
          @Override
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            responseLabel.addStyleName("serverResponseLabelError");
            responseLabel.setText(SERVER_ERROR);
          }

          @Override
          public void onSuccess(String result) {
            responseLabel.removeStyleName("serverResponseLabelError");
            responseLabel.setText(result);
            dataProvider.getList().add(Stock.parse(result));
            dataProvider.refresh();
            stockTable.redraw();
            updateDate();
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    addButton.addClickHandler(handler);
    nameField.addKeyUpHandler(handler);
  }

  private void initStockTable() {
    Column<Stock, String> deleteColumn = new Column<Stock, String>(new ButtonCell()) {
      @Override
      public String getValue(Stock stock) {
        return "x";
      }
    };
    deleteColumn.setFieldUpdater((index, stock, value) -> {
      dataProvider.getList().remove(stock);
      dataProvider.refresh();
      stockTable.redraw();
    });

    stockTable.addColumn(Stocks.newIdColumn(), "ID");
    stockTable.addColumn(Stocks.newCompanyColumn(), "Company");
    stockTable.addColumn(Stocks.newPriceColumn(), "Price");
    stockTable.addColumn(Stocks.newChangeColumn(), "Change");
    stockTable.addColumn(deleteColumn, "Remove");

    dataProvider.addDataDisplay(stockTable);
    dataProvider.setList(Stocks.newRows(10));
  }

  private void initRandomStockTable() {
    Column<StockData, String> deleteColumn = new Column<StockData, String>(new ButtonCell()) {
      @Override
      public String getValue(StockData stock) {
        return "x";
      }
    };
    deleteColumn.setFieldUpdater((index, stock, value) -> {
      randomDataProvider.getList().remove(stock);
      randomDataProvider.refresh();
      randomStockTable.redraw();
    });

    randomStockTable.addColumn(new TextColumn<StockData>() {
      @Override
      public String getValue(StockData stock) {
        return String.valueOf(stock.getSymbol());
      }
    }, "Symbol");

    randomStockTable.addColumn(new TextColumn<StockData>() {
      @Override
      public String getValue(StockData stock) {
        return String.valueOf(stock.getPrice());
      }
    }, "Price");
    randomStockTable.addColumn(new TextColumn<StockData>() {
      @Override
      public String getValue(StockData stock) {
        return String.valueOf(stock.getChange());
      }
    }, "Change");

    randomDataProvider.addDataDisplay(randomStockTable);
    randomDataProvider.setList(new ArrayList<>());
  }

}
