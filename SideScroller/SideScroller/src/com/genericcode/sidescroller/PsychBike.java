package com.genericcode.sidescroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.genericcode.sidescroller.screens.MainMenuScreen;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.states.screens.SplashScreen;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.SoundManager;

public class PsychBike extends Game {
	
	@Override
	public void create() {
		Convert.init(1f);
		
		title = "PsychBike";
		
		landscapeMode = true;
		
		super.create();
		
		this.getScreenManager().addScreen(new SplashScreen(this, Gdx.files.internal("data/Textures/splash.png"), new MainMenuScreen(this), 1.25f, 4f, 1.25f));
	}
	
	@Override
	protected void loadSounds() { 
		Preferences pref = Gdx.app.getPreferences("settings");
		
		float soundVol = pref.getBoolean("Sound", true) ? 1f : 0f;
		float musicVol = pref.getBoolean("Music", true) ? 1f : 0f;
		
		SoundManager.setSoundVolume(soundVol);
		SoundManager.setMusicVolume(musicVol);
		
		SoundManager.addSound("back", Gdx.files.internal("data/Sounds/back.wav"));
		SoundManager.addSound("select", Gdx.files.internal("data/Sounds/select.wav"));
		SoundManager.addSound("shot", Gdx.files.internal("data/Sounds/shot.wav"));
		SoundManager.addSound("explosion", Gdx.files.internal("data/Sounds/explosion.wav"));
		SoundManager.addSound("hit", Gdx.files.internal("data/Sounds/hit.wav"));
	}
	
}
