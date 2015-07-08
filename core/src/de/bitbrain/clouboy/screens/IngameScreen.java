package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.core.CloudGenerator;
import de.bitbrain.clouboy.core.GameContext;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.GameObjectFactory;
import de.bitbrain.clouboy.core.World;
import de.bitbrain.clouboy.graphics.CameraTracker;
import de.bitbrain.clouboy.graphics.JumpParticleRenderer;
import de.bitbrain.clouboy.tweens.Animator.AnimatorCallback;
import de.bitbrain.clouboy.ui.GameInfoWidget;
import de.bitbrain.clouboy.ui.GameOverWidget;

public class IngameScreen extends AbstractScreen {

  private Sprite background;

  private World world;

  private GameObjectFactory factory;

  private CloudGenerator cloudGenerator;

  private CameraTracker cameraTracker;

  private GameObject player;

  private GameContext info;

  private boolean gameOver = false, wasTouchUp = false;

  private GameInfoWidget gameInfoWidget;

  private GameOverWidget gameOverWidget;

  private Music envSound;

  public IngameScreen(ClouBoy game) {
    super(game);
  }

  @Override
  protected void onShow() {
    background = new Sprite(assets.get(Assets.TEX_BACKGROUND, Texture.class));
    world = new World(camera);
    world.init();
    factory = new GameObjectFactory(world, tweenManager);
    cloudGenerator = new CloudGenerator(camera, factory, tweenManager);
    envSound = assets.get(Assets.MSC_WIND, Music.class);
    envSound.setVolume(0.1f);
    envSound.setLooping(true);
    envSound.play();
    camera.zoom = 1.5f;
    info = new GameContext(player);
    init();
  }

  @Override
  protected void initStage(Stage stage) {
    gameInfoWidget = new GameInfoWidget(info, tweenManager);
    gameOverWidget = new GameOverWidget(info, tweenManager);
    stage.addActor(gameInfoWidget);
  }

  @Override
  protected void beforeRender(float delta) {
    cameraTracker.update(delta);
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK)) {
      game.setScreen(new TitleScreen(game));
    } else if (Gdx.input.isTouched() && gameOver && wasTouchUp) {
      init();
    }
    if (!gameOver) {
      cloudGenerator.update(delta);
    } else {
      wasTouchUp = !Gdx.input.isTouched();
    }
    background.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
        - (camera.zoom * camera.viewportHeight) / 2);
    background.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
    background.draw(batch, 1f);
    particleRenderer.updateAndRender(delta, batch);
    if (!gameOver) {
      world.updateAndRender(batch, delta);
      checkForGameOver();
    }
  }

  @Override
  protected void onResize(int width, int height) {
    fx.fadeIn(1f);
  }

  private void checkForGameOver() {
    if (player.getTop() < -2000) {
      assets.get(Assets.SND_GAME_OVER, Sound.class).play(1f, 1f, 1f);
      world.reset();
      cloudGenerator.reset();
      fx.fadeOut(0.01f);
      animator.fadeOut(gameInfoWidget, 0.01f, 0f).after(new AnimatorCallback() {
        @Override
        public void action() {
          stage.getActors().removeValue(gameInfoWidget, true);
          gameOverWidget.animate();
          stage.addActor(gameOverWidget);
          animator.fadeIn(gameOverWidget, 2f);
          gameOver = true;
        }
      });
      envSound.stop();
    }
  }

  @Override
  public void dispose() {
    super.dispose();
    world.reset();
    cloudGenerator.reset();
  }

  private void init() {
    player = factory.createPlayer(0, 1800, cloudGenerator, info, new JumpParticleRenderer(particleRenderer));
    cameraTracker = new CameraTracker(player, camera);
    cameraTracker.focus();
    info.setPlayer(player);
    gameOver = false;
    if (gameInfoWidget != null) {
      stage.getActors().removeValue(gameOverWidget, true);
      stage.addActor(gameInfoWidget);
      animator.fadeIn(gameInfoWidget, 1f, 1f);
    }
    fx.fadeIn(1f);
    envSound.play();
  }

}
