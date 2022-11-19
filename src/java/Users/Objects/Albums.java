package Users.Objects;

public class Albums {
  private String date;
  private String id;

  public Albums(String date, String id) {
    this.date = date;
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
