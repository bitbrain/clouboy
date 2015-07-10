package de.bitbrain.clouboy.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.social.SocialManager;

public class AndroidLauncher extends AndroidApplication implements SocialManager, GameHelper.GameHelperListener {

  private GameHelper aHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    aHelper = new GameHelper(this, GameHelper.CLIENT_ALL);
    aHelper.setup(this);
    initialize(new ClouBoy(this), config);
  }

  @Override
  protected void onStart() {
    super.onStart();
    aHelper.onStart(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    aHelper.onStop();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    aHelper.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void login() {
    try {
      runOnUiThread(new Runnable(){

        //@Override
        public void run(){
          aHelper.beginUserInitiatedSignIn();
        }
      });
    }catch (final Exception ex){

    }
  }

  @Override
  public void logout() {
    try {
      runOnUiThread(new Runnable(){

        //@Override
        public void run(){
          aHelper.signOut();
        }
      });
    }catch (final Exception ex){

    }

  }

  @Override
  public boolean isSignedIn() {
    return aHelper.isSignedIn();
  }

  @Override
  public void submitScore(int score) {

  }

  @Override
  public void submitAchievement(String id) {

  }

  @Override
  public void incrementAchievement(String id, int points) {

  }

  @Override
  public void showLadder() {
    startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(aHelper.getApiClient()), 105);
  }

  @Override
  public void showAchievements() {

  }

  @Override
  public void getScoresData() {

  }

  @Override
  public boolean isConnected() {
    return aHelper.isSignedIn();
  }

  @Override
  public void onSignInFailed() {

  }

  @Override
  public void onSignInSucceeded() {

  }
}
