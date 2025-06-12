package com.example.demo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("/example")
public class ExampleResource {

    @Inject
    private ExampleService exampleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExampleData getExample() {
        return exampleService.getExampleData();
    }
}

package com.example.demo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleService {

    public ExampleData getExampleData() {
        return new ExampleData("Sample Data");
    }
}

package com.example.demo;

public class ExampleData {
    private final String data;

    public ExampleData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}