

<%@ page import="photogallary.Photo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${photoInstance}">
            <div class="errors">
                <g:renderErrors bean="${photoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post"  enctype="multipart/form-data">
                <g:hiddenField name="id" value="${photoInstance?.id}" />
                <g:hiddenField name="version" value="${photoInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="thumbnail"><g:message code="photo.thumbnail.label" default="Thumbnail" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'thumbnail', 'errors')}">
                                    <input type="file" id="thumbnail" name="thumbnail" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="caption"><g:message code="photo.caption.label" default="Caption" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'caption', 'errors')}">
                                    <g:textField name="caption" value="${photoInstance?.caption}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="image"><g:message code="photo.image.label" default="Image" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'image', 'errors')}">
                                    <input type="file" id="image" name="image" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="width"><g:message code="photo.width.label" default="Width" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'width', 'errors')}">
                                    <g:textField name="width" value="${fieldValue(bean: photoInstance, field: 'width')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="height"><g:message code="photo.height.label" default="Height" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photoInstance, field: 'height', 'errors')}">
                                    <g:textField name="height" value="${fieldValue(bean: photoInstance, field: 'height')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
