package com.qqviaja.plugins

import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*
import com.qqviaja.plugins.GRBundle.message
import javax.swing.JLabel

/**
 *
 * <p>Create on 2022/4/1.</p>
 * @author Kimi Chen
 */
class GoldenRadioConfigurable : BoundSearchableConfigurable(
    message("configurable.golden.radio.configurable.display.name"),
    GRConstants.GOLDEN_RADIO_ID
) {

    private val grSettings = GRSettingsHolder.GRSettings.getSettings()

    override fun createPanel(): DialogPanel {
        return panel {
            row(message("settings.golden.radio.name")) {}.layout(RowLayout.PARENT_GRID)

            row {
                label(message("settings.proportion.name"))
                slider(0, GRSettingsHolder.CONTEXT_RANGE_MODES.size - 1, 1, 1)
                    .labelTable(
                        GRSettingsHolder.CONTEXT_RANGE_MODES.mapIndexed { index: Int, _: Float ->
                            index to JLabel(GRSettingsHolder.CONTEXT_RANGE_MODE_LABELS[index])
                        }.toMap()
                    ).bindValue(({
                        GRSettingsHolder.CONTEXT_RANGE_MODES.indexOfFirst { it == grSettings.proportion }
                            .coerceAtLeast(0)
                    }), ({ grSettings.proportion = GRSettingsHolder.CONTEXT_RANGE_MODES[it] }))
            }
            row {
                checkBox(
                    message("settings.switch.tabs.between.golden.radio.default.max")
                ).bindSelected(grSettings::switchTabsBetweenGrDefaultMax)
            }
            row {
                checkBox(
                    message("settings.auto.toggle")
                ).bindSelected(grSettings::autoToggle)
            }
        }
    }
}