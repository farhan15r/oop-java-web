package Users.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Orders {
  private int id;
  private String packageName;
  private String status_order;
  private String date;

  public Orders(int id, String packageName, String status_order, String date) {
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
}
