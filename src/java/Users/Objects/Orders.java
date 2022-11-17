package Users.Objects;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Orders {
  private int id;
  private String packageName;
  private String statusOrder;
  private String date;
  private String price;
  private String photographer;

  // set data

  public void setId(int id) {
    this.id = id;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public void setStatusOrder(String status_order) {
    this.statusOrder = status_order;
  }

  public void setDate(String date) {
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
  }

  public void setPrice(int price) {
    // int to string IDR 1.000.000
    Locale locale = new Locale("id", "ID");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    this.price = currencyFormatter.format(price);
  }

  public void setPhotographer(String photographer) {
    this.photographer = photographer;
  }

  // get data
  public int getId() {
    return id;
  }

  public String getDate() {
    return date;
  }

  public String getStatusOrder() {
    return statusOrder;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getPrice() {
    return price;
  }

  public String getPhotographer() {
    return photographer;
  }
}
