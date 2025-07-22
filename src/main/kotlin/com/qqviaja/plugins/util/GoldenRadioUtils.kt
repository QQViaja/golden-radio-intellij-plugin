package com.qqviaja.plugins.util

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Splitter
import com.qqviaja.plugins.model.GoldenRadioSettingsHolder.GoldenRadioSettings.Companion.getSettings
import javax.swing.JComponent


/**
 * Utility class for applying golden ratio to editor splitters.
 * 
 * Provides functionality to adjust the proportions of editor splitters according to the
 * golden ratio settings. The golden ratio (approximately 1.618:1) is considered aesthetically
 * pleasing and can improve the visual layout of the editor panels.
 *
 * <p>Create on 2023/8/27.</p>
 * @author Kimi Chen
 */
object GoldenRadioUtils {

    private val LOG = Logger.getInstance(GoldenRadioUtils::class.java)

    /**
     * Applies the golden ratio to all splitters in the component hierarchy of the editor.
     * 
     * This method starts from the editor component and traverses up the component hierarchy
     * to find and adjust all splitters according to the golden ratio settings.
     *
     * @param editor The editor whose splitters should be adjusted
     */
    fun applyGoldenRatioToSplitters(editor: Editor) {
        LOG.debug("Starting to apply golden ratio to editor components for ${editor.document}")
        findAndAdjustSplitter(editor.component, editor.component.parent as? JComponent)
    }

    /**
     * Recursively finds and adjusts splitters in the component hierarchy.
     * 
     * @param self The component to check if it's part of a splitter
     * @param component The parent component to check if it's a splitter
     */
    private tailrec fun findAndAdjustSplitter(self: JComponent, component: JComponent?) {
        if (component == null) return
        if (component is Splitter) adjustSplitterProportion(self, component)
        findAndAdjustSplitter(component, component.parent as? JComponent)
    }

    /**
     * Adjusts the proportion of a splitter according to the golden ratio settings.
     * 
     * The proportion is set based on whether the component is the first or second component
     * of the splitter. If it's the first component, the proportion is set to the configured
     * value; if it's the second, it's set to (1 - configured value).
     *
     * @param self The component that is part of the splitter
     * @param splitter The splitter to adjust
     */
    private fun adjustSplitterProportion(self: JComponent, splitter: Splitter) {
        val currentProportion = splitter.proportion
        val settingsProportion = getSettings().proportion

        val newProportion = if (self == splitter.firstComponent) settingsProportion else 1 - settingsProportion

        splitter.proportion = newProportion
        LOG.debug("Adjusting <${splitter.firstComponent}, ${splitter.secondComponent}> proportion from $currentProportion to $newProportion for ${splitter.javaClass.simpleName}")
    }

}