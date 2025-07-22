package com.qqviaja.plugins.util

import com.qqviaja.plugins.GoldenRadioBundle

/**
 * Constants used throughout the Golden Radio plugin.
 * 
 * This class centralizes all constant values used by the plugin to ensure
 * consistency and make maintenance easier. Constants include configuration
 * identifiers, file names, and other fixed values.
 *
 * <p>Create on 2023/8/27.</p>
 * @author Kimi Chen
 */
object GoldenRadioConstants {
    /**
     * The unique identifier for the Golden Radio plugin's configurable component.
     * Used in the plugin.xml to reference the configurable.
     */
    const val GOLDEN_RADIO_ID = "golden.radio.id"
    
    /**
     * The filename for storing Golden Radio plugin settings.
     * Used in the @State annotation for the settings holder.
     */
    const val GOLDEN_RADIO_CONFIG = "GoldenRadioSettings.xml"
    
    /**
     * The filename for storing Golden Radio plugin license information.
     * Used in the @State annotation for the license manager.
     */
    const val GOLDEN_RADIO_LICENSE_CONFIG = "GoldenRadioLicense.xml"
    
    /**
     * The unique identifier for the Golden Radio plugin's license configurable component.
     * Used in the plugin.xml to reference the license configurable.
     */
    const val GOLDEN_RADIO_LICENSE_ID = "golden.radio.license.id"
    
    /**
     * Available proportion values for the golden ratio settings.
     * The last value (-1) represents the disabled state.
     */
    val PROPORTION_VALUES = floatArrayOf(0.5f, 0.618f, 0.7f, 0.8f, 0.9f, -1f)

    /**
     * Labels for the proportion values displayed in the UI.
     * The last label is for the disabled state.
     */
    val PROPORTION_LABELS = arrayOf(
        ".5",
        ".618",
        ".7",
        ".8",
        ".9",
        GoldenRadioBundle.message("configurable.golden.radio.ranges.disable")
    )
}