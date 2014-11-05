package com.github.sgillespie.stash.hook.service;

import com.atlassian.stash.repository.Branch;
import com.atlassian.stash.repository.Repository;
import com.atlassian.stash.repository.RepositoryBranchesRequest;
import com.atlassian.stash.repository.RepositoryMetadataService;
import com.github.sgillespie.stash.hook.domain.HierarchicalBranch;
import com.github.sgillespie.stash.hook.domain.HierarchicalBranchModel;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;

import java.util.Collection;
import java.util.Set;

import static com.github.sgillespie.stash.hook.FlexibleAutomergePostReceiveHook.PAGE_REQUEST;

public class HierarchicalBranchModelServiceImpl implements HierarchicalBranchModelService {
    private RepositoryMetadataService repositoryMetadataService;

    public HierarchicalBranchModelServiceImpl(RepositoryMetadataService repositoryMetadataService) {
        this.repositoryMetadataService = repositoryMetadataService;
    }

    @Override
    public HierarchicalBranchModel getModel(Repository repository) {
        HierarchicalBranchModel model = new HierarchicalBranchModel();

        RepositoryBranchesRequest request = new RepositoryBranchesRequest
                .Builder()
                .repository(repository)
                .filterText("master")
                .build();

        Iterable<Branch> branches = repositoryMetadataService
                .getBranches(request, PAGE_REQUEST)
                .getValues();
        Optional<Branch> master = Iterables.tryFind(branches, new Predicate<Branch>() {
            @Override
            public boolean apply(@Nullable Branch branch) {
                return StringUtils.equals(branch.getId(), "refs/heads/master");
            }
        });
        Collection<HierarchicalBranch> masterHierarchicalBranch = Collections2.transform(master.asSet(), new Function<Branch, HierarchicalBranch>() {
            @Override
            public HierarchicalBranch apply(@Nullable Branch branch) {
                return new HierarchicalBranch(branch);
            }
        });

        model.getBranches().addAll(masterHierarchicalBranch);
        return model;
    }
}
