package de.bitbrain.clouboy.core;

public class Physics {

  private static final float FAKE_GRAVITY = 30;

  private static final float SLIDING = 0.8f;

  public void apply(GameObject object, float delta) {
    object.getVelocity().add(object.getAccelleration());
    object.move(object.getVelocity().x, object.getVelocity().y);

    object.getVelocity().scl(SLIDING);
    object.getAccelleration().scl(SLIDING);
    if (object.getBottom() > object.getHeight()) {
      object.move(0, -FAKE_GRAVITY);
    }
  }
}
