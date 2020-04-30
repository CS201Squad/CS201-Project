<%@ page import="java.util.ArrayList" %>
<%@ page import="export.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>write review</title>
	<link rel="stylesheet" type="text/css" href="writereviews.css">
	<link rel="stylesheet" type="text/css" href="Julie_Pages/topbar.css">
	<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
	<script src="Julie_Pages/Chart.js"></script>

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
		width: 996px;
		height: 50px;
		left: 334px;
		top: 200px;
		font-style: normal;
		font-weight: bold;
		font-size: 30px;
		line-height: 35px;
		color: #870000;
	}
	.more{
		position: absolute;
		width: 996px;
		height: 50px;
		left: 334px;
		top: 200px;
		font-style: normal;
		font-weight: bold;
		font-size: 25px;
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
	
	a{
	text-decoration: none;
	color:#ffffff
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
	<!-- bar -->
	<%
		CourseProfessor cp=(CourseProfessor)request.getAttribute("cp");
		ArrayList<course> courses=cp.get_courses();
		String combo=cp.get_prefix().toUpperCase()+Integer.toString(cp.get_courseNum());
	%>
	<div class="course-box">
	
		<div class="course-id"><a href="Search?searchby=Courses&search=<%=combo %>"><%=combo%></a></div>
	</div>
	<div class="course-title">
		<%=cp.get_name() %>
	</div>
	<!-- TODO -->
	
	<a href="ProfessorProfile?pid=<%=cp.get_professor().get_professorID() %>" class="instructor-name" style="text-decoration: none;">
	<%String fname=Character.toUpperCase(cp.get_professor().get_fname().charAt(0)) + cp.get_professor().get_fname().substring(1);
	String lname=Character.toUpperCase(cp.get_professor().get_lname().charAt(0)) + cp.get_professor().get_lname().substring(1);%>
		<%=fname+" "+lname%>
	</a>
	<div class="chart1">
		<div class="title">Ratings</div>
		<canvas id="myChart" style="display: block; height: 326px; width: 300px;"></canvas>
		<script>
		var ctx = document.getElementById('myChart').getContext('2d');
		Chart.defaults.global.legend.display = false;
		
		var myChart = new Chart(ctx, {
			type: 'horizontalBar',
			data: {
				labels: ['Overall      ', 'Difficulty    ', 'Workload  ', 'Clarity       '],
				datasets: [{
					label: 'Score: ',
					data: [<%=cp.get_overall()%>, <%=cp.get_difficulty()%>, <%=cp.get_workload()%>, <%=cp.get_clarity()%>],
					backgroundColor: "rgba(242,201,76,1)",
					borderWidth: 0.1
				}]
			},
			options: {
				responsive: true,
				maintainAspectRatio: false,
				scales: {
					yAxes: [{
						gridLines: {
							display: false,
							drawOnChartArea: false,
							drawTicks: false
						},
						ticks: {
							beginAtZero: true,
							fontSize: 24
						},
						barPercentage: 0.5
					}],
					xAxes:[{
						gridLines: {
							display: false,
							drawOnChartArea: false,
							drawTicks: false
						},
						ticks: {
							beginAtZero: true
						},
						display: false
					}]
				},
				layout: {
					padding: {
						left: 50,
						right: 0,
						top: 0,
						bottom: 0
					},
					widths: 100
				}


			}
		});
	</script>
	
	</div>
	<div class="review-section">
	<%
	String params="?pre="+cp.get_prefix()+"&num="+Integer.toString(cp.get_courseNum())+"&pid="
	+Integer.toString(cp.get_professor().get_professorID());
	ArrayList<Review> reviews=cp.get_reviews();
	%>
		<a class="more" href="Reviews<%=params%>" style="text-decoration: none;">   See More  </a>
		<div class="review">Reviews</div>
		<%for(int i=0;i<3 && i<reviews.size();i++) {
			String cont=reviews.get(i).get_content();
			String content=cont;
			if(cont.length()>30){
				content=cont.substring(0,30)+"...";
			}
		%>
		
			<div class="review-box1"></div>
			<div class="review-text1"><%=content %></div>
			<div class="review-date1">Taken: <%=reviews.get(i).get_course().term() %></div>
		
		<%} %>
		
	</div>
		<div class="grade-section">
			<div class="grades">Grades</div>
			<td>
					<select id="term" onchange="changeData()">
					<%for(int i=0;i<courses.size();i++){ %>
						<option value=<%=i+1 %>><%=courses.get(i).term() %></option>
					<%} %>
					</select>
			</td>
				<div class="chart2">
			  <canvas id="chart-0" style="display: block; height: 326px; width: 300px;"></canvas>
<script type="text/javascript">
			    var dataObjects = [
			    <%for(int i=0;i<courses.size();i++){%>
			    {
			      label: "<%=courses.get(i).term() %>",
			      data: <%=courses.get(i).get_grades().toArray()%>
			    },
			    <%}%>

			    ]


			    /* data */
			    var data = {
			      labels: ["A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"],
			      datasets: [  {
			        label:  dataObjects[0].label,
			        data: dataObjects[0].data,
			        /* global setting */
			        backgroundColor: "rgba(242,201,76,1)",
			        borderWidth: 1
			      }]
			    };

			    var options = {
			      legend: {
			        display: true,
			        fillStyle: "red",
			        // position: '',
			        labels: {
			          boxWidth: 0,
			          fontSize: 16,
			        }

			      },
			      scales: {
			        xAxes: [{
			          stacked: false,
			          scaleLabel: {
			            display: false,
			          },
			          gridLines: {
			            display: false,
			            drawOnChartArea: false,
			            drawTicks: false
			          },
								ticks: {
									beginAtZero: true,
									fontSize: 20
								}
			        }],
			        yAxes: [{
			          stacked: true,
			          scaleLabel: {
			            display: false,
			          },
			          ticks: {
			            suggestedMin: 0,
			            suggestedMax: 5
			          },
			          gridLines: {
			            display: false,
			            drawOnChartArea: false,
			            drawTicks: false
			          },
								position: "left"
			        }]
			      },
			    };

			    var chart = new Chart('chart-0', {
			      // plugins: [ChartDataLabels], /*https://chartjs-plugin-datalabels.netlify.com*/
			      type: 'bar',
			      data: data,
			      options: options
			    });

			    function changeData() {
			      var e = document.getElementById("term");
			      var index = e.selectedIndex;
			      chart.data.datasets.forEach(function(dataset) {
			        dataset.label = dataObjects[index].label;
			        dataset.data = dataObjects[index].data;
			        //dataset.backgroundColor = dataObjects[index].backgroundColor;
			      });
			      chart.update();
			    }
			  </script>
		</div>


</body>
</html>
