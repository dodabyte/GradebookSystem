package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Teacher;
import com.example.lab2.objects.references.TeacherDiscipline;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DisciplinesDao extends DataAccessObject<Discipline> {
    @Override
    public boolean contains(Discipline entity) {
        Discipline discipline = null;
        try {
            TypedQuery<Discipline> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE name = '" + entity.getName() +
                    "' AND typeOfMark.name = '" + entity.getTypeOfMark().getName() + "'",
                    getType());
            discipline = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return discipline != null;
    }

    public List<Discipline> findDisciplines(Teacher entity) {
        List<Discipline> disciplines = null;
        try {
            TypedQuery<Discipline> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "SELECT t3 FROM " + TeacherDiscipline.class.getSimpleName() + " as t1 " +
                            "JOIN t1.teacher as t2 " +
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
    protected Class<Discipline> getType() {
        return Discipline.class;
    }
}
