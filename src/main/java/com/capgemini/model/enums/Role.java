package com.capgemini.model.enums;

public enum Role {
	ADMIN("ROLE_ADMIN"), REGISTRADO("ROLE_REGISTRADO");

	private final String roleName;

	Role(final String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}
}
