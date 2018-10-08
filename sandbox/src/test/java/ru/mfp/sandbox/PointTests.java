package ru.mfp.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance (){
    Point p1 = new Point(3,4);
    Point p2 = new Point(8,7);

    Assert.assertEquals((p1.distance(p2.x,p2.y)), 5.830951894845301);
  }

  @Test
  public void testDistance1 (){
    Point p1 = new Point(3,4);
    Point p2 = new Point(8,7);

    Assert.assertEquals((p1.distance(p2)), 5.830951894845301);
  }

  @Test
  public void testDistance2 (){
    Point p1 = new Point(3,3);
    Point p2 = new Point(3,3);

    Assert.assertEquals((p1.distance(p2)), 0.0);
  }

  @Test
  public void testDistance3 (){
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);

    Assert.assertEquals((p1.distance(p2.x,p2.y)), 0.0);
  }

  @Test
  public void testDistance4 (){
    Point p1 = new Point(3,4);
    Point p2 = new Point(0,0);

    Assert.assertEquals((p1.distance(p2)), 5.0);
  }

  @Test
  public void testDistance5 (){
    Point p1 = new Point(3,4);
    Point p2 = new Point(0,0);

    Assert.assertEquals((p1.distance(p2.x,p2.y)), 5.0);
  }

  @Test
  public void testDistance6 (){
    Point p1 = new Point(3,4);
    Point p2 = new Point(0,0);

    Assert.assertEquals((p1.distance(p2)), 5.0);
  }

  @Test
  public void testDistance7 () {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(0, 0);

    Assert.assertEquals((p1.distance(p2.x, p2.y)), 5.0);
  }
}
