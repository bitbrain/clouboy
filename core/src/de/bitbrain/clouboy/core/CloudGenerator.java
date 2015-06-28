package de.bitbrain.clouboy.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.graphics.Camera;

import de.bitbrain.clouboy.core.PlayerBehavior.PlayerListener;

public class CloudGenerator implements PlayerListener {

  private Camera camera;

  private float currentGap = 0;

  private float cloudDistance = 150;

  private GameObjectFactory factory;

  private GameObject recentCloud;

  private HashMap<String, Collection<GameObject>> data = new HashMap<String, Collection<GameObject>>();

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
    return (float) (Math.random() * 300);
  }

  private void generateNext() {
    int size = (int) (6 + Math.random() * 12);
    List<GameObject> clouds = factory.createCloud(currentGap + cloudDistance, getRandomY(), size);
    float maxX = clouds.get(0).getRight();
    for (GameObject cloud : clouds) {
      if (cloud.getRight() > maxX) {
        maxX = cloud.getRight();
        recentCloud = cloud;
        currentGap = recentCloud.getRight();
      }
    }
    data.put(clouds.get(0).getId(), clouds);
  }

  @Override
  public void onJump(GameObject player) {
    if (player.getLastCollision() != null && player.getLastCollision().getType() == GameObjectType.CLOUD) {
      GameObject cloud = player.getLastCollision();

      Collection<GameObject> clouds = data.get(cloud.getId());
      for (GameObject object : clouds) {
        factory.getWorld().remove(object);
      }
      data.remove(cloud.getId());
    }
  }
}
