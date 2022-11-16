package Admins.Objects;

import java.text.NumberFormat;
import java.util.Locale;

public class Packages {
  private int id;
  private String name;
  private String price;

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    Locale locale = new Locale("id", "ID");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    this.price = currencyFormatter.format(price);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPrice() {
    return price;
  }
}
