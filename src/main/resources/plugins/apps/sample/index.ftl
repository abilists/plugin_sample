<#import "/apps/layout/plugins/abilistsPluginsLayout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<div class="row">
<div class="col-sm-12">
	<div class="caption mittle-size-title middle-works-bg">
		<h5><b>출퇴근 관리</b></h5>
	</div>
	<div class="item-box" style="overflow: hidden;">

	<div class="row" style="margin-right: 0px;">
		<div class="col-sm-12">
		Hello World!
		</div>
	</div>

	<div id="newMdataFormId" class="item-box" style="display: none;">
	<form name="newForm" class="form-horizontal" action="${configBean.contextPath?if_exists}/plugins/sample/istSample" method="post" id="newFormId" onkeypress="return captureReturnKey(event);">
	  	<div class="row">
	  	  	<div class="col-sm-12 col-md-12">	
					<label class="control-label">샘플 내용</label>
			  	<div class="input-group" style="float:right; width: 100%;">
			  		<span class="input-group-addon"><span id="calendarId" class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
			  		<input class="form-control" type="text" id="usmSampleId" name="usmSample" placeholder="sample" autocomplete="off" />
			  	</div>
			</div>
	  	</div>
	  	<input type="hidden" name="token" value="<#if model??>${model.token?if_exists}</#if>" />
	  	<br/>
		<p align="center">
			<button type="button" class="btn btn-primary" onclick="return confirmData('newFormId');" ><@spring.message "works.button.save"/></button>
			<button class="btn btn-primary" type="button" onClick="newFormCancel();"><@spring.message "works.button.cancel"/></button>
		</p>
	</form>
	</div>

	<div id="udtMdataFormId" class="item-box" style="background-color: #efebe7;margin: 10px; display: none;">
	<form id="udtFormId" name="udtForm" class="form-horizontal" action="${configBean.contextPath?if_exists}/plugins/sample/udtsample" method="post" onkeypress="return captureReturnKey(event);">
		<div class="row">
	  	  	<div class="col-sm-12 col-md-12">	
	  			<label class="control-label">샘플 내용</label>
			  	<div class="input-group" style="float:right; width: 100%;">
			  		<span class="input-group-addon"><span id="calendarId" class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
			  		<input class="form-control" type="text" id="usmSampleId" name="usmSample" placeholder="sample" autocomplete="off" />
			  	</div>
			</div>
			<input type="hidden" id="usmNoId" name="usmNo" />
			<input type="hidden" id="tokenId" name="token" />
			<br/>
			<p align="center">
		      <button type="button" class="btn btn-primary" onclick="return confirmData('udtFormId');">저장</button>
		      <button type="button" class="btn btn-primary" onClick="udtFormCancel();">취소</button>
		    </p>
		</div>
	</form>
	</div>

	<div id="userSampleId" style="margin: 10px;">
		<div id="sampleTableId" style="border: 1px solid #CDCDCD;">
		<div>
	    <ul class="table-ul table-ul-header">
	    	<li>샘플 내용</li>
	        <li>입력한 날짜</li>
	    </ul>
	    <#if plugins??>
	    <#if plugins.sampleList?has_content>
	    <#list plugins.sampleList as sample>
		    <ul class="table-ul bg-color ul-hover">
	        	<li><#if sample.utrWorkHour??>${sample.usmSample?if_exists}</#if></li>
		        <li><#if sample.utrEndTime??>${sample.insertTime?string('HH:mm:ss')?if_exists}</#if></li>
		    </ul>
		</#list>
		</#if>
		</#if>
		</div>
		</div>
	</div>

	<nav class="text-center">
    <ul class="pagination">
	    <#if model?exists>
	  	<#if model.paging?exists>
			<#if model.paging.prevPage?exists>
			<li><a href="/plugins/sample?nowPage=${model.paging.prevPage.nowPage}&allCount=${model.paging.allCount?c}" title="Prev" accesskey="*">Prev</span></a></li>
			</#if>
			<#if model.paging.pagingInfoList?has_content>
				<#list model.paging.pagingInfoList as pageList>
					<#if model.paging.nowPage?if_exists == pageList.pageNumber?if_exists>
					<li class="active"><a href="#">${pageList.pageNumber} <span class="sr-only">(current)</span></a></li>
					<#else>
					<li><a href="/plugins/sample?nowPage=${pageList.pageNumber}&allCount=${model.paging.allCount?c}">${pageList.pageNumber}</a></li>
					</#if>
				</#list>
			</#if>
			<#if model.paging.nextPage?exists>
			<li><a href="/plugins/sample?nowPage=${model.paging.nextPage.nowPage}&allCount=${model.paging.allCount?c}" accesskey="#" title="Next">Next</a></li>
			</#if>
		</#if>
		</#if>
	</ul>
	</nav><!-- end #nav -->

</div>
</div>

<#include "/apps/common/abilistsPluginsLoadJs.ftl"/>
<#include "/apps/sample/js/indexJs.ftl"/>

</@layout.myLayout>