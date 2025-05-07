<%@ include file="layout/header.jsp" %>

<h2>Add New Person</h2>

<form:form action="${pageContext.request.contextPath}/persons/add" method="post" modelAttribute="person">
    <div class="form-group">
        <label for="name">Name:</label>
        <form:input path="name" id="name" required="required"/>
        <form:errors path="name" cssClass="error"/>
    </div>
    
    <div class="form-group">
        <label for="age">Age:</label>
        <form:input path="age" id="age" type="number" required="required"/>
        <form:errors path="age" cssClass="error"/>
    </div>
    
    <button type="submit" class="btn">Add Person</button>
    <a href="<c:url value='/persons'/>" class="btn btn-warning">Cancel</a>
</form:form>

<%@ include file="layout/footer.jsp" %> 