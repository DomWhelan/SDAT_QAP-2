package com.keyin.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "members",path = "members")
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findById(@Param("id") long id);

    List<Member> findAll();

}
