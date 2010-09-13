<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>image</title>
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
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name">Id</td>

                <td valign="top" class="value">${image.id}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name">Thumbnail</td>

                <td valign="top" class="value"><img src="${createLink(action:'showImage',controller:'image',id:image?.id, params:[thumbnail:'true'])}"  /></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name">Caption</td>

                <td valign="top" class="value">${image.caption}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name">Image</td>

                <td valign="top" class="value"><img src="${createLink(action:'showImage',controller:'image',id:image?.id)}" width="100" height="100" /></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name">Width</td>

                <td valign="top" class="value">${image.width}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name">Height</td>

                <td valign="top" class="value">${image.height}</td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${image?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit" value="Edit"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="Delete" onclick="return confirm('Are you sure?');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
