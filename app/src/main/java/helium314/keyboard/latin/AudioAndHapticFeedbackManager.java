/*
 * Copyright (C) 2012 The Android Open Source Project
 * modified
 * SPDX-License-Identifier: Apache-2.0 AND GPL-3.0-only
 */

package helium314.keyboard.latin;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.View;

import helium314.keyboard.event.HapticEvent;
import helium314.keyboard.keyboard.internal.keyboard_parser.floris.KeyCode;
import helium314.keyboard.latin.common.Constants;
import helium314.keyboard.latin.settings.SettingsValues;

/**
 * This class gathers audio feedback and haptic feedback functions.
 * <p>
 * It offers a consistent and simple interface that allows LatinIME to forget about the
 * complexity of settings and the like.
 */
public final class AudioAndHapticFeedbackManager {
    private AudioManager mAudioManager;
    private Vibrator mVibrator;
    private SoundPool mSoundPool;
    private int mSoundStandard = -1;
    private int mSoundDelete = -1;
    private int mSoundReturn = -1;
    private int mSoundSpacebar = -1;

    private SettingsValues mSettingsValues;
    private boolean mSoundOn;
    private boolean mDoNotDisturb;

    private static final AudioAndHapticFeedbackManager sInstance =
            new AudioAndHapticFeedbackManager();

    public static AudioAndHapticFeedbackManager getInstance() {
        return sInstance;
    }

    private AudioAndHapticFeedbackManager() {
        // Intentional empty constructor for singleton.
    }

    public static void init(final Context context) {
        sInstance.initInternal(context);
    }

    private void initInternal(final Context context) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        initSoundPool(context);
    }

    private void initSoundPool(final Context context) {
        if (mSoundPool != null) {
            mSoundPool.release();
        }
        final AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(attrs)
                .build();

        final int fallbackSoundId = loadSound(context, "audio");
        mSoundStandard = loadSound(context, "audio_standard");
        if (mSoundStandard == -1) mSoundStandard = fallbackSoundId;
        mSoundDelete = loadSound(context, "audio_delete");
        if (mSoundDelete == -1) mSoundDelete = fallbackSoundId;
        mSoundReturn = loadSound(context, "audio_return");
        if (mSoundReturn == -1) mSoundReturn = fallbackSoundId;
        mSoundSpacebar = loadSound(context, "audio_spacebar");
        if (mSoundSpacebar == -1) mSoundSpacebar = fallbackSoundId;
    }

    private int loadSound(final Context context, final String name) {
        final int resId = context.getResources().getIdentifier(name, "raw", context.getPackageName());
        if (resId != 0) {
            return mSoundPool.load(context, resId, 1);
        }
        return -1;
    }

    public void release() {
        if (mSoundPool != null) {
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    public void performHapticAndAudioFeedback(
        final int code,
        final View viewToPerformHapticFeedbackOn,
        final HapticEvent hapticEvent
    ) {
        performHapticFeedback(viewToPerformHapticFeedbackOn, hapticEvent);
        performAudioFeedback(code, hapticEvent);
    }

    public boolean hasVibrator() {
        return mVibrator != null && mVibrator.hasVibrator();
    }

    public void vibrate(final long milliseconds) {
        if (mVibrator == null || milliseconds <= 0) {
            return;
        }
        mVibrator.vibrate(milliseconds);
    }

    private boolean reevaluateIfSoundIsOn() {
        if (mSettingsValues == null || !mSettingsValues.mSoundOn || mAudioManager == null || mDoNotDisturb) {
            return false;
        }
        return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    public void performAudioFeedback(final int code, final HapticEvent hapticEvent) {
        // if mAudioManager is null, we can't play a sound anyway, so return
        if (mAudioManager == null) {
            return;
        }
        if (!mSoundOn) {
            return;
        }
        if (hapticEvent != HapticEvent.KEY_PRESS) {
            return;
        }

        final int customSoundId = switch (code) {
            case KeyCode.DELETE -> mSoundDelete;
            case Constants.CODE_ENTER -> mSoundReturn;
            case Constants.CODE_SPACE -> mSoundSpacebar;
            default -> mSoundStandard;
        };

        if (customSoundId != -1 && mSoundPool != null) {
            float volume = mSettingsValues.mKeypressSoundVolume;
            if (volume < 0) volume = 1.0f; // Default volume for SoundPool if not set
            mSoundPool.play(customSoundId, volume, volume, 1, 0, 1.0f);
        } else {
            final int sound = switch (code) {
                case KeyCode.DELETE -> AudioManager.FX_KEYPRESS_DELETE;
                case Constants.CODE_ENTER -> AudioManager.FX_KEYPRESS_RETURN;
                case Constants.CODE_SPACE -> AudioManager.FX_KEYPRESS_SPACEBAR;
                default -> AudioManager.FX_KEYPRESS_STANDARD;
            };
            mAudioManager.playSoundEffect(sound, mSettingsValues.mKeypressSoundVolume);
        }
    }

    public void performHapticFeedback(final View viewToPerformHapticFeedbackOn, final HapticEvent hapticEvent) {
        if (!mSettingsValues.mVibrateOn || (mDoNotDisturb && !mSettingsValues.mVibrateInDndMode)) {
            return;
        }
        if (hapticEvent == HapticEvent.NO_HAPTICS) {
            // Avoid surprises with the handling of HapticFeedbackConstants.NO_HAPTICS
            return;
        }
        if (hapticEvent.allowCustomDuration && mSettingsValues.mKeypressVibrationDuration >= 0) {
            vibrate(mSettingsValues.mKeypressVibrationDuration);
            return;
        }
        // Go ahead with the system default
        if (viewToPerformHapticFeedbackOn != null) {
            viewToPerformHapticFeedbackOn.performHapticFeedback(
                    hapticEvent.feedbackConstant,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        }
    }

    public void onSettingsChanged(final SettingsValues settingsValues) {
        mSettingsValues = settingsValues;
        mSoundOn = reevaluateIfSoundIsOn();
    }

    public void onRingerModeChanged(boolean doNotDisturb) {
        mDoNotDisturb = doNotDisturb;
        mSoundOn = reevaluateIfSoundIsOn();
    }
}
