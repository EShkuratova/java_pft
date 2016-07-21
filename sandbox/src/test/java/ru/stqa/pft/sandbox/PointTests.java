package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by eshkuratova on 21.07.2016.
 */
public class PointTests {
  @Test
  public void testDistance1(){

    Point point = new Point(4,-3);
    point.setX(1);
    point.setY(9);
    Assert.assertEquals(point.getDistance(),5.0,"Расстояние вычислено неверно");

  }

  @Test
  public void testDistance2(){
    Point point = new Point(0,0);
    point.setX(1);
    point.setY(1);
    Assert.assertEquals(point.getDistance(),0.0,0.0,"Расстояние вычислено неверно");
  }

  @Test
  public void testDistance3(){
    Point point = new Point(36,77);
    point.setX(0);
    point.setY(0);
    Assert.assertEquals(point.getDistance(),85.0,"Расстояние вычислено неверно");

  }
}
