package com.qqviaja.plugins

import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.*
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
            titledRow(message("settings.golden.radio.name")) {
                row {
                    cell(isFullWidth = true) {
                        label(message("settings.proportion.name"))
                        slider(0, GRSettingsHolder.CONTEXT_RANGE_MODES.size - 1, 1, 1)
                            .labelTable {
                                GRSettingsHolder.CONTEXT_RANGE_MODES.forEachIndexed { index, _ ->
                                    put(index, JLabel(GRSettingsHolder.CONTEXT_RANGE_MODE_LABELS[index]))
                                }
                            }
                            .withValueBinding(
                                PropertyBinding(
                                    {
                                        GRSettingsHolder.CONTEXT_RANGE_MODES.indexOfFirst { it == grSettings.proportion }
                                            .coerceAtLeast(0)
                                    },
                                    { grSettings.proportion = GRSettingsHolder.CONTEXT_RANGE_MODES[it] }
                                )
                            )
                    }
                }
                row {
                    checkBox(
                        message("settings.switch.tabs.between.golden.radio.default.max"),
                        grSettings::switchTabsBetweenGrDefaultMax
                    )
                }
            }
        }
    }
}