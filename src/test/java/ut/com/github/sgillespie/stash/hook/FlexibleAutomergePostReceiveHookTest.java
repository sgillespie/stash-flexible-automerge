package ut.com.github.sgillespie.stash.hook;

import com.atlassian.stash.hook.repository.RepositoryHookContext;
import com.atlassian.stash.repository.RefChange;
import com.github.sgillespie.stash.hook.FlexibleAutomergePostReceiveHook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static java.util.Arrays.asList;

public class FlexibleAutomergePostReceiveHookTest {
    private FlexibleAutomergePostReceiveHook flexibleAutomergePostReceiveHook;

    @Mock
    private RepositoryHookContext repositoryHookContext;
    @Mock
    private RefChange refChange;

    @Before
    public void setUp() {
        flexibleAutomergePostReceiveHook = new FlexibleAutomergePostReceiveHook();
    }

    @Test
    public void pushToReleaseBranchShouldTriggerAutomerge() {
        flexibleAutomergePostReceiveHook.postReceive(repositoryHookContext, asList(refChange));
    }
}
