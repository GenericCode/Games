package com.genericcode.sidescroller.entities.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.genericcode.sidescroller.entities.components.items.Gun;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.generic.Inventory;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.systems.InputSystem;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.Display;
import com.lostcode.javalib.utils.SoundManager;

@SuppressWarnings("unused")
public class PlayerControlSystem extends InputSystem {

	private static final float PLAYER_SPEED = Convert.pixelsToMeters(140f);
	
	private boolean rightClick = false;
	private boolean leftClick = false;
	private boolean middleClick = false;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;
	private boolean movingDown = false;
	
	private boolean reload = false;
	
	private boolean select0 = false;
	private boolean select1 = false;
	private boolean select2 = false;
	private boolean select3 = false;
	private boolean select4 = false;

	private Vector2 aim;

	private Stage stage;

	public PlayerControlSystem(InputMultiplexer input) {
		super(input);
		aim = new Vector2();

		stage = new Stage(Display.getPreferredWidth(),
				Display.getPreferredHeight(), false);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getType().equals("Player");
	}

	@Override
	protected void process(Entity e) {
		super.process(e);
		Body b = (Body) e.getComponent(Body.class);
		Inventory inv = (Inventory) e.getComponent(Inventory.class);
		Cooldown cd = (Cooldown) e.getComponent(Cooldown.class);
		cd.drain(deltaSeconds());
		Vector2 velocity = new Vector2();

		if (movingLeft) {
			velocity.x = -1f;
		} else if (movingRight) {
			velocity.x = 1f;
		}

		if (movingUp) {
			velocity.y = 1f;
		} else if (movingDown) {
			velocity.y = -1f;
		}
		
		if (reload) {
			if( inv.getSelected().use("reload") && cd.getCurrentValue() < ( (Gun) inv.getSelected()).reloadTime ) {
				cd.setMaxValue( ( (Gun) inv.getSelected() ).reloadTime );
				cd.restart();
			}
		}
		
		if (select0) {
			inv.select(0);
		} else if (select1) {
			inv.select(1);
		} else if (select2) {
			inv.select(2);
		} else if (select3) {
			inv.select(3);
		} else if (select4) {
			inv.select(4);
		}

		velocity.nor();
		velocity.scl(PLAYER_SPEED);

		b.setLinearVelocity(velocity);

		Vector2 fireL = new Vector2();

		aim.x = Convert.pixelsToMeters(Gdx.input.getX()
				- Gdx.graphics.getWidth() / 2f
				+ world.getCamera().position.x);
		aim.y = Convert.pixelsToMeters(-Gdx.input.getY()
				+ Gdx.graphics.getHeight() / 2f
				+ world.getCamera().position.y);
		fireL = aim.cpy().sub(b.getPosition());

		if (!velocity.equals(Vector2.Zero))
			b.setRotation((float) Math.toRadians(velocity.angle()));
		else
			b.setRotation((float) Math.toRadians(fireL.angle()));

		if ( Gdx.input.isTouched() ) {
			b.setRotation((float) Math.toRadians(fireL.angle()));
			if ( cd.isFinished() ) {
				b.setRotation((float) Math.toRadians(fireL.angle()));
				fireL.angle();

				if (!fireL.equals(new Vector2())) {
					fireL.nor();
					if(leftClick)
						if( inv.getSelected().use("primary", e, world, fireL ) ) {
							cd.setMaxValue( inv.getSelected().getUseTime() );
							cd.restart();
							SoundManager.playSound("shot", 0.5f);
						}
					if(rightClick)
						if( inv.getSelected().use("secondary", e, world, fireL ) ) {
							cd.setMaxValue( inv.getSelected().getUseTime() );
							cd.restart();
							SoundManager.playSound("shot", 0.5f);
						}
				}
			}
		}//Secondary use should be made more dissimilar/given proper variables in G
			
	}

	@Override
	public void pause() {
		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;

		super.pause();

		input.removeProcessor(stage);
	}

	@Override
	public void resume() {
		super.resume();
		input.addProcessor(stage);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.A) {
			movingLeft = true;
			return true;
		}

		if (keycode == Keys.W) {
			movingUp = true;
			return true;
		}

		if (keycode == Keys.D) {
			movingRight = true;
			return true;
		}

		if (keycode == Keys.S) {
			movingDown = true;
			return true;
		}
		
		if (keycode == Keys.NUM_0) {
			select0 = true;
			return true;
		}
		
		if (keycode == Keys.NUM_1) {
			select1 = true;
			return true;
		}
		
		if (keycode == Keys.NUM_2) {
			select2 = true;
			return true;
		}
		
		if (keycode == Keys.NUM_3) {
			select3 = true;
			return true;
		}
		
		if (keycode == Keys.NUM_4) {
			select4 = true;
			return true;
		}
		
		if (keycode == Keys.R) {
			reload = true;
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.A) {
			movingLeft = false;
			return true;
		}

		if (keycode == Keys.W) {
			movingUp = false;
			return true;
		}

		if (keycode == Keys.D) {
			movingRight = false;
			return true;
		}

		if (keycode == Keys.S) {
			movingDown = false;
			return true;
		}
		
		if (keycode == Keys.NUM_0) {
			select0 = false;
			return true;
		}
		
		if (keycode == Keys.NUM_1) {
			select1 = false;
			return true;
		}
		
		if (keycode == Keys.NUM_2) {
			select2 = false;
			return true;
		}
		
		if (keycode == Keys.NUM_3) {
			select3 = false;
			return true;
		}
		
		if (keycode == Keys.NUM_4) {
			select4 = false;
			return true;
		}
		
		if (keycode == Keys.R) {
			reload = false;
			return true;
		}

		return false;
	}
	
	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if( button == 0 )
			leftClick = true;
		
		if( button == 1 )
			rightClick = true;
		
		if( button == 2 )
			middleClick = true;
			
		return false;
	}
	
	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		if( button == 0 )
			leftClick = false;
		
		if( button == 1 )
			rightClick = false;
		
		if( button == 2 )
			middleClick = false;
		
		return false;
	}
	
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		return false;
	}
}
