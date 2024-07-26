package com.qqviaja.plugins

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.ui.playback.commands.ActionCommand
import com.intellij.psi.PsiDocumentManager
import com.qqviaja.plugins.model.GoldenRadioSettingsHolder.GoldenRadioSettings.Companion.getSettings


/**
 *
 * <p>Create on 2022/10/7.</p>
 * @author Kimi Chen
 */
class GoldenRadioListener : FileEditorManagerListener {

    override fun selectionChanged(event: FileEditorManagerEvent) {
        super.selectionChanged(event)
        PsiDocumentManager.getInstance(event.manager.project)
            .performLaterWhenAllCommitted { triggerAction(event.newEditor) }
    }

    private fun triggerAction(source: FileEditor?) {
        source ?: return
        if (!getSettings().autoToggle) return
        val actionManager = ActionManager.getInstance()
        actionManager.tryToExecute(
            actionManager.getAction("GoldenRadioAction"),
            ActionCommand.getInputEvent("GoldenRadioAction"),
            null,
            ActionPlaces.EDITOR_TAB,
            true
        )
    }
}