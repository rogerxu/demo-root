package demo.service;

import java.util.Calendar;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class SampleResource extends ServerResource {

    @Override
    protected Representation get() throws ResourceException {
        String message = "Hello world!" + "\n"
                + Calendar.getInstance().getTime().toString();

        return new StringRepresentation(message, MediaType.TEXT_PLAIN);
    }

}
