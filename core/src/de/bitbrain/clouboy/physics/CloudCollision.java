package de.bitbrain.clouboy.physics;

import de.bitbrain.clouboy.core.GameObject;

public final class CloudCollision implements CollisionResolver {

  public static final CloudCollision INSTANCE = new CloudCollision();

  private CloudCollision() {
  }

  @Override
  public void resolve(GameObject source, GameObject target) {
    if (source.getVelocity().y < 0 && source.getRight() > target.getLeft() && source.getLeft() < target.getRight()
        && source.getBottom() > target.getBottom()) {
      source.setPosition(source.getLeft() + target.getVelocity().x, target.getBottom());
      source.getVelocity().y = 0;
      source.getAccelleration().y = 0;
    }
  }
}
