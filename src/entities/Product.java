package entities;

import java.text.Format;

public class Product {

  private String name;
  private Double preço;

  public Product(String name, Double preço) {
    this.name = name;
    this.preço = preço;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPreço() {
    return preço;
  }

  public void setPreço(Double preço) {
    this.preço = preço;
  }

  @Override
  public String toString() {
    return name = ", " + String.format("%.2f", preço);
  }
}
