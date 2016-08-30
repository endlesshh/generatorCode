<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="_csrf" content="${'$'}{_csrf.token}"/><meta name="_csrf_header" content="${'$'}{_csrf.headerName}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ftl_description}列表</title>

<link  	type="text/css"	href="${'$!'}webPath/resources/style/system/manage/${'$!'}{config.websiteCss}/template.css"  rel="stylesheet" />
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery.shop.common.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery.poshytip.min.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/security.js"></script>

</head>

<script>
</script>

<body>
  <div class="cont">
  
	    <h1 class="seth1">${ftl_description}管理</h1>
	    
	    <div class="settab">
	    	<span class="tab-one"></span> 
	    	<span class="tabs">
	    		 <a href="${'$!'}webPath/wifuns/${entityName?uncap_first}_list.htm"  class="this">管理</a>
	    		|<a href="${'$!'}webPath/wifuns/${entityName?uncap_first}_add.htm">新增</a> 
	        </span> 
	        <span class="tab-two"></span>
	     </div>
	     
		<form>
		    <div class="allmem_search">
		      <ul>
		         <li><span>
			             <select name="condition" id="condition">
			             	<#list originalColumns as po>
								<#if po.fieldName != wifuns_table_id> 
									<#if po.fieldType == "java.lang.String"> 
										<option value="${po.fieldName}" #if(${'$!'}condition=="${po.fieldName}") selected #end>${po.filedComment}</option>
									</#if>
								</#if> 
							</#list> 
			             </select>
			         </span> 
		          	 <span class="allmen size4">
		          		  <input name="value" type="text" id="value" value="${'$!'}value"/>
		          	 </span>
		          	 <span class="btn_search">
		          		  <input name="" type="submit"  value="搜索" style="cursor:pointer;" title="搜索${ftl_description}"/>
		             </span> 
		         </li>
		      </ul>
		    </div>
		    ${'$!'}{_csrf.UniqueHiddenField} 
		</form>
		
		<div class="operation">
		      <h3>友情提示</h3>
		      <ul>
		          <li>此处可以添加提示</li>
		      </ul>
		</div>
		    
		<form name="ListForm" id="ListForm"  action="${'$!'}webPath/wifuns/${entityName?uncap_first}_list.htm" method="post">
		    
		    <div class="allmem_table">
		    
		      	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		       		<tr style="background: #if(${'$!'}config.websiteCss=='blue') #2A7AD2 #end 
		     		 	  #if(${'$!'}config.websiteCss=='black')#333 #end; height:30px; color:#FFF">
		     		 	  
		     		 	  <td width="8%" align="left">&nbsp;</td>
		     		 	  
	     		 	  	  <#list originalColumns as po>
							 <#if po.fieldName != wifuns_table_id> 
								 <td width="6%" align="left">${po.filedComment}</td>
							 </#if> 
						  </#list>  
						  
				          <td  align="center">操作</td> 
				    </tr>
				    
			        #foreach(${'$'}!obj in ${'$'}objs)
			        <tr>
			          	<td  align="left">
			          		<input name="id" type="checkbox" id="id" style="width:16px; border:none;" value="${'$!'}obj.id" />
			          	</td>
			          	
			          	<#list originalColumns as po>
							 <#if po.fieldName != wifuns_table_id> 
								  <td align="left">${'$'}!obj.${po.fieldName}</td>
							 </#if> 
						</#list>
						 <td class="ac8" align="left"><a href="${'$!'}webPath/wifuns/${entityName?uncap_first}_edit.htm?id=${'$!'}obj.id&currentPage=${'$!'}currentPage">编辑</a> | <a href="javascript:void(0);" onclick="if(confirm('合作伙伴删除后不可恢复，是否继续删除?'))window.location.href='${'$!'}webPath/wifuns/${entityName?uncap_first}_del.htm?mulitId=${'$!'}obj.id&currentPage=${'$!'}currentPage'">删除</a></td>  
			        </tr>
			        #end
		        
		        	<tr style="background:#F2F2F2; height:30px;">
			          	<td colspan="9">
			          		<div class="shopbtn shopfx">
			              		<input name="all" type="checkbox" id="all" onclick="selectAll(this)" value="" />&nbsp;&nbsp;全部
			              	</div>
			            	<div class="shop_btn_del shopbtn">
			              		<input name="" type="button" value="删除" style="cursor:pointer;" onclick="cmd('${'$'}!webPath/wifuns/${entityName?uncap_first}_del.htm')"/>
			            	</div>
			            </td>
		        	</tr>
		      </table>
		    </div>
		    <div class="fenye">
			  	<input name="value" type="hidden" id="value" value="${'$!'}value" />
			  	<input name="condition" type="hidden" id="condition" value="${'$!'}condition" />
		      	<input type="hidden" name="currentPage" id="currentPage" value="${'$!'}currentPage" />
		      	<input name="mulitId" type="hidden" id="mulitId" />
		     	${'$!'}gotoPageFormHTML
			</div>
			${'$!'}{_csrf.UniqueHiddenField}
		</form>
  </div>
</body>
</html>
