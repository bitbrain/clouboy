package de.bitbrain.clouboy.physics;

import de.bitbrain.clouboy.core.GameObject;

public class Physics {

  private static final float FAKE_GRAVITY = 120.2f;

  private static final float SLIDING = 0.93f;

  private CollisionDetector collisionDetector;

  public Physics(Iterable<GameObject> objects) {
    collisionDetector = new CollisionDetector(objects);
  }

  public void apply(GameObject object, float delta) {
    object.getVelocity().add(object.getAccelleration());
    applyGravity(object, delta);
    GameObject collision = collisionDetector.getCollision(object);
    if (!object.isStatic()) {
      handleCollision(object, collision);
    }
    object.move(object.getVelocity().x, object.getVelocity().y);
    if (!object.isStatic()) {
      collision = collisionDetector.getCollision(object);
      handleCollision(object, collision);
      object.getVelocity().scl(SLIDING);
      object.getAccelleration().scl(SLIDING);
    }
  }

  private void applyGravity(GameObject object, float delta) {
    if (!object.isStatic()) {
      object.getVelocity().y -= FAKE_GRAVITY * delta;
    }
  }

  private void handleCollision(GameObject source, GameObject target) {
    if (target != null && target.getCollisionResolver() != null) {
      target.getCollisionResolver().resolve(source, target);
      target.setLastCollision(source);
    }
    source.setLastCollision(target);
  }
}
