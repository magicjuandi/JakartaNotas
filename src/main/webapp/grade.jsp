<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28/09/2023
  Time: 2:14 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3><%= "Formulario notas" %>
</h3>

<form action="grade-form" method="post">
    <div class="row mb-3">
        <label for="id_student" class="col-form-label col-sm-2">Student Id</label>
        <div class="col-sm-4"><input type="text" name="id_student" id="id_student" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <label for="id_subject" class="col-form-label col-sm-2">Subject Id</label>
        <div class="col-sm-4"><input type="text" name="id_subject" id="id_subject" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <label for="grade" class="col-form-label col-sm-2">Grade</label>
        <div class="col-sm-4"><input type="text" name="grade" id="grade" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <div>
            <input type="submit" value="Enviar" class="btn btn-primary">
        </div>
    </div>
</form>
<br/>
<a href="grade-form">Vamos a GradeController</a>
</body>
</html>
