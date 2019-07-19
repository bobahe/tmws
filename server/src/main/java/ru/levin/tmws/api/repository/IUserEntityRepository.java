package ru.levin.tmws.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.levin.tmws.entity.User;

@Repository
public interface IUserEntityRepository extends JpaRepository<User, String> {
}
