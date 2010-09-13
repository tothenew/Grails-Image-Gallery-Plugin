<%@ page import="org.grails.plugins.imagegallery.Gallery" contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Simple GSP page</title>
    <g:javascript library="jquery" plugin="jquery"/>
    <ig:resources/>
</head>
<body>Place your content here
<div style="width:300px">
    <ig:show galleryInstance="${Gallery.get(1)}" theme="classic" showInLightBox="true"/>
</div>
</body>
</html>