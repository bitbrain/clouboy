package de.bitbrain.clouboy.core;

import java.util.List;

import com.badlogic.gdx.graphics.Camera;

public class CloudGenerator {

  private Camera camera;

  private float currentGap = 100;

  private float cloudDistance = 250;

  private GameObjectFactory factory;

  private GameObject recentCloud;

  public CloudGenerator(Camera camera, GameObjectFactory factory) {
    this.camera = camera;
    this.factory = factory;
  }

  public void update(float delta) {
    currentGap = recentCloud != null ? recentCloud.getRight() : 0;
    while (currentGap < maxGap()) {
      generateNext();
    }
  }

  private float maxGap() {
    return camera.position.x + camera.viewportWidth * 2f;
  }

  private float getRandomY() {
    return (float) (Math.random() * 100);
  }

  private void generateNext() {
    int size = (int) (6 + Math.random() * 6);
    List<GameObject> clouds = factory.createCloud(currentGap + cloudDistance, getRandomY(), size);
    float maxX = clouds.get(0).getRight();
    for (GameObject cloud : clouds) {
      if (cloud.getRight() > maxX) {
        maxX = cloud.getRight();
        recentCloud = cloud;
        currentGap = recentCloud.getRight();
      }
    }
  }
}
