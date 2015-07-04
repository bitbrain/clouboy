package de.bitbrain.clouboy.physics;

import de.bitbrain.clouboy.core.GameObject;

public final class Collisions {

  public static final CollisionResolver NONE = new CollisionResolver() {
    @Override
    public void resolve(GameObject source, GameObject target) {
    }
  };
  public static final CollisionResolver CLOUD = new CloudCollision();
}
