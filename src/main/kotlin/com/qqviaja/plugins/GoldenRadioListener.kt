package com.qqviaja.plugins

import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.TextEditor
import com.qqviaja.plugins.util.GoldenRadioUtils.applyGoldenRatioToSplitters


/**
 *
 * <p>Create on 2022/10/7.</p>
 * @author Kimi Chen
 */
class GoldenRadioListener : FileEditorManagerListener {

    override fun selectionChanged(event: FileEditorManagerEvent) {
        when (val fileEditor = event.newEditor) {
            is TextEditor -> {
                val editor = fileEditor.editor
                applyGoldenRatioToSplitters(editor)
            }
        }
    }
}