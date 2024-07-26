package com.qqviaja.plugins

import com.intellij.ide.IdeBundle
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.ui.Splitter
import com.qqviaja.plugins.model.GoldenRadioSettingsHolder.GoldenRadioSettings.Companion.getSettings
import com.qqviaja.plugins.util.GoldenRadioUtils
import com.qqviaja.plugins.util.GoldenRadioUtils.CURRENT_STATE_IS_RADIO_KEY
import com.qqviaja.plugins.util.GoldenRadioUtils.getSplittersToNormalize

class GoldenRadioAction : DumbAwareAction(
    GoldenRadioBundle.message("action.golden.radio.editor") + "/" +
            IdeBundle.message("action.normalize.splits")
) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val settingsProportion = getSettings().proportion
        val negatedSettingsProportion = 1 - settingsProportion
        val splittersToGoldenRadio = getSplittersToGoldenRadio(e)
        e.project?.let {

            if (splittersToGoldenRadio.isEmpty()) {
                getSplittersToNormalize(e).forEach { it.proportion = 0.5f }
            } else {
                splittersToGoldenRadio.forEach { (splitter, isFirst) ->
                    splitter.proportion = if (isFirst) settingsProportion else negatedSettingsProportion
                }
            }
        }
    }

    override fun update(e: AnActionEvent) {
        val presentation = e.presentation
        presentation.isEnabled = true
        val goldenRadioAction = GoldenRadioBundle.message("action.golden.radio.editor")
        val normalizeSplitsAction = IdeBundle.message("action.normalize.splits")
        val splittersToGoldenRadio = getSplittersToGoldenRadio(e)

        when {
            splittersToGoldenRadio.isNotEmpty() -> {
                presentation.text = goldenRadioAction
                presentation.putClientProperty(CURRENT_STATE_IS_RADIO_KEY, false)
            }

            getSplittersToNormalize(e).isNotEmpty() -> {
                presentation.text = normalizeSplitsAction
                presentation.putClientProperty(CURRENT_STATE_IS_RADIO_KEY, true)
            }

            else -> presentation.isEnabled = false
        }
    }

    private fun getSplittersToGoldenRadio(e: AnActionEvent): Set<Pair<Splitter, Boolean>> {
        val project = e.project
        val editor = e.getData(CommonDataKeys.HOST_EDITOR)

        return if (project != null && editor != null) {
            GoldenRadioUtils.getSplittersToGoldenRadio(project, editor)
        } else {
            emptySet()
        }
    }
}