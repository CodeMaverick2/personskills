<%@ include file="layout/header.jsp" %>

<h2>Persons List</h2>
<a href="<c:url value='/persons/add'/>" class="btn">Add New Person</a>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${persons}" var="person">
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.age}</td>
                <td>
                    <a href="<c:url value='/persons/edit/${person.id}'/>" class="btn btn-warning">Edit</a>
                    <a href="<c:url value='/persons/delete/${person.id}'/>" class="btn btn-danger" 
                       onclick="return confirm('Are you sure you want to delete this person?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="layout/footer.jsp" %> 