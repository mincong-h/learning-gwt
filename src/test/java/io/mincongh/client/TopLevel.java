package io.mincongh.client;

/**
 * @author Mincong Huang
 */
public class TopLevel {

  private final int myInt;

  TopLevel() {
    myInt = 1;
  }

  TopLevel(int myInt) {
    this.myInt = myInt;
  }

  static class StaticInner {

    private float myFloat;

    StaticInner() {
      myFloat = 1;
    }

    StaticInner(float myFloat) {
      this.myFloat = myFloat;
    }

    float getFloat() {
      return myFloat;
    }
  }

  class InstanceInner {

    private char myChar;

    InstanceInner() {
      myChar = 'a';
    }

    InstanceInner(char myChar) {
      this.myChar = myChar;
    }

    char getChar() {
      return myChar;
    }

  }

  int getInt() {
    return myInt;
  }

}
