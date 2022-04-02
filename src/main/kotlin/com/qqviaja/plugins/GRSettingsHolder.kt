package com.qqviaja.plugins

import com.intellij.openapi.components.*
import com.intellij.openapi.util.Key
import com.intellij.util.xmlb.annotations.OptionTag
import com.intellij.util.xmlb.annotations.XMap

/**
 *
 * <p>Create on 2022/4/1.</p>
 * @author Kimi Chen
 */
@State(
    name = "GRSettingsHolder",
    storages = [Storage(GRUtil.GR_CONFIG)],
    category = SettingsCategory.CODE
)
class GRSettingsHolder : PersistentStateComponent<GRSettingsHolder.State> {

    companion object {
        @JvmField
        val CONTEXT_RANGE_MODES: FloatArray = floatArrayOf(0.5f, 0.618f, 0.7f, 0.8f, 0.9f, -1f)

        @JvmField
        val CONTEXT_RANGE_MODE_LABELS: Array<String> = arrayOf(
            ".5",
            ".618",
            ".7",
            ".8",
            ".9",
            GRBundle.message("configurable.golden.radio.ranges.disable")
        )
    }

    data class SharedSettings(
        // Fragments settings
        var PROPORTION: Float = 0.618f,
        var SWITCH_TABS_BETWEEN_GR_DEFAULT_MAX: Boolean = false
    )


    class GRSettings internal constructor(
        private val SHARED_SETTINGS: SharedSettings,
    ) {
        constructor() : this(SharedSettings())

//        fun addListener(listener: Listener, disposable: Disposable) {
//            PLACE_SETTINGS.eventDispatcher.addListener(listener, disposable)
//        }

        var switchTabsBetweenGrDefaultMax: Boolean
            get() = SHARED_SETTINGS.SWITCH_TABS_BETWEEN_GR_DEFAULT_MAX
            set(value) {
                SHARED_SETTINGS.SWITCH_TABS_BETWEEN_GR_DEFAULT_MAX = value
            }

        var proportion: Float
            get() = SHARED_SETTINGS.PROPORTION
            set(value) {
                SHARED_SETTINGS.PROPORTION = value
            }

        //
        // Impl
        //

        companion object {
            @JvmField
            val KEY: Key<GRSettings> = Key.create("GRSettings")

            @JvmStatic
            fun getSettings(): GRSettings = service<GRSettingsHolder>().getSettings()


            internal fun getDefaultSettings(): GRSettings = GRSettings(SharedSettings())
        }
    }

    fun getSettings(): GRSettings {
        return GRSettings(myState.sharedSettings)
    }

    private fun copyStateWithoutDefaults(): State {
        val result = State()
        result.sharedSettings = myState.sharedSettings
        return result
    }


    class State {
        @OptionTag
        @XMap
        @JvmField
        var sharedSettings: SharedSettings = SharedSettings()
    }

    private var myState: State = State()

    override fun getState(): State {
        return copyStateWithoutDefaults()
    }

    override fun loadState(state: State) {
        myState = state
    }
}