package br.dev.mhc.authentication.dto;

import java.util.HashSet;
import java.util.Set;

import br.dev.mhc.authentication.entities.enums.Profile;

public class UserDTO {
	
	private Long id;
	private String username;
	private boolean active;
	private Set<Profile> profiles = new HashSet<>();
			
	public UserDTO() {
	}

	public UserDTO(Long id, String username, boolean active, Set<Profile> profiles) {
		super();
		this.id = id;
		this.username = username;
		this.active = active;
		this.profiles = profiles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}
}
