package com.qqviaja.plugins

import com.intellij.ide.IdeBundle
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.DumbAwareAction
import com.qqviaja.plugins.util.GoldenRadioUtils.applyGoldenRatioToSplitters

class GoldenRadioAction : DumbAwareAction(
    GoldenRadioBundle.message("action.golden.radio.editor") + "/" +
            IdeBundle.message("action.normalize.splits")
) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }

    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let { project ->
            val editor = FileEditorManager.getInstance(project).selectedTextEditor
            if (editor != null) {
                applyGoldenRatioToSplitters(editor)
            }
        }
    }
}

