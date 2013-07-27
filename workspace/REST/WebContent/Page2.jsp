<%@ 
	
	page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List,Resturants.Resturant"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Works

<% 
	List<Resturant> r = (List<Resturant>)(request.getAttribute("rest"));
	for(Resturant res : r){
		out.println(res.getName());
	}
	%>
</body>
</html>