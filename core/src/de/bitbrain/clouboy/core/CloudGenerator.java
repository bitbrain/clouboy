package de.bitbrain.clouboy.core;

import java.util.List;

import com.badlogic.gdx.graphics.Camera;

public class CloudGenerator {

  private Camera camera;

  private float currentGap = 0;

  private float cloudDistance = 20;

  private GameObjectFactory factory;

  public CloudGenerator(Camera camera, GameObjectFactory factory) {
    this.camera = camera;
    this.factory = factory;
  }

  public void update(float delta) {
    while (currentGap < maxGap()) {
      generateNext();
    }
  }

  private float maxGap() {
    return camera.position.x + camera.viewportWidth * 2f;
  }

  private float getRandomY() {
    return (float) (Math.random() * 200 + 5);
  }

  private float getWidth(List<GameObject> cloud) {
    return 128;
  }

  private void generateNext() {
    float width = getWidth(factory.createCloud(currentGap + cloudDistance, getRandomY(), 6));
    currentGap += width + cloudDistance;
  }
}
