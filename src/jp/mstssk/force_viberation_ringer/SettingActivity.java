package jp.mstssk.force_viberation_ringer;

import android.app.Activity;
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
		final PackageController packageController = new PackageController(this);
		this.forceViberation.setChecked(packageController.isReceiverStateEnabled(VolumeChangedReceiver_.class));
		this.forceViberation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(final CompoundButton buttonView, final boolean checked) {
				packageController.setReceiverStateEnabled(VolumeChangedReceiver_.class, checked);
			}
		});
	}
}
