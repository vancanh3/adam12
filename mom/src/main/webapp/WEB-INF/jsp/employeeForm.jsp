<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BORAJI.COM</title>

<style type="text/css">
  span.error{
    color: red;
    margin-left: 5px;
  }
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	/*  Submit form using Ajax */
	$('button[type=submit]').click(function(e) {
		
		//Prevent default submission of form
		e.preventDefault();
		
		//Remove all errors
		$('input').next().remove();
		
		$.post({
			url : 'saveEmployee',
			data : $('form[name=employeeForm]').serialize(),
			success : function(res) {
				
				if(res.validated){
					//Set response
					$('#resultContainer pre code').text(JSON.stringify(res));
					$('#resultContainer').show();

				}else{
					//Set error messages
					$("#")
				}
			}
		})
	});
});
</script>
</head>
<body>
  <h1>Employee Input Form</h1>
  <hr />

  <form action="saveEmployee" method="post" name="employeeForm">
    <table>
        <td>Email</td>
        <td><input name="email" type="text" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input name="password" type="password" /></td>
      </tr>
      <tr>
        <td></td>
        <td><button type="submit">Submit</button></td>
      </tr>
    </table>
  </form>

<!-- Result Container  -->
<p id="result"></p>
</body>
</html>