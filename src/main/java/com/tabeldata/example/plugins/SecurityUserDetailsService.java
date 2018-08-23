package com.tabeldata.example.plugins;

import com.tabeldata.example.dao.SecurityDao;
import com.tabeldata.example.model.*;
import com.tabeldata.example.repository.GroupUserRolesRepository;
import com.tabeldata.example.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final static Logger console = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private SecurityDao userDao;

    @Autowired
    private GroupUserRolesRepository groupRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        try {
            User user = userDao.findUserByUsername(userId);
            if (user != null && user.isSudo()) {
                GroupUserRoles userRolesSudo = new GroupUserRoles();
                GroupRoles groupSudo = new GroupRoles();
                List<GroupRolesManagement> groupRolesManagements = new ArrayList<>();
                for (Role role : roleRepository.findAll()) {
                    GroupRolesManagement sudoRoleManagement = new GroupRolesManagement();
                    sudoRoleManagement.setRole(role);
                    groupRolesManagements.add(sudoRoleManagement);
                }
                groupSudo.setRoles(groupRolesManagements);
                userRolesSudo.setGroup(groupSudo);
                user.setGroups(Arrays.asList(userRolesSudo));
            } else {
                user.setGroups(groupRepository.findByUserUsername(userId));
            }
            return new SecurityUserDetails(user);
        } catch (EmptyResultDataAccessException emptyException) {
//            emptyException.printStackTrace();
            console.warn("username {} not found!", userId);
            throw new UsernameNotFoundException("email " + userId + " not found");
        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
            console.error("username {} not found!", userId);
            throw new UsernameNotFoundException("email " + userId + " not found");
        } catch (Exception ex) {
//            ex.printStackTrace();
            console.error("user security exception!", ex);
            throw new UsernameNotFoundException("email " + userId + " not found");
        }
    }
}
