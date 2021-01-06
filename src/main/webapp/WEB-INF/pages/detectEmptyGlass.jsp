<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>

<tags:main pageTitle="Detect Empty Glass">

    <c:if test="${isEmpty}">
        <div class="empty-glass-message">
            <span>
                Hey James, your cocktail seems to be empty. Would you like to go for another one?
            </span>
            <div class="empty-glass-buttons">
                <div class="no-btn">
                    <span>No, thanks</span>
                </div>
                <div class="yes-btn">
                    <span>Sure</span>
                </div>
            </div>
        </div>
    </c:if>

    <div class="message">
        Upload image of your cup or glass
    </div>
    <c:if test="${fileWasNotSelected.length() != 0}">
        <div class="ui-state-error-text error message">
            <c:out value="${fileWasNotSelected}" />
        </div>
    </c:if>
    <form method="POST" action="<c:url value="/detectEmptyGlass"/>" enctype="multipart/form-data" class="form">
        <div class="upload-image">
            <input type="file" name="file">
        </div>
        <input type="submit" class="btn btn-primary btn-rounded submit" value="Submit" />
    </form>
    <c:if test="${not empty result.classes}" >
        <div class="classification">
            <c:forEach items="${result.classes}" var="c">
                <c:out value="${c.className} : ${c.score}" />
                <br/>
            </c:forEach>
        </div>
    </c:if>
</tags:main>