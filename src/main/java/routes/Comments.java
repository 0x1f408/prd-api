package routes;

// Started 13:38
// Finished 16:29

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.mysql.MySQLCommentDAO;
import models.Comment;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO: Make error messages more consistent, and make them provide more useful information
@Path("/comment/")
public class Comments {
    private static final MySQLCommentDAO MY_SQL_COMMENT_DAO = new MySQLCommentDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testConf(){
        ObjectMapper om = new ObjectMapper();
        Comment comment = new Comment();
        try {
            return Response.status(Response.Status.OK).entity(om.writeValueAsString(comment)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.OK).entity("Something went wrong with the mapper").build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{proposalId}")
    public Response getCommentsOnProposal(@PathParam("proposalId") String proposalId){
        ObjectMapper om = new ObjectMapper();

        List<Comment> comments = new ArrayList<>();

        try {
            // Build this from the controller
            comments = MY_SQL_COMMENT_DAO.getCommentsOnProposal(proposalId);
            return Response.status(Response.Status.OK).entity(om.writeValueAsString(comments)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing JSON").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error in writing to DB").build();

        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{proposalId}/{proposalVersion}")
    public Response getCommentsOnVersion(@PathParam("proposalId") String proposalId, @PathParam("proposalVersion") int proposalVersion){
        ObjectMapper om = new ObjectMapper();

        List<Comment> comments = new ArrayList<>();
        try {
            comments = MY_SQL_COMMENT_DAO.getCommentsOnVersion(proposalId,proposalVersion);
            return Response.status(Response.Status.OK).entity(om.writeValueAsString(comments)).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error in writing to DB").build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing JSON").build();

        }


    }

    // e.g.: output expected JSON structure and/or received vs expected response
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response postComment(String input){
        ObjectMapper om = new ObjectMapper();

        try {
            Comment comment = om.readValue(input, Comment.class);
            boolean success = MY_SQL_COMMENT_DAO.submitNewComment(comment);
            if (success){
                return Response.status(Response.Status.OK).entity("Comment written successfully to DB").build();

            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Comment failed to write to DB").build();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Improperly formatted JSON").build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error in writing to DB").build();
        }

    }

}
