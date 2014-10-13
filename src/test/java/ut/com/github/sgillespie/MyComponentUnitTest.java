package ut.com.github.sgillespie;

import org.junit.Test;
import com.github.sgillespie.MyPluginComponent;
import com.github.sgillespie.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}