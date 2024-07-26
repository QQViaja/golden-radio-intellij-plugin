package com.qqviaja.plugins.util

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.EditorsSplitters
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Splitter
import com.intellij.openapi.util.Key
import com.intellij.ui.ClientProperty
import com.intellij.ui.ComponentUtil
import com.intellij.util.ui.UIUtil
import com.qqviaja.plugins.model.GoldenRadioSettingsHolder.GoldenRadioSettings.Companion.getSettings
import java.util.stream.Collectors
import javax.swing.JComponent


/**
 *
 * <p>Create on 2023/8/27.</p>
 * @author Kimi Chen
 */
object GoldenRadioUtils {

    val CURRENT_STATE_IS_RADIO_KEY: Key<Boolean> = Key.create("CURRENT_STATE_IS_GOLDEN_RADIO")
    const val GOLDEN_RADIO_CONFIG = "GoldenRadioSettings.xml"


    fun getSplittersToGoldenRadio(project: Project, editor: Editor): Set<Pair<Splitter, Boolean>> {
        val instance =
            FileEditorManager.getInstance(project) as FileEditorManagerImpl? ?: return emptySet()
        val splitters = mutableSetOf<Pair<Splitter, Boolean>>()
        var comp = editor.component
        while (comp !== instance.mainSplitters) {
            val parent = comp.parent
            if (parent is Splitter && ClientProperty.isTrue(parent, EditorsSplitters.SPLITTER_KEY)) {
                val proportion = getSettings().proportion

                when {
                    parent.firstComponent == comp && parent.proportion != proportion && parent.proportion > 0 ->
                        splitters.add(parent to true)

                    else ->
                        splitters.add(parent to false)
                }
            }
            comp = (parent ?: break) as JComponent
        }
        return splitters;
    }

    fun getSplittersToNormalize(e: AnActionEvent): Set<Splitter> {
        val project = e.project
        val editor = e.getData(CommonDataKeys.HOST_EDITOR)
        if (project == null || editor == null) {
            return emptySet()
        }

        val set = HashSet<Splitter>()
        var splitters = ComponentUtil.getParentOfType(EditorsSplitters::class.java, editor.component)
        while (splitters != null) {
            val candidate = ComponentUtil.getParentOfType(EditorsSplitters::class.java, splitters.parent)
            if (splitters === candidate) {
                break
            }
            splitters = candidate
        }
        if (splitters != null) {
            return UIUtil.findComponentsOfType(splitters, Splitter::class.java)
                .stream().filter { splitter -> ClientProperty.isTrue(splitter, EditorsSplitters.SPLITTER_KEY) }
                .collect(Collectors.toSet())
        }
        return set
    }

}