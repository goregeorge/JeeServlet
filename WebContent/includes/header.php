<?php include_once("includes/config.php"); ?>
<!DOCTYPE html>
<html lang="es"
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--<link rel="shortcut icon" href="../../assets/ico/favicon.ico">-->

    <title>Gamers Retail Stores</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="css/carousel.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap-social.css">
    <link rel="stylesheet" href="css/font-awesome.css">
  </head>
<!-- NAVBAR
================================================== -->
  <body>
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
              <a class="navbar-brand" href="<?php echo baseWeb; ?>"><img src="img/logo.png" alt="Gamers Logo"></a>
            </div>
            <div class="col-sm-6 col-md-9">
            <div class="navbar-collapse collapse">
              
              <!--Store list-->
              
              <ul class="nav navbar-nav">
			  <li class="dropdown">
			    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tienda <b class="caret"></b></a>
			    <ul class="dropdown-menu">
			      <li><a href="#">Todo</a></li>
			      <li class="divider"></li>
			      <li class="dropdown-submenu">
			        <a tabindex="-1" href="#">Xbox</a> 
			        <ul class="dropdown-menu">
			          <li><a href="juegos.php" title="Xbox 360 games">Xbox 360</a></li>
			          <li><a href="#" title="Xbox ONE games">Xbox ONE</a></li>
			        </ul>
			      </li>
			      <li class="dropdown-submenu">
			        <a href="#">Play Station</a>
			        <ul tabindex="-1" class="dropdown-menu">
			          <li><a href="#" title="Play Station 2 games">Play Station 2</a></li>
			          <li><a href="#" title="Play Station 3 games">Play Station 3</a></li>
			          <li><a href="#" title="Play Station 4 games">Play Station 4</a></li>
			          <li class="divider"></li>
			          <li><a href="#" title="PSP games">PSP</a></li>
			          <li><a href="#" title="PSVita games">PSVita</a></li>
			        </ul>
			      </li>
			      <li class="dropdown-submenu">
			        <a href="#">Nintendo</a>
			        <ul tabindex="-1" class="dropdown-menu">
			          <li><a href="#" title="Wii games">Wii</a></li>
			          <li><a href="#" title="WiiU games">WiiU</a></li>
			          <li class="divider"></li>
			          <li><a href="#" title="Nintendo 3DS games">Nintendo 3DS</a></li>
			          <li><a href="#" title="Nintendo DS games">Nintendo DS</a></li>
			        </ul>
			      </li>
			    </ul>
			  </li>
			  
              <!-- end Store list -->
              
              <!-- Usuario navbar -->
              <li class="dropdown">
			  <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Usuario <b class="caret"></b></a>
			  <ul class="dropdown-menu">
			    <li><a href="#">WishList</a></li>
			    <li><a href="#">ShoppingCart</a></li>
			    <li><a href="#">Historial de compras</a></li>
			    
			    <li class="divider"></li>
			    
			    <li><a href="#">Informacion de perfil</a></li>
			    <li><a href="#">Formas de pago</a></li>
			    <li><a href="#">Direcciones de envio</a></li>
			    
			    <li class="divider"></li>
			    <li class="dropdown-submenu">
			        <a href="#">Editar catalogo</a>
			        <ul tabindex="-1" class="dropdown-menu">
			          <li><a href="#" title="Iformacion productos">Productos</a></li>
			          <li><a href="#" title="Informacion consolas">Consolas</a></li>
			        </ul>
			    </li>
			    <li><a href="#">Informacion de usuarios</a></li>
			    <li><a href="#">Informacion de perfil</a></li>
			    
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
