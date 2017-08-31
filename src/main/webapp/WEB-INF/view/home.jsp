<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="20" charset=UTF-8">
<title>Ping Service</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<link type="text/css" rel="stylesheet"
	href="../resources/static/css/bootstrap.min.css" />
<style type="text/css">
.true-value {
	font-size: 16px;
	color: green;
}

.false-value {
	font-size: 16px;
	color: red;
	td
	{
	padding
	:
	1em;
}

}
@
-webkit-keyframes invalid {from { color:red;
	
}

to {
	color: inherit;
}

}
@
-moz-keyframes invalid {from { color:red;
	
}

to {
	color: inherit;
}

}
@
-o-keyframes invalid {from { color:red;
	
}

to {
	color: inherit;
}

}
@
keyframes invalid {
	from {color: red;
}

to {
	color: inherit;
}

}
.invalid {
	-webkit-animation: invalid 1s infinite; /* Safari 4+ */
	-moz-animation: invalid 1s infinite; /* Fx 5+ */
	-o-animation: invalid 1s infinite; /* Opera 12+ */
	animation: invalid 1s infinite; /* IE 10+ */
}

@
-webkit-keyframes valid {from { color:green;
	
}

to {
	color: inherit;
}

}
@
-moz-keyframes valid {from { color:green;
	
}

to {
	color: inherit;
}

}
@
-o-keyframes valid {from { color:green;
	
}

to {
	color: inherit;
}

}
@
keyframes valid {
	from {color: green;
}

to {
	color: inherit;
}

}
.valid {
	-webkit-animation: valid 1s infinite; /* Safari 4+ */
	-moz-animation: valid 1s infinite; /* Fx 5+ */
	-o-animation: valid 1s infinite; /* Opera 12+ */
	animation: valid 1s infinite; /* IE 10+ */
}
table {
    border: 1px solid lightgray;
}
table th {
    background-color: silver;
    text-align: center;
    padding: 10px;
}
table td {
    width: 200px;
    border: 2px dotted silver;
    word-break: break-all;
}
.grayColor{
color: lightgray;
}
</style>
</head>
<body>
	<div >
		<div class="row">
			<div>
				<a href="/pingservice/home"><img
					src="../resources/static/images/logo.jpg"
					style="width: 10%; height: 6%" /></a>
			</div>
		</div>
		<div class="page-header" id="banner">
			<div class="row">
				<div class="col-lg-8 col-md-7 col-sm-6">
					<h4>List of Applications</h4>
				</div>
			</div>
		</div>
		<div >
		
		<table>
    <tr>
        <th>ID</th>
        <th>Application Name</th>
        <th>Server Type -Availability</th>						
		
    </tr>
    <tr>
       	<c:forEach items="${applications}" var="app">
						<tr><c:out value="${index+1}" /></tr>
						<tr><c:out value="${app.applicationName}" /></tr>
						<c:forEach items="${app.applicationUrl}" var="appurl">
					                <td>
					                <tr >
					                <p>${appurl.serverType.name}</p>
									<p class="${appurl.status == true ? 'true-value valid':'false-value invalid'}">${appurl.status==true?'AVAILABLE':'NOT AVAILABLE'}</p>
								
									
									</tr></td>
									
							
							
						</c:forEach>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss "
								value="${app.lastSyncTime}" /></td>


					</c:forEach>
    </tr>    
</table>
		
		</div>
	</div>
	<footer>
	<div >
		<p class="navbar-text pull-left">
			&copy;
			<script>
				var date = new Date();
				document.write(date.getFullYear() + " ")
			</script>
			<a href="https://www.cma-cgm.com" target="_blank">CMA CGM SSC IT
				Projects</a>
		</p>
	</div>
	</footer>
</body>
</html>


