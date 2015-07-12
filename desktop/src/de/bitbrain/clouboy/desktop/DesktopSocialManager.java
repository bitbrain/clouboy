package de.bitbrain.clouboy.desktop;

import de.bitbrain.clouboy.social.SocialManager;

public class DesktopSocialManager implements SocialManager {

  @Override
  public void login() {

  }

  @Override
  public void logout() {

  }

  @Override
  public boolean isSignedIn() {
    return false;
  }

  @Override
  public void submitScore(int score, String leaderboardId) {
    System.out.println("New score: " + score);
  }

  @Override
  public void submitAchievement(String id) {
    System.out.println("Achievement unlocked: " + id);
  }

  @Override
  public void incrementAchievement(String id, int points) {
    System.out.println("Incremential achievement " + id + " increased by " + points);
  }

  @Override
  public void showLadder() {
    System.out.println("Show ladder");
  }

  @Override
  public void showAchievements() {
    System.out.println("Show achievements");
  }

  @Override
  public int getPlayerRecord() {
    return 0;
  }

  @Override
  public boolean isConnected() {
    return false;
  }

}
