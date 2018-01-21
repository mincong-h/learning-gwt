package io.mincongh.client;

/**
 * @author Mincong Huang
 */
public class Stock {

  private final int id;

  private final String company;

  private double price;

  private double variation;

  public Stock(int i) {
    this.id = i;
    this.company = "Company " + i;
    this.price = 1.0 * i;
    this.variation = 0.1 * i;
  }

  private Stock(int id, String company, double price, double variation) {
    this.id = id;
    this.company = company;
    this.price = price;
    this.variation = variation;
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

  public double getVariation() {
    return variation;
  }

  public static Stock parse(String s) {
    String[] tokens = s.split(",");
    if (tokens.length != 4) {
      String msg = "Incorrect entry, expected 4 fields: id, company, price, variation";
      throw new IllegalArgumentException(msg);
    }
    int id = Integer.parseInt(tokens[0]);
    String company = tokens[1];
    double price = Double.parseDouble(tokens[2]);
    double variation = Double.parseDouble(tokens[3]);
    return new Stock(id, company, price, variation);
  }

  @Override
  public String toString() {
    return "Stock{" +
        "id='" + id + '\'' +
        ", company='" + company + '\'' +
        ", price=" + price +
        ", variation=" + variation +
        '}';
  }

}
