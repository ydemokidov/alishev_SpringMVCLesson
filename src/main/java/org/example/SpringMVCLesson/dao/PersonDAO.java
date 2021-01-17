package org.example.SpringMVCLesson.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.SpringMVCLesson.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Component
public class PersonDAO {
    private static final String URL="jdbc:postgresql://localhost:5432/springmvclesson";
    private static final String username = "postgres";
    private static final String password = "admin";

    private static Connection connection;
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
    }

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
        List<Person> people = new ArrayList<>();

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
        return people;
    }

    public Person show(int id){
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE ID =?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //переходим на первую запись
            resultSet.next();

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
        /*person.setId(++peopleCount);
        people.add(person);*/
        try {
            //Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(?,?,?,?)");
            preparedStatement.setInt(1,++peopleCount);
            preparedStatement.setString(2,person.getName());
            preparedStatement.setInt(3,person.getAge());
            preparedStatement.setString(4,person.getEmail());
            //String SQL = "INSERT INTO Person VALUES("+(++peopleCount)+",'"+person.getName()+"',"+person.getAge()+",'"+person.getEmail()+"'";
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatePerson){
        /*Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());*/
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name=?,age=?,email=? WHERE id=?");
            preparedStatement.setString(1,updatePerson.getName());
            preparedStatement.setInt(2,updatePerson.getAge());
            preparedStatement.setString(3,updatePerson.getEmail());
            preparedStatement.setInt(4,id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int id) {
        //people.removeIf(p -> p.getId() == id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
