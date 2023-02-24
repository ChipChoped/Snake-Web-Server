package fr.snake.api;

import fr.snake.beans.BeanException;
import fr.snake.dao.DAOException;
import fr.snake.dto.ExceptionDTO;
import fr.snake.dto.LoginsDTO;
import fr.snake.dto.UserIDDTO;
import fr.snake.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

@Path("/user")
public class UserAPI {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(LoginsDTO logins) {
        try {
            if (UserService.doLoginsExist(logins)) {
                UserIDDTO userID = UserService.getUserID(logins.getUsername());
                return Response.status(Response.Status.OK).entity(userID).build();
            }
            else throw new BeanException("Username or/and password are wrong");
        }
        catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ExceptionDTO(e.getMessage())).build();
        }
        catch (BeanException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionDTO(e.getMessage())).build();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
