package com.github.sgillespie.stash.hook.service;

import com.atlassian.stash.repository.Repository;
import com.github.sgillespie.stash.hook.domain.HierarchicalBranchModel;

public interface HierarchicalBranchModelService {
    public HierarchicalBranchModel getModel(Repository repository);
}
