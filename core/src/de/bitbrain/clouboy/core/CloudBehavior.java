package de.bitbrain.clouboy.core;

import de.bitbrain.clouboy.core.World.Behavior;

public class CloudBehavior implements Behavior {

  private float customVelocity = 0;

  private float velocity;

  public CloudBehavior(float velocity) {
    customVelocity = (float) (Math.random() * 300f);
    this.velocity = velocity;
  }

  @Override
  public void update(GameObject object, float delta) {
    object.getVelocity().x = -(velocity + customVelocity) * delta;
  }
}
