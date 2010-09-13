<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit image Gallery</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New</g:link></span>
        </div>
        <div class="body">
            <h1>Edit image Gallery</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gallery}">
            <div class="errors">
                <g:renderErrors bean="${gallery}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${gallery?.id}" />
                <g:hiddenField name="version" value="${gallery?.version}" />
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
                    <span class="button"><g:actionSubmit class="save" action="update" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="Delete" onclick="return confirm('Are you sure?');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
