<!DOCTYPE html>
<html lang = "en">
<head>
    <#include "header.ftl">
</head>

<body>
<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand float-xs-left">Picilter</h3>
                    <nav>
                        <ul class="nav masthead-nav">
                            <li ><a href="index.html">Home</a></li>
                            <li class="active"><a href="discover.html">Discover</a></li>
                            <li><a href="filter.html">Filter</a></li>
                            <li><a href="group.html">Group</a></li>
                            <li><a href="#">Log in/Register</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

            <div class="inner cover">
                <div>
                    <div class="row">
                        ${image1}
                        ${image2}
                        ${image3}
                        ${image4}
                    </div>
                    <div class="row">
                        ${image0}
                        ${image5}
                        ${image6}
                        ${image7}
                    </div>
                    <script src="js/myScript.js"></script>
                </div>
            </div>

            <div class="mastfoot">
                <div class="inner">
                    <p class="small">
                        Created by <a href="http://shizheng.li">lszlawrence</a>
                    </p>
                </div>
            </div>

        </div>

    </div>

</div>
<!-- jQuery-->
<script src="jquery/jquery.min.js">
</script>
<!-- include bootstrap plugins-->
<script src="stylesheets/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>