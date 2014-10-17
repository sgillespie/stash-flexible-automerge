package com.github.sgillespie.stash.hook;

import com.atlassian.stash.hook.repository.*;
import com.atlassian.stash.repository.*;
import com.atlassian.stash.setting.*;
import java.net.URL;
import java.util.Collection;

public class FlexibleAutomergePostReceiveHook implements AsyncPostReceiveRepositoryHook, RepositorySettingsValidator
{
    /**
     * Connects to a configured URL to notify of all changes.
     */
    @Override
    public void postReceive(RepositoryHookContext context, Collection<RefChange> refChanges) {
    }

    @Override
    public void validate(Settings settings, SettingsValidationErrors errors, Repository repository) {
    }
}