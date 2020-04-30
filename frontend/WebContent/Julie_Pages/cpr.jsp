<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="export.*" %>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>write review</title>
	<link rel="stylesheet" type="text/css" href="Julie_Pages/topbar.css">
	
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <!-- <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> -->
    <!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">


	<script src="Chart.js"></script>

	<!-- <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'> -->

	<style>
	body {
		font-family: 'Montserrat';
	}
	.course-box{
		/* position: absolute; */
		position: absolute;
		width: 234px;
		height: 138px;
		left: 63px;
		top: 119px;
		background: #DC2C2C;
		border-radius: 15px;
		margin: auto;

	}
	.course-id{
		position:relative;
		width: 197px;
		height: 56px;
		text-align: center;
		margin-right: auto;
		margin-left: auto;
		margin-top: 41px;
		margin-bottom: 41px;
		font-style: normal;
		font-weight: normal;
		font-size: 40px;
		line-height: 56px;
		color: #FFFFFF;
	}
	.course-title{
		position: absolute;
		/* width: 600px;
		height: 35px; */
		left: 331px;
		top: 128px;
		font-style: normal;
		font-weight: normal;
		font-size: 48px;
		line-height: 56px;
		color: #333333;
	}
	/* Jeffrey Miller */
	.instructor-name{
		position: absolute;
		width: auto;
		height: 50px;
		left: 334px;
		top: 200px;
		font-style: normal;
		font-weight: bold;
		font-size: 30px;
		line-height: 35px;
		color: #870000;
	}
	.chart1{
		position: absolute;
		width: 738px;
		height: 326px;
		top: 305px;
	}
	.title{
		position: relative;
		width: 113px;
		height: 41px;
		left: 60px;
		top: 0px;
		font-style: normal;
		font-weight: 1000;
		font-size: 30px;
		line-height: 35px;
	}
	canvas.chart1 {
		background-color: #f00;
	}
	canvas{
		width:738px !important;
		height:269px !important;
	}
	.chart2{
		position: absolute;
		top: 40px;
		left: 30px;
		width: 738px;
		height: 326px;
	}

	.seemore{
		position: absolute;
		left: 25.84%;
		right: 35.11%;
		top: 2.93%;
		bottom: 82.41%;

		font-style: normal;
		font-weight: normal;
		font-size: 18px;
		line-height: 21px;
		color: #870000;
	}
	.review-section{
		position: absolute;
		width: 507px;
		height: 310px;
		left: 860px;
		top: 292px;

	}
	/* Reviews */
	.review{
		position: absolute;
		left: 0%;
		right: 3.35%;
		top: 0%;
		bottom: 82.74%;
		font-style: normal;
		font-weight: bold;
		font-size: 30px;
		line-height: 35px;
		color: #000000;
	}
	/* Rectangle 15 */
	.review-box1{
		position: absolute;
		left: 0.79%;
		right: 3.35%;
		top: 17.26%;
		bottom: 44.3%;
		background: #C4C4C4;
	}
	/* Great class, great professor! */
	.review-text1{
		position: absolute;
		left: 5.52%;
		right: 7.5%;
		top: 25.41%;
		bottom: 65.8%;
		font-style: normal;
		font-weight: normal;
		font-size: 24px;
		line-height: 28px;

		color: #000000;
	}


	/* Taken: Fall 18 */
	.review-date1{
		position: absolute;
		left: 5.52%;
		right: 27.61%;
		top: 38.44%;
		bottom: 52.77%;

		font-style: normal;
		font-weight: normal;
		font-size: 24px;
		line-height: 28px;
		color: #8D8D8D;
	}

	/* Rectangle 16 */
	.review-box2{
		position: absolute;
		left: 0.79%;
		right: 3.35%;
		top: 61.24%;
		bottom: 0%;
		background: #C4C4C4;
	}

	/* Great professor, great class! */
	.review-text2{
		position: absolute;
		left: 5.52%;
		right: 27.61%;
		top: 69.71%;
		bottom: 21.5%;
		font-style: normal;
		font-weight: normal;
		font-size: 24px;
		line-height: 28px;
		color: #000000;
	}

	/* Taken: Fall 17 */
	.review-date2{
		position: absolute;
		left: 5.52%;
		right: 27.61%;
		top: 82.41%;
		bottom: 8.47%;

		font-style: normal;
		font-weight: normal;
		font-size: 24px;
		line-height: 28px;
		color: #8D8D8D;
	}

	/* Grades */
