package com.qqviaja.plugins.model

import com.intellij.openapi.components.*
import com.intellij.openapi.util.Key
import com.intellij.util.xmlb.annotations.OptionTag
import com.intellij.util.xmlb.annotations.XMap
import com.qqviaja.plugins.util.GoldenRadioConstants

/**
 * Persistent settings holder for the Golden Radio plugin.
 * 
 * This class is responsible for storing and retrieving the plugin settings
 * using IntelliJ's persistence framework. It implements PersistentStateComponent
 * to allow the IDE to automatically save and load settings.
 *
 * <p>Create on 2022/4/1.</p>
 * @author Kimi Chen
 */
@State(
    name = "GoldenRadioSettingsHolder",
    storages = [Storage(GoldenRadioConstants.GOLDEN_RADIO_CONFIG)],
    category = SettingsCategory.CODE
)
class GoldenRadioSettingsHolder : PersistentStateComponent<GoldenRadioSettingsHolder.State> {

    companion object {

        val contextRangeModes = GoldenRadioConstants.PROPORTION_VALUES

        val contextRangeModeLabels = GoldenRadioConstants.PROPORTION_LABELS
    }

    /**
     * Data class representing shared settings for the Golden Radio plugin.
     * 
     * @property proportion The proportion value for the golden ratio (default is 0.618f)
     * @property switchTabsBetweenGrDefaultMax Whether to switch tabs between golden ratio and default max
     * @property autoToggle Whether to automatically apply golden ratio when switching tabs
     */
    data class SharedSettings(
        // Fragments settings
        var proportion: Float = 0.618f,
        var switchTabsBetweenGrDefaultMax: Boolean = false,
        var autoToggle: Boolean = true
    )

    /**
     * State class for persisting settings.
     * 
     * @property sharedSettings The shared settings for the plugin
     */
    data class State(
        @OptionTag @XMap var sharedSettings: SharedSettings = SharedSettings()
    )

    private var myState: State = State()

    override fun getState(): State = myState.copy()

    override fun loadState(state: State) {
        myState = state
    }

    /**
     * Returns the current Golden Radio settings.
     * 
     * @return A GoldenRadioSettings instance with the current settings
     */
    fun getSettings(): GoldenRadioSettings = GoldenRadioSettings(myState.sharedSettings)

    /**
     * Settings class for the Golden Radio plugin.
     * 
     * This class provides a convenient API for accessing and modifying
     * the plugin settings.
     */
    class GoldenRadioSettings internal constructor(
        private val sharedSettings: SharedSettings,
    ) {
        constructor() : this(SharedSettings())

        /**
         * Whether to switch tabs between golden ratio and default max.
         */
        var switchTabsBetweenGrDefaultMax: Boolean
            get() = sharedSettings.switchTabsBetweenGrDefaultMax
            set(value) {
                sharedSettings.switchTabsBetweenGrDefaultMax = value
            }

        /**
         * Whether to automatically apply golden ratio when switching tabs.
         */
        var autoToggle: Boolean
            get() = sharedSettings.autoToggle
            set(value) {
                sharedSettings.autoToggle = value
            }

        /**
         * The proportion value for the golden ratio.
         */
        var proportion: Float
            get() = sharedSettings.proportion
            set(value) {
                sharedSettings.proportion = value
            }

        companion object {
            val KEY: Key<GoldenRadioSettings> = Key.create("GoldenRadioSettings")

            /**
             * Get the current Golden Radio settings.
             * 
             * @return The current Golden Radio settings
             */
            @JvmStatic
            fun getSettings(): GoldenRadioSettings = service<GoldenRadioSettingsHolder>().getSettings()

            /**
             * Get the default Golden Radio settings.
             * 
             * @return The default Golden Radio settings
             */
            internal fun getDefaultSettings(): GoldenRadioSettings = GoldenRadioSettings(SharedSettings())
        }
    }

}