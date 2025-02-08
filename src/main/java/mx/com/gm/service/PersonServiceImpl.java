package mx.com.gm.service;

import mx.com.gm.dao.IPersonDao;
import mx.com.gm.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {
    // IPersonDao injection
    private final IPersonDao personDao;
    public PersonServiceImpl(IPersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> listPersons() {
        return (List<Person>) personDao.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Person findPerson(Person person) {
        return personDao.findById(person.getIdPerson()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Person person) {
        personDao.save(person);
    }

    @Override
    @Transactional
    public void delete(Person person) {
        personDao.delete(person);
    }

}