.grade-section{
	position: absolute;
	top: 692px;
	width: 1064px;
	height: 562px;
	left: 30px;
}
.grades{
	position: absolute;
	left: 30px;
	right: 5.55%;
	top: 0.71%;
	bottom: 90.39%;
	font-style: normal;
	font-weight: bold;
	font-size: 30px;
	line-height: 35px;
	color: #000000;
}

#term{
position: absolute;
width: 200.47px;
height: 25.49px;
top: 0.71%;
left: 554px;
background: #E7E7E7;
}

a.course, a.course:hover{
	text-decoration: none;
	color:#ffffff
}

	a.course, a.course:hover{
		text-decoration: none;
	}



</style>
</head>
<body>
	<!-- bar -->
	<div class="fb-header-base">
	</div>
	<div class="fb-header">
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
	<!-- end of bar -->
	<%
		CourseProfessor cp=(CourseProfessor)request.getAttribute("cp");
		ArrayList<course> courses=cp.get_courses();
		String combo=cp.get_prefix().toUpperCase()+Integer.toString(cp.get_courseNum());
		ArrayList<Review> reviews=cp.get_reviews();
	%>

	<div class="course-box">
		<div class="course-id"><a class="course" href="Search?searchby=Courses&search=<%=combo %>"><%=combo%></a></div>
	</div>
	<div class="course-title">
		Software Engineering
	</div>
	<a href="ProfessorProfile?pid=<%=cp.get_professor().get_professorID() %>" class="instructor-name" style="text-decoration: none;">
	<%String fname=Character.toUpperCase(cp.get_professor().get_fname().charAt(0)) + cp.get_professor().get_fname().substring(1);
	String lname=Character.toUpperCase(cp.get_professor().get_lname().charAt(0)) + cp.get_professor().get_lname().substring(1);%>
		<%=fname+" "+lname%>
		</a>
    <div style="margin-left: 1000px; margin-top: 140px;">
        <!-- <div class="col-md-12"> -->
        <%
		String params="?pre="+cp.get_prefix()+"&num="+Integer.toString(cp.get_courseNum())+"&pid="
		+Integer.toString(cp.get_professor().get_professorID());
		boolean btn=(boolean)request.getAttribute("canReview");%>
		
            <button id="ar" onclick="window.location.href='ADRedirect<%=params%>';" type="button" class="btn btn-primary" style="background-color: #990000; border-color: #990000">Add Review</button>
			<%if(!btn){%>
			<script type="text/javascript">
				document.getElementById("ar").style.visibility="hidden";
			</script>
		<%}%>
			<!-- </div> -->
    </div>
	<div style="margin: 130px 0 auto auto; padding: 0 50px;">
        <div class="title">Reviews</div>
        <!-- review per section! -->
         <%for(int i=0;i<reviews.size();i++){
        	Review r=reviews.get(i);%>
        <section class="write-review py-3 bg-light" >
            <div class="container" style="width : 100%;">
            <div class="row">
                <div class="col-md-3">
                    <div class="row">
                    <div class="col-md-6">
                        <h5>Overall Rating</h5>
                    </div>
                    <div class="col-md-6"><%=r.get_overall() %>
                    </div>
                </div>
                <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Difficulty</p>
                        </div>
                        <div class="col-md-6"><%=r.get_difficulty() %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Workload</p>
                        </div>
                        <div class="col-md-6"><%=r.get_workload() %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Clarity</p>
                        </div>
                        <div class="col-md-6"><%=r.get_clarity() %>
                        </div>
                    </div>
			</div>
			<div class="col-md-6">
				<h5><%=r.get_course().term() %> </h5>
				<h6>2000-03-04</h6>
				

				<h5><%=r.get_content() %> </h5>
    
			</div>
			<div class="col-md-2.5">
			<%
			String text="No";
			String att="No";
			if(r.get_text())text="Yes";
			if(r.get_att())att="Yes";
			%>
				<h6>Textbook Required:	<%=text %></h6>
				<h6>Attendance Required:	<%=att %></h6>
			</div>			
        </div>
        
		</section>
		<hr>
		<%} %>
	</div>
	

</body>
</html>
