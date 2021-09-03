<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="header-left">
    <div class="logo">
      <a href="/">
            <img id="logo_img" src="${baseUrl}/assets/public/images/logo/logo-sehati-color.png" alt="" width="150">
      </a>
	</div>
	<div class="sidemenu-opener"><a href="#" title=""><i class="icon dripicons-align-left"></i></a></div>
	<div class="custom-header-links" id="nowtime" style="padding-left:20px; color:#fff;"></div>
	</div>
	<div class="header-right">
	<div class="control-search roleinfo">
		<c:choose>
			<c:when test="${userData[0].user_type == '1'}">
           		<strong>${userData[0].user_fullname}</strong>												
			</c:when>
			<c:otherwise>
          		<strong>${userData[0].company_name}</strong>											
			</c:otherwise>
		</c:choose>
		
	</div>
	<form id="goLogout" method="get" action="${baseUrl}/sso/oidc/logout" style="display:none;">
		<input type="hidden" name="id_token_hint" value="${idToken}">
		<input type="hidden" name="post_logout_redirect_uri" value="${baseUrl}/portal">
		<button type="submit">Logout</button>
	</form>
	<ul class="top-header-btns">
	      <li class="dropdown">
	            <a class="login-user" data-toggle="dropdown" href="#" title="">
		            <c:choose>
						<c:when test="${fn:length(userData[0].user_photo) > 0}">
	            			<img src="${baseUrl}/assets/apps/images/noprofile.png" alt="" />						
						</c:when>
						<c:otherwise>
	            			<img src="${baseUrl}/assets/apps/images/noprofile.png" alt="" />						
						</c:otherwise>
					</c:choose>
	            </a>
	            <div class="dropdown-menu dropdown-menu-right">
	                  <a class="dropdown-item" href="/settings/profile" style="color:#000 !important;"> <i class="fas fa-user"></i> Profil</a>
	                  <a class="dropdown-item" href="/settings/password" style="color:#000 !important;"> <i class="fas fa-key"></i> Edit Password</a>
	                  <div class="dropdown-divider"></div>
	                  <a class="dropdown-item" href="#" onclick="$('#goLogout').submit()" style="color:#000 !important;"> <i class="fas fa-sign-out-alt"></i> Keluar</a>
	            </div>
	      </li>
	      <li class="dropdown">
	            <a href="#" title="" class="notificon" data-toggle="dropdown"><i class="icon dripicons-bell"></i></a>
	            <div class="dropdown-menu  dropdown-menu-right">
	                  <div class="dropdown-menu-header">
	                        <div class="dmh-inner"> <h4>Notifikasi</h4> <span></span> </div>
	                  </div>
	                  <div class="timeline "></div>
	            </div>
	      </li>
	</ul>
</div>
<script>
set_datetimeclock('nowtime');
</script>