var myApp = angular.module('login', ['ngCookies'])

myApp.controller('loginController', ['$scope', '$http','$cookies','$cookieStore',function ($scope, $http,$cookies,$cookieStore) {
    var vm = $scope
    vm.flag=false

    //true登录，false  注册

    vm.index="登录"
    vm.change=function () {
        vm.type=!vm.type
        if(vm.type){
            vm.index="登录"
            vm.flag=false
            return
        }
        vm.index="注册"
        vm.flag=true
    }
  vm.imgUrl=""





    request=$http
    vm.register=function () {
        if(vm.username.length<6||vm.username.length>15){
            swal("错误","用户名长度要在6到15位之间","error")
            return
        }
        if(vm.password.length<6||vm.password.length>15){
            swal("错误","密码长度要在6到15位之间","error")
            return
        }
        console.log(vm.password)
        data={
            username:vm.username,
            password:hex_md5(vm.password),
            imgUrl: vm.imgUrl

        }
        console.log(data)
        request({
            method: 'POST',
            url: 'http://localhost:9999/register',
            data: data



        }).then(function (response) {
             console.log(response.data)

            if(response.data.code==500){
                swal("错误","已被注册","error")
                return
            }
             vm.index="登录"
            vm.flag=false


            swal("OK!", "注册成功，请登录", "success");





        }, function (response) {

        });


    }
    function tiao(url){
        window.location.href=url
    }
    vm.login=function () {
        if(vm.username.length<6||vm.username.length>15){
            swal("错误","用户名长度要在6到15位之间","error")
            return
        }
        if(vm.password.length<6||vm.password.length>15){
            swal("错误","密码长度要在6到15位之间","error")
            return
        }
        console.log(vm.password)
        data = {
            username: vm.username,
            password: hex_md5(vm.password),

        }
        console.log(data)
        request({
            method: 'POST',
            url: 'http://localhost:9999/login',
            data: data


        }).then(function (response) {
            // console.log(response.data)
            var rs=response.data
            console.log(response)
            if(rs.code=="200"){
                $cookieStore.put("user",rs)

                tiao("/menu")
            }else{
                swal("错误!", "用户名或密码错误", "error");
            }
            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }


}])