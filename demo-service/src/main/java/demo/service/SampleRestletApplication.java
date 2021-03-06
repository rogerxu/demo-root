package demo.service;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class SampleRestletApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a new instance of
        // SampleResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attachDefault(SampleResource.class);

        return router;
    }

}
