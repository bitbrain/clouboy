package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.CloudGenerator;
import de.bitbrain.clouboy.core.GameInfo;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.GameObjectFactory;
import de.bitbrain.clouboy.core.World;
import de.bitbrain.clouboy.graphics.CameraTracker;
import de.bitbrain.clouboy.ui.GameInfoWidget;

public class IngameScreen extends AbstractScreen {

  private Sprite background;

  private AssetManager assets;

  private World world;

  private GameObjectFactory factory;

  private CloudGenerator cloudGenerator;

  private CameraTracker cameraTracker;

  private GameObject player;

  private GameInfo info;

  public IngameScreen(ClouBoy game) {
    super(game);
  }

  @Override
  protected void onShow() {
    assets = SharedAssetManager.getInstance();
    background = new Sprite(assets.get(Assets.TEX_BACKGROUND, Texture.class));
    world = new World(camera);
    world.init();
    factory = new GameObjectFactory(world, tweenManager);
    cloudGenerator = new CloudGenerator(camera, factory, tweenManager);
    Music envSound = assets.get(Assets.MSC_WIND, Music.class);
    envSound.setVolume(0.1f);
    envSound.setLooping(true);
    envSound.play();
    camera.zoom = 1.5f;
    info = new GameInfo(player);
    init();
  }

  @Override
  protected void initStage(Stage stage) {
    GameInfoWidget widget = new GameInfoWidget(info, tweenManager);
    stage.addActor(widget);
  }

  @Override
  protected void beforeRender(float delta) {
    cameraTracker.update(delta);
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    cloudGenerator.update(delta);
    background.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
        - (camera.zoom * camera.viewportHeight) / 2);
    background.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
    background.draw(batch, 1f);
    world.updateAndRender(batch, delta);
    checkForGameOver();
  }

  private void checkForGameOver() {
    if (player.getTop() < -1000) {
      world.reset();
      cloudGenerator.reset();
      init();
    }
  }

  private void init() {
    player = factory.createPlayer(0, 1400, cloudGenerator, info);
    cameraTracker = new CameraTracker(player, camera);
    cameraTracker.focus();
    info.setPlayer(player);
  }

}
