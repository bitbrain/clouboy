package de.bitbrain.clouboy.core;

import java.util.ArrayList;
import java.util.List;

public class GameObjectFactory {

  private World world;

  public GameObjectFactory(World world) {
    this.world = world;
  }

  public GameObject createPlayer(float x, float y) {
    GameObject player = world.addObject();
    world.applyBehavior(player, new PlayerBehavior());
    player.setType(GameObjectType.BOY);
    player.setPosition(x, y);
    player.setDimensions(64, 64);
    return player;
  }

  public List<GameObject> createCloud(float x, float y, int elements) {
    List<GameObject> clouds = new ArrayList<GameObject>();
    for (int i = 0; i < elements; ++i) {
      double angle = Math.toRadians(Math.random() * 160.0);
      double length = Math.random() * 70.0;
      float localX = (float) (x + Math.cos(angle) * length);
      float localY = (float) (y + Math.sin(angle) * length);
      GameObject cloud = world.addObject();
      world.applyBehavior(cloud, new CloudBehavior());
      cloud.setType(GameObjectType.CLOUD);
      cloud.setPosition(localX, localY);
      cloud.setStatic(true);
      cloud.setDimensions(64, 64);
      clouds.add(cloud);
    }
    return clouds;
  }
}
