package model.shapes;

/**
 * Color is a class that represents a color through standard RGB values.
 */
public class Color {
  private final int MAX_RGB = 255;
  private final int MIN_RGB = 0;
  private int r;
  private int g;
  private int b;

  /**
   * Initialize Color.
   * @param r r
   * @param g g
   * @param b b
   */
  public Color(int r, int g, int b) {
    validateRGB(r, g, b);
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Check if RGB is valid.
   * @param r r value
   * @param g g value
   * @param b b value
   */
  private void validateRGB(int r, int g, int b) {

    for (int rgb : new int[]{r, g, b}) {
      if (rgb < MIN_RGB || rgb > MAX_RGB) {
        throw new IllegalArgumentException("RGB value must be between 0 and 255");
      }
    }
  }

  /**
   * clone color.
   */
  public Color clone() {
    return new Color(r,g,b);
  }

  /**
   * change color.
   * @param c colot
   */
  public void changeColor(Color c) {
    if (c == null) {
      throw new NullPointerException("Color cannot be null");
    }
    this.r = c.getR();
    this.g = c.getG();
    this.b = c.getB();
  }

  /**
   * get R.
   * @return R.
   */
  public int getR() {
    return this.r;
  }

  /**
   * get G.
   * @return G.
   */
  public int getG() {
    return this.g;
  }

  /**
   * get B.
   * @return B.
   */
  public int getB() {
    return this.b;
  }

  @Override
  public String toString() {
    return "(" + r + ", " + g + ", " + b + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Color)) {
      return false;
    }

    Color c = (Color) obj;

    return r == c.getR() && g == c.getG() && b == c.getB();
  }
}