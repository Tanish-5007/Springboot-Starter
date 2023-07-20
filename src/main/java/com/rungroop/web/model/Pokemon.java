package com.rungroop.web.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pokemon {
    @Id
    private int id;
    private String name;
    private String type;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<Review>();
    
    public void setId(int id) {
		this.id = id;
	}
    public void setName(String name) {
		this.name = name;
	}
    public void setType(String type) {
		this.type = type;
	}
    public int getId() {
		return id;
	}
    public String getName() {
		return name;
	}
    public String getType() {
		return type;
	}
}
