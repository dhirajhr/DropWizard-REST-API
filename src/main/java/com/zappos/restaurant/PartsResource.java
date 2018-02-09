package com.zappos.restaurant;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;

import com.codahale.metrics.annotation.Timed;

@Path("/parts")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class PartsResource {
  private final PartsService partsService;

  public PartsResource(PartsService partsService) {
    this.partsService = partsService;
  }

  @GET
  @Timed
  public Representation<List<Part>> getParts() {
    return new Representation<List<Part>>(HttpStatus.OK_200, partsService.getParts());
  }

  @GET
  @Timed
  @Path("{id}")
  public Representation<Part> getPart(@PathParam("id") final int id) {
    return new Representation<Part>(HttpStatus.OK_200, partsService.getPart(id));
  }

  @POST
  @Timed
  public Representation<Part> createPart(@NotNull @Valid final Part part) {
    return new Representation<Part>(HttpStatus.OK_200, partsService.createPart(part));
  }

  @PUT
  @Timed
  @Path("{id}")
  public Representation<Part> editPart(@NotNull @Valid final Part part,
      @PathParam("id") final int id) {
    part.setId(id);
    return new Representation<Part>(HttpStatus.OK_200, partsService.editPart(part));
  }

  @DELETE
  @Timed
  @Path("{id}")
  public Representation<String> deletePart(@PathParam("id") final int id) {
    return new Representation<String>(HttpStatus.OK_200, partsService.deletePart(id));
  }
}