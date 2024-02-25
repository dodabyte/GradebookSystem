package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.references.SpecializationDiscipline;
import jakarta.persistence.TypedQuery;

public class SpecializationDisciplineDao extends DataAccessObject<SpecializationDiscipline> {
    @Override
    public boolean contains(SpecializationDiscipline entity) {
        SpecializationDiscipline specializationDiscipline = null;
        try {
            TypedQuery<SpecializationDiscipline> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE specialization.number = '" + entity.getSpecialization().getNumber() +
                    "' AND specialization.name = '" + entity.getSpecialization().getName() +
                    "' AND specialization.studyDuration = " + entity.getSpecialization().getStudyDuration() +
                    " AND discipline.name = '" + entity.getDiscipline().getName() +
                    "' AND discipline.typeOfMark.name = '" + entity.getDiscipline().getTypeOfMark().getName() + "'",
                    getType());
            specializationDiscipline = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return specializationDiscipline != null;
    }

    @Override
    protected Class<SpecializationDiscipline> getType() {
        return SpecializationDiscipline.class;
    }
}
