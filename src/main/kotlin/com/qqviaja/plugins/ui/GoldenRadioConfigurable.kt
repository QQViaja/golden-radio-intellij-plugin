package com.qqviaja.plugins.ui

import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindValue
import com.intellij.ui.dsl.builder.labelTable
import com.intellij.ui.dsl.builder.panel
import com.qqviaja.plugins.GoldenRadioBundle.message
import com.qqviaja.plugins.util.GoldenRadioConstants
import com.qqviaja.plugins.model.GoldenRadioSettingsHolder
import javax.swing.JLabel

/**
 *
 * <p>Create on 2022/4/1.</p>
 * @author Kimi Chen
 */
class GoldenRadioConfigurable : BoundSearchableConfigurable(
    message("configurable.golden.radio.configurable.display.name"),
    GoldenRadioConstants.GOLDEN_RADIO_ID
) {

    private val goldenRadioSettings = GoldenRadioSettingsHolder.GoldenRadioSettings.getSettings()

    override fun createPanel(): DialogPanel = panel {
        group(message("settings.golden.radio.name")) {
            row {
                label(message("settings.proportion.name"))
                slider(0, GoldenRadioSettingsHolder.contextRangeModes.size - 1, 1, 1)
                    .labelTable(
                        GoldenRadioSettingsHolder.contextRangeModes.mapIndexed { index, _ ->
                            index to JLabel(GoldenRadioSettingsHolder.contextRangeModeLabels[index])
                        }.toMap()
                    ).bindValue(
                        {
                            GoldenRadioSettingsHolder.contextRangeModes.indexOfFirst { it == goldenRadioSettings.proportion }
                                .coerceAtLeast(0)
                        },
                        { goldenRadioSettings.proportion = GoldenRadioSettingsHolder.contextRangeModes[it] }
                    )
            }
            row {
                checkBox(message("settings.switch.tabs.between.golden.radio.default.max"))
                    .bindSelected(goldenRadioSettings::switchTabsBetweenGrDefaultMax)
            }
            row {
                checkBox(message("settings.auto.toggle"))
                    .bindSelected(goldenRadioSettings::autoToggle)
            }
        }
    }
}