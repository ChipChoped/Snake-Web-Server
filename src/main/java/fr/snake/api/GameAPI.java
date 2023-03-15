package fr.snake.api;

import fr.snake.beans.BeanException;
import fr.snake.dao.DAOException;
import fr.snake.dto.ExceptionDTO;
import fr.snake.dto.GameDTO;
import fr.snake.services.GameService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
public class GameAPI {
    @PUT
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveGame(GameDTO game) {
        try {
            GameService.addGame(game);
            return Response.status(Response.Status.CREATED).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ExceptionDTO(e.getMessage())).build();
        } catch (BeanException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionDTO(e.getMessage())).build();
        }
    }
}
