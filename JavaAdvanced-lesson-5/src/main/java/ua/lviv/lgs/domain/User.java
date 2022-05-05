package ua.lviv.lgs.domain;

import java.util.Objects;

public class User {

	private Integer id;
	private String email;
	private String first_name;
	private String last_name;
	private String role;

	public User(String email, String first_name, String last_name, String role) {
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
	}

	public User(Integer id, String email, String first_name, String last_name, String role) {
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User id: " + id + ", email: " + email + ", first_name: " + first_name + ", last_name: " + last_name
				+ ", role: " + role + "";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, first_name, id, last_name, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(first_name, other.first_name)
				&& Objects.equals(id, other.id) && Objects.equals(last_name, other.last_name)
				&& Objects.equals(role, other.role);
	}

}
