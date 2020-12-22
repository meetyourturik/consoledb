package com.epam.turik.consoledb.data;

import com.epam.turik.consoledb.data.objects.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
