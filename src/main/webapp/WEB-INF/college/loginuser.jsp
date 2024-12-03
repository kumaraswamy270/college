
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<header>
		<!-- Other header content, like logo, navigation, etc. -->
		<div style="position: absolute; top: 1px; right: 10px;">
			<!-- Welcome message and logout button -->
			<p class="welcome-message">
				Welcome, <strong><%=session.getAttribute("username")%></strong>!
			</p>
			<a href="/logout" class="logout-button">Logout</a>
		</div>
	</header>