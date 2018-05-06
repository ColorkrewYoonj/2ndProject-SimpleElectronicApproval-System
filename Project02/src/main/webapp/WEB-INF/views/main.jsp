<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="resources/CSS/bbs.css" rel = "stylesheet" type = "text/css">
<script type="text/javascript" src="<c:url value ="/resources/js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript">
function pagingMove(currentPage,signState_id,isPersonal){
	init(signState_id,isPersonal,currentPage)
}
//////////////////////////////////////////////////////
function readOne(doc_id,page){
	$.ajax({
		url : "selectOne",
		type : "post",
		data : {
			doc_id : doc_id,
			page : page
		},
		dataType : "json",
		success : function(doc){
			$('#center').html('');
			var str2 = '<table border ="1">';
			str2 += '<tr>';
			str2 += '<td colspan="2">user</td>';
			str2 += '<td colspan="2">'+doc.board.employee_name+'</td>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td>office</td>';
			str2 += '<td>'+doc.board.department_name+'</td>';
			str2 += '<td>settlement</td>';
			str2 += '<td>'+doc.board.signState+'</td>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td colspan="2">data</td>';
			str2 += '<td colspan="2">'+doc.board.draftDate+'</td>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td colspan="2">title</td>';
			str2 += '<td colspan="2">'+doc.board.doc_name+'</td>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td colspan="4"></td>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td colspan="4"><a href ="download?doc_id='+doc_id+'">'+doc.board.doc_content+'</a></td>'
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td colspan="4"><input type="button" value = "delete" id = "Del" employee_id ="'+doc.board.employee_id+'" doc_id = "'+doc.board.doc_id+'" >';
			str2 += '<input type="button" value = "list" id = "List" onclick="init(\''+doc.board.signState_id+'\',\''+doc.board.isPersonal+'\','+doc.page+');">';
			if (doc.board.manager_id == "${sessionScope.id}" && doc.board.signState_id != 'r') {
				str2 += '<input type="button" value = "approval" id = "sign" doc_id = "'+doc.board.doc_id+'">';
				str2 += '<input type="button" value = "restoration" id = "reject" doc_id = "'+doc.board.doc_id+'">';
			}
			str2 += '</tr>';
			$('#center').html(str2);
			//삭제 부분//
			$("#Del").on('click',function(){
				var employee_id = $(this).attr('employee_id');
				var doc_id = $(this).attr('doc_id');
				if (employee_id == "${sessionScope.id}" && confirm("U really wanna delete this?")) {
					$.ajax({
						url : "delete",
						type : "post",
						data : {
							doc_id : doc_id
						},
						success : function(){
							location.reload();
							alert("delete done");
						},
						error : function(e){
							alert(JSON.stringify(e));
						}
					});
				}else{
					return;
				}
			});
			$('#sign').on('click',function(){
				var doc_id = $(this).attr('doc_id');
				var employee_id =  "${sessionScope.id}";
				$.ajax({
					url : "sign",
					type : "post",
					contentType : "application/json; charset=utf-8",
					data : JSON.stringify({
							doc_id : doc_id,
							employee_id : employee_id
					}),
					success : function(){
						alert("appovaled")
						location.reload();
					},
					error : function(){
						
					}
				});
			});
			
			$('#reject').on('click',function(){
				var doc_id = $(this).attr('doc_id');
				$.ajax({
					url : "reject",
					type : "post",
					data : {
							doc_id : doc_id
					},
					success : function(){
						alert("restorated")
						location.reload();
					},
					error : function(){
						
					}
				});
			});
			
		},
		error : function(e){
			alert(JSON.stringify(e));
		}
	});
};
///////////////////////////////////////////////////////////
function init(signState_id,isPersonal,page){
	var person = "${sessionScope.id}";
	$.ajax({
		url : "select",
		type : "get",
		data : {
			signState_id : signState_id,
			isPersonal : isPersonal,
			person : person,
			page : page
		},
		dataType : "json",
		success : function(model){
			$('#center').html('');
			var ip = '\''+model.signState_id+'\',\''+model.isPersonal+'\'';
			var str = '<h1>'+model.isPersonal+'Document Box</h1>';
				str +='<table border ="1">';
				str += '<tr>';
				str += '<th>office</th>';
				str += '<th>title</a></th>';
				str += '<th>writer</th>';
				str += '<th>deadline</th>';
				str += '<th>division</th>';
				str += '</tr>';
			$.each(model.list,function(index, item){
				str += '<tr>';
				str += '<td>'+item.department_name+'</td>';
				str += '<td><a href="javascript:;" doc_id="'+item.doc_id+'" currentPage="'+model.navi.currentPage+'" class="read">'+item.doc_name+'</a></td>';
				str += '<td>'+item.employee_name+'</td>';
				str += '<td>'+item.draftDate+'</td>';
				str += '<td>'+item.isPersonal+'</td>';
				str += '</tr>';
			});
			str +='</table>';
			str +='<a href="javascript:pagingMove(('+model.navi.startPageGroup+'-1),'+ip+')">◁◁</a>';
			str +='<a href="javascript:pagingMove(('+model.navi.currentPage+'-1),'+ip+')">◀</a>';
			for (var i = model.navi.startPageGroup; i <= model.navi.endPageGroup; i++) {
				str += '<a href="javascript:pagingMove('+i+','+ip+')">'+i+'</a>';
			}
			str +='<a href="javascript:pagingMove(('+model.navi.currentPage+'+1),'+ip+')">▶</a>';
			str +='<a href="javascript:pagingMove(('+model.navi.endPageGroup+'+1),'+ip+')">▷▷</a>';
			$('#center').html(str);
			
			$('.read').on('click', function(){
				var doc_id = $(this).attr('doc_id');
				readOne(doc_id,model.navi.currentPage);
			});
		},
		error : function(e){
			alert(JSON.stringify(e));
		}
	});
}
//////////////////////////////////////////////////////

