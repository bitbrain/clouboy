package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.CloudGenerator;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.GameObjectFactory;
import de.bitbrain.clouboy.core.World;
import de.bitbrain.clouboy.graphics.CameraTracker;

public class IngameScreen extends AbstractScreen {

  private Sprite background;

  private AssetManager assets;

  private World world;

  private GameObjectFactory factory;

  private CloudGenerator cloudGenerator;

  private CameraTracker cameraTracker;

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
    GameObject player = factory.createPlayer(200, 800);
    factory.createCloud(200, 200, 10);
    cloudGenerator = new CloudGenerator(camera, factory);
    cameraTracker = new CameraTracker(player, camera);
  }

  @Override
  protected void beforeRender(float delta) {
    cameraTracker.update(delta);
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    cloudGenerator.update(delta);
    background.setPosition(camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2);
    background.setSize(camera.viewportWidth, camera.viewportHeight);
    background.draw(batch, 1f);
    world.updateAndRender(batch, delta);
  }

}
