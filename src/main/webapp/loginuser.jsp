 <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <div style="float: right; margin-right: 25px; margin-top: 25px;">
            <p class="welcome-message">
                Welcome, <strong><%=session.getAttribute("username")%></strong>!
            </p>
            <a href="LogOutServlet" class="logout-button">Logout</a>
        </div>
    </header>
