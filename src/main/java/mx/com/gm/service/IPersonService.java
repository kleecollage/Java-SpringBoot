package mx.com.gm.service;

import mx.com.gm.domain.Person;

import java.util.List;

public interface IPersonService {
    public List<Person> listPersons();

    public Person findPerson(Person person);

    public void save(Person person);

    public void delete(Person person);
}
