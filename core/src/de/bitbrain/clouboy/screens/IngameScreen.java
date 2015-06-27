package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.CloudGenerator;
import de.bitbrain.clouboy.core.GameObjectFactory;
import de.bitbrain.clouboy.core.World;

public class IngameScreen extends AbstractScreen {

  private Sprite background;

  private AssetManager assets;

  private World world;

  private GameObjectFactory factory;

  private CloudGenerator cloudGenerator;

  public IngameScreen(ClouBoy game) {
    super(game);
  }

  @Override
  protected void onShow() {
    assets = SharedAssetManager.getInstance();
    background = new Sprite(assets.get(Assets.TEX_BACKGROUND, Texture.class));
    world = new World(camera);
    world.init();
    factory = new GameObjectFactory(world);
    factory.createPlayer(200, 800);
    factory.createCloud(200, 200, 10);
    cloudGenerator = new CloudGenerator(camera, factory);
    world.addListener(cloudGenerator);
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    cloudGenerator.update(delta);
    background.draw(batch, 1f);
    world.updateAndRender(batch, delta);
  }

}
