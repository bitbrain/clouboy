package de.bitbrain.clouboy.physics;

import de.bitbrain.clouboy.core.GameObject;

public class CollisionDetector {

  private Iterable<GameObject> objects;

  public CollisionDetector(Iterable<GameObject> objects) {
    this.objects = objects;
  }

  public GameObject getCollision(GameObject object) {
    for (GameObject other : objects) {
      if (!other.equals(object) && checkCollision(other, object)) {
        return other;
      }
    }
    return null;
  }

  private boolean checkCollision(GameObject o1, GameObject o2) {
    boolean xCollision = Math.abs(o1.getLeft() - o2.getLeft()) * 2 < (o1.getWidth() + o2.getWidth());
    boolean yCollision = Math.abs(o1.getTop() - o2.getTop()) * 2 < (o1.getHeight() + o2.getHeight());
    return xCollision && yCollision;
  }
}