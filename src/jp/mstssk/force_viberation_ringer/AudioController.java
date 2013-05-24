package jp.mstssk.force_viberation_ringer;

import android.media.AudioManager;

/**
 * Controll {@link AudioManager}.
 * 
 * @author mstssk
 * 
 */
public class AudioController {

	public enum RingerMode {
		SILENT(AudioManager.RINGER_MODE_SILENT), // silent
		VIBERATE(AudioManager.RINGER_MODE_VIBRATE), // viberation
		NORMAL(AudioManager.RINGER_MODE_NORMAL); // normal

		private final int value;

		private RingerMode(final int value) {
			this.value = value;
		}

		private static RingerMode get(final int value) {
			for (final RingerMode ringerMode : RingerMode.values()) {
				if (ringerMode.value == value) {
					return ringerMode;
				}
			}
			throw new IllegalStateException("Unknown ringer mode: " + Integer.toHexString(value));
		}
	}

	final private AudioManager manager;

	public AudioController(final AudioManager audioManager) {
		this.manager = audioManager;
	}

	public RingerMode getRingerMode() {
		return RingerMode.get(this.manager.getRingerMode());
	}

	public void setRingerMode(final RingerMode ringerMode) {
		this.manager.setRingerMode(ringerMode.value);
	}

	/**
	 * Set to VIVERATE ringer mode forces, if SILENT.
	 */
	public void forceViberation() {
		if (this.getRingerMode() == RingerMode.SILENT) {
			this.setRingerMode(RingerMode.VIBERATE);
		}
	}
}
