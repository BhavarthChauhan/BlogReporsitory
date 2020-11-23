package com.adobe.interview.blog.repository;

import com.adobe.interview.blog.model.BlogSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogSpaceRepository extends JpaRepository<BlogSpace, Long> {

        @Query(value = "Select b from BlogSpace b where b.id = id")
        public List<BlogSpace> getBlogSpaceById(@Param("id") long id);

}
