package repository.impl;

import domain.models.Teacher;
import exceptions.ServiceJdbcException;
import mapping.dtos.TeacherDto;
import mapping.mappers.TeacherMapper;
import repository.Repository;
import repository.TeacherRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryimpl implements Repository<TeacherDto> {

    private Connection conn;
    public TeacherRepositoryimpl(Connection conn) {
        this.conn = conn;
    }
    public TeacherDto createTeacher(ResultSet resultSet)throws SQLException{
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        return TeacherMapper.mapFrom(teacher);
    }
    public List<TeacherDto> list(){
        List<TeacherDto> teacherList = new ArrayList<TeacherDto>();
        try(Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM teacher")){
            while(rs.next()){
                TeacherDto teacher = createTeacher(rs);
                teacherList.add(teacher);
            }

        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return teacherList;
    }
    public TeacherDto byId(Long id){
        TeacherDto teacher = null;
        try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM teacher WHERE id =?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                teacher = createTeacher(resultSet);
            }
            resultSet.close();
        }catch(SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return teacher;
    }
    public void delete(Long id){
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("DELETE  FROM teacher WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
    public void save(TeacherDto teacherDto){
        String sql;
        Teacher teacher = TeacherMapper.mapFrom(teacherDto);
        if(teacher.getId() != null && teacher.getId()>0){
            sql = "UPDATE teacher SET name=?, email=? WHERE id=?";
        }else{
            sql = "INSERT INTO teacher (name, email) VALUES (?, ?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            if(teacher.getId() != null && teacher.getId()>0){
                stmt.setLong(3, teacher.getId());
            }
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
