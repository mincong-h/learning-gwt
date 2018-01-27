package io.mincongh.client;

/**
 * @author Mincong Huang
 */
public class Stock {

  private final int id;

  private final String company;

  private final double price;

  private final double delta;

  public Stock(int id) {
    this(id, "Company " + id, Math.random() * 100, -10 + Math.random() * 20);
  }

  private Stock(int id, String company, double price, double delta) {
    this.id = id;
    this.company = company;
    this.price = price;
    this.delta = delta;
  }

  public int getId() {
    return id;
  }

  public String getCompany() {
    return company;
  }

  public double getPrice() {
    return price;
  }

  public double getChange() {
    return delta / price;
  }

  public double getDelta() {
    return delta;
  }

  public static Stock parse(String input) {
    String[] tokens = input.split(",");
    if (tokens.length != 4) {
      String msg = "Incorrect entry, expected 4 fields: id, company, price, variation";
      throw new IllegalArgumentException(msg);
    }
    int id = Integer.parseInt(tokens[0]);
    String company = tokens[1];
    double price = Double.parseDouble(tokens[2]);
    double delta = Double.parseDouble(tokens[3]);
    return new Stock(id, company, price, delta);
  }

  @Override
  public String toString() {
    return "Stock{"
        + "id='" + id + '\''
        + ", company='" + company + '\''
        + ", price=" + price
        + ", delta=" + delta
        + '}';
  }

}
