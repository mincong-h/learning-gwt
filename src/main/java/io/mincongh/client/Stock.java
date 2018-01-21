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
