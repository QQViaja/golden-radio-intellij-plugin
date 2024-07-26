package com.qqviaja.plugins.model

import com.intellij.openapi.components.*
import com.intellij.openapi.util.Key
import com.intellij.util.xmlb.annotations.OptionTag
import com.intellij.util.xmlb.annotations.XMap
import com.qqviaja.plugins.GoldenRadioBundle
import com.qqviaja.plugins.util.GoldenRadioUtils

/**
 *
 * <p>Create on 2022/4/1.</p>
 * @author Kimi Chen
 */
@State(
    name = "GoldenRadioSettingsHolder",
    storages = [Storage(GoldenRadioUtils.GOLDEN_RADIO_CONFIG)],
    category = SettingsCategory.CODE
)
class GoldenRadioSettingsHolder : PersistentStateComponent<GoldenRadioSettingsHolder.State> {

    companion object {

        val contextRangeModes = floatArrayOf(0.5f, 0.618f, 0.7f, 0.8f, 0.9f, -1f)

        val contextRangeModeLabels = arrayOf(
            ".5",
            ".618",
            ".7",
            ".8",
            ".9",
            GoldenRadioBundle.message("configurable.golden.radio.ranges.disable")
        )
    }

    data class SharedSettings(
        // Fragments settings
        var proportion: Float = 0.618f,
        var switchTabsBetweenGrDefaultMax: Boolean = false,
        var autoToggle: Boolean = true
    )

    data class State(
        @OptionTag @XMap var sharedSettings: SharedSettings = SharedSettings()
    )

    private var myState: State = State()

    override fun getState(): State = myState.copy()

    override fun loadState(state: State) {
        myState = state
    }

    fun getSettings(): GoldenRadioSettings = GoldenRadioSettings(myState.sharedSettings)


    class GoldenRadioSettings internal constructor(
        private val sharedSettings: SharedSettings,
    ) {
        constructor() : this(SharedSettings())

        var switchTabsBetweenGrDefaultMax: Boolean
            get() = sharedSettings.switchTabsBetweenGrDefaultMax
            set(value) {
                sharedSettings.switchTabsBetweenGrDefaultMax = value
            }

        var autoToggle: Boolean
            get() = sharedSettings.autoToggle
            set(value) {
                sharedSettings.autoToggle = value
            }

        var proportion: Float
            get() = sharedSettings.proportion
            set(value) {
                sharedSettings.proportion = value
            }

        companion object {
            val KEY: Key<GoldenRadioSettings> = Key.create("GoldenRadioSettings")

            @JvmStatic
            fun getSettings(): GoldenRadioSettings = service<GoldenRadioSettingsHolder>().getSettings()

            internal fun getDefaultSettings(): GoldenRadioSettings = GoldenRadioSettings(SharedSettings())
        }
    }

}