package com.github.sgillespie.stash.hook;

import com.atlassian.stash.hook.repository.*;
import com.atlassian.stash.repository.*;
import com.atlassian.stash.setting.*;

import javax.annotation.Nonnull;
import java.util.Collection;

public class FlexibleAutomergePostReceiveHook implements AsyncPostReceiveRepositoryHook, RepositorySettingsValidator
{
    /**
     * Connects to a configured URL to notify of all changes.
     */
    @Override
    public void postReceive(@Nonnull RepositoryHookContext context, @Nonnull Collection<RefChange> refChanges) {
    }

    @Override
    public void validate(@Nonnull Settings settings, @Nonnull SettingsValidationErrors errors, @Nonnull Repository repository) {
    }
}