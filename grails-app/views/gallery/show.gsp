<%@ page import="photogallery.Gallery" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Show Photo Gallery</title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list">List Photo Gallery</g:link></span>
    <span class="menuButton"><g:link class="create" action="create">Create Photo Gallery</g:link></span>
</div>
<div class="body">
    <h1>
        %{--<span class="menuButton"><g:link class="list" action="list" controller="photo" id="${gallery?.id}">Photos</g:link></span>--}%
        <span class="menuButton"><g:link class="create" action="addPhoto" id="${gallery?.id}">Add Photos</g:link></span>
    </h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name">Id</td>

                <td valign="top" class="value">${gallery.id}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name">Name</td>

                <td valign="top" class="value">${gallery.name}</td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${gallery?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit" value="Edit"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="Delete" onclick="return confirm('Are you sure?');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
