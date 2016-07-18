package ru.stqa.pft.sandbox;

/**
 * Created by eshkuratova on 18.07.2016.
 */
public class Point {

  private double x;
  private double y;
  private double a;
  private double b;

  public Point(double a,double b){

    this.a=a;
    this.b=b;

  }

    public double getDistance(){
    return distance();
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }



  private double distance(){
    double x2=x+a;
    double y2=y+b;
    double distance =Math.sqrt(Math.pow((x-x2),2)+Math.pow((y-y2),2));
    return distance;
  }
}
