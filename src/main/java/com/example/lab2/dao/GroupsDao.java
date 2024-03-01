package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Group;
import com.example.lab2.objects.main.Specialization;
import com.example.lab2.objects.main.Teacher;
import com.example.lab2.objects.references.SpecializationDiscipline;
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
    public List<Group> findGroups(Teacher teacher, Discipline discipline) {
        List<Group> groups = null;
        try {
            TypedQuery<Group> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "SELECT t3 FROM " + TeacherGroup.class.getSimpleName() + " as t1 " +
                            "JOIN t1.teacherDiscipline as t2 " +
                            "JOIN t1.group as t3 " +
                            "WHERE t2.discipline.id  = " + discipline.getId()+
                            "AND t2.teacher.id = " + teacher.getId(),
                    getType());
            groups = typedQuery.getResultList();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return groups;
    }

    // TODO Запрос слегка неверен, надо поправить
    public List<Group> findGroups(Discipline discipline) {
        List<Group> groups = null;
        try {
            TypedQuery<Group> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "SELECT t4 FROM " + getTableName() + " as t4 " +
                    "JOIN t4.specialization as t5 " +
                    "WHERE t5.id = " + "(SELECT t3.id FROM " + SpecializationDiscipline.class.getSimpleName() + " as t1 " +
                    "JOIN t1.discipline as t2 " +
                    "JOIN t1.specialization as t3 " +
                    "WHERE t2.id = " + discipline.getId() + " " +
                    "LIMIT 1)",
                    getType());
            groups = typedQuery.getResultList();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) { ignored.printStackTrace(); }
        return groups;
    }

    public List<Group> findGroups(Specialization specialization) {
        List<Group> groups = null;
        try {
            TypedQuery<Group> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "SELECT t1 FROM " + getTableName() + " as t1 " +
                            "JOIN t1.specialization as t2 " +
                            "WHERE t2.id = " + specialization.getId(),
                    getType());
            groups = typedQuery.getResultList();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) { ignored.printStackTrace(); }
        return groups;
    }

    @Override
    protected Class<Group> getType() {
        return Group.class;
    }
}
