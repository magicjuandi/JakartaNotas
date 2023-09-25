package repository.impl;

import domain.models.Subject;
import domain.models.Teacher;
import mapping.dtos.SubjectDto;
import mapping.mappers.SubjectMapper;
import repository.Repository;
import repository.SubjectRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryimpl implements Repository<SubjectDto> {
    private Connection conn;
    public SubjectRepositoryimpl(Connection conn) {
        this.conn = conn;
    }
    private SubjectDto createSubject(ResultSet resultSet)throws SQLException{
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("subject"));
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("teacherid"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));

        subject.setTeacher(teacher);
        return SubjectMapper.mapFrom(subject);
    }
    public List<SubjectDto> list(){
        List<SubjectDto> subjectList = new ArrayList<SubjectDto>();
        try(Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT sub.id, sub.name AS subject, sub.teacherid, tea.name," +
                    " tea.email FROM subject AS sub, teacher AS tea WHERE sub.teacherid = tea.id;")){
            while(rs.next()){
                SubjectDto subject = createSubject(rs);
                subjectList.add(subject);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return subjectList;
    }
    public SubjectDto byId(Long id){
        SubjectDto subject = null;
        try(PreparedStatement preparedStatement = conn
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
        }
        return subject;
    }
    public void delete(Long id){
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("DELETE FROM subject WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void save(SubjectDto subjectDto){
        String sql;
        Subject subject = SubjectMapper.mapFrom(subjectDto);
        if(subject.getId() != null && subject.getId()>0){
            sql = "UPDATE subject SET name=?, teacherid=? WHERE id=?";
        }else{
            sql = "INSERT INTO subject (name, teacherid) VALUES (?, ?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, subject.getName());
            stmt.setLong(2, subject.getTeacher().getId());
            if(subject.getId() != null && subject.getId()>0){
                stmt.setLong(3, subject.getId());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
