<%@ include file="layout/header.jsp" %>

<h2>Add New Skill</h2>

<form:form action="${pageContext.request.contextPath}/skills/add" method="post" modelAttribute="skill">
    <div class="form-group">
        <label for="skillName">Skill Name:</label>
        <form:input path="skillName" id="skillName" required="required"/>
        <form:errors path="skillName" cssClass="error"/>
    </div>
    
    <div class="form-group">
        <label for="level">Level:</label>
        <form:input path="level" id="level" type="number" min="1" max="10" required="required"/>
        <form:errors path="level" cssClass="error"/>
    </div>
    
    <div class="form-group">
        <label for="person">Person:</label>
        <form:select path="person.id" id="person" required="required">
            <form:option value="" label="-- Select Person --"/>
            <form:options items="${persons}" itemValue="id" itemLabel="name"/>
        </form:select>
        <form:errors path="person" cssClass="error"/>
    </div>
    
    <button type="submit" class="btn">Add Skill</button>
    <a href="<c:url value='/skills'/>" class="btn btn-warning">Cancel</a>
</form:form>

<%@ include file="layout/footer.jsp" %> 