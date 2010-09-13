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
    <h1>Add Images to ${gallery?.name}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:form action="saveImage" controller="gallery">
        <g:hiddenField name="id" value="${gallery?.id}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="image">images</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: gallery, field: 'name', 'errors')}">
                        <g:each in="${images}" var="image" status="index">

                            <g:checkBox value="${image?.id}" checked="${galleryimages.contains(image)}" name="image"/>
                            ${image?.caption}
                            <g:if test="${index%5==4}">
                                <br/>
                            </g:if>
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
