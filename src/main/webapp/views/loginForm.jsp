<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="login" value="/login.do" />


<h1>Authentification</h1>
<p><c:out value="${message}" default="Identifiez-vous :"/></p>

<form method="POST" action="${login}">
    <label>username :</label>
    <input name="user_name" type="text"
        value="<c:out value="${user.userName}"/>"/>
    <label>password : </label>
    <input name="password" type="password"
        value="<c:out value="${user.password}"/>"/>
    <br/>
    <br/>
    <label>Validation :</label>
    <input name="ok" type="submit" value="Ok"/>
</form>