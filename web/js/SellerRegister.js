$(document).ready(function () {
    $("#SRe").click(function () {
        if ($("#sacc").val()==null||$("#sacc").val()==""){
            alert("账号不能为空");
            return;
        }
        if ($("#spwd").val()==null||$("#spwd").val()==""){
            alert("密码不能为空");
            return;
        }
        if ($("#stel").val()==null||$("#stel").val()==""){
            alert("联系电话不能为空")
            return;
        }
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/NWI_20191119___JavaEE_war_exploded/SellerRegister.action",
            data:{
                sacc:$("#sacc").val(),
                spwd:$("#spwd").val(),
                shopName:$("#shopName").val(),
                mainBusiness:$("#mainBusiness").val(),
                stel:$("#stel").val()
            },
            success:function (data) {
                //将json字符串转换为json数组对象
                console.log("控制台信息——————"+data);
                let json=JSON.parse(data);
                if (json.mname==null){
                    //登录失败
                    $("#smsg").html(json.msg);
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