package de.bitbrain.clouboy.core;

import de.bitbrain.clouboy.core.World.Behavior;

public class CloudBehavior implements Behavior {

  private static final float X_VELOCITY = 20f;

  @Override
  public void update(GameObject object, float delta) {
    object.getVelocity().x = -X_VELOCITY * delta;
  }
}
