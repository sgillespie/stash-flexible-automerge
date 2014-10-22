package com.github.sgillespie.stash.hook.domain;

import com.atlassian.stash.repository.Branch;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

public class HierarchicalBranch implements Branch {
    private final Branch branch;
    private final Collection<HierarchicalBranch> parents = new ArrayList<>();

    public HierarchicalBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public boolean getIsDefault() {
        return branch.getIsDefault();
    }

    @Nonnull
    @Override
    public String getLatestChangeset() {
        return branch.getLatestChangeset();
    }

    @Nonnull
    @Override
    public String getDisplayId() {
        return branch.getDisplayId();
    }

    @Nonnull
    @Override
    public String getId() {
        return branch.getId();
    }

    public Branch getBranch() {
        return branch;
    }

    public Collection<HierarchicalBranch> getParents() {
        return parents;
    }
}
