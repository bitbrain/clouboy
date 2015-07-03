package de.bitbrain.clouboy.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.graphics.ParticleRenderer;
import de.bitbrain.clouboy.tweens.Animator;

public abstract class AbstractScreen implements Screen {

  protected Stage stage;

  protected OrthographicCamera camera;

  private Batch batch;

  protected ClouBoy game;

  protected TweenManager tweenManager;

  protected ParticleRenderer particleRenderer;

  protected AssetManager assets = SharedAssetManager.getInstance();

  protected Animator animator;

  AbstractScreen(ClouBoy game) {
    this.game = game;
  }

  @Override
  public final void show() {
    batch = new SpriteBatch();
    camera = new OrthographicCamera(1000, 600);
    tweenManager = new TweenManager();
    animator = new Animator(tweenManager);
    particleRenderer = new ParticleRenderer();
    onShow();
  }

  @Override
  public final void render(float delta) {
    beforeRender(delta);
    Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    tweenManager.update(delta);
    camera.update();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    onRender(batch, delta);
    batch.end();
    stage.draw();
  }

  @Override
  public final void resize(int width, int height) {
    if (stage == null) {
      stage = new Stage(new FillViewport(1000, 600));
      initStage(stage);
    }
    camera.setToOrtho(false, 1000, 600);
    onResize(width, height);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }

  protected abstract void onRender(Batch batch, float delta);

  protected void beforeRender(float delta) {

  }

  protected void initStage(Stage stage) {

  }

  protected void onResize(int width, int height) {

  }

  protected abstract void onShow();
}
