package repository.impl;

import domain.models.Subject;
import domain.models.Teacher;
import repository.SubjectRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryimpl implements SubjectRepository {
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConexionDB.getInstance();
    }
    private Subject createSubject(ResultSet resultSet)throws SQLException{
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("subject"));
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("teacherid"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));

        subject.setTeacher(teacher);
        return subject;
    }
    public List<Subject> list(){
        List<Subject> subjectList = new ArrayList<Subject>();
        try(Statement stat = getConnection().createStatement();
            ResultSet rs = stat.executeQuery("SELECT sub.id, sub.name AS subject, sub.teacherid, tea.name," +
                    " tea.email FROM subject AS sub, teacher AS tea WHERE sub.teacherid = tea.id;")){
            while(rs.next()){
                Subject subject = createSubject(rs);
                subjectList.add(subject);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return subjectList;
    }
    public Subject byId(Long id){
        Subject subject = null;
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT sub.id, sub.name AS subject, sub.teacherid, tea.name," +
                        " tea.email FROM subject AS sub, teacher AS tea WHERE sub.teacherid = tea.id " +
                        "AND sub.id = ?;")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                subject = createSubject(resultSet);
            }
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return subject;
    }
    public void delete(int id){
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("DELETE FROM subject WHERE id =?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(Subject subject){
        String sql;
        if(subject.getId() != null && subject.getId()>0){
            sql = "UPDATE subject SET name=?, teacherid=? WHERE id=?";
        }else{
            sql = "INSERT INTO subject (name, teacherid) VALUES (?, ?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, subject.getName());
            stmt.setLong(2, subject.getTeacher().getId());
            if(subject.getId() != null && subject.getId()>0){
                stmt.setLong(3, subject.getId());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
