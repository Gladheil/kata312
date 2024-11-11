package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoleDAOImpl implements RoleDAO {
    EntityManager em;

    @Autowired
    public RoleDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role getRole(String role) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :role", Role.class);
        query.setParameter("role", role);
        return query.getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public List<Role> getRolesByNames(List<String> names){
        return names.stream().map(this::getRole).collect(Collectors.toList());
    }
}
