package Users.Objects;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Orders {
  private int id;
  private String packageName;
  private String status_order;
  private String date;
  private String price;

  public Orders(int id, String packageName, String status_order, String date, int price) {
    this.id = id;
    this.packageName = packageName;
    this.status_order = status_order;

    // string YYYY-MM-DD to date
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date_ = null;
    try {
      date_ = formatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    // date to string EEEE dd MMMM yyyy locale english
    SimpleDateFormat formatter2 = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("en"));
    this.date = formatter2.format(date_);

    // int to string IDR 1.000.000
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

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getStatus_order() {
    return status_order;
  }

  public void setStatus_order(String status_order) {
    this.status_order = status_order;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
