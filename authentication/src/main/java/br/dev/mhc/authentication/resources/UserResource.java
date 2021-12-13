package br.dev.mhc.authentication.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.dev.mhc.authentication.dto.UserDTO;
import br.dev.mhc.authentication.dto.UserNewDTO;
import br.dev.mhc.authentication.entities.User;
import br.dev.mhc.authentication.services.AuthService;
import br.dev.mhc.authentication.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void insert(@RequestBody UserNewDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
	}

	@PreAuthorize("hasAnyRole('BASIC_USER')")
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody UserDTO objDTO, @PathVariable Long id) {
		AuthService.validatesUserAuthorization(id);
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> findAll() {
		List<User> userList = service.findAll();
		return userList.stream().map(x -> service.toDTO(x)).collect(Collectors.toList());
	}

	@PreAuthorize("hasAnyRole('BASIC_USER')")
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO findById(@PathVariable Long id) {
		AuthService.validatesUserAuthorization(id);
		User obj = service.findById(id);
		return service.toDTO(obj);
	}
	
	@PreAuthorize("hasAnyRole('BASIC_USER')")
	@PatchMapping(value = "/{id}/set-password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void setPassword(@RequestBody UserNewDTO objDTO, @PathVariable Long id) {
		AuthService.validatesUserAuthorization(id);
		User user = service.fromDTO(objDTO);
		user.setId(id);
		service.updatePassword(user);
	}
}
