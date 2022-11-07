package Objects;

import java.text.NumberFormat;
import java.util.Locale;

public class Packages {
  private int id;
  private String name;
  private String price;
  private String time;
  private String desc_1;
  private String desc_2;
  private String desc_3;
  private String desc_4;

  public Packages(int id, String name, int price, String time, String desc_1, String desc_2, String desc_3,
      String desc_4) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.desc_1 = desc_1;
    this.desc_2 = desc_2;
    this.desc_3 = desc_3;
    this.desc_4 = desc_4;

    // price with currency format
    Locale locale = new Locale("id", "ID");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    this.price = currencyFormatter.format(price);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getDesc_1() {
    return desc_1;
  }

  public void setDesc_1(String desc_1) {
    this.desc_1 = desc_1;
  }

  public String getDesc_2() {
    return desc_2;
  }

  public void setDesc_2(String desc_2) {
    this.desc_2 = desc_2;
  }

  public String getDesc_3() {
    return desc_3;
  }

  public void setDesc_3(String desc_3) {
    this.desc_3 = desc_3;
  }

  public String getDesc_4() {
    return desc_4;
  }

  public void setDesc_4(String desc_4) {
    this.desc_4 = desc_4;
  }
}
