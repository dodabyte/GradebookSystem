package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.references.TeacherGroup;
import jakarta.persistence.TypedQuery;

public class TeacherGroupDao extends DataAccessObject<TeacherGroup> {
    @Override
    public boolean contains(TeacherGroup entity) {
        TeacherGroup teacherGroup = null;
        try {
            TypedQuery<TeacherGroup> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE teacherDiscipline.discipline.name = '" + entity.getTeacherDiscipline().getDiscipline().getName() +
                    "' AND teacherDiscipline.discipline.typeOfMark.name = '" + entity.getTeacherDiscipline().getDiscipline().getTypeOfMark().getName() +
                    "' AND teacherDiscipline.teacher.lastName = '" + entity.getTeacherDiscipline().getTeacher().getLastName() +
                    "' AND teacherDiscipline.teacher.firstName = '" + entity.getTeacherDiscipline().getTeacher().getFirstName() +
                    "' AND teacherDiscipline.teacher.patronymic = '" + entity.getTeacherDiscipline().getTeacher().getPatronymic() +
                    "' AND teacherDiscipline.teacher.department.name = '" + entity.getTeacherDiscipline().getTeacher().getDepartment().getName() +
                    "' AND teacherDiscipline.teacher.post.name = '" + entity.getTeacherDiscipline().getTeacher().getPost().getName() +
                    "' AND teacherDiscipline.teacher.address.city = '" + entity.getTeacherDiscipline().getTeacher().getAddress().getCity() +
                    "' AND teacherDiscipline.teacher.address.street = '" + entity.getTeacherDiscipline().getTeacher().getAddress().getStreet() +
                    "' AND teacherDiscipline.teacher.address.houseNumber = '" + entity.getTeacherDiscipline().getTeacher().getAddress().getHouseNumber() +
                    "' AND teacherDiscipline.teacher.address.apartmentNumber = " + entity.getTeacherDiscipline().getTeacher().getAddress().getApartmentNumber() +
                    " AND group.name = '" + entity.getGroup().getName() +
                    "' AND group.course = " + entity.getGroup().getCourse() +
                    " AND group.semester = " + entity.getGroup().getSemester() +
                    " AND group.specialization.number = '" + entity.getGroup().getSpecialization().getNumber() +
                    "' AND group.specialization.name = '" + entity.getGroup().getSpecialization().getName() +
                    "' AND group.specialization.studyDuration = " + entity.getGroup().getSpecialization().getStudyDuration(),
                    getType());
            teacherGroup = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        } catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return teacherGroup != null;
    }

    @Override
    protected Class<TeacherGroup> getType() {
        return TeacherGroup.class;
    }
}
