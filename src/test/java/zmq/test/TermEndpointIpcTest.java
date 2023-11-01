package zmq.test;

import java.util.UUID;

public class TermEndpointIpcTest extends TestTermEndpoint
{
    @Override
    protected String endpointWildcard()
    {
        return "ipc://*";
    }

    @Override
    protected String endpointNormal()
    {
        return "ipc://" + UUID.randomUUID();
    }
}
