package jp.mstssk.force_viberation_ringer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.googlecode.androidannotations.annotations.EReceiver;
import com.googlecode.androidannotations.annotations.SystemService;

/**
 * Receiver for "android.media.RINGER_MODE_CHANGED".
 * 
 * @author mstssk
 * 
 */
@EReceiver
public class VolumeChangedReceiver extends BroadcastReceiver {

	@SystemService
	AudioManager audioManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		forceViberation();
	}

	/**
	 * Set to VIVERATE ringer mode forces, if SILENT.
	 */
	private void forceViberation() {
		if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
	}
}
