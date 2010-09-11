<%@ page import="photogallery.Gallery" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Create Photo Gallery</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Photo Gallery</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gallery}">
            <div class="errors">
                <g:renderErrors bean="${gallery}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: gallery, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${gallery?.name}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
