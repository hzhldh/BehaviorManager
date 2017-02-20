<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="keywords" content="scclui框架">
	<meta name="description" content="scclui为轻量级的网站后台管理系统模版。">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>首页</title>   
    <link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/> 
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>  
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/layer/3.0.1/layer.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>    
  </head>
  
  <body>
	<div class="container-fluid">  
        <div id="toolbar" class="btn-group">  
            <a><button type="button" id="add" class="btn btn-default"  data-toggle="modal" data-target="#addModal">  
                <i class="glyphicon glyphicon-plus"></i>  
            </button></a>
            <a><button onclick="editHr();" type="button" id="edit" class="btn btn-default">  
                <i class="glyphicon glyphicon-pencil"></i>  
            </button></a>  
            <a><button type="button" onclick="deleteHr();" id="delete" class="btn btn-default">  
                <i class="glyphicon glyphicon-trash"></i>  
            </button></a>  
        </div>  
        <table id="empUserList" >  
        </table>  <!-- 留意-->          
    </div>
    
    
     <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-hidden="true">
                   <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">添加记录</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form" action="javascript:void(0)">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" placeholder="请输入姓名">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="age" placeholder="请输入年龄">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="classname" placeholder="请输入班级">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="addRecord" onclick="addRecord();">提交</button>
                            </div>
                        </div>
                    </div>
         </div>
         
        <!--  编辑弹出层 -->
         <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
                   <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">编辑</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form" action="javascript:void(0)">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" autocomplete="off">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="age" autocomplete="off">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="classname" autocomplete="off">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="editRecord" onclick="editRecord();">提交更改</button>
                            </div>
                        </div>
                    </div>
         </div>
        
	
<script type="text/javascript">       
//提交编辑需要全局变量
  editId=0;
     
 $(function() {  
    querys();  
})  
  
function querys() {  
    $("#empUserList").bootstrapTable({  
        url : 'showAllStudents',  
        dataType: "json",
        pagination:true,
        singleSelect:false,
        showExport: true, 
        exportDataType: "basic",  
        queryParams : queryParams, 
      //pageSize: 5,  //每页显示的记录数  ，默认显示10条
      //pageNumber:1, //当前第几页  ,页码
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表  
        toolbar : "#toolbar",// 指定工具栏  
        showColumns : true, // 显示隐藏列  
        showRefresh : true, // 显示刷新按钮
        search: true, //显示搜索框
        sidePagination: "server", //服务端处理分页  
        columns : [ {  
            title : 'state',
            field : 'state',  
            class:"tablebody",
            align : 'center',  
            valign : 'middle',  
            checkbox : true  
        }, {  
            title : '编号',  
            field : 'id',  
            align : 'center',  
            valign : 'middle',  
            sortable : true  
        }, {  
            title : '姓名',  
            field : 'name',  
            align : 'center',  
            valign : 'middle',  
            sortable : true,
        }, {  
            title : '年龄',  
            field : 'age',  
            align : 'center',  
            valign : 'middle',  
            sortable : true  
        }, {  
            title : '班级',  
            field : 'classname',  
            align : 'center',  
            valign : 'middle',  
            sortable : true  
        }],         
    })  
}  

/** 刷新页面 */  
function refresh() {  
    $('#empUserList').bootstrapTable('refresh');  
}  
/**查询条件与分页数据 */  
function queryParams(pageReqeust) {  
   // pageReqeust.enabled = $("#enabled").val();  
   // pageReqeust.querys = $("#querys").val();  
   // pageReqeust.pageNo = this.offset;  
    pageReqeust.offset= this.pageNumber;  
    return pageReqeust;  
}  
/** 编辑数据 */  
function editHr() {  
    var selectRow = $("#empUserList").bootstrapTable('getSelections');  
    if (selectRow.length != 1) {  
        layer.alert('请选择并只能选择一条数据进行编辑！', {icon: 2});  
        return false;  
    } else {  
        $("#editModal").modal("show"); 
        $("#editModal").find("#name").val(selectRow[0].name);
        $("#editModal").find("#age").val(selectRow[0].age);
        $("#editModal").find("#classname").val(selectRow[0].classname);
        //为全局变量赋值
        editId=selectRow[0].id;
    }  
}  
/**  提交编辑*/
function editRecord(){
    var name=$("#editModal").find("#name").val();
    var age=$("#editModal").find("#age").val();
    var classname=$("#editModal").find("#classname").val();   
    $.ajax({  
                url:'/BehaviorManager/page/updateStudent',  
                type:'get',
                traditional: true,  //阻止深度序列化，向后台传送数组  
                data:{id:editId,name:name,age:age,classname:classname},  
                contentType:'application/json',  
                success:function(msg){  
                    if(msg==0){  
                        $("#editModal").modal("hide");
                        layer.alert("更改成功",{icon:1,time:2000});      
                    }else{  
                        layer.alert("更改失败",{icon:2});  
                    }         
                    refresh();  
                }  
            })  
}



/** 
 * 添加数据 
 */  
function addRecord(){
    var name=$("#addModal").find("#name").val();
    var age=$("#addModal").find("#age").val();
    var classname=$("#addModal").find("#classname").val();   
    $.ajax({  
                url:'/BehaviorManager/page/addStudent',  
                type:'get',
                traditional: true,  //阻止深度序列化，向后台传送数组  
                data:{name:name,age:age,classname:classname},  
                contentType:'application/json',  
                success:function(msg){  
                    if(msg==0){  
                        //清空全部表单信息，并隐藏弹出框
                        $("#addModal").find("input").val("");
                        $("#addModal").modal("hide");
                        layer.alert("添加成功",{icon:1,time:2000});      
                    }else{  
                        layer.alert("添加失败",{icon:2});  
                    }  
                    refresh();  
                }  
            })  
}


/** 
 * 删除数据 
 */  
function deleteHr() {  
    var hrs = $("#empUserList").bootstrapTable('getSelections');  
    if (hrs.length < 1) {  
        layer.alert('请选择一条或多条数据进行删除！', {icon: 2});  
    } else {  
        layer.confirm('确定要删除所选数据？', {icon: 3, title:'提示'}, function(){  
            var id = [];  
            for (var i=0;i<hrs.length;i++){  
                    id.push(hrs[i].id);  
            }  
            $.ajax({  
                url:'/BehaviorManager/page/del',  
                type:'get',
                traditional: true,  //阻止深度序列化，向后台传送数组  
                data:{id:id},  
                contentType:'application/json',  
                success:function(msg){  
                    if(msg==0){  
                        layer.alert("删除成功",{icon:1,time:2000});  
                        refresh();  
                    }else{  
                        layer.alert("删除失败",{icon:2});  
                    }  
                    
                }  
            })  
          });  
    }  
}       
</script>  
  </body>
</html>
