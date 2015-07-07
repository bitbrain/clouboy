package de.bitbrain.clouboy.ui;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.bitbrain.clouboy.core.GameContext;

public class GameOverWidget extends Table {

  private Label points;

  public GameOverWidget(GameContext gameInfo, TweenManager tweenManager) {
    setFillParent(true);
    points = new Label("0", Styles.LABEL_STYLE_TEXT);
    add(points).padBottom(430f).row();
  }
}
