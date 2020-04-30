<%@ page import="java.util.ArrayList" %>
<%@ page import="export.*" %>
<%@ page import="java.util.*" %>

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

    /*here begins contents under search bar */
    /*class title bar and text*/
    #class-base{
        margin-top:10%;
        margin-left: 5%;
    }
    #photo{
        border-radius: 15px;
        margin-top: 4%;
        display: block;
        max-width:150px;
        max-height:150px;
        width: auto;
        height: auto;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    }

    #prof-name{
        padding-top: 27px;
        background:none;
        color: #333333;
        font-weight: bold;
        font-size: 35px;
        
    }
    /*class professors and their ratings*/

    #intro-text {
        padding-left:5%;
        padding-top: 4%;
    }
 
    .left {
        width: 30%;
        padding-left: 6%;
        padding-top: 8%;
        height: 60px;
        width: 300px;
        bottom: 80px;
        align-content:center;
    }

    .right {
        width: 80%;
        padding-left: 3%;
        padding-top: 9%;
        height: 95%;
        width: 70%;
        bottom: 100%;
        margin-bottom: 4%;
        background-position: right;
        background-origin: padding-box;
    }
    .prof-title{
        font-size: 30px;
        color:#870000;
        font-weight: bold;
    }
    
    /*table rows and columns*/
    .row2 {
        width: 90%;
        margin-left: 6%;
        margin-top: 4%;
        margin-bottom: 50px;
    }
    
    #professorlist tr:nth-child(odd){background-color: #F2F2F2;}

    #professorlist tr:hover {background-color: #ddd;}
    #professorlist {
        width: 100%;
        
    }
    .profName{
        padding-left: 5%;
        text-align: center;
        font-size: 40px;
        font-weight: bold;
        table-layout: fixed;
        width: 230px;
        font-size: 30px;
    }
    .number{
        table-layout: fixed;
        width: 150px;
        text-align:center;
        font-size: 60px;
        font-weight: bold;
    }
    .type{
        table-layout: fixed;
        width: 150px;
        text-align:center;
        font-size: 25px;
    }
</style>

<body>

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
        <div class="left">
            <div id="class-base">
                <!-- Load professor photo here -->
                <img id="photo" src="https://irds.usc.edu/images/headshots/jeffrey-miller.jpg">
            </div>

        </div>
        <%
				Professor p=(Professor)request.getAttribute("professor");%>
        <div class = "right">
            <!-- Load professor name here -->
            <%String fname=Character.toUpperCase(p.get_fname().charAt(0)) + p.get_fname().substring(1);
	String lname=Character.toUpperCase(p.get_lname().charAt(0)) + p.get_lname().substring(1);%>
            <p id= "prof-name"><%=fname+" "+lname%></p> 
            <span class="prof-title">Professor in</span> 
            <!-- Load professor department here -->
            <span class="prof-title" id = "prof-dept"><%=p.get_department() %></span>
             <span class="prof-title">Department</span>
        </div>
    </div>
    <h3 id = "intro-text">  Past Classes</h3>
    <div class = "row2">
        <!-- Load lcass title and ratngs here -->
            <table id = "professorlist">
				
				<tbody>
				<%
				HashMap<String, CourseProfessor> courses=p.get_courses();
				Iterator hmiterator=courses.entrySet().iterator();
				while(hmiterator.hasNext()){
					Map.Entry mapElement = (Map.Entry)hmiterator.next();
					CourseProfessor cp=(CourseProfessor)mapElement.getValue();
				%>
					<tr>
						<td>
						<%
						String params="?pre="+cp.get_prefix()+"&num="+Integer.toString(cp.get_courseNum())+"&pid="
							+Integer.toString(p.get_professorID());%>
							<a href="CourseProfessorResult<%=params%>"><h4 class = "profName"><%=cp.get_prefix().toUpperCase()+Integer.toString(cp.get_courseNum())%></h4></a>
						</td>
						<td>
                            <h3 class = "number"><%=cp.get_overall() %></h3>
                            <h6 class = "type">Overall</h6>
						</td>
						<td>
							<h3 class = "number"><%=cp.get_difficulty() %></h3>
                            <h6 class = "type">Difficulty</h6>
						</td>
						<td>
							<h3 class = "number"><%=cp.get_workload() %></h3>
                            <h6 class = "type">Workload</h6>
						</td>
						<td>
							<h3 class = "number"><%=cp.get_clarity() %></h3>
                            <h6 class = "type">Clarity</h6>
						</td>
						
                    </tr>
                    <%} %>
					
				</tbody>
			</table>
        
        
    </div>




</body>

</html>