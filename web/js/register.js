$(document).ready(function () {
    $("#submit0").click(function () {
        if ($("#uacc").val()==null||$("#uacc").val()==""){
            alert("账号不能为空");
            return;
        }
        if ($("#upwd").val()==null||$("#upwd").val()==""){
            alert("密码不能为空");
            return;
        }
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/NWI_20191119___JavaEE_war_exploded/register.action",
            data:{
                //uid:$("#uid").val(),
                uname:$("#uname").val(),
                uacc:$("#uacc").val(),
                upwd:$("#upwd").val(),
                utel:$("#utel").val()
            },
            success:function (data) {
                //将json字符串转换为json数组对象
                console.log("控制台信息——————"+data);
                let json=JSON.parse(data);
                if (json.mname==null){
                    //登录失败
                    $("#msg").html(json.msg);
                }else{
                    //登录成功
                    window.location.href=json.url;
                }
            },
            error:function (error) {
                console.log(error.status);
                console.log(error.responseText);
            }
        });
    });
});