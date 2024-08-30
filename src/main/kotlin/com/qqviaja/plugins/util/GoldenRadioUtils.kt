package com.qqviaja.plugins.util

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Splitter
import com.qqviaja.plugins.model.GoldenRadioSettingsHolder.GoldenRadioSettings.Companion.getSettings
import javax.swing.JComponent


/**
 *
 * <p>Create on 2023/8/27.</p>
 * @author Kimi Chen
 */
object GoldenRadioUtils {

    const val GOLDEN_RADIO_CONFIG = "GoldenRadioSettings.xml"

    fun applyGoldenRatioToSplitters(editor: Editor) {
        tailrec fun findAndAdjustSplitter(self: JComponent, component: JComponent?) {
            if (component == null) return
            if (component is Splitter) adjustSplitterProportion(self, component)
            findAndAdjustSplitter(component, component.parent as? JComponent)
        }
        println("Starting to apply golden ratio to editor components for ${editor.document}")
        findAndAdjustSplitter(editor.component, editor.component.parent as? JComponent)
    }

    private fun adjustSplitterProportion(self: JComponent, splitter: Splitter) {
        val currentProportion = splitter.proportion
        val settingsProportion = getSettings().proportion

        val newProportion = if (self == splitter.firstComponent) settingsProportion else 1 - settingsProportion

        splitter.proportion = newProportion
        println("Adjusting <${splitter.firstComponent}, ${splitter.secondComponent}> proportion from $currentProportion to $newProportion for ${splitter.javaClass.simpleName}")

    }

}