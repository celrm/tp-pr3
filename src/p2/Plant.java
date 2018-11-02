package p2;

public abstract class Plant {
  private String name;

  public Plant(String name) {
    this.name = name;
  }

  public String getName(){
    return this.name;
  }
}
