package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Post;
import jakarta.persistence.TypedQuery;

public class PostsDao extends DataAccessObject<Post> {
    @Override
    public boolean contains(Post entity) {
        Post posts = null;
        try {
            TypedQuery<Post> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE " +
                    "name = '" + entity.getName() + "'", getType());
            posts = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return posts != null;
    }

    @Override
    protected Class<Post> getType() {
        return Post.class;
    }
}
