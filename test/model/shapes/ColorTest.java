package model.shapes;
import model.shapes.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ColorTest is a class that tests the Color class.
 */
public class ColorTest {

  /**
   * Test getters. This tests that the constructor creates a Color object with the correct RGB values.
   */
  @Test
  public void testGetters() {
    Color c = new Color(255, 128, 0);
    assertEquals(255, c.getR());
    assertEquals(128, c.getG());
    assertEquals(0, c.getB());
  }

  /**
   * Test constructor with invalid low R value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidR() {
    new Color(-1, 128, 23);
  }

  /**
   * Test constructor with invalid high R value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidR2() {
    new Color(256, 128, 23);
  }

  /**
   * Test constructor with invalid low G value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidG() {
    new Color(255, -1, 23);
  }

  /**
   * Test constructor with invalid high G value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidG2() {
    new Color(255, 256, 23);
  }

  /**
   * Test constructor with invalid low B value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidRGB() {
    new Color(255, 128, -1);
  }

  /**
   * Test constructor with invalid high B value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidB2() {
    new Color(255, 128, 256);
  }

  /**
   * Test clone. This tests that the clone method returns a new Color object with the same RGB values.
   */
  @Test
  public void testClone() {
    Color c1 = new Color(255, 128, 0);
    Color c2 = c1.clone();
    assertEquals(c1, c2);
    assertNotSame(c1, c2);
  }

  /**
   * Test changeColor. This tests that the changeColor method changes the color to the correct color.
   */
  @Test
  public void testChangeColor() {
    Color c1 = new Color(255, 128, 0);
    Color c2 = new Color(0, 255, 128);
    c1.changeColor(c2);
    assertEquals(c1, c2);
    assertNotSame(c1, c2);
  }

  /**
   * Test changeColor with null color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorWithNull() {
    Color c1 = new Color(255, 128, 0);
    c1.changeColor(null);
  }

  /**
   * Test toString. This tests that the toString method returns the correct string.
   */
  @Test
  public void testToString() {
    Color c = new Color(255, 128, 0);
    assertEquals("(255, 128, 0)", c.toString());
  }

  /**
   * Test equals. One tests that two colors with the same RGB values are equal, and the other tests that two colors with
   * different RGB values are not equal.
   */
  @Test
  public void testEquals() {
    Color c1 = new Color(255, 128, 0);
    Color c2 = new Color(255, 128, 0);
    Color c3 = new Color(128, 255, 0);
    assertEquals(c1, c2);
    assertNotEquals(c1, c3);
  }
}
