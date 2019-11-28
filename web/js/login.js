$(document).ready(function () {
    $("#submit").click(function () {
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/NWI_20191119___JavaEE_war_exploded/login.action",
            data:{
                uacc:$("#acc").val(),
                upwd:$("#pwd").val()
            },
            success:function (data) {
                //将json字符串转换为json数组对象
                console.log("控制台信息——————"+data);
                let json=JSON.parse(data);
                if (json.flag==null){
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
//--------------------------------------------------------------------------------------------------------------------//
