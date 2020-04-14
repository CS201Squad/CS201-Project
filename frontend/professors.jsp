<%@ page language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>professors</title>
	<link rel="stylesheet" type="text/css" href="professors.css">
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
            <button class="fb-header" id="btn-topright">Add Review</button>
            <button class="fb-header" id="btn-topright2">Sign Out</button>
        </form>
    </div>
   
    <%for(int i=0; i < resultVec.size(); i++) {
    %>
        <div class="resultBox">
        <table id="resultTable">
            <col width="300">
            <col width="800">
            <col width="213">
            <tr>
                <td height="220">
                    <div class="course-box">
                        <div class="professor-id"> <%=profName%> </div>
                    </div>
                    <div class="rating" name="rating" style="color: #A30101">
                        <br />
                        <strong> <%=profRating%> </strong>
                    </div>
                    <div id="overall">
                        Overall Rating 
                    </div>
                </td>
                <td align="left">
                    <!--classes -->
                    <div class=title>
                        Courses
                    </div>
                    <ul class="courseList" name="courseList">
                        <% for (int i=0; i < 3; i++) {
                        %>
                            <li> <%System.out.println(profCourses.(i));%> </li>
                        <%
                            }
                        %> 
                    </ul>
                </td>
                <td>
                    <!--ratings -->
                    <ul class="ratingList" name="ratingList">
                        <% for (int i=0; i < 3; i++) {
                        %>
                            <li> <%System.out.println(profCourseRatings.(i));%> </li>
                        <%
                            }
                        %> 
                    </ul>
                </td>
            </tr>
        </div>
      <% i++ 
    }
    %>
    

</body>
</html>