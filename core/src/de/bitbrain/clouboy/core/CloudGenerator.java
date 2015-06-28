package de.bitbrain.clouboy.core;

import java.util.List;

import com.badlogic.gdx.graphics.Camera;

public class CloudGenerator {

  private Camera camera;

  private float currentGap = 100;

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
    return (float) (Math.random() * 200 + 5);
  }

  private void generateNext() {
    List<GameObject> clouds = factory.createCloud(currentGap, getRandomY(), 6);
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
