package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.i18n.Bundle;
import de.bitbrain.clouboy.i18n.Messages;
import de.bitbrain.clouboy.ui.Styles;

public class TitleScreen extends AbstractScreen {

  private Sprite background;

  private Sprite logo;

  private Label credits;

  private Table buttons;

  public TitleScreen(ClouBoy game) {
    super(game);
  }

  @Override
  protected void onShow() {
    background = new Sprite(assets.get(Assets.TEX_BACKGROUND, Texture.class));
    logo = new Sprite(assets.get(Assets.TEX_LOGO, Texture.class));

  }

  @Override
  protected void initStage(Stage stage) {
    Table layout = new Table();
    layout.setFillParent(true);
    credits = new Label(Bundle.general.get(Messages.CREDITS), Styles.LABEL_STYLE_TEXT);
    buttons = new Table();
    buttons.add(createAchievementButton()).padRight(26f);
    buttons.add(createPlayButton()).padRight(26f);
    buttons.add(createLadderButton());
    layout.add(buttons).height(300f).padTop(220f).row();
    credits.setFontScale(0.7f);
    layout.add(credits);
    stage.addActor(layout);
  }

  @Override
  protected void onRender(Batch batch, float delta) {

    background.draw(batch, 1f);

    logo.draw(batch, 1f);
  }

  @Override
  protected void onResize(int width, int height) {
    background.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
        - (camera.zoom * camera.viewportHeight) / 2);
    background.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
    logo.setPosition(camera.position.x - logo.getWidth() / 2f, camera.viewportHeight / 1.65f);
    animate();
  }

  private Actor createPlayButton() {
    final Image button = new Image(new SpriteDrawable(new Sprite(assets.get(Assets.TEX_BUTTON_PLAY, Texture.class))));
    button.addCaptureListener(new TitleButtonListener() {
      @Override
      protected void clicked() {
        game.setScreen(new IngameScreen(game));
      }
    });
    return button;
  }

  private Actor createAchievementButton() {
    final Image button =
        new Image(new SpriteDrawable(new Sprite(assets.get(Assets.TEX_BUTTON_ACHIEVEMENTS, Texture.class))));
    button.addCaptureListener(new TitleButtonListener() {
      @Override
      protected void clicked() {
        System.out.println("Open Achievements..");
      }
    });
    return button;
  }

  private Actor createLadderButton() {
    final Image button = new Image(new SpriteDrawable(new Sprite(assets.get(Assets.TEX_BUTTON_LADDER, Texture.class))));
    button.addCaptureListener(new TitleButtonListener() {
      @Override
      protected void clicked() {
        System.out.println("Open Ladder..");
      }
    });
    return button;
  }

  private void animate() {
    animator.fadeIn(logo, 2f);
    animator.fadeIn(background, 1.5f);
    animator.fadeIn(credits, 3f, 0.5f);
    animator.fadeIn(buttons, 3f);
  }

  private abstract class TitleButtonListener extends ClickListener {
    @Override
    public void clicked(InputEvent event, float x, float y) {
      clicked();
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
      Actor button = event.getListenerActor();
      button.setColor(new Color(0.8f, 0.9f, 0.9f, 1f));
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
      Actor button = event.getListenerActor();
      button.setColor(Color.WHITE.cpy());
    }

    protected abstract void clicked();
  }
}
