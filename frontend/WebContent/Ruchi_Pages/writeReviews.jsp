<%@ page import="java.util.ArrayList" %>
<%@ page import="export.*" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Write a Review</title>
    <link rel="stylesheet" type="text/css" href="Ruchi_Pages/writeReviews.css">
    <link rel="stylesheet" type="text/css" href="Julie_Pages/topbar.css">
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
  </head>
  <style>
  body {

      font-family: 'Montserrat';
      color: #333333;
  }
  h1{
    font-family: Montserrat;
    font-style: normal;
    font-weight: normal;
    font-size: 30px;
    line-height: 59px;
    color: #333333;
  }
  td{
    font-family: Montserrat;
    font-style: normal;
    font-weight: normal;
    font-size: 24px;
    line-height: 37px;

    color: #333333;
        }
  input{
    border: 1px solid #C4C4C4;
    height: 24px;
    font-size: 18px;
    font-family: Montserrat;
  }
  .rating{
    width : 50px;
  }

  #submit-btn{
    position: absolute;
    width: 264px;
    height: 62px;
    left: 568px;
    top: 680px;
    background: #A30101;
    border-radius: 10px;
  }

  #submit-btn:hover{
    position: absolute;
    width: 264px;
    height: 62px;
    left: 568px;
    top: 680px;
    background-color: #ffcc00;
    color: black;
    border-radius: 10px;

  }
  </style>
  <body>
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


    <br />
    <br />
    <br />
    
    <%
	CourseProfessor cp=(CourseProfessor)request.getAttribute("cp");
	%>
	<%String fname=Character.toUpperCase(cp.get_professor().get_fname().charAt(0)) + cp.get_professor().get_fname().substring(1);
	String lname=Character.toUpperCase(cp.get_professor().get_lname().charAt(0)) + cp.get_professor().get_lname().substring(1);%>
		

    <h1>Write a Review For <%=cp.get_prefix().toUpperCase()+Integer.toString(cp.get_courseNum())+" with "+fname+" "+lname%></h1>
    <form class="review-form" name="reviewform" method="GET" action="AddReview">
      <table id="t1">

        <tr>
          <td>Term</td>
          <td>
            <!-- changed professor name to term -->
            <input type="hidden" name="prefix" value="<%=cp.get_prefix() %>">
             <input type="hidden" name="num" value="<%=cp.get_courseNum() %>">
              <input type="hidden" name="pid" value="<%=cp.get_professor().get_professorID() %>">
            
            <input name="profName" list="profs" />
            <datalist id="profs">
              <option value="Fall" />
              <option value="Spring" />
            </datalist>
          </td>
        </tr>
        <tr>
          <td>Year</td>
          <td>
            <!-- changed course name to year  -->
            <input type="text" id="year" ><br>
            
          </td>
        </tr>
        <tr>
          <td >Overall Rating</td>
          <td>
            <input class="rating" type="number" step="any" min="1" max="5" name="overall">
          </td>
        </tr>
        <tr>
          <td>Difficulty Rating</td>
          <td>
            <input class="rating" type="number" step="any" min="1" max="5" name="difficulty">
          </td>
        </tr>
        <tr>
          <td >Workload Rating</td>
          <td>
            <input class="rating" type="number" step="any" min="1" max="5" name="workload">
          </td>
        </tr>
        <tr>
          <td >Clarity Rating</td>
          <td>
            <input class="rating" type="number" step="any" min="1" max="5" name="clarity">
          </td>
        </tr>

        <tr>
          <td>Textbook Required?</td>
          <td>
            <input type="radio" name="textbook" value="yes" />Yes
            <input type="radio" name="textbook" value="no" />No
          </td>
        </tr>
        <tr>
          <td>Attendance Required?</td>
          <td>
            <input type="radio" name="attendance" value="yes" />Yes
            <input type="radio" name="attendance" value="no" />No
          </td>
        </tr>
        <tr>
          <td>Additional Comments</td>
          <td>
            <textarea type="textarea" style="font-size: 18px; "height:200px"  id="msg" name="addtlComments" rows="7" cols="85" style="font-family: Montserrat;"> </textarea>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <input id=submit-btn type="submit" name="submit" value="Submit Form" sty="" style="font-family: Montserrat;"/>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>
