package jp.mstssk.force_viberation_ringer;

import jp.mstssk.force_viberation_ringer.AudioController.RingerMode;
import android.app.Activity;
import android.media.AudioManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * Setting screen.
 * 
 * @author mstssk
 * 
 */
@EActivity(R.layout.setting)
public class SettingActivity extends Activity {

	@SystemService
	AudioManager audioManager;
	private AudioController audioController;
	private PackageController packageController;

	@ViewById(R.id.forceViberation)
	CheckBox forceViberation;

	@AfterViews
	void setupInjecties() {
		this.audioController = new AudioController(this.audioManager);
		this.packageController = new PackageController(this);
	}

	@AfterViews
	void setupCheckbox() {
		this.forceViberation.setChecked(this.packageController.isReceiverStateEnabled(VolumeChangedReceiver_.class));
		this.forceViberation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(final CompoundButton buttonView, final boolean checked) {
				SettingActivity.this.switchForceViberationEnabled(checked);
			}
		});
	}

	private void switchForceViberationEnabled(final boolean enabled) {
		this.packageController.setReceiverStateEnabled(VolumeChangedReceiver_.class, enabled);

		// Switch on Viberation, when the mode is Silent.
		if (this.audioController.getRingerMode() == RingerMode.SILENT) {
			this.audioController.forceViberation();
		}
	}
}
