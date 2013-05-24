package jp.mstssk.force_viberation_ringer;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Controll {@link PackageManager}
 * 
 * @author mstssk
 * 
 */
public class PackageController {

	private final Context context;
	private final PackageManager manager;

	public PackageController(final Context context) {
		this.context = context;
		this.manager = context.getPackageManager();
	}

	public boolean isReceiverStateEnabled(final Class<? extends BroadcastReceiver> klass) {
		return this.isComponentStateEnabled(klass);
	}

	public void setReceiverStateEnabled(final Class<? extends BroadcastReceiver> klass, final boolean enabled) {
		this.setComponentStateEnabled(klass, enabled);
	}

	private boolean isComponentStateEnabled(final Class<?> klass) {
		final ComponentName name = new ComponentName(this.context, klass);
		final int state = this.manager.getComponentEnabledSetting(name);
		return PackageManager.COMPONENT_ENABLED_STATE_ENABLED == state;
	}

	private void setComponentStateEnabled(final Class<?> klass, final boolean enabled) {
		final int state = enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
				: PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
		final ComponentName name = new ComponentName(this.context, klass);
		this.manager.setComponentEnabledSetting(name, state, PackageManager.DONT_KILL_APP);
	}
}
