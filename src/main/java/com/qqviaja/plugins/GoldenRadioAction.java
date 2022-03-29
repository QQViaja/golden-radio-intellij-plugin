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

import static com.qqviaja.plugins.SplitterUtil.CURRENT_STATE_IS_RADIO_KEY;
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
        var project = e.getProject();
        Optional.ofNullable(project).ifPresent(p -> {
            final Set<Pair<Splitter, Boolean>> splittersToGoldenRadio = getSplittersToGoldenRadio(e);
            if (splittersToGoldenRadio.isEmpty()) {
                SplitterUtil.getSplittersToNormalize(e).forEach(splitter -> splitter.setProportion(.5f));
            } else {
                splittersToGoldenRadio.forEach(splitter ->
                        splitter.first.setProportion(splitter.getSecond() ? SplitterUtil.GOLDEN_RADIO_PROPORTION : (1 - SplitterUtil.GOLDEN_RADIO_PROPORTION)));
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

        final Set<Splitter> splittersToNormalize = SplitterUtil.getSplittersToNormalize(e);
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
        return SplitterUtil.getSplittersToGoldenRadio(project, editor);
    }
}
