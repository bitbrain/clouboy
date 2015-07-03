package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.i18n.Bundle;
import de.bitbrain.clouboy.i18n.Messages;
import de.bitbrain.clouboy.ui.Styles;

public class TitleScreen extends AbstractScreen {

  private Sprite background;

  private Sprite logo;

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
    Label credits = new Label(Bundle.general.get(Messages.CREDITS), Styles.LABEL_STYLE_TEXT);
    credits.setColor(1f, 1f, 1f, 0.4f);
    credits.setFontScale(0.7f);
    layout.add(credits).padTop(530f);
    stage.addActor(layout);
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    background.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
        - (camera.zoom * camera.viewportHeight) / 2);
    background.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
    background.draw(batch, 1f);
    logo.setPosition(camera.position.x - logo.getWidth() / 2f, camera.viewportHeight / 1.65f);
    logo.draw(batch, 1f);

  }

}