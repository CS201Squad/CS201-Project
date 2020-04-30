<%@ page import="java.util.ArrayList" %>
<%@ page import="export.*" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
    integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
    crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>

<head>
    <link type="text/css" rel="stylesheet" href="style.css" />
    <meta name="Description" content="rateSC profile page" />


</head>

<style>
        html {
        font-family: Montserrat;
    }

    h2 {
        margin-left: 3%;
        margin-bottom: 5%;
    }

    .fb-header-base {
        width: 100%;
        height: 60px;
        position: absolute;
        top: 0;
        left: 0;
        color: white;
        z-index: 7;
        -webkit-box-shadow: 0 3px 8px rgba(0, 0, 0, .25);
    }

    #home {
        display: block;
        margin-top: 5px;
        width: 130px;
        height: 40px;
        left: 50px;
        background-color: #990000;
        color: white;
    }

    .fb-header {
        width: 100%;
        height: 60px;
        position: absolute;
        background: #990000;
        top: 0;
        left: 0;
        color: white;
        z-index: 12;

    }
    .top-bar{
      width: 100%;
      height: 60px;
      position: fixed;
    }
    #img1 {
        left: 38px;
        height: 30px;
        width: 300px;
        top: 5px;
        color: white;
        font-size: 30px;
    }

    #img2 {
        left: 90px;
        height: 30px;
        width: 300px;
        top: 5px;
        color: #FFCC00;
        font-size: 30px;
        font-weight: bold;
    }

    #search {
       
        border-radius: 8px;
        border: none;
        left: 25%;
        height: 45%;
        width: 30%;
        top: 25%;
        background: #7a190c;
        color: #white;
        font-size: 15px;
        
    }

    #search-btn{
        border-radius: 14px;
        border: 1px solid white;
        left: 56%;
        height: 50%;
        width: 6%;
        top: 25%;
        color: white;
        font-size: 12px;
    }
    #search-btn:hover {
        border-radius: 14px;
        border: none;
        left: 56%;
        height: 50%;
        width: 6%;
        top: 25%;
        color: white;
        background: #7a190c;
        font-size: 12px;
    }
    #search-btn2{
        border-radius: 14px;
        border: 1px solid white;
        left: 63%;
        height: 50%;
        width: 6%;
        top: 25%;
        color: white;
        font-size: 12px;
    }
    #search-btn2:hover {
      border-radius: 14px;
      border: none;
      left: 63%;
      height: 50%;
      width: 6%;
      top: 25%;
      color: white;
      background: #7a190c;
      font-size: 12px;
    }
    #btn-topright {
        border-radius: 8px;
        border: 0.8px solid white;
        left: 81%;
        height: 50%;
        width: 8%;
        top: 25%;
        text-align: center;
        color: white;
        font-size: 12px;
    }
    a{
	text-decoration: none;
	color:#000000
	}


    #btn-topright:hover {
        border-radius: 8px;
        border: none;
        left: 81%;
        height: 50%;
        width: 8%;
        top: 25%;
        text-align: center;
        background: #7a190c;
        font-size: 12px;
    }
    #btn-topright2 {
        border-radius: 8px;
        border: 0.8px solid white;
        left: 90%;
        height: 50%;
        width: 8%;
        top: 25%;
        color: white;
        font-size: 12px;
    }

    #btn-topright2:hover {
        border-radius: 8px;
        border: 0.8px none;
        left: 90%;
        height: 50%;
        width: 8%;
        top: 25%;
        text-align: center;
        background: #7a190c;
        font-size: 12px;
    }

    .row:after {
        content: "";
        display: table;
        clear: both;
    }
    #photo{
        margin-top: 4%;
    }
    .left {
        width: 30%;
        padding-left: 6%;
        padding-top: 8%;
        height: 60px;
        width: 300px;
        bottom: 80px;
        align-content:center;
        color: hsl(0, 100%, 30%);
    }

    .right {
        width: 80%;
        padding-left: 10%;
        padding-top: 8%;
        height: 95%;
        width: 70%;
        bottom: 100%;
        color: #990000;
        margin-bottom: 4%;
        background-position: right;
        background-origin: padding-box;
    }
    .example1 {
        
        border-radius: 8px;
        border: none;
        padding: 25px;
        width: 95%;
        background: rgb(235, 233, 233);
        background-repeat: no-repeat;
        height: 95%;
        background-origin: padding-box;
}
    .form2 {
        padding-left: 4%;
        padding-top: 10%;
        height: 60px;
        width: 250px;
        color: #990000;


    }
    #charthead {
        background-color: ivory;
    }
    #photo {
        border-radius: 10px;
        margin-top: 10%;
        margin-bottom: 4%;
        display: block;
        max-width:150px;
        max-height:150px;
        width: auto;
        height: auto;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

    }
</style>

<body>
	<%
	Student s=(Student)request.getAttribute("s");
	ArrayList<Review> reviews=s.get_Reviews();
	%>
    <div class="fb-header-base">
    </div>
    <div class="fb-header">
        <!-- Here location should be changed to the home page -->
        <ul id="home" onclick="location.href='../www.google.com';" style="cursor: pointer;">
            <a id="img1" class="fb-header">rate</a>
            <a id="img2" class="fb-header">sc</a>

        </ul>
        <form action="Search" method="GET">
            <!-- Here search button and add review and sign out should be made dynamic -->
            <input class="fb-header" id="search" name="search" placeholder="" aria-label="Search">
            <input class="fb-header" id="search-btn" class=button type="submit" name="searchby" value="Courses"/>
			<input class="fb-header" id="search-btn2" class=button type="submit" name="searchby" value="Professors"/>    
        </form>
        <form action="StudentProfile">
            <button type="submit" class="fb-header" id="btn-topright">Profile</button>
        </form>
		<form action="SignOut">
			<button class="fb-header" id="btn-topright2">Sign Out</button>
		</form>
    </div>
    <div class="content">
    </div>
    <div class="row">
        
    <div class = "right">
        <h2 >My Rated Courses</h2>
        <div class = "example1">
            <!-- Load past reviews here from SQL database -->
            <table class="table">
				<thead>
					<tr id = "charthead">
						<th>
							#
						</th>
						<th>
							Course No
						</th>
						<th>
							Year
						</th>
						<th>
							Professor
						</th>
						<th>
							Overall
						</th>
					</tr>
				</thead>
				<tbody>
				<%for(int i=0;i<reviews.size();i++) {
					Review r=reviews.get(i);
					String params="?pre="+r.get_course().get_prefix()+"&num="+Integer.toString(r.get_course().courseNum())+"&pid="
						+Integer.toString(r.get_course().get_professorID());
					String fname=Character.toUpperCase(r.get_course().get_professor().get_fname().charAt(0)) + r.get_course().get_professor().get_fname().substring(1);
					String lname=Character.toUpperCase(r.get_course().get_professor().get_lname().charAt(0)) + r.get_course().get_professor().get_lname().substring(1);%>
					<tr>
						<td>
							<%=i+1 %>
						</td>
						<td>
							<a href="CourseProfessorResult<%=params%>"><%=reviews.get(i).get_course().get_prefix().toUpperCase()+Integer.toString(reviews.get(i).get_course().courseNum()) %></a>
						</td>
						<td>
							<%=reviews.get(i).get_course().term() %>
						</td>
						<td>
						<a href="ProfessorProfile?pid=<%=r.get_course().get_professorID() %>">	<%=fname+" "+lname%></a>
						</td>
						<td>
							<%=reviews.get(i).get_overall() %>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
        </div>
        
    </div>




</body>

</html>