<%@ include file="layout/header.jsp" %>

<h2>Skills List</h2>
<a href="<c:url value='/skills/add'/>" class="btn">Add New Skill</a>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Skill Name</th>
            <th>Level</th>
            <th>Person</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${skills}" var="skill">
            <tr>
                <td>${skill.id}</td>
                <td>${skill.skillName}</td>
                <td>${skill.level}</td>
                <td>${skill.person.name}</td>
                <td>
                    <a href="<c:url value='/skills/edit/${skill.id}'/>" class="btn btn-warning">Edit</a>
                    <a href="<c:url value='/skills/delete/${skill.id}'/>" class="btn btn-danger" 
                       onclick="return confirm('Are you sure you want to delete this skill?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="layout/footer.jsp" %> 