package de.bitbrain.clouboy.ui;

import java.util.ArrayList;
import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.GameInfo;
import de.bitbrain.clouboy.core.PlayerBehavior;
import de.bitbrain.clouboy.tweens.ActorTween;

public class GameInfoWidget extends Table {

  private GameInfo info;

  private TweenManager tweenManager;

  private Label points;

  private AssetManager assets = SharedAssetManager.getInstance();

  private JumpWidget jumpWidget;

  static {
    Tween.registerAccessor(Image.class, new ActorTween());
  }

  public GameInfoWidget(GameInfo info, TweenManager tweenManager) {
    setFillParent(true);
    this.tweenManager = tweenManager;
    this.info = info;
    points = new Label("0", Styles.LABEL_STYLE_TEXT);
    add(points).padBottom(430f).row();
    jumpWidget = new JumpWidget();
    add(jumpWidget);
  }

  @Override
  public void act(float delta) {
    points.setText(String.valueOf(info.getPoints()));
    jumpWidget.setJumps(info.getJumps());
    super.act(delta);
  }

  private class JumpWidget extends Table {

    private static final float ALPHA = 0.1f;

    private static final float ALPHA_JUMP = 0.8f;

    private List<Image> jumps = new ArrayList<Image>();

    private int amount = 0;

    public JumpWidget() {
      setMaxJumps(PlayerBehavior.MAX_JUMPS);
    }

    private void setJumps(int amount) {
      if (this.amount != amount) {
        for (int i = 0; i < jumps.size(); ++i) {
          Image image = jumps.get(i);
          if (amount < 2 && i < amount) {
            image.setColor(Color.RED.cpy());
          } else {
            image.setColor(Color.WHITE.cpy());
          }
          image.getColor().a = ALPHA;
          tweenManager.killTarget(image);
          if (i < amount) {
            Tween.to(image, ActorTween.ALPHA, 1f).target(ALPHA_JUMP).start(tweenManager);
          }
        }
        this.amount = amount;
      }
    }

    private void setMaxJumps(int amount) {
      jumps.clear();
      for (int i = 0; i < amount; ++i) {
        Image image = new Image(new SpriteDrawable(new Sprite(assets.get(Assets.TEX_CLOUD, Texture.class))));
        image.setColor(1f, 1f, 1f, ALPHA);
        image.setScale(0.75f);
        add(image);
        jumps.add(image);
      }
    }
  }
}
