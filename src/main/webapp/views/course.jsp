<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:url var="logout" value="/logout.do" />
<c:url var="home" value="/home.do" />


<h1>Bienvenue <c:out value="${user.userName}"/></h1>


<header>
<h1>Séance de ${seance.type}</h1>
</header>
<main>
<nav>
<ul>
<li><a href="${home}">Home</a></li>
<li><a href="${logout}">Logout</a></li>

</nav>

<div class="intitule">
<h2>Intitulé : ${seance.intitule}</h2>
</div>

<div class="contenu">
    <p> ${seance.contenu}</p>  
</div>

</main>
