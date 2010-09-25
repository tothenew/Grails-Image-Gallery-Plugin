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
<g:if test="${flash.message}">
  <div class="message">${flash.message}</div>
</g:if>
<div class="list">
  <ig:show options="height:500,preload:2,carousel:true,transition:'pulse',image_pan_smoothness:5" showInLightBox="true">
    <g:each in="${images}" status="i" var="image">
      <ig:img src="${createLink(action: 'showImage', controller: 'image', id: image?.id)}" alt="${image?.description}" title="${image?.caption}"
              thumbnailSrc="${createLink(action: 'showImage', controller: 'image', id: image?.id,params:[thumbnail:true])}"/>
    </g:each>
  </ig:show>
</div>
<div class="paginateButtons">
  <g:paginate total="${imageTotal}"/>
</div>
</body>
</html>
