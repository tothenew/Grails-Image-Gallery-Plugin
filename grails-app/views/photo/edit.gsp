<%@ page import="photogallery.Photo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Photo</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New</g:link></span>
        </div>
        <div class="body">
            <h1></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${photo}">
            <div class="errors">
                <g:renderErrors bean="${photo}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post"  enctype="multipart/form-data">
                <g:hiddenField name="id" value="${photo?.id}" />
                <g:hiddenField name="version" value="${photo?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>                      
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="caption">Caption</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photo, field: 'caption', 'errors')}">
                                    <g:textField name="caption" value="${photo?.caption}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="image">Image</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photo, field: 'image', 'errors')}">
                                    <input type="file" id="image" name="image" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="width">Width</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photo, field: 'width', 'errors')}">
                                    <g:textField name="width" value="${fieldValue(bean: photo, field: 'width')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="height">Height</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: photo, field: 'height', 'errors')}">
                                    <g:textField name="height" value="${fieldValue(bean: photo, field: 'height')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="Delete" onclick="return confirm('Are you sure?');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
