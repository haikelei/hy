$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByGET("/hy/account/getAllCount", {}, initPersonCallback);


})
/**
 * 最大值id
 */
var maxId = 0;
/**
 * 当前页码
 */
var currentPage = 1;
/**
 * 一页显示条数
 */
var showNum = 10;
/**
 * select类型
 */
var type = 0;

function initPersonCallback(result) {
    var count = result;
    var page = Math.ceil(count / showNum);
    pagination(count, page, initAllPersonPage);
}

function initAllPersonPage(page) {
    currentPage = page;
    ajaxByGET("/hy/account/accountList", {start: (page - 1) * showNum, limit: showNum}, listAllPersonCallBack);

}


function dateFormatUtil(longTypeDate) {
    var dateTypeDate = "";
    var date = new Date(longTypeDate);
    dateTypeDate += date.getFullYear();   //年
    dateTypeDate += "-" + (date.getMonth() + 1); //月
    dateTypeDate += "-" + date.getDate();  //日
    dateTypeDate += ' '
    dateTypeDate += date.getHours();
    dateTypeDate += ":" + date.getMinutes();
    dateTypeDate += ":" + date.getSeconds();
    return dateTypeDate;
}


function listAllPersonCallBack(result) {
    var array = result.data;
    var table = $("#table");
    table.empty();
    for (var i = 0; i < array.length; i++) {
        var td = "<tr>";
        var id = array[i].id;
        //td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='display: none'>" + array[i].id + "</td>";
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";
        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";
        var roleName = array[i].roleName == undefined ? " " : array[i].roleName;
        td += "<td style='vertical-align: middle;'>" + roleName + "</td>";
        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";
        td += "<td style='vertical-align: middle;'><button onclick='editPerson(" + id + ")' type='button' class='btn btn-primary'>编辑</button><button type='button' class='btn btn-primary' onclick='delPerson(" + array[i].id + ")'>删除</button></td>";
        table.append(td);
    }
}


function delPerson(id) {

    ajaxByGET("/hy/account/delAccount", {id: id}, initDel)
}

function initDel(result) {

    if (result.code = 200) {
        alert("删除成功")
        location.reload();
    } else {
        alert("操作失败")
        location.reload();
    }

}

//拿到下拉框的权限名的列表
findRoleList();

function findRoleList() {
    ajaxByGET("/hy/account/findListRole", {start: 0, limit: 100000}, initListRole)
}

function initListRole(result) {
    var role_html = "";
    var roles = result.data;
    for (var i = 0; i < roles.length; i++) {
        role_html += '<option value="' + roles[i].id + '">' + roles[i].name + '</option>'
        $("#roleList").html(role_html)

    }
}

function addPerson() {

    // $("#personAddModal").modal('show');
    layer.open({
        type: 1,
        shade: false,
        title: '添加管理员', //不显示标题
        area: ['600px', '400px'],
        content: $('.modal-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

}
function editPerson(id) {
    $("#editPersonId").val(id)

    ajaxByGET("/hy/account/findById", {id: id}, initFindById)

    layer.open({
        type: 1,
        shade: false,
        title: '修改管理员   (注意工号不可重复)', //不显示标题
        area: ['400px', '250px'],
        content: $('.modal-eddit-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

}
var roleId;

function initFindById(result) {
    $("#editPersonId").val(result.data.id)
    $("#edidPersonName").val(result.data.username);
    $("#editPersonCode").val(result.data.code);
    findEditRoleList()
    roleId = result.data.roleId;
}
function findEditRoleList() {

    ajaxByGET("/hy/account/findListRole", {start: 0, limit: 100000}, initEditListRole)
}

function edidPersonSubmit() {
    var id = $("#editPersonId").val()
    var name = $("#edidPersonName").val();
    var code = $("#editPersonCode").val();
    var roleId = $("#editRoleList  option:selected").val();
    ajaxByGET("/hy/account/updAccount", {id: id, username: name, code: code, roleId: roleId}, initEditFinishRole)

}

function initEditFinishRole(result) {

    if (result.code = 200) {
        alert("修改成功")
        location.reload();
    } else if (result.code == 2) {
        alert("工号不能重复")
        return;

    } else {
        alert("操作失败")
        return;
    }

}


function initEditListRole(result) {
    var role_html = "";
    var roles = result.data;
    for (var i = 0; i < roles.length; i++) {
        if (roles[i].id == roleId) {
            role_html += '<option value="' + roles[i].id + '" selected="true">' + roles[i].name + '</option>'
        } else {

            role_html += '<option value="' + roles[i].id + '">' + roles[i].name + '</option>'
        }
        $("#editRoleList").html(role_html)
    }

}


function addPersonSubmit() {
    var username = $("#addPersonName").val();
    var code = $("#addPersonCode").val();
    var password = $("#addPassword").val();
    var roleId = $("#roleList  option:selected").val();
    ajaxByGET("/hy/account/addAccount", {
        username: username,
        code: code,
        password: password,
        roleId: roleId
    }, initAddAccount)

}
function initAddAccount(result) {

    if (result.code == 2) {
        alert("工号不能重复");
        return
    }
    if (result.code == 1) {
        alert("角色不存在");
        return
    }


    if (result.code == 200) {
        alert("添加成功")
        location.reload();
    } else {
        alert("操作失败")
    }


}


function cancel() {

    location.reload();
}
