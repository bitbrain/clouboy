package de.bitbrain.clouboy.core;

import java.util.List;

import com.badlogic.gdx.graphics.Camera;

import de.bitbrain.clouboy.core.World.WorldListener;

public class CloudGenerator implements WorldListener {

  private Camera camera;

  private float gapCurrent, gapMax;

  private float cloudDistance = 20;

  private GameObjectFactory factory;

  private float front;

  public CloudGenerator(Camera camera, GameObjectFactory factory) {
    this.camera = camera;
    this.factory = factory;
    gapCurrent = 250f;
    front = gapCurrent;
    updateGapMax();
    generate();
    gapCurrent = camera.position.x + camera.viewportWidth;
  }

  public void update(float delta) {
    gapCurrent = camera.position.x + camera.viewportWidth;
    if (reached()) {
      updateGapMax();
      generate();
    }
    front = gapCurrent;
  }

  private boolean reached() {
    return gapCurrent >= gapMax || gapCurrent - front > 0;
  }

  private void updateGapMax() {
    gapMax = camera.position.x + camera.viewportWidth * 2;
  }

  private float getRandomY() {
    return (float) (Math.random() * 300 + 50);
  }

  private float getWidth(List<GameObject> cloud) {
    return 128;
  }

  private void generate() {
    float x = gapCurrent;
    while (x < gapMax) {
      x += cloudDistance;
      if (x < gapMax) {
        x += getWidth(factory.createCloud(x, getRandomY(), 6));
      }
    }
    cloudDistance += 50;
  }

  @Override
  public void onGameObjectUpdate(GameObject object) {
    if (object.isType(GameObjectType.CLOUD)) {
      if (object.getRight() > front) {
        front = object.getRight();
      }
    }
  }
}
