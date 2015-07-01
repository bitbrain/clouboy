package de.bitbrain.clouboy.ui;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.bitbrain.clouboy.core.GameInfo;

public class GameInfoWidget extends Table {

  private GameInfo info;

  private TweenManager tweenManager;

  private Label points;

  public GameInfoWidget(GameInfo info, TweenManager tweenManager) {
    setFillParent(true);
    this.info = info;
    points = new Label("0", Styles.LABEL_STYLE_TEXT);
    add(points).padBottom(500f);
  }

  @Override
  public void act(float delta) {
    points.setText(String.valueOf(info.getPoints()));
    super.act(delta);
  }
}
