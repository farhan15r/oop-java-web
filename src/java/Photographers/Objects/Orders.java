package Photographers.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Orders {
  private int id;
  private String date;
  private String packageName;
  private String statusPayment;
  private String username;

  public void setId(int id) {
    this.id = id;
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

  public void setStatusPayment(String statusPayment) {
    this.statusPayment = statusPayment;
  }

  public void setPackageName(String name) {
    this.packageName = name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getId() {
    return id;
  }

  public String getDate() {
    return date;
  }

  public String getStatusPayment() {
    return statusPayment;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getUsername() {
    return username;
  }
}
