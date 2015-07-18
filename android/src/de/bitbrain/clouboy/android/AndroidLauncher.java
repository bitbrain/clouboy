package de.bitbrain.clouboy.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.example.games.basegameutils.GameHelper;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.social.Leaderboard;
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
  protected void onPause() {
    super.onPause();
    aHelper.onStop();
  }

  @Override
  protected void onResume() {
    super.onResume();
    aHelper.onStart(this);
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
  public void submitScore(int score, String leaderboardId) {
    if (isSignedIn()) {
      Games.Leaderboards.submitScore(aHelper.getApiClient(), leaderboardId, score);
    }
  }

  @Override
  public void submitAchievement(String id) {
    if (isSignedIn()) {
      Games.Achievements.unlock(aHelper.getApiClient(), id);
    }
  }

  @Override
  public void incrementAchievement(String id, int points) {
    if (isSignedIn()) {
      Games.Achievements.increment(aHelper.getApiClient(), id, points);
    }
  }

  @Override
  public void showLadder() {
    if (isSignedIn()) {
      startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(aHelper.getApiClient()), 105);
    }
  }

  @Override
  public void showAchievements() {
    if (isSignedIn()) {
      startActivityForResult(Games.Achievements.getAchievementsIntent(aHelper.getApiClient()), 105);
    }
  }

  @Override
  public int getPlayerRecord() {
    final AtomicBoolean found = new AtomicBoolean();
    Games.Leaderboards.loadPlayerCenteredScores(aHelper.getApiClient(), Leaderboard.MOST_POINTS, 1,
            1,
            25).setResultCallback(new ResultCallback<Leaderboards.LoadScoresResult>() {

      @Override
      public void onResult(Leaderboards.LoadScoresResult arg0) {
        found.set(true);
      }
    });
    while (!found.get()) {

    }
    found.set(false);
    final AtomicLong score = new AtomicLong(0);
    Games.Leaderboards.loadCurrentPlayerLeaderboardScore(aHelper.getApiClient(), Leaderboard.MOST_POINTS, LeaderboardVariant.TIME_SPAN_ALL_TIME, LeaderboardVariant.COLLECTION_PUBLIC).setResultCallback(new ResultCallback<Leaderboards.LoadPlayerScoreResult>() {

      @Override
      public void onResult(Leaderboards.LoadPlayerScoreResult result) {
        if (result != null && result.getScore() != null) {
          LeaderboardScore lbScore = result.getScore();
          score.set(lbScore.getRawScore());
        }
        found.set(true);
      }
    });
    while (!found.get()) {

    }
    return Math.round(score.get());
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
