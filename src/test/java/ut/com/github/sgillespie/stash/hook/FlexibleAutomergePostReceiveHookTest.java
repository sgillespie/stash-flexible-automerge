package ut.com.github.sgillespie.stash.hook;

import com.atlassian.stash.hook.repository.RepositoryHookContext;
import com.atlassian.stash.repository.RefChange;
import com.github.sgillespie.stash.hook.FlexibleAutomergePostReceiveHook;
import com.github.sgillespie.stash.hook.service.HierarchicalBranchModelService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static java.util.Arrays.asList;
import static org.mockito.MockitoAnnotations.initMocks;

public class FlexibleAutomergePostReceiveHookTest {
    private FlexibleAutomergePostReceiveHook flexibleAutomergePostReceiveHook;

    @Mock
    private HierarchicalBranchModelService hierarchicalBranchModelService;
    @Mock
    private RepositoryHookContext repositoryHookContext;
    @Mock
    private RefChange refChange;

    @Before
    public void setUp() {
        initMocks(this);
        flexibleAutomergePostReceiveHook = new FlexibleAutomergePostReceiveHook(hierarchicalBranchModelService);
    }

    @Test
    public void pushToReleaseBranchShouldTriggerAutomerge() {
        flexibleAutomergePostReceiveHook.postReceive(repositoryHookContext, asList(refChange));
    }
}
