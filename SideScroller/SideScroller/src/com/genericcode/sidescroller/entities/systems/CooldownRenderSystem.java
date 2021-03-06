package com.genericcode.sidescroller.entities.systems;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.render.Renderable;
import com.lostcode.javalib.entities.systems.ComponentSystem;
import com.lostcode.javalib.utils.Convert;

/**
 * Generic system that renders health bars above every entity that has a Health
 * component.
 * 
 * @author Natman64
 * @created Jul 27, 2013
 */
public class CooldownRenderSystem extends ComponentSystem {

	private static final int VERTICAL_OFFSET = 8;

	// region Fields

	private SpriteBatch spriteBatch;
	private Camera camera;

	private Texture backTexture;
	private Texture frontTexture;

	// endregion

	// region Initialization/Disposal

	/**
	 * Constructs a HealthRenderSystem.
	 * 
	 * @param backTextureHandle
	 *            A FileHandle pointing to the texture that will be drawn as the
	 *            back of the health bar.
	 * @param frontTextureHandle
	 *            A FileHandle pointing to the texture that will be drawn as the
	 *            front of the health bar.
	 */
	@SuppressWarnings("unchecked")
	public CooldownRenderSystem(Camera camera, FileHandle backTextureHandle,
			FileHandle frontTextureHandle) {
		super(Cooldown.class, Transform.class, Renderable.class);

		spriteBatch = new SpriteBatch();
		this.camera = camera;

		backTexture = new Texture(backTextureHandle);
		frontTexture = new Texture(frontTextureHandle);
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();

		backTexture.dispose();
		frontTexture.dispose();
	}

	// endregion

	// region Processing

	@Override
	public void processEntities() {
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();

		super.processEntities();

		spriteBatch.end();
	}

	@Override
	protected void process(Entity e) {
		Cooldown cooldown = (Cooldown) e.getComponent(Cooldown.class);

		if (!cooldown.render)
			return;

		Transform transform = (Transform) e.getComponent(Transform.class);
		Renderable sprite = (Renderable) e.getComponent(Renderable.class);

		Vector2 pos = transform.getPosition().cpy();

		pos = Convert.metersToPixels(pos);
		pos.add(new Vector2(-sprite.getWidth() / 2, sprite.getHeight() / 2
				+ VERTICAL_OFFSET));

		float height = backTexture.getHeight();
		float width = sprite.getWidth();

		spriteBatch.draw(backTexture, pos.x, pos.y, width, height);

		width *= cooldown.fraction();

		spriteBatch.draw(frontTexture, pos.x, pos.y, width, height);

	}

	// endregion

}
