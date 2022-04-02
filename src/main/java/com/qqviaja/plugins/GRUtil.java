package com.qqviaja.plugins;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.impl.EditorsSplitters;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Splitter;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.ui.ComponentUtil;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

/**
 * <p>Create on 2022/3/24.</p>
 *
 * @author Kimi Chen
 */
public class GRUtil {

    public final static Key<Boolean> CURRENT_STATE_IS_RADIO_KEY = Key.create("CURRENT_STATE_IS_GOLDEN_RADIO");

    @NotNull
    @NonNls
    public static final String GR_CONFIG = "GRSettings.xml";

    public static Set<Pair<Splitter, Boolean>> getSplittersToGoldenRadio(Project project, Editor editor) {
        final FileEditorManager instance = FileEditorManager.getInstance(project);
        if (!(instance instanceof FileEditorManagerImpl)) {

            return emptySet();
        }
        final FileEditorManagerImpl editorManager = (FileEditorManagerImpl) instance;

        var set = new HashSet<Pair<Splitter, Boolean>>();
        Component comp = editor.getComponent();
        while (comp != editorManager.getMainSplitters()) {
            var parent = comp.getParent();
            if (parent instanceof Splitter && UIUtil.isClientPropertyTrue(parent, EditorsSplitters.SPLITTER_KEY)) {
                final Splitter splitter = (Splitter) parent;
                if (splitter.getFirstComponent() == comp) {
                    final float proportion = GRSettingsHolder.GRSettings.getSettings().getProportion();
                    if (splitter.getProportion() != proportion && proportion > 0) {
                        set.add(new Pair<>(splitter, true));
                    }
                } else {
                    set.add(new Pair<>(splitter, false));
                }
            }
            comp = parent;
        }
        return set;
    }

    public static Set<Splitter> getSplittersToNormalize(AnActionEvent e) {
        var project = e.getProject();
        var editor = e.getData(CommonDataKeys.HOST_EDITOR);
        if (project == null || editor == null) {
            return emptySet();
        }

        var set = new HashSet<Splitter>();
        var splitters = ComponentUtil.getParentOfType(EditorsSplitters.class, editor.getComponent());
        while (splitters != null) {
            var candidate = ComponentUtil.getParentOfType(EditorsSplitters.class, splitters.getParent());
            if (splitters == candidate) {
                break;
            }
            splitters = candidate;
        }
        if (splitters != null) {
            return UIUtil.findComponentsOfType(splitters, Splitter.class)
                    .stream().filter(splitter -> UIUtil.isClientPropertyTrue(splitter, EditorsSplitters.SPLITTER_KEY))
                    .collect(Collectors.toSet());
        }
        return set;
    }


}
