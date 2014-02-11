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
import com.lostcode.javalib.entities.components.render.Renderable;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.systems.InputSystem;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.Display;
import com.lostcode.javalib.utils.SoundManager;

@SuppressWarnings("unused")
public class PlayerControlSystem extends InputSystem {

	private static final float PLAYER_SPEED = Convert.pixelsToMeters(200f);
	
	private boolean rightClick = false;
	private boolean leftClick = false;
	private boolean middleClick = false;
	
	private String use = "";
	
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
	private boolean select5 = false;
	private boolean select6 = false;

	private Vector2 aim;

	private String name;
	
	private Stage stage;

	public PlayerControlSystem(InputMultiplexer input, String name) {
		super(input);
		aim = new Vector2();

		this.name = name;
		
		stage = new Stage(Display.getPreferredWidth(),
				Display.getPreferredHeight(), false);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals(name);
	}

	@Override
	protected void process(Entity e) {
		super.process(e);
		Body b = (Body) e.getComponent(Body.class);
		Sprite s = (Sprite) e.getComponent(Sprite.class);
		Renderable r = (Renderable) e.getComponent(Renderable.class);
		Inventory inv = (Inventory) e.getComponent(Inventory.class);
		Cooldown cd = (Cooldown) e.getComponent(Cooldown.class);
		cd.drain(deltaSeconds());
		Vector2 velocity = new Vector2();
		Vector2 origin = b.getPosition().add(new Vector2(0,5f));
		
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
		
		velocity.nor();
		velocity.scl(PLAYER_SPEED);
		
		velocity.y /= 2;
		
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
		} else if (select5) {
			inv.select(5);
		} else if (select6) {
			inv.select(6);
		}
		
		if(rightClick)
			use = "secondary";
		if(leftClick)
			use = "primary";

		b.setLinearVelocity(velocity);

		Vector2 fireL = new Vector2();

		aim.x = Convert.pixelsToMeters(Gdx.input.getX()
				- Gdx.graphics.getWidth() / 2f
				+ world.getCamera().position.x);
		aim.y = Convert.pixelsToMeters(-Gdx.input.getY()
				+ Gdx.graphics.getHeight() / 2f
				+ world.getCamera().position.y);
		fireL = aim.cpy().sub(b.getPosition());

		b.setRotation(0);
		
		//Sprite/Renderable handling
		Vector2 draw = new Vector2( b.getPosition().x-s.getWidth()/2, b.getPosition().cpy().y-Convert.pixelsToMeters(4f) );
		r.setOrigin(b.getPosition().sub(draw));
		r.setLayer((int) -(b.getPosition().y-world.getBounds().height) );
		if (aim.x - b.getPosition().x > 0)
			s.setScale(1, 1);
		else
			s.setScale(-1, 1);

		if ( leftClick || rightClick ) {
			//b.setRotation((float) Math.toRadians(fireL.angle()));
			if ( cd.isFinished() ) {
				fireL.angle();

				if (!fireL.equals(new Vector2())) {
					//fireL.nor();
					if( inv.getSelected().use(use, e, world, fireL.cpy() ) ) {
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
		rightClick = false;
		leftClick = false;
		middleClick = false;
		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		reload = false;
		select0 = false;
		select1 = false;
		select2 = false;
		select3 = false;
		select4 = false;
		select5 = false;
		select6 = false;

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
		
		if (keycode == Keys.NUM_5) {
			select5 = true;
			return true;
		}
		
		if (keycode == Keys.NUM_6) {
			select6 = true;
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
		
		if (keycode == Keys.NUM_5) {
			select5 = false;
			return true;
		}
		
		if (keycode == Keys.NUM_6) {
			select6 = false;
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
