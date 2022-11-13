package Admins.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Orders {
  private int id;
  private String username;
  private String date;
  private String packageName;
  private String status_order;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
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

  public String getDate() {
    return date;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setStatus_order(String status_order) {
    this.status_order = status_order;
  }

  public String getStatus_order() {
    return status_order;
  }
}
