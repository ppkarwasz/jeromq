package org.zeromq.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.zeromq.SocketType;
import org.zeromq.Utils;
import org.zeromq.ZMQ;
import org.zeromq.ZSocket;

public class ZSocketTest
{
    @Test
    public void pushPullTest() throws IOException
    {
        int port = Utils.findOpenPort();

        try (
                final ZSocket pull = new ZSocket(SocketType.PULL);
                final ZSocket push = new ZSocket(SocketType.PUSH)) {
            pull.bind("tcp://*:" + port);
            push.connect("tcp://127.0.0.1:" + port);

            final String expected = "hello";
            push.sendStringUtf8(expected);
            final String actual = pull.receiveStringUtf8();

            assertEquals(expected, actual);
            assertEquals(SocketType.PULL, pull.getSocketType());
            Assert.assertEquals(ZMQ.PULL, pull.getType());
            assertEquals(SocketType.PUSH, push.getSocketType());
            assertEquals(ZMQ.PUSH, push.getType());
        }
    }
}
