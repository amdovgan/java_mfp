package ru.mfp.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTests {

  @Test
  public void testArea() {
    Rectangle re = new Rectangle(4,5);
    Assert.assertEquals(re.area(), 20.0);
  }
}
