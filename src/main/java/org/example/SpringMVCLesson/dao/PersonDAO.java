package org.example.SpringMVCLesson.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.SpringMVCLesson.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Component
public class PersonDAO {
    //private static final String URL="jdbc:postgresql://localhost:5432/springmvclesson";
    //private static final String username = "postgres";
    //private static final String password = "admin";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*private static Connection connection;
    static{
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    //database stub
    static int peopleCount=0;
    /*List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person(++peopleCount,"Tom",24,"tom@mail.ru"));
        people.add(new Person(++peopleCount,"Ted",52,"ted@mail.ru"));
        people.add(new Person(++peopleCount,"Bill",18,"biil@mail.ru"));
        people.add(new Person(++peopleCount,"Mary",34,"mary@mail.ru"));
    }*/

    public List<Person> index(){
        //return people;
        /*List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM PERSON";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;*/
        //return jdbcTemplate.query("SELECT * FROM Person",new PersonRowMapper());
        return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        //return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},new PersonRowMapper()).stream().findAny().orElse(null);
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES (?,?,?,?)",++peopleCount,person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET name=?, age=?,email=? WHERE id=?",updatePerson.getName(),updatePerson.getAge(),updatePerson.getEmail(),updatePerson.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
}
