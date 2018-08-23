package com.tabeldata.example.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "group_member_role", schema = "user_management")
public class GroupRolesManagement {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GenericGenerator(name = "uuid_generator", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_generator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupRoles groupRole;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "last_update_by")
    private String lastUpdateBy;

    @Column(name = "last_update_date")
    private Timestamp lastUpdateDate;


}
