package ru.mfp.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SquareTests {

  @Test
  public void testArea() {
    Square si = new Square(5);
    Assert.assertEquals( si.area(),25.0);
  }
}
