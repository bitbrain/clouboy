package de.bitbrain.clouboy.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import de.bitbrain.clouboy.core.PointManager;
import de.bitbrain.clouboy.tweens.ActorTween;
import de.bitbrain.clouboy.tweens.IntegerValueTween;
import de.bitbrain.clouboy.util.IntegerValueProvider;

public class GameOverWidget extends Table {

  private Label points;

  private IntegerValueProvider pointProvider = new IntegerValueProvider();

  private TweenManager tweenManager;

  private PointManager context;

  private Label touchInfo;

  private Label record;

  static {
    Tween.registerAccessor(IntegerValueProvider.class, new IntegerValueTween());
  }

  public GameOverWidget(PointManager context, TweenManager tweenManager) {
    this.tweenManager = tweenManager;
    this.context = context;
    setFillParent(true);
    points = new Label("                       ", Styles.LABEL_STYLE_TEXT);
    points.setFontScale(3f);
    points.setColor(Color.valueOf("00ccff"));
    add(points).padBottom(50f).row();
    record = new Label("Record\n10", Styles.LABEL_STYLE_TEXT);
    record.setAlignment(Align.center);
    add(record).padBottom(45f).row();
    touchInfo = new Label("[ touch to try again ]", Styles.LABEL_STYLE_TEXT);
    add(touchInfo).row();
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    points.setText(String.valueOf(pointProvider.getValue()));
  }

  private void setRecord() {
    if (context.hasNewRecord()) {
      record.setText("New record!");
      record.setColor(Color.GREEN);
    } else {
      record.setText("Record\n" + context.getRecord());
      record.setColor(Color.WHITE);
    }
  }

  public void animate() {
    pointProvider.setValue(0);
    tweenManager.killTarget(pointProvider);
    tweenManager.killTarget(touchInfo);
    touchInfo.getColor().a = 1f;
    setRecord();
    Tween.to(pointProvider, IntegerValueTween.VALUE, 0.8f).target(context.getPoints()).start(tweenManager);
    Tween.to(touchInfo, ActorTween.ALPHA, 0.5f).target(0.0f).repeatYoyo(Tween.INFINITY, 0).start(tweenManager);
  }
}
