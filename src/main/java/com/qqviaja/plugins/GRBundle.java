package com.qqviaja.plugins;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.function.Supplier;

/**
 * <p>Create on 2021/11/20.</p>
 *
 * @author Kimi Chen
 */
public class GRBundle extends DynamicBundle {

    @NonNls
    public static final String BUNDLE = "GRBundle";
    private static final GRBundle INSTANCE = new GRBundle();

    private GRBundle() {
        super(BUNDLE);
    }

    @NotNull
    public static @Nls
    String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return INSTANCE.getMessage(key, params);
    }

    @NotNull
    public static Supplier<String> messagePointer(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return INSTANCE.getLazyMessage(key, params);
    }
}
