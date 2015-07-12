package de.bitbrain.clouboy.social;

public interface SocialManager {

  public void login();

  public void logout();

  public boolean isSignedIn();

  public void submitScore(int score, String leaderboard);

  public void submitAchievement(String id);

  public void incrementAchievement(String id, int points);

  public void showLadder();

  void showAchievements();

  public void getScoresData();

  boolean isConnected();
}
