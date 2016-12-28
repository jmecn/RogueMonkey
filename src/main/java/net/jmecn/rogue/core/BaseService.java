package net.jmecn.rogue.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseService implements Service {

	static Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	protected Game game;
	private boolean enabled = false;

	@Override
	public void initialize(Game game) {
		this.game = game;
		this.enabled = true;
	}

	@Override
	public void terminate(Game game) {
		if( isEnabled() ) {
            logger.info("onDisable():{}", this);
            onDisable();
        }
		this.game = null;
		this.enabled = false;
	}

	public abstract void update(float tpf);
	
	public abstract void onEnable();
	
	public abstract void onDisable();
	
	public void setEnabled(boolean enabled) {
		if (this.enabled == enabled) {
			return;
		}
		
		this.enabled = enabled;
		
		if (enabled) {
			logger.debug("onEnabled(): {}", this);
			onEnable();
		} else {
			logger.debug("onDisabled(): {}", this);
			onDisable();
		}
	}
	
	public boolean isEnabled() {
		return enabled;
	}
}
