<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  Java file path
	cmd + shift + . in mac to show hidden files
   Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\MyWebApp\org\apache\jsp
 	When we create a jsp file and run it, a .java file gets created automatically by the server(Tomcat) at this path.
 	This .java file is a servlet class. And what we see as output is from this class and not jsp.
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% //a scriplet
		String str = "Hello Everyone";
		String str2 = " Hey Anchal";
	%>
	<h1><%= str + ',' + str2 %></h1>
	<center>
		<%
			for(int i=1; i<=6; i++) {
		%>
		<font size="<%= i %>">Hey Anchal!</font><br>
		<%
			}
		%>
	</center>
</body>
</html>