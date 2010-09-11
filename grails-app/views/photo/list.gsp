<%@ page import="photogallery.Photo" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Photo</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
  <span class="menuButton"><g:link class="create" action="create">New</g:link></span>
</div>
<div class="body">
  <h1>List Photo</h1>
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
      <g:each in="${photos}" status="i" var="photo">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td><g:link action="show" id="${photo.id}">${photo.id}</g:link></td>
          <td><g:link action="show" id="${photo.id}"><img src="${createLink(action: 'showPhoto', controller: 'photo', id: photo?.id, params: [thumbnail: true])}" width="100" height="100"/></g:link></td>
          <td><g:link action="show" id="${photo.id}">${photo.caption}</g:link></td>
          <td><g:link action="show" id="${photo.id}">${photo.width}</g:link></td>
          <td><g:link action="show" id="${photo.id}">${photo.height}</g:link></td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate total="${photoTotal}"/>
  </div>
</div>
</body>
</html>
