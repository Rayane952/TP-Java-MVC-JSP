<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="course" value="/course.do" />
<c:url var="logout" value="/logout.do" />


<h1>Bienvenue <c:out value="${user.userName}"/></h1>


<header>
<h1>Séance</h1>
</header>
<main>
<nav>
<ul>
<li><a href="${logout}">Logout</a></li>
</nav>
    
<form method="POST" action="${course}">
<select name="type">
<option value="COURS">COURS</option>
<option value="TD">TD</option>
<option value="TP">TP</option>
</select>
<p><input type="submit" value="Valider" /></p>
</form>

</main>
