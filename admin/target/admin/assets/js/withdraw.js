$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByPOST("/hy/withdraw/findWithdrawCount", {withdrawType: 0}, initPersonCallback);


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

    //$("#aliMode").css("display","block");
    currentPage = page;
    ajaxByPOST("/hy/withdraw/findWithdraw", {
        start: (page - 1) * showNum,
        limit: showNum,
        withdrawType: 0
    }, listAllPersonCallBack);

}
function initAllWithdrawPage(result) {

    var count = result;
    var page = Math.ceil(count / showNum);
    pagination2(count, page, initAllPersonForBankPage);
}

function initAllPersonForBankPage(page) {
    currentPage = page;
    ajaxByPOST("/hy/withdraw/findWithdraw", {
        start: (page - 1) * showNum,
        limit: showNum,
        withdrawType: 1
    }, listAllPersonForBankCallBack);
}


$("select#selectForClass").click(function () {
    var se = $('#selectForClass option:selected').val();
    if (se == 0) {

        ajaxByPOST("/hy/withdraw/findWithdrawCount", {withdrawType: 0}, initPersonCallback);
    } else {
        ajaxByPOST("/hy/withdraw/findWithdrawCount", {withdrawType: 1}, initAllWithdrawPage);

    }
})


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
    $("#bankMode").css("display", "none");
    $("#aliMode").css("display", "block");

    var array = result.data;
    var table = $("#table");
    table.empty();
    for (var i = 0; i < array.length; i++) {
        var td = "<tr>";
        var id = array[i].id;
        td += "<td style='display: none'>" + array[i].id + "</td>";
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";
        var phone = array[i].phone == undefined ? " " : array[i].phone;
        td += "<td style='vertical-align: middle;'>" + phone + "</td>";
        var status = array[i].withdrawStatus == undefined ? " " : array[i].withdrawStatus;
        if (status == 100) {

            td += "<td style='vertical-align: middle;'>" + "待审核" + "</td>";
        } else if (status == 200) {

            td += "<td style='vertical-align: middle;'>" + "审核成功" + "</td>";

        } else {
            td += "<td style='vertical-align: middle;'>" + "审核失败" + "</td>";

        }

        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";
        var alipayAccount = array[i].alipayAccount == undefined ? " " : array[i].alipayAccount;
        td += "<td style='vertical-align: middle;'>" + alipayAccount + "</td>";
        var name = array[i].name == undefined ? " " : array[i].name;
        td += "<td style='vertical-align: middle;'>" + name + "</td>";
        var money = array[i].money == undefined ? " " : array[i].money;
        td += "<td style='vertical-align: middle;'>" + money + "</td>";
        if (status == 100) {
            td += "<td style='vertical-align: middle;'><button onclick='editClassify(" + id + ")' type='button' class='btn btn-primary'>审核</button></td>";

        } else {
            td += "<td style='vertical-align: middle;'></td>"
        }
        table.append(td);
    }
}


function listAllPersonForBankCallBack(result) {
    $("#aliMode").css("display", "none");
    $("#bankMode").css("display", "block");
    var array = result.data;
    var table = $("#tableBank");
    table.empty();
    for (var i = 0; i < array.length; i++) {
        var td = "<tr>";
        var id = array[i].id;
        td += "<td style='display: none'>" + array[i].id + "</td>";
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";
        var phone = array[i].phone == undefined ? " " : array[i].phone;
        td += "<td style='vertical-align: middle;'>" + phone + "</td>";
        var status = array[i].withdrawStatus == undefined ? " " : array[i].withdrawStatus;
        if (status == 100) {

            td += "<td style='vertical-align: middle;'>" + "待审核" + "</td>";
        } else if (status == 200) {

            td += "<td style='vertical-align: middle;'>" + "审核成功" + "</td>";

        } else {
            td += "<td style='vertical-align: middle;'>" + "审核失败" + "</td>";

        }
        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";
        var bank = array[i].bank == undefined ? " " : array[i].bank;
        td += "<td style='vertical-align: middle;'>" + bank + "</td>";
        var card = array[i].card == undefined ? " " : array[i].card;
        td += "<td style='vertical-align: middle;'>" + card + "</td>";
        var name = array[i].name == undefined ? " " : array[i].name;
        td += "<td style='vertical-align: middle;'>" + name + "</td>";
        var money = array[i].money == undefined ? " " : array[i].money;
        td += "<td style='vertical-align: middle;'>" + money + "</td>";
        if (status == 100) {
            td += "<td style='vertical-align: middle;'><button onclick='editClassify(" + id + ")' type='button' class='btn btn-primary'>审核</button></td>";

        } else {
            td += "<td style='vertical-align: middle;'></td>"
        }
        table.append(td);
    }
}


function editClassify(id) {
    $("#withdrawId").val(id)
    layer.open({
        type: 1,
        shade: false,
        title: '审核提现', //不显示标题
        area: ['400px', '250px'],
        content: $('.modal-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

}

function updateProveForSuccessSubmit() {

    var id = $("#withdrawId").val();
    ajaxByPOST("/hy/withdraw/updWithdraw", {type: 0, id: id}, initAllWithdrawSuc);

}
function updateProveForFailSubmit() {

    var id = $("#withdrawId").val();
    ajaxByPOST("/hy/withdraw/updWithdraw", {type: 1, id: id}, initAllWithdrawSuc);

}

function initAllWithdrawSuc(result) {
    if (result.code != 200) {
        alert(result.msg)
    } else {
        alert(操作成功)
    }

}


function cancel() {

    location.reload();
}

