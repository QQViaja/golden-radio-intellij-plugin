package com.qqviaja.plugins;

import com.intellij.ide.IdeBundle;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.Splitter;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

import static com.qqviaja.plugins.GRUtil.CURRENT_STATE_IS_RADIO_KEY;
import static java.util.Collections.emptySet;

/**
 * <p>Create on 2022/3/24.</p>
 *
 * @author Kimi Chen
 */
public class GoldenRadioAction extends DumbAwareAction {

    public GoldenRadioAction() {
        getTemplatePresentation().setText(GRBundle.message("action.golden.radio.editor") + "/" + IdeBundle.message("action.normalize.splits"));
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        final float proportion = GRSettingsHolder.GRSettings.getSettings().getProportion();
        var project = e.getProject();
        Optional.ofNullable(project).ifPresent(p -> {
            final Set<Pair<Splitter, Boolean>> splittersToGoldenRadio = getSplittersToGoldenRadio(e);
            if (splittersToGoldenRadio.isEmpty()) {
                GRUtil.getSplittersToNormalize(e).forEach(splitter -> splitter.setProportion(.5f));
            } else {
                splittersToGoldenRadio.forEach(splitter ->
                        splitter.first.setProportion(splitter.getSecond() ? proportion : (1 - proportion)));
            }
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        final Presentation presentation = e.getPresentation();
        presentation.setEnabled(true);

        final Set<Pair<Splitter, Boolean>> splittersToGoldenRadio = getSplittersToGoldenRadio(e);

        if (!splittersToGoldenRadio.isEmpty()) {
            presentation.setText(GRBundle.message("action.golden.radio.editor"));
            presentation.putClientProperty(CURRENT_STATE_IS_RADIO_KEY, false);
            return;
        }

        final Set<Splitter> splittersToNormalize = GRUtil.getSplittersToNormalize(e);
        if (!splittersToNormalize.isEmpty()) {
            presentation.setText(IdeBundle.message("action.normalize.splits"));
            presentation.putClientProperty(CURRENT_STATE_IS_RADIO_KEY, true);
            return;
        }
        presentation.setEnabled(false);
    }

    private Set<Pair<Splitter, Boolean>> getSplittersToGoldenRadio(AnActionEvent e) {
        var project = e.getProject();
        var editor = e.getData(CommonDataKeys.HOST_EDITOR);
        if (project == null || editor == null) {
            return emptySet();
        }
        return GRUtil.getSplittersToGoldenRadio(project, editor);
    }
}
