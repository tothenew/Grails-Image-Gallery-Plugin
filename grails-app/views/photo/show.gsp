<%@ page import="photogallery.Photo" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="photo.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: photoInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="photo.thumbnail.label" default="Thumbnail"/></td>

                <td valign="top" class="value"><img src="${createLink(action:'showThumbNail',controller:'photo',id:photoInstance?.id)}"  /></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="photo.caption.label" default="Caption"/></td>

                <td valign="top" class="value">${fieldValue(bean: photoInstance, field: "caption")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="photo.image.label" default="Image"/></td>

                <td valign="top" class="value"><img src="${createLink(action:'showPhoto',controller:'photo',id:photoInstance?.id)}" width="100" height="100" /></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="photo.width.label" default="Width"/></td>

                <td valign="top" class="value">${fieldValue(bean: photoInstance, field: "width")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="photo.height.label" default="Height"/></td>

                <td valign="top" class="value">${fieldValue(bean: photoInstance, field: "height")}</td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${photoInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
