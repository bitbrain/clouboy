package de.bitbrain.clouboy.core;

public class GameInfo {

  private GameObject player;

  private int record;

  public GameInfo(GameObject player) {
    this.player = player;
  }

  public void setPlayer(GameObject player) {
    this.player = player;
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

  public int getPoints() {
    float offset = player.getLeft();
    if (offset < 0) {
      offset = 0;
    }
    return Math.round(offset / 100f);
  }

}
