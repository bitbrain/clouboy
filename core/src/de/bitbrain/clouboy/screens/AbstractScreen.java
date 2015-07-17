package de.bitbrain.clouboy.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.graphics.FX;
import de.bitbrain.clouboy.graphics.ParticleRenderer;
import de.bitbrain.clouboy.graphics.ScreenShake;
import de.bitbrain.clouboy.tweens.ActorTween;
import de.bitbrain.clouboy.tweens.Animator;
import de.bitbrain.clouboy.ui.Tooltip;

public abstract class AbstractScreen implements Screen {

  private static final float BUTTON_TRANSPARENCY = 0.5f;

  protected Stage stage;

  protected OrthographicCamera camera;

  private Batch batch;

  protected ClouBoy game;

  protected TweenManager tweenManager;

  protected ParticleRenderer particleRenderer;

  protected AssetManager assets = SharedAssetManager.getInstance();

  protected Animator animator;

  protected FX fx = FX.getInstance();

  protected Tooltip tooltip = Tooltip.getInstance();

  protected InputMultiplexer input;

  private ScreenShake shake;

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
    shake = new ScreenShake(tweenManager);
    fx.init(tweenManager, camera, shake);
    input = new InputMultiplexer();
    Gdx.input.setCatchBackKey(true);
    Gdx.input.setCatchMenuKey(true);
    Gdx.input.setInputProcessor(input);
    onShow();
  }

  @Override
  public final void render(float delta) {
    beforeRender(delta);
    Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    tweenManager.update(delta);
    camera.translate(shake.getShake().x, shake.getShake().y);
    camera.update();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    onRender(batch, delta);
    fx.render(batch, delta);
    batch.end();
    stage.draw();
    camera.translate(-shake.getShake().x, -shake.getShake().y);
  }

  @Override
  public final void resize(int width, int height) {
    if (stage == null) {
      stage = new Stage(new FillViewport(1000, 600));
      initStage(stage);
      tooltip.init(tweenManager, stage, camera);
      input.addProcessor(stage);
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
    stage.dispose();
    tweenManager.killAll();
  }

  protected abstract void onRender(Batch batch, float delta);

  protected void beforeRender(float delta) {

  }

  protected void initStage(Stage stage) {

  }

  protected void onResize(int width, int height) {

  }

  protected abstract void onShow();



  protected abstract class TitleButtonListener extends ClickListener {
    @Override
    public void clicked(InputEvent event, float x, float y) {
      clicked();
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
      Actor button = event.getListenerActor();
      tweenManager.killTarget(button);
      Tween.to(button, ActorTween.ALPHA, 0.1f).target(1f).start(tweenManager);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
      Actor button = event.getListenerActor();
      tweenManager.killTarget(button);
      Tween.to(button, ActorTween.ALPHA, 0.6f).target(BUTTON_TRANSPARENCY).start(tweenManager);
    }

    protected abstract void clicked();
  }

    protected Actor createAchievementButton() {
      final Image button =
              new Image(new SpriteDrawable(new Sprite(assets.get(Assets.TEX_BUTTON_ACHIEVEMENTS, Texture.class)))) {
                @Override
                public void act(float delta) {
                  setVisible(game.getSocialManager().isConnected());
                  super.act(delta);
                }
              };
      button.addCaptureListener(new TitleButtonListener() {
        @Override
        protected void clicked() {
          game.getSocialManager().showAchievements();
        }
      });
      button.getColor().a = BUTTON_TRANSPARENCY;
      return button;
    }

    protected Actor createLadderButton() {
      final Image button = new Image(new SpriteDrawable(new Sprite(assets.get(Assets.TEX_BUTTON_LADDER, Texture.class)))) {
        @Override
        public void act(float delta) {
          setVisible(game.getSocialManager().isConnected());
          super.act(delta);
        }
      };
      button.addCaptureListener(new TitleButtonListener() {
        @Override
        protected void clicked() {
          game.getSocialManager().showLadder();
        }
      });
      button.getColor().a = BUTTON_TRANSPARENCY;
      return button;
    }



    protected Actor createPlayButton() {
      final Image button = new Image(new SpriteDrawable(new Sprite(assets.get(Assets.TEX_BUTTON_PLAY, Texture.class))));
      button.addCaptureListener(new TitleButtonListener() {
        @Override
        protected void clicked() {
          game.setScreen(new IngameScreen(game));
        }
      });
      button.getColor().a = BUTTON_TRANSPARENCY;
      return button;
    }
}
