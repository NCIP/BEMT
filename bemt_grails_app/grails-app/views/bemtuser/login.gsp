<html>
	<head>
		<meta name="layout" content="../layouts/public"/>
		<title>Login</title>
	</head>
	<body>
		<br/>
		<div class="row">
			<div class="small-12 columns">
				<h3><i class="fa fa-bar-chart-o"></i> Biobank Economic Modeling Tool</h3>
			</div>			
		</div>
		<br/>
		<browser:choice>
			<browser:isMsie versionLower="9">
				<div class="staticmessage error">
	  			<p>
<%-- The BEMT requires the use of Firefox, Chrome, Safari, or Internet Explorer 9 or higher.  If you are not using one of these browsers, please try a different browser.   If you are getting this message and using Internet Explorer 9 or higher, it is possible your browser’s compatibility settings are restricting access to the BEMT site.  Please check with your system administrator about correcting this. --%>
The BEMT requires the use of Firefox, Chrome, or Safari. If you are not using one of these browsers, please try a different browser. 
                                </p>
				</div>
			</browser:isMsie>
			<browser:otherwise>
  			<browser:isMsie>
					<div class="row">
						<div class="small-12 columns">
							<div class="staticmessage error">
				  			<p>You are currently using Internet Explorer (IE).  Certain configurations of IE may cause issues while performing certain functions on this site.  We recommend using Firefox, Chrome or Safari if possible.  Thank you.</p>
							</div>
						</div>
					</div>
				</browser:isMsie>
				<div class="row">
					<div class="small-6 columns">
						<h5>Welcome!</h5>
						<p>You have reached the United States National Institutes of Health, National Cancer Institute’s Biorepositories and Biospecimen Research Branch (US NIH/NCI BBRB) Biobank Economic Modeling Tool (BEMT) Website.</p>
						<p>The BEMT is designed to support cost recovery and financial planning activities for biobanks. </p>
					</div>							
					<div class="small-6 columns">
							<h5>Both new and existing biobanks can use this tool to:</h5>
							<p>							
								<ul>
									<li><i>Develop a 3 year financial plan / forecast</i></li>
									<li><i>Conduct direct cost analysis for specimens/products and services</i></li>
									<li><i>Measure percent of costs recovered</i></li>
									<li><i>Produce Fee Schedules</i></li>
									<li><i>Review resources, fees, volumes, and products/services from other biobanks</i></li>
								</ul>
							</p>
					</div>
				</div>
				<div class="row">
					<div class="small-4 columns">
						<g:form action="validate">
							<fieldset>
								<legend>Existing Users</legend>
								<g:if test="${flash.object == 'login'}">
									<g:render template="../components/flashmessage" model="['flash':flash]"/>
								</g:if>
								<p>Login with your email and password</p>
								<label>Email Address</label>
								<input id="username" name="email" type="text" placeholder="Your email address…">
								<label>Password</label>
								<input id="password" name="password" type="password" placeholder="Your password…" autocomplete="off">
								<button type="submit" class="button tiny radius">Login</button>
								<button type="reset" class="button tiny radius">Reset</button>
							</fieldset>
						</g:form>	
					</div>				
					<div class="small-8 columns">
						<g:form controller="bemtuser" action="create_new_org_and_user">
							<fieldset>
								<legend>New Users</legend>
								<g:if test="${flash.object == 'signup'}">
									<g:render template="../components/flashmessage"/>
								</g:if>
								<g:render template="../components/validationerrormessage"/>
								<p>Provide your information below and start using BEMT for your biobank.</p>
								<div class="row">
									<div class="small-6 columns">
										<label>Biobank Name</label>
										<input id="biobank_name" name="biobank_name" type="text" placeholder="Name of your Biobank…" value="${params.biobank_name}">
										<label>First Name</label>
										<input id="nameFirst" type="text" name="nameFirst" placeholder="First Name…" value="${obj?.nameFirst}">
										<label>Last Name</label>
										<input id="nameLast" type="text" name="nameLast" placeholder="Last Name…" value="${obj?.nameLast}">
									</div>
									<div class="small-6 columns">
										<label>Email Address (this will be your Username)</label>
										<input id="email"  name="email" type="text" placeholder="Email address…" value="${obj?.email}">
										<label>Password <span data-tooltip title="Valid characters: A-Z, a-z, 0-9" data-options="disable_for_touch:true" class="has-tip">(at least 8 letters or numbers) <i class="fa fa-question-circle"></i></span></label>
										<input id="password"  name="password" type="password" placeholder="Your password…" autocomplete="off">
										<label>Confirm Password</label>
										<input id="password_check" type="password" placeholder="Confirm password…" autocomplete="off">
									</div>
								</div>
								<button type="submit" class="button tiny radius">Signup</button>
								<button type="reset" class="button tiny radius">Reset</button>						
							</fieldset>
						</g:form>					
					</div>	
				</div>
			</browser:otherwise>
		</browser:choice>
	</body>
</html>