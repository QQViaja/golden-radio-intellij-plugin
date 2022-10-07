package com.qqviaja.plugins

import com.intellij.ide.DataManager
import com.intellij.ide.actions.MaximizeEditorInSplitAction.Companion.CURRENT_STATE_IS_MAXIMIZED_KEY
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.ui.playback.commands.ActionCommand
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.IdeFocusManager
import com.qqviaja.plugins.GRSettingsHolder.GRSettings.Companion.getSettings


/**
 *
 * <p>Create on 2022/10/7.</p>
 * @author Kimi Chen
 */
class GoldenRadioListener : FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (!getSettings().autoToggle) return
        val manager = source as? FileEditorManagerImpl ?: return
        manager.selectedEditor ?: return
        val am = ActionManager.getInstance()
        am.tryToExecute(
            am.getAction("GoldenRadioAction"),
            ActionCommand.getInputEvent("GoldenRadioAction"),
            null,
            ActionPlaces.UNKNOWN,
            true
        )
    }

    override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
        if (!getSettings().autoToggle) return
        val actionManager = ActionManager.getInstance()
        val manager = source as? FileEditorManagerImpl ?: return
        val editor = manager.selectedEditor ?: return
        actionManager.tryToExecute(
            actionManager.getAction("GoldenRadioAction"),
            ActionCommand.getInputEvent("GoldenRadioAction"),
            null,
            ActionPlaces.EDITOR_TAB,
            true
        )

    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        if (!getSettings().autoToggle) return
        event.manager as? FileEditorManagerImpl ?: return
        event.oldEditor ?: return
        val am = ActionManager.getInstance()
        am.tryToExecute(
            am.getAction("GoldenRadioAction"),
            ActionCommand.getInputEvent("GoldenRadioAction"),
            null,
            ActionPlaces.UNKNOWN,
            true
        )
    }
}