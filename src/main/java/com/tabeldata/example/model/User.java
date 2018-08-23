package com.tabeldata.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "user_management")
@ToString(exclude = "groups")
public class User {

    @Id
    @GenericGenerator(name = "uuid_generator", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;
    @Column(name = "is_locked", nullable = false)
    private boolean locked;
    @Column(name = "is_sudo", nullable = false)
    private boolean sudo;
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;
    @Column(name = "last_updated_by")
    private String lastUpdateBy;
    @Column(name = "last_updated_date")
    private Timestamp lastUpdateDate;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<GroupUserRoles> groups = new ArrayList<>();

}
