package ca.cognizantgames.game;

import ca.cognizantgames.screens.Splash;
import com.badlogic.gdx.Game;

public class Nith extends Game {

	@Override
	public void create () {
		this.setScreen(new Splash(this));
	}
}
