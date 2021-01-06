<%@ tag trimDirectiveWhitespaces="true" pageEncoding="utf-8" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.0.0/mdb.min.css"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>" />
    <title>${pageTitle}</title>
</head>
<body>

    <div class="bar-mixing-logo">
        <img class="img" src="<c:url value="/resources/images/image.png"/>">
    </div>

    <main>

        <jsp:doBody />

    </main>

<script>
    var contextPath = "${pageContext.servletContext.contextPath}";
</script>

<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<!-- MDB -->
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.0.0/mdb.min.js"
></script>
<script src="<c:url value="/resources/js/script.js"/>"></script>
</body>
</html>