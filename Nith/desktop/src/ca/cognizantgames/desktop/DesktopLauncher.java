package ca.cognizantgames.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ca.cognizantgames.game.Nith;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 620;
        config.height = 720;
        config.addIcon("core/assets/images/icon.png", Files.FileType.Internal);
        config.useGL30 = false;
        config.title = "Nith the surface!";
		new LwjglApplication(new Nith(), config);
	}
}
