package com.github.sgillespie.stash.hook.domain;

import java.util.ArrayList;
import java.util.Collection;

public class HierarchicalBranchModel {
    private Collection<HierarchicalBranch> branches = new ArrayList<>();

    public Collection<HierarchicalBranch> getBranches() {
        return branches;
    }

    public void setBranches(Collection<HierarchicalBranch> branches) {
        this.branches = branches;
    }
}
