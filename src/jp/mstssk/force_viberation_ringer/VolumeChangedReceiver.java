package jp.mstssk.force_viberation_ringer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.googlecode.androidannotations.annotations.AfterInject;
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
	private AudioController audioController;

	@AfterInject
	void setup() {
		this.audioController = new AudioController(this.audioManager);
	}

	@Override
	public void onReceive(final Context context, final Intent intent) {
		this.audioController.forceViberation();
	}
}
