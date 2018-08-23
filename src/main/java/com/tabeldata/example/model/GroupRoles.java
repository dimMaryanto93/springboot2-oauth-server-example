package com.tabeldata.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "group_role", schema = "user_management")
@ToString(exclude = {"roles"})
public class GroupRoles {

    @Id
    @GenericGenerator(name = "uuid_generator", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_generator")
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "groupRole")
    @JsonIgnore
    private List<GroupRolesManagement> roles = new ArrayList<>();

}
