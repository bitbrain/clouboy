package de.bitbrain.clouboy.core;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aurelienribon.tweenengine.TweenManager;
import de.bitbrain.clouboy.core.PlayerBehavior.PlayerListener;
import de.bitbrain.clouboy.core.World.Behavior;
import de.bitbrain.clouboy.physics.Collisions;
import de.bitbrain.clouboy.tweens.PlayerAnimator;

public class GameObjectFactory {

  private World world;

  private TweenManager tweenManager;

  private SecureRandom random = new SecureRandom(UUID.randomUUID().toString().getBytes());

  public GameObjectFactory(World world, TweenManager tweenManager) {
    this.world = world;
    this.tweenManager = tweenManager;
  }

  public World getWorld() {
    return world;
  }

  public GameObject createPlayer(float x, float y, PlayerListener... listeners) {
    GameObject player = world.addObject();
    PlayerBehavior behavior = new PlayerBehavior();
    for (PlayerListener l : listeners) {
      behavior.addListener(l);
    }
    behavior.addListener(new PlayerAnimator(tweenManager));
    world.applyBehavior(player, behavior);
    player.setType(GameObjectType.BOY);
    player.setPosition(x, y);
    player.setDimensions(64, 64);
    return player;
  }

  public List<GameObject> createCloud(float x, float y, int elements, boolean normal) {
    List<GameObject> clouds = new ArrayList<GameObject>();
    Behavior behavior = new CloudBehavior(normal ? 20f : 560f);
    int offset = 0;
    String id = UUID.randomUUID().toString();
    for (int i = 0; i < elements; ++i) {
      double angle = Math.toRadians(random.nextFloat() * 45.0);
      double length = 10f + random.nextFloat() * 30.0;
      float localX = (float) (x + Math.cos(angle) * length);
      float localY = (float) (y + Math.sin(angle) * length);
      float size = 32f + random.nextFloat() * 64f;
      GameObject cloud = world.addObject();
      world.applyBehavior(cloud, behavior);
      cloud.setType(normal ? GameObjectType.CLOUD : GameObjectType.DARK_CLOUD);
      cloud.setPosition(localX + offset, localY);
      cloud.setStatic(true);
      cloud.setCollision(normal ? Collisions.CLOUD : Collisions.NONE);
      cloud.setId(id);
      cloud.setDimensions(size, size);
      if (!normal) {
        cloud.setColor(0.5f, 0.5f, 0.5f, 1f);
      }
      clouds.add(cloud);
      offset += random.nextFloat() * 15f + 5;
    }
    return clouds;
  }
}
