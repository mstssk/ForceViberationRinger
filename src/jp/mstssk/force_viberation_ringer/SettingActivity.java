package jp.mstssk.force_viberation_ringer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * Setting screen.
 * 
 * @author mstssk
 * 
 */
@EActivity(R.layout.setting)
public class SettingActivity extends Activity {

	@ViewById(R.id.forceViberation)
	CheckBox forceViberation;

	@AfterViews
	void setup() {
		forceViberation.setChecked(isVolumeChangedReceiverEnabled());
		forceViberation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(final CompoundButton buttonView, final boolean checked) {
				swichVolumeChangedReceiverEnabled(checked);
			}
		});
	}

	private boolean isVolumeChangedReceiverEnabled() {
		ComponentName name = new ComponentName(this, VolumeChangedReceiver_.class);
		int enabledSetting = getPackageManager().getComponentEnabledSetting(name);
		return PackageManager.COMPONENT_ENABLED_STATE_ENABLED == enabledSetting;
	}

	private void swichVolumeChangedReceiverEnabled(final boolean enabled) {
		int enabledFlag = enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
				: PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
		ComponentName name = new ComponentName(this, VolumeChangedReceiver_.class);
		getPackageManager().setComponentEnabledSetting(name, enabledFlag, PackageManager.DONT_KILL_APP);
	}
}
