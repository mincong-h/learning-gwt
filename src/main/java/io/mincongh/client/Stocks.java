package io.mincongh.client;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for class {@link Stock}.
 *
 * @author Mincong Huang
 */
public final class Stocks {

  private Stocks() {
    // Utility class, do not instantiate.
  }

  public static List<Stock> newRows(int count) {
    return IntStream.rangeClosed(1, count)
        .boxed()
        // Cannot be simplified to method reference
        // https://gwt-review.googlesource.com/c/gwt/+/14572
        .map(i -> new Stock(i))
        .collect(Collectors.toList());
  }

}
