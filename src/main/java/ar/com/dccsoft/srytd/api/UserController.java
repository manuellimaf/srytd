package ar.com.dccsoft.srytd.api;

import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrFail;
import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrNotFound;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNumeric;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.api.dto.RoleDTO;
import ar.com.dccsoft.srytd.api.dto.UserDTO;
import ar.com.dccsoft.srytd.model.Role;
import ar.com.dccsoft.srytd.services.UserService;

import com.google.common.base.Enums;
import com.google.common.collect.Lists;

@Path("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService service = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getPage(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit) {
		return service.getPage(start, limit);
	}

	@PUT
	@Path("/{id}/disable")
	@Produces(MediaType.APPLICATION_JSON)
	public Response disableUser(@PathParam("id") String id) {
		
		validateId(id);
		service.disable(Long.valueOf(id));
		
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}/enable")
	@Produces(MediaType.APPLICATION_JSON)
	public Response enableUser(@PathParam("id") String id) {
		
		validateId(id);
		service.enable(Long.valueOf(id));
		
		return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserDTO dto) {
		logger.info("Creating new user");

		// TODO - username
		String username = "web";
		validateCreation(dto);
		service.createUser(dto, username);
		
		return Response.status(200).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(UserDTO dto) {
		logger.info("Updating user");
		validateUpdate(dto);
		service.updateUser(dto);
		return Response.status(200).build();
	}


	@GET
	@Path("/all-roles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoleDTO> getRoles() {
		List<Role> roles = Lists.newArrayList(Role.values());
		return Lists.newArrayList(
				Lists.transform(roles, (role) -> new RoleDTO(role.name(), role.toString())));
	}

	
	@POST
	@Path("/auth")
	public Response authenticate() {
		// Si llegó hasta acá es porque pasó correctamente por los filtros de autenticación.
		return Response.ok().build();
	}

	private void validateUpdate(UserDTO dto) {
		validateOrNotFound(() -> service.userExists(dto.getId()));
		genericValidations(dto);
	}

	public void genericValidations(UserDTO dto) {
		validateOrFail("Nombre de usuario es requerido", () -> isNotBlank(dto.getUsername()));		
		validateOrFail("Contraseña es requerido", () -> isNotBlank(dto.getPassword()));		
		validateOrFail("Confirmación de contraseña es requerido", () -> isNotBlank(dto.getPasswordConf()));		
		validateOrFail("Rol es requerido", () -> isNotBlank(dto.getRole()));		
		validateOrFail("Las contraseñas no son iguales", () -> dto.getPassword().equals(dto.getPasswordConf()));		
		validateOrFail(dto.getRole() + " no es un rol válido", () -> 
			Enums.getIfPresent(Role.class, dto.getRole()).isPresent());
	}
	
	private void validateCreation(UserDTO dto) {
		validateOrFail("Ya existe un usuario con este nombre", () -> !service.userExists(dto.getUsername()));
		genericValidations(dto);
	}

	private void validateId(String id) {
		validateOrFail(id + " no es un id válido", () -> isNumeric(id));		
		validateOrNotFound(() -> service.userExists(Long.valueOf(id)));
	}
}
