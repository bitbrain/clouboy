package de.bitbrain.clouboy.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import de.bitbrain.clouboy.util.IntegerValueProvider;

public class IntegerValueTween implements TweenAccessor<IntegerValueProvider> {

  public static final int VALUE = 1;

  @Override
  public void setValues(IntegerValueProvider target, int type, float[] newValues) {
    switch (type) {
      case VALUE:
        target.setValue(Math.round(newValues[0]));
        break;
    }
  }

  @Override
  public int getValues(IntegerValueProvider target, int type, float[] returnValues) {
    switch (type) {
      case VALUE:
        returnValues[0] = target.getValue();
        return 1;
      default:
        return 0;
    }
  }

}