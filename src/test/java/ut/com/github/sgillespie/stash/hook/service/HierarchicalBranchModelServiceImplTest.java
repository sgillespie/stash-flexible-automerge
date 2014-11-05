package ut.com.github.sgillespie.stash.hook.service;

import com.atlassian.stash.repository.Branch;
import com.atlassian.stash.repository.Repository;
import com.atlassian.stash.repository.RepositoryBranchesRequest;
import com.atlassian.stash.repository.RepositoryMetadataService;
import com.atlassian.stash.util.Page;
import com.atlassian.stash.util.PageImpl;
import com.atlassian.stash.util.PageRequest;
import com.github.sgillespie.stash.hook.FlexibleAutomergePostReceiveHook;
import com.github.sgillespie.stash.hook.domain.HierarchicalBranch;
import com.github.sgillespie.stash.hook.domain.HierarchicalBranchModel;
import com.github.sgillespie.stash.hook.service.HierarchicalBranchModelService;
import com.github.sgillespie.stash.hook.service.HierarchicalBranchModelServiceImpl;
import org.hamcrest.Matcher;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static com.github.sgillespie.stash.hook.FlexibleAutomergePostReceiveHook.PAGE_REQUEST;
import static java.util.Arrays.asList;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HierarchicalBranchModelServiceImplTest {
    @Mock
    Repository repository;
    @Mock
    RepositoryMetadataService repositoryMetadataService;
    @Mock
    Branch branch;

    @Before
    public void setUp() {
        initMocks(this);

        when(branch.getId()).thenReturn("refs/heads/master");

        Page<Branch> branches = new PageImpl<Branch>(PAGE_REQUEST, asList(branch), true);
        when(repositoryMetadataService.getBranches((RepositoryBranchesRequest)anyObject(),
                (PageRequest)anyObject())).thenReturn(branches);

    }

    @Test
    public void branchModelShouldContainMasterWithNoParents() {
        HierarchicalBranchModelService hierarchicalBranchModelService =
                new HierarchicalBranchModelServiceImpl(repositoryMetadataService);
        HierarchicalBranchModel branchModel = hierarchicalBranchModelService.getModel(repository);

        Matcher<HierarchicalBranch> isMasterBranch = hasProperty("branch", hasProperty(
                "id", equalTo("refs/heads/master")));
        assertThat(branchModel.getBranches(), hasItem(isMasterBranch));
    }

    @Test
    public void branchModelShouldContainReleaseWithOneParent() {
        HierarchicalBranchModelService hierarchicalBranchModelService =
                new HierarchicalBranchModelServiceImpl(repositoryMetadataService);
        HierarchicalBranchModel branchModel = hierarchicalBranchModelService.getModel(repository);

        Matcher<HierarchicalBranch> hasReleaseBranch = hasProperty("branch", hasProperty(
                "id", equalTo("refs/heads/release/rel-1")));
        assertThat(branchModel.getBranches(), hasItem(isReleaseBranch));
    }
}
