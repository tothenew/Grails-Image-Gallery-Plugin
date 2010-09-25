<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Show image Gallery</title>
    <g:javascript library="jquery" plugin="jquery"/>
    <ig:resources/>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list">List image Gallery</g:link></span>
    <span class="menuButton"><g:link class="create" action="create">Create image Gallery</g:link></span>
</div>
<div class="body">
    <h1>
        <span class="menuButton"><g:link class="list" action="list" controller="image" id="${gallery?.id}">images</g:link></span>
        <span class="menuButton"><g:link class="create" action="addImage" id="${gallery?.id}">Add images</g:link></span>
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
    <div style="width:300px;height:200px;float:right">
        <ig:show galleryInstance="${gallery}" options="height:500,preload:2,carousel:true,transition:'pulse',image_pan_smoothness:5" showInLightBox="true"/>
    </div>
</div>
</body>
</html>
