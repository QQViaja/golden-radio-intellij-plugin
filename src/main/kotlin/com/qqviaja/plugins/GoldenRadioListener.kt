package com.qqviaja.plugins

import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.TextEditor
import com.qqviaja.plugins.model.GoldenRadioSettingsHolder.GoldenRadioSettings.Companion.getSettings
import com.qqviaja.plugins.util.GoldenRadioUtils.applyGoldenRatioToSplitters


/**
 * Listener that automatically applies the golden ratio to editor splitters when a file is selected.
 * 
 * This listener monitors file editor selection changes and applies the golden ratio
 * to the editor splitters if the auto-toggle feature is enabled in the settings.
 * This provides automatic adjustment of editor panels according to the golden ratio
 * whenever the user switches between files.
 *
 * <p>Create on 2022/10/7.</p>
 * @author Kimi Chen
 */
class GoldenRadioListener : FileEditorManagerListener {

    /**
     * Handles the file editor selection change event.
     * 
     * Applies the golden ratio to the editor splitters when a new file is selected,
     * but only if the auto-toggle feature is enabled in the settings.
     * 
     * @param event The event containing information about the old and new selected editors
     */
    override fun selectionChanged(event: FileEditorManagerEvent) {
        // Only apply golden ratio if autoToggle is enabled in settings
        if (!getSettings().autoToggle) {
            return
        }
        
        when (val fileEditor = event.newEditor) {
            is TextEditor -> {
                val editor = fileEditor.editor
                applyGoldenRatioToSplitters(editor)
            }
        }
    }
}