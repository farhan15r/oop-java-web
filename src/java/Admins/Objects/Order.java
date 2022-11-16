package Admins.Objects;

import java.text.NumberFormat;
import java.util.Locale;

public class Order {
  private int id;
  private String date;
  private String price;
  private String name;
  private String username;
  private String statusOrder;
  private String statusPayment;
  private int packageId;

  public void setId(int id) {
    this.id = id;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setPrice(int price) {
    Locale locale = new Locale("id", "ID");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    this.price = currencyFormatter.format(price);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setStatusOrder(String status) {
    this.statusOrder = status;
  }

  public void setPackageId(int packageId) {
    this.packageId = packageId;
  }

  public void setStatusPayment(String status) {
    this.statusPayment = status;
  }

  public int getId() {
    return id;
  }

  public String getDate() {
    return date;
  }

  public String getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getStatusOrder() {
    return statusOrder;
  }

  public int getPackageId() {
    return packageId;
  }

  public String getStatusPayment() {
    return statusPayment;
  }
}
