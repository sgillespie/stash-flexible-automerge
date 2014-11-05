package com.github.sgillespie.stash.hook;

import com.atlassian.stash.hook.repository.*;
import com.atlassian.stash.repository.*;
import com.atlassian.stash.setting.*;
import com.atlassian.stash.util.PageRequest;
import com.atlassian.stash.util.PageRequestImpl;
import com.github.sgillespie.stash.hook.domain.HierarchicalBranchModel;
import com.github.sgillespie.stash.hook.service.HierarchicalBranchModelService;

import javax.annotation.Nonnull;
import java.util.Collection;

public class FlexibleAutomergePostReceiveHook implements AsyncPostReceiveRepositoryHook, RepositorySettingsValidator {
    public static final PageRequest PAGE_REQUEST = new PageRequestImpl(0, PageRequest.MAX_PAGE_LIMIT);

    private final HierarchicalBranchModelService hierarchicalBranchModelService;

    public FlexibleAutomergePostReceiveHook(HierarchicalBranchModelService hierarchicalBranchModelService) {
        this.hierarchicalBranchModelService = hierarchicalBranchModelService;
    }

    /**
     * Connects to a configured URL to notify of all changes.
     */
    @Override
    public void postReceive(@Nonnull RepositoryHookContext context, @Nonnull Collection<RefChange> refChanges) {
        hierarchicalBranchModelService.getModel(context.getRepository());
    }

    @Override
    public void validate(@Nonnull Settings settings, @Nonnull SettingsValidationErrors errors, @Nonnull Repository repository) {
    }
}