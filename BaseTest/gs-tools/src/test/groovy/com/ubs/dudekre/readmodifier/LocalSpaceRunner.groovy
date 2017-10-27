package com.ubs.dudekre.readmodifier

import com.gigaspaces.document.SpaceDocument
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith (SpringJUnit4ClassRunner)
@ContextConfiguration ('classpath:/com/ubs/dudekre/local-space.xml')
class LocalSpaceRunner {
    @Test
    public void runSpace() {
        sleep(3_600_000)
    }
}
