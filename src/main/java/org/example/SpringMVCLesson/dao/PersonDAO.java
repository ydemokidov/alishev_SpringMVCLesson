package org.example.SpringMVCLesson.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.SpringMVCLesson.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Component
public class PersonDAO {
    List<Person> people;
    static int peopleCount=0;
    {
        people = new ArrayList<>();
        people.add(new Person(++peopleCount,"Tom",24,"tom@mail.ru"));
        people.add(new Person(++peopleCount,"Ted",52,"ted@mail.ru"));
        people.add(new Person(++peopleCount,"Bill",18,"biil@mail.ru"));
        people.add(new Person(++peopleCount,"Mary",34,"mary@mail.ru"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream()
                .filter(p->p.getId()==id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++peopleCount);
        people.add(person);
    }

    public void update(int id, Person updatePerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
