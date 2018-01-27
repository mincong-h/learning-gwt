package io.mincongh.client;

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

  static TextColumn<Stock> newIdColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        return String.valueOf(stock.getId());
      }
    };
  }

  /**
   * Returns the price in format:
   * <pre>
   * %.2f
   * </pre>
   */
  static TextColumn<Stock> newPriceColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        String s = String.valueOf(stock.getPrice() * 1.00);
        return s.substring(0, s.indexOf('.') + 3);
      }
    };
  }

  /**
   * Returns the price-changes in format:
   * <pre>
   * %+.2f (%+.2f%%)
   * </pre>
   * where the 1st double is the delta in price, and the 2nd double
   * is the percentage of the change compared to the base price.
   */
  static TextColumn<Stock> newChangeColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        StringBuilder sb = new StringBuilder();
        if (stock.getDelta() > 0) {
          sb.append('+');
        }
        String s1 = String.valueOf(stock.getDelta() * 1.00);
        sb.append(s1.substring(0, s1.indexOf('.') + 3));

        sb.append(" (");
        if (stock.getChange() > 0) {
          sb.append('+');
        }
        String s2 = String.valueOf(stock.getChange() * 100.00);
        sb.append(s2.substring(0, s2.indexOf('.') + 3));
        sb.append("%)");
        return sb.toString();
      }
    };
  }

  static TextColumn<Stock> newCompanyColumn() {
    return new TextColumn<Stock>() {
      @Override
      public String getValue(Stock stock) {
        return stock.getCompany();
      }
    };
  }

}
