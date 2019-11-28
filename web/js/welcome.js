$.ajax({
    type:"POST",
    url:"http://localhost:8080/NWI_20191119___JavaEE_war_exploded/welcome.action",
    data:"",
    success:function (data) {
        $("#commodity").html("");
        let json=JSON.parse(data);
        //循环遍历商品信息
        console.log(json+"000000-----");
        if (json.flag=="666"){
        $.each(json.object,function (index,item) {
            $("#commodity").append
                ("<div class='div1'>"+
                    "<form action='addToCart.action' method='post'>"+
                "<div>"+"<div>商品id：<label>"+item.cid+"</label></div>"+
                "<div>"+"<div>商品类别：<label>"+item.caid+"</label></div>"+
                "<div>"+"<div>商品名称：<label>"+item.cname+"</label></div>"+
                "<div>"+"<div>商品价格：<label>"+item.cprice+"</label></div>"+
                "<div>"+"<div>商品库存：<label>"+item.inStock+"</label></div>"+
                "<div>"+"<div>商品产地：<label>"+item.place+"</label></div>"+
                "<div>"+"<div>商品描述：<label>"+item.productDes+"</label></div>"+
                "<div>"+"<div>商品图片：<label>"+item.productImg+"</label></div>"+
                    "<input type='hidden' name='cid' value='"+item.id+"' />"+
                    "<input type='submit' value='加入购物车'/>"+
                    "</form>"+
                "</div>");
        });
    }else{
        $("#commodity").html("暂无商品");

     }
    },error:function (error) {
        console.log("js发生错误")
    }
});