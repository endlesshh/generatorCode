<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="_csrf" content="${'$!'}{_csrf.token}"/><meta name="_csrf_header" content="${'$!'}{_csrf.headerName}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ftl_description}新增</title>
<link 	type="text/css"	href="${'$!'}webPath/resources/style/system/manage/${'$!'}{config.websiteCss}/template.css"  rel="stylesheet" />
<link  	type="text/css"	href="${'$!'}webPath/resources/style/common/css/jquery-ui-1.8.22.custom.css"  rel="stylesheet" />
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery.shop.common.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery.poshytip.min.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery-ui-1.8.21.js"></script>
<script type="text/javascript"	src="${'$!'}webPath/resources/js/jquery.zh.cn.js"></script>
</head>

<script>
jQuery(document).ready(function(){
	// 添加新的验证方法的示例
  	//  jQuery.validator.addMethod("positiveinteger", function(value, element) {
  	//	var aint=parseInt(value);	
  	//	return aint>=0&& (aint+"")==value;   
  	//  }, "请填入有效的数字"); 
	//	jQuery("#theForm").validate({
	//    	rules:{
	//		 	sequence:{positiveinteger:true}
	//		},
	//	  	messages:{
	//			 sequence:{positiveinteger:"活动序号必须为正整数"}
	//		}
	//  });

	
  	jQuery("#theForm").validate({  
  	
		rules:{
		<#list originalColumns as po>
			<#if po.fieldName != wifuns_table_id> 
				 ${po.fieldName}:{required:true},
			 </#if>
		</#list>  
		},
	    messages:{
	    	<#list originalColumns as po>
				<#if po.fieldName != wifuns_table_id> 
					 ${po.fieldName}:{required:"${po.filedComment}不能为空"},
				 </#if>
			</#list> 
		}
	    
  	});
 	//改变系统提示的样式
	 jQuery("span .w").mousemove(function(){
			var id=jQuery(this.parentNode).attr("id");
			if(id="nothis"){
			   jQuery(this.parentNode).attr("id","this")
			}
	 }).mouseout(function(){
	     var id=jQuery(this.parentNode).attr("id");
		 if(id="this"){
		   jQuery(this.parentNode).attr("id","nothis")
		 }
	 });
 
});

function saveForm(){
	jQuery("#theForm").submit();
}
</script>

<body>
	<form action="${'$!'}webPath/wifuns/${entityName?uncap_first}_save.htm" method="post" name="theForm" id="theForm">
	  <div class="cont">
	    	<h1 class="seth1">${ftl_description}管理</h1>
	    	<div class="settab">
	    		<span class="tab-one"></span> 
	    		<span class="tabs"> 
	    			<a href="${'$!'}webPath/wifuns/${entityName?uncap_first}_list.htm">所有${ftl_description}</a> | 
	    			<a href="${'$!'}webPath/wifuns/${entityName?uncap_first}_add.htm"  
	    				#if(!${'$!'}edit)class="this"#end>新增${ftl_description}</a> 
	    				#if(${'$!'}edit)<a href="javascript:void(0);" class="this">编辑</a>#end</span> 
	    		<span class="tab-two"></span>
	    	</div>
	    	
	    <div class="setcont" id="base">
	    
		    <#list originalColumns as po> 
		    
		    	<#if po.fieldName == wifuns_table_id> 
		    		<input name="${po.fieldName}" type="hidden" id="${po.fieldName}" value="${'$!'}obj.${po.fieldName}" />
				</#if>
				
				<ul class="set1">
					<#if po.fieldName != wifuns_table_id> 
			    		
			    		<li>
			    			<strong class="orange fontsize20">*</strong>${po.filedComment}
				        </li> 
				        <li>
					        <span class="webname">
					          	<input name="${po.fieldName}" type="text" id="${po.fieldName}" value="${'$!'}obj.${po.fieldName}" />
					        </span>
		       			 </li>
					</#if>
		      	</ul>
			</#list>  
	    </div>
	 </div>
	 <div class="submit">
	    <input name="" type="button" value="保存" style="cursor:pointer;" onclick="saveForm();"/>
	 </div>
	 ${'$!'}{_csrf.UniqueHiddenField} 
	 </form>
</body>
</html>
