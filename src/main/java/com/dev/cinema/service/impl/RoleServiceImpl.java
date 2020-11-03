package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao dao;

    @Override
    public void add(Role role) {
        dao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return dao.getRoleByName(roleName);
    }
}
