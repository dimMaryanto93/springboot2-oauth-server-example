package com.tabeldata.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "group_member_users", schema = "user_management")
public class GroupUserRoles {

    @Id
    @GenericGenerator(name = "uuid_generator", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_generator")
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupRoles group;
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;
    @Column(name = "last_update_by")
    private String lastUpdateBy;
    @Column(name = "last_update_date")
    private Timestamp lastUpdatedDate;
}
