package repository.impl;

import domain.models.Student;
import repository.StudentRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryimpl implements StudentRepository {
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }
    private Student createStudent(ResultSet resultSet)throws SQLException{
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setSemester(resultSet.getString("semester"));
        return student;
    }

    public List<Student> list(){
        List<Student> studentList = new ArrayList<Student>();
        try(Statement stat = getConnection().createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM student")){

            while(rs.next()){

                Student student = createStudent(rs);
                studentList.add(student);

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return studentList;
    }
    public Student byId(Long id){
        Student student = null;
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM student WHERE id =?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                student = createStudent(resultSet);
            }
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        }return student;
    }
    public void delete(int id){
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("DELETE FROM student WHERE id =?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void save(Student subject){
        String sql;
        if(subject.getId() != null && subject.getId()>0){
            sql = "UPDATE student SET name=?, email=?, semester=? WHERE id=?";
        }else{
            sql = "INSERT INTO student (name, email, semester) VALUES (?,?,?);";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getEmail());
            stmt.setString(3, subject.getSemester());
            if(subject.getId() != null && subject.getId()>0){
                stmt.setLong(4, subject.getId());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
