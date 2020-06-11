package com.example.market.entites;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "roles")
public class Role {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

	static {
		COLUMN_MAPPINGS.put("id", "id");
		COLUMN_MAPPINGS.put("name", "name");
	}

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
