package repository.impl;

import domain.models.Teacher;
import repository.TeacherRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryimpl implements TeacherRepository{

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConexionDB.getInstance();
    }
    //prueba commits
    public Teacher createTeacher(ResultSet resultSet)throws SQLException{
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        return teacher;
    }
    public List<Teacher> list(){
        List<Teacher> teacherList = new ArrayList<Teacher>();
        try(Statement stat = getConnection().createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM teacher")){
            while(rs.next()){
                Teacher teacher = createTeacher(rs);
                teacherList.add(teacher);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return teacherList;
    }
    public Teacher byId(Long id){
        Teacher teacher = null;
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM teacher WHERE id =?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                teacher = createTeacher(resultSet);
            }
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return teacher;
    }
    public void delete(Long id){
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("DELETE  FROM teacher WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(Teacher teacher){
        String sql;
        if(teacher.getId() != null && teacher.getId()>0){
            sql = "UPDATE teacher SET name=?, email=? WHERE id=?";
        }else{
            sql = "INSERT INTO teacher (name, email) VALUES (?, ?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            if(teacher.getId() != null && teacher.getId()>0){
                stmt.setLong(3, teacher.getId());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
