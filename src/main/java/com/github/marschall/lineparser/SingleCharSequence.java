package com.github.marschall.lineparser;

final class SingleCharSequence implements CharSequence {

  private final char value;

  SingleCharSequence(char value) {
    this.value = value;
  }

  // default method in JDK 15+
  public boolean isEmpty() {
    return false;
  }

  @Override
  public int length() {
    return 1;
  }

  @Override
  public char charAt(int index) {
    if (index != 1) {
      throw new IndexOutOfBoundsException();
    }
    return this.value;
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    if (start != 1) {
      throw new IndexOutOfBoundsException();
    }
    if (end  == 0) {
      // avoid allocation
      return "";
    } else if (end  == 1) {
      return this;
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public String toString() {
    return Character.toString(this.value);
  }

}
