package repository.impl;

import domain.models.Student;
import exceptions.ServiceJdbcException;
import exceptions.UniversityException;
import mapping.dtos.StudentDto;
import mapping.mappers.StudentMapper;
import repository.Repository;
import repository.StudentRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryimpl implements Repository<StudentDto> {
    private Connection conn;
    public StudentRepositoryimpl(Connection conn) {
        this.conn = conn;
    }

    private StudentDto createStudent(ResultSet resultSet)throws SQLException{
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setSemester(resultSet.getString("semester"));
        return StudentMapper.mapFrom(student);
    }

    public List<StudentDto> list() throws ServiceJdbcException {
        List<StudentDto> studentList = new ArrayList<StudentDto>();
        try(Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM student")){

            while(rs.next()){

                StudentDto student = createStudent(rs);
                studentList.add(student);

            }

        } catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return studentList;
    }
    public StudentDto byId(Long id){
        StudentDto student = null;
        try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM student WHERE id =?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                student = createStudent(resultSet);
            }
            resultSet.close();
        }catch(SQLException e){
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }return student;
    }
    public void delete(Long id){
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("DELETE FROM student WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void save(StudentDto studentDto){
        String sql;
        Student student = StudentMapper.mapFrom(studentDto);
        if(student.getId() != null && student.getId()>0){
            sql = "UPDATE student SET name=?, email=?, semester=? WHERE id=?";
        }else{
            sql = "INSERT INTO student (name, email, semester) VALUES (?,?,?);";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getSemester());
            if(student.getId() != null && student.getId()>0){
                stmt.setLong(4, student.getId());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
