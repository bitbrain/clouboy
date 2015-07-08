package de.bitbrain.clouboy.core;

import com.badlogic.gdx.audio.Sound;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.PlayerBehavior.PlayerListener;
import de.bitbrain.clouboy.graphics.FX;

public class PointManager implements PlayerListener {

  private GameObject player;

  private int record;

  private int points;

  private int jumps, maxJumps, lastJumps;

  private int multiplicator = 1;

  private FX fx = FX.getInstance();

  public PointManager(GameObject player) {
    this.player = player;
  }

  public int getJumps() {
    return jumps;
  }

  public int getMaxJumps() {
    return maxJumps;
  }

  public void setPlayer(GameObject player) {
    this.record = getPoints();
    this.player = player;
    this.points = 0;
  }

  public void setRecord(int record) {
    this.record = record;
  }

  public int getRecord() {
    return this.record;
  }

  public boolean hasNewRecord() {
    return getPoints() > getRecord();
  }

  public void addPoints(int points) {
    this.points += points;
    Sound sound = SharedAssetManager.get(Assets.SND_KLING, Sound.class);
    sound.play(0.6f);
  }

  public void addPoint() {
    points++;
    Sound sound = SharedAssetManager.get(Assets.SND_KLING, Sound.class);
    sound.play(0.6f);
  }

  public int getPoints() {
    return points;
  }

  @Override
  public void onJump(GameObject player, int jumps, int maxJumps) {
    this.jumps = jumps;
    this.maxJumps = maxJumps;
  }

  @Override
  public void onLand(GameObject player, int jumps, int maxJumps) {
    if (player.getLastCollision() != null && player.getLastCollision().getType() == GameObjectType.CLOUD) {
      if (jumps == maxJumps && lastJumps == maxJumps) {
        multiplicator++;
      } else {
        multiplicator = 1;
      }
      if (jumps * multiplicator > 0) {
        addPoints(jumps * multiplicator);
      }
      lastJumps = jumps;
    }
  }

}
