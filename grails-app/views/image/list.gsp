<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:javascript library="jquery" plugin="jquery"/>
    <ig:resources/>
    <title>image</title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="create" action="create">New</g:link></span>
</div>
<g:if test="${gallery?.id}">
    <ig:show galleryInstance="${gallery}" options="height:500,preload:2,carousel:true,transition:'pulse',image_pan_smoothness:5" showInLightBox="true"/>
</g:if>
<g:else>
    <div class="body">
        <h1>List image</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div class="list">
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="id" title="Id"/>
                    <g:sortableColumn property="thumbnail" title="Image"/>
                    <g:sortableColumn property="caption" title="Caption"/>
                    <g:sortableColumn property="width" title="Width"/>
                    <g:sortableColumn property="height" title="Height"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${images}" status="i" var="image">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${image.id}">${image.id}</g:link></td>
                        <td><g:link action="show" id="${image.id}"><img src="${createLink(action: 'showImage', controller: 'image', id: image?.id, params: [thumbnail: true])}"/></g:link></td>
                        <td><g:link action="show" id="${image.id}">${image.caption}</g:link></td>
                        <td><g:link action="show" id="${image.id}">${image.width}</g:link></td>
                        <td><g:link action="show" id="${image.id}">${image.height}</g:link></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <div class="paginateButtons">
            <g:paginate total="${imageTotal}"/>
        </div>
    </div>
</g:else>
</body>
</html>
