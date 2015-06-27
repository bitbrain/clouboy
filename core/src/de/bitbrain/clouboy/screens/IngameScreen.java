package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.GameObjectType;
import de.bitbrain.clouboy.core.PlayerBehavior;
import de.bitbrain.clouboy.core.World;

public class IngameScreen extends AbstractScreen {

  private Sprite background;

  private AssetManager assets;

  private World world;

  public IngameScreen(ClouBoy game) {
    super(game);
  }

  @Override
  protected void onShow() {
    assets = SharedAssetManager.getInstance();
    background = new Sprite(assets.get(Assets.TEX_BACKGROUND, Texture.class));
    world = new World();
    world.init();

    GameObject player = world.addObject();
    world.applyBehavior(player, new PlayerBehavior());
    player.setType(GameObjectType.BOY);
    player.setPosition(0, 0);
    player.setDimensions(64, 64);

    GameObject obstacle = world.addObject();
    obstacle.setType(GameObjectType.BOY);
    obstacle.setPosition(200, 200);
    obstacle.setStatic(true);
    obstacle.setDimensions(64, 64);
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    background.draw(batch, 1f);
    world.updateAndRender(batch, delta);
  }

}
