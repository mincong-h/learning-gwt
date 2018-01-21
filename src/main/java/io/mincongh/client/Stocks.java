package io.mincongh.client;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for class {@link Stock}.
 *
 * @author Mincong Huang
 */
final class Stocks {

  private Stocks() {
    // Utility class, do not instantiate.
  }

  static List<Stock> newRows(int count) {
    return IntStream.rangeClosed(1, count)
        .boxed()
        // Cannot be simplified to method reference
        // https://gwt-review.googlesource.com/c/gwt/+/14572
        .map(i -> new Stock(i))
        .collect(Collectors.toList());
  }

  private static TextColumn<Stock> newIdColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        return String.valueOf(stock.getId());
      }
    };
  }

  private static TextColumn<Stock> newPriceColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        return String.valueOf(stock.getPrice());
      }
    };
  }

  private static TextColumn<Stock> newVariationColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        String s = String.valueOf(stock.getVariation() * 1.00);
        int point = s.indexOf('.');
        return s.substring(0, point + 2);
      }
    };
  }

  private static TextColumn<Stock> newCompanyColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        return stock.getCompany();
      }
    };
  }

  static CellTable<Stock> newCellTable() {
    CellTable<Stock> table = new CellTable<>();
    table.addColumn(Stocks.newIdColumn(), "ID");
    table.addColumn(Stocks.newCompanyColumn(), "Company");
    table.addColumn(Stocks.newPriceColumn(), "Price");
    table.addColumn(Stocks.newVariationColumn(), "Variation");
    return table;
  }

}
