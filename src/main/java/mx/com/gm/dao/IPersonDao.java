package mx.com.gm.dao;

import mx.com.gm.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonDao extends JpaRepository<Person, Long> { }
