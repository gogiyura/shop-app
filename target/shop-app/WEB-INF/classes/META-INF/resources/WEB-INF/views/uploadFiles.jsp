<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.11.2021
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE >
<html>
<head>
    <title>Upload files</title>
</head>
<body>

<div style="padding:5px; color:red;font-style:italic;">
    ${errorMessage}
</div>

<h2>Upload Files</h2>

<form method="post" action="${pageContext.request.contextPath}/uploadFile"
      enctype="multipart/form-data">

    Select file to upload:
    <br />
    <input type="file" name="file"  />
    <br />
    <input type="file" name="file" />
    <br />
    Description:
    <br />
    <input type="text" name="description" size="100" />
    <br />
    <br />
    <input type="submit" value="Upload" />
</form>

</body>
</html>