$(function(){
	$('#writeBt').on('click',function(){
		writeform();
	});
	$('.waiting').on('click', function(){
		var signState_id = $(this).attr('signState_id');
		var isPersonal = $(this).attr('isPersonal');
		init(signState_id,isPersonal,'1');
	});
	$('.ongoing').on('click',function(){
		var signState_id = $(this).attr('signState_id');
		var isPersonal = $(this).attr('isPersonal');
		init(signState_id,isPersonal,'1');
	});
	$('.complete').on('click',function(){
		var signState_id = $(this).attr('signState_id');
		var isPersonal = $(this).attr('isPersonal');
		init(signState_id,isPersonal,'1');
	});
	$('.rejected').on('click',function(){
		var signState_id = $(this).attr('signState_id');
		var isPersonal = $(this).attr('isPersonal');
		init(signState_id,isPersonal,'1');
	});
	$('#need').on('click', function(){
		var employee_id =  "${sessionScope.id}";
		$.ajax({
			url : "need",
			type : "get",
			data : {
				employee_id : employee_id
			},
			dataType : "json",
			success : function(model){
				$('#center').html('');
				var str = '<h1>Personal Document Box</h1>';
					str +='<table border ="1">';
					str += '<tr>';
					str += '<th>office</th>';
					str += '<th>title</a></th>';
					str += '<th>writer</th>';
					str += '<th>deadline</th>';
					str += '<th>division</th>';
					str += '</tr>';
				$.each(model.list,function(index, item){
					str += '<tr>';
					str += '<td>'+item.department_name+'</td>';
					str += '<td><a href="javascript:;" doc_id="'+item.doc_id+'" currentPage="'+model.navi.currentPage+'" class="read">'+item.doc_name+'</a></td>';
					str += '<td>'+item.employee_name+'</td>';
					str += '<td>'+item.draftDate+'</td>';
					str += '<td>'+item.isPersonal+'</td>';
					str += '</tr>';
				});
				str +='</table>';
				$('#center').html(str);
				
				$('.read').on('click', function(){
					var doc_id = $(this).attr('doc_id');
					readOne(doc_id,model.navi.currentPage);
				});
			},
			error : function(e){
				alert(JSON.stringify(e));
			}
		});
	});
});
//////////////////////////
function writeform(){
	$.ajax({
		url : "writeform",
		type : "get",
		success : function(map){
			$('#center').html('');
			var str2 = '<table border ="1">';
			str2 += '<tr>';
			str2 += '<td>Department</td>';
			str2 += '<td><select id = "write_department">';
			$.each(map.depart,function(index, item){
				str2 +='<option value="'+item.department_id+'">'+item.department_name+'</option>';
			});
			str2 += '</td></tr>';
			str2 += '<tr>';
			str2 += '<td>title</td>';
			str2 += '<td><input type = "text" id ="write_name" value = ""></td>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td>P/office</td>';
			str2 += '<td><input type = "radio" value = "personal" name ="isPersonal">personal';
			str2 += '<input type = "radio" value = "office" name ="isPersonal">office</td>';
			str2 += '</tr>';
			str2 += '<td>양식</td>';
			str2 += '<td><select id = "doc_content" onchange = "javascript:contentloard();">';
			str2 +='<option value="">paper forms</option>';
			$.each(map.content,function(index, item){
				str2 +='<option value="'+item+'">'+item+'</option>';
			});
			str2 += '</td></tr>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td colspan="4"><textarea id ="write_content" rows="40" cols="100"></textarea></td>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<td colspan="4"><input type="button" value = "check" id = "WrBt">';
			str2 += '<input type="button" value = "cancel" onclick ="javascript:location.reload();"></td>';
			str2 += '</tr>';
			$('#center').html(str2);
			
			///작성요청 보내기///
			$('#WrBt').on('click',function(){
				var write_department = $('#write_department option:selected').val();
				var write_name = $('#write_name').val();
				var write_content = $('#write_content').val().split('\n');
				var write_personal = $("input[type=radio][name=isPersonal]:checked").val();
				var write_id = "${sessionScope.id}";
				$.ajax({
					url : "write",
					type : "post",
					contentType : "application/json; charset=utf-8",
					data : JSON.stringify({
						'department_id' : write_department,
						'employee_id' : write_id,
						'doc_name' : write_name,
						'isPersonal' : write_personal,
						'content_real' : write_content,
					}),
					success : function(){
						init('w',write_personal,1);
					},
					error : function(e){
						alert(JSON.stringify(e));
					}
				});
			});
		},
		error : function(e){
			alert(JSON.stringify(e));
		}
	});		
}
function contentloard(){
	var content = $('#doc_content option:selected').val();
	$.ajax({
		url : "content",
		type : "post",
		data : {
			content : content
		},
		success : function(result){
			var str = "";
			for (var i = 0; i < result.length; i++) {
				str+=(result[i]);
			};
			/* var a = document.getElementById("test");
			var sb = /\,/g;
			var test = decodeURIComponent(result.replace(sb, " ") );
			a.innerHTML = test; */
			$('#write_content').text(str);
		},
		error : function(){
		}
	})
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>Electronic document approval system</h1>
<p>Hello. ${sessionScope.name} ${sessionScope.id}</p>
<div id = "side">
<p>Personal Information</p>
	<ul>
		<li><a href="logout">log-out</a></li>
		<li><a href="javascript:;" id = "need">need approval</a></li>
	</ul>
<p>Write draft</p>
	<ul>
		<li><a href="javascript:;" id="writeBt">write form</a></li>
	</ul>
<p>Personal Document</p>
	<ul>
		<li><a href="javascript:;" class = "waiting" signState_id = "w" isPersonal="개인">결재 대기</a></li>
		<li><a href="javascript:;" class = "ongoing" signState_id = "o" isPersonal="개인">결재 진행</a></li>
		<li><a href="javascript:;" class = "complete" signState_id = "c" isPersonal="개인">결재 완료</a></li>
		<li><a href="javascript:;" class = "rejected" signState_id = "r" isPersonal="개인">결재 반려</a></li>
	</ul>
<p>Office Document</p>
	<ul>
		<li><a href="javascript:;" class = "waiting" signState_id = "w" isPersonal="부서">결재 대기</a></li>
		<li><a href="javascript:;" class = "ongoing" signState_id = "o" isPersonal="부서">결재 진행</a></li>
		<li><a href="javascript:;" class = "complete" signState_id = "c" isPersonal="부서">결재 완료</a></li>
		<li><a href="javascript:;" class = "rejected" signState_id = "r" isPersonal="부서">결재 반려</a></li>
	</ul>
</div>
<div id ="center">
</div>
</html>