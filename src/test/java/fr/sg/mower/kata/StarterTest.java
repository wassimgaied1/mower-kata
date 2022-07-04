package fr.sg.mower.kata;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;
/**
 * @author Wassim Gaied
 */
@RunWith(MockitoJUnitRunner.class)
public class StarterTest {


    private final Starter starter = new Starter();


    @Test
    public void testMain() {

        ch.qos.logback.classic.Logger logger = ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Starter.class));
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        String args[] = {"src\\test\\resources\\mower"};
        starter.main(args);
        assertTrue(listAppender.list.get(0).getMessage().contains("1 3 N"));
        assertTrue(listAppender.list.get(1).getMessage().contains("5 1 E"));


    }
}
