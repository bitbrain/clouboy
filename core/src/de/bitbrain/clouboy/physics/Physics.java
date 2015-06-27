package de.bitbrain.clouboy.physics;

import de.bitbrain.clouboy.core.GameObject;

public class Physics {

  private static final float FAKE_GRAVITY = 300;

  private static final float SLIDING = 0.8f;

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
    object.getVelocity().scl(SLIDING);
    object.getAccelleration().scl(SLIDING);
  }

  private void applyGravity(GameObject object, float delta) {
    if (object.getTop() > 0 && !object.isStatic()) {
      object.getVelocity().y -= FAKE_GRAVITY * delta;
    }
  }

  private void handleCollision(GameObject source, GameObject target) {
    if (target != null) {
      if ((source.getRight() > target.getLeft() || source.getLeft() < target.getRight())
          && target.getBottom() < source.getBottom()) {
        source.setPosition(source.getLeft(), target.getTop() + source.getHeight() + 1);
        source.getVelocity().y = 0;
        source.getAccelleration().y = 0;
      }
    }
  }
}
