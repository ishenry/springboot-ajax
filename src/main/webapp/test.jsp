<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=false">
<title>测试</title>
<style>
body {
width: 100%;
height: 100%;
}
.container {
position: absolute;
width:  600px;
height: 220px;
left: 50%;
top: 50%;
margin-left: -300px;
margin-top: -120px;
text-align: center;
}
.container h1 {
font-size: 40px;
}
.container .input {
display: block;
width: 330px;
height: 60px;
margin: 0 auto;
}
.container button {
width: 330px;
height: 70px;
font-size: 30px;
margin-top: 10px;
}
</style>
</head>

<body>

<div class="container">
    <h1>支付测试</h1>
        <!--<input id="amount" type="text" name="input" class="input">-->
        <button id="testAjax" type="submit">支付测试</button>
        <button id="testAjax1" type="submit">查询测试</button>
        <!--<button id="testAjax2" type="submit">查询测试</button>-->
</div>
</body>


<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">

    function nowtime(){
        var mydate = new Date();
        var str = "" + mydate.getFullYear();
        var mm = mydate.getMonth()+1
        if(mydate.getMonth()>9){
            str += mm;
        } else{
            str += "0" + mm;
        }

        if(mydate.getDate()>9){
            str += mydate.getDate();
        } else{
            str += "0" + mydate.getDate();
        }

        if(mydate.getHours()>9){
            str += mydate.getHours();
        } else{
            str += "0" + mydate.getHours();
        }

        if(mydate.getMinutes()>9){
            str += mydate.getMinutes();
        } else{
            str += "0" + mydate.getMinutes();
        }

        if(mydate.getSeconds()>9){
            str += mydate.getSeconds();
        } else{
            str += "0" + mydate.getSeconds();
        }

        return str;
    }

    $(function () {
            $('#testAjax').click(function () {
            var jsonData= {
                orderCode:"000000001062",
                version:"01",
                productId:"00000003",
                tranTime:nowtime(),
                timeOut:"20161024120000",
                busiType:1,
                tranAmt:"000000000001",
                currencyCode:156,
                accAttr:0,
                accNo:"6216261000000000018",
                accType:4,
                accName:"全渠道",
                bankName:"cbc",
                bankType:1,
                remark:"pay"
            };

            var saveDataAry=[];
            saveDataAry.push(jsonData);

            $.ajax({
                type: "POST",
                url:  "./paramTest",
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                data:JSON.stringify(saveDataAry),
                success: function (response) {
                    alert("success:"+response.responseText);
                    console.log(responseText);
                },
                error: function (error) {
                    alert("There was an error posting the data to the server: " + error.responseText);
                    console.log(error.responseText);
                }
            });
        });

        $('#testAjax1').click(function () {
            var testData= {
                orderCode:"000000001051",
                version:"01",
                productId:"00000003",
                tranTime:nowtime(),
                timeOut:"20161024120000"
            };

            $.ajax({
                type: "POST",
                url:  "./queryTest",
                contentType :"application/json;charset=UTF-8",
                dataType: "json",
                data:JSON.stringify(testData),
                success: function (response) {
                    alert("success:"+response);
                    console.log(response);
                },
                error: function (error) {
                    alert("There was an error posting the data to the server: " + error.responseText);
                }
            });
        });

        $('#testAjax2').click(function () {
        var saveDataAry=[];
        var data1={"id":5,"name":"四生石","province":"西藏","matchTime":"2015年10月20日","numbers":8};
        var data2={"id":6,"name":"五生石","province":"拉萨","matchTime":"2015年10月21日","numbers":10};
        saveDataAry.push(data1);
        saveDataAry.push(data2);
        $.ajax({
            type:"post",
            url:"./usertest",
            dataType:"json",
            contentType:"application/json;charset=gbk",
            data:JSON.stringify(saveDataAry),
            success:function(data){
            }
        });
        });
    });
</script>
</html>