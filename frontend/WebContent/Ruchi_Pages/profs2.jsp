<%@ page import="java.util.ArrayList" %>
<%@ page import="export.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>professors</title>
	<link rel="stylesheet" type="text/css" href="profs.css">
	<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<style>
body {
    font-family: 'Montserrat';
}

</style>
</head>
<body>
	<div class="fb-header-base">
	</div>
	<div class="fb-header">
        <ul id="home" onclick="location.href='../www.google.com';" style="cursor: pointer;">
            <a id="img1" class="fb-header">rate</a>
            <a id="img2" class="fb-header">sc</a>

        </ul>
        <form>
            <input class="fb-header" id="search" placeholder="" aria-label="Search">
            <button class="fb-header" id="search-btn">Search</button>
            <button class="fb-header" id="btn-topright">My Reviews</button>
            <button class="fb-header" id="btn-topright2">Sign Out</button>
        </form>
    </div>
    
     <%
    ArrayList<Professor> professors=(ArrayList<Professor>)request.getAttribute("professors");
    if(professors.size()==0){%>
		<div id="title"><h2>Sorry, no results were found.</h2></div>
    <%}
    for(int i=0;i<professors.size();i++){
    	Professor p=professors.get(i);
    	HashMap<String, CourseProfessor> courses=p.get_courses();
    	System.out.println(p.get_fname());
    	System.out.flush();
    	String fname=Character.toUpperCase(p.get_fname().charAt(0)) + p.get_fname().substring(1);
    	String lname=Character.toUpperCase(p.get_lname().charAt(0)) + p.get_lname().substring(1);
    %>

    <div id="resultBox">
        <div id="course-box">
            <div id="professor-id"><%=p.get_fname()+" "+p.get_lname()%></div>
        </div>
        <div id="rating" name="rating" style="color: #A30101">
            <br />
            <strong> <%=p.get_overall() %> </strong>
        </div>
        <div id="overall">
            Overall Rating 
        </div>
        <div id=title>Courses</div>
        
        <div id="courseList">
        <%
        		Iterator hiterator=courses.entrySet().iterator();
        		int j=0;
        	while(hiterator.hasNext() && j<3){
        		j++;
				Map.Entry mapElement = (Map.Entry)hiterator.next();
				CourseProfessor cp=(CourseProfessor)mapElement.getValue(); 
				String params="?pre="+cp.get_prefix()+"&num="+Integer.toString(cp.get_courseNum())+"&pid="+p.get_professorID();
			%>
            <div id="class"><%=cp.get_prefix()+" "+Integer.toString(cp.get_courseNum())+" : "+cp.get_name()%></div>
            <div id="rate"><%=cp.get_overall() %></div>
             
        </div>
        <%} %>
    </div> 
    <%}%>
</body>
</html>