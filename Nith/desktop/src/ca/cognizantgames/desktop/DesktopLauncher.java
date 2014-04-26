package ca.cognizantgames.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ca.cognizantgames.game.Nith;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 620;
        config.height = 720;
        //config.addIcon();
        config.useGL30 = true;
        config.title = "Nith the surface!";
		new LwjglApplication(new Nith(), config);
	}
}
