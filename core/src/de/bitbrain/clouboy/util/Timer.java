package de.bitbrain.clouboy.util;

public class Timer {

  private float time;

  public float getTicks() {
    return time;
  }

  public void update(float delta) {
    time += delta;
  }

  public void reset() {
    time = 0;
  }

  public boolean reached(float millis) {
    return time >= millis;
  }
}