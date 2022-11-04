package Home;

public class Packages {
  private String name;
  private int price;
  private String time;
  private String desc_1;
  private String desc_2;
  private String desc_3;

  public Packages(String name, int price, String time, String desc_1, String desc_2, String desc_3) {
    this.name = name;
    this.price = price;
    this.time = time;
    this.desc_1 = desc_1;
    this.desc_2 = desc_2;
    this.desc_3 = desc_3;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
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
}
