<%@ page import="photogallery.Gallery" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Create</title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list">List</g:link></span>
</div>
<div class="body">
    <h1>Add Photos to ${gallery?.name}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:form action="savePhoto" controller="gallery">
        <g:hiddenField name="id" value="${gallery?.id}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="photo">Photos</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gallery, field: 'name', 'errors')}">
                        <g:each in="${photos}" var="photo">
                            <g:checkBox value="${photo?.id}" checked="${galleryPhotos.contains(photo)}" name="photo"/>
                            ${photo?.caption}
                        </g:each>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save" value="Create"/></span>
        </div>
    </g:form>
</div>

</body>
</html>
