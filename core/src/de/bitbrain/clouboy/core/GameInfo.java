package de.bitbrain.clouboy.core;

import de.bitbrain.clouboy.core.PlayerBehavior.PlayerListener;

public class GameInfo implements PlayerListener {

  private GameObject player;

  private int record;

  private int points;

  public GameInfo(GameObject player) {
    this.player = player;
  }

  public void setPlayer(GameObject player) {
    this.record = this.points;
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
    return getPoints() > this.record;
  }

  public void addPoint() {
    points++;
  }

  public int getPoints() {
    return points;
  }

  @Override
  public void onJump(GameObject player) {
    if (player.getLastCollision() != null && player.getLastCollision().getType() == GameObjectType.CLOUD) {
      addPoint();
    }

  }

}
