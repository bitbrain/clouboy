package de.bitbrain.clouboy.core;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.OrthographicCamera;

import de.bitbrain.clouboy.core.PlayerBehavior.PlayerListener;
import de.bitbrain.clouboy.tweens.GameObjectTween;

public class CloudGenerator implements PlayerListener {

  private OrthographicCamera camera;

  private float currentGap = 0;

  private float cloudDistance = -50;

  private GameObjectFactory factory;

  private GameObject recentCloud;

  private HashMap<String, Collection<GameObject>> data = new HashMap<String, Collection<GameObject>>();

  private TweenManager tweenManager;

  private String lastUUID;

  private SecureRandom random = new SecureRandom(UUID.randomUUID().toString().getBytes());

  static {
    Tween.registerAccessor(GameObject.class, new GameObjectTween());
  }

  public CloudGenerator(OrthographicCamera camera, GameObjectFactory factory, TweenManager tweenManager) {
    this.camera = camera;
    this.factory = factory;
    this.tweenManager = tweenManager;
  }

  public void reset() {
    data.clear();
    lastUUID = "";
    recentCloud = null;
    currentGap = 0;

  }

  public void update(float delta) {
    if (recentCloud != null) {
      while (!recentCloud.getUUID().equals(lastUUID)) {
        if (currentGap >= maxGap()) {
          return;
        }
        generateNext();
      }
    }
    currentGap = recentCloud != null ? recentCloud.getRight() : 0;
    while (currentGap < maxGap()) {
      generateNext();
    }
  }

  private float maxGap() {
    return camera.position.x + camera.zoom * camera.viewportWidth;
  }

  private float getRandomY() {
    return 2600 - random.nextFloat() * 500 + random.nextFloat() * 500;
  }

  private void generateNext() {
    int size = (int) (8 + random.nextFloat() * 5);
    float y = getRandomY();
    float defaultCloudChance = y > 3200 ? 0.75f : 0.94f;
    List<GameObject> clouds =
        factory.createCloud(currentGap + cloudDistance, y, size, random.nextFloat() < defaultCloudChance);
    recentCloud = clouds.get(0);
    float maxX = recentCloud.getRight();
    for (GameObject cloud : clouds) {
      if (cloud.getRight() > maxX) {
        maxX = cloud.getRight();
        recentCloud = cloud;
      }
    }
    currentGap = recentCloud.getRight();
    lastUUID = recentCloud.getUUID();
    data.put(recentCloud.getId(), clouds);
  }

  @Override
  public void onJump(GameObject player, int jumps, int maxJumps) {
    if (player.getLastCollision() != null && player.getLastCollision().getType() == GameObjectType.CLOUD
        || player.getLastCollision() != null && player.getLastCollision().getType() == GameObjectType.DARK_CLOUD) {
      GameObject cloud = player.getLastCollision();
      Collection<GameObject> clouds = data.get(cloud.getId());
      if (clouds != null) {
        for (GameObject object : clouds) {
          removeCloud(object);
        }
        data.remove(cloud.getId());
      }
    }
  }

  private void removeCloud(final GameObject cloud) {
    final float duration = 1f - random.nextFloat() * 0.5f;
    cloud.setCollision(null);
    tweenManager.killTarget(cloud);
    Tween.to(cloud, GameObjectTween.SCALE, duration).target(0f).setCallbackTriggers(TweenCallback.COMPLETE)
        .setCallback(new TweenCallback() {
          @Override
          public void onEvent(int type, BaseTween<?> source) {
            factory.getWorld().remove(cloud);
          }
        }).start(tweenManager);
    Tween.to(cloud, GameObjectTween.ALPHA, duration).target(0f).start(tweenManager);
  }

  @Override
  public void onLand(GameObject player, int jumps, int maxJumps) {
    // TODO Auto-generated method stub

  }
}
