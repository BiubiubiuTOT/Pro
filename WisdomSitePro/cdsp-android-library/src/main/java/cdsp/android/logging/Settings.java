package cdsp.android.logging;

public final class Settings {

  private boolean showThreadInfo = true;

  public Settings hideThreadInfo() {
    showThreadInfo = false;
    return this;
  }

  public boolean isHideThreadInfo(){
	  return showThreadInfo;
  }
}
