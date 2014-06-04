<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<title>Insert title here</title>
</head>
<body>
<!-- NAVBAR -->




    <div class="navbar-wrapper">
      <div class="container">

        <div class="navbar navbar-inverse navbar-static-top" role="navigation">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="indexAction"><img src="img/logo.png" alt="Gamers Logo"></a>
            </div>
            <div class="col-sm-6 col-md-9">
            <div class="navbar-collapse collapse">
              
              <!--Store list-->
              
              <ul class="nav navbar-nav">
			  <li class="dropdown">
			    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tienda <b class="caret"></b></a>
			    <ul class="dropdown-menu">
			      <li><a href="catalogAction">Catalogo</a></li>
			      <li class="divider"></li>
			      
			      <s:iterator value="consoles">
			      <li><a href="catalogByConsoleAction?idConsole=<s:property value="idConsole"/>"><s:property value="company"/></a></li>
			      </s:iterator>
			      
			    </ul>
			  </li>
			  
              <!-- end Store list -->
              
              <!-- Usuario navbar -->
              <li class="dropdown">
			  <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Usuario <b class="caret"></b></a>
			  <ul class="dropdown-menu">
			  
			  <s:if test="%{#session['muser']==null}">
			  
			    <li><a href="initAction">Iniciar sesion</a></li>
			    
			  </s:if><s:else>
			    
			    <li class="dropdown-submenu">
			    	<a href="#">Informacion de perfil</a>
			    	<ul tabindex="-1" class="dropdown-menu">
			    		<li><a href="editInfoPersonalAction">Editar Informacion basica</a></li>
			    		<li><a href="editLoginPasswordAction">Editar nombre usuario y contraseña</a></li>
			    	</ul>
			    </li>
			    
			    <s:if test="#session['muser'].type==true">
			    	<li class="divider"></li>
				    <li class="dropdown-submenu">
				        <a href="#">Editar catalogo</a>
				        <ul tabindex="-1" class="dropdown-menu">
				          <li><a href="listProductsAction" title="Iformacion productos">Productos</a></li>
				          <li><a href="listConsolesAction" title="Informacion consolas">Consolas</a></li>
				        </ul>
				    </li>
				    <li><a href="listUsersAction">Listar usuarios</a></li>
			    </s:if><s:else>
			    	<li class="divider"></li>
				  	<li><a href="displayWishListAction">WishList</a></li>
				    <li><a href="displayShoppingCartAction">ShoppingCart</a></li>
				    <li><a href="displayBuyHistoryAction">Historial de compras</a></li>
			    </s:else>
			  
			  	<li class="divider"></li>
			  	<li><a href="logoutAction">Cerrar Sesion</a></li>
			  
			  </s:else>
			  
			  </ul>
			</li>
			</ul>
              <!-- end usuario navbar -->
                
              <div class="col-sm-3 col-md-2 pull-right">
              <form class="navbar-form" role="search">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                  <div class="input-group-btn">
                    <button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                  </div>
                </div>
              </form>
              </div>
            </div>
            </div>
          </div>
          </div>
        </div>
        </div>



<!-- Fin NAVBAR -->
</body>
</html>