package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Group;
import com.example.lab2.objects.main.Teacher;
import com.example.lab2.objects.references.TeacherDiscipline;
import com.example.lab2.objects.references.TeacherGroup;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GroupsDao extends DataAccessObject<Group> {
    @Override
    public boolean contains(Group entity) {
        Group group = null;
        try {
            TypedQuery<Group> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE name = '" + entity.getName() +
                    "' AND course = " + entity.getCourse() +
                    " AND semester = " + entity.getSemester() +
                    " AND specialization.number = '" + entity.getSpecialization().getNumber() +
                    "' AND specialization.name = '" + entity.getSpecialization().getName() +
                    "' AND specialization.studyDuration = " + entity.getSpecialization().getStudyDuration(),
                    getType());
            group = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return group != null;
    }
    public List<Discipline> findDisciplines(Teacher entity) {
        List<Discipline> disciplines = null;
        try {
            TypedQuery<Discipline> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "SELECT t3 FROM " + TeacherGroup.class.getSimpleName() + " as t1 " +
                            "JOIN t1.group as t3 " +
                            "JOIN t1.discipline as t3 " +
                            "WHERE t2.id = " + entity.getId(),
                    getType());
            disciplines = typedQuery.getResultList();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return disciplines;
    }
    @Override
    protected Class<Group> getType() {
        return Group.class;
    }
}
