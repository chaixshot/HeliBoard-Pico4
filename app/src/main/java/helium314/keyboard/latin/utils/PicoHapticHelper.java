package helium314.keyboard.latin.utils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.osui.utils.OSUIHelper;

/**
 * Helper class for triggering Pico VR controller haptic feedback across the keyboard UI.
 */
public final class PicoHapticHelper {

    private static int sLastDeviceId = 0;

    private PicoHapticHelper() {
        // Utility class
    }

    /**
     * Stores the last active input device ID (VR controller) that interacted with the keyboard UI.
     */
    public static void setLastDeviceId(int deviceId) {
        if (deviceId != 0) {
            sLastDeviceId = deviceId;
        }
    }

    /**
     * Returns the last active input device ID.
     */
    public static int getLastDeviceId() {
        return sLastDeviceId;
    }

    /**
     * Triggers haptic feedback on the active Pico VR controller.
     * Maps device ID: 100001/1 -> Controller 1 (Left), 100002/2 -> Controller 2 (Right), default -> Controller 2.
     *
     * @param deviceId   input device ID of the VR controller
     * @param isHover    true for light hover feedback, false for key press feedback
     * @param durationMs duration of the vibration pulse in milliseconds
     */
    public static void triggerHaptic(int deviceId, boolean isHover, float durationMs) {
        try {
            if (OSUIHelper.isLoaded) {
                if (deviceId != 0) {
                    setLastDeviceId(deviceId);
                } else {
                    deviceId = getLastDeviceId();
                }

                float amplitude = isHover ? 120f : 255f;
                float frequency = isHover ? 177f : 100f;
                float duration = isHover ? 10f : Math.max(10f, Math.min(durationMs, 100f));

                int nativeDeviceId = switch (deviceId) {
                    case 100001, 1 -> 1;
                    case 100002, 2 -> 2;
                    default -> 2; // Default to Right controller
                };

                OSUIHelper.a(nativeDeviceId, amplitude, frequency, duration);
            }
        } catch (Throwable t) {
            Log.e("PicoHaptic", "Error triggering Pico haptic", t);
        }
    }

    /**
     * Triggers Pico VR controller haptic feedback using the device ID from a MotionEvent.
     */
    public static void triggerHaptic(MotionEvent event, boolean isHover) {
        if (event != null && event.getDeviceId() != 0) {
            setLastDeviceId(event.getDeviceId());
        }
        int deviceId = event != null ? event.getDeviceId() : getLastDeviceId();
        triggerHaptic(deviceId, isHover, isHover ? 10f : 35f);
    }

    /**
     * Triggers Pico VR controller haptic feedback using the last active pointer device ID.
     */
    public static void triggerHaptic(float durationMs) {
        int deviceId = getLastDeviceId();
        triggerHaptic(deviceId, false, durationMs);
    }

    /**
     * Recursively attaches Pico VR controller hover haptic listeners to a view hierarchy.
     */
    public static void setupHoverHapticsRecursively(View view) {
        if (view == null) return;
        view.setOnHoverListener((v, event) -> {
            setLastDeviceId(event.getDeviceId());
            if (event.getActionMasked() == MotionEvent.ACTION_HOVER_ENTER) {
                triggerHaptic(event, true);
            }
            return false;
        });
        if (view instanceof ViewGroup group) {
            for (int i = 0; i < group.getChildCount(); i++) {
                setupHoverHapticsRecursively(group.getChildAt(i));
            }
        }
    }
}
