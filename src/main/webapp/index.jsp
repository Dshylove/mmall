<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Hello World!</title>
</head>
<body>
<h2>Hello World!</h2>

Spring MVC上传文件
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="Spring MVC上传文件"/>
</form>

富文本图片上传文件
<form name="form1" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="富文本图片上传文件"/>
</form>

</body>
</html>
