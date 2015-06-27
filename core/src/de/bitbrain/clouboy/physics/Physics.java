package de.bitbrain.clouboy.physics;

import de.bitbrain.clouboy.core.GameObject;

public class Physics {

  private static final float FAKE_GRAVITY = 30;

  private static final float SLIDING = 0.8f;

  private CollisionDetector collisionDetector;

  public Physics(Iterable<GameObject> objects) {
    collisionDetector = new CollisionDetector(objects);
  }

  public void apply(GameObject object, float delta) {
    object.getVelocity().add(object.getAccelleration());

    object.getVelocity().scl(SLIDING);
    object.getAccelleration().scl(SLIDING);
    GameObject collision = collisionDetector.getCollision(object);
    if (!object.hasCollisionEnabled() || collision == null) {
      object.move(object.getVelocity().x, object.getVelocity().y);
      if (object.getBottom() > object.getHeight()) {
        object.move(0, -FAKE_GRAVITY);
      }
    } else {
      object.setPosition(object.getLeft(), collision.getTop());
    }

  }
}
