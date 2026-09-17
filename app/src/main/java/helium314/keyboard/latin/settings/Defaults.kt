package helium314.keyboard.latin.settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import helium314.keyboard.keyboard.KeyboardActionListener
import helium314.keyboard.keyboard.KeyboardTheme
import helium314.keyboard.latin.BuildConfig
import helium314.keyboard.latin.common.Constants.Separators
import helium314.keyboard.latin.common.Constants.Subtype.ExtraValue
import helium314.keyboard.latin.utils.LayoutType
import helium314.keyboard.latin.utils.POPUP_KEYS_LABEL_DEFAULT
import helium314.keyboard.latin.utils.POPUP_KEYS_ORDER_DEFAULT
import helium314.keyboard.latin.utils.defaultClipboardToolbarPref
import helium314.keyboard.latin.utils.defaultPinnedToolbarPref
import helium314.keyboard.latin.utils.defaultToolbarPref

object Defaults {
    fun initDynamicDefaults(context: Context) {
        PREF_GESTURE_DYNAMIC_PREVIEW_FOLLOW_SYSTEM = getTransitionAnimationScale(context) != 0.0f
        val dm = context.resources.displayMetrics
        val px600 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600f, dm)
        PREF_POPUP_ON = dm.widthPixels >= px600 || dm.heightPixels >= px600
    }

    // must correspond to a file name
    val LayoutType.default get() = when (this) {
        LayoutType.MAIN -> "qwerty"
        LayoutType.SYMBOLS -> "symbols"
        LayoutType.MORE_SYMBOLS -> "symbols_shifted"
        LayoutType.FUNCTIONAL -> if (Settings.getInstance().isTablet) "functional_keys_tablet" else "functional_keys"
        LayoutType.NUMBER -> "number"
        LayoutType.NUMBER_ROW -> "number_row"
        LayoutType.NUMPAD -> "numpad"
        LayoutType.NUMPAD_LANDSCAPE -> "numpad_landscape"
        LayoutType.DPAD -> "dpad"
        LayoutType.PHONE -> "phone"
        LayoutType.PHONE_SYMBOLS -> "phone_symbols"
        LayoutType.EMOJI_BOTTOM -> "emoji_bottom_row"
        LayoutType.CLIPBOARD_BOTTOM -> "clip_bottom_row"
    }

    private const val DEFAULT_SIZE_SCALE = 1.0f // 100%
    const val PREF_THEME_STYLE = KeyboardTheme.STYLE_ROUNDED
    fun PREF_ICON_STYLE(prefs: SharedPreferences) = prefs.getString(Settings.PREF_THEME_STYLE, PREF_THEME_STYLE)!!
    const val PREF_THEME_COLORS = "Pico"
    const val PREF_THEME_COLORS_NIGHT = "Pico"
    const val PREF_THEME_KEY_BORDERS = true
    @JvmField
    val PREF_THEME_DAY_NIGHT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    const val PREF_CUSTOM_ICON_NAMES = ""
    const val PREF_TOOLBAR_CUSTOM_KEY_CODES = ""
    const val PREF_AUTO_CAP = true
    const val PREF_VIBRATE_ON = false
    const val PREF_VIBRATE_IN_DND_MODE = false
    const val PREF_SOUND_ON = true
    const val PREF_SUGGEST_EMOJIS = true
    const val PREF_INLINE_EMOJI_SEARCH = true
    const val PREF_SHOW_EMOJI_DESCRIPTIONS = true
    @JvmField
    var PREF_POPUP_ON = false
    const val PREF_AUTO_CORRECTION = true
    const val PREF_MORE_AUTO_CORRECTION = true
    const val PREF_AUTO_CORRECT_CONFIDENCE = 0.24f
    const val PREF_AUTOCORRECT_SHORTCUTS = true
    const val PREF_BACKSPACE_REVERTS_AUTOCORRECT = true
    const val PREF_AUTOCORRECT_CAPITALIZED_SUGGESTION = true
    const val PREF_CENTER_SUGGESTION_TEXT_TO_ENTER = false
    const val PREF_SHOW_SUGGESTIONS = true
    const val PREF_ALWAYS_SHOW_SUGGESTIONS = false
    const val PREF_ALWAYS_SHOW_SUGGESTIONS_EXCEPT_WEB_TEXT = true
    const val PREF_KEY_USE_PERSONALIZED_DICTS = true
    const val PREF_KEY_USE_DOUBLE_SPACE_PERIOD = true
    const val PREF_BLOCK_POTENTIALLY_OFFENSIVE = false
    const val PREF_SHOW_LANGUAGE_SWITCH_KEY = true
    const val PREF_LANGUAGE_SWITCH_KEY = "internal"
    const val PREF_SHOW_EMOJI_KEY = false
    const val PREF_SHOW_DPAD_KEY = true
    const val PREF_VARIABLE_TOOLBAR_DIRECTION = true
    const val PREF_ADDITIONAL_SUBTYPES = "de${Separators.SET}${ExtraValue.KEYBOARD_LAYOUT_SET}=MAIN:qwerty${Separators.SETS}" +
            "fr${Separators.SET}${ExtraValue.KEYBOARD_LAYOUT_SET}=MAIN:qwertz${Separators.SETS}" +
            "hu${Separators.SET}${ExtraValue.KEYBOARD_LAYOUT_SET}=MAIN:qwerty"
    const val PREF_ENABLE_SPLIT_KEYBOARD = false
    @JvmField
    val PREF_SPLIT_SPACER_SCALE = Array(4) { DEFAULT_SIZE_SCALE }
    @JvmField
    val PREF_KEYBOARD_HEIGHT_SCALE = arrayOf(1.0f, 1.8f, 1.0f, 1.0f)
    @JvmField
    val PREF_BOTTOM_ROW_SCALE = Array(4) { DEFAULT_SIZE_SCALE }
    @JvmField
    // DEFAULT_SIZE_SCALE for portrait, 0 for landscape (normal and folded)
    val PREF_BOTTOM_PADDING_SCALE = arrayOf(DEFAULT_SIZE_SCALE, 0f, DEFAULT_SIZE_SCALE, 0f)
    @JvmField
    val PREF_SIDE_PADDING_SCALE = arrayOf(0f, 0.20839696f, 0f, 0f, 0f, 0f, 0f, 0f)
    @JvmField
    val PREF_KEY_GAP_SCALE = arrayOf(0.5f, 0.608067f, 1.0f, 1.0f)
    const val PREF_FONT_SCALE = DEFAULT_SIZE_SCALE
    const val PREF_HINT_FONT_SCALE = 1.1081995f
    const val PREF_EMOJI_FONT_SCALE = 0.70534354f
    const val PREF_EMOJI_KEY_FIT = true
    const val PREF_EMOJI_SKIN_TONE = ""
    @JvmField
    val PREF_SPACE_HORIZONTAL_SWIPE = KeyboardActionListener.SwipeAction.MOVE_CURSOR.name
    @JvmField
    val PREF_SPACE_VERTICAL_SWIPE = KeyboardActionListener.SwipeAction.TOGGLE_DPAD.name
    const val PREF_DELETE_SWIPE = true
    const val PREF_AUTOSPACE_AFTER_PUNCTUATION = true
    const val PREF_AUTOSPACE_AFTER_SUGGESTION = true
    const val PREF_AUTOSPACE_AFTER_GESTURE_TYPING = true
    const val PREF_AUTOSPACE_BEFORE_GESTURE_TYPING = true
    const val PREF_SHIFT_REMOVES_AUTOSPACE = false
    const val PREF_ALWAYS_INCOGNITO_MODE = false
    const val PREF_BIGRAM_PREDICTIONS = true
    const val PREF_SUGGEST_PUNCTUATION = false
    const val PREF_SUGGEST_CLIPBOARD_CONTENT = true
    const val PREF_GESTURE_INPUT = true
    const val PREF_VIBRATION_DURATION_SETTINGS = -1
    const val PREF_KEYPRESS_SOUND_VOLUME = -0.01f
    const val PREF_KEY_LONGPRESS_TIMEOUT = 255
    const val PREF_ENABLE_EMOJI_ALT_PHYSICAL_KEY = true
    const val PREF_GESTURE_PREVIEW_TRAIL = true
    const val PREF_GESTURE_FLOATING_PREVIEW_TEXT = true
    const val PREF_GESTURE_FLOATING_PREVIEW_DYNAMIC = true
    @JvmField
    var PREF_GESTURE_DYNAMIC_PREVIEW_FOLLOW_SYSTEM = true
    const val PREF_GESTURE_SPACE_AWARE = false
    const val PREF_GESTURE_FAST_TYPING_COOLDOWN = 500
    const val PREF_GESTURE_TRAIL_FADEOUT_DURATION = 800
    const val PREF_SHOW_SETUP_WIZARD_ICON = true
    const val PREF_USE_CONTACTS = false
    const val PREF_USE_APPS = false
    const val PREFS_LONG_PRESS_SYMBOLS_FOR_NUMPAD = true
    const val PREF_ONE_HANDED_MODE = false
    @SuppressLint("RtlHardcoded")
    const val PREF_ONE_HANDED_GRAVITY = Gravity.LEFT
    const val PREF_ONE_HANDED_SCALE = 2.135556f
    const val PREF_SHOW_NUMBER_ROW = true
    const val PREF_SHOW_NUMBER_ROW_IN_SYMBOLS = true
    const val PREF_LOCALIZED_NUMBER_ROW = true
    const val PREF_SHOW_NUMBER_ROW_HINTS = false
    const val PREF_CUSTOM_CURRENCY_KEY = ""
    const val PREF_SHOW_HINTS = true
    const val PREF_POPUP_KEYS_ORDER = POPUP_KEYS_ORDER_DEFAULT
    const val PREF_POPUP_KEYS_HINT_ORDER = POPUP_KEYS_LABEL_DEFAULT
    const val PREF_SHOW_POPUP_HINTS = false
    const val PREF_SHOW_TLD_POPUP_KEYS = true
    const val PREF_MORE_POPUP_KEYS = "main"
    const val PREF_SPACE_TO_CHANGE_LANG = true
    const val PREF_LANGUAGE_SWIPE_DISTANCE = 5
    const val PREF_TOUCHPAD_SENSITIVITY = 50
    const val PREF_TOUCHPAD_EDGE_SCROLL = true
    const val PREF_ENABLE_CLIPBOARD_HISTORY = true
    const val PREF_CLIPBOARD_HISTORY_RETENTION_TIME = 10 // minutes
    const val PREF_CLIPBOARD_HISTORY_PINNED_FIRST = true
    const val PREF_CLIPBOARD_USE_FILES = true
    const val PREF_CLIPBOARD_FILES_SIZE_LIMIT = 20 // megabytes
    const val PREF_ADD_TO_PERSONAL_DICTIONARY = true
    @JvmField
    val PREF_NAVBAR_COLOR = false
    const val PREF_ENABLED_SUBTYPES = "en-US§SupportTouchPositionCorrection,TrySuppressingImeSwitcher;"
    const val PREF_SELECTED_SUBTYPE = "en-US§SupportTouchPositionCorrection,TrySuppressingImeSwitcher"
    const val PREF_URL_DETECTION = true
    const val PREF_DONT_SHOW_MISSING_DICTIONARY_DIALOG = true
    const val PREF_TOOLBAR_MODE = "EXPANDABLE"
    const val PREF_TOOLBAR_HIDING_GLOBAL = true
    const val PREF_TOOLBAR_SWIPE_DOWN_TO_HIDE = true
    const val PREF_QUICK_PIN_TOOLBAR_KEYS = true
    val PREF_PINNED_TOOLBAR_KEYS = "CLIPBOARD:false|UNDO:false|VOICE:false|NUMPAD:false|DPAD:false|SETTINGS:false|SELECT_ALL:false|SELECT_WORD:false|COPY:true|REDO:false|CUT:false|PASTE:false|ONE_HANDED:false|FLOATING:false|SPLIT:false|INCOGNITO:false|AUTOCORRECT:false|CLEAR_CLIPBOARD:false|EMOJI:false|LEFT:false|RIGHT:false|UP:false|DOWN:false|WORD_LEFT:false|WORD_RIGHT:false|PAGE_UP:false|PAGE_DOWN:false|FULL_LEFT:false|FULL_RIGHT:false|PAGE_START:false|PAGE_END:false"
    val PREF_TOOLBAR_KEYS = "SETTINGS:false|VOICE:true|CLIPBOARD:false|UNDO:true|REDO:true|SELECT_WORD:false|COPY:true|PASTE:true|LEFT:true|RIGHT:true|NUMPAD:false|DPAD:false|SELECT_ALL:false|CUT:false|ONE_HANDED:false|FLOATING:false|SPLIT:false|INCOGNITO:false|AUTOCORRECT:true|CLEAR_CLIPBOARD:false|EMOJI:false|UP:false|DOWN:false|WORD_LEFT:false|WORD_RIGHT:false|PAGE_UP:false|PAGE_DOWN:false|FULL_LEFT:false|FULL_RIGHT:false|PAGE_START:false|PAGE_END:false"
    const val PREF_AUTO_SHOW_TOOLBAR = true
    const val PREF_AUTO_HIDE_TOOLBAR = true
    val PREF_CLIPBOARD_TOOLBAR_KEYS = defaultClipboardToolbarPref
    const val PREF_ABC_AFTER_EMOJI = true
    const val PREF_ABC_AFTER_CLIP = true
    const val PREF_ABC_AFTER_SYMBOL_SPACE = true
    const val PREF_ABC_AFTER_NUMPAD_SPACE = false
    const val PREF_REMOVE_REDUNDANT_POPUPS = true
    const val PREF_SPACE_BAR_TEXT = ""
    const val PREF_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val PREF_EMOJI_MAX_SDK = 36
    const val PREF_RECENT_EMOJIS = ""
    const val PREF_LAST_SHOWN_EMOJI_CATEGORY_PAGE_ID = 0
    const val PREF_SHOW_DEBUG_SETTINGS = false
    val PREF_DEBUG_MODE = BuildConfig.DEBUG
    const val PREF_SHOW_SUGGESTION_INFOS = false
    const val PREF_FORCE_NON_DISTINCT_MULTITOUCH = false
    const val PREF_SLIDING_KEY_INPUT_PREVIEW = true
    const val PREF_USER_COLORS = "[{\"name\":\"accent\",\"auto\":false,\"color\":-11645362},{\"name\":\"spacebar\",\"auto\":false,\"color\":-11645362},{\"name\":\"keys\",\"auto\":false,\"color\":-11776948},{\"name\":\"hint_text\",\"auto\":false,\"color\":-1610612737},{\"name\":\"text\",\"auto\":false,\"color\":-1},{\"name\":\"suggestion_text\",\"auto\":true,\"color\":null},{\"name\":\"spacebar_text\",\"auto\":false,\"color\":-6250336},{\"name\":\"background\",\"auto\":false,\"color\":-12763843},{\"name\":\"gesture\",\"auto\":true,\"color\":-13487566},{\"name\":\"functional_keys\",\"auto\":false,\"color\":-13355980}]"
    const val PREF_USER_MORE_COLORS = 1
    const val PREF_USER_ALL_COLORS = ""
    const val PREF_SAVE_SUBTYPE_PER_APP = true
    const val PREF_SPELLCHECK_SUGGEST = true
    const val PREF_SHOW_ONLY_TOOLBAR_WITH_HARDWARE_KEYBOARD = false
}
