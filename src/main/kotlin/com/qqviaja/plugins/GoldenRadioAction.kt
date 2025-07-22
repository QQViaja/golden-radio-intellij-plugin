package com.qqviaja.plugins

import com.intellij.ide.IdeBundle
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.DumbAwareAction
import com.qqviaja.plugins.util.GoldenRadioUtils.applyGoldenRatioToSplitters

/**
 * Action that applies the golden ratio to editor splitters.
 * 
 * This action can be triggered manually by the user to adjust the editor splitters
 * according to the golden ratio settings. It's available in the editor tabs group
 * and can be invoked via a keyboard shortcut.
 *
 * <p>Create on 2022/10/7.</p>
 * @author Kimi Chen
 */
class GoldenRadioAction : DumbAwareAction(
    GoldenRadioBundle.message("action.golden.radio.editor") + "/" +
            IdeBundle.message("action.normalize.splits")
) {
    /**
     * Specifies that this action should be executed on the EDT thread.
     * 
     * @return The thread on which the action update should be performed
     */
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }

    /**
     * Applies the golden ratio to the selected editor's splitters.
     * 
     * @param e The action event containing context information
     */
    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let { project ->
            val editor = FileEditorManager.getInstance(project).selectedTextEditor
            if (editor != null) {
                applyGoldenRatioToSplitters(editor)
            }
        }
    }
}
