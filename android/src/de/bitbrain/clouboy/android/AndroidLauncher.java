package de.bitbrain.clouboy.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.social.SocialManager;

public class AndroidLauncher extends AndroidApplication implements SocialManager {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    initialize(new ClouBoy(this), config);
  }

  @Override
  public void login() {
    // TODO Auto-generated method stub

  }

  @Override
  public void logout() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isSignedIn() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void submitScore(int score) {
    // TODO Auto-generated method stub

  }

  @Override
  public void submitAchievement(String id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void incrementAchievement(String id, int points) {
    // TODO Auto-generated method stub

  }

  @Override
  public void showLadder() {
    // TODO Auto-generated method stub

  }

  @Override
  public void showAchievements() {
    // TODO Auto-generated method stub

  }

  @Override
  public void getScoresData() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isConnected() {
    // TODO Auto-generated method stub
    return false;
  }
}
