<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Send Gmail</title>
<style>
body {
	font-family: Arial, sans-serif;
}

.container {
	max-width: 400px;
	margin: 50px auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input, textarea, button {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

button {
	background-color: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}
</style>
</head>
<div class="container">
	<h2>Send Gmail</h2>
	<form id="gmailForm">
		<label for="toEmail">To:</label> <input type="email" id="toEmail"
			name="toEmail" placeholder="Recipient's Email" required> <label
			for="subject">Subject:</label> <input type="text" id="subject"
			name="subject" placeholder="Subject" required> <label
			for="message">Message:</label>
		<textarea id="message" name="message" rows="5"
			placeholder="Write your message here..." required></textarea>

		<button type="submit">Send Gmail</button>
	</form>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/email.js' />"></script>


