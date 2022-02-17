<aside class="main-sidebar">
	<section class="sidebar">		
		<ul class="sidebar-menu">
			<li class="header text-center">MAIN MENU</li>
			<li class="treeview" id="manage" ng-show="<%= session.getAttribute("usertypeidadmin") %> == 1 || <%= session.getAttribute("usertypeidadmin") %> == 2">
				<a href="#">
					<i class="fa fa-cogs"></i> <span>Manage</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li id="enquiry"><a href="<%=request.getContextPath() %>/manage_enquiry" onclick="location.reload()"><i class="fa fa-circle-o"></i> Enquiries</a></li>
					<%-- <li id="enquiry"><a href="<%=request.getContextPath() %>/employee_activity" onclick="location.reload()"><i class="fa fa-circle-o"></i> Employee Activities</a></li> --%>															
					<li id="product"><a href="<%=request.getContextPath() %>/manage_task" onclick="location.reload()"><i class="fa fa-circle-o"></i> Task</a></li>
					<li id="product"><a href="<%=request.getContextPath() %>/manage_product" onclick="location.reload()"><i class="fa fa-circle-o"></i> Product</a></li>
					<li id="quotation"><a href="<%=request.getContextPath() %>/manage_quotation" onclick="location.reload()"><i class="fa fa-circle-o"></i> Quotation</a></li>
					<li id="user"><a href="<%=request.getContextPath() %>/manage_user" onclick="location.reload()"><i class="fa fa-circle-o"></i> User</a></li>					
				</ul>
			</li>
			<li class="treeview" id="manage" ng-show="<%= session.getAttribute("usertypeidadmin") %> == 3">
				<a href="#">
					<i class="fa fa-cogs"></i> <span>Manage</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li id="enquiry"><a href="<%=request.getContextPath() %>/manage_enquiry" onclick="location.reload()"><i class="fa fa-circle-o"></i> Enquiries</a></li>											
				</ul>
			</li>
			<li class="treeview" id="master" ng-show="<%= session.getAttribute("usertypeidadmin") %> == 1 || <%= session.getAttribute("usertypeidadmin") %> == 2">
				<a href="#">
					<i class="fa fa-flag-checkered"></i> <span>Masters</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">	
					<li id="brand"><a href="<%=request.getContextPath() %>/manage_brand"><i class="fa fa-circle-o"></i> Brand</a></li>
					<li id="company"><a href="<%=request.getContextPath() %>/manage_company"><i class="fa fa-circle-o"></i> Company</a></li>
					<li id="category"><a href="<%=request.getContextPath() %>/manage_category"><i class="fa fa-circle-o"></i> Category</a></li>			
					<li id="country"><a href="<%=request.getContextPath() %>/manage_country"><i class="fa fa-circle-o"></i> Country</a></li>
					<li id="currency"><a href="<%=request.getContextPath() %>/manage_currency"><i class="fa fa-circle-o"></i> Currency</a></li>					
					<li id="financial_year"><a href="<%=request.getContextPath() %>/manage_financial_year"><i class="fa fa-circle-o"></i> Financial Year</a></li>				
					<li id="measurement_unit"><a href="<%=request.getContextPath() %>/manage_measurement_unit"><i class="fa fa-circle-o"></i> Measurement Unit</a></li>
					<li id="quotation_template"><a href="<%=request.getContextPath() %>/manage_quotation_template"><i class="fa fa-circle-o"></i> Quotation Templates</a></li>									
					<li id="state"><a href="<%=request.getContextPath() %>/manage_state"><i class="fa fa-circle-o"></i> State</a></li>
					<li id="subcategory"><a href="<%=request.getContextPath() %>/manage_subcategory"><i class="fa fa-circle-o"></i> Sub-Category</a></li>									
					<li id="tax"><a href="<%=request.getContextPath() %>/manage_tax"><i class="fa fa-circle-o"></i> Tax</a></li>															
				</ul>
			</li>			
		</ul>
	</section>
</aside>